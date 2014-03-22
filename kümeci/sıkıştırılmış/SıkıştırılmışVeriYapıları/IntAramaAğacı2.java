package S�k��t�r�lm��VeriYap�lar�;

import java.util.ArrayList;

/**
 * kural �u : 
 * <br> diyelim ki [1,4,5,7,8,10,14,17] say�lar� bu arama a�ac�na konumu� olsun
 * <br> ilk ba�ta girilen diziyi k���kten b�y��e s�ralanacak
 * <br> ilk kural �u tepede orta de�er  olacak ki arama i�e yaras�n
 * <br> arama a�ac� bir dizi ile olu�turulmal�
 * <br> dizinin medyan� tepe de�er olacak
 * <br> burada tepe de�er 8 , 8 in sa� 8 in sa� kolu dizinin medyan� olan 14 olacak  
 * <br> 8 in sol kolu solundaki dizinin medyan� olan 4 olacak (e�er dizi �ift elemanl� ise ortan�n sa��ndakini se�elim b�yle bir kural olsun)
 * <br> bu �ekilde devam edecek ve 
 * <br>                       8
 * <br>               4              14
 * <br>            1    5         10     17   
 * <br>                    7
 * <br> �ekline d�n��ecek
 * @author Furkan
 */
public class IntAramaA�ac�2 {

	public IntAramaA�ac�2 b�y�k,k���k;
	public int say�;
	public int enB�y�kDe�er,enK���kDe�er;
	public int elemanSay�s� =-3;
	
	// {{ kurucular
    public IntAramaA�ac�2 (){
    	
    }
	public IntAramaA�ac�2(int de�er){
		b�y�k = null;
		k���k = null;
		this.say� = de�er;
	}
	/** 
	 * de�erlerin s�ral� olup olmad���na bakar s�ral� olup olmad��� di�er i� a�a�lar i�indir
	 * <br> d��ard�dan olu�tururken s�ral� bilgisinin verilmesi �nemsizdir.Tekrar tekrar bakmas�n� �nlemek i�indir
	 * @param de�erler
	 */
	public IntAramaA�ac�2(int [] de�erler,boolean s�ral�M�) {
	   	b�y�k = null;
	   	k���k = null;
	   	if(s�ral�M�){
	   		s�ral�De�erleriDoldur(de�erler);	
	   	}
	   	else {
	   		s�ral�d�ktanSonraDoldur(de�erler);	
	   	}
	   	elemanSay�s� = de�erler.length;
	}
	private void s�ral�d�ktanSonraDoldur(int[] de�erler) {
		s�rala(de�erler);
		enB�y�kDe�er = de�erler[de�erler.length-1];
		enK���kDe�er = de�erler[0];
		if(de�erler.length <= 3){ // demek ki son a�amaya geldik
			sonA�amay�Ekle(de�erler); 
		}
		else {
			araA�amalar�Ekle(de�erler);
		}
	}
	private void araA�amalar�Ekle(int[] de�erler) {
		this.say� = medyanBul(de�erler);
		int [] k����eGidecek = k����eGidecekDizi(de�erler);
		int [] b�y��eGidecek = b�y��eGidecekDizi(de�erler);
		b�y�k = new IntAramaA�ac�2(b�y��eGidecek,true);
		k���k = new IntAramaA�ac�2(k����eGidecek,true);
	}
	private void s�ral�De�erleriDoldur(int[] de�erler) {
		enB�y�kDe�er = de�erler[de�erler.length-1];
		enK���kDe�er = de�erler[0];
		if(de�erler.length <= 3){ // demek ki son a�amaya geldik
			sonA�amay�Ekle(de�erler); 
		}
		else {
			araA�amalar�Ekle(de�erler);
		}
	}
	/**
	 * bo� dizi gelmemeli
	 * @param de�erler
	 */
	private void sonA�amay�Ekle(int[] de�erler) {
		if(de�erler.length == 3){
			k���kEkle(de�erler[0]);  
			this.say� = de�erler[1];  //  medyan kendisi
			b�y�kEkle(de�erler[2]);
		}
		else if(de�erler.length == 2) { // buras� kabule bak�yor k����� yukar� almay� tercih ettik
			this.say� = de�erler[0];
			b�y�kEkle(de�erler[1]);
		}
		else { 
			this.say� = de�erler[0];
		}
		elemanSay�s�++;
	}
	
