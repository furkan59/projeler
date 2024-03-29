package yapısalKümeleme;

import java.util.ArrayList;

import veriYapıları.BoolDizi;
import kümeci.Kümeleme;

public class BağEnerji extends Kümeleme {

	private int seçiliMakine,seçiliParça;
	private ArrayList<Integer> makinelerEnİyiDiziliş = new ArrayList<>() ; // bu dizinin eleman sayısı parça makinenin sutun sayısına ulaşınca olay bitecek
	private ArrayList<Integer> parçalarEnİyiDiziliş = new ArrayList<>() ;
	private ArrayList<Integer> makineDenekler = new ArrayList<>() ; // burada en iyi dizilişe dahil olmayanlar bulunacak buradan bir eleman seçilecek ve en iyi dizilişe dahil
	private ArrayList<Integer> parçaDenekler = new ArrayList<>() ; 
	private long baş= System.currentTimeMillis(),son;
	// edilerek bir sonraki aşamaya geçilecek
    private int deneklerdenGidenMakineninDeneklerdekiKonumu,deneklerdenGidenParçanınDeneklerdekiKonumu;
	

	public BağEnerji(BoolDizi parçaMakine, String[] makineİsimleri,String[] parçaİsimleri) {
		super(parçaMakine, makineİsimleri, parçaİsimleri);
		makineGruplarıOluştur();
	}

	public BağEnerji(String yazı, char ayırıcı) throws Exception {
		super(yazı, ayırıcı);
		makineGruplarıOluştur();
	}

	public BağEnerji(String yazı) throws Exception {
		super(yazı);
		makineGruplarıOluştur();
	}

