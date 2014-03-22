package s�k��t�r�lm��Ba�lant�l�;

import java.util.ArrayList;

import veriYap�lar�.BoolDizi;
import veriYap�lar�.BoolVekt�r;
import S�k��t�r�lm��MetaSezgisel.K�meleme2;
import S�k��t�r�lm��VeriYap�lar�.BoolEksikMatrisA�a�l�;
/**
 * ilk �nce en iyi sutun dizili�ini bulacak
 * <br>-------------sutun dizili�i y�ntemi ----------------
 * <br> en iyi par�a dizili�leri bo� olarak olu�turulur kullan�lan fonk : par�alarEn�yiDizili�leriOlu�tur();
 * <br> par�a "1" i�in ��z�m yap�lacaksa ��yle olur
 * <br> 
 * @author Furkan
 *
 */

public class s�k��t�r�lm��Ba�Enerji extends K�meleme2{

	ArrayList<ArrayList<Integer>> par�aEn�yiDizili� = new ArrayList<ArrayList<Integer>>(); // t�m par�alar i�in en iyi dizili�ler
	ArrayList<Integer> par�alarEn�yiDizili� = new ArrayList<Integer>(); // t�m par�alar�n en iyisinin en iyisi
	int [][] par�alar�nBenzerli�i = new int [par�aSay�s�()][]; // tekrar l� br �ekilde kullan�ld��� i�in 
	
	ArrayList<ArrayList<Integer>> makineEn�yiDizili� = new ArrayList<ArrayList<Integer>>(); // t�m par�alar i�in en iyi dizili�ler
	ArrayList<Integer> makinelerEn�yiDizili� = new ArrayList<Integer>(); // t�m par�alar�n en iyisinin en iyisi
	int [][] makinelerinBenzerli�i = new int [makineSay�s�()][];// tekrar l� br �ekilde kullan�ld��� i�in
	
	int [] denemeler ; // enerjisi hesaplanacak en iyisi en iyi dizili� olacak
	int ekVeriler []; // 
    String yaz� = ""; // ��z�m� tutar
    int a�amaNo = 0;
    
	public s�k��t�r�lm��Ba�Enerji(BoolEksikMatrisA�a�l� par�aMakine, String[] makine�simleri, String[] par�a�simleri) {
		super(par�aMakine, makine�simleri, par�a�simleri);
		par�alar�nBenzerli�iniOlu�tur();
		makinelerinBenzerli�iniOlu�tur();
	}
	public s�k��t�r�lm��Ba�Enerji(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
		par�alar�nBenzerli�iniOlu�tur();
		makinelerinBenzerli�iniOlu�tur();
	}
	public s�k��t�r�lm��Ba�Enerji(String yaz�) throws Exception {
		super(yaz�);
		par�alar�nBenzerli�iniOlu�tur();
		makinelerinBenzerli�iniOlu�tur();
	}
    
