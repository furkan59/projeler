package yapısalKümeleme;

import veriYapıları.BoolDizi;
import veriYapıları.DoubleListVektör;

public class DoğrusalKümeleme extends Bağlantılı {

	public DoğrusalKümeleme(BoolDizi parçaMakine, String[] makineİsimleri, String [] parçaİsimleri) throws Exception {
		super(parçaMakine, makineİsimleri, parçaİsimleri);
	}
	public DoğrusalKümeleme(String yazı, char ayırıcı) throws Exception {
		super(yazı, ayırıcı);
	}
	public DoğrusalKümeleme(String yazı) throws Exception {
		super(yazı);
	}

	public void makineBenzerlikMatrsiniHesapla() throws Exception{
		int makine1;
		for(makine1=0;makine1<makineİsimleri.length;makine1++){
			makineSatırınBenzerlikHesapla(makine1);
		}
	}
	private void makineSatırınBenzerlikHesapla(int makine1) throws Exception {
		DoubleListVektör yeniSatır = new DoubleListVektör();
		int makine2;
		for(makine2=0;makine2<makine1+1;makine2++){
			double benzerlikKatsayısı = makineBenzerlikHesapla(makine1, makine2);
			yeniSatır.ekle(benzerlikKatsayısı);
		}
	    makinelerBenzerlik.satırEkle(yeniSatır);
	}
	private double makineBenzerlikHesapla(int makine1, int makine2) throws Exception{
		if(makine1 == makine2){
			return 0.0; // aynı makineleri gruplamasın diye
		}
		else {
			 return farklıMakinelerİçinHesapla(makine1, makine2);
		}
	}
	private double farklıMakinelerİçinHesapla(int makine1, int makine2) throws Exception {
		 int ortakParçalar = makineOrtakParçaEriş(makine1, makine2);
		 int benzerlik =  ortakParçalar*(parçaSayısı()-1);
		 benzerlik = ikiMakinedeBirdenİşlenmeyenleriEkle(makine1, makine2,benzerlik);
		 return benzerlik;
	}
	private int ikiMakinedeBirdenİşlenmeyenleriEkle(int makine1, int makine2, int benzerlik) throws Exception {
		for(int elemanNo = 0;elemanNo<parçaSayısı();elemanNo++){
			 if(parçaMakine.eriş(makine1, elemanNo) == false && parçaMakine.eriş(makine2, elemanNo) == false){
				 benzerlik ++;
			 }
		 }
		return benzerlik;
	}
    

	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1İleBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2İleBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return (grup1İleBenzerlik + grup2İleBenzerlik ) / 2;
	}
	
	@Override
	public String çöz() throws Exception{
		çözüm += "Doğrusal Kümeleme \n\n"; 
		çözüm += super.çöz();
		return çözüm;	
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}

	public String hızlıÇöz() throws Exception{
		return "Doğrusal Kümeleme \n\n" + super.hızlıÇöz();	
	}
}
