package veriYapýlarý;

import java.util.ArrayList;

public class BoolDizi {

	public BoolVektör [] matris;
	public BoolDizi(int satýr,int sutun,Boolean ilkDeðer) {
		matris = new BoolVektör [satýr];
		int satýrNo;
		for(satýrNo = 0;satýrNo < satýr ;satýrNo++){
			matris[satýrNo] = new BoolVektör(sutun, ilkDeðer);
		}
	}

	public BoolDizi (String yazýlý,char ayýrýcý) throws Exception{
		String [] satýrlar = yazýlý.split("\\n");
		matris = new BoolVektör[satýrlar.length];
		diziyiDoldur(ayýrýcý, satýrlar);
	}
	/**
	 *  içi boþ satýrlar oluþturur sonradan doldurulmasý gerekir yarým bir oluþturmadýr
	 * @param satýrSayýsý
	 */
	public BoolDizi (int satýrSayýsý){
		matris = new BoolVektör [satýrSayýsý];
	}
    private void diziyiDoldur(char ayýrýcý, String[] satýrlar) throws Exception {
		if(satýrlar[0].length() < 1){ // ilk baþta boþ varmýþ
			baþtBoþElemanVarsa(ayýrýcý, satýrlar);
		}
		else {
			baþtaBoþElemanYoksa(ayýrýcý, satýrlar);
		}
	}
    private void baþtaBoþElemanYoksa(char ayýrýcý, String[] satýrlar) throws Exception {
		int satýrNo;
		for(satýrNo=0;satýrNo<satýrlar.length;satýrNo++){
			matris[satýrNo] = new BoolVektör(satýrlar[satýrNo], ayýrýcý);
		}
	}
    private void baþtBoþElemanVarsa(char ayýrýcý, String[] satýrlar) throws Exception {
		int satýrNo;
		for(satýrNo=1;satýrNo<satýrlar.length;satýrNo++){
			matris[satýrNo-1] = new BoolVektör(satýrlar[satýrNo], ayýrýcý);
		}
	}
	
	public int satýrSay(){
		return matris.length;
	}
	public int sutunSay(){
		return matris[0].boy();
	}
	public Boolean eriþ(int satýrNo,int sutunNo) throws Exception{
		satýraEriþimiKontrolEt(satýrNo);
		return matris[satýrNo].eriþ(sutunNo);
	}

	public BoolVektör satýr(int satýrNo){
		return matris[satýrNo];
	}
	public BoolVektör sutun(int sutunNo) throws Exception{
		BoolVektör sutun = new BoolVektör(satýrSay());
		for(int satýrNo=0;satýrNo<satýrSay();satýrNo++){
			boolean yeniDeðer = eriþ(satýrNo, sutunNo);
			sutun.deðiþ(satýrNo, yeniDeðer);
		}
		return sutun;
	}

	private void satýraEriþimiKontrolEt(int satýrNo) throws Exception {
		if(satýrNo < 0 || satýrNo >  satýrSay() -1  ){
			throw new Exception("satýrNo uyumsuz girilen satýrNo = " + satýrNo + " toplam satýr = " + satýrSay() +"\n" );
		}
	}
	public void deðiþ(int satýrNo,int sutunNo,Boolean yeniDeðer) throws Exception{
		//sutunaEriþimiKontrolEt(sutunNo);
		satýraEriþimiKontrolEt(satýrNo);
		matris[satýrNo].deðiþ(sutunNo, yeniDeðer);
	}
	