	public IntAramaA�ac�2 (String yaz�, String ay�r�c�){
		String [] b�l�nm�� = ay�r�c�.split(ay�r�c�);
		int [] de�erler = new int [b�l�nm��.length];
		int elemanNo;
		for(elemanNo=0;elemanNo<de�erler.length;elemanNo++){
			de�erler [elemanNo] = Integer.parseInt(b�l�nm��[elemanNo]);
		}
		new IntAramaA�ac�2(de�erler, false);
	}
	/**
	 * ilk �nce yaz�y� ay�r�c� ile b�ler sonra ba�lama yerinden itibaren okur ve diziye yazar 
	 * <br> sonra da a�ac� olu�turur
	 * @param yaz�
	 * @param ba�lamaYeri
	 */
	public IntAramaA�ac�2 (String yaz�,int ba�lamaYeri ,String ay�r�c�){
		String [] b�l�nm�� = ay�r�c�.split(ay�r�c�);
		int [] de�erler = new int [b�l�nm��.length-ba�lamaYeri];
		int elemanNo;
		for(elemanNo=ba�lamaYeri;elemanNo<de�erler.length;elemanNo++){
			de�erler [elemanNo-ba�lamaYeri] = Integer.parseInt(b�l�nm��[elemanNo]);
		}
		new IntAramaA�ac�2(de�erler, false);
	}
	public IntAramaA�ac�2 (String [] yaz�,int ba�lamaYeri){
		int [] de�erler = new int [yaz�.length-ba�lamaYeri];
		int elemanNo;
		for(elemanNo=ba�lamaYeri;elemanNo<de�erler.length;elemanNo++){
			de�erler [elemanNo-ba�lamaYeri] = Integer.parseInt(yaz�[elemanNo]);
		}
		new IntAramaA�ac�2(de�erler, false);
	}
	
	// }} kurucular
    
