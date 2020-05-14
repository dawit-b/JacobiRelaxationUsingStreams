package jacobiRelaxation.serial;

import java.util.Date;

public class JacobiRelaxationSerial {

	public static void main(String[] args) {
		final int n = 10000;
		float [][] A = new float [n][n];
		float [][] B = new float [n][n];
		double tolerance = 0.1;
		
		// initializing array A
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < n; j++) {
				A[i][j] = 0;
			}
		}
		for (int i = 0; i < n; i++) {
			A[i][0] = 10;
			A[i][n - 1] = 10;
			A[0][i] = 10;
			A[n - 1][i] = 10;
		}
		
		boolean done = false; 
		double change = 0;
		int counter = 0;
		Date start_time = new Date();
		do {
			counter++;
			for(int i=1; i< n-1; i++) {
				for(int j=1; j< n-1; j++) {
					B[i][j] = (A[i-1][j] + A[i+1][j]+A[i][j-1]+A[i][j+1]) / 4;
				}
				
			}
			
			done = true;
			for(int k=1; k<n-1; k++) {
				for(int l=1; l<n-1; l++){
					change = Math.abs(B[k][l]-A[k][l]);
					if(change > tolerance) done = false;
					
				}
				
			}
			for (int i = 1; i < n-1; i++) {
				for (int j = 1; j < n-1; j++) {
					A[i][j] = B[i][j];
				}
			}
			
			
			
		}while(!done);
		
		Date end_time = new Date();
		System.out.println("Number of iteration: " + counter);
		System.out.println("Sequential code time elapsed: " + (end_time.getTime() - start_time.getTime()));

	}
}
