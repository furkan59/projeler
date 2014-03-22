package SýkýþtýrýlmýþVeriYapýlarý;

import java.util.ArrayList;

import veriYapýlarý.BoolVektör;

public class BoolEksikMatrisAðaçlý {

	public IntAramaAðacý2 [] dizi; // dolu elemanlarýn sutunlarýný tutar
	public int sutunSayýsý;
	public int doluElemanSayýsý=0;
	public BoolEksikMatrisAðaçlý(int satýrSayýsý){
		dizi = new IntAramaAðacý2 [satýrSayýsý];
		int elemanNo;
		for(elemanNo=0;elemanNo<satýrSayýsý;elemanNo++){
			dizi[elemanNo] = new IntAramaAðacý2();
		}
	}
	public BoolEksikMatrisAðaçlý(String girdi, char ayýrýcý) throws Exception {
	     String ayýr = "" + ayýrýcý;
		 String [] satýrlar = girdi.split("\\n");
		 sutunSayýsý = satýrlar.length;
		 dizi = new IntAramaAðacý2 [satýrlar.length];
		 int satýrNo;
		 for(satýrNo=0;satýrNo<satýrlar.length;satýrNo++){
			 satýrOku(satýrNo, satýrlar[satýrNo], ayýr);
		 }
		 // son satýr okunmadý
	}
	public BoolEksikMatrisAðaçlý(String girdi, String ayýrýcý,int baþlamaSutunu,int baþlamaSatýrý) throws Exception {
	     String ayýr = "" + ayýrýcý;
		 String [] satýrlar = girdi.split("\\n");
		 sutunSayýsý = satýrlar.length;
		 dizi = new IntAramaAðacý2 [satýrlar.length- baþlamaSatýrý];
		 int satýrNo;
		 for(satýrNo=baþlamaSatýrý;satýrNo<satýrlar.length;satýrNo++){
			 satýrOku(satýrNo-baþlamaSatýrý, satýrlar[satýrNo], ayýr,baþlamaSutunu);
		 }
	}
	// þu an listeden diziye aktarma var belki deðiþebilir
    public void satýrOku(int satýrNo,String satýr,String ayýrýcý) throws Exception{
    	String [] bölük = satýr.split(ayýrýcý);
    	ArrayList<Integer> sutunlar = new ArrayList<Integer>();
    	int elemanNo;
    	for(elemanNo=0;elemanNo<bölük.length;elemanNo++){
    		elemanOku(bölük, sutunlar, elemanNo);
    		doluElemanSayýsý++;
    	}
    	int [] sutunlarDizi = new int [sutunlar.size()];
    	diziyiAktar(sutunlar, sutunlarDizi);
    	dizi[satýrNo] = new IntAramaAðacý2(sutunlarDizi,true);
    }
	public void elemanOku(String[] bölük, ArrayList<Integer> sutunlar,int elemanNo) throws Exception {
		if(bölük[elemanNo].compareTo("1") == 0 ){
			sutunlar.add(elemanNo);
		}
		else if( bölük[elemanNo].compareTo("0") != 0 && bölük[elemanNo].compareTo("") != 0 ){
			throw new Exception("ya sýfýr da ya bir girmen gerekli ama girilen deðer\n" + bölük[elemanNo] + "\n");
		}
	}
    public void diziyiAktar(ArrayList<Integer> dizi1,int [] dizi2){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<dizi2.length;elemanNo++){
    		dizi2 [elemanNo] = dizi1.get(elemanNo);
    	}
    }
    
    public void satýrOku(int satýrNo,String satýr,String ayýrýcý,int baþlamaYeri) throws Exception{
    	String [] bölük = satýr.split(ayýrýcý);
    	ArrayList<Integer> sutunlar = new ArrayList<Integer>();
    	int elemanNo;
    	for(elemanNo=baþlamaYeri;elemanNo<bölük.length;elemanNo++){
    		elemanOku(bölük, sutunlar, elemanNo);
    		doluElemanSayýsý++;
    	}
    	int [] sutunlarDizi = new int [sutunlar.size()];
    	diziyiAktar(sutunlar, sutunlarDizi);
    	dizi[satýrNo] = new IntAramaAðacý2(sutunlarDizi,true);
    }
    public void satýrOku(int satýrNo,String [] satýr,int baþlamaYeri) throws Exception{
    	ArrayList<Integer> sutunlar = new ArrayList<Integer>();
    	int elemanNo;
    	for(elemanNo=baþlamaYeri;elemanNo<satýr.length;elemanNo++){
    		elemanOku(satýr, sutunlar, elemanNo,baþlamaYeri);
    		doluElemanSayýsý++;
    	}
    	int [] sutunlarDizi = new int [sutunlar.size()];
    	diziyiAktar(sutunlar, sutunlarDizi);
    	dizi[satýrNo] = new IntAramaAðacý2(sutunlarDizi,true);
    }
    public void elemanOku(String[] bölük, ArrayList<Integer> sutunlar,int elemanNo,int baþlamaYeri) throws Exception {
		if(bölük[elemanNo].compareTo("1") == 0 ){
			sutunlar.add(elemanNo-baþlamaYeri);
		}
		else if( bölük[elemanNo].compareTo("0") != 0 && bölük[elemanNo].compareTo("") != 0 ){
			throw new Exception("ya sýfýr da ya bir girmen gerekli ama girilen deðer\n" + bölük[elemanNo] + "\n");
		}
	}
    public boolean eriþ(int satýr,int sutun){
    	return dizi[satýr].ara(sutun);
    }
    
    /** <br>enkýsaAralýk [0] = enküçükaralýðýn baþý 
     * <br>enkýsaAralýk [1] = enküçükaralýðýn sonu
     * <br> eðer aralýklar ayrýk ise bu iþlevi kullanmaya gerek yok çünkü benzerlik sýfýr çýkacaktýr
     * @return
     */
    private int [] enKýsaAralýk(int satýr1 ,int satýr2){
    	int sutun1baþ = dizi[satýr1].enKüçükDeðer;
    	int sutun1son = dizi[satýr1].enBüyükDeðer;
    	int sutun2baþ = dizi[satýr2].enKüçükDeðer;
    	int sutun2son = dizi[satýr2].enBüyükDeðer;
        int [] enKýsaAralýk = new int [2];
    	if(sutun1baþ < sutun2baþ && sutun1son > sutun2son){ // ikinci  aralýk birincinin içinde
             enKýsaAralýk [0] = sutun2baþ;
             enKýsaAralýk [1] = sutun2son;
    	}
    	else if(sutun1baþ > sutun2baþ && sutun1son < sutun2son) {// birinci aralýk ikincinin içinde
    		enKýsaAralýk [0] = sutun1baþ;
            enKýsaAralýk [1] = sutun1son;
    	}
    	else if(sutun1baþ > sutun2son|| sutun1son < sutun2baþ){ // ayrýk olma durumu
    		enKýsaAralýk [0] = -1;
            enKýsaAralýk [1] = -1;
    	}
    	else { // kesiþme durumu
    		if(sutun1baþ < sutun2baþ){ // satýr1 geride
    			enKýsaAralýk [0] = sutun2baþ;
    			enKýsaAralýk [1] = sutun1son;
    		}
    		else { // satýr1 ileride
    			enKýsaAralýk [0] = sutun1baþ;
    			enKýsaAralýk [1] = sutun2son;
    		}
    	}
    	return enKýsaAralýk;
    }	    
    private ArrayList<Integer> aralýktakiSutunlarýBul(int satýr ,int altDeðer, int üstDeðer){
    	return dizi [ satýr].aralýkBul(altDeðer, üstDeðer);
    }
    
    public int benzerlikBul(int satýr1,int satýr2){
    	int [] enKýsaAralýk = enKýsaAralýk(satýr1, satýr2);
    	return sutunlaraBakarakTara(satýr1, satýr2, enKýsaAralýk);
    }
	private int sutunlaraBakarakTara(int satýr1, int satýr2, int[] enKýsaAralýk) {
		int benzerlik = 0;
		ArrayList<Integer> sutunlar1 = dizi[satýr1].aralýkBul(enKýsaAralýk[0]-1, enKýsaAralýk[1]+1); // çünkü aralýkBul sýnýr deðerleri içermez 
    	ArrayList<Integer> sutunlar2 = dizi[satýr2].aralýkBul(enKýsaAralýk[0]-1, enKýsaAralýk[1]+1); // ayrýca <= ve ya >= iþlemi daha uzun sürer
    	if(sutunlar1.size() > sutunlar2.size()){
    		benzerlik += benzerlikTara(satýr1, sutunlar2);
    	}
    	else {
    		benzerlik += benzerlikTara(satýr2, sutunlar1);
    	}
		return benzerlik;
	}
	private int benzerlikTara(int satýr1,ArrayList<Integer> sutunlar2) {
		int elemanNo;
		int benzerlik = 0;
		for(elemanNo=0;elemanNo<sutunlar2.size();elemanNo++){
			int sutun = sutunlar2.get(elemanNo); // bununn zaten true olduðunu biliyoruz burada eleman var ama önemli olan karþýda var mý ?
			if(eriþ(satýr1,sutun)){ // demek ki orada eleman var
				benzerlik++;
			}
		}
		return benzerlik;
	}

    public int satýrSay(){
    	return dizi.length;
    }
    public int sutunSay(){
    	return sutunSayýsý;
    }

    public BoolVektör sutunÇek(int sutunNo){
    	BoolVektör dizi = new BoolVektör(satýrSay());
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
    		dizi.dizi[satýrNo] = eriþ(satýrNo,sutunNo);
    	}
    	return dizi;
    }
    public BoolVektör satýrÇek(int satýrNo){
    	BoolVektör dizi = new BoolVektör(sutunSayýsý);
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSayýsý;sutunNo++){
    		dizi.dizi[sutunNo] = eriþ(satýrNo,sutunNo);
    	}
    	return dizi;
    }
    public String yaz(){
    	String yazý = "";
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
    	   yazý += dizi[satýrNo].tümDeðerleriYaz() + "\n";	
    	}
    	return yazý;
    }
}