    // {{ s�ralama ve geni�letme
	/** 
     * insertion sort uygular
     * @param de�erler
     */
	private void s�rala(int [] de�erler){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<de�erler.length;elemanNo++){
    		int enK�����nYeri = enK�����nYeriniBul(de�erler, elemanNo);
    		int enK���k = de�erler[enK�����nYeri];
    		de�erler [enK�����nYeri] = de�erler[elemanNo];
    		de�erler [elemanNo] = enK���k;
    	}
    	
    }
	private int enK�����nYeriniBul(int [] de�erler,int ba�){
		int elemanNo;
		int enK���k= de�erler[ba�];
		int yer = ba�;
		for(elemanNo=ba�+1;elemanNo<de�erler.length;elemanNo++){
			if(de�erler[elemanNo] <enK���k  ){
				enK���k = de�erler[elemanNo];
				yer = elemanNo;
			}
		}
		return yer;
	}
    private int medyanBul(int []de�erler){
		int medyan�nYeri = de�erler.length / 2 ;
		return de�erler[medyan�nYeri];
}
	
    public void b�y�kEkle(int de�er){
    	b�y�k = new IntAramaA�ac�2(de�er);
    	elemanSay�s� ++;
    }
    public void k���kEkle(int de�er){
    	k���k = new IntAramaA�ac�2(de�er);
    	elemanSay�s� ++;
    }
    private int [] k����eGidecekDizi(int [] de�erler){
    	int medyan�nYeri = de�erler.length / 2;
    	int [] gidecek = new int [medyan�nYeri];
    	int elemanNo;
    	for(elemanNo=0;elemanNo<medyan�nYeri;elemanNo++){
			gidecek [elemanNo] = de�erler[elemanNo];
	    }
    	return gidecek;	
    }
    private int [] b�y��eGidecekDizi(int [] de�erler){
    	//3,4,5,8,9,16,18,20,22,23,25
    	int medyan�nYeri = de�erler.length / 2;
    	int [] gidecek = new int [de�erler.length - medyan�nYeri - 1];
    	int elemanNo;
    	for(elemanNo=0;elemanNo<gidecek.length;elemanNo++){
			gidecek [elemanNo] = de�erler[elemanNo+medyan�nYeri+1];
    	}
    	return gidecek;	
    }
    
    /**    
     * 1 b�y�k kola git demek 0 k���k kola git demek
     * <br> �rnek kod = "10101000" gibi olacak
     * @param kod
     * @return
     */
    public IntAramaA�ac�2 eri�(String kod){
    	int noktaNo;
    	IntAramaA�ac�2 aranan = this;
    	for(noktaNo=0;noktaNo<kod.length();noktaNo++){
    		if(kod.charAt(noktaNo) == '1'){
    			aranan = aranan.b�y�k;
    		}
    		else {
    			aranan = aranan.k���k;
    		}
    	}
    	return aranan;
    }
    // }} s�ralama ve geni�letme
    // {{ arama

    public boolean ara(int de�er){
    	IntAramaA�ac�2 aranan = this;
    	if(de�er == aranan.say�){
    		return true;
    	}
    	else if(de�er > aranan.enB�y�kDe�er || de�er < aranan.enK���kDe�er){
    		return false;
    	}
    	else if( de�er < aranan.say� ){ // di�te bir noktaya geldik demektir
    		return de�erK���kkenAra(de�er, aranan);
    	}
    	else { // de�er > aranan.say� kal�r geriye
    		return de�erB�y�kkenAra(de�er, aranan);
    	}
    }
	private boolean de�erB�y�kkenAra(int de�er, IntAramaA�ac�2 aranan) {
		if(aranan.b�y�k == null){
			return false;
		}
		else {
			aranan = aranan.b�y�k;
			return aranan.ara(de�er);	
		}
	}
	private boolean de�erK���kkenAra(int de�er, IntAramaA�ac�2 aranan) {
		if(aranan.k���k == null){
			return false;
		}
		else {
			aranan = aranan.k���k;
			return aranan.ara(de�er);	
		}
	}
    
	/**
	 * bir say�dan daha b�y�k olan t�m de�erleri liste yapar
	 * @param altDe�er
	 * @param �stDe�er
	 * @return
	 */
	public ArrayList<Integer> b�y�kDe�eriBul(int altDe�er){
		ArrayList<Integer> de�erler = new ArrayList<Integer>();
    	if(altDe�er > enB�y�kDe�er){
    		return de�erler;
    	}
    	else{
    		return altDe�erOlurlu(altDe�er, de�erler);
         }
	}
	private ArrayList<Integer> altDe�erOlurlu(int altDe�er,ArrayList<Integer> de�erler) {
		if(say� > altDe�er ){ // say�n�n kendiis alt de�erden b�y�kse b�y�k kolundaki t�m de�erler listeye eklenebilir
			say�AltDe�erdenB�y�k(altDe�er, de�erler);
		}
		else{ // say� aranan de�erden k���k ve ya ona e�it
			b�y�kKoldaB�y�kDe�erAra(altDe�er, de�erler);	
		}	
		return de�erler;
	}
	private void b�y�kKoldaB�y�kDe�erAra(int altDe�er, ArrayList<Integer> de�erler) {
		if(b�y�k != null ){ // belki kendinden b�y�k de�erler aranan de�erden b�y�kt�r
			de�erler.addAll(b�y�k.b�y�kDe�eriBul(altDe�er));	
		}
	}
	private void say�AltDe�erdenB�y�k(int altDe�er,ArrayList<Integer> de�erler) {
		de�erler.add(say�); // daha b�y�k de�erler olabilir
		b�y�kKoluEkle(de�erler);	
		k���kKoldaB�y�kDe�erAra(altDe�er, de�erler);
	}
	private void k���kKoldaB�y�kDe�erAra(int altDe�er,
			ArrayList<Integer> de�erler) {
		if(k���k != null){ // say� �ok b�y�kt�r ve kendisinden k���k de�erler bile aranan de�erden b�y�kt�r
			de�erler.addAll(k���k.b�y�kDe�eriBul(altDe�er));
		}
	}
	private void b�y�kKoluEkle(ArrayList<Integer> de�erler) {
		if(b�y�k != null ){
		   de�erler.addAll(b�y�k.t�mDe�erleriListele());	
		}
	}
	
    public ArrayList<Integer> k���kDe�eriBul(int �stDe�er){
		ArrayList<Integer> de�erler = new ArrayList<Integer>();
    	if(�stDe�er < enK���kDe�er){
    		return de�erler;
    	}
    	else {
    		return �stDe�erOlurlu(�stDe�er, de�erler);
    	}
	}
	private ArrayList<Integer> �stDe�erOlurlu(int �stDe�er,ArrayList<Integer> de�erler) {
		if(say� < �stDe�er ){
			say��stDe�erdenK���k(�stDe�er, de�erler);
		} // say� aranan de�ere e�it ya da ondan daha b�y�k
		else {
			say��stDe�erdenB�y�k(�stDe�er, de�erler);	
		}
		return de�erler;
	}
	private void say��stDe�erdenB�y�k(int �stDe�er, ArrayList<Integer> de�erler) {
		if(k���k != null ){ // bir ithimal kendinden k���k de�erler olumlu sonu� verebilir ama
			// kendinden b�y�k de�erler olurlu sonu� vermez
			de�erler.addAll(k���k.k���kDe�eriBul(�stDe�er));	
		}
	}
	private void say��stDe�erdenK���k(int �stDe�er,ArrayList<Integer> de�erler) {
		de�erler.add(say�); // a�ac�n k���k kolunun alt�ndaki t�m de�erler kendi de�erinden k���kt�r 
		//bu nedenle e�er k���k kol varsat�m k���k kol eklenebilir
		k���kKoluEkle(de�erler);
		b�y�kKoldakiK���kleriEkle(�stDe�er, de�erler);
	}
	private void b�y�kKoldakiK���kleriEkle(int �stDe�er,	ArrayList<Integer> de�erler) {
		if(b�y�k !=null){
			de�erler.addAll(b�y�k.k���kDe�eriBul(�stDe�er));
		}
	}
	private void k���kKoluEkle(ArrayList<Integer> de�erler) {
		if(k���k != null){
		de�erler.addAll(k���k.t�mDe�erleriListele());
		}
	}
	
	private boolean alt�ndaNoktaVarM�(){
		if(b�y�k == null && k���k == null){
			return true;
		}
		else {
			return false;
		}
	}
    
	public ArrayList<Integer> aral�kBul(int altDe�er, int �stDe�er){
		ArrayList<Integer> de�erler = new ArrayList<Integer>();
		if(altDe�er < �stDe�er){ // zaten bu sa�ma olur aramaya gerek yok d�ng�lerde gelebilir
			if ( �stDe�er > enK���kDe�er && altDe�er < enB�y�kDe�er){ // aral�k olurlu aral�kta
				aramaYap(altDe�er, �stDe�er, de�erler);
			}	
		}
		return de�erler;
	}
	private void aramaYap(int altDe�er, int �stDe�er,ArrayList<Integer> de�erler) {
		boolean say�AltDe�erdenK���kM� = say� < altDe�er;
		boolean say��stDe�erdenB�y�kM� = say� > �stDe�er;
		if( say�AltDe�erdenK���kM� ){ // o zaman b�y�k taraf�na bakmal�y�z ama k���k tarafa gerek yok
		    b�y�kTaraftaAral�kAra(altDe�er, �stDe�er, de�erler);
		}
		else if ( say��stDe�erdenB�y�kM�){ // k���k taraf�na bakmakta fayda var , b�y�k tarafa gerek yok
			k���kTaraftaAral�kAra(altDe�er, �stDe�er, de�erler);
		}
		else { // say� arada bir yerdedir iki tarafa da bakmak gerekli hem de kendisini alal�m
			de�erler.add(say�);
			b�y�kTaraftaAral�kAra(altDe�er, �stDe�er, de�erler);
			k���kTaraftaAral�kAra(altDe�er, �stDe�er, de�erler);
		}
	}
	private void k���kTaraftaAral�kAra(int altDe�er, int �stDe�er, ArrayList<Integer> de�erler) {
		if(k���k != null){
			de�erler.addAll(k���k.aral�kBul(altDe�er, �stDe�er));
		}
	}
	private void b�y�kTaraftaAral�kAra(int altDe�er, int �stDe�er, ArrayList<Integer> de�erler) {
		if(b�y�k != null){
			de�erler.addAll(b�y�k.aral�kBul(altDe�er, �stDe�er));
		}
	}
 	
	// }} arama
	
    // {{ yazd�rma
	public ArrayList<Integer> t�mDe�erleriListele(){
		ArrayList<Integer> de�erler = new ArrayList<Integer>();
		de�erler.add(say�);
		b�y�kKoluEkle(de�erler);
		k���kKoluEkle(de�erler);
		return de�erler;
	}
	public String yaz(){
    	String yaz� = "";
    	if(b�y�k != null){
    		yaz� += "b�y�k kol de�eri : " + b�y�k.say� + "\n";
    	}
    	if(k���k != null){
    		yaz� += "k���k kol de�eri : " + k���k.say� + "\n";
    	}
    	if(this != null){
    		yaz� +=  say� + "\n";	
    	}
    	return yaz�;
    }
    public String t�mDe�erleriYaz(){
    	return t�mDe�erleriListele().toString();
    }
    // }} yazd�rma
    
    

}
