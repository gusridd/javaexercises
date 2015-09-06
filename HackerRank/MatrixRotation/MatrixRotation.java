import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MatrixRotation {
	static public void main(String[] args){
		Scanner s = new Scanner(System.in);
		int M = s.nextInt();
		int N = s.nextInt();
		int R = s.nextInt();
		int[][] matrix = new int[M][N];
		for(int i = 0;i<M;i++){
			for(int j = 0; j<N;j++){
				matrix[i][j] = s.nextInt();
			}
		}

		// printMatrix(matrix);

		ArrayList<ArrayList<Integer>> lines = getLines(matrix);
		ArrayList<ArrayList<Integer>> rotatedLines = new ArrayList<ArrayList<Integer>>();
		for(ArrayList<Integer> l : lines){
			// System.out.println(l.toString());
			// System.out.println(rotatedList(l,R).toString());
			rotatedLines.add(rotatedList(l,R));
		}

		int[][] rotatedMatrix = recoverMatrix(rotatedLines,M,N);
		printMatrix(rotatedMatrix);
		
	}

	static public void printMatrix(int[][] matrix){
		int M = matrix.length;
		int N = matrix[0].length;
		String prefix;
		for(int i = 0;i<M;i++){
			prefix = "";
			for(int j = 0; j<N;j++){
				System.out.print(prefix + matrix[i][j]);
				prefix = " ";
			}
			System.out.println();
		}
	}

	static public ArrayList<Integer> rotatedList(ArrayList<Integer> list, int R){
		R = -R;
		ArrayList<Integer> rotated = new ArrayList<Integer>(list.size());
		int i = (list.size()-R)%list.size();
		for(int c = 0; c<list.size();c++){
			rotated.add(list.get(i));
			i = (i+1)%list.size();
		}
		return rotated;
	}

	static public int[][] recoverMatrix(ArrayList<ArrayList<Integer>> lists, int M,int N){
		int[][] matrix = new int[M][N];
		int bound = 0;
		int min = Math.min(M,N);
		while(bound*2 < min){
			// System.out.println("bound:" + bound);
			ArrayList<Integer> list = lists.get(bound);
			// System.out.println("right");
			int count = 0;
			for(int j = bound; j<N-bound; j++){
				// list.add(matrix[bound][j]);
				matrix[bound][j] = list.get(count++);
				// System.out.println(list.toString());
			}
			// System.out.println("down");
			for(int i = bound+1; i<M-bound; i++){
				// list.add(matrix[i][N-bound-1]);
				matrix[i][N-bound-1] = list.get(count++);
				// System.out.println(list.toString());
			}
			// System.out.println("left");
			for(int j = N-bound-2; j>bound; j--){
				// list.add(matrix[M-bound-1][j]);
				matrix[M-bound-1][j] = list.get(count++);
				// System.out.println(list.toString());
			}
			// System.out.println("up");
			for(int i = M-bound-1; i>bound; i--){
				// list.add(matrix[i][bound]);
				matrix[i][bound] = list.get(count++);
				// System.out.println(list.toString());
			}
			bound++;
		}
		return matrix;
	}

	static public ArrayList<ArrayList<Integer>> getLines(int[][] matrix){
		ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();	
		int bound = 0;
		int M = matrix.length;
		int N = matrix[0].length;
		// System.out.println("M:" + M);
		// System.out.println("N:" + N);
		int min = Math.min(M,N);
		while(bound*2 < min){
			// System.out.println("bound:" + bound);
			ArrayList<Integer> list = new ArrayList<Integer>();
			// System.out.println("right");
			for(int j = bound; j<N-bound; j++){
				list.add(matrix[bound][j]);
				// System.out.println(list.toString());
			}
			// System.out.println("down");
			for(int i = bound+1; i<M-bound; i++){
				list.add(matrix[i][N-bound-1]);
				// System.out.println(list.toString());
			}
			// System.out.println("left");
			for(int j = N-bound-2; j>bound; j--){
				list.add(matrix[M-bound-1][j]);
				// System.out.println(list.toString());
			}
			// System.out.println("up");
			for(int i = M-bound-1; i>bound; i--){
				list.add(matrix[i][bound]);
				// System.out.println(list.toString());
			}
			res.add(list);
			bound++;
		}
		return res;
	}
}