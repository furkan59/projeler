package S�k��t�r�lm��VeriYap�lar�;


import veriYap�lar�.BoolDizi;
import veriYap�lar�.BoolVekt�r;



public class EksikBoolDizi {
    public int doluElemanSay�s� = 0;
	public EksikBoolVekt�r [] dizi; // her sat�r�n dolu elemanlar�n�n oldu�u sutun no lar� tutar
	public int sutunSay�s�;
	
	public int sat�rSay(){
		return dizi.length;
	}
	public int sutunSay(){
    	return sutunSay�s�;
    }
	/**
	 * direk diziyi aktarma
	 * @param girdi
	 * @throws Exception 
	 */
	public EksikBoolDizi( BoolDizi girdi) throws Exception{
		dizi = new EksikBoolVekt�r [girdi.sat�rSay()];
		for(int sat�rNo = 0;sat�rNo<girdi.sat�rSay();sat�rNo++){
			BoolVekt�r sat�r = girdi.sat�r(sat�rNo);
			EksikBoolVekt�r yeniSat�r = new EksikBoolVekt�r(sat�r);
			dizi[sat�rNo] = yeniSat�r;
		}
	}
	
    public EksikBoolDizi(String girdi, char ay�r�c�) throws Exception {
		 String [] sat�rlar = girdi.split("\\n");
		 sutunSay�s� = sat�rlar.length;
		 dizi = new EksikBoolVekt�r [sat�rlar.length];
		 int sat�rNo;
		 for(sat�rNo=0;sat�rNo<sat�rlar.length;sat�rNo++){
			 dizi [sat�rNo] = new EksikBoolVekt�r(girdi, ay�r�c�);
		 }   
	}
    
    /**
     * BoolDizi gibi yazd�rmak i�in eri�imdir
     * @param sat�rNo
     * @param sutunNo
     * @return
     */
    public boolean eri�(int sat�rNo,int sutunNo){
    	int elemanNo;
    	EksikBoolVekt�r tarancak = dizi[sat�rNo];
    	for(elemanNo=0;elemanNo<tarancak.boy();elemanNo++){
    		if(tarancak.eri�(elemanNo) == sutunNo){
    			return true;
    		}
    	}
    	return false;
    }
    
    public EksikBoolVekt�r sat�r(int sat�rNo){
    	return dizi[sat�rNo];
    }
    
    private String durumAnaliziYap(){
    	String yaz� = "";
    	yaz� += "dolu eleman say�s� : " + doluElemanSay�s� + "\n";
    	yaz� += "sat�r say�s� :" + sat�rSay() + "\n";
    	yaz� += "sutun say�s� : " + sutunSay�s� + "\n";
     	if( doluElemanSay�s� < (sat�rSay()*(sutunSay�s�-1))/2  ){
    		yaz� += "eksik matris kullanmak daha iyi";
    	}
     	else {
     		yaz� += "eksik matris kullanmak faydal� de�il";
     	}
     	return yaz�;
    }
	private String diziyiYazd�r() throws Exception {
		String yaz� = "";
		int sat�r,sutun;
		for(sat�r=0;sat�r<sat�rSay();sat�r++){
			for(sutun=0;sutun<sutunSay�s�;sutun++){
				if(eri�(sat�r, sutun)){
					yaz� += "1\t";
				}
				else {
				    yaz� += "0\t";	
				}
			}
			yaz� += "\n";
		}
		return yaz�;
	}
	private String i�DizileriYazd�r() {
		String yaz� = "";
		yaz� += "dolu elemanlar�n sutunlar� : " ;
		int sat�rNo,sutunNo;
		for(sat�rNo=0;sat�rNo<dizi.length;sat�rNo++){
			for(sutunNo=0;sutunNo<dizi[sat�rNo].boy();sutunNo++){
				yaz� += dizi[sat�rNo].eri�(sutunNo)  + "\t";
			}
			yaz� += "\n";
		}
		return yaz�;
	}
	public String yaz() throws Exception{
		String yaz� = "";
		yaz� += diziyiYazd�r();
		yaz� += "\n\n";
		yaz� += i�DizileriYazd�r() + "\n\n";
		yaz� += durumAnaliziYap();
		return yaz�;
	}
	
	/**
	 * 2 sat�r�n kesi�imininin alt ve �st s�n�rlar�n� bulur
	 * <br>e�er kesi�im yoksa (-1,-1) d�ner
	 * @param sat�r1
	 * @param sat�r2
	 * @return (alt s�n�r,�st s�n�r)
	 */
	public int [] kesi�im (int sat�r1,int sat�r2){
		int [] kesi�me = new int [2];
		kesi�me [0] = -1;
		kesi�me [1] = -1;
		EksikBoolVekt�r sat�rBir = dizi[sat�r1];
		EksikBoolVekt�r sat�r�ki = dizi[sat�r2];
		if(sat�rBir.boy() != 0 && sat�r�ki.boy() != 0){ // diziler bo� de�il
			bo�OlmamaDurumu(kesi�me, sat�rBir, sat�r�ki);
		}
		return kesi�me;
	}
	private void bo�OlmamaDurumu(int[] kesi�me, EksikBoolVekt�r sat�rBir,EksikBoolVekt�r sat�r�ki) {
		boolean bir�kinin�lerisindeDe�il = sat�rBir.eri�(0) <= sat�r�ki.son() ; 
		boolean ikiBirin�lerisindeDe�il = sat�r�ki.eri�(0) <= sat�rBir.son() ;
		if(ikiBirin�lerisindeDe�il && bir�kinin�lerisindeDe�il){ // kesi�me var 
			kesi�imiBul(kesi�me, sat�rBir, sat�r�ki);
		}
	}
	private void kesi�imiBul(int[] kesi�me, EksikBoolVekt�r sat�rBir,EksikBoolVekt�r sat�r�ki) {
		if(sat�rBir.eri�(0) >= sat�r�ki.eri�(0)){
			kesi�me [0] = sat�rBir.eri�(0);
			kesi�me [1] = sat�r�ki.son(); 
		}
		else {
			kesi�me [0] = sat�r�ki.eri�(0);
			kesi�me [1] = sat�rBir.son(); 
		}
	}
	
	
}