	private int makineBağEnerjisiHesapla(int makine1, int makine2) throws Exception{
		int enerji = 0,sutunNo;
		for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
			if(parçaMakine.eriş(makine1, sutunNo) && parçaMakine.eriş(makine2, sutunNo)){
				enerji++;
			}
		}
		return enerji;
	}
	private int makineBağEnerjisiHesapla(ArrayList<Integer> makineler) throws Exception{
		int  enerji = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<makineler.size()-1;elemanNo++){
			enerji += makineBağEnerjisiHesapla(makineler.get(elemanNo),makineler.get(elemanNo+1));
		}
		return enerji;
	}
	/**
	 * yeni makine gelince tekrardan dizi oluşturmamak için bunu yaptık sanki 
	 * <br>dizi içindeymiş gibi olacak sonradan hangi makinenin diziye katılacağı bulunacak
	 * @param diziliş
	 * @param yeniMakine
	 * @param makineninKonumu
	 * @return
	 * @throws Exception
	 */
    private int makineBağEnerjisiHesapla(ArrayList<Integer> diziliş , int yeniMakine, int makineninKonumu) throws Exception{
    	int enerji = makineBağEnerjisiHesapla(diziliş);   	
    	if(makineninKonumu == 0){ // konum başta için
    		enerji += makineBağEnerjisiHesapla(yeniMakine, diziliş.get(0));
    	}
    	else if(makineninKonumu == diziliş.size()){ // demek ki sondadır
    		enerji += makineBağEnerjisiHesapla(diziliş.get(diziliş.size()-1), yeniMakine);
    	}
    	else { // makine arada demektir
    		enerji = yeniMakineAradaykenBağEnerjisiHesapla(diziliş,makineninKonumu); 
    	}
    	return enerji;
    }

	private int yeniMakineAradaykenBağEnerjisiHesapla(ArrayList<Integer> diziliş,int makineninKonumu) throws Exception {
		int elemanNo;
		int enerji = 0;
		for(elemanNo = 0;elemanNo<makineninKonumu-1;elemanNo++){
			enerji += makineBağEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		// ara makine burada geliyor
		enerji += makineBağEnerjisiHesapla(makineninKonumu, diziliş.get(makineninKonumu));
		// buradan sonra makine aynen devam
		for(elemanNo= makineninKonumu+1;elemanNo<diziliş.size()-1;elemanNo++){
			enerji += makineBağEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		return enerji;
	}
	
	/**
	 * bunu başta yapıyoruz başta seçili makine hariç herşey denenecekler olcak
	 */
	private void denenecekleriBulMakine(){
		int elemanNo;
		// makineden öncesi
		for(elemanNo=0;elemanNo<seçiliMakine;elemanNo++){
			makineDenekler.add(elemanNo);
		}
		// makineden sonrası
		for(elemanNo= seçiliMakine+1;elemanNo<parçaMakine.satırSay();elemanNo++){
			makineDenekler.add(elemanNo);
		}
	}
    /**
     * cevap [0] = en iyi enerjiyi verdiği konum
     * <br>cevap [1] = en iyi enerji değeri
     * @param denekNo
     * @return
     * @throws Exception
     */
	private int [] denenceklerleEnİyiBağEnerjisiBul(int denekNo) throws Exception{
		int konumNo;
		int denek = makineDenekler.get(denekNo);
		int [] cevap = new int [2];
		cevap [0] = 0; // en iyi konum
		cevap [1] = makineBağEnerjisiHesapla(makinelerEnİyiDiziliş, denek, 0); //  en iyi enerji kabulu
		for(konumNo=1;konumNo<makinelerEnİyiDiziliş.size();konumNo++){
		 int yeniEnerji = makineBağEnerjisiHesapla(makinelerEnİyiDiziliş, denek, konumNo);
		 if(yeniEnerji > cevap [1]){
			 cevap [1] =  yeniEnerji;
			 cevap [0] = konumNo;
		 }
		}
		return cevap;
	}
    /**
     * cevap [0] = yeni makinenin en iyi enerjiyi verdiği konum
     * <br>cevap [1] = yeni makienin verdiği en iyi enerji
     * <br>cevap [2] = yeni makinenin no su (hangi makine )
     * @return
     * @throws Exception
     */
	private int [] tümDenemeleriYap() throws Exception{
		int denekNo;
		int [] konumEnerjiMakine = new int [3]; // en iyi enerjiyi veren makine , konumu ve enerjisi
		int [] konumEnerji = denenceklerleEnİyiBağEnerjisiBul(0);
		konumEnerjiMakine [0] = konumEnerji [0];
		konumEnerjiMakine [1] = konumEnerji [1];
		konumEnerjiMakine [2] = makineDenekler.get(0);
		deneklerdenGidenMakineninDeneklerdekiKonumu  = 0;
		// en iyi deneği bulma
		for(denekNo=0;denekNo<makineDenekler.size();denekNo++){
			int [] yeniKonumEnerji = denenceklerleEnİyiBağEnerjisiBul(denekNo); 
			if( yeniKonumEnerji [1] > konumEnerjiMakine [1]){
				konumEnerjiMakine [0] = yeniKonumEnerji[0];
				konumEnerjiMakine [1] = yeniKonumEnerji[1];
				konumEnerjiMakine [2] = makineDenekler.get(denekNo);
				deneklerdenGidenMakineninDeneklerdekiKonumu = denekNo;
			}
		}
		return konumEnerjiMakine;
	}
    
	/**
	 * en iyi dizilişe yeni makine istenilen konumda ekler
	 * @param yeniMakine
	 * @param konumu
	 */
    private void enİyiDizilişiGüncelle(int  yeniMakine, int konumu){
    	 makinelerEnİyiDiziliş.add(konumu, yeniMakine);
    }
    private void denekleriGüncelle(){
    	makineDenekler.remove(deneklerdenGidenMakineninDeneklerdekiKonumu);
    }
    private void güncellemeleriYap(int  yeniMakine, int konumu){
    	enİyiDizilişiGüncelle(yeniMakine, konumu);
    	denekleriGüncelle();
    }
    
    public String makineİlerle() throws Exception{
    	String çözüm = "";
    	int [] konumEnerjiMakine = tümDenemeleriYap();
    	çözüm += "yeni gelen makine : " + konumEnerjiMakine [2] + "\n";
    	çözüm += "gelen makinenin en iyi dizilişteki konumu : " + konumEnerjiMakine [0] +"\n";
    	çözüm += "yeni dizilişin enerjisi : " + konumEnerjiMakine [1] +"\n";
    	güncellemeleriYap(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    	çözüm += "yeni diziliş : " + makinelerEnİyiDiziliş.toString() + "\n\n";
    	return çözüm;
    }
    /**
     * en iyi makine dizilişine göre satırları sıralar ve yeni br parça makine matrisi oluşturur
     * @return
     * @throws Exception 
     */
    private String satırlarıSırala() throws Exception{
    	String yazı = "";
    	int satırNo;
    	for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
    		int satır = makinelerEnİyiDiziliş.get(satırNo);
    		yazı += parçaMakine.satır(satır).yaz() +"\n";
    	}
    	return yazı;
    }
    
    
    
    
    private int parçaBağEnerjisiHesapla(int parça1, int parça2) throws Exception{
		int enerji = 0,satırNo;
		for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
			if(parçaMakine.eriş(satırNo, parça1) && parçaMakine.eriş(satırNo, parça2)){
				enerji++;
			}
		}
		return enerji;
	}
	private int parçaBağEnerjisiHesapla(ArrayList<Integer> parçalar) throws Exception{
		int  enerji = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<parçalar.size()-1;elemanNo++){
			enerji += parçaBağEnerjisiHesapla(parçalar.get(elemanNo),parçalar.get(elemanNo+1));
		}
		return enerji;
	}
	/**
	 * yeni makine gelince tekrardan dizi oluşturmamak için bunu yaptık sanki 
	 * <br>dizi içindeymiş gibi olacak sonradan hangi makinenin diziye katılacağı bulunacak
	 * @param diziliş
	 * @param yeniParça
	 * @param parçanınKonumu
	 * @return
	 * @throws Exception
	 */
    private int parçaBağEnerjisiHesapla(ArrayList<Integer> diziliş , int yeniParça, int parçanınKonumu) throws Exception{
    	int enerji = parçaBağEnerjisiHesapla(diziliş);   	
    	if(parçanınKonumu == 0){ // konum başta için
    		enerji += parçaBağEnerjisiHesapla(yeniParça, diziliş.get(0));
    	}
    	else if(parçanınKonumu == diziliş.size()){ // demek ki sondadır
    		enerji += parçaBağEnerjisiHesapla(diziliş.get(diziliş.size()-1), yeniParça);
    	}
    	else { // makine arada demektir
    		enerji = yeniParçaAradaykenBağEnerjisiHesapla(diziliş,parçanınKonumu); 
    	}
    	return enerji;
    }
    private int yeniParçaAradaykenBağEnerjisiHesapla(ArrayList<Integer> diziliş,int parçanınKonumu) throws Exception {
		int elemanNo;
		int enerji = 0;
		for(elemanNo = 0;elemanNo<parçanınKonumu-1;elemanNo++){
			enerji += parçaBağEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		// ara makine burada geliyor
		enerji += parçaBağEnerjisiHesapla(parçanınKonumu, diziliş.get(parçanınKonumu));
		// buradan sonra makine aynen devam
		for(elemanNo= parçanınKonumu+1;elemanNo<diziliş.size()-1;elemanNo++){
			enerji += parçaBağEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		return enerji;
	}
	
    /** bunu başta yapıyoruz başta seçili makine hariç herşey denenecekler olcak
	 */
	private void denenecekleriBulParça(){
		int elemanNo;
		// makineden öncesi
		for(elemanNo=0;elemanNo<seçiliParça;elemanNo++){
			parçaDenekler.add(elemanNo);
		}
		// makineden sonrası
		for(elemanNo= seçiliParça+1;elemanNo<parçaMakine.sutunSay();elemanNo++){
			parçaDenekler.add(elemanNo);
		}
	}
   /**
    * cevap [0] = en iyi enerjiyi verdiği konum
    * <br>cevap [1] = en iyi enerji değeri
    * @param denekNo
    * @return
    * @throws Exception
    */
	private int [] denekParçalarlaEnİyiBağEnerjisiBul(int denekNo) throws Exception{
		int konumNo;
		int denek = parçaDenekler.get(denekNo);
		int [] cevap = new int [2];
		cevap [0] = 0; // en iyi konum
		cevap [1] = parçaBağEnerjisiHesapla(parçalarEnİyiDiziliş, denek, 0); //  en iyi enerji kabulu
		for(konumNo=1;konumNo<parçalarEnİyiDiziliş.size();konumNo++){
		 int yeniEnerji = parçaBağEnerjisiHesapla(parçalarEnİyiDiziliş, denek, konumNo);
		 if(yeniEnerji > cevap [1]){
			 cevap [1] =  yeniEnerji;
			 cevap [0] = konumNo;
		 }
		}
		return cevap;
	}
   /**
    * cevap [0] = yeni makinenin en iyi enerjiyi verdiği konum
    * <br>cevap [1] = yeni makienin verdiği en iyi enerji
    * <br>cevap [2] = yeni makinenin no su (hangi makine )
    * @return
    * @throws Exception
    */
	private int [] tümDenemeleriYapParça() throws Exception{
		int denekNo;
		int [] konumEnerjiMakine = new int [3]; // en iyi enerjiyi veren makine , konumu ve enerjisi
		int [] konumEnerji = denekParçalarlaEnİyiBağEnerjisiBul(0);
		konumEnerjiMakine [0] = konumEnerji [0];
		konumEnerjiMakine [1] = konumEnerji [1];
		konumEnerjiMakine [2] = parçaDenekler.get(0);
		deneklerdenGidenParçanınDeneklerdekiKonumu  = 0;
		// en iyi deneği bulma
		for(denekNo=0;denekNo<parçaDenekler.size();denekNo++){
			int [] yeniKonumEnerji = denekParçalarlaEnİyiBağEnerjisiBul(denekNo); 
			if( yeniKonumEnerji [1] > konumEnerjiMakine [1]){
				konumEnerjiMakine [0] = yeniKonumEnerji[0];
				konumEnerjiMakine [1] = yeniKonumEnerji[1];
				konumEnerjiMakine [2] = parçaDenekler.get(denekNo);
				deneklerdenGidenParçanınDeneklerdekiKonumu = denekNo;
			}
		}
		return konumEnerjiMakine;
	}
   
	/**
	 * en iyi dizilişe yeni makine istenilen konumda ekler
	 * @param yeniParça
	 * @param konumu
	 */
   private void enİyiDizilişiGüncelleParça(int  yeniParça, int konumu){
   	 parçalarEnİyiDiziliş.add(konumu, yeniParça);
   }
   private void denekleriGüncelleParça(){
	   parçaDenekler.remove(deneklerdenGidenMakineninDeneklerdekiKonumu);
   }
   private void güncellemeleriYapParça(int  yeniParça, int konumu){
   	enİyiDizilişiGüncelleParça(yeniParça, konumu);
   	denekleriGüncelleParça();
   }
   
   public String parçaİlerle() throws Exception{
   	String çözüm = "";
   	int [] konumEnerjiMakine = tümDenemeleriYapParça();
   	çözüm += "yeni gelen makine : " + konumEnerjiMakine [2] + "\n";
   	çözüm += "gelen makinenin en iyi dizilişteki konumu : " + konumEnerjiMakine [0] +"\n";
   	çözüm += "yeni dizilişin enerjisi : " + konumEnerjiMakine [1] +"\n";
   	güncellemeleriYapParça(konumEnerjiMakine[2], konumEnerjiMakine[0]);
   	çözüm += "yeni diziliş : " + parçalarEnİyiDiziliş.toString() + "\n\n";
   	return çözüm;
   }
   /**
    * en iyi makine dizilişine göre satırları sıralar ve yeni br parça makine matrisi oluşturur
    * @return
    * @throws Exception 
    */
   
   private String sutunlarıSırala() throws Exception{
   	String yazı = "";
   	int satırNo;
   	for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
   		yazı = satırYaz(yazı, satırNo);
   	}
   	return yazı;
   }
   private String satırYaz(String yazı, int satırNo) throws Exception {
	for(int sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
		int sutun = parçalarEnİyiDiziliş.get(sutunNo);
		yazı = boolDeğerYaz(yazı, satırNo, sutun);
	}
	yazı += "\n";
	return yazı;
}
   private String boolDeğerYaz(String yazı, int satırNo, int sutun)
		throws Exception {
	if(parçaMakine.eriş(satırNo, sutun) ){
		yazı += "1\t";	
	}
	else {
		yazı += "\t";
	}
	return yazı;
}
   private String sonSıralama() throws Exception{
	   String yazı = "";
	   int satırNo;
	   	for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
	   		yazı = sonSıralamaSatırYaz(yazı, satırNo);
	   	}
	   return yazı;
   }
   private String sonSıralamaSatırYaz(String yazı, int satırNo) throws Exception {
	int satır = makinelerEnİyiDiziliş.get(satırNo);
	for(int sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
		int sutun = parçalarEnİyiDiziliş.get(sutunNo);
		yazı = boolDeğerYaz(yazı, satır, sutun);
	}
	yazı += "\n";
	return yazı;
}
   
   public String çöz() throws Exception{
    	String çözüm = "Bağ enerji yöntemi\n\n";
    	çözüm = makineleriGrupla(çözüm);
    	çözüm = parçalarıGrupla(çözüm);
    	çözüm += "son sıralama : \n" + sonSıralama() +"\n\n";
    	son = System.currentTimeMillis();
    	çözüm += "çözüm süresi : " + (son -baş) + " milisaniye\n\n";
    	return çözüm;
    }
   private String parçalarıGrupla(String çözüm) throws Exception {
	int aşamaNo;
	parçalarEnİyiDiziliş.add(0);
	denenecekleriBulParça();
	for(aşamaNo=0;aşamaNo<parçaMakine.sutunSay()-1;aşamaNo++){
		çözüm += "aşamaNo : " + aşamaNo + "\n\n" + parçaİlerle() + "\n\n";
	}
	çözüm += "satırların sıralanmış hali : \n" + sutunlarıSırala() + "\n\n";
	return çözüm;
    }
   private String makineleriGrupla(String çözüm) throws Exception {
    	makinelerEnİyiDiziliş.add(0);
    	denenecekleriBulMakine();
    	for(int aşamaNo=0;aşamaNo<parçaMakine.satırSay()-1;aşamaNo++){
    		çözüm += "aşamaNo : " + aşamaNo + "\n\n" + makineİlerle() + "\n\n";
    	}
    	çözüm += "satırların sıralanmış hali : " + satırlarıSırala() + "\n\n";
    	return çözüm;
    }
    
    public String hızlıÇöz() throws Exception{
	    String çözüm = "Bağ enerji yöntemi\n\n";
     	çözüm += hızlıMakineleriGrupla();
    	çözüm += hızlıParçalarıGrupla();
    	çözüm += "son sıralama : \n" + sonSıralama() +"\n\n";
    	son = System.currentTimeMillis();
    	çözüm += "çözüm süresi : " + (son -baş) + " milisaniye\n\n";
    	return çözüm;
    } 
    private  String  hızlıMakineleriGrupla() throws Exception{
    	makinelerEnİyiDiziliş.add(0);
    	denenecekleriBulMakine();
    	for(int aşamaNo=0;aşamaNo<parçaMakine.satırSay()-1;aşamaNo++){
    		hızlıMakineİlerle() ;
    	}
    	return "satırların sıralanmış hali : " + satırlarıSırala() + "\n\n";
    }
    private String hızlıParçalarıGrupla() throws Exception{
    	int aşamaNo;
    	parçalarEnİyiDiziliş.add(0);
    	denenecekleriBulParça();
    	for(aşamaNo=0;aşamaNo<parçaMakine.sutunSay()-1;aşamaNo++){
    		hızlıParçaİlerle() ;
    	}
    	return  "satırların sıralanmış hali : \n" + sutunlarıSırala() + "\n\n";
    }
    private void hızlıParçaİlerle() throws Exception{
    	int [] konumEnerjiMakine = tümDenemeleriYapParça();
       	güncellemeleriYapParça(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    }
    private void hızlıMakineİlerle() throws Exception{
    	int [] konumEnerjiMakine = tümDenemeleriYap();
    	güncellemeleriYap(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    }
    
}
