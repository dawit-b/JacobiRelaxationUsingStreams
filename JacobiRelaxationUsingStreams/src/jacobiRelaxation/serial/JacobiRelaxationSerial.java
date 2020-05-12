package jacobiRelaxation.serial;

public class JacobiRelaxationSerial {

	public static void main(String[] args) {
		final int n = 32;
		int[][] A = new int[n][n];
		int[][] B = new int[n][n];
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
		
		

	}
	/* prints 2D array
	 * @param int array
	 * 
	 */
	public static void printArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[i][j] + "  ");
			}
			System.out.println();
			
		}
	}

}
