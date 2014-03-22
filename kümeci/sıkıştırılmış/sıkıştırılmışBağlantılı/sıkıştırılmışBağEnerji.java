package sıkıştırılmışBağlantılı;

import java.util.ArrayList;

import veriYapıları.BoolDizi;
import veriYapıları.BoolVektör;
import SıkıştırılmışMetaSezgisel.Kümeleme2;
import SıkıştırılmışVeriYapıları.BoolEksikMatrisAğaçlı;
/**
 * ilk önce en iyi sutun dizilişini bulacak
 * <br>-------------sutun dizilişi yöntemi ----------------
 * <br> en iyi parça dizilişleri boş olarak oluşturulur kullanılan fonk : parçalarEnİyiDizilişleriOluştur();
 * <br> parça "1" için çözüm yapılacaksa şöyle olur
 * <br> 
 * @author Furkan
 *
 */

public class sıkıştırılmışBağEnerji extends Kümeleme2{

	ArrayList<ArrayList<Integer>> parçaEnİyiDiziliş = new ArrayList<ArrayList<Integer>>(); // tüm parçalar için en iyi dizilişler
	ArrayList<Integer> parçalarEnİyiDiziliş = new ArrayList<Integer>(); // tüm parçaların en iyisinin en iyisi
	int [][] parçalarınBenzerliği = new int [parçaSayısı()][]; // tekrar lı br şekilde kullanıldığı için 
	
	ArrayList<ArrayList<Integer>> makineEnİyiDiziliş = new ArrayList<ArrayList<Integer>>(); // tüm parçalar için en iyi dizilişler
	ArrayList<Integer> makinelerEnİyiDiziliş = new ArrayList<Integer>(); // tüm parçaların en iyisinin en iyisi
	int [][] makinelerinBenzerliği = new int [makineSayısı()][];// tekrar lı br şekilde kullanıldığı için
	
	int [] denemeler ; // enerjisi hesaplanacak en iyisi en iyi diziliş olacak
	int ekVeriler []; // 
    String yazı = ""; // çözümü tutar
    int aşamaNo = 0;
    
	public sıkıştırılmışBağEnerji(BoolEksikMatrisAğaçlı parçaMakine, String[] makineİsimleri, String[] parçaİsimleri) {
		super(parçaMakine, makineİsimleri, parçaİsimleri);
		parçalarınBenzerliğiniOluştur();
		makinelerinBenzerliğiniOluştur();
	}
	public sıkıştırılmışBağEnerji(String yazı, char ayırıcı) throws Exception {
		super(yazı, ayırıcı);
		parçalarınBenzerliğiniOluştur();
		makinelerinBenzerliğiniOluştur();
	}
	public sıkıştırılmışBağEnerji(String yazı) throws Exception {
		super(yazı);
		parçalarınBenzerliğiniOluştur();
		makinelerinBenzerliğiniOluştur();
	}
    
