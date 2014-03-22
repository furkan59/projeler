package S�k��t�r�lm��MetaSezgisel;

import java.util.ArrayList;

import k�meci.K�meleme;
import S�k��t�r�lm��VeriYap�lar�.BoolEksikMatrisA�a�l�;
import metaSezgisel.Par�ac�k;
import veriYap�lar�.BoolDizi;

public class Par�ac�kS�r�2 extends K�meleme2{

	public Par�ac�kS�r�2(BoolEksikMatrisA�a�l� par�aMakine, String[] makine�simleri, String[] par�a�simleri,int k�meSay�s�,int par�ac�kSay�s�) throws Exception {
		super(par�aMakine, makine�simleri, par�a�simleri);
		this.par�ac�kSay�s� = par�ac�kSay�s�;
		this.k�meSay�s� = k�meSay�s�;
		par�ac�klar�Olu�tur();
	}
	public Par�ac�kS�r�2(String yaz�, char ay�r�c�,int k�meSay�s�,int par�ac�kSay�s�) throws Exception {
		super(yaz�, ay�r�c�);
		this.par�ac�kSay�s� = par�ac�kSay�s�;
		this.k�meSay�s� = k�meSay�s�;
		par�ac�klar�Olu�tur();
	}
	public Par�ac�kS�r�2(String yaz�,int k�meSay�s�,int par�ac�kSay�s�) throws Exception {
		super(yaz�);
		this.par�ac�kSay�s� = par�ac�kSay�s�;
		this.k�meSay�s� = k�meSay�s�;
		par�ac�klar�Olu�tur();
	}

	public int par�ac�kSay�s�;
	public int k�meSay�s�;
	private Par�ac�k2 [] par�ac�klar;
	
	// g�ven parametreleri belirlenmedi�i zaman par�alar kendi g�ven de�erlini kullan�r
	
	protected int s�r�Ba��=-1;
	public long ��z�mZaman�;
	
	public int enFazlaA�ama;
	public int enFazla�lerleme;
	public boolean ilerlemeK�s�tl� = false;
	
	public double duyarl�l�k = 0.00000000001;

