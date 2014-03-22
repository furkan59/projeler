package yap�salK�meleme;
import veriYap�lar�.*;


public class TekBa�lant�l� extends Ba�lant�l�{

	public TekBa�lant�l�(BoolDizi par�aMakine, String[] makine�simleri, String[] par�a�simleri) throws Exception {
		super(par�aMakine, makine�simleri, par�a�simleri);
	}
    public TekBa�lant�l�(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
	}
    public TekBa�lant�l�(String yaz�) throws Exception {
		super(yaz�);
	}
    
	@Override
	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1�leBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2�leBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return k���kBenzerli�iBul(grup1�leBenzerlik, grup2�leBenzerlik);
	}
	private double k���kBenzerli�iBul(double grup1�leBenzerlik, double grup2�leBenzerlik) {
		if(grup1�leBenzerlik < grup2�leBenzerlik){
			return grup1�leBenzerlik;
		}
		else{
			return grup2�leBenzerlik;
		}
	}
	
	@Override
	public String ��z() throws Exception{
		��z�m += "Tek Ba�lant�l� K�meleme \n\n";
		��z�m += super.��z();
		return ��z�m;
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}


	public String h�zl���z() throws Exception{
		String ��z�m = "Tek Ba�lant�l� K�meleme \n\n"; 
		return ��z�m + super.h�zl���z();
	}
}
