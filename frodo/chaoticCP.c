/* Chaotic IPS in Z^2 */

/* Copyright 2008 Daniel Remenik */

/* A good part of this program is based in simulations by Nicolas Lanchier */
/* See <http://math.la.asu.edu/~lanchier/> */
/*      and <http://math.la.asu.edu/~lanchier/simulations.html> */

/* This program is free software: you can redistribute it and/or modify */
/* it under the terms of the GNU General Public License as published by */
/* the Free Software Foundation, either version 3 of the License, or */
/* (at your option) any later version. */

/* This program is distributed in the hope that it will be useful, */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the */
/* GNU General Public License for more details. */

/* You should have received a copy of the GNU General Public License */
/* along with this program.  If not, see <http://www.gnu.org/licenses/>. */


#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <math.h>
#include <gtk/gtk.h>
#include <gdk/gdkrgb.h>
#include <glade/glade.h>
#include <glib.h>
#include <string.h>

#define N 400
#define T 750000
#define square 1
#define P 1
#define P2 100
#define P3 100

FILE *ofp;
char *ofile = "evcv.csv";

GtkWidget *Simulation_window;
GtkWidget *Drawing_area;

guchar config[3*square*square*N*N];

gfloat beta = 2.25;
gfloat rho = 0.1;
gfloat epsilon = 0.00001;
gint range = 5;
gint range_ep = 1;

gboolean STAR = FALSE;
gboolean DRAW = FALSE;

int site;
int x,y;
int a,b;
int eta[N*N];
int etaNew[N*N];
int display;
int display2;
int display3;
float alea;
float runtime;
int ones = 0;

void
on_range_spnbtn_value_changed           (GtkSpinButton   *spinbutton,
  gpointer         user_data)
{
  range = gtk_spin_button_get_value(spinbutton);
}

void
on_range_ep_spnbtn_value_changed           (GtkSpinButton   *spinbutton,
  gpointer         user_data)
{
  range_ep = gtk_spin_button_get_value(spinbutton);
}

void
on_rho_spnbtn_value_changed           (GtkSpinButton   *spinbutton,
  gpointer         user_data)
{
  rho = gtk_spin_button_get_value(spinbutton);
}

void
on_beta_spnbtn_value_changed           (GtkSpinButton   *spinbutton,
 gpointer         user_data)
{
  beta = gtk_spin_button_get_value(spinbutton);
}

void
on_epsilon_spnbtn_value_changed           (GtkSpinButton   *spinbutton,
 gpointer         user_data)
{
  epsilon = gtk_spin_button_get_value(spinbutton);
}

float rnd () {
  return (float)rand()/RAND_MAX;
}

/* Chooses a neighbor within range 'range' at random, using the L^infinity norm */
int neighbor (int site, float alea) {

  int y = floor((2*range+1)*(2*range+1)*alea);

  int a = y/(2*range+1)-range;
  int b = y%(2*range+1)-range;

  int x = site;
  x = x-x%N+(x%N+a+N)%N;
  x = x%N+((x/N+b+N)%N)*N;

  return x ;

}

/* Determines the y-th neighbor within range 'r' using the L^infinity norm */
int detNeighbor (int site, int y, int r) {

  int a = y/(2*r+1)-r;
  int b = y%(2*r+1)-r;

  int x = site;
  x = x-x%N+(x%N+a+N)%N;
  x = x%N+((x/N+b+N)%N)*N;

  return x;

}

void draw_config() {
  GdkGC *pen;
  GdkPixmap *image;
  image = gdk_pixmap_new(Drawing_area -> window, square*N, square*N, -1);
  pen = gdk_gc_new(image);
  gdk_draw_rgb_image(image, pen, 0, 0, square*N, square*N, GDK_RGB_DITHER_NORMAL, config, 3*square*N);
  gdk_window_set_back_pixmap (Drawing_area -> window, image, FALSE);
  gdk_gc_destroy(pen);
  gdk_pixmap_unref(image);
  gdk_window_clear(Drawing_area -> window);
}

void killCluster(int site) {
  eta[site] = 0;
  ones--;
  int neigh, i;
  for (i = 0; i < (2*range_ep+1)*(2*range_ep+1); i++) {
    neigh = detNeighbor(site,i,range_ep);
    if (eta[neigh]== 1)
      killCluster(neigh);
  }
  return;
}

int poisson(float beta) {
  int i = 0;
  int factorial = 1;
  alea = rnd();
  float cum = exp(-beta);
  while (TRUE) {
    if (alea < cum)
      return i;
    i++;
    factorial = factorial * i;
    //printf("beta: %f factorial: %d, cum: %f\n",beta,factorial,cum);
    cum = cum + exp(-beta)*pow(beta,i)/factorial;
  }
}			

