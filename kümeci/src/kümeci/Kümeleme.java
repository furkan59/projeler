package k�meci;
import veriYap�lar�.*;
import yap�salK�meleme.Dendogram;
import yap�salK�meleme.DereceS�ra;

import java.util.ArrayList;

public class K�meleme {

	protected BoolDizi par�aMakine;
	protected String [] makine�simleri;
	protected String [] par�a�simleri;
	protected ArrayList<ArrayList<Integer>> makineGruplar� = new ArrayList<ArrayList<Integer>>();
	protected ArrayList<ArrayList<Integer>> par�aGruplar� = new ArrayList<ArrayList<Integer>>();
	
	protected ArrayList<ArrayList<Integer>> gruplananlar = new ArrayList<ArrayList<Integer>>();
	public boolean dendogramVarM� = false;
	protected DoubleDizi k�meMerkezleri ;
	
	// {{ kurucular 
	public K�meleme(BoolDizi par�aMakine,String [] makine�simleri,String [] par�a�simleri) {
		this.par�a�simleri = par�a�simleri;
		this.makine�simleri = makine�simleri;
		this.par�aMakine = par�aMakine;
	}
	protected void makineGruplar�Olu�tur() {
		int elemanNo;
		for(elemanNo = 0; elemanNo < makine�simleri.length ; elemanNo++){
			ArrayList<Integer> yeniGrup = new ArrayList<Integer>();
			yeniGrup.add(elemanNo);
			makineGruplar�.add(yeniGrup);
		}
	}
	/**
	 * makine isimleri; 
	 * par�a  isimleri;
	 * par�a - makine Matrisi
	 * @param yaz�
	 * @throws Exception 
	 */
	public K�meleme(String yaz�,char ay�r�c�) throws Exception{
		String [] b�l�nm�� = yaz�.split(";");
		String  ay�ra� = "" + ay�r�c�;
		makine�simleriniAl(b�l�nm��, ay�ra�);
		par�a�simleriniAl(b�l�nm��, ay�ra�);
		par�aMakine = new BoolDizi(b�l�nm��[2], ay�r�c�);
	}
	private void par�a�simleriniAl(String[] b�l�nm��, String ay�ra�) {
		par�a�simleri = b�l�nm��[0].split(ay�ra�);
		if(par�a�simleri[0].contains("\n")){
			par�a�simleri[0] = par�a�simleri[0].split("\n")[0];
		}
		if(par�a�simleri[par�a�simleri.length-1].contains("\n")){
			par�a�simleri[par�a�simleri.length-1] = par�a�simleri[par�a�simleri.length-1].split("\n")[0];
				
		}
	}
	private void makine�simleriniAl(String[] b�l�nm��, String ay�ra�) {
		makine�simleri =  b�l�nm��[1].split(ay�ra�);
		if(makine�simleri[0].contains("\n")){
			makine�simleri[0] = makine�simleri[0].split("\n")[0];
		}
		if(makine�simleri[makine�simleri.length-1].contains("\n")){
			makine�simleri[makine�simleri.length-1] =  makine�simleri[makine�simleri.length-1].split("\n")[0];
		}
	}
	/**
	    p1	p2	p3	p4	p5	p6	p7	p8 
<br>m1	1	0	1	0	0	1	0	1
<br>m2	1	0	0	0	0	1	0	0
<br>m3 	0	1	0	0	1	0	0	1
<br>m4  1	0	1	0	0	1	0	0
<br>m5	0	0	0	1	0	0	1	0
<br>m6  0	1	0	0	1	0	0	1
<br>m7  0	0	0	0	1	0	0	1
<br>m8	1	0	1	0	0	1	0	0
<br>m9	0	0	0	1	0	0	1	0
<br>m10	0	1	0	0	0	0	1	0
<br>bu �ekilde girilecek
<br> veriler excelden dirke al�nabilir
	 * @param yaz�
	 * @throws Exception 
	 */
	public K�meleme(String yaz�) throws Exception{
		String [] b�l�nm�� = yaz�.split("\\n");
		String [] par�alar = b�l�nm��[0].split("\t");
		// ilk eleman bo� olacak
		par�a�simleriniAl(par�alar);
		makine�simleri = new String [b�l�nm��.length-1];
		par�aMakine = new BoolDizi(makine�simleri.length, par�a�simleri.length, false);
		int sat�rNo;
		for(sat�rNo=1;sat�rNo<b�l�nm��.length;sat�rNo++){
			String [] yeniSat�r = b�l�nm��[sat�rNo].split("\t");
			yeniMakineAl(sat�rNo, yeniSat�r);
			BoolVekt�r yeni = new BoolVekt�r(b�l�nm��[sat�rNo],1,"\t");
			par�aMakine.matris[sat�rNo-1] = yeni;
		}
	}
	private void yeniMakineAl(int sat�rNo, String[] yeniSat�r) {
		String yeniMakine = yeniSat�r[0];
		makine�simleri[sat�rNo-1] = yeniMakine;
	}
	private void par�a�simleriniAl(String[] par�alar) {
		par�a�simleri  = new String [par�alar.length-1];
		int par�aNo;
		for(par�aNo=1;par�aNo<par�alar.length;par�aNo++){
			par�a�simleri[par�aNo-1] = par�alar[par�aNo];
		}
	}
    
