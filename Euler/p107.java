import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

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

		// We calculate the original weight of the graph
		int originalWeight = 0;
		for(i = 0; i < matrix.length; i++){
			for(j = i + 1; j < matrix.length; j++){
				originalWeight += (matrix[i][j] != INFINITE) ? matrix[i][j] : 0;
			}
		}
		System.out.println("originalWeight: " + originalWeight);

		// Start of Prim's algorithm

		// Minimum cost to reach each node
		int C[] = new int[MATRIX_SIZE];
		// Vertices that has the minimum cost
		int E[] = new int[MATRIX_SIZE];
		// Set of vertices, true if is has been included in the MST, false if not
		boolean Q[] = new boolean[MATRIX_SIZE];

		// at the beggining reach each node is reacheable by a special node with infinite cost
		for(i = 0; i < MATRIX_SIZE; i++){
			C[i] = INFINITE;
			E[i] = NON_EDGE;
		}

		
		for

		for(i = 0; i < matrix.length; i++){
			for(j = 0; j < matrix.length; j++){
				System.out.print(matrix[i][j] + ",");
			}
			System.out.println("");
		}

	}
}