package jacobiRelaxation.parallelStreams;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class JacobiRelaxationParallel {
	static final int n = 10000;
	static float [][] A = new float [n][n];
	static float [][] B = new float [n][n];
	static double change = 0;
	public static void main(String[] args) {
		
		double tolerance = 0.1;
		
		// initializing array A
		A = initalizeArray(A);
		
		int counter = 0;
		Date start_time = new Date();

		do {
			counter++;
			int iter = IntStream.range(1, n-1).parallel()
			.map(i -> {
				for(int j=1; j< n-1; j++) {
					B[i][j] = (A[i-1][j] + A[i+1][j] + A[i][j-1] + A[i][j+1]) / 4;
				}
				return i;
				
			}).sum();
			
			OptionalDouble diff = IntStream.range(1, n-1).parallel()
			.mapToDouble(i -> {
				double maxChange = 0;
				for( int k=1; k<n-1; k++) {
					change = Math.abs(B[i][k] - A[i][k]);
					A[i][k] = B[i][k];
					if( change > maxChange) maxChange = change;
				}
				return maxChange;
			})
			.max();
			
			change = diff.getAsDouble();
		}while(change > tolerance);
		
		Date end_time = new Date();
		System.out.println("Number of iteration: " + counter);
		System.out.println("Parallel Stream Time Elapsed: " + (end_time.getTime() - start_time.getTime()));
		
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


}
