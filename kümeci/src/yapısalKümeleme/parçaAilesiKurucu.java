package yapısalKümeleme;
import veriYapıları.*;
import java.util.ArrayList;

public class parçaAilesiKurucu {

	ArrayList<ArrayList<Parça>> parçaGrupları = new ArrayList<ArrayList<Parça>>();
	DoubleListDizi uzaklık = new DoubleListDizi();
	
	int [] satırSutun = new int [2]; // satır , sutun
	
	public double enDüşükUzaklık = -1;
    public int enAzAileSayısı = -1;
    public int enFazlaAileBüyüklüğü = -1;
    
    Boolean uzaklıkKoşulu ;
    Boolean aileBüyüklüğüKoşulu ;
    Boolean aileSayısıKoşulu;
    Boolean olurluÇözüm = true;
    
    double aşamaİçinEnKüçükUzaklık;
    /**
     * parça1:1,2,3,34 
     * parça2:3,12,3,4
     * parça3:4,4,2,13
     * @param yazı
     * @throws Exception
     */
    public parçaAilesiKurucu(String  yazı) throws Exception {
		String [] satırlar = yazı.split("\\n");
		int satırNo;
		for(satırNo=0;satırNo<satırlar.length;satırNo++){
			Parça yeniParça = new Parça(satırlar[satırNo]);
			ArrayList<Parça> yeniSatır = new ArrayList<Parça>();
			yeniSatır.add(yeniParça);
			parçaGrupları.add(yeniSatır);
		}
	}
	/**
	 * sadece ilk aşama için yapılı matris oluşturulduktan sonra zaten hesaplama işi yok
	 * <br>iki grup benzerliği için küçük olan seçilecek hesaplama yok
	 * @throws Exception
	 */
    public void parçaUzaklıkMatrisiniHesapla() throws Exception{
    	int satırNo;
    	for(satırNo=0;satırNo<parçaGrupları.size();satırNo++){
    		yeniSatırEkle(satırNo);
    	}
    }
	private void yeniSatırEkle(int satırNo) throws Exception {
		DoubleListVektör yeniSatır = new DoubleListVektör();
		Parça parça1 = parçaGrupları.get(satırNo).get(0); // ilk başta zaten her grupta bir parça var
		yeniSatırHesapla(yeniSatır, parça1, satırNo);
		uzaklık.satırEkle(yeniSatır);
	}
	private void yeniSatırHesapla(DoubleListVektör yeniSatır, Parça parça1,int satırNo) throws Exception {
		int sutunNo;
		for(sutunNo=0;sutunNo<satırNo+1;sutunNo++){
			yeniUzaklıkHesapla(sutunNo, yeniSatır, parça1);
		}
	}
	private void yeniUzaklıkHesapla(int sutunNo, DoubleListVektör yeniSatır, Parça parça1) throws Exception {
		Parça parça2 = parçaGrupları.get(sutunNo).get(0); // ilk başta her grupta bir parça var
		double yeniBenzerlik = parça1.minkowskiUzaklıkBul(parça2);
		yeniSatır.ekle(yeniBenzerlik);
	}

	private double grupUzaklıkEriş(int grup1, int grup2){
		if(grup1 > grup2){
			return uzaklık.eriş(grup1, grup2);
		}
		else{
			return uzaklık.eriş(grup2, grup1);
		}
	}
	// gruplar arası uzaklık hesaplama
	/**
	 * grupNo1 grubu ile grupNo2 grubunun parçaNo parçası arasındaki uzaklığı verir
	 * @param grupNo1
	 * @param grupNo2
	 * @param parçaNo
	 * @return
	 * @throws Exception
	 */
	public double parçaGrupUzaklıkHesapla(int grupNo1, int grupNo2, int parçaNo) throws Exception{
		ArrayList<Parça> parça2ninGrubu = parçaGrupları.get(grupNo2);
		Parça parça = parça2ninGrubu.get(parçaNo);
		ArrayList<Parça> birinciParçaGrubu = parçaGrupları.get(grupNo1);
		return parçaGrupUzaklıkHesapla(birinciParçaGrubu, parça);
	}
	public double parçaGrupUzaklıkHesapla(int grupNo, Parça parça) throws Exception{
		ArrayList<Parça> grup = parçaGrupları.get(grupNo);
		return parçaGrupUzaklıkHesapla(grup, parça);
	}
	public double parçaGrupUzaklıkHesapla(ArrayList<Parça> grup, Parça parça) throws Exception{
        double enKüçükUzaklık = grup.get(0).minkowskiUzaklıkBul(parça); // şimdilik böyle kabul ediyoruz
		int parçaNo;
		for(parçaNo=1;parçaNo<grup.size();parçaNo++){
			Parça grupElemanı = grup.get(parçaNo);
			enKüçükUzaklık = yeniUzaklıkAra(parça, enKüçükUzaklık, grupElemanı);
		}
		return enKüçükUzaklık;
	}
	private double yeniUzaklıkAra(Parça parça, double enKüçükUzaklık, Parça grupElemanı) throws Exception {
		double yeniUzaklık = grupElemanı.minkowskiUzaklıkBul(parça);
		if(yeniUzaklık < enKüçükUzaklık ){
			enKüçükUzaklık = yeniUzaklık;
		}
		return enKüçükUzaklık;
	}
    public double grupUzaklıkHesapla(int grup1, int grup2) throws Exception{
    	if(grup1 == grup2){
    		return 0.0;
    	}
    	double enKüçükUzaklık = parçaGrupUzaklıkHesapla(grup1,grup2,0); // ilk grup ile ikinci grubun 0. parçası arasındaki uzaklık
    	int grup1No;
    	for(grup1No=1;grup1No<parçaGrupları.get(grup1).size();grup1No++){
    		double yeniUzaklık = parçaGrupUzaklıkHesapla(grup1, grup2, grup1No);
    		if(yeniUzaklık < enKüçükUzaklık){
    			enKüçükUzaklık = yeniUzaklık;
    		}
    	}
    	return enKüçükUzaklık;
    }
	
