package yap�salK�meleme;

import java.util.ArrayList;
import java.util.TreeMap;

import veriYap�lar�.BoolDizi;
import k�meci.K�meleme;

public class K�meBulmaA�a�l� extends K�meleme {

	private TreeMap<Integer, Integer> �izilenSat�rlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> �izilmeyenSat�rlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> �izilenSutunlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> �izilmeyenSutunlar = new TreeMap<Integer, Integer>();
	
	private int grupNo=0; // gruplanan sat�rlar i�in kullan�lacak 
    private long ba� = System.currentTimeMillis(),son;
	public K�meBulmaA�a�l�(String yaz�) throws Exception {
		super(yaz�);
	}
    
	private void sat�r�iz(int sat�r) throws Exception{
			�izilenSat�rlar.put(sat�r, sat�r);
			�izilmeyenSat�rlardan��kar(sat�r);
			makineGruplar�.get(grupNo).add(sat�r);
	}
    private void �izilmeyenSat�rlardan��kar(int sat�r) {
		�izilmeyenSat�rlar.remove(sat�r);
	}
	
    private void sutun�iz(int sutun) throws Exception{
    		�izilenSutunlar.put(sutun,sutun);
    		�izilmeyenSutunlardan��kar(sutun);
    		par�aGruplar�.get(grupNo).add(sutun);
	}
	private void �izilmeyenSutunlardan��kar(int sutun) {
		�izilmeyenSutunlar.remove(sutun);
	}
	
