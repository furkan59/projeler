package metaSezgisel;
import veriYapýlarý.*;

import java.util.ArrayList;

import kümeci.Kümeleme;

public class K_Ortalamalar extends Kümeleme {

	DoubleDizi kümeMerkezleri;
	int kümeSayýsý;
	ArrayList<ArrayList<Integer>> kümeler; 
	Boolean ilerle = false; // her aþama için bakýlacak 
	
    public K_Ortalamalar(BoolDizi parçaMakine, String[] makineÝsimleri,String[] parçaÝsimleri) {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
		makineGruplarýOluþtur();
	}
	public K_Ortalamalar(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
		makineGruplarýOluþtur();
	}
	public K_Ortalamalar(String yazý) throws Exception {
		super(yazý);
		makineGruplarýOluþtur();
	}
    
	public void kümeSayýsýBelirle(int kümeSayýsý) throws Exception{
		kümeSayýsýnýKontrolEt(kümeSayýsý);
		this.kümeSayýsý = kümeSayýsý;
		kümeleriBoþDoldur();
	}
	private void kümeleriBoþDoldur() {
		kümeler = new ArrayList<ArrayList<Integer>>();
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
			ArrayList<Integer> yeniKüme = new ArrayList<Integer>();
			kümeler.add(yeniKüme);
		}
	}
	private void kümeSayýsýnýKontrolEt(int kümeSayýsý) throws Exception {
		if(kümeSayýsý < 0 || kümeSayýsý > parçaMakine.satýrSay()){
			throw new Exception("geçersiz küme sayýsý\ngirilen küme sayýsý : " + kümeSayýsý + "\ntoplam makine sayýsý\n"); 
		}
	}
    public void kümeMerkezleriniRassalBelirle() throws Exception{
    	kümeMerkezleri = new DoubleDizi(kümeSayýsý, parçaÝsimleri.length);
    	kümeMerkezleri.rassalDiziDoldur();
    }
    
    public int makineyeEnYakýnKümeyiBul(int makineNo) throws Exception{
    	BoolVektör makineSatýrý = parçaMakine.satýr(makineNo);
    	DoubleVektör ilkMakine = kümeMerkezleri.dizi[0];
    	double enKüçükUzaklýk = makineSatýrý.öklidUzaklýðýHesapla(ilkMakine); // ilk baþta böyle kabul edelim
    	int enYakýnKüme = 0;
    	int kümeNo;
    	for(kümeNo=1;kümeNo<kümeSayýsý;kümeNo++){
    		DoubleVektör yeniKüme = kümeMerkezleri.dizi[kümeNo];
    		double yeniUzaklýk = makineSatýrý.öklidUzaklýðýHesapla(yeniKüme);
    		if(yeniUzaklýk < enKüçükUzaklýk){
    			enKüçükUzaklýk = yeniUzaklýk;
    			enYakýnKüme = kümeNo;
    		}
    	}
    	return enYakýnKüme;
    }
    public void ilkKümeleme() throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<parçaMakine.satýrSay();makineNo++){
    		int makineyeYakýnKüme = makineyeEnYakýnKümeyiBul(makineNo);
    		ArrayList<Integer> makineninGideceðiKüme = kümeler.get(makineyeYakýnKüme);
    		makineninGideceðiKüme.add(makineNo);
    	}
    }
    /**
     * bunun yapýlabilmesi için kümenin dolu olmasý gerekir
     * @param kümeNo
     * @throws Exception 
     */
    private void yeniKümeMerkeziBelirle(int kümeNo) throws Exception{
    	kümelerBoþMuBak();
    	int makineSayýsý = kümeler.get(kümeNo).size();
    	int parçaSayýsý = parçaMakine.sutunSay();
    	int parçaNo;
    	for(parçaNo=0;parçaNo<parçaSayýsý;parçaNo++){
    		kümeMerkeziÝçinSutunOrtalamasIHesapla(kümeNo, makineSayýsý, parçaNo);
    	}
    }
	private void kümeMerkeziÝçinSutunOrtalamasIHesapla(int kümeNo, int makineSayýsý, int parçaNo) throws Exception {
		int makineNo;
		double toplam = 0;
		for(makineNo=0;makineNo<makineSayýsý;makineNo++){
			if(parçaMakine.eriþ(makineNo, parçaNo) ){
				toplam ++;
			}
		}
		kümeMerkezleri.deðiþ(kümeNo, parçaNo, toplam / makineSayýsý);
	}
	private void yeniKümeMerkezleriBelirle() throws Exception{
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
			yeniKümeMerkeziBelirle(kümeNo);
		}
	}
	
    private void kümelerBoþMuBak() throws Exception {
		if(kümeler.get(0) == null){
    		throw new Exception("kümeler boþ\n");
    	}
	}
    private void yeniKümelemeYap() throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<parçaMakine.satýrSay();makineNo++){
    		makineyiKümeyeAta(makineNo);
    	}
    }
	private void makineyiKümeyeAta(int makineNo) throws Exception {
		int makineninEskiKümesi = makineninKümesiniBul(makineNo);
		int makineninYeniKümesi = makineyeEnYakýnKümeyiBul(makineNo);
		if(makineninEskiKümesi != makineninYeniKümesi){ // deðiþim var
			makineyiBaþkaBirKümeyeAl(makineNo, makineninEskiKümesi, makineninYeniKümesi);
		}
	}
	private void makineyiBaþkaBirKümeyeAl(int makineNo,int makineninEskiKümesi, int makineninYeniKümesi) {
		makineyiEskiKümesindenÇýkar(makineNo, makineninEskiKümesi);
		makineyiYeniKümeyeEkle(makineNo, makineninYeniKümesi);
		ilerle = true;
	}
	private void makineyiYeniKümeyeEkle(int makineNo, int makineninYeniKümesi) {
		ArrayList<Integer> makineninGideceðiKüme = kümeler.get(makineninYeniKümesi);
		makineninGideceðiKüme.add(makineNo);
	}
	private void makineyiEskiKümesindenÇýkar(int makineNo,int makineninEskiKümesi) {
		ArrayList<Integer> makineninGeldiðiKüme = kümeler.get(makineninEskiKümesi);
		int makineninYeri = makineninGeldiðiKüme.indexOf(makineNo);
		makineninGeldiðiKüme.remove(makineninYeri);
		if(makineninGeldiðiKüme.size() == 0){
			ilerle = false; // bir küme bitmiþ
		}
	}
	/**
	 * @param makineNo
	 * @return
	 * @throws Exception 
	 */
    private int  makineninKümesiniBul(int makineNo) throws Exception{
    	int kümeNo;
    	for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
    		ArrayList<Integer> küme = kümeler.get(kümeNo);
    		if(küme.contains(makineNo)){
    			return kümeNo;
    		}
    	}
    	throw new Exception("makine yok!\nmakineNo : " + makineNo + "\n" );
    }
	
    public String çöz() throws Exception{
    	String çözüm = "";
    	ilkAþama();
    	çözüm += yaz();
    	int aþamaNo;
    	for(aþamaNo=0;;aþamaNo++){
    		ilerle();
    		çözüm += "aþamaNo : " + aþamaNo + "\n\n" + yaz() + "\n\n"; 
    		if(ilerle == false){
    			break;
    		}
    	}
    	return çözüm;
    }
	private void ilkAþama() throws Exception {
		kümeleriBoþDoldur();
    	kümeMerkezleriniRassalBelirle();
    	ilkKümeleme();
	}
    @Override
    public String yaz(){
    	String yazý = "deðiþim var mý : "  + ilerle + "\n\n";
    	yazý += "küme sayýsý : " + kümeSayýsý + "\n\n";
    	yazý += "küme merkezleri :\n\n" + kümeMerkezleri.yaz("\t") + "\n\n";
    	yazý += kümeleriYaz();
    	return yazý;
    }
    public String kümeleriYaz(){
    	String yazý ="";
    	int kümeNo;
    	for(kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
    		yazý += kümeyiYazdýr(kümeNo);
    	}
    	return yazý;
    }
    private String kümeyiYazdýr(int kümeNo){
    	String yazý = "";
    	yazý += "küme : " + (kümeNo + 1)  + " : ";
    	ArrayList<Integer> küme = kümeler.get(kümeNo);
		int elemanNo;
		for(elemanNo=0;elemanNo<küme.size();elemanNo++){
			yazý += küme.get(elemanNo) + " , ";
		}
		yazý += "\n";
		return yazý;
    }

    public String hýzlýÇöz() throws Exception{
    	String çözüm = "";
    	ilkAþama();
    	hýzlýÇözümDöngüsü();
    	çözüm += "parça makine matrisi : " + yeniParçaMakineMatrisiYaz2() + "\n";
    	return çözüm;
    }
	private void hýzlýÇözümDöngüsü() throws Exception {
		int aþamaNo;
    	for(aþamaNo=0;;aþamaNo++){
    		ilerle();
    		if(ilerle == false){
    			break;
    		}
    	}
	}
	private void ilerle() throws Exception {
		ilerle = false; // ilk baþta deðiþim yok kabul edelim eðer deðiþim olmazs süreç duracaktýr ama yeni kümeleme
		// yapýldýktan sonra deðiþim var ise görünür
		yeniKümeMerkezleriBelirle();
		yeniKümelemeYap();
	}

}
