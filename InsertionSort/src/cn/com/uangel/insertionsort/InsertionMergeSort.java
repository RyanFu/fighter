package cn.com.uangel.insertionsort;

public class InsertionMergeSort {

	
	public static void main(String [] args){
		int A[]={8,2,4,9,3,6,3,2,4,9,3,6,3,2,4,9,3,6,3,2,4,9,3,6,3};
		sort(A);
		for(int i=0;i<A.length;i++){
			System.out.print(A[i]);
		}
	}
	
	
	public static final int K = 7;// 当k=7的时候用插入排序

	public static void sort(int[] A) {
		mergeSort(A, 0, A.length - 1);
	}

	private static void mergeSort(int[] A, int p, int r) {
		if (r - p + 1 <= K) {
			InsertionSort.sort(A, p + 1, r + 1);
			System.out.println("insertion");
		} else {
			System.out.println("merge");
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
		}
	}

	private static void merge(int[] A, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1 + 1];
		int[] R = new int[n2 + 1];
		for (int i = 0; i < n1; i++) {
			L[i] = A[p + i];
		}
		L[n1] = Integer.MAX_VALUE; // put the sentinel
		for (int j = 0; j < n2; j++) {
			R[j] = A[q + 1 + j];
		}
		R[n2] = Integer.MAX_VALUE;
		int i = 0;
		int j = 0;
		for (int k = p; k <= r; k++) {
			if (L[i] < R[j]) {
				A[k] = L[i];
				i = i + 1;
			} else {
				A[k] = R[j];
				j = j + 1;
			}
		}
	}
}
