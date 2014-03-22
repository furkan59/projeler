package veriYapýlarý;

import java.util.ArrayList;

public class DoubleListDizi {

	public ArrayList<DoubleListVektör> dizi = new ArrayList<DoubleListVektör>(); 
	public DoubleListDizi() {
	
	}
    public double eriþ(int satýr, int sutun){
    	return dizi.get(satýr).eriþ(sutun);
    }
    public void deðiþ(int satýr, int sutun, double deðer){
    	dizi.get(satýr).deðiþ(sutun, deðer);
    }
    
    public void satýrDeðiþ(int satýr, DoubleListVektör yeniSatýr){
    	satýrSil(satýr);
    	dizi.add(satýr, yeniSatýr);
    }
    public void satýrEkle(int satýr , DoubleListVektör yeniSatýr){
    	dizi.add(satýr,yeniSatýr);
    }
    public void satýrEkle(DoubleListVektör yeniSatýr){
    	dizi.add(yeniSatýr);
    }
    public void sutunDeðiþ(int sutun, DoubleListVektör yeniSutun){
    	int satýrNo;
    	int satýrSayýsý = satýrSay();
    	for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
    		double yeniDeðer = yeniSutun.eriþ(sutun);
    		dizi.get(satýrNo).deðiþ(sutun, yeniDeðer);
    	}
    }
    public void sutunEkle(DoubleListVektör yeniSutun){
    	if(dizi == null) {// demek ki ilk baþta sutun olarak eklenecen 
    	      sutundanDiziYap(yeniSutun);
    	}
    	else{
    		diziyeSutunEkle(yeniSutun);
    	}
    }
	private void diziyeSutunEkle(DoubleListVektör yeniSutun) {
		int satýrNo;
		int satýrSayýsý = satýrSay();
		for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
			double yeniDeðer = yeniSutun.eriþ(satýrNo);
			dizi.get(satýrNo).ekle(yeniDeðer);;
		}
	}
	private void sutundanDiziYap(DoubleListVektör yeniSutun) {
		int satýrNo;
		  int yeniSutununBoyu = yeniSutun.boy();
		  for(satýrNo=0;satýrNo<yeniSutununBoyu;satýrNo++){
			  DoubleListVektör yeniSatýr = new DoubleListVektör();
			  yeniSatýr.ekle(yeniSutun.eriþ(satýrNo));
		  }
	}
    public void sutunEkle(int sutunNo, DoubleListVektör yeniSutun){
    	int satýrNo;
    	int satýrSayýsý = satýrSay();
    	for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
    		double yeniDeðer = yeniSutun.eriþ(satýrNo);
    		dizi.get(satýrNo).ekle(sutunNo,yeniDeðer);;
    	}
    }
	
    public int satýrSay(){
		return dizi.size();
	}
	public int sutunSay(){
		return dizi.get(0).boy();
	}
	
	public void satýrSil ( int satýrNo){
		dizi.remove(satýrNo);
	}
	public void sutunsil(int sutunNo){
		int satýrNo;
		int satýrSayýsý = satýrSay();
		for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
			if(dizi.get(satýrNo).boy() > sutunNo){
				dizi.get(satýrNo).sil(sutunNo);	
			}
		}
	}
    public void satýrSutunSil(int satýrNo, int sutunNo){
    	satýrSil(satýrNo);
    	sutunsil(sutunNo);
    }
	
	public int [] enBüyüðünYeri(){
		int satýr,sutun;
		int satýrSayýsý = satýrSay();
		double enBüyük = eriþ(0,0);
		int [] enBüyüðünYeri = new int [2];
		enBüyüðüAta(enBüyüðünYeri);
		for(satýr=0;satýr<satýrSayýsý;satýr++){
			int sutunSayýsý = dizi.get(satýr).boy();
			for(sutun=0;sutun<sutunSayýsý;sutun++){
				enBüyük = enBüyüðüKarþýlaþtýr(satýr, sutun, enBüyük, enBüyüðünYeri);
			}
		}
		return enBüyüðünYeri;
	}
	private void enBüyüðüAta(int[] enBüyüðünYeri) {
		enBüyüðünYeri [0] = 0;
		enBüyüðünYeri [1] = 0;
	}
	private double enBüyüðüKarþýlaþtýr(int satýr, int sutun, double enBüyük, int[] enBüyüðünYeri) {
		double yeniDeðer = eriþ(satýr, sutun);
		if(yeniDeðer > enBüyük){
			enBüyük = yeniDeðer;
			enBüyüðünYeri[0] = satýr;
			enBüyüðünYeri[1] = sutun;
		}
		return enBüyük;
	}
 
	public Boolean büyüðüVarMý(double aranacak){
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
			DoubleListVektör satýr = dizi.get(satýrNo);
			if(satýr.büyüðüVarMý(aranacak)){
				return true;
			}
		}
		return  false; // eðer döngüden kurtulursa buraya gelir zaten
	}

	public String yaz(char ayýrýcý){
		int satýrNo,satýrSayýsý = satýrSay();
		String yazý = "";
		for(satýrNo=0;satýrNo<satýrSayýsý-1;satýrNo++){
			yazý += dizi.get(satýrNo).yaz(ayýrýcý) + "\n";
		}
		yazý += dizi.get(satýrNo).yaz(ayýrýcý);
		return yazý;
	}

	public DoubleListDizi kopya (){
		DoubleListDizi kopya = new DoubleListDizi();
		int satýrNo,satýrSayýsý = satýrSay();
		for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
			DoubleListVektör yeniSatýr = dizi.get(satýrNo).kopya();
			kopya.satýrEkle(yeniSatýr);
		}
		return kopya;
	}
}
