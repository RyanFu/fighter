package cn.com.uangel.insertionsort;

public class InsertionSort {

	 
	
	public static void main(String [] args)
	{
		int A[]={8,2,4,9,3,6};
		sort(A,1,A.length);
		
	}
	/**
	 * 
	 * @param A 待排序数组
	 * @param p java数组由0开始，所以p初始化为1 ，也就是无序队列里的第一个元素
	 * @param length_A 数组的长度
	 */
	public static void sort(int A[],int p,int length_A)
	{
		for(int j=p;j<length_A;j++)
		{
			int key=A[j];
			System.out.println(key);
			int i=j-1;
			while (i>-1&&A[i]>key)
			{
				A[i+1]=A[i];
				i=i-1;
				System.out.println("------- i :  "+ i);
			}
			A[i+1]=key;
		}

		for (int m=0;m<A.length;m++)
		{
			System.out.print(A[m]);
		}
		
	}
}
