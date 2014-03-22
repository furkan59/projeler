package SýkýþtýrýlmýþVeriYapýlarý;

import java.util.ArrayList;

/**
 * kural þu : 
 * <br> diyelim ki [1,4,5,7,8,10,14,17] sayýlarý bu arama aðacýna konumuþ olsun
 * <br> ilk baþta girilen diziyi küçükten büyüðe sýralanacak
 * <br> ilk kural þu tepede orta deðer  olacak ki arama iþe yarasýn
 * <br> arama aðacý bir dizi ile oluþturulmalý
 * <br> dizinin medyaný tepe deðer olacak
 * <br> burada tepe deðer 8 , 8 in sað 8 in sað kolu dizinin medyaný olan 14 olacak  
 * <br> 8 in sol kolu solundaki dizinin medyaný olan 4 olacak (eðer dizi çift elemanlý ise ortanýn saðýndakini seçelim böyle bir kural olsun)
 * <br> bu þekilde devam edecek ve 
 * <br>                       8
 * <br>               4              14
 * <br>            1    5         10     17   
 * <br>                    7
 * <br> þekline dönüþecek
 * @author Furkan
 */
public class IntAramaAðacý2 {

	public IntAramaAðacý2 büyük,küçük;
	public int sayý;
	public int enBüyükDeðer,enKüçükDeðer;
	public int elemanSayýsý =-3;
	
	// {{ kurucular
    public IntAramaAðacý2 (){
    	
    }
	public IntAramaAðacý2(int deðer){
		büyük = null;
		küçük = null;
		this.sayý = deðer;
	}
	/** 
	 * deðerlerin sýralý olup olmadýðýna bakar sýralý olup olmadýðý diðer iç aðaçlar içindir
	 * <br> dýþardýdan oluþtururken sýralý bilgisinin verilmesi önemsizdir.Tekrar tekrar bakmasýný önlemek içindir
	 * @param deðerler
	 */
	public IntAramaAðacý2(int [] deðerler,boolean sýralýMý) {
	   	büyük = null;
	   	küçük = null;
	   	if(sýralýMý){
	   		sýralýDeðerleriDoldur(deðerler);	
	   	}
	   	else {
	   		sýralýdýktanSonraDoldur(deðerler);	
	   	}
	   	elemanSayýsý = deðerler.length;
	}
	private void sýralýdýktanSonraDoldur(int[] deðerler) {
		sýrala(deðerler);
		enBüyükDeðer = deðerler[deðerler.length-1];
		enKüçükDeðer = deðerler[0];
		if(deðerler.length <= 3){ // demek ki son aþamaya geldik
			sonAþamayýEkle(deðerler); 
		}
		else {
			araAþamalarýEkle(deðerler);
		}
	}
	private void araAþamalarýEkle(int[] deðerler) {
		this.sayý = medyanBul(deðerler);
		int [] küçüðeGidecek = küçüðeGidecekDizi(deðerler);
		int [] büyüðeGidecek = büyüðeGidecekDizi(deðerler);
		büyük = new IntAramaAðacý2(büyüðeGidecek,true);
		küçük = new IntAramaAðacý2(küçüðeGidecek,true);
	}
	private void sýralýDeðerleriDoldur(int[] deðerler) {
		enBüyükDeðer = deðerler[deðerler.length-1];
		enKüçükDeðer = deðerler[0];
		if(deðerler.length <= 3){ // demek ki son aþamaya geldik
			sonAþamayýEkle(deðerler); 
		}
		else {
			araAþamalarýEkle(deðerler);
		}
	}
	/**
	 * boþ dizi gelmemeli
	 * @param deðerler
	 */
	private void sonAþamayýEkle(int[] deðerler) {
		if(deðerler.length == 3){
			küçükEkle(deðerler[0]);  
			this.sayý = deðerler[1];  //  medyan kendisi
			büyükEkle(deðerler[2]);
		}
		else if(deðerler.length == 2) { // burasý kabule bakýyor küçüðü yukarý almayý tercih ettik
			this.sayý = deðerler[0];
			büyükEkle(deðerler[1]);
		}
		else { 
			this.sayý = deðerler[0];
		}
		elemanSayýsý++;
	}
	
