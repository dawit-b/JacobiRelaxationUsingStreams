package jacobiRelaxation.parallelStreams;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class JacobiRelaxationParallel {
	static double change = 0;
	public static void main(String[] args) {
		final int n = 10000;
		float [][] A = new float [n][n];
		//float [][] B = new float [n][n];
		double tolerance = 0.1;
		
		// initializing array A
		A = initalizeArray(A);
		float[] X = flatArray(A);
		float[] Y = flatArray(A);
		
		Date start_time = new Date();
		do {
			
			OptionalDouble diff = IntStream.range(n, n*n-n).parallel()
			.map(i -> {
				Y[i] = (X[i-n] + X[i+n] + X[i+1] + X[i-1]) / 4;
				return i;
			})
			.mapToDouble(i -> { change = Math.abs(X[i] - Y[i]);
						X[i] = Y[i];
				return change;
			})
			.max();
			
			change = diff.getAsDouble();
		}while(change > tolerance);
		
		Date end_time = new Date();
		System.out.println(change);
		System.out.println("Parallel Time Elapsed: " + (end_time.getTime() - start_time.getTime()));
	}
	/*
	 * converts a 2D array into a stream
	 */
	public static IntStream arrayToStream(int[][] array) {
		IntStream arrayStream = Arrays.stream(array).flatMapToInt(a -> Arrays.stream(a));
		return arrayStream;
	}
	
	/*
	 * initializes an array with all margins 10 and 0 inside
	 */
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
	/*
	 * converts 2D array to 1D array with all elements
	 */
	public static float[] flatArray(float[][] A) {
		float [] B = new float[A.length*A.length];
		int index = 0;
		for(int i =0; i<A.length; i++) {
			for(int j=0; j<A.length; j++) {
				B[index++] =  A[i][j];
			}
		}
		return B;
	}

}
