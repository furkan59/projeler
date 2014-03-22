package metaSezgisel;
import java.util.ArrayList;

import kümeci.Kümeleme;
import veriYapýlarý.*;
public class ParçacýkSürü extends Kümeleme {

	
	public int parçacýkSayýsý;
	public int kümeSayýsý;
	private Parçacýk [] parçacýklar;
	
	// güven parametreleri belirlenmediði zaman parçalar kendi güven deðerlini kullanýr
	
	protected int sürüBaþý=-1;
	public long çözümZamaný;
	
	public int enFazlaAþama;
	public int enFazlaÝlerleme;
	public boolean ilerlemeKýsýtlý = true;
	
	public double duyarlýlýk = 0.00000000001;
	public ParçacýkSürü(BoolDizi parçaMakine, String[] makineÝsimleri,String[] parçaÝsimleri) {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
		makineGruplarýOluþtur();
	}
    public ParçacýkSürü(String yazý, char ayýrýcý,int kümeSayýsý,int parçacýkSayýsý) throws Exception {
		super(yazý, ayýrýcý);
		this.kümeSayýsý = kümeSayýsý;
    	this.parçacýkSayýsý = parçacýkSayýsý;
    	parçacýklarýOluþtur();
	}
	public ParçacýkSürü(String yazý,int kümeSayýsý,int parçacýkSayýsý) throws Exception {
		super(yazý);
		this.kümeSayýsý = kümeSayýsý;
    	this.parçacýkSayýsý = parçacýkSayýsý;
    	parçacýklarýOluþtur();
	}
         
