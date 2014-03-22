package metaSezgisel;
import java.util.ArrayList;

import k�meci.K�meleme;
import veriYap�lar�.*;
public class Par�ac�kS�r� extends K�meleme {

	
	public int par�ac�kSay�s�;
	public int k�meSay�s�;
	private Par�ac�k [] par�ac�klar;
	
	// g�ven parametreleri belirlenmedi�i zaman par�alar kendi g�ven de�erlini kullan�r
	
	protected int s�r�Ba��=-1;
	public long ��z�mZaman�;
	
	public int enFazlaA�ama;
	public int enFazla�lerleme;
	public boolean ilerlemeK�s�tl� = true;
	
	public double duyarl�l�k = 0.00000000001;
	public Par�ac�kS�r�(BoolDizi par�aMakine, String[] makine�simleri,String[] par�a�simleri) {
		super(par�aMakine, makine�simleri, par�a�simleri);
		makineGruplar�Olu�tur();
	}
    public Par�ac�kS�r�(String yaz�, char ay�r�c�,int k�meSay�s�,int par�ac�kSay�s�) throws Exception {
		super(yaz�, ay�r�c�);
		this.k�meSay�s� = k�meSay�s�;
    	this.par�ac�kSay�s� = par�ac�kSay�s�;
    	par�ac�klar�Olu�tur();
	}
	public Par�ac�kS�r�(String yaz�,int k�meSay�s�,int par�ac�kSay�s�) throws Exception {
		super(yaz�);
		this.k�meSay�s� = k�meSay�s�;
    	this.par�ac�kSay�s� = par�ac�kSay�s�;
    	par�ac�klar�Olu�tur();
	}
         
	private void par�ac�klar�Olu�tur() throws Exception {
    	par�ac�klar = new Par�ac�k [this.par�ac�kSay�s�];
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�klar[par�ac�kNo] = new Par�ac�k(k�meSay�s�,par�aMakine);
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
		double yeniAma� = par�ac�klar[par�ac�kNo].ama�De�eriHesapla();
		if(yeniAma� > par�ac�klar[s�r�Ba��].ama�De�eri){
			s�r�Ba�� = par�ac�kNo;
		}
	}
    