    private ArrayList<Integer> sat�rdaBirleriBul(int sat�r) throws Exception{
		ArrayList<Integer> birlerinSutunlar� = new ArrayList<>();
		int sutunNo;
		for(sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
			if(par�aMakine.eri�(sat�r, sutunNo)){
				birlerinSutunlar�.add(sutunNo);
			}
		}
		return birlerinSutunlar�;
	}
	private ArrayList<Integer> sutundaBirleriBul(int sutun) throws Exception{
		ArrayList<Integer> birlerinSat�rlar� = new ArrayList<>();
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
			if(par�aMakine.eri�(sat�rNo, sutun)){
				birlerinSat�rlar�.add(sat�rNo);
			}
		}
		return birlerinSat�rlar�;
	}

	
	private boolean sat�r�izilebilirMi(int sat�r) throws Exception{
		return �izilmeyenSat�rlar.containsKey(sat�r);
	}
	private boolean sutun�izilebilirMi(int sutun) throws Exception{
		return �izilmeyenSutunlar.containsKey(sutun);
	}

	/**
	 * o sat�rdan ba�lar o sat�r� �izer sonra da o sat�rdaki 1 ler bulur ve 1 lerin
	 * <br> oldu�u sutunlar� �izer (e�er sutunlar �izilebilirse)
	 * <br> sat�r�n �izilip �izilemeyece�inin ba�ka bir fonksyon taraf�ndan 
	 * <br> kontrol edilmesi gerekir
	 * @param sat�r
	 * @throws Exception 
	 */
    private boolean sat�rSutun�iz(int sat�r) throws Exception{
    	sat�r�iz(sat�r);
    	ArrayList<Integer> sutunlar = sat�rdaBirleriBul(sat�r);
    	return sutunSat�r�iz(sutunlar);
    }
    private boolean sat�rSutun�iz(ArrayList<Integer> sat�rlar ) throws Exception{
    	int elemanNo;
    	boolean devamM� = false;
    	for(elemanNo=0;elemanNo<sat�rlar.size();elemanNo++){
    		int sat�r = sat�rlar.get(elemanNo);
    		if(sat�r�izilebilirMi(sat�r)){
    			sat�rSutun�iz(sat�r);
    			devamM� = true;
    		}
    	}
    	return devamM�;
    }
    
    private boolean sutunSat�r�iz(int sutun) throws Exception{
    	sutun�iz(sutun);
    	ArrayList<Integer> sat�rlar = sutundaBirleriBul(sutun);
    	return sat�rSutun�iz(sat�rlar);
    }
    private boolean sutunSat�r�iz(ArrayList<Integer> sutunlar ) throws Exception{
    	int elemanNo;
    	boolean devamM� = false;
    	for(elemanNo=0;elemanNo<sutunlar.size();elemanNo++){
    		int sutun = sutunlar.get(elemanNo);
    		if(sutun�izilebilirMi(sutun)){
    			sutunSat�r�iz(sutun);
    			devamM� = true;
    		}
    	}
    	return devamM�;
    }

    private void �izilmeyenSutunlar�Olu�tur(){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<par�aMakine.sutunSay();elemanNo++){
    		�izilmeyenSutunlar.put(elemanNo,elemanNo);
    	}
    }
    private void �izilmeyenSat�rlar�Olu�tur(){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<par�aMakine.sat�rSay();elemanNo++){
    		�izilmeyenSat�rlar.put(elemanNo,elemanNo);
    	}
    }

    
    private void k�meBul() throws Exception{
    	// �nce hangi sat�rdan ba�licaz onu bulal�m
    	int ba�lamaSat�r� = �izilmeyenSat�rlar.firstKey();
    	gruplananlar�Geni�let();
    	sat�rSutun�iz(ba�lamaSat�r�);
    	grupNo ++;
    }

	private void gruplananlar�Geni�let() {
		ArrayList<Integer> yenigrupSat�r = new ArrayList<>();
    	ArrayList<Integer> yeniGrupSutun = new ArrayList<>();
    	par�aGruplar�.add(yeniGrupSutun);
    	makineGruplar�.add(yenigrupSat�r);
	}
    
    private boolean t�mMatris�izildiMi(){
    	if(�izilmeyenSat�rlar.size() == 0 && �izilmeyenSutunlar.size() == 0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public String ��z() throws Exception{
    	String ��z�m = "";
    	�izilmeyenleriOlu�tur();
    	��z�m += ��z�mD�ng�s�Yap();
    	��z�m += yeniPar�aMakineMatrisi();
    	son = System.currentTimeMillis();
    	��z�m += "��zme s�resi : " + (son-ba�) +" milisaniye\n";
    	return ��z�m;
    }

	private String yeniPar�aMakineMatrisi() throws Exception {
		String ��z�m = "";
		��z�m += "yeni par�a makine matrisi \n";
    	��z�m +=  yeniPar�aMakineMatrisiYaz2()+ "\n";
		return ��z�m;
	}
	private void �izilmeyenleriOlu�tur() {
		�izilmeyenSat�rlar�Olu�tur();
    	�izilmeyenSutunlar�Olu�tur();
	}
    private String ��z�mD�ng�s�Yap() throws Exception {
		String ��z�m = "";
		int a�amaNo;
    	for(a�amaNo=0;;a�amaNo++){
    		if(t�mMatris�izildiMi() ){
    			break;
    		}
    		else{
    			k�meBul();
        		��z�m += "a�amaNo : " + a�amaNo  + "\n\n-------------------------\n\n";
        		��z�m += yaz() + "\n\n";	
    		}
    	}
		return ��z�m;
	}

    public String yaz() throws Exception{
    	String yaz� ="";
    	yaz� += "�izilen sat�rlar : " + �izilenSat�rlar.toString() + "\n";
    	yaz� += "�izilen sutunlar : " + �izilenSutunlar.toString() + "\n";
    	yaz� += "�izilmeyen sat�rlar : "  + �izilmeyenSat�rlar.toString() + "\n";
    	yaz� += "�ilzmeyen sutunlar  : " + �izilmeyenSutunlar.toString()+ "\n";
    	yaz� += "gruplanan Sat�rlar : \n" + makineGruplar�n�Yaz() +"\n"; 
    	yaz� += "gruplanan Sutunlar  : \n" + par�aGruplar�n�Yaz() +"\n";
    	return yaz�;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
