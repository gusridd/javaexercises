import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import java.util.Random;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Chaos {


	static public void main(String []args) throws FileNotFoundException{
		//JFrame frame = new JFrame("FrameDemo");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//JLabel emptyLabel = new JLabel("Empty");
		//frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		//frame.pack();
		//frame.setVisible(true);

		Calc c = new Calc();
		//c.GO();

		EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaUI().setVisible(true);
            }
        });
	}
}

class JavaUI extends JFrame {


	private DrawPanel drawPanel;

	public JavaUI(){
		super("JavaUI");
		initComponents();
	}

	private void initComponents() {
        // we want a custom Panel2, not a generic JPanel!
        drawPanel = new DrawPanel();

        drawPanel.setBackground(new java.awt.Color(255, 255, 255));
        drawPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        // drawPanel.addMouseListener(new MouseAdapter() {
        //     public void mousePressed(MouseEvent evt) {
        //         drawPanelMousePressed(evt);
        //     }
        //     public void mouseReleased(MouseEvent evt) {
        //         drawPanelMouseReleased(evt);
        //     }
        // });
        // drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
        //     public void mouseDragged(MouseEvent evt) {
        //         drawPanelMouseDragged(evt);
        //     }
        // });

        // add the component to the frame to see it!
        this.setContentPane(drawPanel);
        // be nice to testers..
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }// </editor-fold>



	class DrawPanel extends JPanel {

		int i = 20;

        DrawPanel() {
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(420,420));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawString("BLAH", i, 20);
            i++;
            g.drawRect(200, 200, 200, 200);
        }
        @Override
        public void paint(Graphics g){
        	super.paint(g);
        	//g.drawString("BLAH", i, 20);
            i++;
        }
    }
}

class Calc {

	File ofp;
	PrintStream printStream;
	Random rnd;
	JavaUI ui;

	public Calc() throws FileNotFoundException{
		ofp = new File("evcv.csv");
		printStream = new PrintStream(ofp);
	}

	final static private int N = 20;
	final static private int T = 750000;
	final static private int square = 1;
	final static private int P = 1;
	final static private int P2 = 100;
	final static private int P3 = 100;

	final static private double beta = 2.25;
	final static private double rho = 0.1;
	final static private double epsilon = 0.00001;

	final static int range = 5;
	final static int range_ep = 1;

	static boolean STAR = false;
	static boolean DRAW = false;

	char[] config = new char[3*square*square*N*N];

	int site;
	int x,y;
	int a,b;
	int eta[] = new int[N*N];
	int etaNew[] = new int[N*N];
	int display;
	int display2;
	int display3;
	double alea;
	double runtime;
	int ones = 0;



	public double rnd() {
  		//return (double)rand()/RAND_MAX;
		return rnd.nextDouble();
	}

	/* Chooses a neighbor within range 'range' at random, using the L^infinity norm */
	private int neighbor (int site, double alea) {

		int y = (int)Math.floor((2*range+1)*(2*range+1)*alea);

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

	// void killCluster(int site) {
	// 	eta[site] = 0;
	// 	ones--;
	// 	int neigh, i;
	// 	for (i = 0; i < (2*range_ep+1)*(2*range_ep+1); i++) {
	// 		neigh = detNeighbor(site,i,range_ep);
	// 		if (eta[neigh]== 1)
	// 			killCluster(neigh);
	// 	}
	// 	return;
	// }

	void killCluster(int site) {
		int neigh, i;
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(site);
		while(!l.isEmpty()){
			site = l.poll();
			for (i = 0; i < (2*range_ep+1)*(2*range_ep+1); i++) {
				neigh = detNeighbor(site,i,range_ep);
				if(eta[neigh]== 1){
					l.add(neigh);
				}
			}
		}
		return;
	}

	int poisson(double beta) {
		int i = 0;
		int factorial = 1;
		alea = rnd();
		double cum = Math.exp(-beta);
		while (true) {
			if (alea < cum)
				return i;
			i++;
			factorial = factorial * i;
			cum = cum + Math.exp(-beta)*Math.pow(beta,i)/factorial;
		}
	}

	void GO(){
		//ofp = fopen(ofile, "w");
		STAR = true;
		DRAW = true;
		runtime = 0;
		display = 0;
		display2 = 0;
		display3 = 0;
		process();
	}

	void process () {

		long beginning = System.nanoTime();
		//time(&beginning);

		rnd = new Random(beginning);
		//srand(beginning);

		int nNeigh = (2*range+1)*(2*range+1); 

		if (STAR == true) {
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
		/* Ignored when withClock = false */
		boolean withClock = false;
		long clock1, clock2;
		clock1 = System.nanoTime();
		clock2 = System.nanoTime();
		double tContact = 0;
		double tEpidemic = 0;

		while (DRAW == true && runtime <= T) {

			//System.out.println(Arrays.toString(config));
			printStream.println(Arrays.toString(config));

			display++;
			display3++;

			ones = 0;

			if (withClock)
				clock1 = System.nanoTime();

			int i,j,children,neigh;

			for (site = 0; site < N*N; site++) {
				if (eta[site] == 1) {
					children = poisson(beta);
					for (i = 1; i <= children; i++) {
						j = (int) Math.floor(rnd()*nNeigh);
						neigh = detNeighbor(site,j,range);
						if (etaNew[neigh] == 0) {
							etaNew[neigh] = 1;
							ones++;
						}
					}
				}
			}

			if (withClock) {
				clock2 = System.nanoTime();
				//tContact = tContact+((double)(clock2-clock1))/CLOCKS_PER_SEC;
				tContact = tContact+((double)(clock2-clock1));
				clock1=clock2;
			}

			//memcpy(eta,etaNew);
			eta = Arrays.copyOf(etaNew,etaNew.length);

			for (site = 0; site < N*N; site++) {
				etaNew[site] = 0;
				if (rnd() < epsilon && eta[site] == 1)
					killCluster(site);
			}

			if (withClock) {
				clock2 = System.nanoTime();
				//tEpidemic = tEpidemic+((double)(clock2-clock1))/CLOCKS_PER_SEC;
				tEpidemic = tEpidemic+((double)(clock2-clock1));
				clock1=clock2;
			}

			runtime++;

			if (display3 == P3) {
				printStream.printf("%f,%f\n",runtime,((double)ones)/((double)N*N));
				//System.out.printf(ofp,"%f,%f\n",runtime,((double)ones)/((double)N*N));
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
						System.out.printf("1's = %f     runtime = %f\ntContact = %f     tEpidemic = %f\n\n",((double)ones)/((double)N*N),runtime,tContact,tEpidemic) ;
					else
						System.out.printf("1's = %f     runtime = %f\n",((double)ones)/((double)N*N),runtime);	
					display2 = 0;
				}

				//draw_config();
				//while (gtk_events_pending ()) gtk_main_iteration ();

			}

		}

	}	
}