	public int par�ac�kSay�s� (){
	   return par�aMakine.sutunSay();
	}
	private void par�ac�klar�Olu�tur() throws Exception {
    	par�ac�klar = new Par�ac�k2 [this.par�ac�kSay�s�];
    	int Par�ac�k2No;
    	for(Par�ac�k2No=0;Par�ac�k2No<par�ac�kSay�s�;Par�ac�k2No++){
    		par�ac�klar[Par�ac�k2No] = new Par�ac�k2(k�meSay�s�, par�aSay�s�(), par�aMakine);
    	}
	}
    public void g�venParameterileriniBelirle(double s�r�yeG�ven, double kendineG�ven , double eylemsizlik ){
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�klar[par�ac�kNo].g�venParameterileriniBelirle(s�r�yeG�ven, kendineG�ven, eylemsizlik);
    	}
    }
    public void ilkH�zBelirle(double ilkH�z) throws Exception{
    	ilkH�zKontrol(ilkH�z);
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�klar[par�ac�kNo].ilkH�zBelirle(ilkH�z);
    	}
    }
	private void ilkH�zKontrol(double ilkH�z) throws Exception {
		if(ilkH�z < 0 ){
    		throw new Exception("ilk h�z s�f�rdan k���k olamaz girlen ilk h�z :" + ilkH�z + "\n");
    	}
	}

	
	public void s�r�n�nBa��n�Bul() throws Exception{
		s�r�Ba�� = 0;
		int par�ac�kNo;
		for(par�ac�kNo=1;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
			s�r�Ba��n�G�ncelle(par�ac�kNo);
		}
	}
	private void s�r�Ba��n�G�ncelle(int par�ac�kNo) throws Exception {
		double yeniAma� = par�ac�klar[par�ac�kNo].ama�De�eriHesapla(par�aMakine);
		if(yeniAma� > par�ac�klar[s�r�Ba��].ama�De�eri){
			s�r�Ba�� = par�ac�kNo;
		}
	}
    
	private void par�aNoKontrolEt(int par�aNo) throws Exception {
		if(par�aNo > par�ac�kSay�s�){
    		throw new Exception("par�ano Par�ac�k2 say�s�ndan fazla olamaz par�aNo : " + par�aNo + " Par�ac�k2 say�s� : " + par�ac�kSay�s� + "\n");
    	}
	}
    private void par�ac�kKonumuG�ncelle(int par�ac�kNo) throws Exception{
    	par�aNoKontrolEt(par�ac�kNo);
        Par�ac�k2 s�r��nderi = s�r��nderi();
    	par�ac�klar[par�ac�kNo].konumuG�ncelle(s�r��nderi,par�aMakine);
    }
    public void par�ac�klar�nKonumunuG�ncelle() throws Exception{
    	int Par�ac�k2No;
    	for(Par�ac�k2No=0;Par�ac�k2No<par�ac�kSay�s�;Par�ac�k2No++){
    		par�ac�kKonumuG�ncelle(Par�ac�k2No);
    	}
    }

    /**
     * t�m Par�ac�k2lar�n ortalamaK�meler��i uzakl�k ve ortalamaK�melerAras�Uzakl�k de�i�kenini s�f�rlar
     */
    private void par�ac�klar�nUzakl�klar�n�S�f�rla(){
    	int Par�ac�k2No;
    	for(Par�ac�k2No=0;Par�ac�k2No<par�ac�kSay�s�;Par�ac�k2No++){
    		par�ac�klar[Par�ac�k2No].uzakl�klar�S�f�rla();
    	}
    }
    public void ilerle() throws Exception{
    	par�ac�klar�nAma�De�erleriniYenile();
    	par�ac�klar�nUzakl�klar�n�S�f�rla();
    	s�r�n�nBa��n�Bul();
    	par�ac�klar�nKonumunuG�ncelle();
    }
    private void par�ac�klar�nAma�De�erleriniYenile(){
    	int Par�ac�k2No;
    	for(Par�ac�k2No=0;Par�ac�k2No<par�ac�kSay�s�;Par�ac�k2No++){
    		par�ac�klar[Par�ac�k2No].ama�De�eriVar = false;
    	}
    }
   
    // ��z�m y�ntemleri ba�
    public String ��z() throws Exception{
    	String ��z�m = "";
    	long ba� = System.currentTimeMillis();
    	��z�m = ba�lang��();
    	��z�m += ��z��z�mD�ng�s�();
    	��z�m += yaz();
    	long son = System.currentTimeMillis();
    	��z�mZaman� = son - ba�;
    	��z�m += "��z�m zaman� : " + ��z�mZaman� + " milisaniye";
    	return ��z�m;
    }
	private String ��z��z�mD�ng�s�() throws Exception {
		String ��z�m = "";
		if(ilerlemeK�s�tl� ){
			��z�m = k�s�ts�z�lerleme(��z�m);
		}
		else {
			��z�m = k�s�tl��lerleme(��z�m);
		}
		return ��z�m;
	}
	private String k�s�tl��lerleme(String ��z�m) throws Exception {
		ArrayList<Double> ama�lar = new ArrayList<Double>();
		��z�m += "ilk a�amalar\n";
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaA�ama;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			��z�m += ��zA�amaYaz(ilerleme);
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			ama�lar.add(yeniAma�De�eri);
		}
		// ilk a�amalar bitti
		for(ilerleme=0;ilerleme<enFazla�lerleme+1;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			��z�m += ��zA�amaYaz(ilerleme);
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			if( yeniAma�De�eri - ama�lar.get(0) < duyarl�l�k){
			    break;	
			}
			ama�lar.remove(0);
			ama�lar.add(yeniAma�De�eri);
		}
		return ��z�m;
	}
	private String k�s�ts�z�lerleme(String ��z�m) throws Exception {
		ArrayList<Double> ama�lar = new ArrayList<Double>();
		��z�m += "ilk a�amalar\n";
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaA�ama;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			��z�m += ��zA�amaYaz(ilerleme);
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			ama�lar.add(yeniAma�De�eri);
		}
		// ilk a�amalar bitti
		for(ilerleme=0;;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			��z�m += ��zA�amaYaz(ilerleme);
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			if( yeniAma�De�eri - ama�lar.get(0) < duyarl�l�k){
			    break;	
			}
			ama�lar.remove(0);
			ama�lar.add(yeniAma�De�eri);
		}
		return ��z�m;
	}
    private String ��zA�amaYaz(int ilerleme) throws Exception{
    	String ��z�m = "";  
		��z�m += "ilerleme : " + ilerleme ;
		��z�m += "" + yaz() + "\n\n--------------------------------------------------------------------------\n\n";	
		��z�m += "" + yaz() ;
		return ��z�m;
    }
    
    
    
    public String h�zl���z() throws Exception{
    	String ��z�m = "";
    	long ba� = System.currentTimeMillis();
    	��z�m = ba�lang��();
    	��z�m += ��z�m�Yaz();
    	long son = System.currentTimeMillis();
    	��z�mZaman� = son - ba�;
    	��z�m += "��z�m zaman� : " + ��z�mZaman� + " milisaniye";
    	return ��z�m;
    }
	private String ��z�m�Yaz() throws Exception {
		String ��z�m = "";
		��z�m += h�zl���z�mD�ng�s�n�Kur();
    	��z�m += "son: \n\n" + s�r��nderi().yaz() + "________________________________________________________\n\n";
		return ��z�m;
	}
	private String ba�lang��() throws Exception {
		String ��z�m = "";
		��z�m += ba�lang��Ko�ullar�n�Yaz();
    	ilerle();
		return ��z�m;
	}
	private String h�zl���z�mD�ng�s�n�Kur() throws Exception {
		String ��z�m = "";
		if(ilerlemeK�s�tl� ){
			��z�m = h�zl���z�lerlemeK�s�tl�(��z�m);
		}
		else {
			��z�m = h�zl���z�lerlemeK�s�ts�z(��z�m);
		}
		return ��z�m;
	}
	private String h�zl���z�lerlemeK�s�ts�z(String ��z�m) throws Exception {
		ArrayList<Double> ama�lar = new ArrayList<Double>();
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaA�ama;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			ama�lar.add(yeniAma�De�eri);
		}
		// ilk a�amalar bitti
		for(ilerleme=0;;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			if( yeniAma�De�eri - ama�lar.get(0) < duyarl�l�k){
			    break;	
			}
			ama�lar.remove(0);
			ama�lar.add(yeniAma�De�eri);
		}
		return ��z�m;
	}
	private String h�zl���z�lerlemeK�s�tl�(String ��z�m) throws Exception {
		ArrayList<Double> ama�lar = new ArrayList<Double>();
		��z�m += "ilk a�amalar\n";
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaA�ama;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			ama�lar.add(yeniAma�De�eri);
		}
		// ilk a�amalar bitti
		for(ilerleme=0;ilerleme<enFazla�lerleme;ilerleme++){
			��z�m += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			if( yeniAma�De�eri - ama�lar.get(0) < duyarl�l�k){
			    break;	
			}
			ama�lar.remove(0);
			ama�lar.add(yeniAma�De�eri);
		}
		return ��z�m;
	}
	private String h�zl���zA�amaYaz()throws Exception {
		String ��z�m = "";  
		ilerle();
		return ��z�m;
	}
    private String h�zl���z�lerleme(int ilerleme) throws Exception{
    	String ��z�m = "";
    	int a�amaNo;
		for(a�amaNo=1;a�amaNo<enFazlaA�ama;a�amaNo++){
			��z�m += h�zl���zA�amaYaz();
    	}
		��z�m += "\n\na�ama say�s� : " + a�amaNo + "\n\n";
		return ��z�m;
    }
	
    // ��z�m y�ntemleri son
    protected Par�ac�k2 s�r��nderi(){
    	return par�ac�klar[s�r�Ba��];
    }
    private int deney��in��z() throws Exception{
    	long ba� = System.currentTimeMillis();
    	ArrayList<Double> ama�lar = new ArrayList<Double>();
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaA�ama;ilerleme++){
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			ama�lar.add(yeniAma�De�eri);
		}
		// ilk a�amalar bitti
		for(ilerleme=0;ilerleme<enFazla�lerleme+1;ilerleme++){
			ilerle();
			double yeniAma�De�eri = s�r��nderi().ama�De�eri;
			if( yeniAma�De�eri - ama�lar.get(0) < duyarl�l�k){
			    break;	
			}
			ama�lar.remove(0);
			ama�lar.add(yeniAma�De�eri);
		}
		
    	long son = System.currentTimeMillis();
    	��z�mZaman� = son - ba�;
    	return ilerleme + enFazlaA�ama;
    }
    public String deneyYap(int par�ac�kSay�s�,int k�meSay�s�) throws Exception{
    	String deney = "";//"��z�m zaman�\tPar�ac�k2 say�s�\tk�me say�s�\ta�ama say�s�\tama� de�eri";
    	this.par�ac�kSay�s� = par�ac�kSay�s�;
    	this.k�meSay�s� = k�meSay�s�;
    	par�ac�klar�Olu�tur();
    	int a�ama = deney��in��z();
    	deney +=  ��z�mZaman� + "\t";
    	deney +=  par�ac�kSay�s�+ "\t";
    	deney +=  k�meSay�s�+ "\t";
    	deney +=  a�ama+ "\t";
    	Par�ac�k2 �nder = s�r��nderi();
    	deney +=  �nder.ama�De�eri+ "\t";
    	deney +=  �nder.bo�K�meSay�s�Bul() + "\t";
    	System.out.print(deney+"\n");
    	return deney;
    }
    public String deneyYap(int par�ac�kSay�s�1,int par�ac�kSay�s�2,int k�meSay�s�1,int k�meSay�s�2) throws Exception{
    	String deney = "��z�m zaman�\tPar�ac�k2 say�s�\tk�me say�s�\ta�ama say�s�\tama� de�eri\tbo� k�me say�s�\n";
    	System.out.print(deney);
    	int Par�ac�k2Say�s�,k�meSay�s�;
    	for(Par�ac�k2Say�s�=par�ac�kSay�s�1;Par�ac�k2Say�s�<par�ac�kSay�s�2;Par�ac�k2Say�s�++){
    		for(k�meSay�s�=k�meSay�s�1;k�meSay�s�<k�meSay�s�2;k�meSay�s�++){
    			deneyYap(Par�ac�k2Say�s�, k�meSay�s�);
    		}
    	}
    	return deney;
    }
    
	private String ba�lang��Ko�ullar�n�Yaz() throws Exception {
		String ��z�m = "";
		��z�m += "kendine g�ven : " + par�ac�klar[0].kendineG�ven + "\n";
    	��z�m += "s�r�ye g�ven : " + par�ac�klar[0].s�r�yeG�ven+ "\n";
    	��z�m += "eylemsizlik : " + par�ac�klar[0].eylemsizlik + "\n";
    	��z�m += "ilkH�z : " + par�ac�klar[0].ilkH�z+ "\n";
    	��z�m += "parcac�k say�s� : " + par�ac�kSay�s� + "\n";
    	��z�m += "k�me say�s� : " + k�meSay�s� + "\n";
    	��z�m += "par�a makine matrisi : \n" + par�aMakine.yaz() + "\n";
    	��z�m += "par�ac�klar : \n\n";
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		��z�m += "par�ac�k " + par�ac�kNo + par�ac�klar[par�ac�kNo].yaz() + "\n\n\n";
    	}
		return ��z�m;
	}
    public String yaz() throws Exception{
    	String yaz� = "";
    	yaz� += s�r�Ba��n�Yaz();
    	yaz� += ba�lang��Ko�ullar�n�Yaz();
    	yaz� += par�ac�klar�Yaz();
    	return yaz�;
    }

	private String par�ac�klar�Yaz() throws Exception {
		String yaz� = "";
		int Par�ac�k2No;
    	for(Par�ac�k2No=0;Par�ac�k2No<par�ac�kSay�s�;Par�ac�k2No++){
    		yaz� += "Par�ac�k2  no : " + Par�ac�k2No  + "\n\n";
    		yaz� += par�ac�klar[Par�ac�k2No].yaz() + "\n\n";
    	}
		return yaz�;
	}

	private String s�r�Ba��n�Yaz() throws Exception {
		String yaz� = "";
		if(s�r�Ba�� != -1){
    		yaz� += "s�r� ba�� : " + s�r�Ba�� + "\n";
    		//yaz� += "s�r�n�n en iyi ama� de�eri : " + Par�ac�k2lar[s�r�Ba��].ama�De�eri + "\n\n";
    		//yaz� += "s�r�n�n en iyi k�me merkezleri : \n" + Par�ac�k2lar[s�r�Ba��].k�meMerkezleri.yaz("\t") + "\n\n";
    		yaz� += par�ac�klar[s�r�Ba��].yaz();
    	}
		return yaz�;
	}
}



