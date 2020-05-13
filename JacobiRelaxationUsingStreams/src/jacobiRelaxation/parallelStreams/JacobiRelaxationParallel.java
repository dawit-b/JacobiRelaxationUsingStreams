package jacobiRelaxation.parallelStreams;

import java.util.Arrays;
import java.util.stream.IntStream;

public class JacobiRelaxationParallel {

	public static void main(String[] args) {
		final int n = 10000;
		float [][] A = new float [n][n];
		float [][] B = new float [n][n];
		double tolerance = 0.1;
		// initializing array A
		A = initalizeArray(A);
		
		
		
		
		
		
	}
	
	public static IntStream arrayToStream(int[][] array) {
		IntStream arrayStream = Arrays.stream(array).flatMapToInt(a -> Arrays.stream(a));
		return arrayStream;
	}
	
	public static float[][] initalizeArray(float[][] A){
		int length = A.length;
		for (int i = 1; i < length; i++) {
			for (int j = 1; j < length; j++) {
				A[i][j] = 0;
			}
		}
		for (int i = 0; i < length; i++) {
			A[i][0] = 10;
			A[i][length - 1] = 10;
			A[0][i] = 10;
			A[length - 1][i] = 10;
		}
		return A;
		
	}

}