	private void parçacýklarýOluþtur() throws Exception {
    	parçacýklar = new Parçacýk [this.parçacýkSayýsý];
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýklar[parçacýkNo] = new Parçacýk(kümeSayýsý,parçaMakine);
    	}
	}
    public void güvenParameterileriniBelirle(double sürüyeGüven, double kendineGüven , double eylemsizlik ){
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýklar[parçacýkNo].güvenParameterileriniBelirle(sürüyeGüven, kendineGüven, eylemsizlik);
    	}
    }
    public void ilkHýzBelirle(double ilkHýz) throws Exception{
    	ilkHýzKontrol(ilkHýz);
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýklar[parçacýkNo].ilkHýzBelirle(ilkHýz);
    	}
    }
	private void ilkHýzKontrol(double ilkHýz) throws Exception {
		if(ilkHýz < 0 ){
    		throw new Exception("ilk hýz sýfýrdan küçük olamaz girlen ilk hýz :" + ilkHýz + "\n");
    	}
	}

	
	public void sürününBaþýnýBul() throws Exception{
		sürüBaþý = 0;
		int parçacýkNo;
		for(parçacýkNo=1;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
			sürüBaþýnýGüncelle(parçacýkNo);
		}
	}
	private void sürüBaþýnýGüncelle(int parçacýkNo) throws Exception {
		double yeniAmaç = parçacýklar[parçacýkNo].amaçDeðeriHesapla();
		if(yeniAmaç > parçacýklar[sürüBaþý].amaçDeðeri){
			sürüBaþý = parçacýkNo;
		}
	}
    
	private void parçaNoKontrolEt(int parçaNo) throws Exception {
		if(parçaNo > parçacýkSayýsý){
    		throw new Exception("parçano parçacýk sayýsýndan fazla olamaz parçaNo : " + parçaNo + " parçacýk sayýsý : " + parçacýkSayýsý + "\n");
    	}
	}
    private void parçacýkKonumuGüncelle(int parçacýkNo) throws Exception{
    	parçaNoKontrolEt(parçacýkNo);
        Parçacýk sürüÖnderi = sürüÖnderi();
    	parçacýklar[parçacýkNo].konumuGüncelle(sürüÖnderi);
    }
    public void parçacýklarýnKonumunuGüncelle() throws Exception{
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýkKonumuGüncelle(parçacýkNo);
    	}
    }

    /**
     * tüm parçacýklarýn ortalamaKümelerÝçi uzaklýk ve ortalamaKümelerArasýUzaklýk deðiþkenini sýfýrlar
     */
    private void parçacýklarýnUzaklýklarýnýSýfýrla(){
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýklar[parçacýkNo].uzaklýklarýSýfýrla();
    	}
    }
    public void ilerle() throws Exception{
    	parçacýklarýnAmaçDeðerleriniYenile();
    	parçacýklarýnUzaklýklarýnýSýfýrla();
    	parçacýklarýnKümeleriniSýfýrla();
    	sürününBaþýnýBul();
    	parçacýklarýnKonumunuGüncelle();
    }
    private void parçacýklarýnAmaçDeðerleriniYenile(){
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýklar[parçacýkNo].amaçDeðeriVar = false;
    	}
    }
    private void parçacýklarýnKümeleriniSýfýrla() throws Exception{
    	for(int parçacýkNo = 0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		parçacýklar [parçacýkNo] .kümeleriSýfýrla();
    	}
    }
    
    // çözüm yöntemleri baþ
    public String çöz() throws Exception{
    	String çözüm = "";
    	long baþ = System.currentTimeMillis();
    	çözüm = baþlangýç();
    	çözüm += çözümDöngüsü();
    	makineGruplarý = parçacýklar[sürüBaþý].kümeler;
    	çözüm += yaz();
    	long son = System.currentTimeMillis();
    	çözümZamaný = son - baþ;
    	çözüm += "çözüm zamaný : " + çözümZamaný + " milisaniye";
    	return çözüm;
    }
	private String çözümDöngüsü() throws Exception {
		String çözüm = "";
		if(ilerlemeKýsýtlý ){
			çözüm = kýsýtsýzÝlerleme(çözüm);
		}
		else {
			çözüm = kýsýtlýÝlerleme(çözüm);
		}
		return çözüm;
	}
	private String kýsýtlýÝlerleme(String çözüm) throws Exception {
		ArrayList<Double> amaçlar = new ArrayList<Double>();
		çözüm += "ilk aþamalar\n";
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaAþama;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			çözüm += çözAþamaYaz(ilerleme);
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			amaçlar.add(yeniAmaçDeðeri);
		}
		// ilk aþamalar bitti
		for(ilerleme=0;ilerleme<enFazlaÝlerleme+1;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			çözüm += çözAþamaYaz(ilerleme);
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			if( yeniAmaçDeðeri - amaçlar.get(0) < duyarlýlýk){
			    break;	
			}
			amaçlar.remove(0);
			amaçlar.add(yeniAmaçDeðeri);
		}
		return çözüm;
	}
	private String kýsýtsýzÝlerleme(String çözüm) throws Exception {
		ArrayList<Double> amaçlar = new ArrayList<Double>();
		çözüm += "ilk aþamalar\n";
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaAþama;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			çözüm += çözAþamaYaz(ilerleme);
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			amaçlar.add(yeniAmaçDeðeri);
		}
		// ilk aþamalar bitti
		for(ilerleme=0;;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			çözüm += çözAþamaYaz(ilerleme);
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			if( yeniAmaçDeðeri - amaçlar.get(0) < duyarlýlýk){
			    break;	
			}
			amaçlar.remove(0);
			amaçlar.add(yeniAmaçDeðeri);
		}
		return çözüm;
	}
    private String çözAþamaYaz(int ilerleme) throws Exception{
    	String çözüm = "";  
		çözüm += "ilerleme : " + ilerleme ;
		çözüm += "" + yaz() + "\n\n--------------------------------------------------------------------------\n\n";	
		çözüm += "" + yaz() ;
		return çözüm;
    }
    
    
    
    public String hýzlýÇöz() throws Exception{
    	String çözüm = "";
    	long baþ = System.currentTimeMillis();
    	çözüm = baþlangýç();
    	hýzlýÇözümDöngüsünüKur();
    	makineGruplarý = parçacýklar[sürüBaþý].kümeler;
    	çözüm += "parça makine matrisi : " + yeniParçaMakineMatrisiYaz2() + "\n\n";
    	long son = System.currentTimeMillis();
    	çözümZamaný = son - baþ;
    	çözüm += "çözüm zamaný : " + çözümZamaný + " milisaniye";
    	return çözüm;
    }
	private String çözümüYaz() throws Exception {
		String çözüm = "";
		çözüm += hýzlýÇözümDöngüsünüKur();
    	çözüm += "son: \n\n" + sürüÖnderi().yaz() + "________________________________________________________\n\n";
    	makineGruplarý = parçacýklar[sürüBaþý].kümeler;
    	çözüm += "yeni parça makine matrisi : \n" + yeniParçaMakineMatrisiYaz2()+ "\n";
    	return çözüm;
	}
	private String baþlangýç() throws Exception {
		String çözüm = "";
		çözüm += baþlangýçKoþullarýnýYaz();
    	ilerle();
		return çözüm;
	}
	private String hýzlýÇözümDöngüsünüKur() throws Exception {
		String çözüm = "";
		if(ilerlemeKýsýtlý ){
			çözüm = hýzlýÇözÝlerlemeKýsýtlý(çözüm);
		}
		else {
			çözüm = hýzlýÇözÝlerlemeKýsýtsýz(çözüm);
		}
		return çözüm;
	}
	private String hýzlýÇözÝlerlemeKýsýtsýz(String çözüm) throws Exception {
		ArrayList<Double> amaçlar = new ArrayList<Double>();
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaAþama;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			amaçlar.add(yeniAmaçDeðeri);
		}
		// ilk aþamalar bitti
		for(ilerleme=0;;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			if( yeniAmaçDeðeri - amaçlar.get(0) < duyarlýlýk){
			    break;	
			}
			amaçlar.remove(0);
			amaçlar.add(yeniAmaçDeðeri);
		}
		return çözüm;
	}
	private String hýzlýÇözÝlerlemeKýsýtlý(String çözüm) throws Exception {
		ArrayList<Double> amaçlar = new ArrayList<Double>();
		çözüm += "ilk aþamalar\n";
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaAþama;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			amaçlar.add(yeniAmaçDeðeri);
		}
		// ilk aþamalar bitti
		for(ilerleme=0;ilerleme<enFazlaÝlerleme;ilerleme++){
			çözüm += "ilerleme : " + ilerleme + "\n\n";
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			if( yeniAmaçDeðeri - amaçlar.get(0) < duyarlýlýk){
			    break;	
			}
			amaçlar.remove(0);
			amaçlar.add(yeniAmaçDeðeri);
		}
		return çözüm;
	}
	
	
    // çözüm yöntemleri son
    protected Parçacýk sürüÖnderi(){
    	return parçacýklar[sürüBaþý];
    }
    private int deneyÝçinÇöz() throws Exception{
    	long baþ = System.currentTimeMillis();
    	ArrayList<Double> amaçlar = new ArrayList<Double>();
		int ilerleme;
		for(ilerleme = 0;ilerleme<enFazlaAþama;ilerleme++){
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			amaçlar.add(yeniAmaçDeðeri);
		}
		// ilk aþamalar bitti
		for(ilerleme=0;ilerleme<enFazlaÝlerleme+1;ilerleme++){
			ilerle();
			double yeniAmaçDeðeri = sürüÖnderi().amaçDeðeri;
			if( yeniAmaçDeðeri - amaçlar.get(0) < duyarlýlýk){
			    break;	
			}
			amaçlar.remove(0);
			amaçlar.add(yeniAmaçDeðeri);
		}
		
    	long son = System.currentTimeMillis();
    	çözümZamaný = son - baþ;
    	return ilerleme + enFazlaAþama;
    }
    public String deneyYap(int parçacýkSayýsý,int kümeSayýsý) throws Exception{
    	String deney = "";//"çözüm zamaný\tparçacýk sayýsý\tküme sayýsý\taþama sayýsý\tamaç deðeri";
    	this.parçacýkSayýsý = parçacýkSayýsý;
    	this.kümeSayýsý = kümeSayýsý;
    	parçacýklarýOluþtur();
    	int aþama = deneyÝçinÇöz();
    	deney +=  çözümZamaný + "\t";
    	deney +=  parçacýkSayýsý+ "\t";
    	deney +=  kümeSayýsý+ "\t";
    	deney +=  aþama+ "\t";
    	Parçacýk önder = sürüÖnderi();
    	deney +=  önder.amaçDeðeri+ "\t";
    	deney +=  önder.boþKümeSayýsýBul() + "\t";
    	System.out.print(deney+"\n");
    	return deney;
    }
    public String deneyYap(int parçacýkSayýsý1,int parçacýkSayýsý2,int kümeSayýsý1,int kümeSayýsý2) throws Exception{
    	String deney = "çözüm zamaný\tparçacýk sayýsý\tküme sayýsý\taþama sayýsý\tamaç deðeri\tboþ küme sayýsý\n";
    	System.out.print(deney);
    	int parçacýkSayýsý,kümeSayýsý;
    	for(parçacýkSayýsý=parçacýkSayýsý1;parçacýkSayýsý<parçacýkSayýsý2;parçacýkSayýsý++){
    		for(kümeSayýsý=kümeSayýsý1;kümeSayýsý<kümeSayýsý2;kümeSayýsý++){
    			deneyYap(parçacýkSayýsý, kümeSayýsý);
    		}
    	}
    	return deney;
    }
    
	private String baþlangýçKoþullarýnýYaz() {
		String çözüm = "";
		çözüm += "kendine güven : " + parçacýklar[0].kendineGüven + "\n";
    	çözüm += "sürüye güven : " + parçacýklar[0].sürüyeGüven+ "\n";
    	çözüm += "eylemsizlik : " + parçacýklar[0].eylemsizlik + "\n";
    	çözüm += "ilkHýz : " + parçacýklar[0].ilkHýz+ "\n";
    	çözüm += "parcacýk sayýsý : " + parçacýkSayýsý + "\n";
    	çözüm += "küme sayýsý : " + kümeSayýsý + "\n";
		return çözüm;
	}
    public String yaz() throws Exception{
    	String yazý = "";
    	yazý += sürüBaþýnýYaz();
    	yazý += baþlangýçKoþullarýnýYaz();
    	yazý += parçacýklarýYaz() + "\n";
    	yazý += "yeni parça makine matrisi : " + yeniParçaMakineMatrisiYap().yaz()+ "\n";
    	return yazý;
    }

	private String parçacýklarýYaz() {
		String yazý = "";
		int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		yazý += "parçacýk  no : " + parçacýkNo  + "\n\n";
    		yazý += parçacýklar[parçacýkNo].yaz() + "\n\n";
    	}
		return yazý;
	}

	private String sürüBaþýnýYaz() {
		String yazý = "";
		if(sürüBaþý != -1){
    		yazý += "sürü baþý : " + sürüBaþý + "\n";
    		//yazý += "sürünün en iyi amaç deðeri : " + parçacýklar[sürüBaþý].amaçDeðeri + "\n\n";
    		//yazý += "sürünün en iyi küme merkezleri : \n" + parçacýklar[sürüBaþý].kümeMerkezleri.yaz("\t") + "\n\n";
    		yazý += parçacýklar[sürüBaþý].yaz();
    	}
		return yazý;
	}

	

}
