package yap�salK�meleme;
import veriYap�lar�.*;

import java.util.ArrayList;

import S�k��t�r�lm��VeriYap�lar�.EksikBoolDizi;
import k�meci.K�meleme;
/**
 * @author Furkan
 */

public class Ba�lant�l� extends K�meleme{

    DoubleListDizi makinelerBenzerlik = new DoubleListDizi(); // bilgilerin saklanaca�� matris
    public double e�ikDe�er = -1;
    public double e�ikDe�er�arpan�=1; // her a�amada e�ik de�er ne kadar azalacak (�imdilik b�yle olsun)
    public int gruptakiEnFazlaMakineSay�s� =-1 ;
    public int enAzGrupSay�s� = -1;
    public String ��z�m = "";
    
    // her a�ama i�in yenilenmeli
    int grup1,grup2;
    double enB�y�kBenzerlik;
    double yeniBenzerlik ;
    
    Boolean e�ikKo�ulu,makineSay�s�Ko�ulu;
    // her a�ama i�in yenilenmeli
    // hep sabit
    int [] makinede��lenenPar�aSay�s� = new int [makineSay�s�()];
    int [][] makineOrtakPar�alar = new int [makineSay�s�()][];
    EksikBoolDizi eksikPar�aMakine = new EksikBoolDizi(par�aMakine);
    // hep sabit
    long ba�= System.currentTimeMillis(),son;
    // {{ kurucular
	public Ba�lant�l�(BoolDizi par�aMakine, String[] makine�simleri,String[] par�a�simleri) throws Exception {
		super(par�aMakine, makine�simleri, par�a�simleri);
		makineMatrisleriniDoldur();
		makineGruplar�Olu�tur();
	}
	public Ba�lant�l�(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
		makineMatrisleriniDoldur();
		makineGruplar�Olu�tur();
	}
	public Ba�lant�l�(String yaz�) throws Exception {
		super(yaz�);
		makineMatrisleriniDoldur();
		makineGruplar�Olu�tur();
	}
	public void e�ikDe�erBelirle(double e�ikDe�er){
    	this.e�ikDe�er = e�ikDe�er;
    }
	// }} kurucular
    
