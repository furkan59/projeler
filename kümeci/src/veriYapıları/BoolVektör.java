package veriYap�lar�;

public class BoolVekt�r {
    public boolean [] dizi;
	public BoolVekt�r(int boy,Boolean ilkDe�er) {
		dizi = new boolean [boy];
		int s�raNo;
		for(s�raNo=0;s�raNo<boy;s�raNo++){
			dizi[s�raNo] = ilkDe�er;
		}
	}
	public BoolVekt�r (int boy){
		dizi = new boolean [boy];
	}
    public BoolVekt�r (String yaz�l�,char  ay�r�c�) throws Exception{
		String ay�r�c�String = "" + ay�r�c�;
		String []  b�l�nm�� = yaz�l�.split(ay�r�c�String);
		diziyiDoldur(b�l�nm��);
	}
	/**
	 * ba� ile son aras� true geri kalan� false olacak �ekilde 
	 * <br> girilen boyda bir vekt�r olu�turur
	 * @param boy
	 * @param ba�
	 * @param son
	 */
    public BoolVekt�r (int boy,int ba�,int son){
    	dizi = new boolean [boy];
    	for(int elemanNo =ba�;elemanNo<son;elemanNo++){
    		dizi [elemanNo] = true;
    	}
	}

    private void diziyiDoldur(String[] b�l�nm��) throws Exception {
		dizi = new boolean [b�l�nm��.length];
		int elemanNo;
		for(elemanNo=0;elemanNo<b�l�nm��.length;elemanNo++){
			elemanAl(b�l�nm��, elemanNo,elemanNo);
		}
	}
	private void elemanAl(String[] b�l�nm��, int elemanNo, int diziElemanNo) throws Exception {
		if(b�l�nm��[elemanNo].compareTo("1") == 0){
			dizi[diziElemanNo] = true;
		}
		else if(b�l�nm��[elemanNo].compareTo("0") == 0 || b�l�nm��[elemanNo].compareTo("") == 0){
			dizi[diziElemanNo] = false;
		}
		else{
			throw new Exception("ya 0 ya da 1 olmal� hata var girilen rakam = :" + b�l�nm��[elemanNo] +":" );
		}
	}
	public BoolVekt�r (String yaz�,int ba�lang��, String ay�r�c�) throws Exception{
		String  [] b�l�nm�� = yaz�.split(ay�r�c�);
	    dizi = new boolean [b�l�nm��.length-1];
	    int elemanNo;
		for(elemanNo=ba�lang��;elemanNo<b�l�nm��.length;elemanNo++){
			elemanAl(b�l�nm��, elemanNo,elemanNo-ba�lang��);
		}
	}
    public Boolean eri�(int s�raNo) throws Exception{
		eri�imiKontrolEt(s�raNo);
		return dizi[s�raNo];
	}
	private void eri�imiKontrolEt(int s�raNo) throws Exception {
		if(s�raNo < 0 || s�raNo > dizi.length-1){
			throw new Exception("s�raNo uyumsuz girilen s�raNo  = " + s�raNo + " toplam uzunluk = " + dizi.length);
		}
	}
    public void de�i�(int s�raNo,Boolean yeniDe�er) throws Exception{
		eri�imiKontrolEt(s�raNo);
		dizi[s�raNo] = yeniDe�er;
	}
    
	public int boy(){
		return dizi.length;
	}

	/**
	 * ilk ba�taki elemana en b�y�k 2li de�eri verir
	 * mesela 3 elemanl� bir dizi i�in ilk eleman� 2^2 ile �arpar
	 * @return
	 * @throws Exception 
	 */
	public double ondal�kDe�eriniBul() throws Exception{
		double ondal�kDe�er = 0;
		int elemanNo ;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(eri�(elemanNo) == true){
				ondal�kDe�er += Math.pow(2, boy() - elemanNo -1 );
			}
		}
		return ondal�kDe�er;
	}
    /**
     * e�er al�nan vekt�r b�y�kse 1 d�ner
     * e�er al�nan vekt�r k���kse -1 d�ner
     * iki vekt�r e�it ise 0 d�ner
     * @throws Exception 
     */
	public int b�y�kM� (BoolVekt�r al�nan) throws Exception{
		dizlerininBoylar�n�Kar��la�t�r(al�nan);
		int sonu�  = 0; // ilk ba�ta ikisini de ayn� kabul ettik
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(al�nan.eri�(elemanNo) == false && this.eri�(elemanNo) == true){
				sonu� =  -1;
				break;
			}
			else if(al�nan.eri�(elemanNo) == true && this.eri�(elemanNo) == false){
				sonu� = 1;
				break;
			}
		}
		return sonu�; // bir farkl�l�k yok ise sonu� == 0 olarak gelir
	}
	private void dizlerininBoylar�n�Kar��la�t�r(BoolVekt�r al�nan) throws Exception {
		if(al�nan.boy() != boy()){
			throw new  Exception("dizilerin boylar� uyu�muyor al�nann�n boyu " + al�nan.boy() + " dizinin boyu = " + boy() +"\n" );
		}
	}

	public double �klidUzakl���Hesapla(DoubleVekt�r ikinci) throws Exception{
		double uzakl�k = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			Boolean de�er = eri�(elemanNo);
			double fark ;
			fark = farkBul(ikinci, elemanNo, de�er);
			uzakl�k += fark*fark;
		}
		return uzakl�k;
	}
	private double farkBul(DoubleVekt�r ikinci, int elemanNo, Boolean de�er)
			throws Exception {
		double fark;
		if(de�er){
			fark = 1 - ikinci.eri�(elemanNo); 
		}
		else {
			fark = ikinci.eri�(elemanNo);
		}
		return fark;
	}
	
    public BoolVekt�r kopya() throws Exception{
    	BoolVekt�r  kopyas� = new BoolVekt�r(boy()); 
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		kopyas�.de�i�(elemanNo, eri�(elemanNo));
    	}
    	return kopyas�;
    }

    public String yaz() throws Exception{
    	String yaz� = "";
    	int elemanNo;
    	for(elemanNo = 0;elemanNo<boy();elemanNo++){
    		if(eri�(elemanNo) ){
    			yaz� += "1\t";
    		}
    		else {
    			yaz� += "\t";
    		}
    	}
    	return yaz�;
    }
    /**
     * 0 lar� da yazar
     * @return
     * @throws Exception 
     */
    public String veriYaz() throws Exception{
    	String yaz� = "";
    	int elemanNo;
    	for(elemanNo = 0;elemanNo<boy();elemanNo++){
    		if(eri�(elemanNo) ){
    			yaz� += "1\t";
    		}
    		else {
    			yaz� += "0\t";
    		}
    	}
    	return yaz�;
    }
}
