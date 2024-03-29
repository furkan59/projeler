package yapısalKümeleme;
import veriYapıları.*;
public class OrtalamaBağlantılıList extends Bağlantılı {

	public OrtalamaBağlantılıList(BoolDizi parçaMakine, String[] makineİsimleri, String[] parçaİsimleri) throws Exception {
		super(parçaMakine, makineİsimleri, parçaİsimleri);
	}

	public OrtalamaBağlantılıList(String yazı, char ayırıcı) throws Exception {
		super(yazı, ayırıcı);
	}

	public OrtalamaBağlantılıList(String yazı) throws Exception {
		super(yazı);
	}
	@Override
	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1İleBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2İleBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return ( grup1İleBenzerlik + grup2İleBenzerlik ) / 2;
	}
	
	@Override
	public String çöz() throws Exception{
		çözüm += "Ortalama Bağlantılı Kümeleme \n\n"; 
		çözüm += super.çöz();
		return çözüm;	
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}

	public String hızlıÇöz() throws Exception{
		return "Ortalama Bağlantılı Kümeleme \n\n" + super.hızlıÇöz();	
	}
}
