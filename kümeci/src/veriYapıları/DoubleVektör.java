package veriYapýlarý;

import java.util.ArrayList;

public class DoubleVektör 
{
	public double [] dizi ;
	
	
	public DoubleVektör(int boy) throws Exception 
	{
		diziOluþturmaBoyutKontrol(boy);
		dizi =  new double[boy];
	}
	private void diziOluþturmaBoyutKontrol(int boy) throws Exception {
		if(boy <= 0 )
		{
			throw new Exception("uygunsuz boy girilen boy = " + boy);
		}
	}
    public DoubleVektör(String virgüllleAyrýlmýþ) throws Exception
    {
    	boþMuKontrol(virgüllleAyrýlmýþ);
    	String [] bölünmüþString = virgüllleAyrýlmýþ.split(",");
    	virgülVarmýKontrol(bölünmüþString);
    	int elemanNo;
    	int dizininBoyu = bölünmüþString.length;
    	dizi =  new double [dizininBoyu];
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		dizi[elemanNo] = Double.parseDouble(bölünmüþString[elemanNo]);
    	}
    }
	private void boþMuKontrol(String virgüllleAyrýlmýþ) throws Exception {
		if(virgüllleAyrýlmýþ.length() == 0)
    	{
    		throw new Exception("girlen yazý boþ ! ");
    	}
	}
	private void virgülVarmýKontrol(String[] bölünmüþString) throws Exception {
		if(bölünmüþString.length == 0)
    	{
    		throw new Exception("girlen yazý virgül içermiyor ");
    	}
	}
    public DoubleVektör(int birimVektörBoyu,int sayýnýnYeri,double sayý) throws Exception
    {
    	if(sayýnýnYeri < 0 || sayýnýnYeri > birimVektörBoyu-1)
    	{
    		throw new Exception("sayýnýn yeri vektör dýþýnda sayýný yeri = " + sayýnýnYeri + "vektörün boyutu = " + birimVektörBoyu);
    	}
    	dizi = new double[birimVektörBoyu];
    	int elemanNo;
    	dizi [sayýnýnYeri] = sayý; // bir konuldu
    	for(elemanNo=0;elemanNo<sayýnýnYeri;elemanNo++)
    	{
    		dizi[elemanNo] = 0;
    	}// birin olduðu yere kadar ki kýsým tamam
    	for(elemanNo= sayýnýnYeri+1;elemanNo<dizi.length;elemanNo++)
    	{
    		dizi[elemanNo] = 0;
    	}
    }
    public DoubleVektör (int vektörBoyu,double sayý) throws Exception
    {
    	diziOluþturmaBoyutKontrol(vektörBoyu);
    	dizi =  new double[vektörBoyu];
    	int elemanNo;
    	for(elemanNo=0;elemanNo<vektörBoyu;elemanNo++)
    	{
    		dizi [elemanNo] = sayý;
    	}
    }
    public DoubleVektör (String yazý,String ayýraç){
    	String [] bölünmüþString = yazý.split(ayýraç);
    	int elemanNo;
    	int dizininBoyu = bölünmüþString.length;
    	dizi =  new double [dizininBoyu];
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		dizi[elemanNo] = Double.parseDouble(bölünmüþString[elemanNo]);
    	}
    }

    
    
    public void rassalDoldur() throws Exception{
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDeðer = Math.random();
            deðiþ(elemanNo, yeniDeðer);
    	}
    }
    public int boy (){
    	return dizi.length;
    }
    
    
    
    // yazdýrma 
    public String yaz(String arayaGirenKarakter)
    {
    	if(dizi.length == 0)
    	{
    		return "";
    	}
    	else
    	{
    		return boþOlmayanDiziYaz(arayaGirenKarakter);
    	}
    }
	private String boþOlmayanDiziYaz(String arayaGirenKarakter)
	{
		int elemanNo;	
		String yazý = "";
		for(elemanNo=0;elemanNo<dizi.length-1;elemanNo++)
		{
			yazý += dizi[elemanNo] + arayaGirenKarakter;
		}
		yazý += dizi[elemanNo];
		return yazý;
	}
    // yazdýrma bitiþ
	
	// matematik iþlemleri
	public void topla (DoubleVektör eklenecek) throws Exception
	{
		diziÝþlemBoyutKontrol(eklenecek);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo] + eklenecek.dizi[elemanNo];
		}
	}
	private void diziÝþlemBoyutKontrol(DoubleVektör eklenecek) throws Exception 
	{
		if(eklenecek.dizi.length != dizi.length)
		{
			throw new Exception("iþleme giren dizinin boyutu tutmuyor gelen dizinin boyutu = "
					+ eklenecek.dizi.length + " yapýdaki dizi boyutu = " + dizi.length);
		}
	}
    public void çarpVeTopla(DoubleVektör eklenecek ,double katsayý) throws Exception
    {
    	diziÝþlemBoyutKontrol(eklenecek);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo] + eklenecek.dizi[elemanNo]*katsayý;
		}
    }
	public double vektörelÇarp(DoubleVektör çarpýlacak) throws Exception 
	{
		diziÝþlemBoyutKontrol(çarpýlacak);
		int elemanNo;
		double çarpým = 0;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			if(eriþ(elemanNo) != 0 && çarpýlacak.eriþ(elemanNo) != 0){
				çarpým +=  dizi[elemanNo] * çarpýlacak.dizi[elemanNo];	
			}
		}
		return çarpým;
	}
    public void çýkar(DoubleVektör çýkarýlacak) throws Exception 
    {
    	diziÝþlemBoyutKontrol(çýkarýlacak);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo] - çýkarýlacak.dizi[elemanNo];
		}
    }
    public void böl (double bölen) throws Exception
    {
    	bölenSýfýrMýKontrol(bölen);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo]/bölen;
		}
    }
	private void bölenSýfýrMýKontrol(double bölen) throws Exception {
		if(bölen == 0)
    	{
    		throw new Exception("bölen sýfýr olamaz");
    	}
	}
    public void çarp(double çarpan)
    {
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] *= çarpan;
		}
    }
    public void çýkar(double sayý){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		dizi[elemanNo] = dizi[elemanNo] - sayý;
    	}
    }
    public DoubleVektör çýkar2(DoubleVektör çýkan) throws Exception{
    	diziÝþlemBoyutKontrol(çýkan);
    	DoubleVektör sonuç = new DoubleVektör(boy());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDeðer = eriþ(elemanNo) - çýkan.eriþ(elemanNo);
    		sonuç.deðiþ(elemanNo, yeniDeðer);
    	}
    	return sonuç;
    }
    // matematik iþlemleri
    
    // seçme 
    public int enKüçüðünYeri() throws Exception{
    	int elemanNo,enKüçüðünYeri=0;
    	for(elemanNo = 0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[enKüçüðünYeri] > dizi[elemanNo]){
    			enKüçüðünYeri = elemanNo;
    		}
    	}
    	return enKüçüðünYeri;
    }
    public int enBüyüðünYeri()throws Exception{
    	int elemanNo,enBüyüðünYeri=0;
    	for(elemanNo = 0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[enBüyüðünYeri] < dizi[elemanNo]){
    			enBüyüðünYeri = elemanNo;
    		}
    	}
    	return enBüyüðünYeri;
    }
    public int enKüçüðünYeri(int nereyeKadarBakýlsýn) throws Exception{
    	int elemanNo,enKüçüðünYeri=0;
    	for(elemanNo = 0;elemanNo<nereyeKadarBakýlsýn;elemanNo++){
    		if(dizi[enKüçüðünYeri] > dizi[elemanNo]){
    			enKüçüðünYeri = elemanNo;
    		}
    	}
    	return enKüçüðünYeri;
    }
    public int enBüyüðünYeri(int nereyeKadarBakýlsýn)throws Exception{
    	int elemanNo,enBüyüðünYeri=0;
    	for(elemanNo = 0;elemanNo<nereyeKadarBakýlsýn;elemanNo++){
    		if(dizi[enBüyüðünYeri] < dizi[elemanNo]){
    			enBüyüðünYeri = elemanNo;
    		}
    	}
    	return enBüyüðünYeri;
    }
    
    public double enBüyük() throws Exception{
    	return dizi[enBüyüðünYeri()];
    }
    public double enKüçük() throws Exception{
    	return dizi[enKüçüðünYeri()];
    }
    public Boolean sýfýrdanKüçükVarMý(){
    	int elemanNo;
    	Boolean sýfýrdanKüçükVarMý=false;
    	for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[elemanNo]< 0){
    			sýfýrdanKüçükVarMý = true;
    			break;
    		}
    	}
    	return sýfýrdanKüçükVarMý;
    }
    public Boolean sýfýrdanKüçükVarMý(int nereyeKadar){
    	int elemanNo;
    	Boolean sýfýrdanKüçükVarMý=false;
    	for(elemanNo=0;elemanNo<nereyeKadar;elemanNo++){
    		if(dizi[elemanNo]< 0){
    			sýfýrdanKüçükVarMý = true;
    			break;
    		}
    	}
    	return sýfýrdanKüçükVarMý;
    }
    public Boolean sýfýrdanBüyükVarMý(){
    	int elemanNo;
    	Boolean sýfýrdanBüyükVarMý=false;
    	for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[elemanNo] > 0){
    			sýfýrdanBüyükVarMý = true;
    			break;
    		}
    	}
    	return sýfýrdanBüyükVarMý;
    }
    public Boolean sýfýrdanBüyükVarMý(int nereyeKadar){
    	int elemanNo;
    	Boolean sýfýrdanBüyükVarMý=false;
    	for(elemanNo=0;elemanNo<nereyeKadar;elemanNo++){
    		if(dizi[elemanNo] > 0){
    			sýfýrdanBüyükVarMý = true;
    			break;
    		}
    	}
    	return sýfýrdanBüyükVarMý;
    }

    public double eriþ(int elemanNo) throws Exception{
    	eriþimiKontrolEt(elemanNo);
    	return dizi[elemanNo];
    }
	private void eriþimiKontrolEt(int elemanNo) throws Exception {
		if(elemanNo < 0 || elemanNo > boy()-1){
    		throw new Exception("eriþim hatasý girilen elemanNo : " + elemanNo + " dizinin boyu : " + boy());
    	}
	}
    public void deðiþ(int elemanNo,double yeniDeðer) throws Exception{
    	eriþimiKontrolEt(elemanNo);
    	dizi[elemanNo] = yeniDeðer;
    }
    
    public DoubleVektör Vektörçek (int baþ,int son) throws Exception{
    	DoubleVektör çekilen = new DoubleVektör(son-baþ+1);
    	int elemanNo;
    	for(elemanNo=baþ;elemanNo<=son;elemanNo++){
    		çekilen.deðiþ(elemanNo-baþ, eriþ(elemanNo));
    	}
    	return çekilen;
    }
    // seçme

    public int kaçTaneVar(double sayý){
    	int elemanNo=0,bulunan = 0;
    	for(;elemanNo<dizi.length;elemanNo++){
    		if(dizi[elemanNo] == sayý){
    			bulunan ++;
    		}
    	}
    	return bulunan;
    }
    public ArrayList<Integer> konumlar(double sayý){
         ArrayList<Integer> konumlar =  new ArrayList<Integer>();
         int elemanNo;
         for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
        	 if(dizi[elemanNo] == sayý ){
        		 konumlar.add(elemanNo);
        	 }
         }
         return konumlar;
    }
    
    public Boolean elemandanÖtesiBirimMi(int baþlangýç){
    	int elemanNo;
    	int birSayýsý =0,sýfýrsayýsý = 0;
    	for(elemanNo=baþlangýç;elemanNo<boy();elemanNo++){
    		if((dizi[elemanNo] != 0 && dizi[elemanNo] != 1) || birSayýsý > 1 || sýfýrsayýsý + baþlangýç + 1 > boy() ){
    			return false;
    		}
    		birSayýsý = birSayýsýnýArtýr(elemanNo, birSayýsý);
    		sýfýrsayýsý = sýfýrSayýsýnýArtýr(elemanNo, sýfýrsayýsý);
    	}
    	return birimMiKontrolEt(baþlangýç, sýfýrsayýsý);
    }
	private int sýfýrSayýsýnýArtýr(int elemanNo, int sýfýrsayýsý) {
		if(dizi[elemanNo] == 0){
			return sýfýrsayýsý+1;
		}
		else{
			return sýfýrsayýsý;
		}
	}
	private int birSayýsýnýArtýr(int elemanNo, int birSayýsý) {
		if(dizi[elemanNo ] == 1){
			return birSayýsý+1;
		}
		return birSayýsý;
	}
	private Boolean birimMiKontrolEt(int baþlangýç, int sýfýrsayýsý) {
		if(sýfýrsayýsý  + baþlangýç + 1 == boy() ){
    		return true;
    	}
    	else {
    		return false;
    	}
	}
    public Boolean birimMi(){
    	int sýfýrlar=0,birler=0,elemanNo=0;
    	for(;elemanNo<boy();elemanNo++){
    		if(dizi[elemanNo] == 0){
    			sýfýrlar++;
    		}
    		else if(dizi[elemanNo] == 1){
    			birler++;
    		}
    		else if(birler > 1){
    			return false;
    		}
    		else {
    			return false;
    		}
    	}
    	if(sýfýrlar == boy() -1){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	
	
    public DoubleVektör kopya() throws Exception{
    	int elemanNo;
    	DoubleVektör kopya =  new DoubleVektör(boy());
    	for(elemanNo=0;elemanNo< boy();elemanNo++){
    		kopya.dizi[elemanNo] = dizi[elemanNo];
    	}
    	return kopya;
    }

    /**
     * eþik deðerden küçük olanlarý sýfýrlar
     * @param eþikDeðer
     * @throws Exception 
     */
    public void indirge(double eþikDeðer) throws Exception{
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		if(eriþ(elemanNo) < eþikDeðer){
    			deðiþ(elemanNo, 0);
    		}
    	}
    }
    
    public void yerleþ(int baþlangýç,int son,DoubleVektör vektör) throws Exception{
    	baþlangýcýKontrolEt(baþlangýç);
    	gelenVektörüKontrolEt(baþlangýç, son, vektör);
    	int elemanNo;
    	for(elemanNo=baþlangýç;elemanNo<=son;elemanNo++){
    		deðiþ(elemanNo, vektör.eriþ(elemanNo-baþlangýç));
    	}
    }
	private void gelenVektörüKontrolEt(int baþlangýç, int son,DoubleVektör vektör) throws Exception {
		if(vektör.boy() + baþlangýç > boy() || vektör.boy() > son -baþlangýç + 1){
    		throw new  Exception("gelen vektör çok büyük vektörün boyu : "  + vektör.boy()  + " baþlangýç : " + baþlangýç + " son : "+ son);
    	}
	}
	private void baþlangýcýKontrolEt(int baþlangýç) throws Exception {
		if(baþlangýç <0 || baþlangýç  > boy()-1){
    		throw new Exception("geçersiz baþlangýç deðeri baþlangýç : " + baþlangýç + " boy : "+ boy());
    	}
	}

    public double öklidUzaklýðýHesapla(DoubleVektör ikinci) throws Exception{
    	double uzaklýk = 0;
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double fark = eriþ(elemanNo) - ikinci.eriþ(elemanNo);
    		uzaklýk += fark*fark;
    	}
    	return Math.sqrt(uzaklýk);
    }
    public double öklidUzaklýðýHesapla(BoolVektör ikinci) throws Exception {
    	double uzaklýk = 0;
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double fark;
    		fark = farkýBul(ikinci, elemanNo);
    		uzaklýk += fark*fark;
    	}
    	return uzaklýk;
    }
	private double farkýBul(BoolVektör ikinci, int elemanNo) throws Exception {
		double fark;
		Boolean ikincininElemaný = ikinci.eriþ(elemanNo);
		if(ikincininElemaný){
			fark = 1- eriþ(elemanNo);
		}
		else {
			fark = eriþ(elemanNo);
		}
		return fark;
	}


}