	public IntAramaAðacý2 (String yazý, String ayýrýcý){
		String [] bölünmüþ = ayýrýcý.split(ayýrýcý);
		int [] deðerler = new int [bölünmüþ.length];
		int elemanNo;
		for(elemanNo=0;elemanNo<deðerler.length;elemanNo++){
			deðerler [elemanNo] = Integer.parseInt(bölünmüþ[elemanNo]);
		}
		new IntAramaAðacý2(deðerler, false);
	}
	/**
	 * ilk önce yazýyý ayýrýcý ile böler sonra baþlama yerinden itibaren okur ve diziye yazar 
	 * <br> sonra da aðacý oluþturur
	 * @param yazý
	 * @param baþlamaYeri
	 */
	public IntAramaAðacý2 (String yazý,int baþlamaYeri ,String ayýrýcý){
		String [] bölünmüþ = ayýrýcý.split(ayýrýcý);
		int [] deðerler = new int [bölünmüþ.length-baþlamaYeri];
		int elemanNo;
		for(elemanNo=baþlamaYeri;elemanNo<deðerler.length;elemanNo++){
			deðerler [elemanNo-baþlamaYeri] = Integer.parseInt(bölünmüþ[elemanNo]);
		}
		new IntAramaAðacý2(deðerler, false);
	}
	public IntAramaAðacý2 (String [] yazý,int baþlamaYeri){
		int [] deðerler = new int [yazý.length-baþlamaYeri];
		int elemanNo;
		for(elemanNo=baþlamaYeri;elemanNo<deðerler.length;elemanNo++){
			deðerler [elemanNo-baþlamaYeri] = Integer.parseInt(yazý[elemanNo]);
		}
		new IntAramaAðacý2(deðerler, false);
	}
	
	// }} kurucular
    
