package kümeci;

import java.util.Random;

import veriYapýlarý.BoolVektör;

public class RassalDizi {
      private BoolVektör iþaret ;
      private int trueSayýsý;
      private float [] dizi ; // rassal sayýlar
      private int yer = 0;
      public RassalDizi (int boy){
    	  iþaret = new BoolVektör(boy,false);
    	  trueSayýsý =0;
    	  dizi = new float [boy];
    	  rassalSayýlarýÜret();
      }
      private void rassalSayýlarýÜret(){
    	  Random rassal = new Random();
    	  for(int elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		  dizi [elemanNo] = rassal.nextFloat();
    	  }
      }
      
      private void deðiþ(int yer) throws Exception{
    	  if(iþaret.eriþ(yer)){
    		  throw new Exception("true olan bir yere deðiþim yapýlamaz sýra No : " + yer + "\n");
    	  }
    	  iþaret.deðiþ(yer, true);
    	  trueSayýsý ++;
      }
      private int eriþ(int ilerleme) throws Exception{
    	  int ilerlemeMiktarý = 0;
    	  if(ilerleme == 0 && iþaret.eriþ(0) == false){
    		  deðiþ(0);
    		  return 0;
    	  }
    	  else{ // ilerleme arada ilken
    		  int elemanNo ;
    		  for(elemanNo = 0;elemanNo<iþaret.boy();elemanNo++){
        		  if(iþaret.eriþ(elemanNo) == false){ // bunu gör
        			  ilerlemeMiktarý ++;
        		  }
        		  if(ilerlemeMiktarý == ilerleme){
        			  deðiþ(elemanNo);
        			  return elemanNo;
        		  }
        	  }
    	  }
    	  throw new Exception("eleman bulunamadý ilerleme : " + ilerleme + "  ilerleme miktarý = " + ilerlemeMiktarý + " "
    	  		+ "\nboy : " + iþaret.boy() +"\n");
      }
      public int rassalYerVer() throws Exception{
    	  float rassal = dizi[yer];
    	  yer ++;
    	  int ilerlemeMiktarý = (int) Math.ceil(rassal*(iþaret.boy()-trueSayýsý));
    	  return eriþ(ilerlemeMiktarý);
      }
      
      public String rassalYerlerYaz() throws Exception{
    	    String yazý = "";
    	    int elemanNo;
    	    for(elemanNo=0;elemanNo<iþaret.boy();elemanNo++){
    	    	yazý += rassalYerVer() + "\n";
    	    }
    	    return yazý;
      }
}