	// sutunlar kısmı
	private void parçalarEnİyiDizilişleriOluştur(){
		int parçaNo;
		for(parçaNo=0;parçaNo<parçaSayısı();parçaNo++){
			ArrayList<Integer> aşama = new ArrayList<Integer>();
			aşama.add(parçaNo);
			parçaEnİyiDiziliş.add(aşama);
		}
	}
	/**
	 * en iyi diziliş içinde olmayanlar ek sutunlarına dahil edilir
	 */
	private void ekVerilerDizisiniOluşturSutun(){
		ArrayList<Integer> diziliş = parçaEnİyiDiziliş.get(aşamaNo);
		ekVeriler = new int [parçaSayısı()- diziliş.size()];
		int elemanNo,ekSutunuNo = 0;
		for(elemanNo=0;elemanNo<parçaSayısı();elemanNo++){
			if(diziliş.contains(elemanNo)== false){
				ekVeriler[ekSutunuNo] = elemanNo;
				ekSutunuNo++;
			}
		}
	}
    private void parçalarınBenzerliğiniOluştur(){
    	int parçaNo;
    	for(parçaNo=0;parçaNo<parçaSayısı();parçaNo++){
    		parçaSatırıDoldur(parçaNo);
    	}
    }
    private void parçaSatırıDoldur(int parçaNo){
    	parçalarınBenzerliği [parçaNo] = new int [parçaNo+1];
    	int parça2;
    	for(parça2=0;parça2<parçalarınBenzerliği[parçaNo].length;parça2++){
    		parçalarınBenzerliği [parçaNo][parça2] = bağEnerjisiHesapla(parçaNo, parça2);
    		System.out.print(parçalarınBenzerliği [parçaNo][parça2] + ",");
    	}
    	System.out.print("\n");
    }
    /**
     * büyük olan satırdan küçük  olan sutundan bulunacak
     * @param parça1
     * @param parça2
     * @return
     */
	public int parçaBenzerlikEriş(int parça1, int parça2){
		if(parça1 > parça2){
			return parçalarınBenzerliği[parça1] [parça2];
		}
		else{
			return parçalarınBenzerliği[parça2] [parça1];
		}
	}
    /** 
     * iki parçanın bağ enerjisini hesaplar 
     * @param parça1
     * @param parça2
     * @return
     */
    private int bağEnerjisiHesapla(int parça1,int parça2){
        int satırNo,enerji= 0;
        for(satırNo=0;satırNo<makineSayısı();satırNo++){
        	if(parçaMakine.eriş(satırNo, parça1) && parçaMakine.eriş(satırNo, parça2)){
        		enerji ++;
        	}
        }
        return enerji;
    }
    /**
     * her aşamada en iyi dizilişe ek olarak bir sutun gelecek ama
     * <br> farklı taraflarına gelebilir mesela en iyi diziliş 1 , 2 ,3  ise ve ek sutun 4 ise 
     * <br> şu dizilimler olabilir
     * <br> 4,1,2,3 ; 1,4,2,3 ; 1,2,4,3 ; 1,2,3,4 
     * <br> bunları tek tek ek sutun hangi konumda en iyi enerjiyi verir onu bulucaz   
     * <br> geri değer dönerken şunu yapar 
     * <br> dizi [0] = enerji
     * <br> dizi [1] = konum
     * <br> UYARI : deneme sutunları dizisi dolu olmalıdır
     * @param ekSutun
     * @return
     */
    private int[] ekSutunİleEnİyiEnerjiKonumHesapla(int ekSutun){
    	denemeSutunlarıİlkDeğerAtama();
    	denemeler[0] = ekSutun;
    	int [] enerjiVeKonum = new int [2];
    	int ekSutunKonumu;
    	enerjiVeKonum [0] = denemeSutunlarınınEnerjisiniHesapla(); // ilk deneme için uygun bir enerji
    	enerjiVeKonum [1] = 0; // en iyi konum 0 olsun 
    	for(ekSutunKonumu = 1;ekSutunKonumu<denemeler.length;++ekSutunKonumu){
    		ekSutunKonumuİçinEnerjiKıyasla(enerjiVeKonum, ekSutunKonumu);
    	}
    	return enerjiVeKonum;
    }
	private void ekSutunKonumuİçinEnerjiKıyasla(int[] enerjiVeKonum, int ekSutunKonumu) {
		int yeniEnerji = denemeSutunlarınınEnerjisiniHesapla();
		if(yeniEnerji > enerjiVeKonum [0]){
			enerjiVeKonum [0] = yeniEnerji;
			enerjiVeKonum [1] = ekSutunKonumu;
		}
		ekVeriyiİleriAl(ekSutunKonumu);
	}
	/** 
	 * oluşturma ve en iyi kısmı aktarma işini yapar
	 */
    private void denemeSutunlarıİlkDeğerAtama() {
    	ArrayList<Integer> diziliş = parçaEnİyiDiziliş.get(aşamaNo);
		denemeler = new int [diziliş.size()+1]; // en iyi diziliş + ek sutun
		enİyiDizilişiDenemeyeAktarSutun();
	}
    private int denemeSutunlarınınEnerjisiniHesapla(){
    	int parçaNo;
    	int enerji = 0;
    	for(parçaNo = 0;parçaNo<denemeler.length-1;parçaNo++){
    		enerji += parçaBenzerlikEriş(denemeler[parçaNo],denemeler[parçaNo +1]);
    	}
    	return enerji;
    }

