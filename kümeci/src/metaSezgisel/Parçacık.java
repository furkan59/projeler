package metaSezgisel;
import veriYapýlarý.*;

import java.util.ArrayList;

public class Parçacýk {

	DoubleDizi kümeMerkezleri;
	float toplamUzaklýk;
	ArrayList<ArrayList<Integer>> kümeler; 
	
	float ortalamaKümelerÝçiUzaklýk = 0;
	double ortalamaKümelerArasýUzaklýk = 0;
	
	DoubleDizi hýz ;
	double amaçDeðeri;  // büyük bir þey olmasý istenir
    boolean amaçDeðeriVar = false;
    
	// en iyileri akýlda tutma
	double kendiEnÝyiAmaçDeðeri;
	DoubleDizi kendiEnÝyiKümeMerkezleri ;
	
	protected double sürüyeGüven=6;
	protected double kendineGüven=5;
	protected double eylemsizlik=0.5;
	protected double ilkHýz = 0.00003;
	
	private int kümeSayýsý;
	private int parçaSayýsý;
	private BoolDizi parçaMakine;
	
	// bu kullanýlcak
    public Parçacýk(int kümeSayýsý,BoolDizi parçaMakine) throws Exception {
        this.kümeSayýsý = kümeSayýsý;
        this.parçaSayýsý = parçaMakine.sutunSay();
        this.parçaMakine = parçaMakine;
    	hýz = new DoubleDizi(kümeSayýsý, parçaSayýsý, ilkHýz);
        kümeleriBoþDoldur();
        kümeMerkezleriniRassalDoldur();
        ilkKümele();
        kendiEnÝyiAmaçDeðeri = amaçDeðeriHesapla();
        hýz = new DoubleDizi(kümeSayýsý, parçaSayýsý, ilkHýz);
	}
    public void güvenParameterileriniBelirle(double sürüyeGüven, double kendineGüven , double eylemsizlik ){
    	this.sürüyeGüven = sürüyeGüven;
    	this.kendineGüven = kendineGüven;
    	this.eylemsizlik = eylemsizlik;
    }
    public void ilkHýzBelirle(double ilkHýz) throws Exception{
    	ilkHýzKontrol(ilkHýz);
    	this.ilkHýz = ilkHýz;
    }
	private void ilkHýzKontrol(double ilkHýz) throws Exception {
		if(ilkHýz < 0 ){
    		throw new Exception("ilk hýz sýfýrdan küçük olamaz girlen ilk hýz :" + ilkHýz + "\n");
    	}
	}

    
	private void kümeleriBoþDoldur() {
		kümeler = new ArrayList<ArrayList<Integer>>();
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
			ArrayList<Integer> yeniKüme = new ArrayList<Integer>();
			kümeler.add(yeniKüme);
		}
	}
    private void kümeMerkezleriniRassalDoldur() throws Exception{
    	kümeMerkezleri = new DoubleDizi(kümeSayýsý, parçaSayýsý);
    	kümeMerkezleri.rassalDiziDoldur();
    	kendiEnÝyiKümeMerkezleri = kümeMerkezleri.kopya();
    }
	private int makineyeEnÇokBenzeyenKümeyiBul(BoolVektör makineSatýrý) throws Exception{
		gelenMakineSatýrýnýKontrolEt(makineSatýrý);
		double enKüçükUzaklýk = kümeMerkezleri.dizi[0].öklidUzaklýðýHesapla(makineSatýrý);
		int enYakýnKüme = 0;
		int kümeNo;
		for(kümeNo=1;kümeNo<kümeMerkezleri.satýrsay();kümeNo++){
			double yeniUzaklýk = kümeMerkezleri.dizi[kümeNo].öklidUzaklýðýHesapla(makineSatýrý);
			if(yeniUzaklýk < enKüçükUzaklýk){
				enKüçükUzaklýk = yeniUzaklýk;
				enYakýnKüme = kümeNo;
			}
		}
		return enYakýnKüme;
	}
	private void gelenMakineSatýrýnýKontrolEt(BoolVektör makineSatýrý)throws Exception {
		if(makineSatýrý.boy() != kümeMerkezleri.sutunsay()){
			throw new Exception("makine satýrý ile parçacýk arasýnda uyuþmazlýk var\nmakine satýrý :" + makineSatýrý.yaz() + 
					"\nparçacýk sutun sayýsý : " + kümeMerkezleri.sutunsay() + "\n" + "makinesatýrý boy : " + makineSatýrý.boy() + "\n");
		}
	}
    private int makineyeEnÇokBenzeyenKümeyiBul(int makineNo) throws Exception{
    	BoolVektör makineSatýrý = parçaMakine.matris[makineNo];
    	return makineyeEnÇokBenzeyenKümeyiBul(makineSatýrý);
    }
	/**
	 * herkes en çok benzediði kümeye gidecek
	 * @throws Exception
	 */
	private void ilkKümele () throws Exception{
		int makineNo;
		for(makineNo=0;makineNo<parçaMakine.satýrSay();makineNo++){
			int makineninGideceðiKüme = makineyeEnÇokBenzeyenKümeyiBul(makineNo);
			ArrayList<Integer> küme = kümeler.get(makineninGideceðiKüme);
			küme.add(makineNo);
		}
	}
    // amaç fonksyon deðerini hesaplama yöntemleri
	
	// bir kümenin elemanlarý ile merkezi arasýndaki uzaklýðý bulma
    private void ortalamaKümeÝçiUzaklýklarHesapla() throws Exception{
    	ortalamaKümelerÝçiUzaklýk = 0;
    	int kümeNo;
    	for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
    		ortalamaKümelerÝçiUzaklýk += kümeninElemanlarýnaUzaklýðý(kümeNo);
    	}
    	ortalamaKümelerÝçiUzaklýk /= kümeSayýsý;
    }
    /**
     * bu iþlemin kullanýlabilmesi için kümeler dolu olmalýdýr
     * @param kümeNo
     * @return
     * @throws Exception 
     */
    private float kümeninElemanlarýnaUzaklýðý(int kümeNo) throws Exception{
    	float uzaklýk = 0;
    	int elemanNo;
    	ArrayList<Integer> küme = kümeler.get(kümeNo);
    	int elemanSayýsý = küme.size();
    	for(elemanNo=0;elemanNo<elemanSayýsý;elemanNo++){
    		uzaklýk += uzaklýkHesapla(kümeNo, elemanNo, küme);
    	}
    	return uzaklýk;
    }
	private float uzaklýkHesapla(int kümeNo,  int elemanNo,ArrayList<Integer> küme) throws Exception {
		float uzaklýk = 0;
		int makineNo = küme.get(elemanNo);
		BoolVektör makineVektörü = parçaMakine.satýr(makineNo);
		DoubleVektör kümeMerkezi = kümeMerkezleri.dizi[kümeNo];
		uzaklýk += makineVektörü.öklidUzaklýðýHesapla(kümeMerkezi);
		return uzaklýk;
	}

	// tüm kümeler için uzaklýk
	private void ortalamaKümelerArasýUzaklýkBul() throws Exception{
		ortalamaKümelerArasýUzaklýk = 0;
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
			ortalamaKümelerArasýUzaklýk += kümeninDiðerKümelerleUzaklýðýnýBul(kümeNo);
		}
		ortalamaKümelerArasýUzaklýk /= kümeSayýsý;
	}
    private double ikiKümeMerkeziArasýUzaklýkBul(int küme1,int küme2) throws Exception{
    	DoubleVektör merkez1 = kümeMerkezleri.dizi[küme1];
    	DoubleVektör merkez2 = kümeMerkezleri.dizi[küme2];
    	return merkez1.öklidUzaklýðýHesapla(merkez2);
    }
    /**
     * kümeNo = x olursa 'x' den sonrasý için kümenin diðer kümelere olan uzaklýðýný bulur
     * @param kümeNo
     * @return
     * @throws Exception 
     */
    private double kümeninDiðerKümelerleUzaklýðýnýBul(int küme) throws Exception{
    	double uzaklýk = 0;
    	int kümeNo;
    	for(kümeNo=küme+1;kümeNo<kümeSayýsý;kümeNo++){
    		uzaklýk += ikiKümeMerkeziArasýUzaklýkBul(küme, kümeNo);
    	}
    	return uzaklýk;
    }
    
    public double amaçDeðeriHesapla() throws Exception{
    	ortalamaKümeÝçiUzaklýklarHesapla();
    	ortalamaKümelerArasýUzaklýkBul();
    	amaçDeðeri = ortalamaKümelerÝçiUzaklýk - ortalamaKümelerArasýUzaklýk;
    	//amaçDeðeri -= (kümeSayýsý - boþKümeSayýsýBul())/kümeSayýsý;
    	if(amaçDeðeriVar == false){
            amaçDeðeriVar = true;
            return amaçDeðeri;
    	}
    	else {
    		amaçDeðeriGüncelle();
    		return amaçDeðeri;
    	}
    }
    public int boþKümeSayýsýBul(){
    	int kümeNo;
    	int boþKümesayýsý = 0;
    	for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
    		if(kümeler.get(kümeNo).size() == 0){
    			boþKümesayýsý ++;
    		}
    	}
    	return boþKümesayýsý;
    }
	private void amaçDeðeriGüncelle() throws Exception {
        if(amaçDeðeri > kendiEnÝyiAmaçDeðeri){
        	kendiEnÝyiAmaçDeðeri = amaçDeðeri;
        	kendiEnÝyiKümeMerkezleri = kümeMerkezleri.kopya();
        }
	}
    
	
	// amaç fonksyon deðerini hesaplama yöntemleri
	public void uzaklýklarýSýfýrla(){
		ortalamaKümelerArasýUzaklýk = 0;
		ortalamaKümelerÝçiUzaklýk = 0;
	}
	
    private void hýzGüncelle(Parçacýk sürüÖnderi) throws Exception{
    	hýz.çarp(eylemsizlik);
    	DoubleDizi kendiEnÝyisineUzaklýk = kendineGüvenme();
    	DoubleDizi sürüEnÝyisineUzaklýk = sürüyeGüvenme(sürüÖnderi);
    	hýz.topla(sürüEnÝyisineUzaklýk);
    	hýz.topla(kendiEnÝyisineUzaklýk);
    }
	private DoubleDizi sürüyeGüvenme(Parçacýk sürüÖnderi) throws Exception {
		DoubleDizi enÝyiMerkezler = sürüÖnderi.kümeMerkezleri;
		DoubleDizi sürüEnÝyisineUzaklýk = enÝyiMerkezler.çýkar(kümeMerkezleri);
    	sürüEnÝyisineUzaklýk.çarp(sürüyeGüven);
		return sürüEnÝyisineUzaklýk;
	}
	private DoubleDizi kendineGüvenme() throws Exception {
		DoubleDizi kendiEnÝyisineUzaklýk = kendiEnÝyiKümeMerkezleri.çýkar(kümeMerkezleri);
    	kendiEnÝyisineUzaklýk.çarp(kendineGüven);
		return kendiEnÝyisineUzaklýk;
	}
    public void konumuGüncelle(Parçacýk sürüÖnderi) throws Exception{
    	hýzGüncelle(sürüÖnderi);
    	kümeMerkezleri.topla(hýz);
    	amaçDeðeriHesapla();
    }

    public String yaz(){
    	String yazý = "";
    	yazý += "hýz : \n" + hýz.yaz("\t") + "\n\n"; 
    	yazý += "amaç deðeri : " + amaçDeðeri + "\n\n";
    	yazý += "en iyi amaç deðeri : " + kendiEnÝyiAmaçDeðeri + "\n\n";
    	yazý += "küme merkezlerine uzaklýk : " + ortalamaKümelerÝçiUzaklýk + "\n\n";
    	yazý += "kümeler arasý uzaklýk : " + ortalamaKümelerArasýUzaklýk +"\n\n";
    	yazý += "küme merkezleri : \n" + kümeMerkezleri.yaz("\t")+ "\n\n\n"; 
    	yazý += "en iyi küme merkezleri : \n" + kendiEnÝyiKümeMerkezleri.yaz("\t") + "\n\n\n";
    	yazý += kümeleriYaz();
    	return yazý;
    }
	private String kümeleriYaz() {
		String yazý = "";
		int kümeNo;
    	for(kümeNo=0;kümeNo<kümeler.size();kümeNo++){
    		ArrayList<Integer> küme = kümeler.get(kümeNo);
    		yazý += küme.toString() + "\n";
    	}
		return yazý;
	}

    public void kümeleriSýfýrla() throws Exception{
    	kümeler = null;
    	kümeleriBoþDoldur();
    	ilkKümele();
    }
}
