package kümeci;

import veriYapıları.BoolDizi;
/**
 * bu rassal koordinat seçmek içindir
 * <br> bir Booldizide koordinatların seçilip seçilmediği tutulur
 * <br> her satır ve sutundaki true sayısı tutulur 
 * <br> erişim için atlamalı gidilir ve truelar görmezden gelinir 
 * @author Furkan
 *
 */
public class rassalKoordinat {
 
	private BoolDizi işaret;
	private int [] satırdakiTrueSayısı;
	private int satırSayısı;
	public rassalKoordinat (int satır,int sutun){
		işaret = new BoolDizi(satır, sutun, false);
		satırdakiTrueSayısınıOluştur(satır);
		satırSayısı = işaret.satırSay();
	}
	private void satırdakiTrueSayısınıOluştur(int satır) {
		satırdakiTrueSayısı = new int [satır];
		for(int elemanNo=0;elemanNo<satır;elemanNo++){
			satırdakiTrueSayısı [elemanNo] = 0;
		}
	}


	/**
	 * erişince BoolDizi (satır,sutun) değişecek
	 * <br> satır sutundaki true sayısı artacak
	 * @param satır
	 * @param sutun
	 * @throws Exception 
	 */
    private void değiş(int  satır,int sutun) throws Exception{
    	if(işaret.eriş(satır, sutun)){
    		throw new Exception("true olan değer değiştirilmek isteniyor!!\nsatır = " + satır  + " sutun : " + sutun +"\n");
    	}
    	işaret.değiş(satır, sutun, true);
    	satırdakiTrueSayısı[satır] ++;
    	if(satırdakiTrueSayısı[satır] == işaret.sutunSay()){ // o satır artık görülmemeli
    		satırSayısı --;
    	}
    }
    private int [] eriş(int satırİlerle,int sutunİlerle) throws Exception{
    	int [] koordinat = new int [2]; // satır , sutun
    	koordinat [0] = -1;
    	koordinat [1] = -1;
    	satıraEriş(satırİlerle, koordinat); 
        sutunaEriş(sutunİlerle, koordinat);
    	değiş(koordinat [0] , koordinat [1]);
        return koordinat;
    }
	private void satıraEriş(int satırİlerle, int[] koordinat) {
		int satırFalseSayısı = 0;
		if(satırİlerle == 0 && satırdakiTrueSayısı[0] == 0){
			koordinat [0] = 0;
		}
		else {
			satırFalseSayısı = satırAradaİken(satırİlerle, koordinat,
					satırFalseSayısı);
		}
	}
	private int satırAradaİken(int satırİlerle, int[] koordinat,int satırFalseSayısı) {
		for(int satırNo = 0;satırNo < işaret.satırSay();satırNo++){
			if(satırdakiTrueSayısı[satırNo] != işaret.sutunSay() ){ // o satırı gör
				satırFalseSayısı ++;
			}
			if(satırFalseSayısı == satırİlerle){
				koordinat [0] = satırNo;
				break;
			}
		}
		return satırFalseSayısı;
	}
	private void sutunaEriş(int sutunİlerle, int[] koordinat) throws Exception {
		int sutunFalseSayısı = 0;
    	if(sutunİlerle == 0 && işaret.eriş(koordinat[0], 0)){
    		koordinat[1] = 0;
    	}
    	else{
    		sutunFalseSayısı = sutunAradaİken(sutunİlerle, koordinat,sutunFalseSayısı);
    	}
	}
	private int sutunAradaİken(int sutunİlerle, int[] koordinat,int sutunFalseSayısı) throws Exception {
		for(int sutunNo = 0;sutunNo < işaret.sutunSay();sutunNo++){
			if(işaret.eriş(koordinat [0],sutunNo )== false){ // o satırı gör
				sutunFalseSayısı ++;
			}
			if(sutunFalseSayısı == sutunİlerle){
				koordinat [1] = sutunNo;
				break;
			}
		}
		return sutunFalseSayısı;
	}
    
    public int [] rassalKoordinatVer() throws Exception{
    	double rassal1 = Math.random();
    	double rassal2 = Math.random();
    	int satırİlerle = (int) Math.ceil(rassal1*satırSayısı);
    	int satırSutun [] = new int [2];
    	satıraEriş(satırİlerle, satırSutun);
    	int satır = satırSutun[0];
    	int satırdakiFalseSayısı = işaret.sutunSay()-satırdakiTrueSayısı[satır];
    	int sutunİlerle = (int) Math.ceil(rassal2*satırdakiFalseSayısı);
    	sutunaEriş(sutunİlerle, satırSutun);
    	değiş(satırSutun[0], satırSutun[1]);
        return satırSutun;
    }

    public String konumlarıYaz(int satır,int sutun) throws Exception{
    	işaret = new BoolDizi(satır, sutun, false);
		satırdakiTrueSayısınıOluştur(satır);
		satırSayısı = işaret.satırSay();
		String yazı = "";
		for(int satırNo=0;satırNo<işaret.satırSay();satırNo++){
			for(int sutunNo = 0;sutunNo<işaret.sutunSay();sutunNo++){
				int [] konum = rassalKoordinatVer();
				yazı += konum [0] + "," +konum [1]  + "\n";
			}
		}
		yazı += "işaret matrisi \n" + işaret.yaz();
        return yazı;
    }

}
