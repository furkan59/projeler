package veriYapýlarý;


import java.util.ArrayList;

public class DoubleDizi 
{
	public DoubleVektör[] dizi;
	private void diziyiDoldur(String[] satýrlar) throws Exception {
		int satýrSayýsý = satýrlar.length;
		if(satýrlar[0].length() == 0){
			dizi = new DoubleVektör[satýrSayýsý-1];
			ilkElemanBoþkenDizi(satýrlar, satýrSayýsý);
		}
		else {
			dizi = new DoubleVektör[satýrSayýsý];
			ilkElemanYokkenDizi(satýrlar, satýrSayýsý);
		}
	}
	private void ilkElemanYokkenDizi(String[] satýrlar, int satýrSayýsý)throws Exception {
		int elemanNo;
		for(elemanNo=0;elemanNo < satýrSayýsý;elemanNo++)
		{
			dizi[elemanNo] = new DoubleVektör(satýrlar[elemanNo]);
		}
	}
	private void ilkElemanBoþkenDizi(String[] satýrlar, int satýrSayýsý) throws Exception {
		int elemanNo;
		for(elemanNo=1;elemanNo < satýrSayýsý;elemanNo++)
		{
			dizi[elemanNo-1] = new DoubleVektör(satýrlar[elemanNo]);
		}
	}
    public DoubleDizi (int satýrSayýsý,int sutunSayýsý,double sayýsý) throws Exception{
    	diziOluþturmBoyutKontrol(satýrSayýsý, sutunSayýsý);
    	int satýrNo;
    	dizi = new DoubleVektör[satýrSayýsý];
    	for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
    		dizi[satýrNo] =  new DoubleVektör(sutunSayýsý, sayýsý);
    	}
    }
	private void diziOluþturmBoyutKontrol(int satýrSayýsý, int sutunSayýsý) throws Exception
    {
		if(satýrSayýsý <= 0 || sutunSayýsý <= 0)
    	{
    		throw new Exception("boyut uyumsuzluðu var satýr sayýsý = " + satýrSayýsý + 
    				"  sutun sayýsý = " + sutunSayýsý);
    	}
	}
    public DoubleDizi (int satýrSayýsý){
    	dizi = new DoubleVektör[satýrSayýsý];
    }
    public DoubleDizi (int satýrSayýsý,int sutunSayýsý) throws Exception{
    	diziOluþturmBoyutKontrol(satýrSayýsý, sutunSayýsý);
    	int satýrNo;
    	dizi = new DoubleVektör[satýrSayýsý];
    	for(satýrNo=0;satýrNo<satýrSayýsý;satýrNo++){
    		dizi[satýrNo] =  new DoubleVektör(sutunSayýsý);
    	}
    }
    /**
     * 1 2 3 4 
     * <br>4 4 5 6
     * <br>4 5 7 5
     * <br>þeklinde verildiðinde çalýþýr
     * @param yazý
     * @throws Exception 
     */
    public DoubleDizi (String yazý,String ayýraç) throws Exception{
    	String düzeltilmiþ = "";
    	veriyiDüzenle(yazý, düzeltilmiþ);
    	String [] satýrlar = düzeltilmiþ.split(ayýraç);
    	diziyiDoldur(satýrlar);
    }
	private void veriyiDüzenle(String yazý, String düzeltilmiþ) {
		if( yazý.charAt(0) == '\n'){
    		düzeltilmiþ = yazý.substring(1);
    	}
    	if (düzeltilmiþ.charAt(düzeltilmiþ.length()-1) == '\n'){
    		düzeltilmiþ = düzeltilmiþ.substring(0,düzeltilmiþ.length()-1);
    	}
	}
    

    
    public void rassalDiziDoldur() throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		DoubleVektör yeniSatýr = new DoubleVektör(sutunsay());
    		yeniSatýr.rassalDoldur();
    		dizi [satýrNo] = yeniSatýr;
    	}
    }
	
    
    public int sutunsay()
	{
		return dizi[0].dizi.length;
	}
	public int satýrsay()
	{
		return dizi.length;
	}
	public String yaz(String arayaGirenKarakter)
	{
		if(satýrsay()== 0)
		{
			return "";
		}
		else
		{
			String yazý = boþOlmayanDiziYaz(arayaGirenKarakter);
			return yazý;
		}
	}
	private String boþOlmayanDiziYaz(String arayaGirenKarakter)
	{
		String yazý = "";
		int satýrNo;
		for(satýrNo=0;satýrNo<dizi.length-1;satýrNo++)
		{
			yazý += dizi[satýrNo].yaz(arayaGirenKarakter) + "\n";
		}
		yazý += dizi[satýrNo].yaz(arayaGirenKarakter);
		return yazý;
	}

	public double eriþ(int satýrNo,int sutunNo) throws Exception
	{
		eriþimSatýrKontroEt(satýrNo,sutunNo);
		eriþimSutunKontrolEt(sutunNo,satýrNo);
		return dizi[satýrNo].dizi[sutunNo];
	}
	private void eriþimSutunKontrolEt(int sutunNo,int satýrNo) throws Exception {
		if(sutunNo < 0 || sutunNo > dizi[satýrNo].boy()-1){
			throw new Exception("sutunno dizi sýnýrlarý dýþýnda sutunno = " + sutunNo + " dizinin sutun sayýsý = " + sutunsay()
					+ "Satýrno = " + satýrNo);
		}
	}
	private void eriþimSatýrKontroEt(int satýrNo,int sutunNo) throws Exception {
		if(satýrNo<0 ||satýrNo > satýrsay()-1){
			throw new Exception("satýrno dizi sýnýrlarý dýþýnda satýrno = " + satýrNo + " dizinin satýr sayýsý = " + satýrsay()+ 
					"\nSutunNo = " + sutunNo);
		}
	}
	public void deðiþ(int satýrNo,int sutunNo,double yenideðer) throws Exception{
		eriþimSatýrKontroEt(satýrNo, sutunNo);
		eriþimSutunKontrolEt(sutunNo, satýrNo);
		dizi[satýrNo].dizi[sutunNo] = yenideðer;
	}
	// matematiksel iþlemler

	public void satýrTopla(int çarpýlacakSatýr,int eklenecekSatýr,double katsayý ) throws Exception{
		int sutunNo;
		for(sutunNo=0;sutunNo<sutunsay();sutunNo++){
			double yeniDeðer = eriþ(eklenecekSatýr,sutunNo) + eriþ(çarpýlacakSatýr,sutunNo)*katsayý;
			deðiþ(eklenecekSatýr,sutunNo,yeniDeðer);
		}
	}
	
	public DoubleVektör çarp (DoubleVektör çarpan) throws Exception
	{
		vektörelBoyutKontrol(çarpan);
		int elemanNo;
	    DoubleVektör cevap = new DoubleVektör(dizi.length);	
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			cevap.dizi[elemanNo] = dizi[elemanNo].vektörelÇarp(çarpan);
		}
		return cevap;
	}
	
    private void vektörelBoyutKontrol(DoubleVektör çarpan) throws Exception 
	{
		if(çarpan.dizi.length == 0 || çarpan.dizi.length != sutunsay())
		{
			throw new Exception("çarpan ile dizi uyumsuz çarpanýn boyu = " + çarpan.dizi.length + 
					" dizinin sutunsayýsý = " + sutunsay());
		}
	}
	
    public void sutunÝlePivot(int pivotSatýr,DoubleVektör sutun) throws Exception{
    	sutunÝlePivotHatalar(pivotSatýr, sutun);
        pivotSatýrýBöl(pivotSatýr, sutun);
    	yardýmcýSutunÝlePivotÝþlem(pivotSatýr, sutun);
    }
	private void sutunÝlePivotHatalar(int pivotSatýr, DoubleVektör sutun)throws Exception {
		pivotSatýrKontrol(pivotSatýr);
    	yardýmcýSutunÝleDiziyiKarþýlaþtýr(sutun);
	}
	private void pivotSatýrýBöl(int pivotSatýr, DoubleVektör sutun)throws Exception {
		double bölen = sutun.dizi[pivotSatýr];
    	dizi[pivotSatýr].böl(bölen);
	}
	private void yardýmcýSutunÝlePivotÝþlem(int pivotSatýr, DoubleVektör sutun)throws Exception {
		if(pivotSatýr == 0)
		{
    		yardýmcýSutunPivotÝlkSatýrÝçin(pivotSatýr, sutun);
		}
		else if(pivotSatýr == satýrsay()-1)
		{
			yardýmcýSutunPivotSonda(pivotSatýr, sutun);
		}
		else
		{
			yardýmcýSutunPivotArada(pivotSatýr, sutun);
		}
	}
	private void yardýmcýSutunPivotSonda(int pivotSatýr, DoubleVektör sutun)throws Exception {
		int satýr;
		for(satýr=0;satýr<satýrsay()-1;satýr++)
		{
			if(sutun.dizi[satýr]!= 0){
			double katsayý = -1 * sutun.dizi[satýr];
			dizi[satýr].çarpVeTopla(dizi[pivotSatýr], katsayý);
			}
		}
	}
	private void yardýmcýSutunPivotArada(int pivotSatýr, DoubleVektör sutun)throws Exception {
		int satýr;
		for(satýr=0;satýr<pivotSatýr;satýr++)
		{
			if(sutun.dizi[satýr]!= 0){
				double katsayý = -1 * sutun.dizi[satýr];
				dizi[satýr].çarpVeTopla(dizi[pivotSatýr], katsayý);
			}
		}
		for(satýr=pivotSatýr+1;satýr<satýrsay();satýr++)
		{
			if(sutun.dizi[satýr]!= 0){
				double katsayý = -1 * sutun.dizi[satýr];
				dizi[satýr].çarpVeTopla(dizi[pivotSatýr], katsayý);
			}
		}
	}
	private void yardýmcýSutunPivotÝlkSatýrÝçin(int pivotSatýr,DoubleVektör sutun) throws Exception {
		int satýr;
		for(satýr=1;satýr<satýrsay();satýr++)
		{
			if(sutun.dizi[satýr] != 0){
				double katsayý = -1 * sutun.dizi[satýr];
				dizi[satýr].çarpVeTopla(dizi[pivotSatýr], katsayý);
			}
		}
	}
	private void yardýmcýSutunÝleDiziyiKarþýlaþtýr(DoubleVektör sutun)
			throws Exception {
		if(sutun.boy() != satýrsay()){
    		throw new Exception("yardýmcý sutun ile dizinin boyutlarý uyumsuz boy : "+ sutun.boy() + " dizinin satýr sayýsý : " + satýrsay());
    	}
	}
	private void pivotSatýrKontrol(int pivotSatýr) throws Exception {
		if(pivotSatýr<0 || pivotSatýr > satýrsay()-1){
    		throw new Exception("pivot satýr sýnýrlar dýþýnda pivot satýr : " + pivotSatýr + " tablonun satýrsayýsý : " + satýrsay() );
    	}
	}
    
    public void pivot(int pivotsatýr,int pivotsutun) throws Exception
	{
		pivotSatýrSutunSýfýrdanKüçükMü(pivotsatýr, pivotsutun);
		pivotSatýrSutunSýnýrlarDýþýndaMý(pivotsatýr, pivotsutun);
		double pivotEleman = eriþ(pivotsatýr,pivotsutun);
		dizi[pivotsatýr].böl(pivotEleman); 
		pivotÝþlem(pivotsatýr, pivotsutun);
	}
    private void pivotÝþlem(int pivotsatýr, int pivotsutun) throws Exception 
	{
		if(pivotsatýr == 0)
		{
			pivotSatýrSýfýr(pivotsatýr, pivotsutun);
		}
		else if(pivotsatýr == satýrsay()-1)
		{
			pivotSatýrSonda(pivotsatýr, pivotsutun);
		}
		else
		{
			pivotSatýrArada(pivotsatýr, pivotsutun);
		}
	}
    private void pivotSatýrArada(int pivotsatýr, int pivotsutun)throws Exception 
	{
		int satýr;
		for(satýr=0;satýr<pivotsatýr;satýr++)
		{
			if(eriþ(satýr,pivotsutun)!= 0){
				double katsayý = -1 * eriþ(satýr,pivotsutun);
				dizi[satýr].çarpVeTopla(dizi[pivotsatýr], katsayý);
			}
		}
		for(satýr=pivotsatýr+1;satýr<satýrsay();satýr++)
		{
			if(eriþ(satýr,pivotsutun)!= 0){
				double katsayý = -1 * eriþ(satýr,pivotsutun);
				dizi[satýr].çarpVeTopla(dizi[pivotsatýr], katsayý);
			}
		}
	}
    private void pivotSatýrSonda(int pivotsatýr, int pivotsutun) throws Exception 
	{
		int satýr;
		for(satýr=0;satýr<satýrsay()-1;satýr++)
		{
			if(eriþ(satýr,pivotsutun)!= 0){
			double katsayý = -1 * eriþ(satýr,pivotsutun);
			dizi[satýr].çarpVeTopla(dizi[pivotsatýr], katsayý);
			}
		}
	}	
    private void pivotSatýrSýfýr(int pivotsatýr, int pivotsutun) throws Exception 
	{
		int satýr;
		for(satýr=1;satýr<satýrsay();satýr++)
		{
			if(eriþ(satýr,pivotsutun)!= 0){
				double katsayý = -1 * eriþ(satýr,pivotsutun);
				dizi[satýr].çarpVeTopla(dizi[pivotsatýr], katsayý);
			}
		}
	}
	private void pivotSatýrSutunSýnýrlarDýþýndaMý(int satýrNo, int sutunNo)throws Exception 
	{
		if(satýrNo > satýrsay()-1 || sutunNo > sutunsay()-1)
		{
			throw new Exception("satýrNo ve ya sutunNo dizi dýþýnda olamaz olamaz satýrsayýsý = "+ 
		   satýrsay() + " sutunsayýsý = " + sutunsay() + " girilen sutunNo = " + sutunNo + 
		   " girilen satýrNo " + satýrNo );
		}
	}
    private void pivotSatýrSutunSýfýrdanKüçükMü(int satýrNo, int sutunNo)throws Exception 
	{
		if(satýrNo < 0 || sutunNo < 0)
		{
			throw new Exception("satýrNo ve ya sutunNo sýfýrdan küçük olamaz satýrsayýsý = "+ 
		   satýrsay() + " sutunsayýsý = " + sutunsay() + " girilen sutunNo = " + sutunNo + 
		   " girilen satýrNo " + satýrNo );
		}
	}

    public void çarp(double sayý){
    	int satýrNo;
    	for(satýrNo =0;satýrNo<satýrsay();satýrNo++){
    		dizi[satýrNo].çarp(sayý);
    	}
    }
	public DoubleDizi çýkar(DoubleDizi çýkan) throws Exception{
		DoubleDizi sonuç = new DoubleDizi(satýrsay());
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
			DoubleVektör yeniSatýr = new DoubleVektör(sutunsay());
			sonuç.dizi[satýrNo] = yeniSatýr;
		}
		return sonuç;
		
	}
    public void topla(DoubleDizi toplanan) throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		DoubleVektör eklenecek = toplanan.dizi[satýrNo];
    		dizi[satýrNo].topla(eklenecek);
    	}
    }
    // matematiksel iþlemler

    // yerleþtirme ve deðiþtirme ve çekme
    
    public void sutunaYerleþ(DoubleVektör sutun,int sutunNo,int baþlangýç) throws Exception{
    	sutunaYerleþHata(sutunNo);
    	if(sutun == null){
    		throw new Exception("girilen sutun boþ");
    	}
    	int elemanNo;
    	for(elemanNo=0;elemanNo<sutun.dizi.length;elemanNo++){
    		deðiþ(elemanNo+baþlangýç,sutunNo,sutun.dizi[elemanNo]);
    	}
    }
	private void sutunaYerleþHata(int sutunNo) throws Exception {
		if(sutunNo < 0 )
    	{
    		throw new Exception("sutun no 0 dan küçük olamaz sutun no =" + sutunNo);
    	}
    	if(sutunNo > sutunsay()-1)
    	{
    		throw new Exception("sutun no dizi dýþýnda olamaz sutun no = "+ sutunNo);
    	}
	}
    public DoubleVektör sutuncek(int sutunNo) throws Exception
    {
    	sutunaYerleþHata(sutunNo);
    	DoubleVektör sutun = new DoubleVektör(satýrsay());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<satýrsay();elemanNo++)
    	{
    		sutun.dizi[elemanNo] = eriþ(elemanNo, sutunNo);
    	}
    	return sutun;
    }
    public DoubleVektör sutuncek(int sutunNo,int baþlangýç) throws Exception{
    	sutunaYerleþHata(sutunNo);
    	sutuncekBaþlangýçHatasý(baþlangýç);
    	DoubleVektör sutun = new DoubleVektör(satýrsay()-baþlangýç);
    	int elemanNo;
    	for(elemanNo=baþlangýç;elemanNo<satýrsay();elemanNo++)
    	{
    		sutun.dizi[elemanNo-baþlangýç] = eriþ(elemanNo, sutunNo);
    	}
    	return sutun;
    }
	private void sutuncekBaþlangýçHatasý(int baþlangýç) throws Exception {
		if(baþlangýç < 0 || baþlangýç > satýrsay()-1){
    		throw new Exception("baþlangýç dizi sýnýrlarý dýþýnda\ndizideki satýrsayýsý = " + satýrsay() + "girlen baþlangýç deðeri" + baþlangýç);
    	}
	}

	
	public DoubleVektör satýrcek(int satýrNo,int baþlangýç,int son) throws Exception{
		satýrÇekmeHatalarýBul(satýrNo, baþlangýç, son);
		return satýrÇekmeÝþlemi(satýrNo, baþlangýç,son);
	}
	private void satýrÇekmeHatalarýBul(int satýrNo, int baþlangýç, int son)throws Exception {
		satýrçekHata(satýrNo);
		satýrÇekmeBaþlangýçHatasý(baþlangýç);
		satýrÇekmeSonDeðeriHatasýBul(son);
	}
	private void satýrÇekmeSonDeðeriHatasýBul(int son) throws Exception {
		if(son < 0 || son > sutunsay()-1){
			throw new Exception("girilen son deðeri satýrçekme için uygundeðil\nson deðeri = " + son + "dizinin sutun sayýsý = " + sutunsay());
		}
	}
	private DoubleVektör satýrÇekmeÝþlemi(int satýrNo, int baþlangýç,int son)throws Exception {
		int elemanNo;
    	DoubleVektör satýr =  new DoubleVektör(son - baþlangýç+1);
    	for(elemanNo=baþlangýç;elemanNo<=son;elemanNo++){
    		satýr.dizi[elemanNo-baþlangýç] = eriþ(satýrNo,elemanNo);
    	}
		return satýr;
	}
	private void satýrÇekmeBaþlangýçHatasý(int baþlangýç) throws Exception {
		if(baþlangýç < 0 || baþlangýç > sutunsay()-1){
			throw new Exception("satýrcekme için yanlýþ baþlangýç deðeri " + "\ngirilen baþlangýç deðeri =" + baþlangýç + 
					"dizideki sutunsayýsý = "  +sutunsay());
		}
	}
    public DoubleVektör satýrcek(int satýrNo) throws Exception
    {
    	satýrçekHata(satýrNo);
    	DoubleVektör satýr =  new DoubleVektör(sutunsay());
    	satýrÇekmeÝþlemi(satýrNo, satýr);
    	return satýr;
    }
	private void satýrÇekmeÝþlemi(int satýrNo, DoubleVektör satýr)throws Exception {
		int elemanNo;
    	for(elemanNo=0;elemanNo<sutunsay();elemanNo++){
    		satýr.dizi[elemanNo] = eriþ(satýrNo,elemanNo);
    	}
	}
	private void satýrçekHata(int satýrNo) throws Exception {
		if(satýrNo < 0 )
    	{
    		throw new Exception("satýr no sýfýrdan küçük olamaz satýr no = " +  satýrNo);
    	}
    	if(satýrNo > satýrsay()-1)
    	{
    		throw new Exception("satýr no dizinin sýnýrlarý dýþýnda olamaz satýr no = " + satýrNo);
    	}
	}
   	public void satýraYerleþ(DoubleVektör satýr,int satýrNo,int baþlangýç) throws Exception
	{
   		gelenVektörüKontrolEt(satýr, baþlangýç);
		int elemanNo;
    	for(elemanNo=0;elemanNo<satýr.dizi.length;elemanNo++){
    		deðiþ(satýrNo,elemanNo+baþlangýç,satýr.dizi[elemanNo]);
    	}
	}
	private void gelenVektörüKontrolEt(DoubleVektör satýr, int baþlangýç)
			throws Exception {
		if(satýr.boy() + baþlangýç  > sutunsay()){
   			throw new Exception("gelen vektör dizi için büyük vektörün boyu : " + satýr.boy() + "\nbaþlangýç : " + baþlangýç + 
   					"\ndizinin sutunsayýsý : "  + sutunsay() );
   		}
	}
	public void sutundanSayýÇýkar(int sutunNo,double sayý) throws Exception{
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
			deðiþ(satýrNo,sutunNo,eriþ(satýrNo,sutunNo)-sayý);
		}
	}
   	
	public DoubleDizi dizicek(int satýr1,int satýr2,int sutun1,int sutun2) throws Exception{
		satýrlarýKarþýlaþtýr(satýr1, satýr2);
		sutunlarýKarþýlaþtýr(sutun1, sutun2);
		DoubleDizi çekilen = new DoubleDizi(satýr2-satýr1+1, sutun2-sutun1 +1);
		int satýrNo;
		for(satýrNo=satýr1;satýrNo<=satýr2;satýrNo++){
			çekilen.satýraYerleþ(satýrcek(satýrNo, sutun1, sutun2), satýrNo-satýr1, 0);
		}
		return çekilen;
	}
	private void sutunlarýKarþýlaþtýr(int sutun1, int sutun2) throws Exception {
		if(sutun2 < sutun1){
			throw new Exception("sutun2 sutun1 den küçük olamaz sutun1 : "  +sutun1 + "sutun2 : " + sutun2);
		}
	}
	private void satýrlarýKarþýlaþtýr(int satýr1, int satýr2) throws Exception {
		if(satýr2 < satýr1  ){
			throw new Exception("satýr2 satýr1 den küçük olamaz satýr1 : "+ satýr1 + " satýr2 : "  + satýr2);
		}
	}
	/**
	 * eþik deðerden küçük olanlarý sýfýrlar
	 * @param eþikDeðer
	 * @throws Exception 
	 */
	public void indirge(double eþikDeðer) throws Exception{
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
			dizi[satýrNo].indirge(eþikDeðer);
		}
	}
	// yerleþtirme ve deðiþtirme ve çekme
	
	

	public int kaçTaneVar(double sayý){
		int satýrNo=0;
		int bulunan=0;
		for(;satýrNo<satýrsay();satýrNo++){
			bulunan += dizi[satýrNo].kaçTaneVar(sayý);
		}
		return bulunan;
	}
    public ArrayList<ArrayList<Integer>> AynýSatýrdaOlanlarýBul(double sayý) throws Exception{
    	int satýrNo;
    	ArrayList<ArrayList<Integer>> konumlar = new ArrayList<ArrayList<Integer>>();
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		konumlar.add(dizi[satýrNo].konumlar(sayý));
    	}
    	return konumlar;
    }
    public ArrayList<ArrayList<Integer>> AynýSutundaOlanlarýBul(double sayý) throws Exception{
    	int sutunNo;
    	ArrayList<ArrayList<Integer>> konumlar = new ArrayList<ArrayList<Integer>>();
    	for(sutunNo=0;sutunNo<satýrsay();sutunNo++){
    		konumlar.add(sutuncek(sutunNo).konumlar(sayý));
    	}
    	return konumlar;
    }
    public ArrayList<ArrayList<Integer>> konumlarýBul(double sayý) throws Exception{
         int satýrNo,sutunNo;
         ArrayList<ArrayList<Integer>> konumlar  = new ArrayList<ArrayList<Integer>>();
         for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
        	 for(sutunNo=0;sutunNo<sutunsay();sutunNo++){
        		 ArrayList<Integer> satýr = new ArrayList<Integer>();
        		 if(eriþ(satýrNo, sutunNo) == sayý){
        			 satýr.add(satýrNo);
        			 satýr.add(sutunNo);
        			 konumlar.add(satýr);
        		 }
        	 }
         }
         return konumlar;
    }

    public DoubleDizi kopya() throws Exception{
    	int satýrNo,sutunNo;
    	DoubleDizi kopya = new DoubleDizi(satýrsay(),sutunsay(),0);
    	for(satýrNo=0;satýrNo<satýrsay();satýrNo++){
    		for(sutunNo=0;sutunNo<sutunsay();sutunNo++){
        		kopya.deðiþ(satýrNo, sutunNo, eriþ(satýrNo,sutunNo));
        	}
    	}
    	return kopya;
    }

}
