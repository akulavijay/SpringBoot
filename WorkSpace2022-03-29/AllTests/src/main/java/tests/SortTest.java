package tests;

public class SortTest {

	public static void main(String[] args) {
		int[] a = { 15, 2, 17, 1, 3 };
		int maxItr = 4;
		while(maxItr >0) {
			for (int i =0 ;i<maxItr;i++){
				if (a[i] > a[i + 1]) {
					int tmp = a[i];
					a[i] = a[i + 1];
					a[i + 1] = tmp;				
				}
			}
			maxItr--;
		}
		int i = 0;
		while (i < 5) {
			System.out.println(a[i]);
			i++;
		}
	}

}
