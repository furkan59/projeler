package metaSezgisel;
import veriYapıları.*;

import java.util.ArrayList;

import kümeci.Kümeleme;

public class K_Ortalamalar extends Kümeleme {

	DoubleDizi kümeMerkezleri;
	int kümeSayısı;
	ArrayList<ArrayList<Integer>> kümeler; 
	Boolean ilerle = false; // her aşama için bakılacak 
	
    public K_Ortalamalar(BoolDizi parçaMakine, String[] makineİsimleri,String[] parçaİsimleri) {
		super(parçaMakine, makineİsimleri, parçaİsimleri);
		makineGruplarıOluştur();
	}
	public K_Ortalamalar(String yazı, char ayırıcı) throws Exception {
		super(yazı, ayırıcı);
		makineGruplarıOluştur();
	}
	public K_Ortalamalar(String yazı) throws Exception {
		super(yazı);
		makineGruplarıOluştur();
	}
    
	public void kümeSayısıBelirle(int kümeSayısı) throws Exception{
		kümeSayısınıKontrolEt(kümeSayısı);
		this.kümeSayısı = kümeSayısı;
		kümeleriBoşDoldur();
	}
	private void kümeleriBoşDoldur() {
		kümeler = new ArrayList<ArrayList<Integer>>();
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
			ArrayList<Integer> yeniKüme = new ArrayList<Integer>();
			kümeler.add(yeniKüme);
		}
	}
	private void kümeSayısınıKontrolEt(int kümeSayısı) throws Exception {
		if(kümeSayısı < 0 || kümeSayısı > parçaMakine.satırSay()){
			throw new Exception("geçersiz küme sayısı\ngirilen küme sayısı : " + kümeSayısı + "\ntoplam makine sayısı\n"); 
		}
	}
    public void kümeMerkezleriniRassalBelirle() throws Exception{
    	kümeMerkezleri = new DoubleDizi(kümeSayısı, parçaİsimleri.length);
    	kümeMerkezleri.rassalDiziDoldur();
    }
    
    public int makineyeEnYakınKümeyiBul(int makineNo) throws Exception{
    	BoolVektör makineSatırı = parçaMakine.satır(makineNo);
    	DoubleVektör ilkMakine = kümeMerkezleri.dizi[0];
    	double enKüçükUzaklık = makineSatırı.öklidUzaklığıHesapla(ilkMakine); // ilk başta böyle kabul edelim
    	int enYakınKüme = 0;
    	int kümeNo;
    	for(kümeNo=1;kümeNo<kümeSayısı;kümeNo++){
    		DoubleVektör yeniKüme = kümeMerkezleri.dizi[kümeNo];
    		double yeniUzaklık = makineSatırı.öklidUzaklığıHesapla(yeniKüme);
    		if(yeniUzaklık < enKüçükUzaklık){
    			enKüçükUzaklık = yeniUzaklık;
    			enYakınKüme = kümeNo;
    		}
    	}
    	return enYakınKüme;
    }
    public void ilkKümeleme() throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<parçaMakine.satırSay();makineNo++){
    		int makineyeYakınKüme = makineyeEnYakınKümeyiBul(makineNo);
    		ArrayList<Integer> makineninGideceğiKüme = kümeler.get(makineyeYakınKüme);
    		makineninGideceğiKüme.add(makineNo);
    	}
    }
    /**
     * bunun yapılabilmesi için kümenin dolu olması gerekir
     * @param kümeNo
     * @throws Exception 
     */
    private void yeniKümeMerkeziBelirle(int kümeNo) throws Exception{
    	kümelerBoşMuBak();
    	int makineSayısı = kümeler.get(kümeNo).size();
    	int parçaSayısı = parçaMakine.sutunSay();
    	int parçaNo;
    	for(parçaNo=0;parçaNo<parçaSayısı;parçaNo++){
    		kümeMerkeziİçinSutunOrtalamasIHesapla(kümeNo, makineSayısı, parçaNo);
    	}
    }
	private void kümeMerkeziİçinSutunOrtalamasIHesapla(int kümeNo, int makineSayısı, int parçaNo) throws Exception {
		int makineNo;
		double toplam = 0;
		for(makineNo=0;makineNo<makineSayısı;makineNo++){
			if(parçaMakine.eriş(makineNo, parçaNo) ){
				toplam ++;
			}
		}
		kümeMerkezleri.değiş(kümeNo, parçaNo, toplam / makineSayısı);
	}
	private void yeniKümeMerkezleriBelirle() throws Exception{
		int kümeNo;
		for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
			yeniKümeMerkeziBelirle(kümeNo);
		}
	}
	
    private void kümelerBoşMuBak() throws Exception {
		if(kümeler.get(0) == null){
    		throw new Exception("kümeler boş\n");
    	}
	}
    private void yeniKümelemeYap() throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<parçaMakine.satırSay();makineNo++){
    		makineyiKümeyeAta(makineNo);
    	}
    }
	private void makineyiKümeyeAta(int makineNo) throws Exception {
		int makineninEskiKümesi = makineninKümesiniBul(makineNo);
		int makineninYeniKümesi = makineyeEnYakınKümeyiBul(makineNo);
		if(makineninEskiKümesi != makineninYeniKümesi){ // değişim var
			makineyiBaşkaBirKümeyeAl(makineNo, makineninEskiKümesi, makineninYeniKümesi);
		}
	}
	private void makineyiBaşkaBirKümeyeAl(int makineNo,int makineninEskiKümesi, int makineninYeniKümesi) {
		makineyiEskiKümesindenÇıkar(makineNo, makineninEskiKümesi);
		makineyiYeniKümeyeEkle(makineNo, makineninYeniKümesi);
		ilerle = true;
	}
	private void makineyiYeniKümeyeEkle(int makineNo, int makineninYeniKümesi) {
		ArrayList<Integer> makineninGideceğiKüme = kümeler.get(makineninYeniKümesi);
		makineninGideceğiKüme.add(makineNo);
	}
	private void makineyiEskiKümesindenÇıkar(int makineNo,int makineninEskiKümesi) {
		ArrayList<Integer> makineninGeldiğiKüme = kümeler.get(makineninEskiKümesi);
		int makineninYeri = makineninGeldiğiKüme.indexOf(makineNo);
		makineninGeldiğiKüme.remove(makineninYeri);
		if(makineninGeldiğiKüme.size() == 0){
			ilerle = false; // bir küme bitmiş
		}
	}
	/**
	 * @param makineNo
	 * @return
	 * @throws Exception 
	 */
    private int  makineninKümesiniBul(int makineNo) throws Exception{
    	int kümeNo;
    	for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
    		ArrayList<Integer> küme = kümeler.get(kümeNo);
    		if(küme.contains(makineNo)){
    			return kümeNo;
    		}
    	}
    	throw new Exception("makine yok!\nmakineNo : " + makineNo + "\n" );
    }
	
    public String çöz() throws Exception{
    	String çözüm = "";
    	ilkAşama();
    	çözüm += yaz();
    	int aşamaNo;
    	for(aşamaNo=0;;aşamaNo++){
    		ilerle();
    		çözüm += "aşamaNo : " + aşamaNo + "\n\n" + yaz() + "\n\n"; 
    		if(ilerle == false){
    			break;
    		}
    	}
    	return çözüm;
    }
	private void ilkAşama() throws Exception {
		kümeleriBoşDoldur();
    	kümeMerkezleriniRassalBelirle();
    	ilkKümeleme();
	}
    @Override
    public String yaz(){
    	String yazı = "değişim var mı : "  + ilerle + "\n\n";
    	yazı += "küme sayısı : " + kümeSayısı + "\n\n";
    	yazı += "küme merkezleri :\n\n" + kümeMerkezleri.yaz("\t") + "\n\n";
    	yazı += kümeleriYaz();
    	return yazı;
    }
    public String kümeleriYaz(){
    	String yazı ="";
    	int kümeNo;
    	for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
    		yazı += kümeyiYazdır(kümeNo);
    	}
    	return yazı;
    }
    private String kümeyiYazdır(int kümeNo){
    	String yazı = "";
    	yazı += "küme : " + (kümeNo + 1)  + " : ";
    	ArrayList<Integer> küme = kümeler.get(kümeNo);
		int elemanNo;
		for(elemanNo=0;elemanNo<küme.size();elemanNo++){
			yazı += küme.get(elemanNo) + " , ";
		}
		yazı += "\n";
		return yazı;
    }

    public String hızlıÇöz() throws Exception{
    	String çözüm = "";
    	ilkAşama();
    	hızlıÇözümDöngüsü();
    	çözüm += "parça makine matrisi : " + yeniParçaMakineMatrisiYaz2() + "\n";
    	return çözüm;
    }
	private void hızlıÇözümDöngüsü() throws Exception {
		int aşamaNo;
    	for(aşamaNo=0;;aşamaNo++){
    		ilerle();
    		if(ilerle == false){
    			break;
    		}
    	}
	}
	private void ilerle() throws Exception {
		ilerle = false; // ilk başta değişim yok kabul edelim eğer değişim olmazs süreç duracaktır ama yeni kümeleme
		// yapıldıktan sonra değişim var ise görünür
		yeniKümeMerkezleriBelirle();
		yeniKümelemeYap();
	}

}
