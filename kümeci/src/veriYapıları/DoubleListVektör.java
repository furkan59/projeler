package veriYap�lar�;

import java.util.ArrayList;

public class DoubleListVekt�r {
    public ArrayList<Double> dizi = new ArrayList<Double>();
	public DoubleListVekt�r (){
		
	}
    public DoubleListVekt�r(String  virg�lleAyr�lm��) throws Exception {
		bo�MuKontrol(virg�lleAyr�lm��);
    	String [] b�l�nm��String = virg�lleAyr�lm��.split(",");
    	int elemanNo;
    	int dizininBoyu = b�l�nm��String.length;
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		double de�er = Double.parseDouble(b�l�nm��String[elemanNo]);
    		dizi.add(de�er); 
    	}
	}
	private void bo�MuKontrol(String virg�lleAyr�lm��) throws Exception {
		if(virg�lleAyr�lm��.length() == 0)
    	{
    		throw new Exception("girlen yaz� bo� ! ");
    	}
	}
	
    public void sil(int elemanNo){
		dizi.remove(elemanNo);
	}
	public void ekle(int elemanNo, double de�er){
		dizi.add(elemanNo, de�er);
	}
	public void ekle(double de�er){
		dizi.add(de�er);
	}
	public void de�i�(int elemanNo, double de�er){
		dizi.set(elemanNo, de�er);
	}
    public double eri�(int elemanNo){
    	return dizi.get(elemanNo);
    }
	
	public int boy(){
		return dizi.size();
	}
	
	public double enB�y�k (){
		int elemanNo;
		double enB�y�k = eri�(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDe�er = eri�(elemanNo);
			if(yeniDe�er> enB�y�k){
				enB�y�k = yeniDe�er;
			}
		}
		return enB�y�k;
	}
	public double enK���k(){
		int elemanNo;
		double enK���k = eri�(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDe�er = eri�(elemanNo);
			if(yeniDe�er < enK���k){
				enK���k = yeniDe�er;
			}
		}
		return enK���k;
	}
	public int enB�y���nYeri(){
		int enB�y���nYeri = 0,elemanNo;
		double enB�y�k = eri�(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDe�er = eri�(elemanNo);
			if(yeniDe�er> enB�y�k){
				enB�y�k = yeniDe�er;
				enB�y���nYeri = elemanNo;
			}
		}
		return enB�y���nYeri;
	}
	public int enK�����nYeri (){
		int enK�����nYeri = 0,elemanNo;
		double enK���k = eri�(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDe�er = eri�(elemanNo);
			if(yeniDe�er < enK���k){
				enK���k = yeniDe�er;
				enK�����nYeri = elemanNo;
			}
		}
		return enK�����nYeri;
	}

	public Boolean b�y���VarM�(double aranacak){
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(eri�(elemanNo) > aranacak){
				return true;
			}
		}
		return false;
	}
	public Boolean varM�(double aranacak){
		Double ara = aranacak;
		return dizi.contains(ara);
	}
    
    public String  yaz(char ay�r�c�){
    	String yaz� = "";
    	String ay�ra� = "" + ay�r�c�;
    	int elemanNo;
    	int boy = boy();
    	for(elemanNo=0;elemanNo<boy-1;elemanNo++){
    		yaz� += eri�(elemanNo) + ay�ra�;
    	}
    	yaz� += eri�(elemanNo);
    	return yaz�;
    }

    public DoubleListVekt�r kopya (){
    	DoubleListVekt�r kopya = new DoubleListVekt�r();
    	int elemanNo,boy = boy();
    	for(elemanNo=0;elemanNo<boy;elemanNo++){
    		kopya.ekle(eri�(elemanNo));
    	}
    	return kopya;
    }

}
