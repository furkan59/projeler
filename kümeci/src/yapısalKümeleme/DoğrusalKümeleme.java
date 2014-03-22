package yap�salK�meleme;

import veriYap�lar�.BoolDizi;
import veriYap�lar�.DoubleListVekt�r;

public class Do�rusalK�meleme extends Ba�lant�l� {

	public Do�rusalK�meleme(BoolDizi par�aMakine, String[] makine�simleri, String [] par�a�simleri) throws Exception {
		super(par�aMakine, makine�simleri, par�a�simleri);
	}
	public Do�rusalK�meleme(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
	}
	public Do�rusalK�meleme(String yaz�) throws Exception {
		super(yaz�);
	}

	public void makineBenzerlikMatrsiniHesapla() throws Exception{
		int makine1;
		for(makine1=0;makine1<makine�simleri.length;makine1++){
			makineSat�r�nBenzerlikHesapla(makine1);
		}
	}
	private void makineSat�r�nBenzerlikHesapla(int makine1) throws Exception {
		DoubleListVekt�r yeniSat�r = new DoubleListVekt�r();
		int makine2;
		for(makine2=0;makine2<makine1+1;makine2++){
			double benzerlikKatsay�s� = makineBenzerlikHesapla(makine1, makine2);
			yeniSat�r.ekle(benzerlikKatsay�s�);
		}
	    makinelerBenzerlik.sat�rEkle(yeniSat�r);
	}
	private double makineBenzerlikHesapla(int makine1, int makine2) throws Exception{
		if(makine1 == makine2){
			return 0.0; // ayn� makineleri gruplamas�n diye
		}
		else {
			 return farkl�Makineler��inHesapla(makine1, makine2);
		}
	}
	private double farkl�Makineler��inHesapla(int makine1, int makine2) throws Exception {
		 int ortakPar�alar = makineOrtakPar�aEri�(makine1, makine2);
		 int benzerlik =  ortakPar�alar*(par�aSay�s�()-1);
		 benzerlik = ikiMakinedeBirden��lenmeyenleriEkle(makine1, makine2,benzerlik);
		 return benzerlik;
	}
	private int ikiMakinedeBirden��lenmeyenleriEkle(int makine1, int makine2, int benzerlik) throws Exception {
		for(int elemanNo = 0;elemanNo<par�aSay�s�();elemanNo++){
			 if(par�aMakine.eri�(makine1, elemanNo) == false && par�aMakine.eri�(makine2, elemanNo) == false){
				 benzerlik ++;
			 }
		 }
		return benzerlik;
	}
    

	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1�leBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2�leBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return (grup1�leBenzerlik + grup2�leBenzerlik ) / 2;
	}
	
	@Override
	public String ��z() throws Exception{
		��z�m += "Do�rusal K�meleme \n\n"; 
		��z�m += super.��z();
		return ��z�m;	
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}

	public String h�zl���z() throws Exception{
		return "Do�rusal K�meleme \n\n" + super.h�zl���z();	
	}
}