	// {{ makineler 
    private void makinede��lenenPar�alar�Doldur () throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<makineSay�s�();makineNo++){
    		makinede��lenenPar�aSay�s� [makineNo] = makinede��lenenPar�aSay�s�n�Bul(makineNo);
    	}
    }
    private void makineOrtakPar�alar�Bul () throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<makineSay�s�();sat�rNo++){
    		makineOrtakPar�alar [sat�rNo] = new int [sat�rNo+1];
    		for(int elemanNo = 0;elemanNo<makineOrtakPar�alar [sat�rNo].length;elemanNo++){
    			makineOrtakPar�alar [sat�rNo][elemanNo] = makineOrtakPar�alar2(sat�rNo, elemanNo);
    		}
    	}
    }
    protected int makineOrtakPar�aEri�(int makine1,int makine2){
    	if(makine1 < makine2){
    		return makineOrtakPar�alar[makine2][makine1];}
    	else
    	{
    		return makineOrtakPar�alar[makine1][makine2];}
    }
    
    private void makineMatrisleriniDoldur() throws Exception{
    	makinede��lenenPar�alar�Doldur();
    	makineOrtakPar�alar�Bul();
    }
    
    public void makineBenzerlikMatrsiniHesapla() throws Exception{
		int makine1;
		for(makine1=0;makine1<makine�simleri.length;makine1++){
			makineSat�r�nBenzerlikHesapla(makine1);
		}
	}
	private void makineSat�r�nBenzerlikHesapla(int makine1) throws Exception {
		DoubleListVekt�r yeniSat�r = new DoubleListVekt�r();
		int makine2;
		for(makine2=0;makine2<makine1+1;makine2++){
			double benzerlikKatsay�s� = makineBenzerlikHesapla(makine1, makine2);
			yeniSat�r.ekle(benzerlikKatsay�s�);
		}
	    makinelerBenzerlik.sat�rEkle(yeniSat�r);
	}
	private double makineBenzerlikHesapla(int makine1, int makine2) throws Exception{
		if(makine1 == makine2){
			return 0.0; // ayn� makineleri gruplamas�n diye
		}
		else{
			double makine1de��lenen = makinede��lenenPar�aSay�s� [makine1];
			double makine2de��lenen = makinede��lenenPar�aSay�s� [makine2];
			double ikisinde��lenen = makineOrtakPar�aEri�(makine1, makine2);
			double payda = makine1de��lenen + makine2de��lenen - ikisinde��lenen;
			return ikisinde��lenen / payda;
		}
	}
    private int makinede��lenenPar�aSay�s�n�Bul(int makineNo) throws Exception{
		makineKontrolEt(makineNo);
		int sutunNo,par�aSay�s�=0;
		for(sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
			if(par�aMakine.eri�(makineNo, sutunNo) == true){
				par�aSay�s� ++;
			}
		}
		return par�aSay�s�;
	}
	private int makineOrtakPar�alar(int makine1,int makine2) throws Exception{
		makineKontrolEt(makine1);
		makineKontrolEt(makine2);
		int ortakPar�a = 0;
		int sutunNo;
		for(sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
			if(par�aMakine.eri�(makine2, sutunNo) && par�aMakine.eri�(makine1, sutunNo) ){
				ortakPar�a++;
			}
		}
		return ortakPar�a;
	}
	
    private void makineKontrolEt(int makine1) throws Exception {
		if(makine1<0 || makine1 > makine�simleri.length-1){
			throw new Exception("girilen makine 1 ge�ersiz makine1 = " +makine1 + " toplam makine say�s� = " + makine�simleri.length + "\n");
		}
	}
    
    protected double makineBenzerlikBul(int makine1, int makine2) throws Exception{
		if(makine1 > makine2){ // b�y�k olan sat�r olsun
			return makinelerBenzerlik.eri�(makine1, makine2);
		}
		else{
			return makinelerBenzerlik.eri�(makine2, makine1);
		}	
	}
    /**
     * eksik matrisi kullanarak benzerlik bulur
     * @param makine1
     * @param makine2
     * @return
     * @throws Exception
     */
    private int makineOrtakPar�alar2(int makine1,int makine2) throws Exception{
		makineKontrolEt(makine1);
		makineKontrolEt(makine2);
		int ortakPar�a = 0;
		int [] kesi�im = eksikPar�aMakine.kesi�im(makine1, makine2);
		return kesi�imeBak(makine1, makine2, ortakPar�a, kesi�im);
	}
	private int kesi�imeBak(int makine1, int makine2, int ortakPar�a,int[] kesi�im) throws Exception {
		if(kesi�im[0] == -1){
			return 0;
		}
		else {
			return aral�kTara(makine1, makine2, ortakPar�a, kesi�im);
		}
	}
    // }} makineler 
	private int aral�kTara(int makine1, int makine2, int ortakPar�a,int[] kesi�im) throws Exception {
		int sutunNo;
		for(sutunNo=kesi�im[0];sutunNo<kesi�im[1];sutunNo++){
			if(par�aMakine.eri�(makine2, sutunNo) && par�aMakine.eri�(makine1, sutunNo) ){
				ortakPar�a++;
			}
		}
		return ortakPar�a;
	}
    
    // {{ gruplar
    protected double grupBenzerlikBul(int grup1, int grup2){
    	if(grup1 > grup2){
    		return makinelerBenzerlik.eri�(grup1, grup2);
    	}
    	else{
    		return makinelerBenzerlik.eri�(grup2, grup1);
    	}
    }
    
    protected int [] en�okBenzeyenleriBul(){
    	return makinelerBenzerlik.enB�y���nYeri();
    }
    /**
     * makine ve ya makine gruplar�n� birle�tirir
     * @param grup1
     * @param grup2
     * @throws Exception
     */
    public void grupla() throws Exception{
    	gruplar�Birle�tir(grup1, grup2);
    	// �imdi benzerlik matrisi daralmal�
    	makinelerBenzerlik.sat�rSutunSil(grup1, grup1);
    	makinelerBenzerlik.sat�rSutunSil(grup2, grup2);
    }

    
	private Boolean grupSay�s�Ko�ulu(){
		if(enAzGrupSay�s� != -1){
			return grupSay�s�naBak();
		}
		return true;
	}
	/**
	 * e�er bu iki grup birle�irse makinesay�s� ko�ulu sa�lanacak m� ona bakar
	 * <br>bu i�lemi gerekli ise yapar
	 * @param grup1
	 * @param grup2
	 * @return
	 */
	private Boolean makineSay�s�Ko�ulu(int grup1, int grup2) {
		if(gruptakiEnFazlaMakineSay�s� != -1){
			ArrayList<Integer> birinciGrup = makineGruplar�.get(grup1);
    	    ArrayList<Integer> ikinciGrup = makineGruplar�.get(grup2);
    	    if(birinciGrup.size() + ikinciGrup.size() > gruptakiEnFazlaMakineSay�s�){
    	    	return  false;
    	    }
		}
		return true;
	}
	/**
	 * diyelim ki en fazla 4 grup istiyoruz 4 grup var demek ki biz devam etmesine izin vermicez 
	 * <br> ��nk� devam ederse 3 grup olacakt�r
	 * @return
	 */
	private Boolean grupSay�s�naBak() {
		if(makineGruplar�.size() <= enAzGrupSay�s�){ 
			return false;
		}
		else{
			return true;
		}
	}
	
	// }} gruplar
	
	// {{ kontrol
    protected DoubleListVekt�r yeniSat�rHaz�rla() throws Exception{
    	DoubleListVekt�r yeniSat�r = new DoubleListVekt�r();
    	int [] k���kB�y�k = new int [2];
    	k���kB�y�kBelirle(k���kB�y�k);
    	benzerlikleriBul(yeniSat�r, k���kB�y�k);
    	yeniSat�r.ekle(0.0);
    	return yeniSat�r;
    }
	private void benzerlikleriBul( DoubleListVekt�r yeniSat�r, int[] k���kB�y�k) {
		int grupSay�s� = makineGruplar�.size();
		int grupNo;
    	grupNo = k����eKadarOlanBenzerlikleriBul(yeniSat�r, k���kB�y�k);
    	grupNo++; // k����� atlad�k �imdi de b�y��� atlicaz
    	grupNo = b�y��eKadarBenzerlikleriBul( yeniSat�r, grupNo, k���kB�y�k);
    	grupNo++; // b�y��� atlad�k
    	grupNo = b�y�ktenSonras�n�Bul(yeniSat�r, grupNo,grupSay�s�);
	}
	private int b�y�ktenSonras�n�Bul(DoubleListVekt�r yeniSat�r, int grupNo, int grupSay�s�) {
		for(;grupNo<grupSay�s�;grupNo++){
    		double yeniBenzerlik = yeniBenzerlikHesapla(grupNo);
    	    yeniSat�r.ekle(yeniBenzerlik);
    	}
		return grupNo;
	}
	private int b�y��eKadarBenzerlikleriBul( DoubleListVekt�r yeniSat�r, int grupNo, int[] k���kB�y�k) {
		for(;grupNo<k���kB�y�k[1];grupNo++){
			double yeniBenzerlik = yeniBenzerlikHesapla(grupNo);
    	    yeniSat�r.ekle(yeniBenzerlik);
    	}
		return grupNo;
	}
	private int k����eKadarOlanBenzerlikleriBul(DoubleListVekt�r yeniSat�r, int[] k���kB�y�k) {
		int grupNo;
		for(grupNo = 0;grupNo<k���kB�y�k[0];grupNo++){
			double yeniBenzerlik = yeniBenzerlikHesapla(grupNo);
    	    yeniSat�r.ekle(yeniBenzerlik);
    	}
		return grupNo;
	}
	private void k���kB�y�kBelirle(int[] k���kB�y�k) {
		if(grup1 < grup2){
    		k���kB�y�k[0] = grup1;
    		k���kB�y�k[1] = grup2;
    	}
    	else{
    		k���kB�y�k[0] = grup2;
    		k���kB�y�k[1] = grup1;
    	}
	}
	protected double yeniBenzerlikHesapla(int grupNo) {
		return -10000;
	}
	public void ilerle() throws Exception{
		adayGruplar�Yaz();
		DoubleListVekt�r yeniSat�r = yeniSat�rHaz�rla();
		grupla();
		makinelerBenzerlik.sat�rEkle(yeniSat�r);
		��z�m += "yeni par�a makine matrisi = \n" + yeniPar�aMakineMatrisiYap().ba�l�kl�Yaz(makine�simleriS�ral�(), par�a�simleri) + "\n\n";
	}
	/**
	 * <br>t�m k�s�tlar� sa�layan uygun olurlu gruplar bulunduktan sonra bunlar�n aras�ndan en iyi iki grup bulunacak
	   <br>olurlu grup olabilmek i�in
	   <br>ko�ul 1 : iki grubun benzerli�i e�ik de�erden fazla olmal�
	   <br>ko�ul 2 : iki grup birle�tirildi�inde gruptaki makine say�s� en fazla makine say�s�n� a�mamal�
	   <br>ko�ul 3 : iki grup birle�ince toplam grup say�s�n� a�mamal�
	   <br>e�er uygun gruplar bulunamazsa iki grup da -1 d�ner
	 */
	public void sat�rSutunBul() {
        a�amaDe�i�kenleriHaz�rla();
		int birinciGrup;
		for(birinciGrup=0;birinciGrup<makinelerBenzerlik.sat�rSay();birinciGrup++){
			sat�rdaOlurluGrupAra(birinciGrup);
		}
	}
	private void a�amaDe�i�kenleriHaz�rla() {
        grup1 = -1;
        grup2 = -1;
        enB�y�kBenzerlik = -1;
        makineSay�s�Ko�ulunuBa�lat();
        e�ikKo�ulunuBa�lat();
	}
	private void makineSay�s�Ko�ulunuBa�lat() {
		if(gruptakiEnFazlaMakineSay�s� != -1){
        	makineSay�s�Ko�ulu = false;
        }
        else{
        	makineSay�s�Ko�ulu = true;
        }
	}
	private void e�ikKo�ulunuBa�lat() {
		if(e�ikDe�er != -1){ 
        	e�ikKo�ulu = false;	// k�s�tlama getirilmi� o zaman ilk de�eri false olsun gerekirse kod onu true yapar
        }
        else{
        	e�ikKo�ulu = true; // zaten k�s�tlama getirilmemi�
        }
	}
	
	
    private void sat�rdaOlurluGrupAra(int birinciGrup) {
		int ikinciGrup;
		DoubleListVekt�r sat�r = makinelerBenzerlik.dizi.get(birinciGrup);
		for(ikinciGrup=0;ikinciGrup<sat�r.boy();ikinciGrup++){ 
			yeniBenzerlikAra(birinciGrup, ikinciGrup);
		}
	}
	private void yeniBenzerlikAra(int birinciGrup, int ikinciGrup) {
		yeniBenzerlik = grupBenzerlikBul(birinciGrup, ikinciGrup);
		if(yeniBenzerlik > enB�y�kBenzerlik){ // e�er yeni durum daha 
			// iyi ise olur mu ona bak�ls�n ��nk� iyi olup olmada��na bakmak kolay. Gereksiz i�lemden kurtulmu� oluruz
			if( grupOlurluMu(birinciGrup, ikinciGrup)  ){ // hem olurlu hem de iyi bir durum olmas� gerekir
				yeniSat�rSutunBul(birinciGrup, ikinciGrup);
			}	
		}
	}
	/**
	 * e�er buraya gelindi ise demek ki ilerlenebinir
	 * @param birinciGrup
	 * @param ikinciGrup
	 */
	private void yeniSat�rSutunBul(int birinciGrup, int ikinciGrup) {
			grup1 = birinciGrup;
			grup2 = ikinciGrup;	
			enB�y�kBenzerlik = yeniBenzerlik;
	}
	private Boolean grupOlurluMu(int sat�rNo, int sutunNo) {
		makineSay�s�Ko�ulu = makineSay�s�Ko�ulu(sat�rNo, sutunNo);
		e�ikKo�ulunaBak();
		return ( makineSay�s�Ko�ulu && e�ikKo�ulu && e�ikKo�ulu);
	}
	private void e�ikKo�ulunaBak() {
		if(e�ikKo�ulu == false){ // false ise true yapmaya �al�� ama true ise dokunma demek ki ko�ul sa�lanm�� ��nk�
			e�ikKo�ulu = yeniBenzerlik > e�ikDe�er;
		}
	}
   
	// }} kontrol
    
	// {{ yazd�rma 
    private void adayGruplar�Yaz() {
		��z�m += "sat�r = " + grup1 + " sutun : " + grup2 + "\n";
		��z�m += "grup1 : " + makineGruplar�.get(grup1) + " grup 2 :" + makineGruplar�.get(grup2) +"\n";
	}
	
	public String ��z() throws Exception{
		makineBenzerlikMatrsiniHesapla();
		int a�amaNo=1;
		a�amaDe�i�kenleriHaz�rla();
		��z�m += super.yaz();
		��z�m += "a�amaNo = 0" + "\n\n" + yaz()  + "\n\n";
		for(;;a�amaNo++){
			if(grupSay�s�Ko�ulu()==false){
				break;
			}
			sat�rSutunBul();
			if(e�ikKo�ulu && makineSay�s�Ko�ulu && enB�y�kBenzerlik != 0){
				ilerle();
				��z�m += "a�amaNo = " + a�amaNo + "\n\n" + yaz() + "\n\n";
			}
			else{ // e�ik ve makineSay�s�ndan ge�emedi
				break;
			}
				
		}
		son = System.currentTimeMillis();
		��z�m += "��z�m s�resi : " + (son -ba�) + " milisaniye\n\n";
		// �imdi burda sat�rlar�n s�ralamas�n� bulduk �imdi de sutunlar�n s�ras�n� bulmak i�in derece s�ralama algoritmas� kullancaz
		// ama �nce par�a makine matrisinin d�zenlenmesi gerekiyor
		
		return ��z�m;
	}
	
    @Override
    public String  yaz() throws Exception{
    	String  yaz� ="";// super.yaz() + "\n\n";
    	//yaz� += "grup1 = " + grup1 + " grup2 : "  + grup2;
    	yaz� = kararVericiDe�erleriYaz(yaz�);
    	yaz� = benzerli�iYaz(yaz�) + "\n\n";
    	//yaz� += ��z�m + "\n\n";
    	return yaz�;
    }
	private String benzerli�iYaz(String yaz�) {
		yaz� += "\t";
		yaz� = ilkSat�r�Yaz(yaz�);
    	yaz� = benzerli�inAltK�sm�Yaz(yaz�);
		return yaz�;
	}
	private String benzerli�inAltK�sm�Yaz(String yaz�) {
		int makineNo;
		yaz� += "\n";
    	for(makineNo=0;makineNo<makineGruplar�.size();makineNo++){
    		yaz� += makineGruplar�.get(makineNo) + 
    	            "\t" + makinelerBenzerlik.dizi.get(makineNo).yaz('\t') + "\n";
    	}
		return yaz�;
	}
	private String ilkSat�r�Yaz(String yaz�) {
		int makineNo;
    	for(makineNo=0;makineNo<makineGruplar�.size();makineNo++){
    		yaz� += makineGruplar�.get(makineNo) + "\t";
    	}
		return yaz�;
	}
	private String kararVericiDe�erleriYaz(String yaz�) {
		if(e�ikDe�er != -1){
    		yaz� += "e�ik de�er : " + e�ikDe�er +"\n";
    		yaz� += "�art sa�land� m� = " + e�ikKo�ulu +"\n"; 
    	}
    	if(gruptakiEnFazlaMakineSay�s� != -1){
    		yaz� += "gruptaki en fazla makine say�s� = " + gruptakiEnFazlaMakineSay�s� + "\n";
    		yaz� += "�art sa�land� m� = " + makineSay�s�Ko�ulu + "\n";
    	}
    	if(enAzGrupSay�s� != -1){
    		yaz� += "en az grup say�s� : " + enAzGrupSay�s� + "\n";
    		yaz� += "�art sa�land� m� = " + grupSay�s�Ko�ulu() + "\n";
    		yaz� += "grup say�s� = " + makineGruplar�.size() + "\n";
    	}
		return yaz�;
	}
    
	// }} yazd�rma 
    public String h�zl���z() throws Exception{
    	String ��z�m = "";
    	makineBenzerlikMatrsiniHesapla();
		int a�amaNo=1;
		a�amaDe�i�kenleriHaz�rla();
		for(;;a�amaNo++){
			if(grupSay�s�Ko�ulu()==false){
				break;
			}
			sat�rSutunBul();
			if(e�ikKo�ulu && makineSay�s�Ko�ulu && enB�y�kBenzerlik != 0){
				h�zl��lerle();
			}
			else{ // e�ik ve makineSay�s�ndan ge�emedi
				break;
			}
		}
		��z�m += "son par�a makine matrisi : \n" + yeniPar�aMakineMatrisiYaz2() + "\n\n";
		��z�m += "k�meler : \n" + makineGruplar�n�Yaz() + "\n\n";
		son = System.currentTimeMillis();
		��z�m += "��z�m s�resi : " + (son -ba�) + " milisaniye\n\n";
        // �imdi burda sat�rlar�n s�ralamas�n� bulduk �imdi de sutunlar�n s�ras�n� bulmak i�in derece s�ralama algoritmas� kullancaz
		// ama �nce par�a makine matrisinin d�zenlenmesi gerekiyor
		if(dendogramVarM�){
			dendogram�iz();
		}
		return ��z�m;
    }
    private void h�zl��lerle() throws Exception{
    	adayGruplar�Yaz();
		DoubleListVekt�r yeniSat�r = yeniSat�rHaz�rla();
		grupla();
		makinelerBenzerlik.sat�rEkle(yeniSat�r);
    }

}