    /**
     * deneme sutunlarının ilk kısmı en iyi diziliş dizisidir bunları aktarıcaz sonra da  ek sutun gelecek onun yeir değişebilir 
     */
    private void enİyiDizilişiDenemeyeAktarSutun(){
    	ArrayList<Integer> diziliş = parçaEnİyiDiziliş.get(aşamaNo);
    	int elemanNo;
    	for(elemanNo=0;elemanNo<diziliş.size();elemanNo++){
    		denemeler [elemanNo+1] = diziliş.get(elemanNo);
    	}
    }
    private void ekVeriyiİleriAl(int ekVeriKonumu){
         int ekVerininDeğeri = denemeler [ekVeriKonumu] ;
         denemeler [ekVeriKonumu] = denemeler [ekVeriKonumu+1];
         denemeler [ekVeriKonumu+1] = ekVerininDeğeri;
    }

    /**
     * tüm ekSutunları tüm konumlarda dener ve en iyi enerjiyi veren ek sutununun kendisini ve konumunu bulur
     * dizi [0] = en iyi ek sutun kim 
     * dizi [1] = en iyi konumu neresi
     * dizi [2] = en yüksek enerji
     * @return
     */
    private int[] tümEkSutunlarınEnİyiEnerjiVereniniHesapla(){
    	ekVerilerDizisiniOluşturSutun();
    	int ekSutunNo; 
    	int [] sutunKonumEnerji = new int [3];
    	sutunKonumEnerji [0] = ekVeriler[0]; // başlangıç çözümü
    	int [] enerjiKonum = ekSutunİleEnİyiEnerjiKonumHesapla(ekVeriler[0]);
    	sutunKonumEnerji [1] = enerjiKonum[1]; // başlangıç çözümü
    	sutunKonumEnerji [2] = enerjiKonum[0]; // başlangıç
    	for(ekSutunNo=0;ekSutunNo<ekVeriler.length;ekSutunNo++){
    		yeniEnerjiVeKonumKıyaslaSutun(ekSutunNo, sutunKonumEnerji);
    	}
    	return sutunKonumEnerji;
    }
	private void yeniEnerjiVeKonumKıyaslaSutun(int ekSutunNo, int[] sutunKonumEnerji) {
		int [] yeniEnerjiKonum  = ekSutunİleEnİyiEnerjiKonumHesapla(ekVeriler[ekSutunNo]);
		if(yeniEnerjiKonum [0] > sutunKonumEnerji [2]){
			sutunKonumEnerji [0] = ekVeriler[ekSutunNo]; // en iyi ek sutun kim
			sutunKonumEnerji [1] = yeniEnerjiKonum[1];    // neredeyken en iyi ( konumu neresi)
			sutunKonumEnerji [2] = yeniEnerjiKonum [0];  // yeni enyüksek enerji
		}
	}

