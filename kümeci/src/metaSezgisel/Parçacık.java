package metaSezgisel;
import veriYap�lar�.*;

import java.util.ArrayList;

public class Par�ac�k {

	DoubleDizi k�meMerkezleri;
	float toplamUzakl�k;
	ArrayList<ArrayList<Integer>> k�meler; 
	
	float ortalamaK�meler��iUzakl�k = 0;
	double ortalamaK�melerAras�Uzakl�k = 0;
	
	DoubleDizi h�z ;
	double ama�De�eri;  // b�y�k bir �ey olmas� istenir
    boolean ama�De�eriVar = false;
    
	// en iyileri ak�lda tutma
	double kendiEn�yiAma�De�eri;
	DoubleDizi kendiEn�yiK�meMerkezleri ;
	
	protected double s�r�yeG�ven=6;
	protected double kendineG�ven=5;
	protected double eylemsizlik=0.5;
	protected double ilkH�z = 0.00003;
	
	private int k�meSay�s�;
	private int par�aSay�s�;
	private BoolDizi par�aMakine;
	
	// bu kullan�lcak
    public Par�ac�k(int k�meSay�s�,BoolDizi par�aMakine) throws Exception {
        this.k�meSay�s� = k�meSay�s�;
        this.par�aSay�s� = par�aMakine.sutunSay();
        this.par�aMakine = par�aMakine;
    	h�z = new DoubleDizi(k�meSay�s�, par�aSay�s�, ilkH�z);
        k�meleriBo�Doldur();
        k�meMerkezleriniRassalDoldur();
        ilkK�mele();
        kendiEn�yiAma�De�eri = ama�De�eriHesapla();
        h�z = new DoubleDizi(k�meSay�s�, par�aSay�s�, ilkH�z);
	}
    public void g�venParameterileriniBelirle(double s�r�yeG�ven, double kendineG�ven , double eylemsizlik ){
    	this.s�r�yeG�ven = s�r�yeG�ven;
    	this.kendineG�ven = kendineG�ven;
    	this.eylemsizlik = eylemsizlik;
    }
    public void ilkH�zBelirle(double ilkH�z) throws Exception{
    	ilkH�zKontrol(ilkH�z);
    	this.ilkH�z = ilkH�z;
    }
	private void ilkH�zKontrol(double ilkH�z) throws Exception {
		if(ilkH�z < 0 ){
    		throw new Exception("ilk h�z s�f�rdan k���k olamaz girlen ilk h�z :" + ilkH�z + "\n");
    	}
	}

    
	private void k�meleriBo�Doldur() {
		k�meler = new ArrayList<ArrayList<Integer>>();
		int k�meNo;
		for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
			ArrayList<Integer> yeniK�me = new ArrayList<Integer>();
			k�meler.add(yeniK�me);
		}
	}
    private void k�meMerkezleriniRassalDoldur() throws Exception{
    	k�meMerkezleri = new DoubleDizi(k�meSay�s�, par�aSay�s�);
    	k�meMerkezleri.rassalDiziDoldur();
    	kendiEn�yiK�meMerkezleri = k�meMerkezleri.kopya();
    }
	private int makineyeEn�okBenzeyenK�meyiBul(BoolVekt�r makineSat�r�) throws Exception{
		gelenMakineSat�r�n�KontrolEt(makineSat�r�);
		double enK���kUzakl�k = k�meMerkezleri.dizi[0].�klidUzakl���Hesapla(makineSat�r�);
		int enYak�nK�me = 0;
		int k�meNo;
		for(k�meNo=1;k�meNo<k�meMerkezleri.sat�rsay();k�meNo++){
			double yeniUzakl�k = k�meMerkezleri.dizi[k�meNo].�klidUzakl���Hesapla(makineSat�r�);
			if(yeniUzakl�k < enK���kUzakl�k){
				enK���kUzakl�k = yeniUzakl�k;
				enYak�nK�me = k�meNo;
			}
		}
		return enYak�nK�me;
	}
	private void gelenMakineSat�r�n�KontrolEt(BoolVekt�r makineSat�r�)throws Exception {
		if(makineSat�r�.boy() != k�meMerkezleri.sutunsay()){
			throw new Exception("makine sat�r� ile par�ac�k aras�nda uyu�mazl�k var\nmakine sat�r� :" + makineSat�r�.yaz() + 
					"\npar�ac�k sutun say�s� : " + k�meMerkezleri.sutunsay() + "\n" + "makinesat�r� boy : " + makineSat�r�.boy() + "\n");
		}
	}
    private int makineyeEn�okBenzeyenK�meyiBul(int makineNo) throws Exception{
    	BoolVekt�r makineSat�r� = par�aMakine.matris[makineNo];
    	return makineyeEn�okBenzeyenK�meyiBul(makineSat�r�);
    }
	/**
	 * herkes en �ok benzedi�i k�meye gidecek
	 * @throws Exception
	 */
	private void ilkK�mele () throws Exception{
		int makineNo;
		for(makineNo=0;makineNo<par�aMakine.sat�rSay();makineNo++){
			int makineninGidece�iK�me = makineyeEn�okBenzeyenK�meyiBul(makineNo);
			ArrayList<Integer> k�me = k�meler.get(makineninGidece�iK�me);
			k�me.add(makineNo);
		}
	}
    // ama� fonksyon de�erini hesaplama y�ntemleri
	
	// bir k�menin elemanlar� ile merkezi aras�ndaki uzakl��� bulma
    private void ortalamaK�me��iUzakl�klarHesapla() throws Exception{
    	ortalamaK�meler��iUzakl�k = 0;
    	int k�meNo;
    	for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
    		ortalamaK�meler��iUzakl�k += k�meninElemanlar�naUzakl���(k�meNo);
    	}
    	ortalamaK�meler��iUzakl�k /= k�meSay�s�;
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
    	ArrayList<Integer> k�me = k�meler.get(k�meNo);
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

	// t�m k�meler i�in uzakl�k
	private void ortalamaK�melerAras�Uzakl�kBul() throws Exception{
		ortalamaK�melerAras�Uzakl�k = 0;
		int k�meNo;
		for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
			ortalamaK�melerAras�Uzakl�k += k�meninDi�erK�melerleUzakl���n�Bul(k�meNo);
		}
		ortalamaK�melerAras�Uzakl�k /= k�meSay�s�;
	}
    private double ikiK�meMerkeziAras�Uzakl�kBul(int k�me1,int k�me2) throws Exception{
    	DoubleVekt�r merkez1 = k�meMerkezleri.dizi[k�me1];
    	DoubleVekt�r merkez2 = k�meMerkezleri.dizi[k�me2];
    	return merkez1.�klidUzakl���Hesapla(merkez2);
    }
    /**
     * k�meNo = x olursa 'x' den sonras� i�in k�menin di�er k�melere olan uzakl���n� bulur
     * @param k�meNo
     * @return
     * @throws Exception 
     */
    private double k�meninDi�erK�melerleUzakl���n�Bul(int k�me) throws Exception{
    	double uzakl�k = 0;
    	int k�meNo;
    	for(k�meNo=k�me+1;k�meNo<k�meSay�s�;k�meNo++){
    		uzakl�k += ikiK�meMerkeziAras�Uzakl�kBul(k�me, k�meNo);
    	}
    	return uzakl�k;
    }
    
    public double ama�De�eriHesapla() throws Exception{
    	ortalamaK�me��iUzakl�klarHesapla();
    	ortalamaK�melerAras�Uzakl�kBul();
    	ama�De�eri = ortalamaK�meler��iUzakl�k - ortalamaK�melerAras�Uzakl�k;
    	//ama�De�eri -= (k�meSay�s� - bo�K�meSay�s�Bul())/k�meSay�s�;
    	if(ama�De�eriVar == false){
            ama�De�eriVar = true;
            return ama�De�eri;
    	}
    	else {
    		ama�De�eriG�ncelle();
    		return ama�De�eri;
    	}
    }
    public int bo�K�meSay�s�Bul(){
    	int k�meNo;
    	int bo�K�mesay�s� = 0;
    	for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
    		if(k�meler.get(k�meNo).size() == 0){
    			bo�K�mesay�s� ++;
    		}
    	}
    	return bo�K�mesay�s�;
    }
	private void ama�De�eriG�ncelle() throws Exception {
        if(ama�De�eri > kendiEn�yiAma�De�eri){
        	kendiEn�yiAma�De�eri = ama�De�eri;
        	kendiEn�yiK�meMerkezleri = k�meMerkezleri.kopya();
        }
	}
    
	
	// ama� fonksyon de�erini hesaplama y�ntemleri
	public void uzakl�klar�S�f�rla(){
		ortalamaK�melerAras�Uzakl�k = 0;
		ortalamaK�meler��iUzakl�k = 0;
	}
	
    private void h�zG�ncelle(Par�ac�k s�r��nderi) throws Exception{
    	h�z.�arp(eylemsizlik);
    	DoubleDizi kendiEn�yisineUzakl�k = kendineG�venme();
    	DoubleDizi s�r�En�yisineUzakl�k = s�r�yeG�venme(s�r��nderi);
    	h�z.topla(s�r�En�yisineUzakl�k);
    	h�z.topla(kendiEn�yisineUzakl�k);
    }
	private DoubleDizi s�r�yeG�venme(Par�ac�k s�r��nderi) throws Exception {
		DoubleDizi en�yiMerkezler = s�r��nderi.k�meMerkezleri;
		DoubleDizi s�r�En�yisineUzakl�k = en�yiMerkezler.��kar(k�meMerkezleri);
    	s�r�En�yisineUzakl�k.�arp(s�r�yeG�ven);
		return s�r�En�yisineUzakl�k;
	}
	private DoubleDizi kendineG�venme() throws Exception {
		DoubleDizi kendiEn�yisineUzakl�k = kendiEn�yiK�meMerkezleri.��kar(k�meMerkezleri);
    	kendiEn�yisineUzakl�k.�arp(kendineG�ven);
		return kendiEn�yisineUzakl�k;
	}
    public void konumuG�ncelle(Par�ac�k s�r��nderi) throws Exception{
    	h�zG�ncelle(s�r��nderi);
    	k�meMerkezleri.topla(h�z);
    	ama�De�eriHesapla();
    }

    public String yaz(){
    	String yaz� = "";
    	yaz� += "h�z : \n" + h�z.yaz("\t") + "\n\n"; 
    	yaz� += "ama� de�eri : " + ama�De�eri + "\n\n";
    	yaz� += "en iyi ama� de�eri : " + kendiEn�yiAma�De�eri + "\n\n";
    	yaz� += "k�me merkezlerine uzakl�k : " + ortalamaK�meler��iUzakl�k + "\n\n";
    	yaz� += "k�meler aras� uzakl�k : " + ortalamaK�melerAras�Uzakl�k +"\n\n";
    	yaz� += "k�me merkezleri : \n" + k�meMerkezleri.yaz("\t")+ "\n\n\n"; 
    	yaz� += "en iyi k�me merkezleri : \n" + kendiEn�yiK�meMerkezleri.yaz("\t") + "\n\n\n";
    	yaz� += k�meleriYaz();
    	return yaz�;
    }
	private String k�meleriYaz() {
		String yaz� = "";
		int k�meNo;
    	for(k�meNo=0;k�meNo<k�meler.size();k�meNo++){
    		ArrayList<Integer> k�me = k�meler.get(k�meNo);
    		yaz� += k�me.toString() + "\n";
    	}
		return yaz�;
	}

    public void k�meleriS�f�rla() throws Exception{
    	k�meler = null;
    	k�meleriBo�Doldur();
    	ilkK�mele();
    }
}
