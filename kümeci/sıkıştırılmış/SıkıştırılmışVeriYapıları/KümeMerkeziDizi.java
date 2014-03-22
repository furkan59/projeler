package S�k��t�r�lm��VeriYap�lar�;

import veriYap�lar�.DoubleDizi;
import veriYap�lar�.DoubleVekt�r;

public class K�meMerkeziDizi {

	public K�meMerkezi [] dizi;
	public K�meMerkeziDizi(String yaz�,String ay�r�c�) throws Exception 
	{
		// dizi = "3,4,5
		// 12,23,4
		// 4,1,3"
		// b�yle girilcek
		String sat�rlar [] = yaz�.split("\\n");
		diziVirg�l��ermiyorHatas�(sat�rlar.length);
		diziyiDoldur(sat�rlar,ay�r�c�);
	}
	private void diziyiDoldur(String[] sat�rlar,String ay�r�c�) throws Exception {
		int sat�rSay�s� = sat�rlar.length;
		if(sat�rlar[0].length() == 0){
			dizi = new K�meMerkezi[sat�rSay�s�-1];
			ilkElemanBo�kenDizi(sat�rlar, sat�rSay�s�, ay�r�c�);
		}
		else {
			dizi = new K�meMerkezi[sat�rSay�s�];
			ilkElemanYokkenDizi(sat�rlar, sat�rSay�s�, ay�r�c�);
		}
	}
	private void ilkElemanYokkenDizi(String[] sat�rlar, int sat�rSay�s�,String ay�r�c�)throws Exception {
		int elemanNo;
		for(elemanNo=0;elemanNo < sat�rSay�s�;elemanNo++)
		{
			dizi[elemanNo] = new K�meMerkezi(sat�rlar[elemanNo], ay�r�c�);
		}
	}
	private void ilkElemanBo�kenDizi(String[] sat�rlar, int sat�rSay�s�,String ay�r�c�) throws Exception {
		int elemanNo;
		for(elemanNo=1;elemanNo < sat�rSay�s�;elemanNo++)
		{
			dizi[elemanNo-1] = new K�meMerkezi(sat�rlar[elemanNo], ay�r�c�);
		}
	}
	private void diziVirg�l��ermiyorHatas�(int sat�rlarUzunluk) throws Exception {
		if(sat�rlarUzunluk == 0){
			throw new Exception("girilen yaz� virg�l i�ermiyor");
		}
	}
    public K�meMerkeziDizi (int sat�rSay�s�,int sutunSay�s�,double say�s�) throws Exception{
    	diziOlu�turmBoyutKontrol(sat�rSay�s�, sutunSay�s�);
    	int sat�rNo;
    	dizi = new K�meMerkezi[sat�rSay�s�];
    	for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
    		dizi[sat�rNo] =  new K�meMerkezi(sutunSay�s�, say�s�);
    	}
    }
	private void diziOlu�turmBoyutKontrol(int sat�rSay�s�, int sutunSay�s�) throws Exception
    {
		if(sat�rSay�s� <= 0 || sutunSay�s� <= 0)
    	{
    		throw new Exception("boyut uyumsuzlu�u var sat�r say�s� = " + sat�rSay�s� + 
    				"  sutun say�s� = " + sutunSay�s�);
    	}
	}
    public K�meMerkeziDizi (int sat�rSay�s�){
    	dizi = new K�meMerkezi[sat�rSay�s�];
    }
    public K�meMerkeziDizi (int sat�rSay�s�,int sutunSay�s�) throws Exception{
    	diziOlu�turmBoyutKontrol(sat�rSay�s�, sutunSay�s�);
    	int sat�rNo;
    	dizi = new K�meMerkezi[sat�rSay�s�];
    	for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
    		dizi[sat�rNo] =  new K�meMerkezi(sutunSay�s�);
    	}
    }
    
    public void rassalDiziDoldur() throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		dizi [sat�rNo] =  new K�meMerkezi(sutunsay());
    		dizi [sat�rNo].rassalDoldur();
    	}
    }
    
    public int sutunsay()
  	{
  		return dizi[0].dizi.boy();
  	}
  	public int sat�rsay()
  	{
  		return dizi.length;
  	}
  	
    public K�meMerkezi sat�r(int sat�rNo){
    	return dizi[sat�rNo];
    }
  	
  	public String yaz(String arayaGirenKarakter) throws Exception
  	{
  		if(sat�rsay()== 0)
  		{
  			return "";
  		}
  		else
  		{
  			String yaz� = bo�OlmayanDiziYaz(arayaGirenKarakter);
  			return yaz�;
  		}
  	}
  	private String bo�OlmayanDiziYaz(String arayaGirenKarakter) throws Exception
  	{
  		String yaz� = "";
  		int sat�rNo;
  		for(sat�rNo=0;sat�rNo<dizi.length-1;sat�rNo++)
  		{
  			yaz� += dizi[sat�rNo].yaz(arayaGirenKarakter) + "\n";
  		}
  		yaz� += dizi[sat�rNo].yaz(arayaGirenKarakter);
  		return yaz�;
  	}

  	public double eri�(int sat�rNo,int sutunNo) throws Exception
  	{
  		eri�imSat�rKontroEt(sat�rNo,sutunNo);
  		eri�imSutunKontrolEt(sutunNo,sat�rNo);
  		return dizi[sat�rNo].dizi.eri�(sutunNo);
  	}
  	private void eri�imSutunKontrolEt(int sutunNo,int sat�rNo) throws Exception {
  		if(sutunNo < 0 || sutunNo > dizi[sat�rNo].boy()-1){
  			throw new Exception("sutunno dizi s�n�rlar� d���nda sutunno = " + sutunNo + " dizinin sutun say�s� = " + sutunsay()
  					+ "Sat�rno = " + sat�rNo);
  		}
  	}
  	private void eri�imSat�rKontroEt(int sat�rNo,int sutunNo) throws Exception {
  		if(sat�rNo<0 ||sat�rNo > sat�rsay()-1){
  			throw new Exception("sat�rno dizi s�n�rlar� d���nda sat�rno = " + sat�rNo + " dizinin sat�r say�s� = " + sat�rsay()+ 
  					"\nSutunNo = " + sutunNo);
  		}
  	}
  	public void de�i�(int sat�rNo,int sutunNo,double yeniDe�er) throws Exception{
  		eri�imSat�rKontroEt(sat�rNo, sutunNo);
  		eri�imSutunKontrolEt(sutunNo, sat�rNo);
  		dizi[sat�rNo].dizi.de�i�(sutunNo, yeniDe�er);
  	}
  	// matematiksel i�lemler

  	public K�meMerkeziDizi kopya() throws Exception{
  		K�meMerkeziDizi kopya = new K�meMerkeziDizi(sat�rsay());
  		int sat�rNo;
  		for(sat�rNo = 0;sat�rNo<sat�rsay();sat�rNo ++){
  			kopya.dizi[sat�rNo] = dizi[sat�rNo].kopya();
  		}
  		return kopya;
  	}

  	public double sat�r�klidBul(int sat�rNo, IntAramaA�ac�2 vekt�r) throws Exception{
  		return dizi[sat�rNo].�klidUzakl���Bul(vekt�r);
  	}

  	public void �arp(double say�) throws Exception{
    	int sat�rNo;
    	for(sat�rNo =0;sat�rNo<sat�rsay();sat�rNo++){
    		dizi[sat�rNo].�arp(say�);
    	}
    }
	public K�meMerkeziDizi ��kar(K�meMerkeziDizi ��kan) throws Exception{
		K�meMerkeziDizi sonu� = new K�meMerkeziDizi (sat�rsay());
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
			K�meMerkezi yeniSat�r = new K�meMerkezi(sutunsay());
			sonu�.dizi[sat�rNo] = yeniSat�r;
		}
		return sonu�;
		
	}
    public void topla(K�meMerkeziDizi toplanan) throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		K�meMerkezi  eklenecek = toplanan.dizi[sat�rNo];
    		dizi[sat�rNo].topla(eklenecek);
    	}
    }

    public DoubleDizi ��kar2(DoubleDizi ��kan) throws Exception{
		DoubleDizi sonu� = new DoubleDizi (sat�rsay());
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
			DoubleVekt�r yeniSat�r = ��kan.dizi[sat�rNo];
			sonu�.dizi[sat�rNo] = dizi[sat�rNo].��kar2(yeniSat�r);
		}
		return sonu�;
		
	}
    public void topla2(DoubleDizi toplanan) throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		DoubleVekt�r  eklenecek = toplanan.dizi[sat�rNo];
    		dizi[sat�rNo].topla(eklenecek);
    	}
    }

    public DoubleDizi ��kar2(K�meMerkeziDizi  ��kan) throws Exception{
    	DoubleDizi sonu� = new DoubleDizi (sat�rsay());
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
			DoubleVekt�r yeniSat�r = ��kan.dizi[sat�rNo].dizi;
			sonu�.dizi[sat�rNo] = dizi[sat�rNo].��kar2(yeniSat�r);
		}
		return sonu�;
	}
    public void topla2(K�meMerkeziDizi  toplanan) throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		K�meMerkezi eklenecek = toplanan.dizi[sat�rNo];
    		dizi[sat�rNo].topla(eklenecek);
    	}
    }

}
