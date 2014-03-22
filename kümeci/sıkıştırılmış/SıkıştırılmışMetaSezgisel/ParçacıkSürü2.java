package SýkýþtýrýlmýþMetaSezgisel;

import java.util.ArrayList;

import kümeci.Kümeleme;
import SýkýþtýrýlmýþVeriYapýlarý.BoolEksikMatrisAðaçlý;
import metaSezgisel.Parçacýk;
import veriYapýlarý.BoolDizi;

public class ParçacýkSürü2 extends Kümeleme2{

	public ParçacýkSürü2(BoolEksikMatrisAðaçlý parçaMakine, String[] makineÝsimleri, String[] parçaÝsimleri,int kümeSayýsý,int parçacýkSayýsý) throws Exception {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
		this.parçacýkSayýsý = parçacýkSayýsý;
		this.kümeSayýsý = kümeSayýsý;
		parçacýklarýOluþtur();
	}
	public ParçacýkSürü2(String yazý, char ayýrýcý,int kümeSayýsý,int parçacýkSayýsý) throws Exception {
		super(yazý, ayýrýcý);
		this.parçacýkSayýsý = parçacýkSayýsý;
		this.kümeSayýsý = kümeSayýsý;
		parçacýklarýOluþtur();
	}
	public ParçacýkSürü2(String yazý,int kümeSayýsý,int parçacýkSayýsý) throws Exception {
		super(yazý);
		this.parçacýkSayýsý = parçacýkSayýsý;
		this.kümeSayýsý = kümeSayýsý;
		parçacýklarýOluþtur();
	}

	public int parçacýkSayýsý;
	public int kümeSayýsý;
	private Parçacýk2 [] parçacýklar;
	
	// güven parametreleri belirlenmediði zaman parçalar kendi güven deðerlini kullanýr
	
	protected int sürüBaþý=-1;
	public long çözümZamaný;
	
	public int enFazlaAþama;
	public int enFazlaÝlerleme;
	public boolean ilerlemeKýsýtlý = false;
	
	public double duyarlýlýk = 0.00000000001;

