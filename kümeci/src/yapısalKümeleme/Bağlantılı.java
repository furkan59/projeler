package yapýsalKümeleme;
import veriYapýlarý.*;

import java.util.ArrayList;

import SýkýþtýrýlmýþVeriYapýlarý.EksikBoolDizi;
import kümeci.Kümeleme;
/**
 * @author Furkan
 */

public class Baðlantýlý extends Kümeleme{

    DoubleListDizi makinelerBenzerlik = new DoubleListDizi(); // bilgilerin saklanacaðý matris
    public double eþikDeðer = -1;
    public double eþikDeðerÇarpaný=1; // her aþamada eþik deðer ne kadar azalacak (þimdilik böyle olsun)
    public int gruptakiEnFazlaMakineSayýsý =-1 ;
    public int enAzGrupSayýsý = -1;
    public String çözüm = "";
    
    // her aþama için yenilenmeli
    int grup1,grup2;
    double enBüyükBenzerlik;
    double yeniBenzerlik ;
    
    Boolean eþikKoþulu,makineSayýsýKoþulu;
    // her aþama için yenilenmeli
    // hep sabit
    int [] makinedeÝþlenenParçaSayýsý = new int [makineSayýsý()];
    int [][] makineOrtakParçalar = new int [makineSayýsý()][];
    EksikBoolDizi eksikParçaMakine = new EksikBoolDizi(parçaMakine);
    // hep sabit
    long baþ= System.currentTimeMillis(),son;
    // {{ kurucular
	public Baðlantýlý(BoolDizi parçaMakine, String[] makineÝsimleri,String[] parçaÝsimleri) throws Exception {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
		makineMatrisleriniDoldur();
		makineGruplarýOluþtur();
	}
	public Baðlantýlý(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
		makineMatrisleriniDoldur();
		makineGruplarýOluþtur();
	}
	public Baðlantýlý(String yazý) throws Exception {
		super(yazý);
		makineMatrisleriniDoldur();
		makineGruplarýOluþtur();
	}
	public void eþikDeðerBelirle(double eþikDeðer){
    	this.eþikDeðer = eþikDeðer;
    }
	// }} kurucular
    