	// sutunlar k�sm�
	private void par�alarEn�yiDizili�leriOlu�tur(){
		int par�aNo;
		for(par�aNo=0;par�aNo<par�aSay�s�();par�aNo++){
			ArrayList<Integer> a�ama = new ArrayList<Integer>();
			a�ama.add(par�aNo);
			par�aEn�yiDizili�.add(a�ama);
		}
	}
	/**
	 * en iyi dizili� i�inde olmayanlar ek sutunlar�na dahil edilir
	 */
	private void ekVerilerDizisiniOlu�turSutun(){
		ArrayList<Integer> dizili� = par�aEn�yiDizili�.get(a�amaNo);
		ekVeriler = new int [par�aSay�s�()- dizili�.size()];
		int elemanNo,ekSutunuNo = 0;
		for(elemanNo=0;elemanNo<par�aSay�s�();elemanNo++){
			if(dizili�.contains(elemanNo)== false){
				ekVeriler[ekSutunuNo] = elemanNo;
				ekSutunuNo++;
			}
		}
	}
    private void par�alar�nBenzerli�iniOlu�tur(){
    	int par�aNo;
    	for(par�aNo=0;par�aNo<par�aSay�s�();par�aNo++){
    		par�aSat�r�Doldur(par�aNo);
    	}
    }
    private void par�aSat�r�Doldur(int par�aNo){
    	par�alar�nBenzerli�i [par�aNo] = new int [par�aNo+1];
    	int par�a2;
    	for(par�a2=0;par�a2<par�alar�nBenzerli�i[par�aNo].length;par�a2++){
    		par�alar�nBenzerli�i [par�aNo][par�a2] = ba�EnerjisiHesapla(par�aNo, par�a2);
    		System.out.print(par�alar�nBenzerli�i [par�aNo][par�a2] + ",");
    	}
    	System.out.print("\n");
    }
    /**
     * b�y�k olan sat�rdan k���k  olan sutundan bulunacak
     * @param par�a1
     * @param par�a2
     * @return
     */
	public int par�aBenzerlikEri�(int par�a1, int par�a2){
		if(par�a1 > par�a2){
			return par�alar�nBenzerli�i[par�a1] [par�a2];
		}
		else{
			return par�alar�nBenzerli�i[par�a2] [par�a1];
		}
	}
    /** 
     * iki par�an�n ba� enerjisini hesaplar 
     * @param par�a1
     * @param par�a2
     * @return
     */
    private int ba�EnerjisiHesapla(int par�a1,int par�a2){
        int sat�rNo,enerji= 0;
        for(sat�rNo=0;sat�rNo<makineSay�s�();sat�rNo++){
        	if(par�aMakine.eri�(sat�rNo, par�a1) && par�aMakine.eri�(sat�rNo, par�a2)){
        		enerji ++;
        	}
        }
        return enerji;
    }
    /**
     * her a�amada en iyi dizili�e ek olarak bir sutun gelecek ama
     * <br> farkl� taraflar�na gelebilir mesela en iyi dizili� 1 , 2 ,3  ise ve ek sutun 4 ise 
     * <br> �u dizilimler olabilir
     * <br> 4,1,2,3 ; 1,4,2,3 ; 1,2,4,3 ; 1,2,3,4 
     * <br> bunlar� tek tek ek sutun hangi konumda en iyi enerjiyi verir onu bulucaz   
     * <br> geri de�er d�nerken �unu yapar 
     * <br> dizi [0] = enerji
     * <br> dizi [1] = konum
     * <br> UYARI : deneme sutunlar� dizisi dolu olmal�d�r
     * @param ekSutun
     * @return
     */
    private int[] ekSutun�leEn�yiEnerjiKonumHesapla(int ekSutun){
    	denemeSutunlar��lkDe�erAtama();
    	denemeler[0] = ekSutun;
    	int [] enerjiVeKonum = new int [2];
    	int ekSutunKonumu;
    	enerjiVeKonum [0] = denemeSutunlar�n�nEnerjisiniHesapla(); // ilk deneme i�in uygun bir enerji
    	enerjiVeKonum [1] = 0; // en iyi konum 0 olsun 
    	for(ekSutunKonumu = 1;ekSutunKonumu<denemeler.length;++ekSutunKonumu){
    		ekSutunKonumu��inEnerjiK�yasla(enerjiVeKonum, ekSutunKonumu);
    	}
    	return enerjiVeKonum;
    }
	private void ekSutunKonumu��inEnerjiK�yasla(int[] enerjiVeKonum, int ekSutunKonumu) {
		int yeniEnerji = denemeSutunlar�n�nEnerjisiniHesapla();
		if(yeniEnerji > enerjiVeKonum [0]){
			enerjiVeKonum [0] = yeniEnerji;
			enerjiVeKonum [1] = ekSutunKonumu;
		}
		ekVeriyi�leriAl(ekSutunKonumu);
	}
	/** 
	 * olu�turma ve en iyi k�sm� aktarma i�ini yapar
	 */
    private void denemeSutunlar��lkDe�erAtama() {
    	ArrayList<Integer> dizili� = par�aEn�yiDizili�.get(a�amaNo);
		denemeler = new int [dizili�.size()+1]; // en iyi dizili� + ek sutun
		en�yiDizili�iDenemeyeAktarSutun();
	}
    private int denemeSutunlar�n�nEnerjisiniHesapla(){
    	int par�aNo;
    	int enerji = 0;
    	for(par�aNo = 0;par�aNo<denemeler.length-1;par�aNo++){
    		enerji += par�aBenzerlikEri�(denemeler[par�aNo],denemeler[par�aNo +1]);
    	}
    	return enerji;
    }