	// }} kurucular 
	
	// {{ yazd�rma 
    public String yaz() throws Exception{
		String yaz� = "";
 		yaz� = par�alar�Yaz(yaz�);
		yaz� += "\n\n";
		yaz� = makineleriVeMatrisiYaz(yaz�);
		int grupNo;
		for(grupNo=0;grupNo<makineGruplar�.size();grupNo++){
			yaz� += "grup " + (grupNo+1) + " = " + makineGruplar�.get(grupNo).toString() +"\n";
		}
		return yaz�;
	}
	private String par�alar�Yaz(String yaz�) {
		int par�aNo;
 		yaz� += "\t";
 		for(par�aNo=0;par�aNo<par�a�simleri.length;par�aNo++){
 			yaz� += par�a�simleri[par�aNo] + "\t";
 		}
		return yaz�;
	}
	private String makineleriVeMatrisiYaz(String yaz�) throws Exception {
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
			yaz� += makine�simleri[sat�rNo] + "\t" + par�aMakine.sat�r(sat�rNo).yaz() + "\n";
		}
		return yaz�;
	}
	// }} yazd�rma 
	
    // {{ gruplar
	/**
	 * makine ve ya makine gruplar�n� birle�tirir
	 * @param grup1
	 * @param grup2
	 * @throws Exception
	 */
	protected void gruplar�Birle�tir(int grup1, int grup2) throws Exception{
		if(grup1 == grup2){
			throw new Exception("ayn� gruplar bunlar " + grup1 + "\n");
		}
		ArrayList<Integer> birinciGrup = makineGruplar�.get(grup1);
		ArrayList<Integer> ikinciGrup = makineGruplar�.get(grup2);
		if(dendogramVarM�){
			ArrayList<Integer> yeniGruplananlar = new ArrayList<Integer>();
			yeniGruplananlar.add(grup1);
			yeniGruplananlar.add(grup2);
			gruplananlar.add(yeniGruplananlar);
		}
		yeniGrubuEkle(birinciGrup, ikinciGrup);
		eskiGruplar�Sil(grup1, grup2);
	}
	private void eskiGruplar�Sil(int grup1, int grup2) {
		if(grup1 < grup2){ // grup1 k���k
			makineGruplar�.remove(grup1); // ilk �nce o aradan �ekilsin
			makineGruplar�.remove(grup2-1); // sonra grup1 gitti�i i�in grup2 bir geriye kayd�
		}
		else{
			makineGruplar�.remove(grup2);
			makineGruplar�.remove(grup1-1);
		}
	}
	private void yeniGrubuEkle(ArrayList<Integer> birinciGrup, ArrayList<Integer> ikinciGrup) {
		ArrayList<Integer> yeniGrup = new ArrayList<Integer>();
		yeniGrup.addAll(birinciGrup);
		yeniGrup.addAll(ikinciGrup);
		makineGruplar�.add(yeniGrup);
	}
    
	protected int makineninGrubunuBul(int makineNo) throws Exception{
		int grupNo,grupSay�s� = makineGruplar�.size();
		int makineninGrubu = -1;
		for(grupNo=0;grupNo<grupSay�s�;grupNo++){
			makineninGrubu = makineyiGruptaAra(makineNo, grupNo, makineninGrubu);
		}
		return makineVarM�Bak(makineninGrubu);
	}
	private int makineyiGruptaAra(int makineNo, int grupNo, int makineninGrubu) {
		ArrayList<Integer> grup = makineGruplar�.get(grupNo);
		if(grup.contains(makineNo)){
			makineninGrubu = grupNo;
		}
		return makineninGrubu;
	}
	private int makineVarM�Bak(int makineninGrubu) throws Exception {
		if(makineninGrubu == -1){
			throw new Exception("makine bulunamad� \n");
		}
		else{
			return makineninGrubu;
		}
	}

	
    protected int par�aSay�s�(){
    	return par�aMakine.sutunSay();
    }
    protected int makineSay�s�(){
    	return par�aMakine.sat�rSay();
    }

    protected String makineGruplar�n�Yaz(){
    	int sat�rNo;
    	String yaz� = "";
    	for(sat�rNo=0;sat�rNo<makineGruplar�.size();sat�rNo++){
    		yaz� += makineGruplar�.get(sat�rNo).toString()+ "\n"; 
    	}
        return yaz�;
    }
    protected int makineGruplar�TekBoyutluEri�im(int makineYeri) throws Exception{
    	int grupNo,birikim = 0;
    	for(grupNo=0;grupNo<makineGruplar�.size();grupNo++){
    		for(int yer = 0;yer < makineGruplar�.get(grupNo).size();yer ++){
    			if(makineYeri == birikim){
    				return makineGruplar�.get(grupNo).get(yer);
    			}
    			else{
    				birikim ++;
    			}
    		}
    	}
    	throw new Exception("makine bulunamad�\n girilen makine yeri : " + makineYeri + "\n\nt�m gruplar \n\n" + makineGruplar�n�Yaz() + "\n");
    }
    protected String [] makine�simleriS�ral�(){
    	int sat�rNo,makNo,elemaNo=0;
    	String [] s�ral� = new String [makine�simleri.length];
    	for(sat�rNo=0;sat�rNo<makineGruplar�.size();sat�rNo++){
    		ArrayList<Integer> yeniGrup = makineGruplar�.get(sat�rNo);
    		for(makNo=0;makNo<yeniGrup.size();makNo++){
    			s�ral� [elemaNo] = makine�simleri[yeniGrup.get(makNo)];
    			elemaNo++;
    		}
    	}
    	return s�ral�;
    }
    
    
    protected String par�aGruplar�n�Yaz(){
    	int sat�rNo;
    	String yaz� = "";
    	for(sat�rNo=0;sat�rNo<par�aGruplar�.size();sat�rNo++){
    		yaz� += par�aGruplar�.get(sat�rNo).toString()+ "\n"; 
    	}
        return yaz�;
    }
    protected int par�aGruplar�TekBoyutluEri�im(int par�aYeri) throws Exception{
    	int grupNo,birikim = 0;
    	for(grupNo=0;grupNo<par�aGruplar�.size();grupNo++){
    		for(int yer = 0;yer < par�aGruplar�.get(grupNo).size();yer ++){
    			if(par�aYeri == birikim){
    				return par�aGruplar�.get(grupNo).get(yer);
    			}
    			else{
    				birikim ++;
    			}
    		}
    	}
    	throw new Exception("par�a bulunamad�\n girilen par�a yeri : " + par�aYeri + "\n\nt�m gruplar \n\n" + par�aGruplar�n�Yaz() + "\n");
    }
    // }} gruplar
    
    // {{ yeni par�a makine matrisi yapma
    /**
     * ilk �nce sat�rlar� al�r ve sat�rlar�n s�rlam�� halini bulur sonra da  derece s�ra ile sutunlar� s�ralar
     * @return
     * @throws Exception
     */
    protected BoolDizi yeniPar�aMakineMatrisiYap() throws Exception{
    	BoolDizi yeni = new BoolDizi(par�aMakine.sat�rSay(), par�aMakine.sutunSay(), false);
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<yeni.sat�rSay();sat�rNo++){
    		int makineNo = makineGruplar�TekBoyutluEri�im(sat�rNo);
    		yeni.matris[sat�rNo] = par�aMakine.sat�r(makineNo);
    	}
    	DereceS�ra sutunlar = new DereceS�ra(yeni, makine�simleri, par�a�simleri);
    	sutunlar.sutunlar�S�rala();
    	return yeni;
    }
    /**
     * derece s�ra kullanmaz onun yerine haz�r olan par�a gruplar�n� kullan�r
     * @return
     * @throws Exception 
     */
    
    protected BoolDizi yeniPar�aMakineMatrisiYap2() throws Exception{
    	if(par�aGruplar�.size() == 0){ // y�ntem kendisi par�a grubu yapam�yorsa bu uygulan�r
    		par�aGruplar�n�Bo�Doldur();
    		par�aGruplar�n�Olu�tur();
    	}
    	int sat�rNo,sutunNo;
    	BoolDizi yeni = new BoolDizi(makineSay�s�(), par�aSay�s�(), false);
    	for(sat�rNo=0;sat�rNo<yeni.sat�rSay();sat�rNo++){
    		int makine = makineGruplar�TekBoyutluEri�im(sat�rNo);
    		for(sutunNo=0;sutunNo<yeni.sutunSay();sutunNo++){
    			int par�a = par�aGruplar�TekBoyutluEri�im(sutunNo);
    			boolean yeniDe�er = par�aMakine.eri�(makine, par�a);
    			yeni.de�i�(sat�rNo, sutunNo, yeniDe�er);
    		}
    	}
    	return yeni;
    }
    /**
     * ba�l�klar�n� kullanarak yazar par�alar grubunu kullan�r
     * @return
     * @throws Exception 
     */
    protected String yeniPar�aMakineMatrisiYaz2() throws Exception{
    	String yaz� = "";
    	BoolDizi yeniMatris = yeniPar�aMakineMatrisiYap2();
    	yaz� += yeniMatrisPar�alar�Yaz(yeniMatris);
    	yaz� += yeniMatrisSat�rlar�Yaz( yeniMatris);
    	return yaz�;    
    }
	private String yeniMatrisSat�rlar�Yaz( BoolDizi yeniMatris) throws Exception {
		String yaz� = "";
		for(int sat�rNo=0;sat�rNo<yeniMatris.sat�rSay();sat�rNo++){
    		yaz� += yeniMatrisSat�rYaz(sat�rNo, yeniMatris);
    	}
		return yaz�;
	}
	private String yeniMatrisPar�alar�Yaz(BoolDizi yeniMatris)throws Exception {
		String yaz� = "\t";
		for(int sutunNo = 0;sutunNo<yeniMatris.sutunSay();sutunNo++){
    		int yer = par�aGruplar�TekBoyutluEri�im(sutunNo);
    		yaz� += par�a�simleri[yer] + "\t";
    	}
    	yaz� += "\n";
		return yaz�;
	}
	private String yeniMatrisSat�rYaz( int sat�rNo,BoolDizi yeniMatris)throws Exception {
		String yaz� = "";
		int makine = makineGruplar�TekBoyutluEri�im(sat�rNo);
		yaz� += makine�simleri[makine] + "\t";
		for(int sutunNo=0;sutunNo<yeniMatris.sutunSay();sutunNo++){
			yaz� += boolDe�erYaz(yeniMatris, sat�rNo, sutunNo);
		}
		yaz� += "\n";
		return yaz�;
	}
	private String boolDe�erYaz(BoolDizi yeniMatris, int makine,int par�a) throws Exception {
		String yaz� = "";
		if(yeniMatris.eri�(makine, par�a)){
			yaz� += "1\t";
		}
		else{
			yaz� += "\t";
		}
		return yaz�;
	}
    
	// }} yeni par�a makine matrisi yapma
    // {{ k�meleme performans �l��m� i�in kullan�lan y�ntemler
	
	// bir k�menin elemanlar� ile merkezi aras�ndaki uzakl��� bulma
    private double ortalamaK�me��iUzakl�klarHesapla() throws Exception{
    	double ortalamaK�meler��iUzakl�k = 0;
    	int k�meNo,k�meSay�s� = makineGruplar�.size() ;
    	for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
    		ortalamaK�meler��iUzakl�k += k�meninElemanlar�naUzakl���(k�meNo);
    	}
    	ortalamaK�meler��iUzakl�k /= k�meSay�s�;
    	return ortalamaK�meler��iUzakl�k;
    }
    /**
     * bu i�lemin kullan�labilmesi i�in k�meler dolu olmal�d�r
     * @param k�meNo
     * @return
     * @throws Exception 
     */
    private float k�meninElemanlar�naUzakl���(int k�meNo) throws Exception{
    	float uzakl�k = 0;
    	int elemanNo;
    	ArrayList<Integer> k�me = makineGruplar�.get(k�meNo);
    	int elemanSay�s� = k�me.size();
    	for(elemanNo=0;elemanNo<elemanSay�s�;elemanNo++){
    		uzakl�k += uzakl�kHesapla(k�meNo, elemanNo, k�me);
    	}
    	return uzakl�k;
    }
	private float uzakl�kHesapla(int k�meNo,  int elemanNo,ArrayList<Integer> k�me) throws Exception {
		float uzakl�k = 0;
		int makineNo = k�me.get(elemanNo);
		BoolVekt�r makineVekt�r� = par�aMakine.sat�r(makineNo);
		DoubleVekt�r k�meMerkezi = k�meMerkezleri.dizi[k�meNo];
		uzakl�k += makineVekt�r�.�klidUzakl���Hesapla(k�meMerkezi);
		return uzakl�k;
	}
	private void k�meMerkezleriniBul() throws Exception{
		k�meMerkezleri = new DoubleDizi(makineGruplar�.size());
		for(int k�meNo=0;k�meNo<k�meMerkezleri.sat�rsay();k�meNo++){
			k�meMerkezleri.dizi[k�meNo] = k�meMerkeziHesapla(k�meNo);
		}
	}
	private DoubleVekt�r k�meMerkeziHesapla(int k�meNo) throws Exception{
		int sutunNo;
		DoubleVekt�r merkez = new DoubleVekt�r(makineGruplar�.get(k�meNo).size());
		for(sutunNo=0;sutunNo<merkez.boy();sutunNo++){
			double toplam = merkezinBirEleman�n�Hesapla(k�meNo, sutunNo);
			merkez.de�i�(sutunNo, toplam/merkez.boy());
		}
		return merkez;
	}
	private double merkezinBirEleman�n�Hesapla(int k�meNo, int sutunNo)throws Exception {
		ArrayList<Integer> k�me = makineGruplar�.get(k�meNo);
		int k�medekiElemanSay�s� = k�me.size();
		return merkezEleman�Hesapla(sutunNo, k�me, k�medekiElemanSay�s�);
	}
	private double merkezEleman�Hesapla(int sutunNo, ArrayList<Integer> k�me,int k�medekiElemanSay�s� ) throws Exception {
		double toplam = 0;
		for(int k�meEleman� = 0;k�meEleman�<k�medekiElemanSay�s�;k�meEleman�++){
			toplam += toplam�Art�r(sutunNo, k�me,  k�meEleman�);
		}
		return toplam;
	}
	private double toplam�Art�r(int sutunNo, ArrayList<Integer> k�me, int k�meEleman�) throws Exception {
		double toplam = 0;
		int sat�rNo = k�me.get(k�meEleman�);
		if(par�aMakine.eri�(sat�rNo, sutunNo)){
			toplam ++;
		}
		return toplam;
	}
	
	// }} k�meleme performans �l��m� i�in kullan�lan y�ntemler

	// {{ par�a gruplar� 
	private int par�an�nK�medeKa�Kez��lendi�iniBul(int par�a,int grup) throws Exception{
		int i�lenmeSay�s� = 0;
		ArrayList<Integer> k�me = makineGruplar�.get(grup);
		for(int elemanNo = 0;elemanNo<k�me.size();elemanNo++){
			if(par�aMakine.eri�(k�me.get(elemanNo), par�a)){
				i�lenmeSay�s�++;
			}
		}
		return i�lenmeSay�s�;
	}
	private int par�an�nEn�ok��lendi�iK�meyiBul(int par�a) throws Exception{
		if(makineGruplar�.size() == 1){
			return 0;
		}
		else{
			return en�ok��lenenK�meyiBul(par�a);
		}
	}
	private int en�ok��lenenK�meyiBul(int par�a) throws Exception {
		int en�ok��lenenK�me = 0;
		int en�ok��lenme = par�an�nK�medeKa�Kez��lendi�iniBul(par�a, 0);
		for(int k�meNo = 0;k�meNo< makineGruplar�.size();k�meNo++){
			int yeni��lenmeSay�s� = par�an�nK�medeKa�Kez��lendi�iniBul(par�a, k�meNo);
			if(yeni��lenmeSay�s� > en�ok��lenme ){
				en�ok��lenme = yeni��lenmeSay�s�;
				en�ok��lenenK�me = k�meNo;
			}
		}	
		return en�ok��lenenK�me;
	}
    private void par�ay�En�ok��lenenK�meyeAta(int par�aNo) throws Exception{
    	int atanacakK�me = par�an�nEn�ok��lendi�iK�meyiBul(par�aNo);
    	ArrayList<Integer> par�aK�mesi = par�aGruplar�.get(atanacakK�me);
    	par�aK�mesi.add(par�aNo);
    }
    /**
     * makine grubu say�s� kadar par�a grubu olu�turur
     */
    private void par�aGruplar�n�Bo�Doldur(){
    	for(int grup = 0;grup < makineGruplar�.size();grup++){
    		ArrayList<Integer> yeniK�me = new ArrayList<Integer>();
    		par�aGruplar�.add(yeniK�me);
    	}
    }

    /**
     * ilk �nce k�meleri bo� doldurur sonra da gerekli par�alar� atar
     * @throws Exception 
     */
    private void par�aGruplar�n�Olu�tur() throws Exception{
    	for(int par�aNo = 0;par�aNo<par�aSay�s�();par�aNo++){
    		par�ay�En�ok��lenenK�meyeAta(par�aNo);
    	}
    }
    // }} par�a gruplar
    
    
    // {{ dendogram �izdirme
       public void dendogram�iz(){
    	   Dendogram �izelge = new Dendogram(makineSay�s�(), 500, 500, 10, gruplananlar, makineGruplar�);
       }
    
    // }} dendogram �izdirme
}