    /**
     * geçmişteki verilerden yaralanarak yeni oluşacak
	 * <br>grubun hazırlanması içn gerekli uzaklık bilgisini bulur
	 */
    private double grupUzaklıkEriş(int grupOlacak1, int grupOlacak2, int olanGrup){
    	double uzaklık1 = grupUzaklıkEriş(grupOlacak2, olanGrup);
    	double uzaklık2 = grupUzaklıkEriş(grupOlacak1, olanGrup);
    	return küçüğüDön(uzaklık1, uzaklık2);
    }
	private double küçüğüDön(double uzaklık1, double uzaklık2) {
		if(uzaklık1 < uzaklık2){
    		return uzaklık1;
    	}
    	else{
    		return uzaklık2;
    	}
	}
    
	
	// gruplar arası uzaklık hesaplama
    // en yakın grupları bulma
    public void enYakınGruplarıBul() throws Exception{
    	olurluEnKüçükUzaklıkBul();
    	if(olurluÇözüm == true){ // olurlu çözüm bulundu ise taramaya decam edilecek
        	enKüçükUzaklığınYeriniBul();
    	}
    }
	private void enKüçükUzaklığınYeriniBul() throws Exception {
		int grupNo1;
		for(grupNo1=0;grupNo1<parçaGrupları.size();grupNo1++){
			grup1İçinEnKüçükUzaklıkBul(grupNo1);
		}
	}
    private void grup1İçinEnKüçükUzaklıkBul(int grupNo1) throws Exception{
    	int grupNo2;
    	for(grupNo2=grupNo1+1;grupNo2<parçaGrupları.size();grupNo2++){
			olurluEnKüçükUzaklıkBul(grupNo1, grupNo2);
		}
    }
	private void olurluEnKüçükUzaklıkBul(int grupNo1, int grupNo2) throws Exception {
		aşamaBaşıKoşullarınİlkDeğerleriniVer(); // tüm değişkenlere true verir, çünkü kısıt yoksa koşul true olur
		tümKoşullaraBak(grupNo1, grupNo2); // olurlu çözüm değişkenini hesaplar
		if(olurluÇözüm == true){ // olurlu çözüm bulundu ise en yakın uzaklık bulunmaya çalışılır
			yeniGrubUzaklığıKıyasla(grupNo1, grupNo2);	
		}
	}
	private void yeniGrubUzaklığıKıyasla(int grupNo1, int grupNo2) throws Exception {
		double yeniUzaklık = grupUzaklıkHesapla(grupNo1, grupNo2); // olurlu çift bulunmalı
		if(yeniUzaklık < aşamaİçinEnKüçükUzaklık){
			aşamaİçinEnKüçükUzaklık = yeniUzaklık;
			// küçük olan satır olacak grupNo1 daha küçük olacağından
			satırSutun[0] = grupNo1;
			satırSutun[1] = grupNo2;
		}
	}
   
	// en yakın grupları bulma
	