void process () {

  time_t beginning;
  time(&beginning);
  srand(beginning);

  int nNeigh = (2*range+1)*(2*range+1); 
  
  if (STAR == TRUE) {
    ones = 0;
    for (site = 0; site < N*N; site ++) {  
      eta [site] = 0;
      etaNew[site] = 0;
      alea = rnd();
      if (alea < rho) {
       eta[site]++;
       ones++;
     }
   }
 }

  /* Used to measure how long it takes to run each part of the simulation. */
  /* Ignored when withClock = FALSE */
 gboolean withClock = FALSE;
 clock_t clock1, clock2;
 double tContact = 0;
 double tEpidemic = 0;

 while (DRAW == TRUE && runtime <= T) {

  display++;
  display3++;

  ones = 0;

  if (withClock)
    clock1 = clock();

  int i,j,children,neigh;

  for (site = 0; site < N*N; site++) {
    if (eta[site] == 1) {
      children = poisson(beta);
      for (i = 1; i <= children; i++) {
       j = floor(rnd()*nNeigh);
       neigh = detNeighbor(site,j,range);
       if (etaNew[neigh] == 0) {
         etaNew[neigh] = 1;
         ones++;
       }
     }
   }
 }
 
 if (withClock) {
  clock2 = clock();
  tContact = tContact+((double)(clock2-clock1))/CLOCKS_PER_SEC;
  clock1=clock2;
}

memcpy(eta,etaNew,sizeof(eta));
for (site = 0; site < N*N; site++) {
  etaNew[site] = 0;
  if (rnd() < epsilon && eta[site] == 1)
   killCluster(site);
}

if (withClock) {
  clock2 = clock();
  tEpidemic = tEpidemic+((double)(clock2-clock1))/CLOCKS_PER_SEC;
  clock1=clock2;
}

runtime++;

if (display3 == P3) {
  fprintf (ofp,"%f,%f\n",runtime,((float)ones)/((float)N*N));
  display3 = 0;
}

if (display == P) {
  
  display = 0 ;
  
  for (site = 0; site < N*N; site++) {

   x = site%N ; 
   y = site/N;

   for (a = 0 ; a < square; a++) {
     for (b = 0; b < square; b++) {
       switch (eta[site]) {
         case 0:
         config[3*((square*x+a)+square*(square*y+b)*N)+0] = 255;
         config[3*((square*x+a)+square*(square*y+b)*N)+1] = 255;
         config[3*((square*x+a)+square*(square*y+b)*N)+2] = 255;
         break;
         case 1:
         config[3*((square*x+a)+square*(square*y+b)*N)+0] = 0;
         config[3*((square*x+a)+square*(square*y+b)*N)+1] = 180;
         config[3*((square*x+a)+square*(square*y+b)*N)+2] = 0;
         break;
       }
     }
   }
 }
 
 display2++;
 if (display2 == P2) {
   if (withClock)
     printf("1's = %f     runtime = %f\ntContact = %f     tEpidemic = %f\n\n",((float)ones)/((float)N*N),runtime,tContact,tEpidemic) ;
   else
     printf("1's = %f     runtime = %f\n",((float)ones)/((float)N*N),runtime) ;	
   display2 = 0;
 }

 draw_config();
 while (gtk_events_pending ()) gtk_main_iteration ();
 
}

}

}


void GO(GtkWidget *widg, GdkEventButton *ev) {

  ofp = fopen(ofile, "w");
  
  STAR = TRUE;
  DRAW = TRUE;
  runtime = 0;
  display = 0;
  display2 = 0;
  display3 = 0;
  process();

}

void STOP(GtkWidget *widg, GdkEventButton *ev) {

  if (DRAW == TRUE)
    DRAW = FALSE;

  else {
    STAR = FALSE;
    DRAW = TRUE;
    process ();
  }

}

int main(int argc, char *argv[]) {

  GladeXML *xml;

  gtk_init(&argc, &argv);
  gdk_rgb_init();
  
  Simulation_window = gtk_window_new(GTK_WINDOW_TOPLEVEL);
  gtk_window_set_title(GTK_WINDOW(Simulation_window)," Chaotic CP ");
  Drawing_area = gtk_drawing_area_new();
  gtk_container_add(GTK_CONTAINER(Simulation_window),Drawing_area);
  gtk_drawing_area_size(GTK_DRAWING_AREA(Drawing_area),square*N,square*N);
  gtk_widget_realize(Drawing_area);
  gtk_widget_show_all(Simulation_window);

  gtk_signal_connect (GTK_OBJECT(Simulation_window),"delete_event",(GtkSignalFunc)gtk_exit,NULL);
  gtk_signal_connect (GTK_OBJECT(Simulation_window),"destroy",(GtkSignalFunc)gtk_exit,NULL);
  gtk_signal_connect (GTK_OBJECT(Drawing_area),"expose_event",(GtkSignalFunc)draw_config,NULL);
  
  xml = glade_xml_new("gui.glade",NULL,NULL);
  glade_xml_signal_autoconnect(xml);

  GtkWidget *wd;
  wd = glade_xml_get_widget(xml,"range_spnbtn");
  gtk_spin_button_set_value(GTK_SPIN_BUTTON(wd),range);
  wd = glade_xml_get_widget(xml,"range_ep_spnbtn");
  gtk_spin_button_set_value(GTK_SPIN_BUTTON(wd),range_ep);
  wd = glade_xml_get_widget(xml,"beta_spnbtn");
  gtk_spin_button_set_value(GTK_SPIN_BUTTON(wd),beta);
  wd = glade_xml_get_widget(xml,"epsilon_spnbtn");
  gtk_spin_button_set_value(GTK_SPIN_BUTTON(wd),epsilon);
  wd = glade_xml_get_widget(xml,"rho_spnbtn");
  gtk_spin_button_set_value(GTK_SPIN_BUTTON(wd),rho);
  
  gtk_main();

  return 0;
  
}
