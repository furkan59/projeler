package SıkıştırılmışMetaSezgisel;

import java.util.ArrayList;

import veriYapıları.DoubleDizi;
import SıkıştırılmışVeriYapıları.BoolEksikMatrisAğaçlı;
import SıkıştırılmışVeriYapıları.EksikBoolDizi;
import SıkıştırılmışVeriYapıları.IntAramaAğacı2;
import SıkıştırılmışVeriYapıları.KümeMerkezi;
import SıkıştırılmışVeriYapıları.KümeMerkeziDizi;

public class Parçacık2 {

	KümeMerkeziDizi kümeMerkezleri;
	double toplamUzaklık;
	ArrayList<ArrayList<Integer>> kümeler; 
	
	double ortalamaKümelerİçiUzaklık = 0;
	double ortalamaKümelerArasıUzaklık = 0;
	
	DoubleDizi hız ;
	double amaçDeğeri;  // büyük bir şey olması istenir
    boolean amaçDeğeriVar = false;
    
	// en iyileri akılda tutma
	double kendiEnİyiAmaçDeğeri;
	KümeMerkeziDizi kendiEnİyiKümeMerkezleri ;
	
	protected double sürüyeGüven=60;
	protected double kendineGüven=50;
	protected double eylemsizlik=0.5;
	protected double ilkHız = 0.00003;
	
	private int kümeSayısı;
	private int parçaSayısı;
	