	// olurlu uzaklık bulma
    private void olurluEnKüçükUzaklıkBul() throws Exception{
    	int grupNo1;
    	for(grupNo1=0;grupNo1<parçaGrupları.size();grupNo1++){
    		grup1İçinOlurluDurumAra(grupNo1);
    		if(olurluÇözüm == true){ // üstteki döngüde bir tane olurlu çözüm bulunmuş demektir
    			break;
    		}
    	}
    }
	private void grup1İçinOlurluDurumAra(int grupNo1) throws Exception {
		int grupNo2;
		for(grupNo2=grupNo1+1;grupNo2<parçaGrupları.size();grupNo2++){
			aşamaBaşıKoşullarınİlkDeğerleriniVer();
			tümKoşullaraBak(grupNo1, grupNo2);
			if( olurluÇözüm  == true){ // bir tane olurlu çözüm bulundu demektir
				satırSutun [0] = grupNo1;
				satırSutun [1] = grupNo2;
				aşamaİçinEnKüçükUzaklık = grupUzaklıkHesapla(grupNo1, grupNo2);
				break;
			}
		}
	}
	private void tümKoşullaraBak(int grupNo1, int grupNo2) throws Exception {
		if(grupNo1 == grupNo2){ // aynı grup ise zaten uzaklık sıfırdır ama bu en küçük uzaklığa aday olamaz
			olurluÇözüm = false;
		}
		else{
			uzaklıkKoşulu(grupNo1, grupNo2);
			aileBüyüklüğüKoşulu(grupNo1, grupNo2);
			aileSayısıKoşulu();
			olurluÇözüm =  uzaklıkKoşulu && aileBüyüklüğüKoşulu && aileSayısıKoşulu;
		}
	}
	private void aileSayısıKoşulu() {
		if(enAzAileSayısı != -1){
			aileSayısınaBak();
		}
	}
	private void aileBüyüklüğüKoşulu(int grupNo1, int grupNo2) {
		if(enFazlaAileBüyüklüğü != -1){
			aileBüyüklüğüKoşulunaBak(grupNo1, grupNo2);
		}
	}
	private void uzaklıkKoşulu(int grupNo1, int grupNo2) throws Exception {
		if(enDüşükUzaklık != -1){
			olurluUzaklıkBak(grupNo1, grupNo2);
		}
	}
	
    private void olurluUzaklıkBak(int grupNo1, int grupNo2) throws Exception {
		double yeniUzaklık = grupUzaklıkHesapla(grupNo1, grupNo2);
		if(yeniUzaklık >= enDüşükUzaklık){
			satırSutun [0] = grupNo1;
			satırSutun [1] = grupNo2;
			uzaklıkKoşulu =  true;
		}
		else{
			uzaklıkKoşulu =  false;
		}
	}
	private void aileBüyüklüğüKoşulunaBak(int grupNo1, int grupNo2){
		int aileBüyüklüğü1 = parçaGrupları.get(grupNo1).size();
		int aileBüyüklüğü2 = parçaGrupları.get(grupNo2).size();
		if(aileBüyüklüğü1 + aileBüyüklüğü2 <= enFazlaAileBüyüklüğü){
			aileBüyüklüğüKoşulu = true;
		}
		else{
			aileBüyüklüğüKoşulu = false;
		}
	}
    private void aileSayısınaBak(){
    	int toplamAileSayısı = parçaGrupları.size();
    	if(toplamAileSayısı -1 < enAzAileSayısı){
    		aileSayısıKoşulu = false; 
    	}
    	else{
    		aileSayısıKoşulu = true;
    	}
    }
    /**
     * tüm koşullar geçti kabule edecek
     * <br>çünkü bu koşulun olup olmadığını bimiyoruz
     * <br>eğer koşul konmamışsa zaten bir sonraki aşamaya geçmeyi engellemez
     */
    private void aşamaBaşıKoşullarınİlkDeğerleriniVer(){
    	uzaklıkKoşulu = true;
        aileBüyüklüğüKoşulu = true;
        aileSayısıKoşulu = true;
    }

    // olurlu uzaklık bulma
    
    private DoubleListVektör uzaklığaYeniSatırHesapla(){
    	DoubleListVektör yeniSatır = new DoubleListVektör();
    	// yeni gruplananlar için yeni satır oluşturulacak
    	int parçaNo;
    	for(parçaNo=0;parçaNo<uzaklık.satırSay();parçaNo++){
    		if(parçaNo != satırSutun[0] && parçaNo != satırSutun[1]){
    			double yeniUzaklık = grupUzaklıkEriş(satırSutun[0],satırSutun[1],parçaNo);
    			yeniSatır.ekle(yeniUzaklık);
    		}
    	}
    	yeniSatır.ekle(0);
    	return yeniSatır;
    }
    private void satırSutunSil(){
    	if(satırSutun[0] < satırSutun[1]){
    		uzaklık.satırSutunSil(satırSutun[0], satırSutun[0]);
        	uzaklık.satırSutunSil(satırSutun[1]-1, satırSutun[1]-1);
    	}
    	else{
    		uzaklık.satırSutunSil(satırSutun[1], satırSutun[1]);
        	uzaklık.satırSutunSil(satırSutun[0]-1, satırSutun[0]-1);
    	}
    }
    