	// {{ makineler 
    private void makinedeÝþlenenParçalarýDoldur () throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<makineSayýsý();makineNo++){
    		makinedeÝþlenenParçaSayýsý [makineNo] = makinedeÝþlenenParçaSayýsýnýBul(makineNo);
    	}
    }
    private void makineOrtakParçalarýBul () throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<makineSayýsý();satýrNo++){
    		makineOrtakParçalar [satýrNo] = new int [satýrNo+1];
    		for(int elemanNo = 0;elemanNo<makineOrtakParçalar [satýrNo].length;elemanNo++){
    			makineOrtakParçalar [satýrNo][elemanNo] = makineOrtakParçalar2(satýrNo, elemanNo);
    		}
    	}
    }
    protected int makineOrtakParçaEriþ(int makine1,int makine2){
    	if(makine1 < makine2){
    		return makineOrtakParçalar[makine2][makine1];}
    	else
    	{
    		return makineOrtakParçalar[makine1][makine2];}
    }
    
    private void makineMatrisleriniDoldur() throws Exception{
    	makinedeÝþlenenParçalarýDoldur();
    	makineOrtakParçalarýBul();
    }
    
    public void makineBenzerlikMatrsiniHesapla() throws Exception{
		int makine1;
		for(makine1=0;makine1<makineÝsimleri.length;makine1++){
			makineSatýrýnBenzerlikHesapla(makine1);
		}
	}
	private void makineSatýrýnBenzerlikHesapla(int makine1) throws Exception {
		DoubleListVektör yeniSatýr = new DoubleListVektör();
		int makine2;
		for(makine2=0;makine2<makine1+1;makine2++){
			double benzerlikKatsayýsý = makineBenzerlikHesapla(makine1, makine2);
			yeniSatýr.ekle(benzerlikKatsayýsý);
		}
	    makinelerBenzerlik.satýrEkle(yeniSatýr);
	}
	private double makineBenzerlikHesapla(int makine1, int makine2) throws Exception{
		if(makine1 == makine2){
			return 0.0; // ayný makineleri gruplamasýn diye
		}
		else{
			double makine1deÝþlenen = makinedeÝþlenenParçaSayýsý [makine1];
			double makine2deÝþlenen = makinedeÝþlenenParçaSayýsý [makine2];
			double ikisindeÝþlenen = makineOrtakParçaEriþ(makine1, makine2);
			double payda = makine1deÝþlenen + makine2deÝþlenen - ikisindeÝþlenen;
			return ikisindeÝþlenen / payda;
		}
	}
    private int makinedeÝþlenenParçaSayýsýnýBul(int makineNo) throws Exception{
		makineKontrolEt(makineNo);
		int sutunNo,parçaSayýsý=0;
		for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
			if(parçaMakine.eriþ(makineNo, sutunNo) == true){
				parçaSayýsý ++;
			}
		}
		return parçaSayýsý;
	}
	private int makineOrtakParçalar(int makine1,int makine2) throws Exception{
		makineKontrolEt(makine1);
		makineKontrolEt(makine2);
		int ortakParça = 0;
		int sutunNo;
		for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
			if(parçaMakine.eriþ(makine2, sutunNo) && parçaMakine.eriþ(makine1, sutunNo) ){
				ortakParça++;
			}
		}
		return ortakParça;
	}
	
    private void makineKontrolEt(int makine1) throws Exception {
		if(makine1<0 || makine1 > makineÝsimleri.length-1){
			throw new Exception("girilen makine 1 geçersiz makine1 = " +makine1 + " toplam makine sayýsý = " + makineÝsimleri.length + "\n");
		}
	}
    
    protected double makineBenzerlikBul(int makine1, int makine2) throws Exception{
		if(makine1 > makine2){ // büyük olan satýr olsun
			return makinelerBenzerlik.eriþ(makine1, makine2);
		}
		else{
			return makinelerBenzerlik.eriþ(makine2, makine1);
		}	
	}
    /**
     * eksik matrisi kullanarak benzerlik bulur
     * @param makine1
     * @param makine2
     * @return
     * @throws Exception
     */
    private int makineOrtakParçalar2(int makine1,int makine2) throws Exception{
		makineKontrolEt(makine1);
		makineKontrolEt(makine2);
		int ortakParça = 0;
		int [] kesiþim = eksikParçaMakine.kesiþim(makine1, makine2);
		return kesiþimeBak(makine1, makine2, ortakParça, kesiþim);
	}
	private int kesiþimeBak(int makine1, int makine2, int ortakParça,int[] kesiþim) throws Exception {
		if(kesiþim[0] == -1){
			return 0;
		}
		else {
			return aralýkTara(makine1, makine2, ortakParça, kesiþim);
		}
	}
    // }} makineler 
	private int aralýkTara(int makine1, int makine2, int ortakParça,int[] kesiþim) throws Exception {
		int sutunNo;
		for(sutunNo=kesiþim[0];sutunNo<kesiþim[1];sutunNo++){
			if(parçaMakine.eriþ(makine2, sutunNo) && parçaMakine.eriþ(makine1, sutunNo) ){
				ortakParça++;
			}
		}
		return ortakParça;
	}
    
    // {{ gruplar
    protected double grupBenzerlikBul(int grup1, int grup2){
    	if(grup1 > grup2){
    		return makinelerBenzerlik.eriþ(grup1, grup2);
    	}
    	else{
    		return makinelerBenzerlik.eriþ(grup2, grup1);
    	}
    }
    
    protected int [] enÇokBenzeyenleriBul(){
    	return makinelerBenzerlik.enBüyüðünYeri();
    }
    /**
     * makine ve ya makine gruplarýný birleþtirir
     * @param grup1
     * @param grup2
     * @throws Exception
     */
    public void grupla() throws Exception{
    	gruplarýBirleþtir(grup1, grup2);
    	// þimdi benzerlik matrisi daralmalý
    	makinelerBenzerlik.satýrSutunSil(grup1, grup1);
    	makinelerBenzerlik.satýrSutunSil(grup2, grup2);
    }

    
	private Boolean grupSayýsýKoþulu(){
		if(enAzGrupSayýsý != -1){
			return grupSayýsýnaBak();
		}
		return true;
	}
	/**
	 * eðer bu iki grup birleþirse makinesayýsý koþulu saðlanacak mý ona bakar
	 * <br>bu iþlemi gerekli ise yapar
	 * @param grup1
	 * @param grup2
	 * @return
	 */
	private Boolean makineSayýsýKoþulu(int grup1, int grup2) {
		if(gruptakiEnFazlaMakineSayýsý != -1){
			ArrayList<Integer> birinciGrup = makineGruplarý.get(grup1);
    	    ArrayList<Integer> ikinciGrup = makineGruplarý.get(grup2);
    	    if(birinciGrup.size() + ikinciGrup.size() > gruptakiEnFazlaMakineSayýsý){
    	    	return  false;
    	    }
		}
		return true;
	}
	/**
	 * diyelim ki en fazla 4 grup istiyoruz 4 grup var demek ki biz devam etmesine izin vermicez 
	 * <br> çünkü devam ederse 3 grup olacaktýr
	 * @return
	 */
	private Boolean grupSayýsýnaBak() {
		if(makineGruplarý.size() <= enAzGrupSayýsý){ 
			return false;
		}
		else{
			return true;
		}
	}
	
	// }} gruplar
	
	// {{ kontrol
    protected DoubleListVektör yeniSatýrHazýrla() throws Exception{
    	DoubleListVektör yeniSatýr = new DoubleListVektör();
    	int [] küçükBüyük = new int [2];
    	küçükBüyükBelirle(küçükBüyük);
    	benzerlikleriBul(yeniSatýr, küçükBüyük);
    	yeniSatýr.ekle(0.0);
    	return yeniSatýr;
    }
	private void benzerlikleriBul( DoubleListVektör yeniSatýr, int[] küçükBüyük) {
		int grupSayýsý = makineGruplarý.size();
		int grupNo;
    	grupNo = küçüðeKadarOlanBenzerlikleriBul(yeniSatýr, küçükBüyük);
    	grupNo++; // küçüðü atladýk þimdi de büyüðü atlicaz
    	grupNo = büyüðeKadarBenzerlikleriBul( yeniSatýr, grupNo, küçükBüyük);
    	grupNo++; // büyüðü atladýk
    	grupNo = büyüktenSonrasýnýBul(yeniSatýr, grupNo,grupSayýsý);
	}
	private int büyüktenSonrasýnýBul(DoubleListVektör yeniSatýr, int grupNo, int grupSayýsý) {
		for(;grupNo<grupSayýsý;grupNo++){
    		double yeniBenzerlik = yeniBenzerlikHesapla(grupNo);
    	    yeniSatýr.ekle(yeniBenzerlik);
    	}
		return grupNo;
	}
	private int büyüðeKadarBenzerlikleriBul( DoubleListVektör yeniSatýr, int grupNo, int[] küçükBüyük) {
		for(;grupNo<küçükBüyük[1];grupNo++){
			double yeniBenzerlik = yeniBenzerlikHesapla(grupNo);
    	    yeniSatýr.ekle(yeniBenzerlik);
    	}
		return grupNo;
	}
	private int küçüðeKadarOlanBenzerlikleriBul(DoubleListVektör yeniSatýr, int[] küçükBüyük) {
		int grupNo;
		for(grupNo = 0;grupNo<küçükBüyük[0];grupNo++){
			double yeniBenzerlik = yeniBenzerlikHesapla(grupNo);
    	    yeniSatýr.ekle(yeniBenzerlik);
    	}
		return grupNo;
	}
	private void küçükBüyükBelirle(int[] küçükBüyük) {
		if(grup1 < grup2){
    		küçükBüyük[0] = grup1;
    		küçükBüyük[1] = grup2;
    	}
    	else{
    		küçükBüyük[0] = grup2;
    		küçükBüyük[1] = grup1;
    	}
	}
	protected double yeniBenzerlikHesapla(int grupNo) {
		return -10000;
	}
	public void ilerle() throws Exception{
		adayGruplarýYaz();
		DoubleListVektör yeniSatýr = yeniSatýrHazýrla();
		grupla();
		makinelerBenzerlik.satýrEkle(yeniSatýr);
		çözüm += "yeni parça makine matrisi = \n" + yeniParçaMakineMatrisiYap().baþlýklýYaz(makineÝsimleriSýralý(), parçaÝsimleri) + "\n\n";
	}
	/**
	 * <br>tüm kýsýtlarý saðlayan uygun olurlu gruplar bulunduktan sonra bunlarýn arasýndan en iyi iki grup bulunacak
	   <br>olurlu grup olabilmek için
	   <br>koþul 1 : iki grubun benzerliði eþik deðerden fazla olmalý
	   <br>koþul 2 : iki grup birleþtirildiðinde gruptaki makine sayýsý en fazla makine sayýsýný aþmamalý
	   <br>koþul 3 : iki grup birleþince toplam grup sayýsýný aþmamalý
	   <br>eðer uygun gruplar bulunamazsa iki grup da -1 döner
	 */
	public void satýrSutunBul() {
        aþamaDeðiþkenleriHazýrla();
		int birinciGrup;
		for(birinciGrup=0;birinciGrup<makinelerBenzerlik.satýrSay();birinciGrup++){
			satýrdaOlurluGrupAra(birinciGrup);
		}
	}
	private void aþamaDeðiþkenleriHazýrla() {
        grup1 = -1;
        grup2 = -1;
        enBüyükBenzerlik = -1;
        makineSayýsýKoþulunuBaþlat();
        eþikKoþulunuBaþlat();
	}
	private void makineSayýsýKoþulunuBaþlat() {
		if(gruptakiEnFazlaMakineSayýsý != -1){
        	makineSayýsýKoþulu = false;
        }
        else{
        	makineSayýsýKoþulu = true;
        }
	}
	private void eþikKoþulunuBaþlat() {
		if(eþikDeðer != -1){ 
        	eþikKoþulu = false;	// kýsýtlama getirilmiþ o zaman ilk deðeri false olsun gerekirse kod onu true yapar
        }
        else{
        	eþikKoþulu = true; // zaten kýsýtlama getirilmemiþ
        }
	}
	
	
    private void satýrdaOlurluGrupAra(int birinciGrup) {
		int ikinciGrup;
		DoubleListVektör satýr = makinelerBenzerlik.dizi.get(birinciGrup);
		for(ikinciGrup=0;ikinciGrup<satýr.boy();ikinciGrup++){ 
			yeniBenzerlikAra(birinciGrup, ikinciGrup);
		}
	}
	private void yeniBenzerlikAra(int birinciGrup, int ikinciGrup) {
		yeniBenzerlik = grupBenzerlikBul(birinciGrup, ikinciGrup);
		if(yeniBenzerlik > enBüyükBenzerlik){ // eðer yeni durum daha 
			// iyi ise olur mu ona bakýlsýn çünkü iyi olup olmadaðýna bakmak kolay. Gereksiz iþlemden kurtulmuþ oluruz
			if( grupOlurluMu(birinciGrup, ikinciGrup)  ){ // hem olurlu hem de iyi bir durum olmasý gerekir
				yeniSatýrSutunBul(birinciGrup, ikinciGrup);
			}	
		}
	}
	/**
	 * eðer buraya gelindi ise demek ki ilerlenebinir
	 * @param birinciGrup
	 * @param ikinciGrup
	 */
	private void yeniSatýrSutunBul(int birinciGrup, int ikinciGrup) {
			grup1 = birinciGrup;
			grup2 = ikinciGrup;	
			enBüyükBenzerlik = yeniBenzerlik;
	}
	private Boolean grupOlurluMu(int satýrNo, int sutunNo) {
		makineSayýsýKoþulu = makineSayýsýKoþulu(satýrNo, sutunNo);
		eþikKoþulunaBak();
		return ( makineSayýsýKoþulu && eþikKoþulu && eþikKoþulu);
	}
	private void eþikKoþulunaBak() {
		if(eþikKoþulu == false){ // false ise true yapmaya çalýþ ama true ise dokunma demek ki koþul saðlanmýþ çünkü
			eþikKoþulu = yeniBenzerlik > eþikDeðer;
		}
	}
   
	// }} kontrol
    
	// {{ yazdýrma 
    private void adayGruplarýYaz() {
		çözüm += "satýr = " + grup1 + " sutun : " + grup2 + "\n";
		çözüm += "grup1 : " + makineGruplarý.get(grup1) + " grup 2 :" + makineGruplarý.get(grup2) +"\n";
	}
	
	public String çöz() throws Exception{
		makineBenzerlikMatrsiniHesapla();
		int aþamaNo=1;
		aþamaDeðiþkenleriHazýrla();
		çözüm += super.yaz();
		çözüm += "aþamaNo = 0" + "\n\n" + yaz()  + "\n\n";
		for(;;aþamaNo++){
			if(grupSayýsýKoþulu()==false){
				break;
			}
			satýrSutunBul();
			if(eþikKoþulu && makineSayýsýKoþulu && enBüyükBenzerlik != 0){
				ilerle();
				çözüm += "aþamaNo = " + aþamaNo + "\n\n" + yaz() + "\n\n";
			}
			else{ // eþik ve makineSayýsýndan geçemedi
				break;
			}
				
		}
		son = System.currentTimeMillis();
		çözüm += "çözüm süresi : " + (son -baþ) + " milisaniye\n\n";
		// þimdi burda satýrlarýn sýralamasýný bulduk þimdi de sutunlarýn sýrasýný bulmak için derece sýralama algoritmasý kullancaz
		// ama önce parça makine matrisinin düzenlenmesi gerekiyor
		
		return çözüm;
	}
	
    @Override
    public String  yaz() throws Exception{
    	String  yazý ="";// super.yaz() + "\n\n";
    	//yazý += "grup1 = " + grup1 + " grup2 : "  + grup2;
    	yazý = kararVericiDeðerleriYaz(yazý);
    	yazý = benzerliðiYaz(yazý) + "\n\n";
    	//yazý += çözüm + "\n\n";
    	return yazý;
    }
	private String benzerliðiYaz(String yazý) {
		yazý += "\t";
		yazý = ilkSatýrýYaz(yazý);
    	yazý = benzerliðinAltKýsmýYaz(yazý);
		return yazý;
	}
	private String benzerliðinAltKýsmýYaz(String yazý) {
		int makineNo;
		yazý += "\n";
    	for(makineNo=0;makineNo<makineGruplarý.size();makineNo++){
    		yazý += makineGruplarý.get(makineNo) + 
    	            "\t" + makinelerBenzerlik.dizi.get(makineNo).yaz('\t') + "\n";
    	}
		return yazý;
	}
	private String ilkSatýrýYaz(String yazý) {
		int makineNo;
    	for(makineNo=0;makineNo<makineGruplarý.size();makineNo++){
    		yazý += makineGruplarý.get(makineNo) + "\t";
    	}
		return yazý;
	}
	private String kararVericiDeðerleriYaz(String yazý) {
		if(eþikDeðer != -1){
    		yazý += "eþik deðer : " + eþikDeðer +"\n";
    		yazý += "þart saðlandý mý = " + eþikKoþulu +"\n"; 
    	}
    	if(gruptakiEnFazlaMakineSayýsý != -1){
    		yazý += "gruptaki en fazla makine sayýsý = " + gruptakiEnFazlaMakineSayýsý + "\n";
    		yazý += "þart saðlandý mý = " + makineSayýsýKoþulu + "\n";
    	}
    	if(enAzGrupSayýsý != -1){
    		yazý += "en az grup sayýsý : " + enAzGrupSayýsý + "\n";
    		yazý += "þart saðlandý mý = " + grupSayýsýKoþulu() + "\n";
    		yazý += "grup sayýsý = " + makineGruplarý.size() + "\n";
    	}
		return yazý;
	}
    
	// }} yazdýrma 
    public String hýzlýÇöz() throws Exception{
    	String çözüm = "";
    	makineBenzerlikMatrsiniHesapla();
		int aþamaNo=1;
		aþamaDeðiþkenleriHazýrla();
		for(;;aþamaNo++){
			if(grupSayýsýKoþulu()==false){
				break;
			}
			satýrSutunBul();
			if(eþikKoþulu && makineSayýsýKoþulu && enBüyükBenzerlik != 0){
				hýzlýÝlerle();
			}
			else{ // eþik ve makineSayýsýndan geçemedi
				break;
			}
		}
		çözüm += "son parça makine matrisi : \n" + yeniParçaMakineMatrisiYaz2() + "\n\n";
		çözüm += "kümeler : \n" + makineGruplarýnýYaz() + "\n\n";
		son = System.currentTimeMillis();
		çözüm += "çözüm süresi : " + (son -baþ) + " milisaniye\n\n";
        // þimdi burda satýrlarýn sýralamasýný bulduk þimdi de sutunlarýn sýrasýný bulmak için derece sýralama algoritmasý kullancaz
		// ama önce parça makine matrisinin düzenlenmesi gerekiyor
		if(dendogramVarMý){
			dendogramçiz();
		}
		return çözüm;
    }
    private void hýzlýÝlerle() throws Exception{
    	adayGruplarýYaz();
		DoubleListVektör yeniSatýr = yeniSatýrHazýrla();
		grupla();
		makinelerBenzerlik.satýrEkle(yeniSatýr);
    }

}
