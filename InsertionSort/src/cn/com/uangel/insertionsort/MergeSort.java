package cn.com.uangel.insertionsort;

public class MergeSort {

	public static void main(String[] args) {
		int A[] = { 8, 2, 4, 9, 3, 6, 3 };
		mergeSort(A, 0, A.length - 1);
	}

	/**
	 * Sort A[p .. r] with Merge Sort
	 */
	public static void mergeSort(int[] A, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(A, p, q);
			mergeSort(A, q + 1, r);
			merge(A, p, q, r);
		}
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
		}
		System.out.println("每一分支结束");
	}

	/**
	 * merge sorted A[p .. q] and A[q+1 .. r] into A[p .. r]
	 */
	private static void merge(int[] A, int p, int q, int r) {
		int[] L = new int[q - p + 1];
		int[] R = new int[r - q];
		// System.out.println("q - p + 1 ::"+(q - p + 1)+"L.length: "+L.length);
		for (int i = 0; i < L.length; i++) {
			L[i] = A[p + i];
		}
		for (int j = 0; j < R.length; j++) {
			R[j] = A[q + 1 + j];
		}

		int i = 0;
		int j = 0;
		int k = p;
		while (i < L.length && j < R.length) {
			if (L[i] < R[j]) {
				A[k++] = L[i++];
			} else {
				A[k++] = R[j++];
			}
		}
		// for(int m=0;m<L.length;m++){
		// System.out.println("L中的元素"+L[m]);
		// }
		//
		// for(int n=0;n<R.length;n++){
		// System.out.println("R中的元素"+R[n]);
		// }
		// System.out.println("i:"+i);
		// System.out.println("j:"+j);
		while (i < L.length) {
			A[k++] = L[i++];
		}
		while (j < R.length) {
			A[k++] = R[j++];
		}
	}
}
