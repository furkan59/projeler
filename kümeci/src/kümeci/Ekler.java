package k�meci;


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
	    int ge�ici;
	    int x = A[r];
	    int i = altLimit-1;

	    for(int elemanNo=altLimit; elemanNo<=r-1; elemanNo++)
	    {
	        if(A[elemanNo]<=x)
	        {
	            i++;
	            ge�ici=A[i];
	            A[i]=A[elemanNo];
	            A[elemanNo]=ge�ici;
	        }
	    }
	    ge�ici=A[i+1];
	    A[i+1]=A[r];
	    A[r]=ge�ici;
	    return i+1;
	}




}