package S�k��t�r�lm��VeriYap�lar�;

import java.util.ArrayList;

import veriYap�lar�.BoolVekt�r;

/** Bool dizileri daha k���k tutmak tarama aral���n� azaltmak i�in vard�r. EksikBoolDizinin bir sat�r�d�r
 * @author Furkan
 */
public class EksikBoolVekt�r {

	private int [] dizi;
	private int boy =0;
	/**
	 * diziden diziye aktarma yapar
	 * @param girdi
	 * @throws Exception 
	 */
	public EksikBoolVekt�r (BoolVekt�r girdi) throws Exception{
		verininBoyunuBul(girdi);
		dizi = new int [boy];
		veriyiAktar(girdi);
	}
	public EksikBoolVekt�r (int boy){
		this.boy = boy;
		dizi = new int [boy];
	}
	private void veriyiAktar(BoolVekt�r girdi) throws Exception {
		int yer = 0;
		for(int elemanNo =0;elemanNo<girdi.boy();elemanNo++){
			if(girdi.eri�(elemanNo)){
				dizi [yer] = elemanNo;
				yer ++;
			}
		}
	}
	private void verininBoyunuBul(BoolVekt�r girdi) throws Exception {
		for(int elemanNo =0;elemanNo<girdi.boy();elemanNo++){
			if(girdi.eri�(elemanNo)){
				boy ++;
			}
		}
	}
	public EksikBoolVekt�r(String girdi,String ay�r�c�) throws Exception {
		String [] b�l�k = girdi.split(ay�r�c�);
		ArrayList<Integer> yerler = new ArrayList<Integer>();
		int elemanNo;
		for(elemanNo=0;elemanNo<b�l�k.length;elemanNo++){
			elemanOku(b�l�k, yerler, elemanNo);
		}
		diziyiAktar(yerler, dizi);
	}
	private void elemanOku(String[] b�l�k, ArrayList<Integer> yerler,int elemanNo) throws Exception {
		if(b�l�k[elemanNo].compareTo("1") == 0){
			yerler.add(elemanNo);
			boy ++;
		}
		else if(b�l�k[elemanNo].compareTo("0") != 0 && b�l�k[elemanNo].compareTo("") != 0){
			throw new Exception("ya s�f�r da ya bir girmen gerekli ama girilen de�er\n" + b�l�k[elemanNo] + "\n");
		}
	}
	private void diziyiAktar(ArrayList<Integer> dizi1,int [] dizi2){
    	dizi2 = new int [dizi1.size()];
		int elemanNo;
    	for(elemanNo=0;elemanNo<dizi2.length;elemanNo++){
    		dizi2 [elemanNo] = dizi1.get(elemanNo);
    	}
    }
	public EksikBoolVekt�r(String girdi,char ay�r�c�) throws Exception {
		String ay�r = "" + ay�r�c�;
		String [] b�l�k = girdi.split(ay�r);
		ArrayList<Integer> yerler = new ArrayList<Integer>();
		int elemanNo;
		for(elemanNo=0;elemanNo<b�l�k.length;elemanNo++){
			elemanOku(b�l�k, yerler, elemanNo);
		}
		diziyiAktar(yerler, dizi);
	}
	public int eri�(int elemanNo){
		return dizi[elemanNo];
	}
	public int boy(){
		return boy;
	}
	/** son eleman� verir
	 * @return
	 */
	public int son(){
		return dizi[dizi.length-1];
	}
}