    /**
     * deneme sutunlar�n�n ilk k�sm� en iyi dizili� dizisidir bunlar� aktar�caz sonra da  ek sutun gelecek onun yeir de�i�ebilir 
     */
    private void en�yiDizili�iDenemeyeAktarSutun(){
    	ArrayList<Integer> dizili� = par�aEn�yiDizili�.get(a�amaNo);
    	int elemanNo;
    	for(elemanNo=0;elemanNo<dizili�.size();elemanNo++){
    		denemeler [elemanNo+1] = dizili�.get(elemanNo);
    	}
    }
    private void ekVeriyi�leriAl(int ekVeriKonumu){
         int ekVerininDe�eri = denemeler [ekVeriKonumu] ;
         denemeler [ekVeriKonumu] = denemeler [ekVeriKonumu+1];
         denemeler [ekVeriKonumu+1] = ekVerininDe�eri;
    }

    /**
     * t�m ekSutunlar� t�m konumlarda dener ve en iyi enerjiyi veren ek sutununun kendisini ve konumunu bulur
     * dizi [0] = en iyi ek sutun kim 
     * dizi [1] = en iyi konumu neresi
     * dizi [2] = en y�ksek enerji
     * @return
     */
    private int[] t�mEkSutunlar�nEn�yiEnerjiVereniniHesapla(){
    	ekVerilerDizisiniOlu�turSutun();
    	int ekSutunNo; 
    	int [] sutunKonumEnerji = new int [3];
    	sutunKonumEnerji [0] = ekVeriler[0]; // ba�lang�� ��z�m�
    	int [] enerjiKonum = ekSutun�leEn�yiEnerjiKonumHesapla(ekVeriler[0]);
    	sutunKonumEnerji [1] = enerjiKonum[1]; // ba�lang�� ��z�m�
    	sutunKonumEnerji [2] = enerjiKonum[0]; // ba�lang��
    	for(ekSutunNo=0;ekSutunNo<ekVeriler.length;ekSutunNo++){
    		yeniEnerjiVeKonumK�yaslaSutun(ekSutunNo, sutunKonumEnerji);
    	}
    	return sutunKonumEnerji;
    }
	private void yeniEnerjiVeKonumK�yaslaSutun(int ekSutunNo, int[] sutunKonumEnerji) {
		int [] yeniEnerjiKonum  = ekSutun�leEn�yiEnerjiKonumHesapla(ekVeriler[ekSutunNo]);
		if(yeniEnerjiKonum [0] > sutunKonumEnerji [2]){
			sutunKonumEnerji [0] = ekVeriler[ekSutunNo]; // en iyi ek sutun kim
			sutunKonumEnerji [1] = yeniEnerjiKonum[1];    // neredeyken en iyi ( konumu neresi)
			sutunKonumEnerji [2] = yeniEnerjiKonum [0];  // yeni eny�ksek enerji
		}
	}

