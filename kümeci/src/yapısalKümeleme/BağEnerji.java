package yapýsalKümeleme;

import java.util.ArrayList;

import veriYapýlarý.BoolDizi;
import kümeci.Kümeleme;

public class BaðEnerji extends Kümeleme {

	private int seçiliMakine,seçiliParça;
	private ArrayList<Integer> makinelerEnÝyiDiziliþ = new ArrayList<>() ; // bu dizinin eleman sayýsý parça makinenin sutun sayýsýna ulaþýnca olay bitecek
	private ArrayList<Integer> parçalarEnÝyiDiziliþ = new ArrayList<>() ;
	private ArrayList<Integer> makineDenekler = new ArrayList<>() ; // burada en iyi diziliþe dahil olmayanlar bulunacak buradan bir eleman seçilecek ve en iyi diziliþe dahil
	private ArrayList<Integer> parçaDenekler = new ArrayList<>() ; 
	private long baþ= System.currentTimeMillis(),son;
	// edilerek bir sonraki aþamaya geçilecek
    private int deneklerdenGidenMakineninDeneklerdekiKonumu,deneklerdenGidenParçanýnDeneklerdekiKonumu;
	

	public BaðEnerji(BoolDizi parçaMakine, String[] makineÝsimleri,String[] parçaÝsimleri) {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
		makineGruplarýOluþtur();
	}

	public BaðEnerji(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
		makineGruplarýOluþtur();
	}

	public BaðEnerji(String yazý) throws Exception {
		super(yazý);
		makineGruplarýOluþtur();
	}

