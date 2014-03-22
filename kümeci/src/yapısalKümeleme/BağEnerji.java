package yap�salK�meleme;

import java.util.ArrayList;

import veriYap�lar�.BoolDizi;
import k�meci.K�meleme;

public class Ba�Enerji extends K�meleme {

	private int se�iliMakine,se�iliPar�a;
	private ArrayList<Integer> makinelerEn�yiDizili� = new ArrayList<>() ; // bu dizinin eleman say�s� par�a makinenin sutun say�s�na ula��nca olay bitecek
	private ArrayList<Integer> par�alarEn�yiDizili� = new ArrayList<>() ;
	private ArrayList<Integer> makineDenekler = new ArrayList<>() ; // burada en iyi dizili�e dahil olmayanlar bulunacak buradan bir eleman se�ilecek ve en iyi dizili�e dahil
	private ArrayList<Integer> par�aDenekler = new ArrayList<>() ; 
	private long ba�= System.currentTimeMillis(),son;
	// edilerek bir sonraki a�amaya ge�ilecek
    private int deneklerdenGidenMakineninDeneklerdekiKonumu,deneklerdenGidenPar�an�nDeneklerdekiKonumu;
	

	public Ba�Enerji(BoolDizi par�aMakine, String[] makine�simleri,String[] par�a�simleri) {
		super(par�aMakine, makine�simleri, par�a�simleri);
		makineGruplar�Olu�tur();
	}

	public Ba�Enerji(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
		makineGruplar�Olu�tur();
	}

	public Ba�Enerji(String yaz�) throws Exception {
		super(yaz�);
		makineGruplar�Olu�tur();
	}