    public int par�a��in��z(int par�aNo) throws Exception{
    	a�amaNo = par�aNo;
    	int par�a;
    	int [] sutunKonumEnerji = new int [3];
    	for(par�a=0;par�a<par�aSay�s�()-1;par�a++){
    		sutunKonumEnerji = t�mEkSutunlar�nEn�yiEnerjiVereniniHesapla();
    		yeniEleman�Yerle�tirPar�a(sutunKonumEnerji[0], sutunKonumEnerji[1]);
    	}
    	yaz� += "en iyi dizili� : " + par�aEn�yiDizili�.get(par�aNo).toString() + "enerji : " +  sutunKonumEnerji[2] + "\n\n";
    	yaz� += "bu dizili� i�in par�a makine matrisi : \n" + sutunDizili�indenPar�aMakineMatrisiOlu�tur(par�aEn�yiDizili�.get(par�aNo)).yaz() + "\n\n";
        return sutunKonumEnerji[2];
    }
    /**
     * yeni eleman� en iyi dizili�e dahil eder
     * @param yeniPar�a
     * @param konum
     */
    private void yeniEleman�Yerle�tirPar�a(int yeniPar�a, int konum){
    	ArrayList<Integer> dizili� = par�aEn�yiDizili�.get(a�amaNo);
    	if(konum >= dizili�.size()){ // demek ki eleman sona geleecek
    		 dizili� .add(yeniPar�a);
    	}
    	else { // eleman araya girecek
    		 dizili� .add(konum, yeniPar�a);
    	}
    }
    /**
     * dizi [0] = en iyi ��z�m hangi makine ile ba�lan�nca sa�lan�yor 
     * <br>dizi [1] = en iyi enerji ne kadar
     * @return
     * @throws Exception 
     */
    public int [] t�mPar�alar��in��z() throws Exception{
    	par�alarEn�yiDizili�leriOlu�tur();
    	int [] en�yiPar�aEnerji = new int [2]; 
       	en�yiPar�aEnerji [0] = 0;
       	yaz� += "0 par�as� i�in ��z�l�yor \n" ;
       	en�yiPar�aEnerji [1] = par�a��in��z(0);
    	int par�aNo;
    	for(par�aNo=1;par�aNo<par�aSay�s�();par�aNo++){
    		yaz� += par�aNo + " par�as� i�in ��z�l�yor \n" ;
    		yeniEnerjiK�yasalaPar�a(en�yiPar�aEnerji, par�aNo);
    		yaz� += "\n\n";
    		her�eyiS�f�rla();
    	}
    	// en iyi enerjisi olan� ak�lda tutmak laz�m
    	par�alarEn�yiDizili� = par�aEn�yiDizili�.get(en�yiPar�aEnerji[0]);
    	return en�yiPar�aEnerji;
    }
	private void yeniEnerjiK�yasalaPar�a(int[] en�yiPar�aEnerji, int par�aNo) throws Exception {
		int yeniEnerji = par�a��in��z(par�aNo);
		if(yeniEnerji > en�yiPar�aEnerji[1]){
			en�yiPar�aEnerji [1] = yeniEnerji;
			en�yiPar�aEnerji [0] = par�aNo;
		}
	}
    private void her�eyiS�f�rla(){
    	denemeler = null;
    	ekVeriler = null;
    }
    