	public void satýrýDeðiþ(int satýr1, int satýr2) throws Exception{
		int sutunNo;
		for(sutunNo=0;sutunNo<sutunSay();sutunNo++){
			if(eriþ(satýr1,sutunNo) ^ eriþ(satýr2, sutunNo)){ // ikisi ayný ise false  döner
				satýrdanElemanDeðiþtir(satýr1, satýr2, sutunNo);
			}
		}
	}
    private void satýrdanElemanDeðiþtir(int satýr1, int satýr2, int sutunNo) throws Exception {
		Boolean satýr2ninElemaný = eriþ(satýr2,sutunNo);
		Boolean satýr1inElemaný = eriþ(satýr1,sutunNo);
		deðiþ(satýr2,sutunNo,satýr1inElemaný);
		deðiþ(satýr1,sutunNo,satýr2ninElemaný);
	}
    public void sutunuDeðiþ(int sutun1,int sutun2) throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
    		if(eriþ(satýrNo, sutun1) ^ eriþ(satýrNo,sutun2)){ // ikisi ayný ise false döner
    			sutunuDeðiþ(sutun1, sutun2, satýrNo);
    		}
    		
    	}
    }
    private void sutunuDeðiþ(int sutun1, int sutun2, int satýrNo)throws Exception {
		Boolean sutun1inElemaný = eriþ(satýrNo,sutun1);
		Boolean sutun2ninElemaný = eriþ(satýrNo,sutun2);
		deðiþ(satýrNo,sutun1,sutun2ninElemaný);
		deðiþ(satýrNo, sutun2, sutun1inElemaný);
	}
    
    
    /**
     * 2li sistemle oluþturulmuþ satýlarý en büyüðü baþta olacak þekilde sýralar
     * @throws Exception
     */
    public void satýrlarýSýrala() throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
    		enBüyükSatýrýBaþaAl(satýrNo);
    	}
    }
    private void enBüyükSatýrýBaþaAl(int satýrNo) throws Exception {
		int satýrNo2;
		int Enbüyük = satýrNo; // ilk baþta bunu kabul edelim
		for(satýrNo2=satýrNo+1;satýrNo2<satýrSay();satýrNo2++){
			if(matris[Enbüyük].büyükMü(matris[satýrNo2]) == 1) {// satýrNo2 daha büyük
				Enbüyük = satýrNo2;
			}
		}
		satýrýDeðiþ(satýrNo, Enbüyük);
	}

    public void sutunlarýSýrala() throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay();sutunNo++){
    		enBüyükSutunuBaþaAl(sutunNo);
    	}
    }
    private void enBüyükSutunuBaþaAl(int sutunNo) throws Exception {
		int sutunNo2;
		int Enbüyük = sutunNo; // ilk baþta bunu kabul edelim
		for(sutunNo2=sutunNo+1;sutunNo2<sutunSay();sutunNo2++){
			if(ikiSutunuKarþýlaþtýr(sutunNo2, Enbüyük) == 1) {// sutunNo2 daha büyük
				Enbüyük = sutunNo2;
			}
		}
		sutunuDeðiþ(sutunNo, Enbüyük);
	}
    
    public Boolean satýrlarSýralýMý() throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrSay()-1;satýrNo++){
    		if(matris[satýrNo].büyükMü(matris[satýrNo+1]) == 1){
    			return false;
    		}
    	}
    	return true;
    }

    public Boolean sutunlarSýralýMý() throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay()-1;sutunNo++){
    		if(ikiSutunuKarþýlaþtýr(sutunNo, sutunNo+1) == -1){
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * sutun1 büyükse 1 döner
     * sutun2 büyükse -1 döner 
     * ikisi eþit ise 0 döner
     * @param sutun1
     * @param sutun2
     * @return
     * @throws Exception
     */
    public int ikiSutunuKarþýlaþtýr(int sutun1,int sutun2) throws Exception{
    	int satýrNo;
		for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
			if(eriþ(satýrNo,sutun2) == false && eriþ(satýrNo,sutun1) == true) { // sutun1 daha büyük 
				return 1;
			}
			else if(eriþ(satýrNo,sutun2) == true && eriþ(satýrNo,sutun1) == false){
				return -1;
			}
		}
		return 0;
    }
    
    public int trueSayýsý() throws Exception{
    	int trueSayýsý = 0;
    	int satýr,sutun;
    	for(satýr=0;satýr<satýrSay();satýr++){
    		for(sutun=0;sutun<sutunSay();sutun++){
    			if(eriþ(satýr,sutun) == true){
    				trueSayýsý++;
    			}
    		}
    	}
    	return trueSayýsý;
    }
    public boolean eksikMatriseGerekVarMý() throws Exception{
    	int satýr = satýrSay();
    	int sutun = sutunSay();
    	int dolu = trueSayýsý();
    	if( dolu < (satýr*(sutun -1) - 1) / 2 ){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void sutunaYerleþ(int sutunNo,BoolVektör yeniSutun) throws Exception{
    	int satýrNo;
    	for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
    		boolean yeniDeðer = yeniSutun.eriþ(satýrNo);
    		deðiþ(satýrNo, sutunNo, yeniDeðer);
    	}
    }
    public void satýraYerleþ(int satýrNo,BoolVektör yeniSatýr) throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay();sutunNo++){
    		boolean yeniDeðer = yeniSatýr.eriþ(sutunNo);
    		deðiþ(satýrNo, sutunNo, yeniDeðer);
    	}
    }
    
    public String yaz() throws Exception{
    	String yazý = "";
    	int satýrNo ;
    	for(satýrNo=0;satýrNo<satýrSay();satýrNo++){
    		yazý += matris[satýrNo].yaz() + "\n"; 
    	}
    	return yazý;
    }
   
    public String baþlýklýYaz(String [] satýrlar, String [] sutunlar) throws Exception{
    	int satýrNo;
    	String yazý = "";
        yazý += "\t" + sutunlarýYaz(sutunlar) + "\n";
    	for(satýrNo = 0;satýrNo < satýrSay();satýrNo++){
    		yazý += satýrlar[satýrNo] + "\t" + matris[satýrNo].yaz() + "\n";
    	}
    	return yazý;
    }
    private String sutunlarýYaz(String [] sutunlar){
    int sutunNo;
    String yazý = "";	
     for(sutunNo = 0;sutunNo < sutunlar.length;sutunNo++){
    	yazý += sutunlar[sutunNo] + "\t";	
     }
     return yazý;
    }
}
