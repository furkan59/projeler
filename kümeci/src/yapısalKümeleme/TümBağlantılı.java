package yapýsalKümeleme;

import veriYapýlarý.BoolDizi;

public class TümBaðlantýlý extends Baðlantýlý {

	// {{ kurucular 
	public TümBaðlantýlý(BoolDizi parçaMakine, String[] makineÝsimleri,
			String[] parçaÝsimleri) throws Exception {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
	}
	public TümBaðlantýlý(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
	}
	public TümBaðlantýlý(String yazý) throws Exception {
		super(yazý);
	}
	// }} kurucular 

	@Override
	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1ÝleBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2ÝleBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return büyükBenzerliðiBul(grup1ÝleBenzerlik, grup2ÝleBenzerlik);
	}
	private double büyükBenzerliðiBul(double grup1ÝleBenzerlik, double grup2ÝleBenzerlik) {
		if(grup1ÝleBenzerlik > grup2ÝleBenzerlik){
			return grup1ÝleBenzerlik;
		}
		else{
			return grup2ÝleBenzerlik;
		}
	}
	@Override
	public String çöz() throws Exception{
		çözüm += "Tam Baðlantýlý Kümeleme \n\n";
		çözüm += super.çöz();
		return çözüm;
	}
	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}
	public String hýzlýÇöz() throws Exception{
		String çözüm = "Tüm Baðlantýlý Kümeleme \n\n"; 
		return çözüm + super.hýzlýÇöz();
	}

}
