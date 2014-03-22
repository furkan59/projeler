package k�meci;

import java.util.Random;

import veriYap�lar�.BoolVekt�r;

public class RassalDizi {
      private BoolVekt�r i�aret ;
      private int trueSay�s�;
      private float [] dizi ; // rassal say�lar
      private int yer = 0;
      public RassalDizi (int boy){
    	  i�aret = new BoolVekt�r(boy,false);
    	  trueSay�s� =0;
    	  dizi = new float [boy];
    	  rassalSay�lar��ret();
      }
      private void rassalSay�lar��ret(){
    	  Random rassal = new Random();
    	  for(int elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		  dizi [elemanNo] = rassal.nextFloat();
    	  }
      }
      
      private void de�i�(int yer) throws Exception{
    	  if(i�aret.eri�(yer)){
    		  throw new Exception("true olan bir yere de�i�im yap�lamaz s�ra No : " + yer + "\n");
    	  }
    	  i�aret.de�i�(yer, true);
    	  trueSay�s� ++;
      }
      private int eri�(int ilerleme) throws Exception{
    	  int ilerlemeMiktar� = 0;
    	  if(ilerleme == 0 && i�aret.eri�(0) == false){
    		  de�i�(0);
    		  return 0;
    	  }
    	  else{ // ilerleme arada ilken
    		  int elemanNo ;
    		  for(elemanNo = 0;elemanNo<i�aret.boy();elemanNo++){
        		  if(i�aret.eri�(elemanNo) == false){ // bunu g�r
        			  ilerlemeMiktar� ++;
        		  }
        		  if(ilerlemeMiktar� == ilerleme){
        			  de�i�(elemanNo);
        			  return elemanNo;
        		  }
        	  }
    	  }
    	  throw new Exception("eleman bulunamad� ilerleme : " + ilerleme + "  ilerleme miktar� = " + ilerlemeMiktar� + " "
    	  		+ "\nboy : " + i�aret.boy() +"\n");
      }
      public int rassalYerVer() throws Exception{
    	  float rassal = dizi[yer];
    	  yer ++;
    	  int ilerlemeMiktar� = (int) Math.ceil(rassal*(i�aret.boy()-trueSay�s�));
    	  return eri�(ilerlemeMiktar�);
      }
      
      public String rassalYerlerYaz() throws Exception{
    	    String yaz� = "";
    	    int elemanNo;
    	    for(elemanNo=0;elemanNo<i�aret.boy();elemanNo++){
    	    	yaz� += rassalYerVer() + "\n";
    	    }
    	    return yaz�;
      }
}