    public int parçaİçinÇöz(int parçaNo) throws Exception{
    	aşamaNo = parçaNo;
    	int parça;
    	int [] sutunKonumEnerji = new int [3];
    	for(parça=0;parça<parçaSayısı()-1;parça++){
    		sutunKonumEnerji = tümEkSutunlarınEnİyiEnerjiVereniniHesapla();
    		yeniElemanıYerleştirParça(sutunKonumEnerji[0], sutunKonumEnerji[1]);
    	}
    	yazı += "en iyi diziliş : " + parçaEnİyiDiziliş.get(parçaNo).toString() + "enerji : " +  sutunKonumEnerji[2] + "\n\n";
    	yazı += "bu diziliş için parça makine matrisi : \n" + sutunDizilişindenParçaMakineMatrisiOluştur(parçaEnİyiDiziliş.get(parçaNo)).yaz() + "\n\n";
        return sutunKonumEnerji[2];
    }
    /**
     * yeni elemanı en iyi dizilişe dahil eder
     * @param yeniParça
     * @param konum
     */
    private void yeniElemanıYerleştirParça(int yeniParça, int konum){
    	ArrayList<Integer> diziliş = parçaEnİyiDiziliş.get(aşamaNo);
    	if(konum >= diziliş.size()){ // demek ki eleman sona geleecek
    		 diziliş .add(yeniParça);
    	}
    	else { // eleman araya girecek
    		 diziliş .add(konum, yeniParça);
    	}
    }
    /**
     * dizi [0] = en iyi çözüm hangi makine ile başlanınca sağlanıyor 
     * <br>dizi [1] = en iyi enerji ne kadar
     * @return
     * @throws Exception 
     */
    public int [] tümParçalarİçinÇöz() throws Exception{
    	parçalarEnİyiDizilişleriOluştur();
    	int [] enİyiParçaEnerji = new int [2]; 
       	enİyiParçaEnerji [0] = 0;
       	yazı += "0 parçası için çözülüyor \n" ;
       	enİyiParçaEnerji [1] = parçaİçinÇöz(0);
    	int parçaNo;
    	for(parçaNo=1;parçaNo<parçaSayısı();parçaNo++){
    		yazı += parçaNo + " parçası için çözülüyor \n" ;
    		yeniEnerjiKıyasalaParça(enİyiParçaEnerji, parçaNo);
    		yazı += "\n\n";
    		herşeyiSıfırla();
    	}
    	// en iyi enerjisi olanı akılda tutmak lazım
    	parçalarEnİyiDiziliş = parçaEnİyiDiziliş.get(enİyiParçaEnerji[0]);
    	return enİyiParçaEnerji;
    }
	private void yeniEnerjiKıyasalaParça(int[] enİyiParçaEnerji, int parçaNo) throws Exception {
		int yeniEnerji = parçaİçinÇöz(parçaNo);
		if(yeniEnerji > enİyiParçaEnerji[1]){
			enİyiParçaEnerji [1] = yeniEnerji;
			enİyiParçaEnerji [0] = parçaNo;
		}
	}
    private void herşeyiSıfırla(){
    	denemeler = null;
    	ekVeriler = null;
    }
    
