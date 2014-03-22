package S�k��t�r�lm��VeriYap�lar�;

import java.util.ArrayList;

import veriYap�lar�.BoolVekt�r;

public class BoolEksikMatrisA�a�l� {

	public IntAramaA�ac�2 [] dizi; // dolu elemanlar�n sutunlar�n� tutar
	public int sutunSay�s�;
	public int doluElemanSay�s�=0;
	public BoolEksikMatrisA�a�l�(int sat�rSay�s�){
		dizi = new IntAramaA�ac�2 [sat�rSay�s�];
		int elemanNo;
		for(elemanNo=0;elemanNo<sat�rSay�s�;elemanNo++){
			dizi[elemanNo] = new IntAramaA�ac�2();
		}
	}
	public BoolEksikMatrisA�a�l�(String girdi, char ay�r�c�) throws Exception {
	     String ay�r = "" + ay�r�c�;
		 String [] sat�rlar = girdi.split("\\n");
		 sutunSay�s� = sat�rlar.length;
		 dizi = new IntAramaA�ac�2 [sat�rlar.length];
		 int sat�rNo;
		 for(sat�rNo=0;sat�rNo<sat�rlar.length;sat�rNo++){
			 sat�rOku(sat�rNo, sat�rlar[sat�rNo], ay�r);
		 }
		 // son sat�r okunmad�
	}
	public BoolEksikMatrisA�a�l�(String girdi, String ay�r�c�,int ba�lamaSutunu,int ba�lamaSat�r�) throws Exception {
	     String ay�r = "" + ay�r�c�;
		 String [] sat�rlar = girdi.split("\\n");
		 sutunSay�s� = sat�rlar.length;
		 dizi = new IntAramaA�ac�2 [sat�rlar.length- ba�lamaSat�r�];
		 int sat�rNo;
		 for(sat�rNo=ba�lamaSat�r�;sat�rNo<sat�rlar.length;sat�rNo++){
			 sat�rOku(sat�rNo-ba�lamaSat�r�, sat�rlar[sat�rNo], ay�r,ba�lamaSutunu);
		 }
	}
	// �u an listeden diziye aktarma var belki de�i�ebilir
    public void sat�rOku(int sat�rNo,String sat�r,String ay�r�c�) throws Exception{
    	String [] b�l�k = sat�r.split(ay�r�c�);
    	ArrayList<Integer> sutunlar = new ArrayList<Integer>();
    	int elemanNo;
    	for(elemanNo=0;elemanNo<b�l�k.length;elemanNo++){
    		elemanOku(b�l�k, sutunlar, elemanNo);
    		doluElemanSay�s�++;
    	}
    	int [] sutunlarDizi = new int [sutunlar.size()];
    	diziyiAktar(sutunlar, sutunlarDizi);
    	dizi[sat�rNo] = new IntAramaA�ac�2(sutunlarDizi,true);
    }
	public void elemanOku(String[] b�l�k, ArrayList<Integer> sutunlar,int elemanNo) throws Exception {
		if(b�l�k[elemanNo].compareTo("1") == 0 ){
			sutunlar.add(elemanNo);
		}
		else if( b�l�k[elemanNo].compareTo("0") != 0 && b�l�k[elemanNo].compareTo("") != 0 ){
			throw new Exception("ya s�f�r da ya bir girmen gerekli ama girilen de�er\n" + b�l�k[elemanNo] + "\n");
		}
	}
    public void diziyiAktar(ArrayList<Integer> dizi1,int [] dizi2){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<dizi2.length;elemanNo++){
    		dizi2 [elemanNo] = dizi1.get(elemanNo);
    	}
    }
    
    public void sat�rOku(int sat�rNo,String sat�r,String ay�r�c�,int ba�lamaYeri) throws Exception{
    	String [] b�l�k = sat�r.split(ay�r�c�);
    	ArrayList<Integer> sutunlar = new ArrayList<Integer>();
    	int elemanNo;
    	for(elemanNo=ba�lamaYeri;elemanNo<b�l�k.length;elemanNo++){
    		elemanOku(b�l�k, sutunlar, elemanNo);
    		doluElemanSay�s�++;
    	}
    	int [] sutunlarDizi = new int [sutunlar.size()];
    	diziyiAktar(sutunlar, sutunlarDizi);
    	dizi[sat�rNo] = new IntAramaA�ac�2(sutunlarDizi,true);
    }
    public void sat�rOku(int sat�rNo,String [] sat�r,int ba�lamaYeri) throws Exception{
    	ArrayList<Integer> sutunlar = new ArrayList<Integer>();
    	int elemanNo;
    	for(elemanNo=ba�lamaYeri;elemanNo<sat�r.length;elemanNo++){
    		elemanOku(sat�r, sutunlar, elemanNo,ba�lamaYeri);
    		doluElemanSay�s�++;
    	}
    	int [] sutunlarDizi = new int [sutunlar.size()];
    	diziyiAktar(sutunlar, sutunlarDizi);
    	dizi[sat�rNo] = new IntAramaA�ac�2(sutunlarDizi,true);
    }
    public void elemanOku(String[] b�l�k, ArrayList<Integer> sutunlar,int elemanNo,int ba�lamaYeri) throws Exception {
		if(b�l�k[elemanNo].compareTo("1") == 0 ){
			sutunlar.add(elemanNo-ba�lamaYeri);
		}
		else if( b�l�k[elemanNo].compareTo("0") != 0 && b�l�k[elemanNo].compareTo("") != 0 ){
			throw new Exception("ya s�f�r da ya bir girmen gerekli ama girilen de�er\n" + b�l�k[elemanNo] + "\n");
		}
	}
    public boolean eri�(int sat�r,int sutun){
    	return dizi[sat�r].ara(sutun);
    }
    
    /** <br>enk�saAral�k [0] = enk���karal���n ba�� 
     * <br>enk�saAral�k [1] = enk���karal���n sonu
     * <br> e�er aral�klar ayr�k ise bu i�levi kullanmaya gerek yok ��nk� benzerlik s�f�r ��kacakt�r
     * @return
     */
    private int [] enK�saAral�k(int sat�r1 ,int sat�r2){
    	int sutun1ba� = dizi[sat�r1].enK���kDe�er;
    	int sutun1son = dizi[sat�r1].enB�y�kDe�er;
    	int sutun2ba� = dizi[sat�r2].enK���kDe�er;
    	int sutun2son = dizi[sat�r2].enB�y�kDe�er;
        int [] enK�saAral�k = new int [2];
    	if(sutun1ba� < sutun2ba� && sutun1son > sutun2son){ // ikinci  aral�k birincinin i�inde
             enK�saAral�k [0] = sutun2ba�;
             enK�saAral�k [1] = sutun2son;
    	}
    	else if(sutun1ba� > sutun2ba� && sutun1son < sutun2son) {// birinci aral�k ikincinin i�inde
    		enK�saAral�k [0] = sutun1ba�;
            enK�saAral�k [1] = sutun1son;
    	}
    	else if(sutun1ba� > sutun2son|| sutun1son < sutun2ba�){ // ayr�k olma durumu
    		enK�saAral�k [0] = -1;
            enK�saAral�k [1] = -1;
    	}
    	else { // kesi�me durumu
    		if(sutun1ba� < sutun2ba�){ // sat�r1 geride
    			enK�saAral�k [0] = sutun2ba�;
    			enK�saAral�k [1] = sutun1son;
    		}
    		else { // sat�r1 ileride
    			enK�saAral�k [0] = sutun1ba�;
    			enK�saAral�k [1] = sutun2son;
    		}
    	}
    	return enK�saAral�k;
    }	    
    private ArrayList<Integer> aral�ktakiSutunlar�Bul(int sat�r ,int altDe�er, int �stDe�er){
    	return dizi [ sat�r].aral�kBul(altDe�er, �stDe�er);
    }
    
    public int benzerlikBul(int sat�r1,int sat�r2){
    	int [] enK�saAral�k = enK�saAral�k(sat�r1, sat�r2);
    	return sutunlaraBakarakTara(sat�r1, sat�r2, enK�saAral�k);
    }
	private int sutunlaraBakarakTara(int sat�r1, int sat�r2, int[] enK�saAral�k) {
		int benzerlik = 0;
		ArrayList<Integer> sutunlar1 = dizi[sat�r1].aral�kBul(enK�saAral�k[0]-1, enK�saAral�k[1]+1); // ��nk� aral�kBul s�n�r de�erleri i�ermez 
    	ArrayList<Integer> sutunlar2 = dizi[sat�r2].aral�kBul(enK�saAral�k[0]-1, enK�saAral�k[1]+1); // ayr�ca <= ve ya >= i�lemi daha uzun s�rer
    	if(sutunlar1.size() > sutunlar2.size()){
    		benzerlik += benzerlikTara(sat�r1, sutunlar2);
    	}
    	else {
    		benzerlik += benzerlikTara(sat�r2, sutunlar1);
    	}
		return benzerlik;
	}
	private int benzerlikTara(int sat�r1,ArrayList<Integer> sutunlar2) {
		int elemanNo;
		int benzerlik = 0;
		for(elemanNo=0;elemanNo<sutunlar2.size();elemanNo++){
			int sutun = sutunlar2.get(elemanNo); // bununn zaten true oldu�unu biliyoruz burada eleman var ama �nemli olan kar��da var m� ?
			if(eri�(sat�r1,sutun)){ // demek ki orada eleman var
				benzerlik++;
			}
		}
		return benzerlik;
	}

    public int sat�rSay(){
    	return dizi.length;
    }
    public int sutunSay(){
    	return sutunSay�s�;
    }

    public BoolVekt�r sutun�ek(int sutunNo){
    	BoolVekt�r dizi = new BoolVekt�r(sat�rSay());
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
    		dizi.dizi[sat�rNo] = eri�(sat�rNo,sutunNo);
    	}
    	return dizi;
    }
    public BoolVekt�r sat�r�ek(int sat�rNo){
    	BoolVekt�r dizi = new BoolVekt�r(sutunSay�s�);
    	int sutunNo;
    	for(sutunNo=0;sutunNo<sutunSay�s�;sutunNo++){
    		dizi.dizi[sutunNo] = eri�(sat�rNo,sutunNo);
    	}
    	return dizi;
    }
    public String yaz(){
    	String yaz� = "";
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
    	   yaz� += dizi[sat�rNo].t�mDe�erleriYaz() + "\n";	
    	}
    	return yaz�;
    }
}