    // {{ sýralama ve geniþletme
	/** 
     * insertion sort uygular
     * @param deðerler
     */
	private void sýrala(int [] deðerler){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<deðerler.length;elemanNo++){
    		int enKüçüðünYeri = enKüçüðünYeriniBul(deðerler, elemanNo);
    		int enKüçük = deðerler[enKüçüðünYeri];
    		deðerler [enKüçüðünYeri] = deðerler[elemanNo];
    		deðerler [elemanNo] = enKüçük;
    	}
    	
    }
	private int enKüçüðünYeriniBul(int [] deðerler,int baþ){
		int elemanNo;
		int enKüçük= deðerler[baþ];
		int yer = baþ;
		for(elemanNo=baþ+1;elemanNo<deðerler.length;elemanNo++){
			if(deðerler[elemanNo] <enKüçük  ){
				enKüçük = deðerler[elemanNo];
				yer = elemanNo;
			}
		}
		return yer;
	}
    private int medyanBul(int []deðerler){
		int medyanýnYeri = deðerler.length / 2 ;
		return deðerler[medyanýnYeri];
}
	
    public void büyükEkle(int deðer){
    	büyük = new IntAramaAðacý2(deðer);
    	elemanSayýsý ++;
    }
    public void küçükEkle(int deðer){
    	küçük = new IntAramaAðacý2(deðer);
    	elemanSayýsý ++;
    }
    private int [] küçüðeGidecekDizi(int [] deðerler){
    	int medyanýnYeri = deðerler.length / 2;
    	int [] gidecek = new int [medyanýnYeri];
    	int elemanNo;
    	for(elemanNo=0;elemanNo<medyanýnYeri;elemanNo++){
			gidecek [elemanNo] = deðerler[elemanNo];
	    }
    	return gidecek;	
    }
    private int [] büyüðeGidecekDizi(int [] deðerler){
    	//3,4,5,8,9,16,18,20,22,23,25
    	int medyanýnYeri = deðerler.length / 2;
    	int [] gidecek = new int [deðerler.length - medyanýnYeri - 1];
    	int elemanNo;
    	for(elemanNo=0;elemanNo<gidecek.length;elemanNo++){
			gidecek [elemanNo] = deðerler[elemanNo+medyanýnYeri+1];
    	}
    	return gidecek;	
    }
    
    /**    
     * 1 büyük kola git demek 0 küçük kola git demek
     * <br> örnek kod = "10101000" gibi olacak
     * @param kod
     * @return
     */
    public IntAramaAðacý2 eriþ(String kod){
    	int noktaNo;
    	IntAramaAðacý2 aranan = this;
    	for(noktaNo=0;noktaNo<kod.length();noktaNo++){
    		if(kod.charAt(noktaNo) == '1'){
    			aranan = aranan.büyük;
    		}
    		else {
    			aranan = aranan.küçük;
    		}
    	}
    	return aranan;
    }
    // }} sýralama ve geniþletme
    // {{ arama

    public boolean ara(int deðer){
    	IntAramaAðacý2 aranan = this;
    	if(deðer == aranan.sayý){
    		return true;
    	}
    	else if(deðer > aranan.enBüyükDeðer || deðer < aranan.enKüçükDeðer){
    		return false;
    	}
    	else if( deðer < aranan.sayý ){ // diðte bir noktaya geldik demektir
    		return deðerKüçükkenAra(deðer, aranan);
    	}
    	else { // deðer > aranan.sayý kalýr geriye
    		return deðerBüyükkenAra(deðer, aranan);
    	}
    }
	private boolean deðerBüyükkenAra(int deðer, IntAramaAðacý2 aranan) {
		if(aranan.büyük == null){
			return false;
		}
		else {
			aranan = aranan.büyük;
			return aranan.ara(deðer);	
		}
	}
	private boolean deðerKüçükkenAra(int deðer, IntAramaAðacý2 aranan) {
		if(aranan.küçük == null){
			return false;
		}
		else {
			aranan = aranan.küçük;
			return aranan.ara(deðer);	
		}
	}
    
	/**
	 * bir sayýdan daha büyük olan tüm deðerleri liste yapar
	 * @param altDeðer
	 * @param üstDeðer
	 * @return
	 */
	public ArrayList<Integer> büyükDeðeriBul(int altDeðer){
		ArrayList<Integer> deðerler = new ArrayList<Integer>();
    	if(altDeðer > enBüyükDeðer){
    		return deðerler;
    	}
    	else{
    		return altDeðerOlurlu(altDeðer, deðerler);
         }
	}
	private ArrayList<Integer> altDeðerOlurlu(int altDeðer,ArrayList<Integer> deðerler) {
		if(sayý > altDeðer ){ // sayýnýn kendiis alt deðerden büyükse büyük kolundaki tüm deðerler listeye eklenebilir
			sayýAltDeðerdenBüyük(altDeðer, deðerler);
		}
		else{ // sayý aranan deðerden küçük ve ya ona eþit
			büyükKoldaBüyükDeðerAra(altDeðer, deðerler);	
		}	
		return deðerler;
	}
	private void büyükKoldaBüyükDeðerAra(int altDeðer, ArrayList<Integer> deðerler) {
		if(büyük != null ){ // belki kendinden büyük deðerler aranan deðerden büyüktür
			deðerler.addAll(büyük.büyükDeðeriBul(altDeðer));	
		}
	}
	private void sayýAltDeðerdenBüyük(int altDeðer,ArrayList<Integer> deðerler) {
		deðerler.add(sayý); // daha büyük deðerler olabilir
		büyükKoluEkle(deðerler);	
		küçükKoldaBüyükDeðerAra(altDeðer, deðerler);
	}
	private void küçükKoldaBüyükDeðerAra(int altDeðer,
			ArrayList<Integer> deðerler) {
		if(küçük != null){ // sayý çok büyüktür ve kendisinden küçük deðerler bile aranan deðerden büyüktür
			deðerler.addAll(küçük.büyükDeðeriBul(altDeðer));
		}
	}
	private void büyükKoluEkle(ArrayList<Integer> deðerler) {
		if(büyük != null ){
		   deðerler.addAll(büyük.tümDeðerleriListele());	
		}
	}
	
    public ArrayList<Integer> küçükDeðeriBul(int üstDeðer){
		ArrayList<Integer> deðerler = new ArrayList<Integer>();
    	if(üstDeðer < enKüçükDeðer){
    		return deðerler;
    	}
    	else {
    		return üstDeðerOlurlu(üstDeðer, deðerler);
    	}
	}
	private ArrayList<Integer> üstDeðerOlurlu(int üstDeðer,ArrayList<Integer> deðerler) {
		if(sayý < üstDeðer ){
			sayýÜstDeðerdenKüçük(üstDeðer, deðerler);
		} // sayý aranan deðere eþit ya da ondan daha büyük
		else {
			sayýÜstDeðerdenBüyük(üstDeðer, deðerler);	
		}
		return deðerler;
	}
	private void sayýÜstDeðerdenBüyük(int üstDeðer, ArrayList<Integer> deðerler) {
		if(küçük != null ){ // bir ithimal kendinden küçük deðerler olumlu sonuç verebilir ama
			// kendinden büyük deðerler olurlu sonuç vermez
			deðerler.addAll(küçük.küçükDeðeriBul(üstDeðer));	
		}
	}
	private void sayýÜstDeðerdenKüçük(int üstDeðer,ArrayList<Integer> deðerler) {
		deðerler.add(sayý); // aðacýn küçük kolunun altýndaki tüm deðerler kendi deðerinden küçüktür 
		//bu nedenle eðer küçük kol varsatüm küçük kol eklenebilir
		küçükKoluEkle(deðerler);
		büyükKoldakiKüçükleriEkle(üstDeðer, deðerler);
	}
	private void büyükKoldakiKüçükleriEkle(int üstDeðer,	ArrayList<Integer> deðerler) {
		if(büyük !=null){
			deðerler.addAll(büyük.küçükDeðeriBul(üstDeðer));
		}
	}
	private void küçükKoluEkle(ArrayList<Integer> deðerler) {
		if(küçük != null){
		deðerler.addAll(küçük.tümDeðerleriListele());
		}
	}
	
	private boolean altýndaNoktaVarMý(){
		if(büyük == null && küçük == null){
			return true;
		}
		else {
			return false;
		}
	}
    
	public ArrayList<Integer> aralýkBul(int altDeðer, int üstDeðer){
		ArrayList<Integer> deðerler = new ArrayList<Integer>();
		if(altDeðer < üstDeðer){ // zaten bu saçma olur aramaya gerek yok döngülerde gelebilir
			if ( üstDeðer > enKüçükDeðer && altDeðer < enBüyükDeðer){ // aralýk olurlu aralýkta
				aramaYap(altDeðer, üstDeðer, deðerler);
			}	
		}
		return deðerler;
	}
	private void aramaYap(int altDeðer, int üstDeðer,ArrayList<Integer> deðerler) {
		boolean sayýAltDeðerdenKüçükMü = sayý < altDeðer;
		boolean sayýÜstDeðerdenBüyükMü = sayý > üstDeðer;
		if( sayýAltDeðerdenKüçükMü ){ // o zaman büyük tarafýna bakmalýyýz ama küçük tarafa gerek yok
		    büyükTaraftaAralýkAra(altDeðer, üstDeðer, deðerler);
		}
		else if ( sayýÜstDeðerdenBüyükMü){ // küçük tarafýna bakmakta fayda var , büyük tarafa gerek yok
			küçükTaraftaAralýkAra(altDeðer, üstDeðer, deðerler);
		}
		else { // sayý arada bir yerdedir iki tarafa da bakmak gerekli hem de kendisini alalým
			deðerler.add(sayý);
			büyükTaraftaAralýkAra(altDeðer, üstDeðer, deðerler);
			küçükTaraftaAralýkAra(altDeðer, üstDeðer, deðerler);
		}
	}
	private void küçükTaraftaAralýkAra(int altDeðer, int üstDeðer, ArrayList<Integer> deðerler) {
		if(küçük != null){
			deðerler.addAll(küçük.aralýkBul(altDeðer, üstDeðer));
		}
	}
	private void büyükTaraftaAralýkAra(int altDeðer, int üstDeðer, ArrayList<Integer> deðerler) {
		if(büyük != null){
			deðerler.addAll(büyük.aralýkBul(altDeðer, üstDeðer));
		}
	}
 	
	// }} arama
	
    // {{ yazdýrma
	public ArrayList<Integer> tümDeðerleriListele(){
		ArrayList<Integer> deðerler = new ArrayList<Integer>();
		deðerler.add(sayý);
		büyükKoluEkle(deðerler);
		küçükKoluEkle(deðerler);
		return deðerler;
	}
	public String yaz(){
    	String yazý = "";
    	if(büyük != null){
    		yazý += "büyük kol deðeri : " + büyük.sayý + "\n";
    	}
    	if(küçük != null){
    		yazý += "küçük kol deðeri : " + küçük.sayý + "\n";
    	}
    	if(this != null){
    		yazý +=  sayý + "\n";	
    	}
    	return yazý;
    }
    public String tümDeðerleriYaz(){
    	return tümDeðerleriListele().toString();
    }
    // }} yazdýrma
    
    

}
