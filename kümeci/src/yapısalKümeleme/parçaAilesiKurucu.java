package yap�salK�meleme;
import veriYap�lar�.*;
import java.util.ArrayList;

public class par�aAilesiKurucu {

	ArrayList<ArrayList<Par�a>> par�aGruplar� = new ArrayList<ArrayList<Par�a>>();
	DoubleListDizi uzakl�k = new DoubleListDizi();
	
	int [] sat�rSutun = new int [2]; // sat�r , sutun
	
	public double enD���kUzakl�k = -1;
    public int enAzAileSay�s� = -1;
    public int enFazlaAileB�y�kl��� = -1;
    
    Boolean uzakl�kKo�ulu ;
    Boolean aileB�y�kl���Ko�ulu ;
    Boolean aileSay�s�Ko�ulu;
    Boolean olurlu��z�m = true;
    
    double a�ama��inEnK���kUzakl�k;
    /**
     * par�a1:1,2,3,34 
     * par�a2:3,12,3,4
     * par�a3:4,4,2,13
     * @param yaz�
     * @throws Exception
     */
    public par�aAilesiKurucu(String  yaz�) throws Exception {
		String [] sat�rlar = yaz�.split("\\n");
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rlar.length;sat�rNo++){
			Par�a yeniPar�a = new Par�a(sat�rlar[sat�rNo]);
			ArrayList<Par�a> yeniSat�r = new ArrayList<Par�a>();
			yeniSat�r.add(yeniPar�a);
			par�aGruplar�.add(yeniSat�r);
		}
	}
	/**
	 * sadece ilk a�ama i�in yap�l� matris olu�turulduktan sonra zaten hesaplama i�i yok
	 * <br>iki grup benzerli�i i�in k���k olan se�ilecek hesaplama yok
	 * @throws Exception
	 */
    public void par�aUzakl�kMatrisiniHesapla() throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<par�aGruplar�.size();sat�rNo++){
    		yeniSat�rEkle(sat�rNo);
    	}
    }
	private void yeniSat�rEkle(int sat�rNo) throws Exception {
		DoubleListVekt�r yeniSat�r = new DoubleListVekt�r();
		Par�a par�a1 = par�aGruplar�.get(sat�rNo).get(0); // ilk ba�ta zaten her grupta bir par�a var
		yeniSat�rHesapla(yeniSat�r, par�a1, sat�rNo);
		uzakl�k.sat�rEkle(yeniSat�r);
	}
	private void yeniSat�rHesapla(DoubleListVekt�r yeniSat�r, Par�a par�a1,int sat�rNo) throws Exception {
		int sutunNo;
		for(sutunNo=0;sutunNo<sat�rNo+1;sutunNo++){
			yeniUzakl�kHesapla(sutunNo, yeniSat�r, par�a1);
		}
	}
	private void yeniUzakl�kHesapla(int sutunNo, DoubleListVekt�r yeniSat�r, Par�a par�a1) throws Exception {
		Par�a par�a2 = par�aGruplar�.get(sutunNo).get(0); // ilk ba�ta her grupta bir par�a var
		double yeniBenzerlik = par�a1.minkowskiUzakl�kBul(par�a2);
		yeniSat�r.ekle(yeniBenzerlik);
	}

	private double grupUzakl�kEri�(int grup1, int grup2){
		if(grup1 > grup2){
			return uzakl�k.eri�(grup1, grup2);
		}
		else{
			return uzakl�k.eri�(grup2, grup1);
		}
	}
	// gruplar aras� uzakl�k hesaplama
	/**
	 * grupNo1 grubu ile grupNo2 grubunun par�aNo par�as� aras�ndaki uzakl��� verir
	 * @param grupNo1
	 * @param grupNo2
	 * @param par�aNo
	 * @return
	 * @throws Exception
	 */
	public double par�aGrupUzakl�kHesapla(int grupNo1, int grupNo2, int par�aNo) throws Exception{
		ArrayList<Par�a> par�a2ninGrubu = par�aGruplar�.get(grupNo2);
		Par�a par�a = par�a2ninGrubu.get(par�aNo);
		ArrayList<Par�a> birinciPar�aGrubu = par�aGruplar�.get(grupNo1);
		return par�aGrupUzakl�kHesapla(birinciPar�aGrubu, par�a);
	}
	public double par�aGrupUzakl�kHesapla(int grupNo, Par�a par�a) throws Exception{
		ArrayList<Par�a> grup = par�aGruplar�.get(grupNo);
		return par�aGrupUzakl�kHesapla(grup, par�a);
	}
	public double par�aGrupUzakl�kHesapla(ArrayList<Par�a> grup, Par�a par�a) throws Exception{
        double enK���kUzakl�k = grup.get(0).minkowskiUzakl�kBul(par�a); // �imdilik b�yle kabul ediyoruz
		int par�aNo;
		for(par�aNo=1;par�aNo<grup.size();par�aNo++){
			Par�a grupEleman� = grup.get(par�aNo);
			enK���kUzakl�k = yeniUzakl�kAra(par�a, enK���kUzakl�k, grupEleman�);
		}
		return enK���kUzakl�k;
	}
	private double yeniUzakl�kAra(Par�a par�a, double enK���kUzakl�k, Par�a grupEleman�) throws Exception {
		double yeniUzakl�k = grupEleman�.minkowskiUzakl�kBul(par�a);
		if(yeniUzakl�k < enK���kUzakl�k ){
			enK���kUzakl�k = yeniUzakl�k;
		}
		return enK���kUzakl�k;
	}
    public double grupUzakl�kHesapla(int grup1, int grup2) throws Exception{
    	if(grup1 == grup2){
    		return 0.0;
    	}
    	double enK���kUzakl�k = par�aGrupUzakl�kHesapla(grup1,grup2,0); // ilk grup ile ikinci grubun 0. par�as� aras�ndaki uzakl�k
    	int grup1No;
    	for(grup1No=1;grup1No<par�aGruplar�.get(grup1).size();grup1No++){
    		double yeniUzakl�k = par�aGrupUzakl�kHesapla(grup1, grup2, grup1No);
    		if(yeniUzakl�k < enK���kUzakl�k){
    			enK���kUzakl�k = yeniUzakl�k;
    		}
    	}
    	return enK���kUzakl�k;
    }
	
    /**
     * ge�mi�teki verilerden yaralanarak yeni olu�acak
	 * <br>grubun haz�rlanmas� i�n gerekli uzakl�k bilgisini bulur
	 */
    private double grupUzakl�kEri�(int grupOlacak1, int grupOlacak2, int olanGrup){
    	double uzakl�k1 = grupUzakl�kEri�(grupOlacak2, olanGrup);
    	double uzakl�k2 = grupUzakl�kEri�(grupOlacak1, olanGrup);
    	return k�����D�n(uzakl�k1, uzakl�k2);
    }
	private double k�����D�n(double uzakl�k1, double uzakl�k2) {
		if(uzakl�k1 < uzakl�k2){
    		return uzakl�k1;
    	}
    	else{
    		return uzakl�k2;
    	}
	}
    
	
	// gruplar aras� uzakl�k hesaplama
    // en yak�n gruplar� bulma
    public void enYak�nGruplar�Bul() throws Exception{
    	olurluEnK���kUzakl�kBul();
    	if(olurlu��z�m == true){ // olurlu ��z�m bulundu ise taramaya decam edilecek
        	enK���kUzakl���nYeriniBul();
    	}
    }
	private void enK���kUzakl���nYeriniBul() throws Exception {
		int grupNo1;
		for(grupNo1=0;grupNo1<par�aGruplar�.size();grupNo1++){
			grup1��inEnK���kUzakl�kBul(grupNo1);
		}
	}
    private void grup1��inEnK���kUzakl�kBul(int grupNo1) throws Exception{
    	int grupNo2;
    	for(grupNo2=grupNo1+1;grupNo2<par�aGruplar�.size();grupNo2++){
			olurluEnK���kUzakl�kBul(grupNo1, grupNo2);
		}
    }
	private void olurluEnK���kUzakl�kBul(int grupNo1, int grupNo2) throws Exception {
		a�amaBa��Ko�ullar�n�lkDe�erleriniVer(); // t�m de�i�kenlere true verir, ��nk� k�s�t yoksa ko�ul true olur
		t�mKo�ullaraBak(grupNo1, grupNo2); // olurlu ��z�m de�i�kenini hesaplar
		if(olurlu��z�m == true){ // olurlu ��z�m bulundu ise en yak�n uzakl�k bulunmaya �al���l�r
			yeniGrubUzakl���K�yasla(grupNo1, grupNo2);	
		}
	}
	private void yeniGrubUzakl���K�yasla(int grupNo1, int grupNo2) throws Exception {
		double yeniUzakl�k = grupUzakl�kHesapla(grupNo1, grupNo2); // olurlu �ift bulunmal�
		if(yeniUzakl�k < a�ama��inEnK���kUzakl�k){
			a�ama��inEnK���kUzakl�k = yeniUzakl�k;
			// k���k olan sat�r olacak grupNo1 daha k���k olaca��ndan
			sat�rSutun[0] = grupNo1;
			sat�rSutun[1] = grupNo2;
		}
	}
   
	// en yak�n gruplar� bulma
	
	// olurlu uzakl�k bulma
    private void olurluEnK���kUzakl�kBul() throws Exception{
    	int grupNo1;
    	for(grupNo1=0;grupNo1<par�aGruplar�.size();grupNo1++){
    		grup1��inOlurluDurumAra(grupNo1);
    		if(olurlu��z�m == true){ // �stteki d�ng�de bir tane olurlu ��z�m bulunmu� demektir
    			break;
    		}
    	}
    }
	private void grup1��inOlurluDurumAra(int grupNo1) throws Exception {
		int grupNo2;
		for(grupNo2=grupNo1+1;grupNo2<par�aGruplar�.size();grupNo2++){
			a�amaBa��Ko�ullar�n�lkDe�erleriniVer();
			t�mKo�ullaraBak(grupNo1, grupNo2);
			if( olurlu��z�m  == true){ // bir tane olurlu ��z�m bulundu demektir
				sat�rSutun [0] = grupNo1;
				sat�rSutun [1] = grupNo2;
				a�ama��inEnK���kUzakl�k = grupUzakl�kHesapla(grupNo1, grupNo2);
				break;
			}
		}
	}
	private void t�mKo�ullaraBak(int grupNo1, int grupNo2) throws Exception {
		if(grupNo1 == grupNo2){ // ayn� grup ise zaten uzakl�k s�f�rd�r ama bu en k���k uzakl��a aday olamaz
			olurlu��z�m = false;
		}
		else{
			uzakl�kKo�ulu(grupNo1, grupNo2);
			aileB�y�kl���Ko�ulu(grupNo1, grupNo2);
			aileSay�s�Ko�ulu();
			olurlu��z�m =  uzakl�kKo�ulu && aileB�y�kl���Ko�ulu && aileSay�s�Ko�ulu;
		}
	}
	private void aileSay�s�Ko�ulu() {
		if(enAzAileSay�s� != -1){
			aileSay�s�naBak();
		}
	}
	private void aileB�y�kl���Ko�ulu(int grupNo1, int grupNo2) {
		if(enFazlaAileB�y�kl��� != -1){
			aileB�y�kl���Ko�ulunaBak(grupNo1, grupNo2);
		}
	}
	private void uzakl�kKo�ulu(int grupNo1, int grupNo2) throws Exception {
		if(enD���kUzakl�k != -1){
			olurluUzakl�kBak(grupNo1, grupNo2);
		}
	}
	
    private void olurluUzakl�kBak(int grupNo1, int grupNo2) throws Exception {
		double yeniUzakl�k = grupUzakl�kHesapla(grupNo1, grupNo2);
		if(yeniUzakl�k >= enD���kUzakl�k){
			sat�rSutun [0] = grupNo1;
			sat�rSutun [1] = grupNo2;
			uzakl�kKo�ulu =  true;
		}
		else{
			uzakl�kKo�ulu =  false;
		}
	}
	private void aileB�y�kl���Ko�ulunaBak(int grupNo1, int grupNo2){
		int aileB�y�kl���1 = par�aGruplar�.get(grupNo1).size();
		int aileB�y�kl���2 = par�aGruplar�.get(grupNo2).size();
		if(aileB�y�kl���1 + aileB�y�kl���2 <= enFazlaAileB�y�kl���){
			aileB�y�kl���Ko�ulu = true;
		}
		else{
			aileB�y�kl���Ko�ulu = false;
		}
	}
    private void aileSay�s�naBak(){
    	int toplamAileSay�s� = par�aGruplar�.size();
    	if(toplamAileSay�s� -1 < enAzAileSay�s�){
    		aileSay�s�Ko�ulu = false; 
    	}
    	else{
    		aileSay�s�Ko�ulu = true;
    	}
    }
    /**
     * t�m ko�ullar ge�ti kabule edecek
     * <br>��nk� bu ko�ulun olup olmad���n� bimiyoruz
     * <br>e�er ko�ul konmam��sa zaten bir sonraki a�amaya ge�meyi engellemez
     */
    private void a�amaBa��Ko�ullar�n�lkDe�erleriniVer(){
    	uzakl�kKo�ulu = true;
        aileB�y�kl���Ko�ulu = true;
        aileSay�s�Ko�ulu = true;
    }

    // olurlu uzakl�k bulma
    
    private DoubleListVekt�r uzakl��aYeniSat�rHesapla(){
    	DoubleListVekt�r yeniSat�r = new DoubleListVekt�r();
    	// yeni gruplananlar i�in yeni sat�r olu�turulacak
    	int par�aNo;
    	for(par�aNo=0;par�aNo<uzakl�k.sat�rSay();par�aNo++){
    		if(par�aNo != sat�rSutun[0] && par�aNo != sat�rSutun[1]){
    			double yeniUzakl�k = grupUzakl�kEri�(sat�rSutun[0],sat�rSutun[1],par�aNo);
    			yeniSat�r.ekle(yeniUzakl�k);
    		}
    	}
    	yeniSat�r.ekle(0);
    	return yeniSat�r;
    }
    private void sat�rSutunSil(){
    	if(sat�rSutun[0] < sat�rSutun[1]){
    		uzakl�k.sat�rSutunSil(sat�rSutun[0], sat�rSutun[0]);
        	uzakl�k.sat�rSutunSil(sat�rSutun[1]-1, sat�rSutun[1]-1);
    	}
    	else{
    		uzakl�k.sat�rSutunSil(sat�rSutun[1], sat�rSutun[1]);
        	uzakl�k.sat�rSutunSil(sat�rSutun[0]-1, sat�rSutun[0]-1);
    	}
    }
    
    private void yeniGrupOlu�tur(int grupNo1, int grupNo2) throws Exception{
    	if(grupNo1 == grupNo2){
    		throw new Exception("bunlar ayn� grup " + grupNo1);
    	}
    	ArrayList<Par�a> grup1 = par�aGruplar�.get(grupNo1);
    	ArrayList<Par�a> grup2 = par�aGruplar�.get(grupNo2);
    	yeniGrubuEkle(grup1, grup2);
    	eskiGruplar�Sil(grupNo1, grupNo2);
    }
	private void eskiGruplar�Sil(int grupNo1, int grupNo2) {
		if(grupNo1 < grupNo2){
			par�aGruplar�.remove(grupNo1); // �nce k���k olan
	    	par�aGruplar�.remove(grupNo2-1); // ��nk� listede bir geri gitti her �ey
		}
		else{
			par�aGruplar�.remove(grupNo2); // �nce k���k olan
	    	par�aGruplar�.remove(grupNo1-1); // ��nk� listede bir geri gitti her �ey
		}
	}
	private void yeniGrubuEkle(ArrayList<Par�a> grup1, ArrayList<Par�a> grup2) {
		ArrayList<Par�a> yeniGrup = new ArrayList<Par�a>();
    	yeniGrup.addAll(grup1);
    	yeniGrup.addAll(grup2);
    	par�aGruplar�.add(yeniGrup);
	}

	public void ilerle() throws Exception{
		enYak�nGruplar�Bul();
		yeniGrupOlu�tur(sat�rSutun[0], sat�rSutun[1]);
		DoubleListVekt�r yeniSat�r = uzakl��aYeniSat�rHesapla();
		sat�rSutunSil();
		uzakl�k.sat�rEkle(yeniSat�r);
	}
	public String ��z() throws Exception{
		par�aUzakl�kMatrisiniHesapla();
		String ��z�m ="";
		��z�m += "a�ama 0:\n\n" + yaz() + "\n\n";
		int a�amaNo;
		for(a�amaNo=0;;a�amaNo++){
			olurluEnK���kUzakl�kBul(); // belki de t�m �artlar� sa�layan olurlu bir ��z�m bulamaz o zaman olurlu��z�m = false olur
			if(olurlu��z�m && uzakl�k.sat�rSay() > 2){ // olurlu bir ��z�m varsa bu iyile�tirilebilir ya da cevap olurlu ��z�m olabilir
				ilerle();
				��z�m += "a�ama : "+ a�amaNo + "\n\n" + yaz() + "\n\n"; 
			}
			else{
				break;
			}
		}
		return ��z�m;
	}
    public String yaz(){
    	String yaz� = "";
    	yaz� = par�aGruplar�n�Yaz(yaz�);
    	yaz� += "\n\nuzakl�k matrisi : \n\n" + uzakl�k.yaz('\t') + "\n\n";
    	yaz� += "en yak�n olanlar : " + sat�rSutun[0] + " , " + sat�rSutun[1] + "\n";
    	yaz� = k�s�tlar�Yaz(yaz�);
        return yaz�;
    }
	private String k�s�tlar�Yaz(String yaz�) {
		yaz� = aileSay�s�Ko�ulunuYaz(yaz�);
    	yaz� = uzakl�kK�s�t�n�Yaz(yaz�);
    	yaz� = aileB�y�kl���K�s�t�n�Yaz(yaz�);
		return yaz�;
	}
	private String aileB�y�kl���K�s�t�n�Yaz(String yaz�) {
		if(enFazlaAileB�y�kl��� == -1){
    		yaz� += "aile b�y�kl��� k�s�tlanmam��\n";
    	}
    	else{
    		yaz� += "aile b�y�kl��� k�s�t� : " + aileB�y�kl���Ko�ulu + "\n";
    	}
		return yaz�;
	}
	private String uzakl�kK�s�t�n�Yaz(String yaz�) {
		if(enD���kUzakl�k == -1){
    		yaz� += "uzakl�k k�s�tlanmam�� \n";
    	}
    	else{
    		yaz� += "uzakl�k k�s�t� : " + uzakl�kKo�ulu + "\n";
    	}
		return yaz�;
	}
	private String aileSay�s�Ko�ulunuYaz(String yaz�) {
		if(enAzAileSay�s� == -1){
    		yaz� += "ailesay�s� k�s�tlanmam�� \n";
    	}
    	else{
    		yaz� += "aileSay�s� k�s�t� : " + enAzAileSay�s� + "\n";
    	}
		return yaz�;
	}
	private String par�aGruplar�n�Yaz(String yaz�) {
		int grupNo,par�aNo;
    	for(grupNo=0;grupNo<par�aGruplar�.size();grupNo++){
    		yaz� += "grup : " + grupNo + "\n";
    		for(par�aNo=0;par�aNo<par�aGruplar�.get(grupNo).size();par�aNo++){
    			yaz� += "par�a " + par�aGruplar�.get(grupNo).get(par�aNo).yaz() + "\n";
    		}
    		yaz� += "------------------\n";
    	}
		return yaz�;
	}

}