    private void yeniGrupOluştur(int grupNo1, int grupNo2) throws Exception{
    	if(grupNo1 == grupNo2){
    		throw new Exception("bunlar aynı grup " + grupNo1);
    	}
    	ArrayList<Parça> grup1 = parçaGrupları.get(grupNo1);
    	ArrayList<Parça> grup2 = parçaGrupları.get(grupNo2);
    	yeniGrubuEkle(grup1, grup2);
    	eskiGruplarıSil(grupNo1, grupNo2);
    }
	private void eskiGruplarıSil(int grupNo1, int grupNo2) {
		if(grupNo1 < grupNo2){
			parçaGrupları.remove(grupNo1); // önce küçük olan
	    	parçaGrupları.remove(grupNo2-1); // çünkü listede bir geri gitti her şey
		}
		else{
			parçaGrupları.remove(grupNo2); // önce küçük olan
	    	parçaGrupları.remove(grupNo1-1); // çünkü listede bir geri gitti her şey
		}
	}
	private void yeniGrubuEkle(ArrayList<Parça> grup1, ArrayList<Parça> grup2) {
		ArrayList<Parça> yeniGrup = new ArrayList<Parça>();
    	yeniGrup.addAll(grup1);
    	yeniGrup.addAll(grup2);
    	parçaGrupları.add(yeniGrup);
	}

	public void ilerle() throws Exception{
		enYakınGruplarıBul();
		yeniGrupOluştur(satırSutun[0], satırSutun[1]);
		DoubleListVektör yeniSatır = uzaklığaYeniSatırHesapla();
		satırSutunSil();
		uzaklık.satırEkle(yeniSatır);
	}
	public String çöz() throws Exception{
		parçaUzaklıkMatrisiniHesapla();
		String çözüm ="";
		çözüm += "aşama 0:\n\n" + yaz() + "\n\n";
		int aşamaNo;
		for(aşamaNo=0;;aşamaNo++){
			olurluEnKüçükUzaklıkBul(); // belki de tüm şartları sağlayan olurlu bir çözüm bulamaz o zaman olurluÇözüm = false olur
			if(olurluÇözüm && uzaklık.satırSay() > 2){ // olurlu bir çözüm varsa bu iyileştirilebilir ya da cevap olurlu çözüm olabilir
				ilerle();
				çözüm += "aşama : "+ aşamaNo + "\n\n" + yaz() + "\n\n"; 
			}
			else{
				break;
			}
		}
		return çözüm;
	}
    public String yaz(){
    	String yazı = "";
    	yazı = parçaGruplarınıYaz(yazı);
    	yazı += "\n\nuzaklık matrisi : \n\n" + uzaklık.yaz('\t') + "\n\n";
    	yazı += "en yakın olanlar : " + satırSutun[0] + " , " + satırSutun[1] + "\n";
    	yazı = kısıtlarıYaz(yazı);
        return yazı;
    }
	private String kısıtlarıYaz(String yazı) {
		yazı = aileSayısıKoşulunuYaz(yazı);
    	yazı = uzaklıkKısıtınıYaz(yazı);
    	yazı = aileBüyüklüğüKısıtınıYaz(yazı);
		return yazı;
	}
	private String aileBüyüklüğüKısıtınıYaz(String yazı) {
		if(enFazlaAileBüyüklüğü == -1){
    		yazı += "aile büyüklüğü kısıtlanmamış\n";
    	}
    	else{
    		yazı += "aile büyüklüğü kısıtı : " + aileBüyüklüğüKoşulu + "\n";
    	}
		return yazı;
	}
	private String uzaklıkKısıtınıYaz(String yazı) {
		if(enDüşükUzaklık == -1){
    		yazı += "uzaklık kısıtlanmamış \n";
    	}
    	else{
    		yazı += "uzaklık kısıtı : " + uzaklıkKoşulu + "\n";
    	}
		return yazı;
	}
	private String aileSayısıKoşulunuYaz(String yazı) {
		if(enAzAileSayısı == -1){
    		yazı += "ailesayısı kısıtlanmamış \n";
    	}
    	else{
    		yazı += "aileSayısı kısıtı : " + enAzAileSayısı + "\n";
    	}
		return yazı;
	}
	private String parçaGruplarınıYaz(String yazı) {
		int grupNo,parçaNo;
    	for(grupNo=0;grupNo<parçaGrupları.size();grupNo++){
    		yazı += "grup : " + grupNo + "\n";
    		for(parçaNo=0;parçaNo<parçaGrupları.get(grupNo).size();parçaNo++){
    			yazı += "parça " + parçaGrupları.get(grupNo).get(parçaNo).yaz() + "\n";
    		}
    		yazı += "------------------\n";
    	}
		return yazı;
	}

}
