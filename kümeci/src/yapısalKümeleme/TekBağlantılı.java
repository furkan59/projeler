package yapýsalKümeleme;
import veriYapýlarý.*;


public class TekBaðlantýlý extends Baðlantýlý{

	public TekBaðlantýlý(BoolDizi parçaMakine, String[] makineÝsimleri, String[] parçaÝsimleri) throws Exception {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
	}
    public TekBaðlantýlý(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
	}
    public TekBaðlantýlý(String yazý) throws Exception {
		super(yazý);
	}
    
	@Override
	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1ÝleBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2ÝleBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return küçükBenzerliðiBul(grup1ÝleBenzerlik, grup2ÝleBenzerlik);
	}
	private double küçükBenzerliðiBul(double grup1ÝleBenzerlik, double grup2ÝleBenzerlik) {
		if(grup1ÝleBenzerlik < grup2ÝleBenzerlik){
			return grup1ÝleBenzerlik;
		}
		else{
			return grup2ÝleBenzerlik;
		}
	}
	
	@Override
	public String çöz() throws Exception{
		çözüm += "Tek Baðlantýlý Kümeleme \n\n";
		çözüm += super.çöz();
		return çözüm;
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}


	public String hýzlýÇöz() throws Exception{
		String çözüm = "Tek Baðlantýlý Kümeleme \n\n"; 
		return çözüm + super.hýzlýÇöz();
	}
}