	private int makineBa�EnerjisiHesapla(int makine1, int makine2) throws Exception{
		int enerji = 0,sutunNo;
		for(sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
			if(par�aMakine.eri�(makine1, sutunNo) && par�aMakine.eri�(makine2, sutunNo)){
				enerji++;
			}
		}
		return enerji;
	}
	private int makineBa�EnerjisiHesapla(ArrayList<Integer> makineler) throws Exception{
		int  enerji = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<makineler.size()-1;elemanNo++){
			enerji += makineBa�EnerjisiHesapla(makineler.get(elemanNo),makineler.get(elemanNo+1));
		}
		return enerji;
	}
	/**
	 * yeni makine gelince tekrardan dizi olu�turmamak i�in bunu yapt�k sanki 
	 * <br>dizi i�indeymi� gibi olacak sonradan hangi makinenin diziye kat�laca�� bulunacak
	 * @param dizili�
	 * @param yeniMakine
	 * @param makineninKonumu
	 * @return
	 * @throws Exception
	 */
    private int makineBa�EnerjisiHesapla(ArrayList<Integer> dizili� , int yeniMakine, int makineninKonumu) throws Exception{
    	int enerji = makineBa�EnerjisiHesapla(dizili�);   	
    	if(makineninKonumu == 0){ // konum ba�ta i�in
    		enerji += makineBa�EnerjisiHesapla(yeniMakine, dizili�.get(0));
    	}
    	else if(makineninKonumu == dizili�.size()){ // demek ki sondad�r
    		enerji += makineBa�EnerjisiHesapla(dizili�.get(dizili�.size()-1), yeniMakine);
    	}
    	else { // makine arada demektir
    		enerji = yeniMakineAradaykenBa�EnerjisiHesapla(dizili�,makineninKonumu); 
    	}
    	return enerji;
    }

	private int yeniMakineAradaykenBa�EnerjisiHesapla(ArrayList<Integer> dizili�,int makineninKonumu) throws Exception {
		int elemanNo;
		int enerji = 0;
		for(elemanNo = 0;elemanNo<makineninKonumu-1;elemanNo++){
			enerji += makineBa�EnerjisiHesapla(elemanNo, elemanNo+1);
		}
		// ara makine burada geliyor
		enerji += makineBa�EnerjisiHesapla(makineninKonumu, dizili�.get(makineninKonumu));
		// buradan sonra makine aynen devam
		for(elemanNo= makineninKonumu+1;elemanNo<dizili�.size()-1;elemanNo++){
			enerji += makineBa�EnerjisiHesapla(elemanNo, elemanNo+1);
		}
		return enerji;
	}
	
	/**
	 * bunu ba�ta yap�yoruz ba�ta se�ili makine hari� her�ey denenecekler olcak
	 */
	private void denenecekleriBulMakine(){
		int elemanNo;
		// makineden �ncesi
		for(elemanNo=0;elemanNo<se�iliMakine;elemanNo++){
			makineDenekler.add(elemanNo);
		}
		// makineden sonras�
		for(elemanNo= se�iliMakine+1;elemanNo<par�aMakine.sat�rSay();elemanNo++){
			makineDenekler.add(elemanNo);
		}
	}
    /**
     * cevap [0] = en iyi enerjiyi verdi�i konum
     * <br>cevap [1] = en iyi enerji de�eri
     * @param denekNo
     * @return
     * @throws Exception
     */
	private int [] denenceklerleEn�yiBa�EnerjisiBul(int denekNo) throws Exception{
		int konumNo;
		int denek = makineDenekler.get(denekNo);
		int [] cevap = new int [2];
		cevap [0] = 0; // en iyi konum
		cevap [1] = makineBa�EnerjisiHesapla(makinelerEn�yiDizili�, denek, 0); //  en iyi enerji kabulu
		for(konumNo=1;konumNo<makinelerEn�yiDizili�.size();konumNo++){
		 int yeniEnerji = makineBa�EnerjisiHesapla(makinelerEn�yiDizili�, denek, konumNo);
		 if(yeniEnerji > cevap [1]){
			 cevap [1] =  yeniEnerji;
			 cevap [0] = konumNo;
		 }
		}
		return cevap;
	}
    /**
     * cevap [0] = yeni makinenin en iyi enerjiyi verdi�i konum
     * <br>cevap [1] = yeni makienin verdi�i en iyi enerji
     * <br>cevap [2] = yeni makinenin no su (hangi makine )
     * @return
     * @throws Exception
     */
	private int [] t�mDenemeleriYap() throws Exception{
		int denekNo;
		int [] konumEnerjiMakine = new int [3]; // en iyi enerjiyi veren makine , konumu ve enerjisi
		int [] konumEnerji = denenceklerleEn�yiBa�EnerjisiBul(0);
		konumEnerjiMakine [0] = konumEnerji [0];
		konumEnerjiMakine [1] = konumEnerji [1];
		konumEnerjiMakine [2] = makineDenekler.get(0);
		deneklerdenGidenMakineninDeneklerdekiKonumu  = 0;
		// en iyi dene�i bulma
		for(denekNo=0;denekNo<makineDenekler.size();denekNo++){
			int [] yeniKonumEnerji = denenceklerleEn�yiBa�EnerjisiBul(denekNo); 
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
	 * en iyi dizili�e yeni makine istenilen konumda ekler
	 * @param yeniMakine
	 * @param konumu
	 */
    private void en�yiDizili�iG�ncelle(int  yeniMakine, int konumu){
    	 makinelerEn�yiDizili�.add(konumu, yeniMakine);
    }
    private void denekleriG�ncelle(){
    	makineDenekler.remove(deneklerdenGidenMakineninDeneklerdekiKonumu);
    }
    private void g�ncellemeleriYap(int  yeniMakine, int konumu){
    	en�yiDizili�iG�ncelle(yeniMakine, konumu);
    	denekleriG�ncelle();
    }
    
    public String makine�lerle() throws Exception{
    	String ��z�m = "";
    	int [] konumEnerjiMakine = t�mDenemeleriYap();
    	��z�m += "yeni gelen makine : " + konumEnerjiMakine [2] + "\n";
    	��z�m += "gelen makinenin en iyi dizili�teki konumu : " + konumEnerjiMakine [0] +"\n";
    	��z�m += "yeni dizili�in enerjisi : " + konumEnerjiMakine [1] +"\n";
    	g�ncellemeleriYap(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    	��z�m += "yeni dizili� : " + makinelerEn�yiDizili�.toString() + "\n\n";
    	return ��z�m;
    }
    /**
     * en iyi makine dizili�ine g�re sat�rlar� s�ralar ve yeni br par�a makine matrisi olu�turur
     * @return
     * @throws Exception 
     */
    private String sat�rlar�S�rala() throws Exception{
    	String yaz� = "";
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
    		int sat�r = makinelerEn�yiDizili�.get(sat�rNo);
    		yaz� += par�aMakine.sat�r(sat�r).yaz() +"\n";
    	}
    	return yaz�;
    }
    
    
    
    
    private int par�aBa�EnerjisiHesapla(int par�a1, int par�a2) throws Exception{
		int enerji = 0,sat�rNo;
		for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
			if(par�aMakine.eri�(sat�rNo, par�a1) && par�aMakine.eri�(sat�rNo, par�a2)){
				enerji++;
			}
		}
		return enerji;
	}
	private int par�aBa�EnerjisiHesapla(ArrayList<Integer> par�alar) throws Exception{
		int  enerji = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<par�alar.size()-1;elemanNo++){
			enerji += par�aBa�EnerjisiHesapla(par�alar.get(elemanNo),par�alar.get(elemanNo+1));
		}
		return enerji;
	}
	/**
	 * yeni makine gelince tekrardan dizi olu�turmamak i�in bunu yapt�k sanki 
	 * <br>dizi i�indeymi� gibi olacak sonradan hangi makinenin diziye kat�laca�� bulunacak
	 * @param dizili�
	 * @param yeniPar�a
	 * @param par�an�nKonumu
	 * @return
	 * @throws Exception
	 */
    private int par�aBa�EnerjisiHesapla(ArrayList<Integer> dizili� , int yeniPar�a, int par�an�nKonumu) throws Exception{
    	int enerji = par�aBa�EnerjisiHesapla(dizili�);   	
    	if(par�an�nKonumu == 0){ // konum ba�ta i�in
    		enerji += par�aBa�EnerjisiHesapla(yeniPar�a, dizili�.get(0));
    	}
    	else if(par�an�nKonumu == dizili�.size()){ // demek ki sondad�r
    		enerji += par�aBa�EnerjisiHesapla(dizili�.get(dizili�.size()-1), yeniPar�a);
    	}
    	else { // makine arada demektir
    		enerji = yeniPar�aAradaykenBa�EnerjisiHesapla(dizili�,par�an�nKonumu); 
    	}
    	return enerji;
    }
    private int yeniPar�aAradaykenBa�EnerjisiHesapla(ArrayList<Integer> dizili�,int par�an�nKonumu) throws Exception {
		int elemanNo;
		int enerji = 0;
		for(elemanNo = 0;elemanNo<par�an�nKonumu-1;elemanNo++){
			enerji += par�aBa�EnerjisiHesapla(elemanNo, elemanNo+1);
		}
		// ara makine burada geliyor
		enerji += par�aBa�EnerjisiHesapla(par�an�nKonumu, dizili�.get(par�an�nKonumu));
		// buradan sonra makine aynen devam
		for(elemanNo= par�an�nKonumu+1;elemanNo<dizili�.size()-1;elemanNo++){
			enerji += par�aBa�EnerjisiHesapla(elemanNo, elemanNo+1);
		}
		return enerji;
	}
	
    /** bunu ba�ta yap�yoruz ba�ta se�ili makine hari� her�ey denenecekler olcak
	 */
	private void denenecekleriBulPar�a(){
		int elemanNo;
		// makineden �ncesi
		for(elemanNo=0;elemanNo<se�iliPar�a;elemanNo++){
			par�aDenekler.add(elemanNo);
		}
		// makineden sonras�
		for(elemanNo= se�iliPar�a+1;elemanNo<par�aMakine.sutunSay();elemanNo++){
			par�aDenekler.add(elemanNo);
		}
	}
   /**
    * cevap [0] = en iyi enerjiyi verdi�i konum
    * <br>cevap [1] = en iyi enerji de�eri
    * @param denekNo
    * @return
    * @throws Exception
    */
	private int [] denekPar�alarlaEn�yiBa�EnerjisiBul(int denekNo) throws Exception{
		int konumNo;
		int denek = par�aDenekler.get(denekNo);
		int [] cevap = new int [2];
		cevap [0] = 0; // en iyi konum
		cevap [1] = par�aBa�EnerjisiHesapla(par�alarEn�yiDizili�, denek, 0); //  en iyi enerji kabulu
		for(konumNo=1;konumNo<par�alarEn�yiDizili�.size();konumNo++){
		 int yeniEnerji = par�aBa�EnerjisiHesapla(par�alarEn�yiDizili�, denek, konumNo);
		 if(yeniEnerji > cevap [1]){
			 cevap [1] =  yeniEnerji;
			 cevap [0] = konumNo;
		 }
		}
		return cevap;
	}
   /**
    * cevap [0] = yeni makinenin en iyi enerjiyi verdi�i konum
    * <br>cevap [1] = yeni makienin verdi�i en iyi enerji
    * <br>cevap [2] = yeni makinenin no su (hangi makine )
    * @return
    * @throws Exception
    */
	private int [] t�mDenemeleriYapPar�a() throws Exception{
		int denekNo;
		int [] konumEnerjiMakine = new int [3]; // en iyi enerjiyi veren makine , konumu ve enerjisi
		int [] konumEnerji = denekPar�alarlaEn�yiBa�EnerjisiBul(0);
		konumEnerjiMakine [0] = konumEnerji [0];
		konumEnerjiMakine [1] = konumEnerji [1];
		konumEnerjiMakine [2] = par�aDenekler.get(0);
		deneklerdenGidenPar�an�nDeneklerdekiKonumu  = 0;
		// en iyi dene�i bulma
		for(denekNo=0;denekNo<par�aDenekler.size();denekNo++){
			int [] yeniKonumEnerji = denekPar�alarlaEn�yiBa�EnerjisiBul(denekNo); 
			if( yeniKonumEnerji [1] > konumEnerjiMakine [1]){
				konumEnerjiMakine [0] = yeniKonumEnerji[0];
				konumEnerjiMakine [1] = yeniKonumEnerji[1];
				konumEnerjiMakine [2] = par�aDenekler.get(denekNo);
				deneklerdenGidenPar�an�nDeneklerdekiKonumu = denekNo;
			}
		}
		return konumEnerjiMakine;
	}
   
	/**
	 * en iyi dizili�e yeni makine istenilen konumda ekler
	 * @param yeniPar�a
	 * @param konumu
	 */
   private void en�yiDizili�iG�ncellePar�a(int  yeniPar�a, int konumu){
   	 par�alarEn�yiDizili�.add(konumu, yeniPar�a);
   }
   private void denekleriG�ncellePar�a(){
	   par�aDenekler.remove(deneklerdenGidenMakineninDeneklerdekiKonumu);
   }
   private void g�ncellemeleriYapPar�a(int  yeniPar�a, int konumu){
   	en�yiDizili�iG�ncellePar�a(yeniPar�a, konumu);
   	denekleriG�ncellePar�a();
   }
   
   public String par�a�lerle() throws Exception{
   	String ��z�m = "";
   	int [] konumEnerjiMakine = t�mDenemeleriYapPar�a();
   	��z�m += "yeni gelen makine : " + konumEnerjiMakine [2] + "\n";
   	��z�m += "gelen makinenin en iyi dizili�teki konumu : " + konumEnerjiMakine [0] +"\n";
   	��z�m += "yeni dizili�in enerjisi : " + konumEnerjiMakine [1] +"\n";
   	g�ncellemeleriYapPar�a(konumEnerjiMakine[2], konumEnerjiMakine[0]);
   	��z�m += "yeni dizili� : " + par�alarEn�yiDizili�.toString() + "\n\n";
   	return ��z�m;
   }
   /**
    * en iyi makine dizili�ine g�re sat�rlar� s�ralar ve yeni br par�a makine matrisi olu�turur
    * @return
    * @throws Exception 
    */
   
   private String sutunlar�S�rala() throws Exception{
   	String yaz� = "";
   	int sat�rNo;
   	for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
   		yaz� = sat�rYaz(yaz�, sat�rNo);
   	}
   	return yaz�;
   }
   private String sat�rYaz(String yaz�, int sat�rNo) throws Exception {
	for(int sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
		int sutun = par�alarEn�yiDizili�.get(sutunNo);
		yaz� = boolDe�erYaz(yaz�, sat�rNo, sutun);
	}
	yaz� += "\n";
	return yaz�;
}
   private String boolDe�erYaz(String yaz�, int sat�rNo, int sutun)
		throws Exception {
	if(par�aMakine.eri�(sat�rNo, sutun) ){
		yaz� += "1\t";	
	}
	else {
		yaz� += "\t";
	}
	return yaz�;
}
   private String sonS�ralama() throws Exception{
	   String yaz� = "";
	   int sat�rNo;
	   	for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
	   		yaz� = sonS�ralamaSat�rYaz(yaz�, sat�rNo);
	   	}
	   return yaz�;
   }
   private String sonS�ralamaSat�rYaz(String yaz�, int sat�rNo) throws Exception {
	int sat�r = makinelerEn�yiDizili�.get(sat�rNo);
	for(int sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
		int sutun = par�alarEn�yiDizili�.get(sutunNo);
		yaz� = boolDe�erYaz(yaz�, sat�r, sutun);
	}
	yaz� += "\n";
	return yaz�;
}
   
   public String ��z() throws Exception{
    	String ��z�m = "Ba� enerji y�ntemi\n\n";
    	��z�m = makineleriGrupla(��z�m);
    	��z�m = par�alar�Grupla(��z�m);
    	��z�m += "son s�ralama : \n" + sonS�ralama() +"\n\n";
    	son = System.currentTimeMillis();
    	��z�m += "��z�m s�resi : " + (son -ba�) + " milisaniye\n\n";
    	return ��z�m;
    }
   private String par�alar�Grupla(String ��z�m) throws Exception {
	int a�amaNo;
	par�alarEn�yiDizili�.add(0);
	denenecekleriBulPar�a();
	for(a�amaNo=0;a�amaNo<par�aMakine.sutunSay()-1;a�amaNo++){
		��z�m += "a�amaNo : " + a�amaNo + "\n\n" + par�a�lerle() + "\n\n";
	}
	��z�m += "sat�rlar�n s�ralanm�� hali : \n" + sutunlar�S�rala() + "\n\n";
	return ��z�m;
    }
   private String makineleriGrupla(String ��z�m) throws Exception {
    	makinelerEn�yiDizili�.add(0);
    	denenecekleriBulMakine();
    	for(int a�amaNo=0;a�amaNo<par�aMakine.sat�rSay()-1;a�amaNo++){
    		��z�m += "a�amaNo : " + a�amaNo + "\n\n" + makine�lerle() + "\n\n";
    	}
    	��z�m += "sat�rlar�n s�ralanm�� hali : " + sat�rlar�S�rala() + "\n\n";
    	return ��z�m;
    }
    
    public String h�zl���z() throws Exception{
	    String ��z�m = "Ba� enerji y�ntemi\n\n";
     	��z�m += h�zl�MakineleriGrupla();
    	��z�m += h�zl�Par�alar�Grupla();
    	��z�m += "son s�ralama : \n" + sonS�ralama() +"\n\n";
    	son = System.currentTimeMillis();
    	��z�m += "��z�m s�resi : " + (son -ba�) + " milisaniye\n\n";
    	return ��z�m;
    } 
    private  String  h�zl�MakineleriGrupla() throws Exception{
    	makinelerEn�yiDizili�.add(0);
    	denenecekleriBulMakine();
    	for(int a�amaNo=0;a�amaNo<par�aMakine.sat�rSay()-1;a�amaNo++){
    		h�zl�Makine�lerle() ;
    	}
    	return "sat�rlar�n s�ralanm�� hali : " + sat�rlar�S�rala() + "\n\n";
    }
    private String h�zl�Par�alar�Grupla() throws Exception{
    	int a�amaNo;
    	par�alarEn�yiDizili�.add(0);
    	denenecekleriBulPar�a();
    	for(a�amaNo=0;a�amaNo<par�aMakine.sutunSay()-1;a�amaNo++){
    		h�zl�Par�a�lerle() ;
    	}
    	return  "sat�rlar�n s�ralanm�� hali : \n" + sutunlar�S�rala() + "\n\n";
    }
    private void h�zl�Par�a�lerle() throws Exception{
    	int [] konumEnerjiMakine = t�mDenemeleriYapPar�a();
       	g�ncellemeleriYapPar�a(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    }
    private void h�zl�Makine�lerle() throws Exception{
    	int [] konumEnerjiMakine = t�mDenemeleriYap();
    	g�ncellemeleriYap(konumEnerjiMakine[2], konumEnerjiMakine[0]);
    }
    
}