	public int parçacýkSayýsý (){
	   return parçaMakine.sutunSay();
	}
	private void parçacýklarýOluþtur() throws Exception {
    	parçacýklar = new Parçacýk2 [this.parçacýkSayýsý];
    	int Parçacýk2No;
    	for(Parçacýk2No=0;Parçacýk2No<parçacýkSayýsý;Parçacýk2No++){
    		parçacýklar[Parçacýk2No] = new Parçacýk2(kümeSayýsý, parçaSayýsý(), parçaMakine);
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
		double yeniAmaç = parçacýklar[parçacýkNo].amaçDeðeriHesapla(parçaMakine);
		if(yeniAmaç > parçacýklar[sürüBaþý].amaçDeðeri){
			sürüBaþý = parçacýkNo;
		}
	}
    
	private void parçaNoKontrolEt(int parçaNo) throws Exception {
		if(parçaNo > parçacýkSayýsý){
    		throw new Exception("parçano Parçacýk2 sayýsýndan fazla olamaz parçaNo : " + parçaNo + " Parçacýk2 sayýsý : " + parçacýkSayýsý + "\n");
    	}
	}
    private void parçacýkKonumuGüncelle(int parçacýkNo) throws Exception{
    	parçaNoKontrolEt(parçacýkNo);
        Parçacýk2 sürüÖnderi = sürüÖnderi();
    	parçacýklar[parçacýkNo].konumuGüncelle(sürüÖnderi,parçaMakine);
    }
    public void parçacýklarýnKonumunuGüncelle() throws Exception{
    	int Parçacýk2No;
    	for(Parçacýk2No=0;Parçacýk2No<parçacýkSayýsý;Parçacýk2No++){
    		parçacýkKonumuGüncelle(Parçacýk2No);
    	}
    }

    /**
     * tüm Parçacýk2larýn ortalamaKümelerÝçi uzaklýk ve ortalamaKümelerArasýUzaklýk deðiþkenini sýfýrlar
     */
    private void parçacýklarýnUzaklýklarýnýSýfýrla(){
    	int Parçacýk2No;
    	for(Parçacýk2No=0;Parçacýk2No<parçacýkSayýsý;Parçacýk2No++){
    		parçacýklar[Parçacýk2No].uzaklýklarýSýfýrla();
    	}
    }
    public void ilerle() throws Exception{
    	parçacýklarýnAmaçDeðerleriniYenile();
    	parçacýklarýnUzaklýklarýnýSýfýrla();
    	sürününBaþýnýBul();
    	parçacýklarýnKonumunuGüncelle();
    }
    private void parçacýklarýnAmaçDeðerleriniYenile(){
    	int Parçacýk2No;
    	for(Parçacýk2No=0;Parçacýk2No<parçacýkSayýsý;Parçacýk2No++){
    		parçacýklar[Parçacýk2No].amaçDeðeriVar = false;
    	}
    }
   
    // çözüm yöntemleri baþ
    public String çöz() throws Exception{
    	String çözüm = "";
    	long baþ = System.currentTimeMillis();
    	çözüm = baþlangýç();
    	çözüm += çözÇözümDöngüsü();
    	çözüm += yaz();
    	long son = System.currentTimeMillis();
    	çözümZamaný = son - baþ;
    	çözüm += "çözüm zamaný : " + çözümZamaný + " milisaniye";
    	return çözüm;
    }
	private String çözÇözümDöngüsü() throws Exception {
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
    	çözüm += çözümüYaz();
    	long son = System.currentTimeMillis();
    	çözümZamaný = son - baþ;
    	çözüm += "çözüm zamaný : " + çözümZamaný + " milisaniye";
    	return çözüm;
    }
	private String çözümüYaz() throws Exception {
		String çözüm = "";
		çözüm += hýzlýÇözümDöngüsünüKur();
    	çözüm += "son: \n\n" + sürüÖnderi().yaz() + "________________________________________________________\n\n";
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
	private String hýzlýÇözAþamaYaz()throws Exception {
		String çözüm = "";  
		ilerle();
		return çözüm;
	}
    private String hýzlýÇözÝlerleme(int ilerleme) throws Exception{
    	String çözüm = "";
    	int aþamaNo;
		for(aþamaNo=1;aþamaNo<enFazlaAþama;aþamaNo++){
			çözüm += hýzlýÇözAþamaYaz();
    	}
		çözüm += "\n\naþama sayýsý : " + aþamaNo + "\n\n";
		return çözüm;
    }
	
    // çözüm yöntemleri son
    protected Parçacýk2 sürüÖnderi(){
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
    	String deney = "";//"çözüm zamaný\tParçacýk2 sayýsý\tküme sayýsý\taþama sayýsý\tamaç deðeri";
    	this.parçacýkSayýsý = parçacýkSayýsý;
    	this.kümeSayýsý = kümeSayýsý;
    	parçacýklarýOluþtur();
    	int aþama = deneyÝçinÇöz();
    	deney +=  çözümZamaný + "\t";
    	deney +=  parçacýkSayýsý+ "\t";
    	deney +=  kümeSayýsý+ "\t";
    	deney +=  aþama+ "\t";
    	Parçacýk2 önder = sürüÖnderi();
    	deney +=  önder.amaçDeðeri+ "\t";
    	deney +=  önder.boþKümeSayýsýBul() + "\t";
    	System.out.print(deney+"\n");
    	return deney;
    }
    public String deneyYap(int parçacýkSayýsý1,int parçacýkSayýsý2,int kümeSayýsý1,int kümeSayýsý2) throws Exception{
    	String deney = "çözüm zamaný\tParçacýk2 sayýsý\tküme sayýsý\taþama sayýsý\tamaç deðeri\tboþ küme sayýsý\n";
    	System.out.print(deney);
    	int Parçacýk2Sayýsý,kümeSayýsý;
    	for(Parçacýk2Sayýsý=parçacýkSayýsý1;Parçacýk2Sayýsý<parçacýkSayýsý2;Parçacýk2Sayýsý++){
    		for(kümeSayýsý=kümeSayýsý1;kümeSayýsý<kümeSayýsý2;kümeSayýsý++){
    			deneyYap(Parçacýk2Sayýsý, kümeSayýsý);
    		}
    	}
    	return deney;
    }
    
	private String baþlangýçKoþullarýnýYaz() throws Exception {
		String çözüm = "";
		çözüm += "kendine güven : " + parçacýklar[0].kendineGüven + "\n";
    	çözüm += "sürüye güven : " + parçacýklar[0].sürüyeGüven+ "\n";
    	çözüm += "eylemsizlik : " + parçacýklar[0].eylemsizlik + "\n";
    	çözüm += "ilkHýz : " + parçacýklar[0].ilkHýz+ "\n";
    	çözüm += "parcacýk sayýsý : " + parçacýkSayýsý + "\n";
    	çözüm += "küme sayýsý : " + kümeSayýsý + "\n";
    	çözüm += "parça makine matrisi : \n" + parçaMakine.yaz() + "\n";
    	çözüm += "parçacýklar : \n\n";
    	int parçacýkNo;
    	for(parçacýkNo=0;parçacýkNo<parçacýkSayýsý;parçacýkNo++){
    		çözüm += "parçacýk " + parçacýkNo + parçacýklar[parçacýkNo].yaz() + "\n\n\n";
    	}
		return çözüm;
	}
    public String yaz() throws Exception{
    	String yazý = "";
    	yazý += sürüBaþýnýYaz();
    	yazý += baþlangýçKoþullarýnýYaz();
    	yazý += parçacýklarýYaz();
    	return yazý;
    }

	private String parçacýklarýYaz() throws Exception {
		String yazý = "";
		int Parçacýk2No;
    	for(Parçacýk2No=0;Parçacýk2No<parçacýkSayýsý;Parçacýk2No++){
    		yazý += "Parçacýk2  no : " + Parçacýk2No  + "\n\n";
    		yazý += parçacýklar[Parçacýk2No].yaz() + "\n\n";
    	}
		return yazý;
	}

	private String sürüBaþýnýYaz() throws Exception {
		String yazý = "";
		if(sürüBaþý != -1){
    		yazý += "sürü baþý : " + sürüBaþý + "\n";
    		//yazý += "sürünün en iyi amaç deðeri : " + Parçacýk2lar[sürüBaþý].amaçDeðeri + "\n\n";
    		//yazý += "sürünün en iyi küme merkezleri : \n" + Parçacýk2lar[sürüBaþý].kümeMerkezleri.yaz("\t") + "\n\n";
    		yazý += parçacýklar[sürüBaþý].yaz();
    	}
		return yazý;
	}
}