    // sutunlar biti�
    
    
    
    
    // sat�rlar benzerlik bulma
    private void ekVerilerDizisiniOlu�turSat�r(){
		ArrayList<Integer> dizili� = makineEn�yiDizili�.get(a�amaNo);
		ekVeriler = new int [makineSay�s�()- dizili�.size()];
		int elemanNo,ekSutunuNo = 0;
		for(elemanNo=0;elemanNo<makineSay�s�();elemanNo++){
			if(dizili�.contains(elemanNo)== false){
				ekVeriler[ekSutunuNo] = elemanNo;
				ekSutunuNo++;
			}
		}
	}
    private void makinelerEn�yiDizili�leriOlu�tur(){
		int makineNo;
		for(makineNo=0;makineNo<makineSay�s�();makineNo++){
			ArrayList<Integer> a�ama = new ArrayList<Integer>();
			a�ama.add(makineNo);
			makineEn�yiDizili�.add(a�ama);
		}
	}
    private void makinelerinBenzerli�iniOlu�tur(){
    	int makineNo;
    	for(makineNo=0;makineNo<makineSay�s�();makineNo++){
    		makineSat�r�Doldur(makineNo);
    	}
    }
    private void makineSat�r�Doldur(int makineNo){
    	makinelerinBenzerli�i[makineNo] = new int [makineNo+1];
    	int makine2;
    	for(makine2=0;makine2<makinelerinBenzerli�i[makineNo].length;makine2++){
    		makinelerinBenzerli�i [makineNo][makine2] = sat�rBenzerlikBul(makineNo, makine2);
    		System.out.print(makinelerinBenzerli�i [makineNo][makine2]  + ",");
    	}
    	System.out.print("\n");
    }
    /**
     * b�y�k olan sat�rdan k���k olan sutunadan bulunacak
     * @param makine1
     * @param makine2
     * @return
     */
    public int makineBenzerlikEri�(int makine1, int makine2){
    	if(makine1 > makine2){
    		return makinelerinBenzerli�i [makine1][makine2];
    	}
    	else {
    		return makinelerinBenzerli�i [makine2][makine1];
    	}
    }
    /**
    * makineler aras� benzerlik
     * @param sat�r1
     * @param sat�r2
     * @return
     */
    private int sat�rBenzerlikBul(int sat�r1, int sat�r2){
    	return par�aMakine.benzerlikBul(sat�r1, sat�r2);
    }
    /**
    * her a�amada en iyi dizili�e ek olarak bir sutun gelecek ama
    * <br> farkl� taraflar�na gelebilir mesela en iyi dizili� 1 , 2 ,3  ise ve ek sutun 4 ise 
    * <br> �u dizilimler olabilir
    * <br> 4,1,2,3 ; 1,4,2,3 ; 1,2,4,3 ; 1,2,3,4 
    * <br> bunlar� tek tek ek sutun hangi konumda en iyi enerjiyi verir onu bulucaz   
    * <br> geri de�er d�nerken �unu yapar 
    * <br> dizi [0] = enerji
    * <br> dizi [1] = konum
    * <br> UYARI : deneme sutunlar� dizisi dolu olmal�d�r
    * @param ekSat�r
    * @return
    */
   private int[] ekSat�r�leEn�yiEnerjiKonumHesapla(int ekSat�r){
   	denemeSat�rlar��lkDe�erAtama();
   	denemeler[0] = ekSat�r;
   	int [] enerjiVeKonum = new int [2];
   	int eksat�rKonumu;
   	enerjiVeKonum [0] = denemeSat�rlar�EnerjisiniHesapla(); // ilk deneme i�in uygun bir enerji
   	enerjiVeKonum [1] = 0; // en iyi konum 0 olsun 
   	for(eksat�rKonumu = 1;eksat�rKonumu<denemeler.length;eksat�rKonumu++){
   		yeniEnerjiK�yaslaEkSat�r(enerjiVeKonum, eksat�rKonumu);
   		ekVeriyi�leriAl(eksat�rKonumu); // denemeler dizisini de�i�tirir
   	}
   	return enerjiVeKonum;
   }
	private void yeniEnerjiK�yaslaEkSat�r(int[] enerjiVeKonum, int ekSat�rKonumu) {
		int yeniEnerji = denemeSat�rlar�EnerjisiniHesapla();
   		if(yeniEnerji > enerjiVeKonum [0]){
   			enerjiVeKonum [0] = yeniEnerji;
   			enerjiVeKonum [1] = ekSat�rKonumu;
   		}
	}
   /**
    * makineler aras� benzerlikleri bulur
    * @return
    */
   private int denemeSat�rlar�EnerjisiniHesapla(){
   	int sat�rNo;
   	int enerji = 0;
   	for(sat�rNo = 0;sat�rNo<denemeler.length-1;sat�rNo++){
   		enerji += makineBenzerlikEri�(denemeler[sat�rNo],denemeler[sat�rNo+1]);
   	}
   	return enerji;
   }