    // sutunlar bitiş
    
    
    
    
    // satırlar benzerlik bulma
    private void ekVerilerDizisiniOluşturSatır(){
		ArrayList<Integer> diziliş = makineEnİyiDiziliş.get(aşamaNo);
		ekVeriler = new int [makineSayısı()- diziliş.size()];
		int elemanNo,ekSutunuNo = 0;
		for(elemanNo=0;elemanNo<makineSayısı();elemanNo++){
			if(diziliş.contains(elemanNo)== false){
				ekVeriler[ekSutunuNo] = elemanNo;
				ekSutunuNo++;
			}
		}
	}
    private void makinelerEnİyiDizilişleriOluştur(){
		int makineNo;
		for(makineNo=0;makineNo<makineSayısı();makineNo++){
			ArrayList<Integer> aşama = new ArrayList<Integer>();
			aşama.add(makineNo);
			makineEnİyiDiziliş.add(aşama);
		}
	}
    private void makinelerinBenzerliğiniOluştur(){
    	int makineNo;
    	for(makineNo=0;makineNo<makineSayısı();makineNo++){
    		makineSatırıDoldur(makineNo);
    	}
    }
    private void makineSatırıDoldur(int makineNo){
    	makinelerinBenzerliği[makineNo] = new int [makineNo+1];
    	int makine2;
    	for(makine2=0;makine2<makinelerinBenzerliği[makineNo].length;makine2++){
    		makinelerinBenzerliği [makineNo][makine2] = satırBenzerlikBul(makineNo, makine2);
    		System.out.print(makinelerinBenzerliği [makineNo][makine2]  + ",");
    	}
    	System.out.print("\n");
    }
    /**
     * büyük olan satırdan küçük olan sutunadan bulunacak
     * @param makine1
     * @param makine2
     * @return
     */
    public int makineBenzerlikEriş(int makine1, int makine2){
    	if(makine1 > makine2){
    		return makinelerinBenzerliği [makine1][makine2];
    	}
    	else {
    		return makinelerinBenzerliği [makine2][makine1];
    	}
    }
    /**
    * makineler arası benzerlik
     * @param satır1
     * @param satır2
     * @return
     */
    private int satırBenzerlikBul(int satır1, int satır2){
    	return parçaMakine.benzerlikBul(satır1, satır2);
    }
    /**
    * her aşamada en iyi dizilişe ek olarak bir sutun gelecek ama
    * <br> farklı taraflarına gelebilir mesela en iyi diziliş 1 , 2 ,3  ise ve ek sutun 4 ise 
    * <br> şu dizilimler olabilir
    * <br> 4,1,2,3 ; 1,4,2,3 ; 1,2,4,3 ; 1,2,3,4 
    * <br> bunları tek tek ek sutun hangi konumda en iyi enerjiyi verir onu bulucaz   
    * <br> geri değer dönerken şunu yapar 
    * <br> dizi [0] = enerji
    * <br> dizi [1] = konum
    * <br> UYARI : deneme sutunları dizisi dolu olmalıdır
    * @param ekSatır
    * @return
    */
   private int[] ekSatırİleEnİyiEnerjiKonumHesapla(int ekSatır){
   	denemeSatırlarıİlkDeğerAtama();
   	denemeler[0] = ekSatır;
   	int [] enerjiVeKonum = new int [2];
   	int eksatırKonumu;
   	enerjiVeKonum [0] = denemeSatırlarıEnerjisiniHesapla(); // ilk deneme için uygun bir enerji
   	enerjiVeKonum [1] = 0; // en iyi konum 0 olsun 
   	for(eksatırKonumu = 1;eksatırKonumu<denemeler.length;eksatırKonumu++){
   		yeniEnerjiKıyaslaEkSatır(enerjiVeKonum, eksatırKonumu);
   		ekVeriyiİleriAl(eksatırKonumu); // denemeler dizisini değiştirir
   	}
   	return enerjiVeKonum;
   }
	private void yeniEnerjiKıyaslaEkSatır(int[] enerjiVeKonum, int ekSatırKonumu) {
		int yeniEnerji = denemeSatırlarıEnerjisiniHesapla();
   		if(yeniEnerji > enerjiVeKonum [0]){
   			enerjiVeKonum [0] = yeniEnerji;
   			enerjiVeKonum [1] = ekSatırKonumu;
   		}
	}
   /**
    * makineler arası benzerlikleri bulur
    * @return
    */
   private int denemeSatırlarıEnerjisiniHesapla(){
   	int satırNo;
   	int enerji = 0;
   	for(satırNo = 0;satırNo<denemeler.length-1;satırNo++){
   		enerji += makineBenzerlikEriş(denemeler[satırNo],denemeler[satırNo+1]);
   	}
   	return enerji;
   }

