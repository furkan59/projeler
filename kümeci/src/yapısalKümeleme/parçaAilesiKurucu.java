package yapýsalKümeleme;
import veriYapýlarý.*;
import java.util.ArrayList;

public class parçaAilesiKurucu {

	ArrayList<ArrayList<Parça>> parçaGruplarý = new ArrayList<ArrayList<Parça>>();
	DoubleListDizi uzaklýk = new DoubleListDizi();
	
	int [] satýrSutun = new int [2]; // satýr , sutun
	
	public double enDüþükUzaklýk = -1;
    public int enAzAileSayýsý = -1;
    public int enFazlaAileBüyüklüðü = -1;
    
    Boolean uzaklýkKoþulu ;
    Boolean aileBüyüklüðüKoþulu ;
    Boolean aileSayýsýKoþulu;
    Boolean olurluÇözüm = true;
    
    double aþamaÝçinEnKüçükUzaklýk;
    /**
     * parça1:1,2,3,34 
     * parça2:3,12,3,4
     * parça3:4,4,2,13
     * @param yazý
     * @throws Exception
     */
    public parçaAilesiKurucu(String  yazý) throws Exception {
		String [] satýrlar = yazý.split("\\n");
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrlar.length;satýrNo++){
			Parça yeniParça = new Parça(satýrlar[satýrNo]);
			ArrayList<Parça> yeniSatýr = new ArrayList<Parça>();
			yeniSatýr.add(yeniParça);
			parçaGruplarý.add(yeniSatýr);
		}
	}
	/**
	 * sadece ilk aþama için yapýlý matris oluþturulduktan sonra zaten hesaplama iþi yok
	 * <br>iki grup benzerliði için küçük olan seçilecek hesaplama yok
	 * @throws Exception
	 */
    public void parçaUzaklýkMatrisiniHesapla() throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<parçaGruplarý.size();satýrNo++){
    		yeniSatýrEkle(satýrNo);
    	}
    }
	private void yeniSatýrEkle(int satýrNo) throws Exception {
		DoubleListVektör yeniSatýr = new DoubleListVektör();
		Parça parça1 = parçaGruplarý.get(satýrNo).get(0); // ilk baþta zaten her grupta bir parça var
		yeniSatýrHesapla(yeniSatýr, parça1, satýrNo);
		uzaklýk.satýrEkle(yeniSatýr);
	}
	private void yeniSatýrHesapla(DoubleListVektör yeniSatýr, Parça parça1,int satýrNo) throws Exception {
		int sutunNo;
		for(sutunNo=0;sutunNo<satýrNo+1;sutunNo++){
			yeniUzaklýkHesapla(sutunNo, yeniSatýr, parça1);
		}
	}
	private void yeniUzaklýkHesapla(int sutunNo, DoubleListVektör yeniSatýr, Parça parça1) throws Exception {
		Parça parça2 = parçaGruplarý.get(sutunNo).get(0); // ilk baþta her grupta bir parça var
		double yeniBenzerlik = parça1.minkowskiUzaklýkBul(parça2);
		yeniSatýr.ekle(yeniBenzerlik);
	}

	private double grupUzaklýkEriþ(int grup1, int grup2){
		if(grup1 > grup2){
			return uzaklýk.eriþ(grup1, grup2);
		}
		else{
			return uzaklýk.eriþ(grup2, grup1);
		}
	}
	// gruplar arasý uzaklýk hesaplama
	/**
	 * grupNo1 grubu ile grupNo2 grubunun parçaNo parçasý arasýndaki uzaklýðý verir
	 * @param grupNo1
	 * @param grupNo2
	 * @param parçaNo
	 * @return
	 * @throws Exception
	 */
	public double parçaGrupUzaklýkHesapla(int grupNo1, int grupNo2, int parçaNo) throws Exception{
		ArrayList<Parça> parça2ninGrubu = parçaGruplarý.get(grupNo2);
		Parça parça = parça2ninGrubu.get(parçaNo);
		ArrayList<Parça> birinciParçaGrubu = parçaGruplarý.get(grupNo1);
		return parçaGrupUzaklýkHesapla(birinciParçaGrubu, parça);
	}
	public double parçaGrupUzaklýkHesapla(int grupNo, Parça parça) throws Exception{
		ArrayList<Parça> grup = parçaGruplarý.get(grupNo);
		return parçaGrupUzaklýkHesapla(grup, parça);
	}
	public double parçaGrupUzaklýkHesapla(ArrayList<Parça> grup, Parça parça) throws Exception{
        double enKüçükUzaklýk = grup.get(0).minkowskiUzaklýkBul(parça); // þimdilik böyle kabul ediyoruz
		int parçaNo;
		for(parçaNo=1;parçaNo<grup.size();parçaNo++){
			Parça grupElemaný = grup.get(parçaNo);
			enKüçükUzaklýk = yeniUzaklýkAra(parça, enKüçükUzaklýk, grupElemaný);
		}
		return enKüçükUzaklýk;
	}
	private double yeniUzaklýkAra(Parça parça, double enKüçükUzaklýk, Parça grupElemaný) throws Exception {
		double yeniUzaklýk = grupElemaný.minkowskiUzaklýkBul(parça);
		if(yeniUzaklýk < enKüçükUzaklýk ){
			enKüçükUzaklýk = yeniUzaklýk;
		}
		return enKüçükUzaklýk;
	}
    public double grupUzaklýkHesapla(int grup1, int grup2) throws Exception{
    	if(grup1 == grup2){
    		return 0.0;
    	}
    	double enKüçükUzaklýk = parçaGrupUzaklýkHesapla(grup1,grup2,0); // ilk grup ile ikinci grubun 0. parçasý arasýndaki uzaklýk
    	int grup1No;
    	for(grup1No=1;grup1No<parçaGruplarý.get(grup1).size();grup1No++){
    		double yeniUzaklýk = parçaGrupUzaklýkHesapla(grup1, grup2, grup1No);
    		if(yeniUzaklýk < enKüçükUzaklýk){
    			enKüçükUzaklýk = yeniUzaklýk;
    		}
    	}
    	return enKüçükUzaklýk;
    }
	
    /**
     * geçmiþteki verilerden yaralanarak yeni oluþacak
	 * <br>grubun hazýrlanmasý içn gerekli uzaklýk bilgisini bulur
	 */
    private double grupUzaklýkEriþ(int grupOlacak1, int grupOlacak2, int olanGrup){
    	double uzaklýk1 = grupUzaklýkEriþ(grupOlacak2, olanGrup);
    	double uzaklýk2 = grupUzaklýkEriþ(grupOlacak1, olanGrup);
    	return küçüðüDön(uzaklýk1, uzaklýk2);
    }
	private double küçüðüDön(double uzaklýk1, double uzaklýk2) {
		if(uzaklýk1 < uzaklýk2){
    		return uzaklýk1;
    	}
    	else{
    		return uzaklýk2;
    	}
	}
    
	
	// gruplar arasý uzaklýk hesaplama
    // en yakýn gruplarý bulma
    public void enYakýnGruplarýBul() throws Exception{
    	olurluEnKüçükUzaklýkBul();
    	if(olurluÇözüm == true){ // olurlu çözüm bulundu ise taramaya decam edilecek
        	enKüçükUzaklýðýnYeriniBul();
    	}
    }
	private void enKüçükUzaklýðýnYeriniBul() throws Exception {
		int grupNo1;
		for(grupNo1=0;grupNo1<parçaGruplarý.size();grupNo1++){
			grup1ÝçinEnKüçükUzaklýkBul(grupNo1);
		}
	}
    private void grup1ÝçinEnKüçükUzaklýkBul(int grupNo1) throws Exception{
    	int grupNo2;
    	for(grupNo2=grupNo1+1;grupNo2<parçaGruplarý.size();grupNo2++){
			olurluEnKüçükUzaklýkBul(grupNo1, grupNo2);
		}
    }
	private void olurluEnKüçükUzaklýkBul(int grupNo1, int grupNo2) throws Exception {
		aþamaBaþýKoþullarýnÝlkDeðerleriniVer(); // tüm deðiþkenlere true verir, çünkü kýsýt yoksa koþul true olur
		tümKoþullaraBak(grupNo1, grupNo2); // olurlu çözüm deðiþkenini hesaplar
		if(olurluÇözüm == true){ // olurlu çözüm bulundu ise en yakýn uzaklýk bulunmaya çalýþýlýr
			yeniGrubUzaklýðýKýyasla(grupNo1, grupNo2);	
		}
	}
	private void yeniGrubUzaklýðýKýyasla(int grupNo1, int grupNo2) throws Exception {
		double yeniUzaklýk = grupUzaklýkHesapla(grupNo1, grupNo2); // olurlu çift bulunmalý
		if(yeniUzaklýk < aþamaÝçinEnKüçükUzaklýk){
			aþamaÝçinEnKüçükUzaklýk = yeniUzaklýk;
			// küçük olan satýr olacak grupNo1 daha küçük olacaðýndan
			satýrSutun[0] = grupNo1;
			satýrSutun[1] = grupNo2;
		}
	}
   
	// en yakýn gruplarý bulma
	
	// olurlu uzaklýk bulma
    private void olurluEnKüçükUzaklýkBul() throws Exception{
    	int grupNo1;
    	for(grupNo1=0;grupNo1<parçaGruplarý.size();grupNo1++){
    		grup1ÝçinOlurluDurumAra(grupNo1);
    		if(olurluÇözüm == true){ // üstteki döngüde bir tane olurlu çözüm bulunmuþ demektir
    			break;
    		}
    	}
    }
	private void grup1ÝçinOlurluDurumAra(int grupNo1) throws Exception {
		int grupNo2;
		for(grupNo2=grupNo1+1;grupNo2<parçaGruplarý.size();grupNo2++){
			aþamaBaþýKoþullarýnÝlkDeðerleriniVer();
			tümKoþullaraBak(grupNo1, grupNo2);
			if( olurluÇözüm  == true){ // bir tane olurlu çözüm bulundu demektir
				satýrSutun [0] = grupNo1;
				satýrSutun [1] = grupNo2;
				aþamaÝçinEnKüçükUzaklýk = grupUzaklýkHesapla(grupNo1, grupNo2);
				break;
			}
		}
	}
	private void tümKoþullaraBak(int grupNo1, int grupNo2) throws Exception {
		if(grupNo1 == grupNo2){ // ayný grup ise zaten uzaklýk sýfýrdýr ama bu en küçük uzaklýða aday olamaz
			olurluÇözüm = false;
		}
		else{
			uzaklýkKoþulu(grupNo1, grupNo2);
			aileBüyüklüðüKoþulu(grupNo1, grupNo2);
			aileSayýsýKoþulu();
			olurluÇözüm =  uzaklýkKoþulu && aileBüyüklüðüKoþulu && aileSayýsýKoþulu;
		}
	}
	private void aileSayýsýKoþulu() {
		if(enAzAileSayýsý != -1){
			aileSayýsýnaBak();
		}
	}
	private void aileBüyüklüðüKoþulu(int grupNo1, int grupNo2) {
		if(enFazlaAileBüyüklüðü != -1){
			aileBüyüklüðüKoþulunaBak(grupNo1, grupNo2);
		}
	}
	private void uzaklýkKoþulu(int grupNo1, int grupNo2) throws Exception {
		if(enDüþükUzaklýk != -1){
			olurluUzaklýkBak(grupNo1, grupNo2);
		}
	}
	
    private void olurluUzaklýkBak(int grupNo1, int grupNo2) throws Exception {
		double yeniUzaklýk = grupUzaklýkHesapla(grupNo1, grupNo2);
		if(yeniUzaklýk >= enDüþükUzaklýk){
			satýrSutun [0] = grupNo1;
			satýrSutun [1] = grupNo2;
			uzaklýkKoþulu =  true;
		}
		else{
			uzaklýkKoþulu =  false;
		}
	}
	private void aileBüyüklüðüKoþulunaBak(int grupNo1, int grupNo2){
		int aileBüyüklüðü1 = parçaGruplarý.get(grupNo1).size();
		int aileBüyüklüðü2 = parçaGruplarý.get(grupNo2).size();
		if(aileBüyüklüðü1 + aileBüyüklüðü2 <= enFazlaAileBüyüklüðü){
			aileBüyüklüðüKoþulu = true;
		}
		else{
			aileBüyüklüðüKoþulu = false;
		}
	}
    private void aileSayýsýnaBak(){
    	int toplamAileSayýsý = parçaGruplarý.size();
    	if(toplamAileSayýsý -1 < enAzAileSayýsý){
    		aileSayýsýKoþulu = false; 
    	}
    	else{
    		aileSayýsýKoþulu = true;
    	}
    }
    /**
     * tüm koþullar geçti kabule edecek
     * <br>çünkü bu koþulun olup olmadýðýný bimiyoruz
     * <br>eðer koþul konmamýþsa zaten bir sonraki aþamaya geçmeyi engellemez
     */
    private void aþamaBaþýKoþullarýnÝlkDeðerleriniVer(){
    	uzaklýkKoþulu = true;
        aileBüyüklüðüKoþulu = true;
        aileSayýsýKoþulu = true;
    }

    // olurlu uzaklýk bulma
    
    private DoubleListVektör uzaklýðaYeniSatýrHesapla(){
    	DoubleListVektör yeniSatýr = new DoubleListVektör();
    	// yeni gruplananlar için yeni satýr oluþturulacak
    	int parçaNo;
    	for(parçaNo=0;parçaNo<uzaklýk.satýrSay();parçaNo++){
    		if(parçaNo != satýrSutun[0] && parçaNo != satýrSutun[1]){
    			double yeniUzaklýk = grupUzaklýkEriþ(satýrSutun[0],satýrSutun[1],parçaNo);
    			yeniSatýr.ekle(yeniUzaklýk);
    		}
    	}
    	yeniSatýr.ekle(0);
    	return yeniSatýr;
    }
    private void satýrSutunSil(){
    	if(satýrSutun[0] < satýrSutun[1]){
    		uzaklýk.satýrSutunSil(satýrSutun[0], satýrSutun[0]);
        	uzaklýk.satýrSutunSil(satýrSutun[1]-1, satýrSutun[1]-1);
    	}
    	else{
    		uzaklýk.satýrSutunSil(satýrSutun[1], satýrSutun[1]);
        	uzaklýk.satýrSutunSil(satýrSutun[0]-1, satýrSutun[0]-1);
    	}
    }
    
    private void yeniGrupOluþtur(int grupNo1, int grupNo2) throws Exception{
    	if(grupNo1 == grupNo2){
    		throw new Exception("bunlar ayný grup " + grupNo1);
    	}
    	ArrayList<Parça> grup1 = parçaGruplarý.get(grupNo1);
    	ArrayList<Parça> grup2 = parçaGruplarý.get(grupNo2);
    	yeniGrubuEkle(grup1, grup2);
    	eskiGruplarýSil(grupNo1, grupNo2);
    }
	private void eskiGruplarýSil(int grupNo1, int grupNo2) {
		if(grupNo1 < grupNo2){
			parçaGruplarý.remove(grupNo1); // önce küçük olan
	    	parçaGruplarý.remove(grupNo2-1); // çünkü listede bir geri gitti her þey
		}
		else{
			parçaGruplarý.remove(grupNo2); // önce küçük olan
	    	parçaGruplarý.remove(grupNo1-1); // çünkü listede bir geri gitti her þey
		}
	}
	private void yeniGrubuEkle(ArrayList<Parça> grup1, ArrayList<Parça> grup2) {
		ArrayList<Parça> yeniGrup = new ArrayList<Parça>();
    	yeniGrup.addAll(grup1);
    	yeniGrup.addAll(grup2);
    	parçaGruplarý.add(yeniGrup);
	}

	public void ilerle() throws Exception{
		enYakýnGruplarýBul();
		yeniGrupOluþtur(satýrSutun[0], satýrSutun[1]);
		DoubleListVektör yeniSatýr = uzaklýðaYeniSatýrHesapla();
		satýrSutunSil();
		uzaklýk.satýrEkle(yeniSatýr);
	}
	public String çöz() throws Exception{
		parçaUzaklýkMatrisiniHesapla();
		String çözüm ="";
		çözüm += "aþama 0:\n\n" + yaz() + "\n\n";
		int aþamaNo;
		for(aþamaNo=0;;aþamaNo++){
			olurluEnKüçükUzaklýkBul(); // belki de tüm þartlarý saðlayan olurlu bir çözüm bulamaz o zaman olurluÇözüm = false olur
			if(olurluÇözüm && uzaklýk.satýrSay() > 2){ // olurlu bir çözüm varsa bu iyileþtirilebilir ya da cevap olurlu çözüm olabilir
				ilerle();
				çözüm += "aþama : "+ aþamaNo + "\n\n" + yaz() + "\n\n"; 
			}
			else{
				break;
			}
		}
		return çözüm;
	}
    public String yaz(){
    	String yazý = "";
    	yazý = parçaGruplarýnýYaz(yazý);
    	yazý += "\n\nuzaklýk matrisi : \n\n" + uzaklýk.yaz('\t') + "\n\n";
    	yazý += "en yakýn olanlar : " + satýrSutun[0] + " , " + satýrSutun[1] + "\n";
    	yazý = kýsýtlarýYaz(yazý);
        return yazý;
    }
	private String kýsýtlarýYaz(String yazý) {
		yazý = aileSayýsýKoþulunuYaz(yazý);
    	yazý = uzaklýkKýsýtýnýYaz(yazý);
    	yazý = aileBüyüklüðüKýsýtýnýYaz(yazý);
		return yazý;
	}
	private String aileBüyüklüðüKýsýtýnýYaz(String yazý) {
		if(enFazlaAileBüyüklüðü == -1){
    		yazý += "aile büyüklüðü kýsýtlanmamýþ\n";
    	}
    	else{
    		yazý += "aile büyüklüðü kýsýtý : " + aileBüyüklüðüKoþulu + "\n";
    	}
		return yazý;
	}
	private String uzaklýkKýsýtýnýYaz(String yazý) {
		if(enDüþükUzaklýk == -1){
    		yazý += "uzaklýk kýsýtlanmamýþ \n";
    	}
    	else{
    		yazý += "uzaklýk kýsýtý : " + uzaklýkKoþulu + "\n";
    	}
		return yazý;
	}
	private String aileSayýsýKoþulunuYaz(String yazý) {
		if(enAzAileSayýsý == -1){
    		yazý += "ailesayýsý kýsýtlanmamýþ \n";
    	}
    	else{
    		yazý += "aileSayýsý kýsýtý : " + enAzAileSayýsý + "\n";
    	}
		return yazý;
	}
	private String parçaGruplarýnýYaz(String yazý) {
		int grupNo,parçaNo;
    	for(grupNo=0;grupNo<parçaGruplarý.size();grupNo++){
    		yazý += "grup : " + grupNo + "\n";
    		for(parçaNo=0;parçaNo<parçaGruplarý.get(grupNo).size();parçaNo++){
    			yazý += "parça " + parçaGruplarý.get(grupNo).get(parçaNo).yaz() + "\n";
    		}
    		yazý += "------------------\n";
    	}
		return yazý;
	}

}
