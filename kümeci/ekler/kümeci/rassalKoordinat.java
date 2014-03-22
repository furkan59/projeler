package k�meci;

import veriYap�lar�.BoolDizi;
/**
 * bu rassal koordinat se�mek i�indir
 * <br> bir Booldizide koordinatlar�n se�ilip se�ilmedi�i tutulur
 * <br> her sat�r ve sutundaki true say�s� tutulur 
 * <br> eri�im i�in atlamal� gidilir ve truelar g�rmezden gelinir 
 * @author Furkan
 *
 */
public class rassalKoordinat {
 
	private BoolDizi i�aret;
	private int [] sat�rdakiTrueSay�s�;
	private int sat�rSay�s�;
	public rassalKoordinat (int sat�r,int sutun){
		i�aret = new BoolDizi(sat�r, sutun, false);
		sat�rdakiTrueSay�s�n�Olu�tur(sat�r);
		sat�rSay�s� = i�aret.sat�rSay();
	}
	private void sat�rdakiTrueSay�s�n�Olu�tur(int sat�r) {
		sat�rdakiTrueSay�s� = new int [sat�r];
		for(int elemanNo=0;elemanNo<sat�r;elemanNo++){
			sat�rdakiTrueSay�s� [elemanNo] = 0;
		}
	}


	/**
	 * eri�ince BoolDizi (sat�r,sutun) de�i�ecek
	 * <br> sat�r sutundaki true say�s� artacak
	 * @param sat�r
	 * @param sutun
	 * @throws Exception 
	 */
    private void de�i�(int  sat�r,int sutun) throws Exception{
    	if(i�aret.eri�(sat�r, sutun)){
    		throw new Exception("true olan de�er de�i�tirilmek isteniyor!!\nsat�r = " + sat�r  + " sutun : " + sutun +"\n");
    	}
    	i�aret.de�i�(sat�r, sutun, true);
    	sat�rdakiTrueSay�s�[sat�r] ++;
    	if(sat�rdakiTrueSay�s�[sat�r] == i�aret.sutunSay()){ // o sat�r art�k g�r�lmemeli
    		sat�rSay�s� --;
    	}
    }
    private int [] eri�(int sat�r�lerle,int sutun�lerle) throws Exception{
    	int [] koordinat = new int [2]; // sat�r , sutun
    	koordinat [0] = -1;
    	koordinat [1] = -1;
    	sat�raEri�(sat�r�lerle, koordinat); 
        sutunaEri�(sutun�lerle, koordinat);
    	de�i�(koordinat [0] , koordinat [1]);
        return koordinat;
    }
	private void sat�raEri�(int sat�r�lerle, int[] koordinat) {
		int sat�rFalseSay�s� = 0;
		if(sat�r�lerle == 0 && sat�rdakiTrueSay�s�[0] == 0){
			koordinat [0] = 0;
		}
		else {
			sat�rFalseSay�s� = sat�rArada�ken(sat�r�lerle, koordinat,
					sat�rFalseSay�s�);
		}
	}
	private int sat�rArada�ken(int sat�r�lerle, int[] koordinat,int sat�rFalseSay�s�) {
		for(int sat�rNo = 0;sat�rNo < i�aret.sat�rSay();sat�rNo++){
			if(sat�rdakiTrueSay�s�[sat�rNo] != i�aret.sutunSay() ){ // o sat�r� g�r
				sat�rFalseSay�s� ++;
			}
			if(sat�rFalseSay�s� == sat�r�lerle){
				koordinat [0] = sat�rNo;
				break;
			}
		}
		return sat�rFalseSay�s�;
	}
	private void sutunaEri�(int sutun�lerle, int[] koordinat) throws Exception {
		int sutunFalseSay�s� = 0;
    	if(sutun�lerle == 0 && i�aret.eri�(koordinat[0], 0)){
    		koordinat[1] = 0;
    	}
    	else{
    		sutunFalseSay�s� = sutunArada�ken(sutun�lerle, koordinat,sutunFalseSay�s�);
    	}
	}
	private int sutunArada�ken(int sutun�lerle, int[] koordinat,int sutunFalseSay�s�) throws Exception {
		for(int sutunNo = 0;sutunNo < i�aret.sutunSay();sutunNo++){
			if(i�aret.eri�(koordinat [0],sutunNo )== false){ // o sat�r� g�r
				sutunFalseSay�s� ++;
			}
			if(sutunFalseSay�s� == sutun�lerle){
				koordinat [1] = sutunNo;
				break;
			}
		}
		return sutunFalseSay�s�;
	}
    
    public int [] rassalKoordinatVer() throws Exception{
    	double rassal1 = Math.random();
    	double rassal2 = Math.random();
    	int sat�r�lerle = (int) Math.ceil(rassal1*sat�rSay�s�);
    	int sat�rSutun [] = new int [2];
    	sat�raEri�(sat�r�lerle, sat�rSutun);
    	int sat�r = sat�rSutun[0];
    	int sat�rdakiFalseSay�s� = i�aret.sutunSay()-sat�rdakiTrueSay�s�[sat�r];
    	int sutun�lerle = (int) Math.ceil(rassal2*sat�rdakiFalseSay�s�);
    	sutunaEri�(sutun�lerle, sat�rSutun);
    	de�i�(sat�rSutun[0], sat�rSutun[1]);
        return sat�rSutun;
    }

    public String konumlar�Yaz(int sat�r,int sutun) throws Exception{
    	i�aret = new BoolDizi(sat�r, sutun, false);
		sat�rdakiTrueSay�s�n�Olu�tur(sat�r);
		sat�rSay�s� = i�aret.sat�rSay();
		String yaz� = "";
		for(int sat�rNo=0;sat�rNo<i�aret.sat�rSay();sat�rNo++){
			for(int sutunNo = 0;sutunNo<i�aret.sutunSay();sutunNo++){
				int [] konum = rassalKoordinatVer();
				yaz� += konum [0] + "," +konum [1]  + "\n";
			}
		}
		yaz� += "i�aret matrisi \n" + i�aret.yaz();
        return yaz�;
    }

}