	// bu kullanılcak
    public Parçacık2(int kümeSayısı,int parçaSayısı,BoolEksikMatrisAğaçlı parçaMakine) throws Exception {
        this.kümeSayısı = kümeSayısı;
        this.parçaSayısı = parçaSayısı;
    	hız = new DoubleDizi(kümeSayısı, parçaSayısı, ilkHız);
        kümeleriBoşDoldur();
        kümeMerkezleriniRassalDoldur();
        ilkKümele(parçaMakine);
        kendiEnİyiAmaçDeğeri = amaçDeğeriHesapla(parçaMakine);
        hız = new DoubleDizi(kümeSayısı, parçaSayısı, ilkHız);
	}
    public void güvenParameterileriniBelirle(double sürüyeGüven, double kendineGüven , double eylemsizlik ){
    	this.sürüyeGüven = sürüyeGüven;
    	this.kendineGüven = kendineGüven;
    	this.eylemsizlik = eylemsizlik;
    }
    public void ilkHızBelirle(double ilkHız) throws Exception{
    	ilkHızKontrol(ilkHız);
    	this.ilkHız = ilkHız;
    }
	private void ilkHızKontrol(double ilkHız) throws Exception {
		if(ilkHız < 0 ){
    		throw new Exception("ilk hız sıfırdan küçük olamaz girlen ilk hız :" + ilkHız + "\n");
    	}
	}

    
	private void kümeleriBoşDoldur() {
		kümeler = new ArrayList<ArrayList<Integer>>();
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
			ArrayList<Integer> yeniKüme = new ArrayList<Integer>();
			kümeler.add(yeniKüme);
		}
	}
    private void kümeMerkezleriniRassalDoldur() throws Exception{
    	kümeMerkezleri = new KümeMerkeziDizi(kümeSayısı, parçaSayısı);
    	kümeMerkezleri.rassalDiziDoldur();
    	kendiEnİyiKümeMerkezleri = kümeMerkezleri.kopya();
    }
	private int makineyeEnÇokBenzeyenKümeyiBul(IntAramaAğacı2 makineSatırı) throws Exception{
		double enKüçükUzaklık = kümeMerkezleri.dizi[0].öklidUzaklığıBul(makineSatırı);
		int enYakınKüme = 0;
		int kümeNo;
		for(kümeNo=1;kümeNo<kümeMerkezleri.satırsay();kümeNo++){
			double yeniUzaklık = kümeMerkezleri.dizi[kümeNo].öklidUzaklığıBul(makineSatırı);
			if(yeniUzaklık < enKüçükUzaklık){
				enKüçükUzaklık = yeniUzaklık;
				enYakınKüme = kümeNo;
			}
		}
		return enYakınKüme;
	}
	
    private int makineyeEnÇokBenzeyenKümeyiBul(int makineNo, BoolEksikMatrisAğaçlı parçaMakine) throws Exception{
    	IntAramaAğacı2 makineSatırı = parçaMakine.dizi[makineNo];
    	return makineyeEnÇokBenzeyenKümeyiBul(makineSatırı);
    }
	/**
	 * herkes en çok benzediği kümeye gidecek
	 * @throws Exception
	 */
	private void ilkKümele (BoolEksikMatrisAğaçlı parçaMakine) throws Exception{
		int makineNo;
		for(makineNo=0;makineNo<parçaMakine.satırSay();makineNo++){
			int makineninGideceğiKüme = makineyeEnÇokBenzeyenKümeyiBul(makineNo,parçaMakine);
			ArrayList<Integer> küme = kümeler.get(makineninGideceğiKüme);
			küme.add(makineNo);
		}
	}
    // amaç fonksyon değerini hesaplama yöntemleri
	
	// bir kümenin elemanları ile merkezi arasındaki uzaklığı bulma
    private void ortalamaKümeİçiUzaklıklarHesapla(BoolEksikMatrisAğaçlı parçaMakine) throws Exception{
    	ortalamaKümelerİçiUzaklık = 0;
    	int kümeNo;
    	for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
    		ortalamaKümelerİçiUzaklık += kümeninElemanlarınaUzaklığı(kümeNo, parçaMakine);
    	}
    	ortalamaKümelerİçiUzaklık /= kümeSayısı;
    }
    /**
     * bu işlemin kullanılabilmesi için kümeler dolu olmalıdır
     * @param kümeNo
     * @return
     * @throws Exception 
     */
    private double kümeninElemanlarınaUzaklığı(int kümeNo, BoolEksikMatrisAğaçlı parçaMakine) throws Exception{
    	double uzaklık = 0;
    	int elemanNo;
    	ArrayList<Integer> küme = kümeler.get(kümeNo);
    	KümeMerkezi merkez = kümeMerkezleri.dizi[kümeNo];
    	int elemanSayısı = küme.size();
    	for(elemanNo=0;elemanNo<elemanSayısı;elemanNo++){
    		 IntAramaAğacı2 makineSatırı = parçaMakine.dizi[küme.get(elemanNo)];
             uzaklık += merkez.öklidUzaklığıBul(makineSatırı);
    	}
    	return uzaklık;
    }
	// tüm kümeler için uzaklık
	private void ortalamaKümelerArasıUzaklıkBul() throws Exception{
		ortalamaKümelerArasıUzaklık = 0;
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
			ortalamaKümelerArasıUzaklık += kümeninDiğerKümelerleUzaklığınıBul(kümeNo);
		}
		ortalamaKümelerArasıUzaklık /= kümeSayısı;
	}
    private double ikiKümeMerkeziArasıUzaklıkBul(int küme1,int küme2) throws Exception{
    	KümeMerkezi merkez1 = kümeMerkezleri.dizi[küme1];
    	KümeMerkezi merkez2 = kümeMerkezleri.dizi[küme2];
    	return merkez1.öklidUzaklığıBul(merkez2);
    }
    /**
     * kümeNo = x olursa 'x' den sonrası için kümenin diğer kümelere olan uzaklığını bulur
     * @param kümeNo
     * @return
     * @throws Exception 
     */
    private double kümeninDiğerKümelerleUzaklığınıBul(int küme) throws Exception{
    	double uzaklık = 0;
    	int kümeNo;
    	for(kümeNo=küme+1;kümeNo<kümeSayısı;kümeNo++){
    		uzaklık += ikiKümeMerkeziArasıUzaklıkBul(küme, kümeNo);
    	}
    	return uzaklık;
    }
    
    public double amaçDeğeriHesapla(BoolEksikMatrisAğaçlı parçaMakine) throws Exception{
    	ortalamaKümeİçiUzaklıklarHesapla(parçaMakine);
    	ortalamaKümelerArasıUzaklıkBul();
    	amaçDeğeri = ortalamaKümelerİçiUzaklık - ortalamaKümelerArasıUzaklık;
    	//amaçDeğeri -= (kümeSayısı - boşKümeSayısıBul())/kümeSayısı;
    	if(amaçDeğeriVar == false){
            amaçDeğeriVar = true;
            return amaçDeğeri;
    	}
    	else {
    		amaçDeğeriGüncelle();
    		return amaçDeğeri;
    	}
    }
    public int boşKümeSayısıBul(){
    	int kümeNo;
    	int boşKümesayısı = 0;
    	for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
    		if(kümeler.get(kümeNo).size() == 0){
    			boşKümesayısı ++;
    		}
    	}
    	return boşKümesayısı;
    }
	private void amaçDeğeriGüncelle() throws Exception {
        if(amaçDeğeri > kendiEnİyiAmaçDeğeri){
        	kendiEnİyiAmaçDeğeri = amaçDeğeri;
        	kendiEnİyiKümeMerkezleri = kümeMerkezleri.kopya();
        }
	}
    
	
	// amaç fonksyon değerini hesaplama yöntemleri
	public void uzaklıklarıSıfırla(){
		ortalamaKümelerArasıUzaklık = 0;
		ortalamaKümelerİçiUzaklık = 0;
	}
	
	private void hızGüncelle(Parçacık2 sürüÖnderi) throws Exception{
    	hız.çarp(eylemsizlik);
    	DoubleDizi kendiEnİyisineUzaklık = kendineGüvenme();
    	DoubleDizi sürüEnİyisineUzaklık = sürüyeGüvenme(sürüÖnderi);
    	hız.topla(sürüEnİyisineUzaklık);
    	hız.topla(kendiEnİyisineUzaklık);
    }
	private DoubleDizi sürüyeGüvenme(Parçacık2 sürüÖnderi) throws Exception {
		KümeMerkeziDizi enİyiMerkezler = sürüÖnderi.kümeMerkezleri;
		DoubleDizi sürüEnİyisineUzaklık = enİyiMerkezler.çıkar2(kümeMerkezleri);
    	sürüEnİyisineUzaklık.çarp(sürüyeGüven);
		return sürüEnİyisineUzaklık;
	}
	private DoubleDizi kendineGüvenme() throws Exception {
		DoubleDizi kendiEnİyisineUzaklık = kendiEnİyiKümeMerkezleri.çıkar2(kümeMerkezleri);
    	kendiEnİyisineUzaklık.çarp(kendineGüven);
		return kendiEnİyisineUzaklık;
	}
    public void konumuGüncelle(Parçacık2 sürüÖnderi,BoolEksikMatrisAğaçlı parçaMakine) throws Exception{
    	hızGüncelle(sürüÖnderi);
    	kümeMerkezleri.topla2(hız);
    	amaçDeğeriHesapla(parçaMakine);
    }

    public String yaz() throws Exception{
    	String yazı = "";
    	yazı += "hız : \n" + hız.yaz("\t") + "\n\n"; 
    	yazı += "amaç değeri : " + amaçDeğeri + "\n\n";
    	yazı += "en iyi amaç değeri : " + kendiEnİyiAmaçDeğeri + "\n\n";
    	yazı += "küme merkezlerine uzaklık : " + ortalamaKümelerİçiUzaklık + "\n\n";
    	yazı += "kümeler arası uzaklık : " + ortalamaKümelerArasıUzaklık +"\n\n";
    	yazı += "küme merkezleri : \n" + kümeMerkezleri.yaz("\t")+ "\n\n\n"; 
    	yazı += "en iyi küme merkezleri : \n" + kendiEnİyiKümeMerkezleri.yaz("\t") + "\n\n\n";
    	yazı += "kümeler \n\n";
    	yazı += kümeleriYaz();
    	return yazı;
    }
	private String kümeleriYaz() {
		String yazı = "";
		int kümeNo;
    	for(kümeNo=0;kümeNo<kümeler.size();kümeNo++){
    		ArrayList<Integer> küme = kümeler.get(kümeNo);
    		yazı += küme.toString() + "\n";
    	}
		return yazı;
	}


}
