import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Random;

public class p107 {

	final static int INFINITE = Integer.MAX_VALUE;
	final static int MATRIX_SIZE = 40;
	final static int NON_EDGE = -1;

	static public void main(String args[]) throws Exception{
		Scanner s = new Scanner(new File("p107_network.txt"));
		s.useDelimiter("[,\n\r\n\r]");
		int matrix[][] = new int[MATRIX_SIZE][MATRIX_SIZE];
		int i = 0;
		int j = 0;
		while(s.hasNext()){
			// Infinite case
			if(s.hasNext("-")){
				matrix[i][j] = INFINITE;
				s.next();
			} else {
				matrix[i][j] = s.nextInt();
				if(matrix[i][j] < 0){
					throw new Exception("Negative Number found: " + matrix[i][j]);
				}
			}
			i = (i+1)%MATRIX_SIZE;
			if(i == 0){
				j++;
			}
		}
		// The matrix is loaded with values.

		// Check for symmetry
		for(i = 0; i<MATRIX_SIZE; i++){
			for(j = 0; j<MATRIX_SIZE; j++){
				if(matrix[i][j] != matrix[j][i]){
					throw new Exception("Non symmetrical matrix");
				}
			}
		}

		// We calculate the original weight of the graph
		int originalWeight = 0;
		for(i = 0; i < matrix.length; i++){
			for(j = i + 1; j < matrix.length; j++){
				originalWeight += (matrix[i][j] != INFINITE) ? matrix[i][j] : 0;
			}
		}
		System.out.println("originalWeight: " + originalWeight);

		// Start of Prim's algorithm

		// Set of vertices, true if is has been included in the MST, false if not
		boolean Q[] = new boolean[MATRIX_SIZE];

		// We use a priority queue to store edge with their weights
		PriorityQueue<Tuple3<Integer,Integer,Integer>> queue 
			= new PriorityQueue<Tuple3<Integer,Integer,Integer>>();

		// We use an array list to store the selected edges
		ArrayList<Tuple3<Integer,Integer,Integer>> finalEdges = 
			new ArrayList<Tuple3<Integer,Integer,Integer>>(MATRIX_SIZE-1);

		// We arbitrarely pick the first element as a starting point
		Random r = new Random();
		int pick = r.nextInt(MATRIX_SIZE);
		Q[pick] = true;
		// We add all vertices starting at that node to the queue
		for(j = 0; j < MATRIX_SIZE; j++){
			if(matrix[pick][j] != INFINITE){
				Tuple3<Integer,Integer,Integer> t = new Tuple3<Integer,Integer,Integer>(matrix[pick][j], pick, j);
				System.out.println("adding to queue: " + t.toString());
				queue.add(t);
			}
		}

		int reducedWeight = 0;

		while(finalEdges.size() < MATRIX_SIZE-1){
			// get the minimum edge
			Tuple3<Integer,Integer,Integer> t = queue.poll();
			// If any of the two connected vertices are included, 
			// then skip the elements as it generates a cycle.
			while(Q[t.y] && Q[t.z]){
				System.out.println("Cycle with: " + t.toString());
				t = queue.poll();
			}
			System.out.println("retrieved from queue: " + t.toString());
			// add the edge to the result
			finalEdges.add(t);
			// count the weigth of the edge
			reducedWeight += t.x;
			// add new edges to the queue
			int row = t.z;
			for(i = 0; i<MATRIX_SIZE; i++){
				Tuple3<Integer,Integer,Integer> aux = new Tuple3<Integer,Integer,Integer>(matrix[row][i], row, i);				
				if(!Q[i] && matrix[row][i] != INFINITE){
					System.out.println("adding to queue: " + aux.toString());
					queue.add(aux);
				}
			}
			Q[row] = true;
		}

		System.out.println("Q: ");
		System.out.println(java.util.Arrays.toString(Q));

		for(i = 0;i<finalEdges.size(); i++){
			System.out.println(finalEdges.get(i).toString());
		}

		System.out.println("reducedWeight: " + reducedWeight);
		System.out.println("maxSavings: " + (originalWeight - reducedWeight));

	}
}

class Tuple3<X extends Comparable<? super X>,Y,Z> implements Comparable<Tuple3<X,Y,Z>>{
	public final X x; 
	public final Y y; 
	public final Z z; 
	public Tuple3(X x, Y y, Z z) { 
		this.x = x; 
		this.y = y; 
		this.z = z;
	}
	public int compareTo(Tuple3<X, Y, Z> t){
		return this.x.compareTo(t.x);
	}

	public String toString(){
		return "(" + y + ", " +  z + ") = " + x;
	}
}