   /**
    * t�m ekSutunlar� t�m konumlarda dener ve en iyi enerjiyi veren ek sutununun kendisini ve konumunu bulur
    * dizi [0] = en iyi ek sutun kim 
    * dizi [1] = en iyi konumu neresi
    * dizi [2] = en y�ksek enerji
    * @return
    */
   private int[] t�mEkSat�rlar�nEn�yiEnerjiVereniniHesapla(){
   	ekVerilerDizisiniOlu�turSat�r();
   	int ekSat�rNo; 
   	int [] sat�rKonumEnerji = new int [3];
   	sat�rKonumEnerjiDoldur(sat�rKonumEnerji);
   	for(ekSat�rNo=0;ekSat�rNo<ekVeriler.length;ekSat�rNo++){
   		yeniEnerjiVeKonumK�yaslaSat�r(ekSat�rNo, sat�rKonumEnerji);
   	}
   	return sat�rKonumEnerji;
   }
   private void sat�rKonumEnerjiDoldur(int[] sat�rKonumEnerji) {
	sat�rKonumEnerji [0] = ekVeriler[0]; // ba�lang�� ��z�m�
   	int [] enerjiKonum = ekSat�r�leEn�yiEnerjiKonumHesapla(ekVeriler[0]);
   	sat�rKonumEnerji [1] = enerjiKonum[1]; // ba�lang�� ��z�m�
   	sat�rKonumEnerji [2] = enerjiKonum[0]; // ba�lang��
}
   private void yeniEnerjiVeKonumK�yaslaSat�r(int ekSat�rNo, int[] sat�rKonumEnerji) {
		int [] yeniEnerjiKonum  = ekSat�r�leEn�yiEnerjiKonumHesapla(ekVeriler[ekSat�rNo]);
		if(yeniEnerjiKonum [0] > sat�rKonumEnerji [2]){
			sat�rKonumEnerji [0] = ekVeriler[ekSat�rNo]; // en iyi ek sutun kim
			sat�rKonumEnerji [1] = yeniEnerjiKonum[1];    // neredeyken en iyi ( konumu neresi)
			sat�rKonumEnerji [2] = yeniEnerjiKonum [0];  // yeni eny�ksek enerji
		}
	}

   public int makine��in��z(int makineNo) throws Exception{
	a�amaNo = makineNo;
   	int [] sat�rKonumEnerji = new int [3];
   	int makine;
   	for(makine=0;makine<makineSay�s�()-1;makine++){
   		sat�rKonumEnerji = t�mEkSat�rlar�nEn�yiEnerjiVereniniHesapla();
   		yeniEleman�Yerle�tirMakine(sat�rKonumEnerji[0], sat�rKonumEnerji[1]);
   	  }
   	yaz� += "en iyi dizili� : " + makineEn�yiDizili�.get(makineNo).toString() + "enerji : " +  sat�rKonumEnerji[2] + "\n\n";
   	yaz� += "bu dizili� i�in par�a makine matrisi : \n" + sat�rDizili�indenPar�aMakineMatrisiOlu�tur(makineEn�yiDizili�.get(makineNo)).yaz() + "\n\n";
    return sat�rKonumEnerji[2]; // enerjiyi geri d�ns�n
   }
   private void yeniEleman�Yerle�tirMakine(int yeniMakine, int konum) throws Exception{
	ArrayList<Integer> dizili� = makineEn�yiDizili�.get(a�amaNo);
   	if ( konum > dizili�.size() ){
   		throw new Exception("konum d��ar�da bir yerde \n girilen konum = " + konum + "\ndizili� : " + dizili�.toString() + "\n");
   	}
	if(konum == dizili�.size()){ // demek ki eleman sona geleecek
   		dizili�.add(yeniMakine);
   	}
   	else { // eleman araya girecek
   		dizili�.add(konum, yeniMakine);
   	}
   }
   /**
    * dizi [0] = en iyi ��z�m hangi makine ile ba�lan�nca sa�lan�yor 
   	* <br>dizi [1] = en iyi enerji ne kadar
    * @return
 * @throws Exception 
    */
   public int [] t�mMakineler��in��z() throws Exception{
	makinelerEn�yiDizili�leriOlu�tur();
   	int makineNo;
   	yaz� += "0 makinesi i�in ��z�l�yor \n" ;
   	int [] en�yiMakineEnerji = new int [2]; 
   	en�yiMakineEnerji [0] = 0;
   	en�yiMakineEnerji [1] = makine��in��z(0);
   	// dizi [0] = en iyi ��z�m hangi makine ile ba�lan�nca sa�lan�yor 
   	// dizi [1] = en iyi enerji ne kadar
   	for(makineNo=1;makineNo<makineSay�s�();makineNo++){
   		makineA�ama(makineNo, en�yiMakineEnerji);
   	}
   	return en�yiMakineEnerji;
   }
   private void makineA�ama(int makineNo, int[] en�yiMakineEnerji) throws Exception {
	yaz� += makineNo + " makinesi i�in ��z�l�yor \n" ;
	yeniEnerjiK�yaslaMakine(makineNo, en�yiMakineEnerji);
	yaz� += "\n\n";
	her�eyiS�f�rla();
}
   private void yeniEnerjiK�yaslaMakine(int makineNo, int[] en�yiMakineEnerji) throws Exception {
	int yeniEnerji = makine��in��z(makineNo);
	if(yeniEnerji > en�yiMakineEnerji[1]){
		en�yiMakineEnerji[0] = makineNo;
		en�yiMakineEnerji[1] = yeniEnerji;
	}
}
   private void en�yiDizili�iDenemeyeAktarSat�r(){
   	ArrayList<Integer> dizili� = makineEn�yiDizili�.get(a�amaNo);
	int elemanNo;
   	for(elemanNo=0;elemanNo<dizili�.size();elemanNo++){
   		denemeler [elemanNo+1] = dizili�.get(elemanNo);
   	}
   }
   
