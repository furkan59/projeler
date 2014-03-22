package veriYap�lar�;

import java.util.ArrayList;

public class BoolDizi {

	public BoolVekt�r [] matris;
	public BoolDizi(int sat�r,int sutun,Boolean ilkDe�er) {
		matris = new BoolVekt�r [sat�r];
		int sat�rNo;
		for(sat�rNo = 0;sat�rNo < sat�r ;sat�rNo++){
			matris[sat�rNo] = new BoolVekt�r(sutun, ilkDe�er);
		}
	}

	public BoolDizi (String yaz�l�,char ay�r�c�) throws Exception{
		String [] sat�rlar = yaz�l�.split("\\n");
		matris = new BoolVekt�r[sat�rlar.length];
		diziyiDoldur(ay�r�c�, sat�rlar);
	}
	/**
	 *  i�i bo� sat�rlar olu�turur sonradan doldurulmas� gerekir yar�m bir olu�turmad�r
	 * @param sat�rSay�s�
	 */
	public BoolDizi (int sat�rSay�s�){
		matris = new BoolVekt�r [sat�rSay�s�];
	}
    private void diziyiDoldur(char ay�r�c�, String[] sat�rlar) throws Exception {
		if(sat�rlar[0].length() < 1){ // ilk ba�ta bo� varm��
			ba�tBo�ElemanVarsa(ay�r�c�, sat�rlar);
		}
		else {
			ba�taBo�ElemanYoksa(ay�r�c�, sat�rlar);
		}
	}
    private void ba�taBo�ElemanYoksa(char ay�r�c�, String[] sat�rlar) throws Exception {
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rlar.length;sat�rNo++){
			matris[sat�rNo] = new BoolVekt�r(sat�rlar[sat�rNo], ay�r�c�);
		}
	}
    private void ba�tBo�ElemanVarsa(char ay�r�c�, String[] sat�rlar) throws Exception {
		int sat�rNo;
		for(sat�rNo=1;sat�rNo<sat�rlar.length;sat�rNo++){
			matris[sat�rNo-1] = new BoolVekt�r(sat�rlar[sat�rNo], ay�r�c�);
		}
	}
	
	public int sat�rSay(){
		return matris.length;
	}
	public int sutunSay(){
		return matris[0].boy();
	}
	public Boolean eri�(int sat�rNo,int sutunNo) throws Exception{
		sat�raEri�imiKontrolEt(sat�rNo);
		return matris[sat�rNo].eri�(sutunNo);
	}

	public BoolVekt�r sat�r(int sat�rNo){
		return matris[sat�rNo];
	}
	public BoolVekt�r sutun(int sutunNo) throws Exception{
		BoolVekt�r sutun = new BoolVekt�r(sat�rSay());
		for(int sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
			boolean yeniDe�er = eri�(sat�rNo, sutunNo);
			sutun.de�i�(sat�rNo, yeniDe�er);
		}
		return sutun;
	}

	private void sat�raEri�imiKontrolEt(int sat�rNo) throws Exception {
		if(sat�rNo < 0 || sat�rNo >  sat�rSay() -1  ){
			throw new Exception("sat�rNo uyumsuz girilen sat�rNo = " + sat�rNo + " toplam sat�r = " + sat�rSay() +"\n" );
		}
	}
	public void de�i�(int sat�rNo,int sutunNo,Boolean yeniDe�er) throws Exception{
		//sutunaEri�imiKontrolEt(sutunNo);
		sat�raEri�imiKontrolEt(sat�rNo);
		matris[sat�rNo].de�i�(sutunNo, yeniDe�er);
	}
	
	public void sat�r�De�i�(int sat�r1, int sat�r2) throws Exception{
		int sutunNo;
		for(sutunNo=0;sutunNo<sutunSay();sutunNo++){
			if(eri�(sat�r1,sutunNo) ^ eri�(sat�r2, sutunNo)){ // ikisi ayn� ise false  d�ner
				sat�rdanElemanDe�i�tir(sat�r1, sat�r2, sutunNo);
			}
		}
	}
    private void sat�rdanElemanDe�i�tir(int sat�r1, int sat�r2, int sutunNo) throws Exception {
		Boolean sat�r2ninEleman� = eri�(sat�r2,sutunNo);
		Boolean sat�r1inEleman� = eri�(sat�r1,sutunNo);
		de�i�(sat�r2,sutunNo,sat�r1inEleman�);
		de�i�(sat�r1,sutunNo,sat�r2ninEleman�);
	}
    public void sutunuDe�i�(int sutun1,int sutun2) throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
    		if(eri�(sat�rNo, sutun1) ^ eri�(sat�rNo,sutun2)){ // ikisi ayn� ise false d�ner
    			sutunuDe�i�(sutun1, sutun2, sat�rNo);
    		}
    		
    	}
    }
    private void sutunuDe�i�(int sutun1, int sutun2, int sat�rNo)throws Exception {
		Boolean sutun1inEleman� = eri�(sat�rNo,sutun1);
		Boolean sutun2ninEleman� = eri�(sat�rNo,sutun2);
		de�i�(sat�rNo,sutun1,sutun2ninEleman�);
		de�i�(sat�rNo, sutun2, sutun1inEleman�);
	}
    
    
    /**
     * 2li sistemle olu�turulmu� sat�lar� en b�y��� ba�ta olacak �ekilde s�ralar
     * @throws Exception
     */
    public void sat�rlar�S�rala() throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
    		enB�y�kSat�r�Ba�aAl(sat�rNo);
    	}
    }
    private void enB�y�kSat�r�Ba�aAl(int sat�rNo) throws Exception {
		int sat�rNo2;
		int Enb�y�k = sat�rNo; // ilk ba�ta bunu kabul edelim
		for(sat�rNo2=sat�rNo+1;sat�rNo2<sat�rSay();sat�rNo2++){
			if(matris[Enb�y�k].b�y�kM�(matris[sat�rNo2]) == 1) {// sat�rNo2 daha b�y�k
				Enb�y�k = sat�rNo2;
			}
		}
		sat�r�De�i�(sat�rNo, Enb�y�k);
	}

    public void sutunlar�S�rala() throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay();sutunNo++){
    		enB�y�kSutunuBa�aAl(sutunNo);
    	}
    }
    private void enB�y�kSutunuBa�aAl(int sutunNo) throws Exception {
		int sutunNo2;
		int Enb�y�k = sutunNo; // ilk ba�ta bunu kabul edelim
		for(sutunNo2=sutunNo+1;sutunNo2<sutunSay();sutunNo2++){
			if(ikiSutunuKar��la�t�r(sutunNo2, Enb�y�k) == 1) {// sutunNo2 daha b�y�k
				Enb�y�k = sutunNo2;
			}
		}
		sutunuDe�i�(sutunNo, Enb�y�k);
	}
    
    public Boolean sat�rlarS�ral�M�() throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rSay()-1;sat�rNo++){
    		if(matris[sat�rNo].b�y�kM�(matris[sat�rNo+1]) == 1){
    			return false;
    		}
    	}
    	return true;
    }

    public Boolean sutunlarS�ral�M�() throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay()-1;sutunNo++){
    		if(ikiSutunuKar��la�t�r(sutunNo, sutunNo+1) == -1){
    			return false;
    		}
    	}
    	return true;
    }
    /**
     * sutun1 b�y�kse 1 d�ner
     * sutun2 b�y�kse -1 d�ner 
     * ikisi e�it ise 0 d�ner
     * @param sutun1
     * @param sutun2
     * @return
     * @throws Exception
     */
    public int ikiSutunuKar��la�t�r(int sutun1,int sutun2) throws Exception{
    	int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
			if(eri�(sat�rNo,sutun2) == false && eri�(sat�rNo,sutun1) == true) { // sutun1 daha b�y�k 
				return 1;
			}
			else if(eri�(sat�rNo,sutun2) == true && eri�(sat�rNo,sutun1) == false){
				return -1;
			}
		}
		return 0;
    }
    
    public int trueSay�s�() throws Exception{
    	int trueSay�s� = 0;
    	int sat�r,sutun;
    	for(sat�r=0;sat�r<sat�rSay();sat�r++){
    		for(sutun=0;sutun<sutunSay();sutun++){
    			if(eri�(sat�r,sutun) == true){
    				trueSay�s�++;
    			}
    		}
    	}
    	return trueSay�s�;
    }
    public boolean eksikMatriseGerekVarM�() throws Exception{
    	int sat�r = sat�rSay();
    	int sutun = sutunSay();
    	int dolu = trueSay�s�();
    	if( dolu < (sat�r*(sutun -1) - 1) / 2 ){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    public void sutunaYerle�(int sutunNo,BoolVekt�r yeniSutun) throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
    		boolean yeniDe�er = yeniSutun.eri�(sat�rNo);
    		de�i�(sat�rNo, sutunNo, yeniDe�er);
    	}
    }
    public void sat�raYerle�(int sat�rNo,BoolVekt�r yeniSat�r) throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay();sutunNo++){
    		boolean yeniDe�er = yeniSat�r.eri�(sutunNo);
    		de�i�(sat�rNo, sutunNo, yeniDe�er);
    	}
    }
    
    public String yaz() throws Exception{
    	String yaz� = "";
    	int sat�rNo ;
    	for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
    		yaz� += matris[sat�rNo].yaz() + "\n"; 
    	}
    	return yaz�;
    }
   
    public String ba�l�kl�Yaz(String [] sat�rlar, String [] sutunlar) throws Exception{
    	int sat�rNo;
    	String yaz� = "";
        yaz� += "\t" + sutunlar�Yaz(sutunlar) + "\n";
    	for(sat�rNo = 0;sat�rNo < sat�rSay();sat�rNo++){
    		yaz� += sat�rlar[sat�rNo] + "\t" + matris[sat�rNo].yaz() + "\n";
    	}
    	return yaz�;
    }
    private String sutunlar�Yaz(String [] sutunlar){
    int sutunNo;
    String yaz� = "";	
     for(sutunNo = 0;sutunNo < sutunlar.length;sutunNo++){
    	yaz� += sutunlar[sutunNo] + "\t";	
     }
     return yaz�;
    }
}