	private void par�aNoKontrolEt(int par�aNo) throws Exception {
		if(par�aNo > par�ac�kSay�s�){
    		throw new Exception("par�ano par�ac�k say�s�ndan fazla olamaz par�aNo : " + par�aNo + " par�ac�k say�s� : " + par�ac�kSay�s� + "\n");
    	}
	}
    private void par�ac�kKonumuG�ncelle(int par�ac�kNo) throws Exception{
    	par�aNoKontrolEt(par�ac�kNo);
        Par�ac�k s�r��nderi = s�r��nderi();
    	par�ac�klar[par�ac�kNo].konumuG�ncelle(s�r��nderi);
    }
    public void par�ac�klar�nKonumunuG�ncelle() throws Exception{
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�kKonumuG�ncelle(par�ac�kNo);
    	}
    }

    /**
     * t�m par�ac�klar�n ortalamaK�meler��i uzakl�k ve ortalamaK�melerAras�Uzakl�k de�i�kenini s�f�rlar
     */
    private void par�ac�klar�nUzakl�klar�n�S�f�rla(){
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�klar[par�ac�kNo].uzakl�klar�S�f�rla();
    	}
    }
    public void ilerle() throws Exception{
    	par�ac�klar�nAma�De�erleriniYenile();
    	par�ac�klar�nUzakl�klar�n�S�f�rla();
    	par�ac�klar�nK�meleriniS�f�rla();
    	s�r�n�nBa��n�Bul();
    	par�ac�klar�nKonumunuG�ncelle();
    }
    private void par�ac�klar�nAma�De�erleriniYenile(){
    	int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�klar[par�ac�kNo].ama�De�eriVar = false;
    	}
    }
    private void par�ac�klar�nK�meleriniS�f�rla() throws Exception{
    	for(int par�ac�kNo = 0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		par�ac�klar [par�ac�kNo] .k�meleriS�f�rla();
    	}
    }
    
    // ��z�m y�ntemleri ba�
    public String ��z() throws Exception{
    	String ��z�m = "";
    	long ba� = System.currentTimeMillis();
    	��z�m = ba�lang��();
    	��z�m += ��z�mD�ng�s�();
    	makineGruplar� = par�ac�klar[s�r�Ba��].k�meler;
    	��z�m += yaz();
    	long son = System.currentTimeMillis();
    	��z�mZaman� = son - ba�;
    	��z�m += "��z�m zaman� : " + ��z�mZaman� + " milisaniye";
    	return ��z�m;
    }
	private String ��z�mD�ng�s�() throws Exception {
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
    	h�zl���z�mD�ng�s�n�Kur();
    	makineGruplar� = par�ac�klar[s�r�Ba��].k�meler;
    	��z�m += "par�a makine matrisi : " + yeniPar�aMakineMatrisiYaz2() + "\n\n";
    	long son = System.currentTimeMillis();
    	��z�mZaman� = son - ba�;
    	��z�m += "��z�m zaman� : " + ��z�mZaman� + " milisaniye";
    	return ��z�m;
    }
	private String ��z�m�Yaz() throws Exception {
		String ��z�m = "";
		��z�m += h�zl���z�mD�ng�s�n�Kur();
    	��z�m += "son: \n\n" + s�r��nderi().yaz() + "________________________________________________________\n\n";
    	makineGruplar� = par�ac�klar[s�r�Ba��].k�meler;
    	��z�m += "yeni par�a makine matrisi : \n" + yeniPar�aMakineMatrisiYaz2()+ "\n";
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
	
	
    // ��z�m y�ntemleri son
    protected Par�ac�k s�r��nderi(){
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
    	String deney = "";//"��z�m zaman�\tpar�ac�k say�s�\tk�me say�s�\ta�ama say�s�\tama� de�eri";
    	this.par�ac�kSay�s� = par�ac�kSay�s�;
    	this.k�meSay�s� = k�meSay�s�;
    	par�ac�klar�Olu�tur();
    	int a�ama = deney��in��z();
    	deney +=  ��z�mZaman� + "\t";
    	deney +=  par�ac�kSay�s�+ "\t";
    	deney +=  k�meSay�s�+ "\t";
    	deney +=  a�ama+ "\t";
    	Par�ac�k �nder = s�r��nderi();
    	deney +=  �nder.ama�De�eri+ "\t";
    	deney +=  �nder.bo�K�meSay�s�Bul() + "\t";
    	System.out.print(deney+"\n");
    	return deney;
    }
    public String deneyYap(int par�ac�kSay�s�1,int par�ac�kSay�s�2,int k�meSay�s�1,int k�meSay�s�2) throws Exception{
    	String deney = "��z�m zaman�\tpar�ac�k say�s�\tk�me say�s�\ta�ama say�s�\tama� de�eri\tbo� k�me say�s�\n";
    	System.out.print(deney);
    	int par�ac�kSay�s�,k�meSay�s�;
    	for(par�ac�kSay�s�=par�ac�kSay�s�1;par�ac�kSay�s�<par�ac�kSay�s�2;par�ac�kSay�s�++){
    		for(k�meSay�s�=k�meSay�s�1;k�meSay�s�<k�meSay�s�2;k�meSay�s�++){
    			deneyYap(par�ac�kSay�s�, k�meSay�s�);
    		}
    	}
    	return deney;
    }
    
	private String ba�lang��Ko�ullar�n�Yaz() {
		String ��z�m = "";
		��z�m += "kendine g�ven : " + par�ac�klar[0].kendineG�ven + "\n";
    	��z�m += "s�r�ye g�ven : " + par�ac�klar[0].s�r�yeG�ven+ "\n";
    	��z�m += "eylemsizlik : " + par�ac�klar[0].eylemsizlik + "\n";
    	��z�m += "ilkH�z : " + par�ac�klar[0].ilkH�z+ "\n";
    	��z�m += "parcac�k say�s� : " + par�ac�kSay�s� + "\n";
    	��z�m += "k�me say�s� : " + k�meSay�s� + "\n";
		return ��z�m;
	}
    public String yaz() throws Exception{
    	String yaz� = "";
    	yaz� += s�r�Ba��n�Yaz();
    	yaz� += ba�lang��Ko�ullar�n�Yaz();
    	yaz� += par�ac�klar�Yaz() + "\n";
    	yaz� += "yeni par�a makine matrisi : " + yeniPar�aMakineMatrisiYap().yaz()+ "\n";
    	return yaz�;
    }

	private String par�ac�klar�Yaz() {
		String yaz� = "";
		int par�ac�kNo;
    	for(par�ac�kNo=0;par�ac�kNo<par�ac�kSay�s�;par�ac�kNo++){
    		yaz� += "par�ac�k  no : " + par�ac�kNo  + "\n\n";
    		yaz� += par�ac�klar[par�ac�kNo].yaz() + "\n\n";
    	}
		return yaz�;
	}

	private String s�r�Ba��n�Yaz() {
		String yaz� = "";
		if(s�r�Ba�� != -1){
    		yaz� += "s�r� ba�� : " + s�r�Ba�� + "\n";
    		//yaz� += "s�r�n�n en iyi ama� de�eri : " + par�ac�klar[s�r�Ba��].ama�De�eri + "\n\n";
    		//yaz� += "s�r�n�n en iyi k�me merkezleri : \n" + par�ac�klar[s�r�Ba��].k�meMerkezleri.yaz("\t") + "\n\n";
    		yaz� += par�ac�klar[s�r�Ba��].yaz();
    	}
		return yaz�;
	}

	

}
