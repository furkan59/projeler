package veriYapýlarý;

import java.util.ArrayList;

public class DoubleListVektör {
    public ArrayList<Double> dizi = new ArrayList<Double>();
	public DoubleListVektör (){
		
	}
    public DoubleListVektör(String  virgülleAyrýlmýþ) throws Exception {
		boþMuKontrol(virgülleAyrýlmýþ);
    	String [] bölünmüþString = virgülleAyrýlmýþ.split(",");
    	int elemanNo;
    	int dizininBoyu = bölünmüþString.length;
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		double deðer = Double.parseDouble(bölünmüþString[elemanNo]);
    		dizi.add(deðer); 
    	}
	}
	private void boþMuKontrol(String virgülleAyrýlmýþ) throws Exception {
		if(virgülleAyrýlmýþ.length() == 0)
    	{
    		throw new Exception("girlen yazý boþ ! ");
    	}
	}
	
    public void sil(int elemanNo){
		dizi.remove(elemanNo);
	}
	public void ekle(int elemanNo, double deðer){
		dizi.add(elemanNo, deðer);
	}
	public void ekle(double deðer){
		dizi.add(deðer);
	}
	public void deðiþ(int elemanNo, double deðer){
		dizi.set(elemanNo, deðer);
	}
    public double eriþ(int elemanNo){
    	return dizi.get(elemanNo);
    }
	
	public int boy(){
		return dizi.size();
	}
	
	public double enBüyük (){
		int elemanNo;
		double enBüyük = eriþ(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDeðer = eriþ(elemanNo);
			if(yeniDeðer> enBüyük){
				enBüyük = yeniDeðer;
			}
		}
		return enBüyük;
	}
	public double enKüçük(){
		int elemanNo;
		double enKüçük = eriþ(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDeðer = eriþ(elemanNo);
			if(yeniDeðer < enKüçük){
				enKüçük = yeniDeðer;
			}
		}
		return enKüçük;
	}
	public int enBüyüðünYeri(){
		int enBüyüðünYeri = 0,elemanNo;
		double enBüyük = eriþ(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDeðer = eriþ(elemanNo);
			if(yeniDeðer> enBüyük){
				enBüyük = yeniDeðer;
				enBüyüðünYeri = elemanNo;
			}
		}
		return enBüyüðünYeri;
	}
	public int enKüçüðünYeri (){
		int enKüçüðünYeri = 0,elemanNo;
		double enKüçük = eriþ(0);
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDeðer = eriþ(elemanNo);
			if(yeniDeðer < enKüçük){
				enKüçük = yeniDeðer;
				enKüçüðünYeri = elemanNo;
			}
		}
		return enKüçüðünYeri;
	}

	public Boolean büyüðüVarMý(double aranacak){
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(eriþ(elemanNo) > aranacak){
				return true;
			}
		}
		return false;
	}
	public Boolean varMý(double aranacak){
		Double ara = aranacak;
		return dizi.contains(ara);
	}
    
    public String  yaz(char ayýrýcý){
    	String yazý = "";
    	String ayýraç = "" + ayýrýcý;
    	int elemanNo;
    	int boy = boy();
    	for(elemanNo=0;elemanNo<boy-1;elemanNo++){
    		yazý += eriþ(elemanNo) + ayýraç;
    	}
    	yazý += eriþ(elemanNo);
    	return yazý;
    }

    public DoubleListVektör kopya (){
    	DoubleListVektör kopya = new DoubleListVektör();
    	int elemanNo,boy = boy();
    	for(elemanNo=0;elemanNo<boy;elemanNo++){
    		kopya.ekle(eriþ(elemanNo));
    	}
    	return kopya;
    }

}
