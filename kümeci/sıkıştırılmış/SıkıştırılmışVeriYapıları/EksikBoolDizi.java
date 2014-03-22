package SıkıştırılmışVeriYapıları;


import veriYapıları.BoolDizi;
import veriYapıları.BoolVektör;



public class EksikBoolDizi {
    public int doluElemanSayısı = 0;
	public EksikBoolVektör [] dizi; // her satırın dolu elemanlarının olduğu sutun no ları tutar
	public int sutunSayısı;
	
	public int satırSay(){
		return dizi.length;
	}
	public int sutunSay(){
    	return sutunSayısı;
    }
	/**
	 * direk diziyi aktarma
	 * @param girdi
	 * @throws Exception 
	 */
	public EksikBoolDizi( BoolDizi girdi) throws Exception{
		dizi = new EksikBoolVektör [girdi.satırSay()];
		for(int satırNo = 0;satırNo<girdi.satırSay();satırNo++){
			BoolVektör satır = girdi.satır(satırNo);
			EksikBoolVektör yeniSatır = new EksikBoolVektör(satır);
			dizi[satırNo] = yeniSatır;
		}
	}
	
    public EksikBoolDizi(String girdi, char ayırıcı) throws Exception {
		 String [] satırlar = girdi.split("\\n");
		 sutunSayısı = satırlar.length;
		 dizi = new EksikBoolVektör [satırlar.length];
		 int satırNo;
		 for(satırNo=0;satırNo<satırlar.length;satırNo++){
			 dizi [satırNo] = new EksikBoolVektör(girdi, ayırıcı);
		 }   
	}
    
    /**
     * BoolDizi gibi yazdırmak için erişimdir
     * @param satırNo
     * @param sutunNo
     * @return
     */
    public boolean eriş(int satırNo,int sutunNo){
    	int elemanNo;
    	EksikBoolVektör tarancak = dizi[satırNo];
    	for(elemanNo=0;elemanNo<tarancak.boy();elemanNo++){
    		if(tarancak.eriş(elemanNo) == sutunNo){
    			return true;
    		}
    	}
    	return false;
    }
    
    public EksikBoolVektör satır(int satırNo){
    	return dizi[satırNo];
    }
    
    private String durumAnaliziYap(){
    	String yazı = "";
    	yazı += "dolu eleman sayısı : " + doluElemanSayısı + "\n";
    	yazı += "satır sayısı :" + satırSay() + "\n";
    	yazı += "sutun sayısı : " + sutunSayısı + "\n";
     	if( doluElemanSayısı < (satırSay()*(sutunSayısı-1))/2  ){
    		yazı += "eksik matris kullanmak daha iyi";
    	}
     	else {
     		yazı += "eksik matris kullanmak faydalı değil";
     	}
     	return yazı;
    }
	private String diziyiYazdır() throws Exception {
		String yazı = "";
		int satır,sutun;
		for(satır=0;satır<satırSay();satır++){
			for(sutun=0;sutun<sutunSayısı;sutun++){
				if(eriş(satır, sutun)){
					yazı += "1\t";
				}
				else {
				    yazı += "0\t";	
				}
			}
			yazı += "\n";
		}
		return yazı;
	}
	private String içDizileriYazdır() {
		String yazı = "";
		yazı += "dolu elemanların sutunları : " ;
		int satırNo,sutunNo;
		for(satırNo=0;satırNo<dizi.length;satırNo++){
			for(sutunNo=0;sutunNo<dizi[satırNo].boy();sutunNo++){
				yazı += dizi[satırNo].eriş(sutunNo)  + "\t";
			}
			yazı += "\n";
		}
		return yazı;
	}
	public String yaz() throws Exception{
		String yazı = "";
		yazı += diziyiYazdır();
		yazı += "\n\n";
		yazı += içDizileriYazdır() + "\n\n";
		yazı += durumAnaliziYap();
		return yazı;
	}
	
	/**
	 * 2 satırın kesişimininin alt ve üst sınırlarını bulur
	 * <br>eğer kesişim yoksa (-1,-1) döner
	 * @param satır1
	 * @param satır2
	 * @return (alt sınır,üst sınır)
	 */
	public int [] kesişim (int satır1,int satır2){
		int [] kesişme = new int [2];
		kesişme [0] = -1;
		kesişme [1] = -1;
		EksikBoolVektör satırBir = dizi[satır1];
		EksikBoolVektör satırİki = dizi[satır2];
		if(satırBir.boy() != 0 && satırİki.boy() != 0){ // diziler boş değil
			boşOlmamaDurumu(kesişme, satırBir, satırİki);
		}
		return kesişme;
	}
	private void boşOlmamaDurumu(int[] kesişme, EksikBoolVektör satırBir,EksikBoolVektör satırİki) {
		boolean birİkininİlerisindeDeğil = satırBir.eriş(0) <= satırİki.son() ; 
		boolean ikiBirinİlerisindeDeğil = satırİki.eriş(0) <= satırBir.son() ;
		if(ikiBirinİlerisindeDeğil && birİkininİlerisindeDeğil){ // kesişme var 
			kesişimiBul(kesişme, satırBir, satırİki);
		}
	}
	private void kesişimiBul(int[] kesişme, EksikBoolVektör satırBir,EksikBoolVektör satırİki) {
		if(satırBir.eriş(0) >= satırİki.eriş(0)){
			kesişme [0] = satırBir.eriş(0);
			kesişme [1] = satırİki.son(); 
		}
		else {
			kesişme [0] = satırİki.eriş(0);
			kesişme [1] = satırBir.son(); 
		}
	}
	
	
}