   /**
    * tüm ekSutunları tüm konumlarda dener ve en iyi enerjiyi veren ek sutununun kendisini ve konumunu bulur
    * dizi [0] = en iyi ek sutun kim 
    * dizi [1] = en iyi konumu neresi
    * dizi [2] = en yüksek enerji
    * @return
    */
   private int[] tümEkSatırlarınEnİyiEnerjiVereniniHesapla(){
   	ekVerilerDizisiniOluşturSatır();
   	int ekSatırNo; 
   	int [] satırKonumEnerji = new int [3];
   	satırKonumEnerjiDoldur(satırKonumEnerji);
   	for(ekSatırNo=0;ekSatırNo<ekVeriler.length;ekSatırNo++){
   		yeniEnerjiVeKonumKıyaslaSatır(ekSatırNo, satırKonumEnerji);
   	}
   	return satırKonumEnerji;
   }
   private void satırKonumEnerjiDoldur(int[] satırKonumEnerji) {
	satırKonumEnerji [0] = ekVeriler[0]; // başlangıç çözümü
   	int [] enerjiKonum = ekSatırİleEnİyiEnerjiKonumHesapla(ekVeriler[0]);
   	satırKonumEnerji [1] = enerjiKonum[1]; // başlangıç çözümü
   	satırKonumEnerji [2] = enerjiKonum[0]; // başlangıç
}
   private void yeniEnerjiVeKonumKıyaslaSatır(int ekSatırNo, int[] satırKonumEnerji) {
		int [] yeniEnerjiKonum  = ekSatırİleEnİyiEnerjiKonumHesapla(ekVeriler[ekSatırNo]);
		if(yeniEnerjiKonum [0] > satırKonumEnerji [2]){
			satırKonumEnerji [0] = ekVeriler[ekSatırNo]; // en iyi ek sutun kim
			satırKonumEnerji [1] = yeniEnerjiKonum[1];    // neredeyken en iyi ( konumu neresi)
			satırKonumEnerji [2] = yeniEnerjiKonum [0];  // yeni enyüksek enerji
		}
	}

   public int makineİçinÇöz(int makineNo) throws Exception{
	aşamaNo = makineNo;
   	int [] satırKonumEnerji = new int [3];
   	int makine;
   	for(makine=0;makine<makineSayısı()-1;makine++){
   		satırKonumEnerji = tümEkSatırlarınEnİyiEnerjiVereniniHesapla();
   		yeniElemanıYerleştirMakine(satırKonumEnerji[0], satırKonumEnerji[1]);
   	  }
   	yazı += "en iyi diziliş : " + makineEnİyiDiziliş.get(makineNo).toString() + "enerji : " +  satırKonumEnerji[2] + "\n\n";
   	yazı += "bu diziliş için parça makine matrisi : \n" + satırDizilişindenParçaMakineMatrisiOluştur(makineEnİyiDiziliş.get(makineNo)).yaz() + "\n\n";
    return satırKonumEnerji[2]; // enerjiyi geri dönsün
   }
   private void yeniElemanıYerleştirMakine(int yeniMakine, int konum) throws Exception{
	ArrayList<Integer> diziliş = makineEnİyiDiziliş.get(aşamaNo);
   	if ( konum > diziliş.size() ){
   		throw new Exception("konum dışarıda bir yerde \n girilen konum = " + konum + "\ndiziliş : " + diziliş.toString() + "\n");
   	}
	if(konum == diziliş.size()){ // demek ki eleman sona geleecek
   		diziliş.add(yeniMakine);
   	}
   	else { // eleman araya girecek
   		diziliş.add(konum, yeniMakine);
   	}
   }
   /**
    * dizi [0] = en iyi çözüm hangi makine ile başlanınca sağlanıyor 
   	* <br>dizi [1] = en iyi enerji ne kadar
    * @return
 * @throws Exception 
    */
   public int [] tümMakinelerİçinÇöz() throws Exception{
	makinelerEnİyiDizilişleriOluştur();
   	int makineNo;
   	yazı += "0 makinesi için çözülüyor \n" ;
   	int [] enİyiMakineEnerji = new int [2]; 
   	enİyiMakineEnerji [0] = 0;
   	enİyiMakineEnerji [1] = makineİçinÇöz(0);
   	// dizi [0] = en iyi çözüm hangi makine ile başlanınca sağlanıyor 
   	// dizi [1] = en iyi enerji ne kadar
   	for(makineNo=1;makineNo<makineSayısı();makineNo++){
   		makineAşama(makineNo, enİyiMakineEnerji);
   	}
   	return enİyiMakineEnerji;
   }
   private void makineAşama(int makineNo, int[] enİyiMakineEnerji) throws Exception {
	yazı += makineNo + " makinesi için çözülüyor \n" ;
	yeniEnerjiKıyaslaMakine(makineNo, enİyiMakineEnerji);
	yazı += "\n\n";
	herşeyiSıfırla();
}
   private void yeniEnerjiKıyaslaMakine(int makineNo, int[] enİyiMakineEnerji) throws Exception {
	int yeniEnerji = makineİçinÇöz(makineNo);
	if(yeniEnerji > enİyiMakineEnerji[1]){
		enİyiMakineEnerji[0] = makineNo;
		enİyiMakineEnerji[1] = yeniEnerji;
	}
}
   private void enİyiDizilişiDenemeyeAktarSatır(){
   	ArrayList<Integer> diziliş = makineEnİyiDiziliş.get(aşamaNo);
	int elemanNo;
   	for(elemanNo=0;elemanNo<diziliş.size();elemanNo++){
   		denemeler [elemanNo+1] = diziliş.get(elemanNo);
   	}
   }
   