   private void denemeSat�rlar��lkDe�erAtama() {
	    ArrayList<Integer> dizili� = makineEn�yiDizili�.get(a�amaNo);
		denemeler = new int [dizili�.size()+1]; // en iyi dizili� + ek sutun
		en�yiDizili�iDenemeyeAktarSat�r();
	}
   
   public void t�mPar�alarVeMakineler��in��z() throws Exception{
	   int [] makineEnerji  = t�mMakineler��in��z();
	   int [] par�aEnerji = t�mPar�alar��in��z();
	   yaz� += "par�alar i�in en iyi dizili� : par�a no " + par�aEnerji[0] + " da ve " + par�aEnerji[1] + " kadar enerji ile sa�lanmaktad�r \n";
	   yaz� += "makinler i�in en iyi dizili� : makine no " + makineEnerji [0] + "da ve " + makineEnerji[1] + " kadar enerji ile sa�lanmaktad�r \n";
	   yaz� += "bulunan en iyi par�a makine matrisi : \n" + yeniPar�aMakineDizili�i(par�alarEn�yiDizili�, makinelerEn�yiDizili�).yaz() + "\n";
   }
   
   // in�aa etme
   public BoolDizi sutunDizili�indenPar�aMakineMatrisiOlu�tur(ArrayList<Integer> sutunDizili�) throws Exception{
	   BoolDizi yeniPar�aMakineMatrisi = new BoolDizi(makineSay�s�(), par�aSay�s�(), false);
	   int sutunNo;
	   for(sutunNo=0;sutunNo<sutunDizili�.size();sutunNo++){
		   BoolVekt�r yeniSutun = par�aMakine.sutun�ek(sutunDizili�.get(sutunNo));
		   yeniPar�aMakineMatrisi.sutunaYerle�(sutunNo, yeniSutun);
	   }
	   return yeniPar�aMakineMatrisi;
   }
   public BoolDizi sat�rDizili�indenPar�aMakineMatrisiOlu�tur(ArrayList<Integer> sat�rDizili�) throws Exception{
	   BoolDizi yeniPar�aMakineMatrisi = new BoolDizi(makineSay�s�(), par�aSay�s�(), false);
	   int sat�rNo;
	   for(sat�rNo=0;sat�rNo<sat�rDizili�.size();sat�rNo++){
		   BoolVekt�r sat�r = par�aMakine.sat�r�ek(sat�rNo);
		   yeniPar�aMakineMatrisi.sat�raYerle�(sat�rNo, sat�r);
	   }
	   return yeniPar�aMakineMatrisi;
   }

   public BoolDizi yeniPar�aMakineDizili�i(ArrayList<Integer> par�aDizili�, ArrayList<Integer> makineDizili�) throws Exception{
	   BoolDizi yeniPar�aMakine = new BoolDizi(makineSay�s�(), par�aSay�s�(), false);
	   int sat�r,sutun;
	   for(sat�r=0;sat�r<yeniPar�aMakine.sat�rSay();sat�r++){
		   for(sutun=0;sutun<yeniPar�aMakine.sutunSay();sutun++){
			   boolean yeniDe�er = par�aMakine.eri�(sat�r, sutun);
			   yeniPar�aMakine.de�i�(sat�r, sutun, yeniDe�er);
		   } 
	   }
	   return yeniPar�aMakine;
   }
   public String yaz(){
    	return yaz�;
    }
}
