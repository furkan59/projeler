package yap�salK�meleme;
import veriYap�lar�.*;
public class OrtalamaBa�lant�l�List extends Ba�lant�l� {

	public OrtalamaBa�lant�l�List(BoolDizi par�aMakine, String[] makine�simleri, String[] par�a�simleri) throws Exception {
		super(par�aMakine, makine�simleri, par�a�simleri);
	}

	public OrtalamaBa�lant�l�List(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
	}

	public OrtalamaBa�lant�l�List(String yaz�) throws Exception {
		super(yaz�);
	}
	@Override
	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1�leBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2�leBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return ( grup1�leBenzerlik + grup2�leBenzerlik ) / 2;
	}
	
	@Override
	public String ��z() throws Exception{
		��z�m += "Ortalama Ba�lant�l� K�meleme \n\n"; 
		��z�m += super.��z();
		return ��z�m;	
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}

	public String h�zl���z() throws Exception{
		return "Ortalama Ba�lant�l� K�meleme \n\n" + super.h�zl���z();	
	}
}
