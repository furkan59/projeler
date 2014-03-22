package veriYapýlarý;

public class BoolVektör {
    public boolean [] dizi;
	public BoolVektör(int boy,Boolean ilkDeðer) {
		dizi = new boolean [boy];
		int sýraNo;
		for(sýraNo=0;sýraNo<boy;sýraNo++){
			dizi[sýraNo] = ilkDeðer;
		}
	}
	public BoolVektör (int boy){
		dizi = new boolean [boy];
	}
    public BoolVektör (String yazýlý,char  ayýrýcý) throws Exception{
		String ayýrýcýString = "" + ayýrýcý;
		String []  bölünmüþ = yazýlý.split(ayýrýcýString);
		diziyiDoldur(bölünmüþ);
	}
	/**
	 * baþ ile son arasý true geri kalaný false olacak þekilde 
	 * <br> girilen boyda bir vektör oluþturur
	 * @param boy
	 * @param baþ
	 * @param son
	 */
    public BoolVektör (int boy,int baþ,int son){
    	dizi = new boolean [boy];
    	for(int elemanNo =baþ;elemanNo<son;elemanNo++){
    		dizi [elemanNo] = true;
    	}
	}

    private void diziyiDoldur(String[] bölünmüþ) throws Exception {
		dizi = new boolean [bölünmüþ.length];
		int elemanNo;
		for(elemanNo=0;elemanNo<bölünmüþ.length;elemanNo++){
			elemanAl(bölünmüþ, elemanNo,elemanNo);
		}
	}
	private void elemanAl(String[] bölünmüþ, int elemanNo, int diziElemanNo) throws Exception {
		if(bölünmüþ[elemanNo].compareTo("1") == 0){
			dizi[diziElemanNo] = true;
		}
		else if(bölünmüþ[elemanNo].compareTo("0") == 0 || bölünmüþ[elemanNo].compareTo("") == 0){
			dizi[diziElemanNo] = false;
		}
		else{
			throw new Exception("ya 0 ya da 1 olmalý hata var girilen rakam = :" + bölünmüþ[elemanNo] +":" );
		}
	}
	public BoolVektör (String yazý,int baþlangýç, String ayýrýcý) throws Exception{
		String  [] bölünmüþ = yazý.split(ayýrýcý);
	    dizi = new boolean [bölünmüþ.length-1];
	    int elemanNo;
		for(elemanNo=baþlangýç;elemanNo<bölünmüþ.length;elemanNo++){
			elemanAl(bölünmüþ, elemanNo,elemanNo-baþlangýç);
		}
	}
    public Boolean eriþ(int sýraNo) throws Exception{
		eriþimiKontrolEt(sýraNo);
		return dizi[sýraNo];
	}
	private void eriþimiKontrolEt(int sýraNo) throws Exception {
		if(sýraNo < 0 || sýraNo > dizi.length-1){
			throw new Exception("sýraNo uyumsuz girilen sýraNo  = " + sýraNo + " toplam uzunluk = " + dizi.length);
		}
	}
    public void deðiþ(int sýraNo,Boolean yeniDeðer) throws Exception{
		eriþimiKontrolEt(sýraNo);
		dizi[sýraNo] = yeniDeðer;
	}
    
	public int boy(){
		return dizi.length;
	}

	/**
	 * ilk baþtaki elemana en büyük 2li deðeri verir
	 * mesela 3 elemanlý bir dizi için ilk elemaný 2^2 ile çarpar
	 * @return
	 * @throws Exception 
	 */
	public double ondalýkDeðeriniBul() throws Exception{
		double ondalýkDeðer = 0;
		int elemanNo ;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(eriþ(elemanNo) == true){
				ondalýkDeðer += Math.pow(2, boy() - elemanNo -1 );
			}
		}
		return ondalýkDeðer;
	}
    /**
     * eðer alýnan vektör büyükse 1 döner
     * eðer alýnan vektör küçükse -1 döner
     * iki vektör eþit ise 0 döner
     * @throws Exception 
     */
	public int büyükMü (BoolVektör alýnan) throws Exception{
		dizlerininBoylarýnýKarþýlaþtýr(alýnan);
		int sonuç  = 0; // ilk baþta ikisini de ayný kabul ettik
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(alýnan.eriþ(elemanNo) == false && this.eriþ(elemanNo) == true){
				sonuç =  -1;
				break;
			}
			else if(alýnan.eriþ(elemanNo) == true && this.eriþ(elemanNo) == false){
				sonuç = 1;
				break;
			}
		}
		return sonuç; // bir farklýlýk yok ise sonuç == 0 olarak gelir
	}
	private void dizlerininBoylarýnýKarþýlaþtýr(BoolVektör alýnan) throws Exception {
		if(alýnan.boy() != boy()){
			throw new  Exception("dizilerin boylarý uyuþmuyor alýnannýn boyu " + alýnan.boy() + " dizinin boyu = " + boy() +"\n" );
		}
	}

	public double öklidUzaklýðýHesapla(DoubleVektör ikinci) throws Exception{
		double uzaklýk = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			Boolean deðer = eriþ(elemanNo);
			double fark ;
			fark = farkBul(ikinci, elemanNo, deðer);
			uzaklýk += fark*fark;
		}
		return uzaklýk;
	}
	private double farkBul(DoubleVektör ikinci, int elemanNo, Boolean deðer)
			throws Exception {
		double fark;
		if(deðer){
			fark = 1 - ikinci.eriþ(elemanNo); 
		}
		else {
			fark = ikinci.eriþ(elemanNo);
		}
		return fark;
	}
	
    public BoolVektör kopya() throws Exception{
    	BoolVektör  kopyasý = new BoolVektör(boy()); 
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		kopyasý.deðiþ(elemanNo, eriþ(elemanNo));
    	}
    	return kopyasý;
    }

    public String yaz() throws Exception{
    	String yazý = "";
    	int elemanNo;
    	for(elemanNo = 0;elemanNo<boy();elemanNo++){
    		if(eriþ(elemanNo) ){
    			yazý += "1\t";
    		}
    		else {
    			yazý += "\t";
    		}
    	}
    	return yazý;
    }
    /**
     * 0 larý da yazar
     * @return
     * @throws Exception 
     */
    public String veriYaz() throws Exception{
    	String yazý = "";
    	int elemanNo;
    	for(elemanNo = 0;elemanNo<boy();elemanNo++){
    		if(eriþ(elemanNo) ){
    			yazý += "1\t";
    		}
    		else {
    			yazý += "0\t";
    		}
    	}
    	return yazý;
    }
}
