package SýkýþtýrýlmýþVeriYapýlarý;

import veriYapýlarý.DoubleDizi;
import veriYapýlarý.DoubleVektör;

public class KümeMerkeziDizi {

	public KümeMerkezi [] dizi;
	public KümeMerkeziDizi(String yazý,String ayýrýcý) throws Exception 
	{
		// dizi = "3,4,5
		// 12,23,4
		// 4,1,3"
		// böyle girilcek
		String satýrlar [] = yazý.split("\\n");
		diziVirgülÝçermiyorHatasý(satýrlar.length);
		diziyiDoldur(satýrlar,ayýrýcý);
	}
	private void diziyiDoldur(String[] satýrlar,String ayýrýcý) throws Exception {
		int satýrSayýsý = satýrlar.length;
		if(satýrlar[0].length() == 0){
			dizi = new KümeMerkezi[satýrSayýsý-1];
			ilkElemanBoþkenDizi(satýrlar, satýrSayýsý, ayýrýcý);
		}
		else {
			dizi = new KümeMerkezi[satýrSayýsý];
			ilkElemanYokkenDizi(satýrlar, satýrSayýsý, ayýrýcý);
		}
	}
	private void ilkElemanYokkenDizi(String[] satýrlar, int satýrSayýsý,String ayýrýcý)throws Exception {
		int elemanNo;
		for(elemanNo=0;elemanNo < satýrSayýsý;elemanNo++)
		{
			dizi[elemanNo] = new KümeMerkezi(satýrlar[elemanNo], ayýrýcý);
		}
	}
	private void ilkElemanBoþkenDizi(String[] satýrlar, int satýrSayýsý,String ayýrýcý) throws Exception {
		int elemanNo;
		for(elemanNo=1;elemanNo < satýrSayýsý;elemanNo++)
		{
			dizi[elemanNo-1] = new KümeMerkezi(satýrlar[elemanNo], ayýrýcý);
		}
	}
	private void diziVirgülÝçermiyorHatasý(int satýrlarUzunluk) throws Exception {
		if(satýrlarUzunluk == 0){
			throw new Exception("girilen yazý virgül içermiyor");
		}
	}
    public KümeMerkeziDizi (int satýrSayýsý,int sutunSayýsý,double sayýsý) throws Exception{
    	diziOluþturmBoyutKontrol(satýrSayýsý, sutunSayýsý);
    	int satýrNo;
    	dizi = new KümeMerkezi[satýrSayýsý];
    	for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
    		dizi[satýrNo] =  new KümeMerkezi(sutunSayýsý, sayýsý);
    	}
    }
	private void diziOluþturmBoyutKontrol(int satýrSayýsý, int sutunSayýsý) throws Exception
    {
		if(satýrSayýsý <= 0 || sutunSayýsý <= 0)
    	{
    		throw new Exception("boyut uyumsuzluðu var satýr sayýsý = " + satýrSayýsý + 
    				"  sutun sayýsý = " + sutunSayýsý);
    	}
	}
    public KümeMerkeziDizi (int satýrSayýsý){
    	dizi = new KümeMerkezi[satýrSayýsý];
    }
    public KümeMerkeziDizi (int satýrSayýsý,int sutunSayýsý) throws Exception{
    	diziOluþturmBoyutKontrol(satýrSayýsý, sutunSayýsý);
    	int satýrNo;
    	dizi = new KümeMerkezi[satýrSayýsý];
    	for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
    		dizi[satýrNo] =  new KümeMerkezi(sutunSayýsý);
    	}
    }
    
    public void rassalDiziDoldur() throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		dizi [satýrNo] =  new KümeMerkezi(sutunsay());
    		dizi [satýrNo].rassalDoldur();
    	}
    }
    
    public int sutunsay()
  	{
  		return dizi[0].dizi.boy();
  	}
  	public int satýrsay()
  	{
  		return dizi.length;
  	}
  	
    public KümeMerkezi satýr(int satýrNo){
    	return dizi[satýrNo];
    }
  	
  	public String yaz(String arayaGirenKarakter) throws Exception
  	{
  		if(satýrsay()== 0)
  		{
  			return "";
  		}
  		else
  		{
  			String yazý = boþOlmayanDiziYaz(arayaGirenKarakter);
  			return yazý;
  		}
  	}
  	private String boþOlmayanDiziYaz(String arayaGirenKarakter) throws Exception
  	{
  		String yazý = "";
  		int satýrNo;
  		for(satýrNo=0;satýrNo<dizi.length-1;satýrNo++)
  		{
  			yazý += dizi[satýrNo].yaz(arayaGirenKarakter) + "\n";
  		}
  		yazý += dizi[satýrNo].yaz(arayaGirenKarakter);
  		return yazý;
  	}

  	public double eriþ(int satýrNo,int sutunNo) throws Exception
  	{
  		eriþimSatýrKontroEt(satýrNo,sutunNo);
  		eriþimSutunKontrolEt(sutunNo,satýrNo);
  		return dizi[satýrNo].dizi.eriþ(sutunNo);
  	}
  	private void eriþimSutunKontrolEt(int sutunNo,int satýrNo) throws Exception {
  		if(sutunNo < 0 || sutunNo > dizi[satýrNo].boy()-1){
  			throw new Exception("sutunno dizi sýnýrlarý dýþýnda sutunno = " + sutunNo + " dizinin sutun sayýsý = " + sutunsay()
  					+ "Satýrno = " + satýrNo);
  		}
  	}
  	private void eriþimSatýrKontroEt(int satýrNo,int sutunNo) throws Exception {
  		if(satýrNo<0 ||satýrNo > satýrsay()-1){
  			throw new Exception("satýrno dizi sýnýrlarý dýþýnda satýrno = " + satýrNo + " dizinin satýr sayýsý = " + satýrsay()+ 
  					"\nSutunNo = " + sutunNo);
  		}
  	}
  	public void deðiþ(int satýrNo,int sutunNo,double yeniDeðer) throws Exception{
  		eriþimSatýrKontroEt(satýrNo, sutunNo);
  		eriþimSutunKontrolEt(sutunNo, satýrNo);
  		dizi[satýrNo].dizi.deðiþ(sutunNo, yeniDeðer);
  	}
  	// matematiksel iþlemler

  	public KümeMerkeziDizi kopya() throws Exception{
  		KümeMerkeziDizi kopya = new KümeMerkeziDizi(satýrsay());
  		int satýrNo;
  		for(satýrNo = 0;satýrNo<satýrsay();satýrNo ++){
  			kopya.dizi[satýrNo] = dizi[satýrNo].kopya();
  		}
  		return kopya;
  	}

  	public double satýrÖklidBul(int satýrNo, IntAramaAðacý2 vektör) throws Exception{
  		return dizi[satýrNo].öklidUzaklýðýBul(vektör);
  	}

  	public void çarp(double sayý) throws Exception{
    	int satýrNo;
    	for(satýrNo =0;satýrNo<satýrsay();satýrNo++){
    		dizi[satýrNo].çarp(sayý);
    	}
    }
	public KümeMerkeziDizi çýkar(KümeMerkeziDizi çýkan) throws Exception{
		KümeMerkeziDizi sonuç = new KümeMerkeziDizi (satýrsay());
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
			KümeMerkezi yeniSatýr = new KümeMerkezi(sutunsay());
			sonuç.dizi[satýrNo] = yeniSatýr;
		}
		return sonuç;
		
	}
    public void topla(KümeMerkeziDizi toplanan) throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		KümeMerkezi  eklenecek = toplanan.dizi[satýrNo];
    		dizi[satýrNo].topla(eklenecek);
    	}
    }

    public DoubleDizi çýkar2(DoubleDizi çýkan) throws Exception{
		DoubleDizi sonuç = new DoubleDizi (satýrsay());
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
			DoubleVektör yeniSatýr = çýkan.dizi[satýrNo];
			sonuç.dizi[satýrNo] = dizi[satýrNo].çýkar2(yeniSatýr);
		}
		return sonuç;
		
	}
    public void topla2(DoubleDizi toplanan) throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		DoubleVektör  eklenecek = toplanan.dizi[satýrNo];
    		dizi[satýrNo].topla(eklenecek);
    	}
    }

    public DoubleDizi çýkar2(KümeMerkeziDizi  çýkan) throws Exception{
    	DoubleDizi sonuç = new DoubleDizi (satýrsay());
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
			DoubleVektör yeniSatýr = çýkan.dizi[satýrNo].dizi;
			sonuç.dizi[satýrNo] = dizi[satýrNo].çýkar2(yeniSatýr);
		}
		return sonuç;
	}
    public void topla2(KümeMerkeziDizi  toplanan) throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		KümeMerkezi eklenecek = toplanan.dizi[satýrNo];
    		dizi[satýrNo].topla(eklenecek);
    	}
    }

}
