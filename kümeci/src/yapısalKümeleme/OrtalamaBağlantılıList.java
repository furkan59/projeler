package yapýsalKümeleme;
import veriYapýlarý.*;
public class OrtalamaBaðlantýlýList extends Baðlantýlý {

	public OrtalamaBaðlantýlýList(BoolDizi parçaMakine, String[] makineÝsimleri, String[] parçaÝsimleri) throws Exception {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
	}

	public OrtalamaBaðlantýlýList(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
	}

	public OrtalamaBaðlantýlýList(String yazý) throws Exception {
		super(yazý);
	}
	@Override
	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1ÝleBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2ÝleBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return ( grup1ÝleBenzerlik + grup2ÝleBenzerlik ) / 2;
	}
	
	@Override
	public String çöz() throws Exception{
		çözüm += "Ortalama Baðlantýlý Kümeleme \n\n"; 
		çözüm += super.çöz();
		return çözüm;	
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}

	public String hýzlýÇöz() throws Exception{
		return "Ortalama Baðlantýlý Kümeleme \n\n" + super.hýzlýÇöz();	
	}
}