	private int makineBaðEnerjisiHesapla(int makine1, int makine2) throws Exception{
		int enerji = 0,sutunNo;
		for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
			if(parçaMakine.eriþ(makine1, sutunNo) && parçaMakine.eriþ(makine2, sutunNo)){
				enerji++;
			}
		}
		return enerji;
	}
	private int makineBaðEnerjisiHesapla(ArrayList<Integer> makineler) throws Exception{
		int  enerji = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<makineler.size()-1;elemanNo++){
			enerji += makineBaðEnerjisiHesapla(makineler.get(elemanNo),makineler.get(elemanNo+1));
		}
		return enerji;
	}
	/**
	 * yeni makine gelince tekrardan dizi oluþturmamak için bunu yaptýk sanki 
	 * <br>dizi içindeymiþ gibi olacak sonradan hangi makinenin diziye katýlacaðý bulunacak
	 * @param diziliþ
	 * @param yeniMakine
	 * @param makineninKonumu
	 * @return
	 * @throws Exception
	 */
    private int makineBaðEnerjisiHesapla(ArrayList<Integer> diziliþ , int yeniMakine, int makineninKonumu) throws Exception{
    	int enerji = makineBaðEnerjisiHesapla(diziliþ);   	
    	if(makineninKonumu == 0){ // konum baþta için
    		enerji += makineBaðEnerjisiHesapla(yeniMakine, diziliþ.get(0));
    	}
    	else if(makineninKonumu == diziliþ.size()){ // demek ki sondadýr
    		enerji += makineBaðEnerjisiHesapla(diziliþ.get(diziliþ.size()-1), yeniMakine);
    	}
    	else { // makine arada demektir
    		enerji = yeniMakineAradaykenBaðEnerjisiHesapla(diziliþ,makineninKonumu); 
    	}
    	return enerji;
    }

	private int yeniMakineAradaykenBaðEnerjisiHesapla(ArrayList<Integer> diziliþ,int makineninKonumu) throws Exception {
		int elemanNo;
		int enerji = 0;
		for(elemanNo = 0;elemanNo<makineninKonumu-1;elemanNo++){
			enerji += makineBaðEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		// ara makine burada geliyor
		enerji += makineBaðEnerjisiHesapla(makineninKonumu, diziliþ.get(makineninKonumu));
		// buradan sonra makine aynen devam
		for(elemanNo= makineninKonumu+1;elemanNo<diziliþ.size()-1;elemanNo++){
			enerji += makineBaðEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		return enerji;
	}
	
	/**
	 * bunu baþta yapýyoruz baþta seçili makine hariç herþey denenecekler olcak
	 */
	private void denenecekleriBulMakine(){
		int elemanNo;
		// makineden öncesi
		for(elemanNo=0;elemanNo<seçiliMakine;elemanNo++){
			makineDenekler.add(elemanNo);
		}
		// makineden sonrasý
		for(elemanNo= seçiliMakine+1;elemanNo<parçaMakine.satýrSay();elemanNo++){
			makineDenekler.add(elemanNo);
		}
	}
    /**
     * cevap [0] = en iyi enerjiyi verdiði konum
     * <br>cevap [1] = en iyi enerji deðeri
     * @param denekNo
     * @return
     * @throws Exception
     */
	private int [] denenceklerleEnÝyiBaðEnerjisiBul(int denekNo) throws Exception{
		int konumNo;
		int denek = makineDenekler.get(denekNo);
		int [] cevap = new int [2];
		cevap [0] = 0; // en iyi konum
		cevap [1] = makineBaðEnerjisiHesapla(makinelerEnÝyiDiziliþ, denek, 0); //  en iyi enerji kabulu
		for(konumNo=1;konumNo<makinelerEnÝyiDiziliþ.size();konumNo++){
		 int yeniEnerji = makineBaðEnerjisiHesapla(makinelerEnÝyiDiziliþ, denek, konumNo);
		 if(yeniEnerji > cevap [1]){
			 cevap [1] =  yeniEnerji;
			 cevap [0] = konumNo;
		 }
		}
		return cevap;
	}
    /**
     * cevap [0] = yeni makinenin en iyi enerjiyi verdiði konum
     * <br>cevap [1] = yeni makienin verdiði en iyi enerji
     * <br>cevap [2] = yeni makinenin no su (hangi makine )
     * @return
     * @throws Exception
     */
	private int [] tümDenemeleriYap() throws Exception{
		int denekNo;
		int [] konumEnerjiMakine = new int [3]; // en iyi enerjiyi veren makine , konumu ve enerjisi
		int [] konumEnerji = denenceklerleEnÝyiBaðEnerjisiBul(0);
		konumEnerjiMakine [0] = konumEnerji [0];
		konumEnerjiMakine [1] = konumEnerji [1];
		konumEnerjiMakine [2] = makineDenekler.get(0);
		deneklerdenGidenMakineninDeneklerdekiKonumu  = 0;
		// en iyi deneði bulma
		for(denekNo=0;denekNo<makineDenekler.size();denekNo++){
			int [] yeniKonumEnerji = denenceklerleEnÝyiBaðEnerjisiBul(denekNo); 
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
	 * en iyi diziliþe yeni makine istenilen konumda ekler
	 * @param yeniMakine
	 * @param konumu
	 */
    private void enÝyiDiziliþiGüncelle(int  yeniMakine, int konumu){
    	 makinelerEnÝyiDiziliþ.add(konumu, yeniMakine);
    }
    private void denekleriGüncelle(){
    	makineDenekler.remove(deneklerdenGidenMakineninDeneklerdekiKonumu);
    }
    private void güncellemeleriYap(int  yeniMakine, int konumu){
    	enÝyiDiziliþiGüncelle(yeniMakine, konumu);
    	denekleriGüncelle();
    }
    
    public String makineÝlerle() throws Exception{
    	String çözüm = "";
    	int [] konumEnerjiMakine = tümDenemeleriYap();
    	çözüm += "yeni gelen makine : " + konumEnerjiMakine [2] + "\n";
    	çözüm += "gelen makinenin en iyi diziliþteki konumu : " + konumEnerjiMakine [0] +"\n";
    	çözüm += "yeni diziliþin enerjisi : " + konumEnerjiMakine [1] +"\n";
    	güncellemeleriYap(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    	çözüm += "yeni diziliþ : " + makinelerEnÝyiDiziliþ.toString() + "\n\n";
    	return çözüm;
    }
    /**
     * en iyi makine diziliþine göre satýrlarý sýralar ve yeni br parça makine matrisi oluþturur
     * @return
     * @throws Exception 
     */
    private String satýrlarýSýrala() throws Exception{
    	String yazý = "";
    	int satýrNo;
    	for(satýrNo=0;satýrNo<parçaMakine.satýrSay();satýrNo++){
    		int satýr = makinelerEnÝyiDiziliþ.get(satýrNo);
    		yazý += parçaMakine.satýr(satýr).yaz() +"\n";
    	}
    	return yazý;
    }
    
    
    
    
    private int parçaBaðEnerjisiHesapla(int parça1, int parça2) throws Exception{
		int enerji = 0,satýrNo;
		for(satýrNo=0;satýrNo<parçaMakine.satýrSay();satýrNo++){
			if(parçaMakine.eriþ(satýrNo, parça1) && parçaMakine.eriþ(satýrNo, parça2)){
				enerji++;
			}
		}
		return enerji;
	}
	private int parçaBaðEnerjisiHesapla(ArrayList<Integer> parçalar) throws Exception{
		int  enerji = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<parçalar.size()-1;elemanNo++){
			enerji += parçaBaðEnerjisiHesapla(parçalar.get(elemanNo),parçalar.get(elemanNo+1));
		}
		return enerji;
	}
	/**
	 * yeni makine gelince tekrardan dizi oluþturmamak için bunu yaptýk sanki 
	 * <br>dizi içindeymiþ gibi olacak sonradan hangi makinenin diziye katýlacaðý bulunacak
	 * @param diziliþ
	 * @param yeniParça
	 * @param parçanýnKonumu
	 * @return
	 * @throws Exception
	 */
    private int parçaBaðEnerjisiHesapla(ArrayList<Integer> diziliþ , int yeniParça, int parçanýnKonumu) throws Exception{
    	int enerji = parçaBaðEnerjisiHesapla(diziliþ);   	
    	if(parçanýnKonumu == 0){ // konum baþta için
    		enerji += parçaBaðEnerjisiHesapla(yeniParça, diziliþ.get(0));
    	}
    	else if(parçanýnKonumu == diziliþ.size()){ // demek ki sondadýr
    		enerji += parçaBaðEnerjisiHesapla(diziliþ.get(diziliþ.size()-1), yeniParça);
    	}
    	else { // makine arada demektir
    		enerji = yeniParçaAradaykenBaðEnerjisiHesapla(diziliþ,parçanýnKonumu); 
    	}
    	return enerji;
    }
    private int yeniParçaAradaykenBaðEnerjisiHesapla(ArrayList<Integer> diziliþ,int parçanýnKonumu) throws Exception {
		int elemanNo;
		int enerji = 0;
		for(elemanNo = 0;elemanNo<parçanýnKonumu-1;elemanNo++){
			enerji += parçaBaðEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		// ara makine burada geliyor
		enerji += parçaBaðEnerjisiHesapla(parçanýnKonumu, diziliþ.get(parçanýnKonumu));
		// buradan sonra makine aynen devam
		for(elemanNo= parçanýnKonumu+1;elemanNo<diziliþ.size()-1;elemanNo++){
			enerji += parçaBaðEnerjisiHesapla(elemanNo, elemanNo+1);
		}
		return enerji;
	}
	
    /** bunu baþta yapýyoruz baþta seçili makine hariç herþey denenecekler olcak
	 */
	private void denenecekleriBulParça(){
		int elemanNo;
		// makineden öncesi
		for(elemanNo=0;elemanNo<seçiliParça;elemanNo++){
			parçaDenekler.add(elemanNo);
		}
		// makineden sonrasý
		for(elemanNo= seçiliParça+1;elemanNo<parçaMakine.sutunSay();elemanNo++){
			parçaDenekler.add(elemanNo);
		}
	}
   /**
    * cevap [0] = en iyi enerjiyi verdiði konum
    * <br>cevap [1] = en iyi enerji deðeri
    * @param denekNo
    * @return
    * @throws Exception
    */
	private int [] denekParçalarlaEnÝyiBaðEnerjisiBul(int denekNo) throws Exception{
		int konumNo;
		int denek = parçaDenekler.get(denekNo);
		int [] cevap = new int [2];
		cevap [0] = 0; // en iyi konum
		cevap [1] = parçaBaðEnerjisiHesapla(parçalarEnÝyiDiziliþ, denek, 0); //  en iyi enerji kabulu
		for(konumNo=1;konumNo<parçalarEnÝyiDiziliþ.size();konumNo++){
		 int yeniEnerji = parçaBaðEnerjisiHesapla(parçalarEnÝyiDiziliþ, denek, konumNo);
		 if(yeniEnerji > cevap [1]){
			 cevap [1] =  yeniEnerji;
			 cevap [0] = konumNo;
		 }
		}
		return cevap;
	}
   /**
    * cevap [0] = yeni makinenin en iyi enerjiyi verdiði konum
    * <br>cevap [1] = yeni makienin verdiði en iyi enerji
    * <br>cevap [2] = yeni makinenin no su (hangi makine )
    * @return
    * @throws Exception
    */
	private int [] tümDenemeleriYapParça() throws Exception{
		int denekNo;
		int [] konumEnerjiMakine = new int [3]; // en iyi enerjiyi veren makine , konumu ve enerjisi
		int [] konumEnerji = denekParçalarlaEnÝyiBaðEnerjisiBul(0);
		konumEnerjiMakine [0] = konumEnerji [0];
		konumEnerjiMakine [1] = konumEnerji [1];
		konumEnerjiMakine [2] = parçaDenekler.get(0);
		deneklerdenGidenParçanýnDeneklerdekiKonumu  = 0;
		// en iyi deneði bulma
		for(denekNo=0;denekNo<parçaDenekler.size();denekNo++){
			int [] yeniKonumEnerji = denekParçalarlaEnÝyiBaðEnerjisiBul(denekNo); 
			if( yeniKonumEnerji [1] > konumEnerjiMakine [1]){
				konumEnerjiMakine [0] = yeniKonumEnerji[0];
				konumEnerjiMakine [1] = yeniKonumEnerji[1];
				konumEnerjiMakine [2] = parçaDenekler.get(denekNo);
				deneklerdenGidenParçanýnDeneklerdekiKonumu = denekNo;
			}
		}
		return konumEnerjiMakine;
	}
   
	/**
	 * en iyi diziliþe yeni makine istenilen konumda ekler
	 * @param yeniParça
	 * @param konumu
	 */
   private void enÝyiDiziliþiGüncelleParça(int  yeniParça, int konumu){
   	 parçalarEnÝyiDiziliþ.add(konumu, yeniParça);
   }
   private void denekleriGüncelleParça(){
	   parçaDenekler.remove(deneklerdenGidenMakineninDeneklerdekiKonumu);
   }
   private void güncellemeleriYapParça(int  yeniParça, int konumu){
   	enÝyiDiziliþiGüncelleParça(yeniParça, konumu);
   	denekleriGüncelleParça();
   }
   
   public String parçaÝlerle() throws Exception{
   	String çözüm = "";
   	int [] konumEnerjiMakine = tümDenemeleriYapParça();
   	çözüm += "yeni gelen makine : " + konumEnerjiMakine [2] + "\n";
   	çözüm += "gelen makinenin en iyi diziliþteki konumu : " + konumEnerjiMakine [0] +"\n";
   	çözüm += "yeni diziliþin enerjisi : " + konumEnerjiMakine [1] +"\n";
   	güncellemeleriYapParça(konumEnerjiMakine[2], konumEnerjiMakine[0]);
   	çözüm += "yeni diziliþ : " + parçalarEnÝyiDiziliþ.toString() + "\n\n";
   	return çözüm;
   }
   /**
    * en iyi makine diziliþine göre satýrlarý sýralar ve yeni br parça makine matrisi oluþturur
    * @return
    * @throws Exception 
    */
   
   private String sutunlarýSýrala() throws Exception{
   	String yazý = "";
   	int satýrNo;
   	for(satýrNo=0;satýrNo<parçaMakine.satýrSay();satýrNo++){
   		yazý = satýrYaz(yazý, satýrNo);
   	}
   	return yazý;
   }
   private String satýrYaz(String yazý, int satýrNo) throws Exception {
	for(int sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
		int sutun = parçalarEnÝyiDiziliþ.get(sutunNo);
		yazý = boolDeðerYaz(yazý, satýrNo, sutun);
	}
	yazý += "\n";
	return yazý;
}
   private String boolDeðerYaz(String yazý, int satýrNo, int sutun)
		throws Exception {
	if(parçaMakine.eriþ(satýrNo, sutun) ){
		yazý += "1\t";	
	}
	else {
		yazý += "\t";
	}
	return yazý;
}
   private String sonSýralama() throws Exception{
	   String yazý = "";
	   int satýrNo;
	   	for(satýrNo=0;satýrNo<parçaMakine.satýrSay();satýrNo++){
	   		yazý = sonSýralamaSatýrYaz(yazý, satýrNo);
	   	}
	   return yazý;
   }
   private String sonSýralamaSatýrYaz(String yazý, int satýrNo) throws Exception {
	int satýr = makinelerEnÝyiDiziliþ.get(satýrNo);
	for(int sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
		int sutun = parçalarEnÝyiDiziliþ.get(sutunNo);
		yazý = boolDeðerYaz(yazý, satýr, sutun);
	}
	yazý += "\n";
	return yazý;
}
   
   public String çöz() throws Exception{
    	String çözüm = "Bað enerji yöntemi\n\n";
    	çözüm = makineleriGrupla(çözüm);
    	çözüm = parçalarýGrupla(çözüm);
    	çözüm += "son sýralama : \n" + sonSýralama() +"\n\n";
    	son = System.currentTimeMillis();
    	çözüm += "çözüm süresi : " + (son -baþ) + " milisaniye\n\n";
    	return çözüm;
    }
   private String parçalarýGrupla(String çözüm) throws Exception {
	int aþamaNo;
	parçalarEnÝyiDiziliþ.add(0);
	denenecekleriBulParça();
	for(aþamaNo=0;aþamaNo<parçaMakine.sutunSay()-1;aþamaNo++){
		çözüm += "aþamaNo : " + aþamaNo + "\n\n" + parçaÝlerle() + "\n\n";
	}
	çözüm += "satýrlarýn sýralanmýþ hali : \n" + sutunlarýSýrala() + "\n\n";
	return çözüm;
    }
   private String makineleriGrupla(String çözüm) throws Exception {
    	makinelerEnÝyiDiziliþ.add(0);
    	denenecekleriBulMakine();
    	for(int aþamaNo=0;aþamaNo<parçaMakine.satýrSay()-1;aþamaNo++){
    		çözüm += "aþamaNo : " + aþamaNo + "\n\n" + makineÝlerle() + "\n\n";
    	}
    	çözüm += "satýrlarýn sýralanmýþ hali : " + satýrlarýSýrala() + "\n\n";
    	return çözüm;
    }
    
    public String hýzlýÇöz() throws Exception{
	    String çözüm = "Bað enerji yöntemi\n\n";
     	çözüm += hýzlýMakineleriGrupla();
    	çözüm += hýzlýParçalarýGrupla();
    	çözüm += "son sýralama : \n" + sonSýralama() +"\n\n";
    	son = System.currentTimeMillis();
    	çözüm += "çözüm süresi : " + (son -baþ) + " milisaniye\n\n";
    	return çözüm;
    } 
    private  String  hýzlýMakineleriGrupla() throws Exception{
    	makinelerEnÝyiDiziliþ.add(0);
    	denenecekleriBulMakine();
    	for(int aþamaNo=0;aþamaNo<parçaMakine.satýrSay()-1;aþamaNo++){
    		hýzlýMakineÝlerle() ;
    	}
    	return "satýrlarýn sýralanmýþ hali : " + satýrlarýSýrala() + "\n\n";
    }
    private String hýzlýParçalarýGrupla() throws Exception{
    	int aþamaNo;
    	parçalarEnÝyiDiziliþ.add(0);
    	denenecekleriBulParça();
    	for(aþamaNo=0;aþamaNo<parçaMakine.sutunSay()-1;aþamaNo++){
    		hýzlýParçaÝlerle() ;
    	}
    	return  "satýrlarýn sýralanmýþ hali : \n" + sutunlarýSýrala() + "\n\n";
    }
    private void hýzlýParçaÝlerle() throws Exception{
    	int [] konumEnerjiMakine = tümDenemeleriYapParça();
       	güncellemeleriYapParça(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    }
    private void hýzlýMakineÝlerle() throws Exception{
    	int [] konumEnerjiMakine = tümDenemeleriYap();
    	güncellemeleriYap(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    }
    
}
