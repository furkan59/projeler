package kümeci;


public class Ekler {



	public static void quickSort(int A[],int p, int r)
	{
	    int q;
	    if(p<r)
	    {
	        q=partition(A,p, r);
	        quickSort(A,p, q-1);
	        quickSort(A,q+1, r);
	    }
	}
	public static int partition(int A[],int altLimit, int r){
	    int geçici;
	    int x = A[r];
	    int i = altLimit-1;

	    for(int elemanNo=altLimit; elemanNo<=r-1; elemanNo++)
	    {
	        if(A[elemanNo]<=x)
	        {
	            i++;
	            geçici=A[i];
	            A[i]=A[elemanNo];
	            A[elemanNo]=geçici;
	        }
	    }
	    geçici=A[i+1];
	    A[i+1]=A[r];
	    A[r]=geçici;
	    return i+1;
	}




}