package SýkýþtýrýlmýþVeriYapýlarý;

import java.util.ArrayList;

import veriYapýlarý.BoolVektör;

/** Bool dizileri daha küçük tutmak tarama aralýðýný azaltmak için vardýr. EksikBoolDizinin bir satýrýdýr
 * @author Furkan
 */
public class EksikBoolVektör {

	private int [] dizi;
	private int boy =0;
	/**
	 * diziden diziye aktarma yapar
	 * @param girdi
	 * @throws Exception 
	 */
	public EksikBoolVektör (BoolVektör girdi) throws Exception{
		verininBoyunuBul(girdi);
		dizi = new int [boy];
		veriyiAktar(girdi);
	}
	public EksikBoolVektör (int boy){
		this.boy = boy;
		dizi = new int [boy];
	}
	private void veriyiAktar(BoolVektör girdi) throws Exception {
		int yer = 0;
		for(int elemanNo =0;elemanNo<girdi.boy();elemanNo++){
			if(girdi.eriþ(elemanNo)){
				dizi [yer] = elemanNo;
				yer ++;
			}
		}
	}
	private void verininBoyunuBul(BoolVektör girdi) throws Exception {
		for(int elemanNo =0;elemanNo<girdi.boy();elemanNo++){
			if(girdi.eriþ(elemanNo)){
				boy ++;
			}
		}
	}
	public EksikBoolVektör(String girdi,String ayýrýcý) throws Exception {
		String [] bölük = girdi.split(ayýrýcý);
		ArrayList<Integer> yerler = new ArrayList<Integer>();
		int elemanNo;
		for(elemanNo=0;elemanNo<bölük.length;elemanNo++){
			elemanOku(bölük, yerler, elemanNo);
		}
		diziyiAktar(yerler, dizi);
	}
	private void elemanOku(String[] bölük, ArrayList<Integer> yerler,int elemanNo) throws Exception {
		if(bölük[elemanNo].compareTo("1") == 0){
			yerler.add(elemanNo);
			boy ++;
		}
		else if(bölük[elemanNo].compareTo("0") != 0 && bölük[elemanNo].compareTo("") != 0){
			throw new Exception("ya sýfýr da ya bir girmen gerekli ama girilen deðer\n" + bölük[elemanNo] + "\n");
		}
	}
	private void diziyiAktar(ArrayList<Integer> dizi1,int [] dizi2){
    	dizi2 = new int [dizi1.size()];
		int elemanNo;
    	for(elemanNo=0;elemanNo<dizi2.length;elemanNo++){
    		dizi2 [elemanNo] = dizi1.get(elemanNo);
    	}
    }
	public EksikBoolVektör(String girdi,char ayýrýcý) throws Exception {
		String ayýr = "" + ayýrýcý;
		String [] bölük = girdi.split(ayýr);
		ArrayList<Integer> yerler = new ArrayList<Integer>();
		int elemanNo;
		for(elemanNo=0;elemanNo<bölük.length;elemanNo++){
			elemanOku(bölük, yerler, elemanNo);
		}
		diziyiAktar(yerler, dizi);
	}
	public int eriþ(int elemanNo){
		return dizi[elemanNo];
	}
	public int boy(){
		return boy;
	}
	/** son elemaný verir
	 * @return
	 */
	public int son(){
		return dizi[dizi.length-1];
	}
}