   private void denemeSatırlarıİlkDeğerAtama() {
	    ArrayList<Integer> diziliş = makineEnİyiDiziliş.get(aşamaNo);
		denemeler = new int [diziliş.size()+1]; // en iyi diziliş + ek sutun
		enİyiDizilişiDenemeyeAktarSatır();
	}
   
   public void tümParçalarVeMakinelerİçinÇöz() throws Exception{
	   int [] makineEnerji  = tümMakinelerİçinÇöz();
	   int [] parçaEnerji = tümParçalarİçinÇöz();
	   yazı += "parçalar için en iyi diziliş : parça no " + parçaEnerji[0] + " da ve " + parçaEnerji[1] + " kadar enerji ile sağlanmaktadır \n";
	   yazı += "makinler için en iyi diziliş : makine no " + makineEnerji [0] + "da ve " + makineEnerji[1] + " kadar enerji ile sağlanmaktadır \n";
	   yazı += "bulunan en iyi parça makine matrisi : \n" + yeniParçaMakineDizilişi(parçalarEnİyiDiziliş, makinelerEnİyiDiziliş).yaz() + "\n";
   }
   
   // inşaa etme
   public BoolDizi sutunDizilişindenParçaMakineMatrisiOluştur(ArrayList<Integer> sutunDiziliş) throws Exception{
	   BoolDizi yeniParçaMakineMatrisi = new BoolDizi(makineSayısı(), parçaSayısı(), false);
	   int sutunNo;
	   for(sutunNo=0;sutunNo<sutunDiziliş.size();sutunNo++){
		   BoolVektör yeniSutun = parçaMakine.sutunÇek(sutunDiziliş.get(sutunNo));
		   yeniParçaMakineMatrisi.sutunaYerleş(sutunNo, yeniSutun);
	   }
	   return yeniParçaMakineMatrisi;
   }
   public BoolDizi satırDizilişindenParçaMakineMatrisiOluştur(ArrayList<Integer> satırDiziliş) throws Exception{
	   BoolDizi yeniParçaMakineMatrisi = new BoolDizi(makineSayısı(), parçaSayısı(), false);
	   int satırNo;
	   for(satırNo=0;satırNo<satırDiziliş.size();satırNo++){
		   BoolVektör satır = parçaMakine.satırÇek(satırNo);
		   yeniParçaMakineMatrisi.satıraYerleş(satırNo, satır);
	   }
	   return yeniParçaMakineMatrisi;
   }

   public BoolDizi yeniParçaMakineDizilişi(ArrayList<Integer> parçaDiziliş, ArrayList<Integer> makineDiziliş) throws Exception{
	   BoolDizi yeniParçaMakine = new BoolDizi(makineSayısı(), parçaSayısı(), false);
	   int satır,sutun;
	   for(satır=0;satır<yeniParçaMakine.satırSay();satır++){
		   for(sutun=0;sutun<yeniParçaMakine.sutunSay();sutun++){
			   boolean yeniDeğer = parçaMakine.eriş(satır, sutun);
			   yeniParçaMakine.değiş(satır, sutun, yeniDeğer);
		   } 
	   }
	   return yeniParçaMakine;
   }
   public String yaz(){
    	return yazı;
    }
}
