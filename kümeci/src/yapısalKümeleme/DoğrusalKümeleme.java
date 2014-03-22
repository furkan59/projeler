package yapýsalKümeleme;

import veriYapýlarý.BoolDizi;
import veriYapýlarý.DoubleListVektör;

public class DoðrusalKümeleme extends Baðlantýlý {

	public DoðrusalKümeleme(BoolDizi parçaMakine, String[] makineÝsimleri, String [] parçaÝsimleri) throws Exception {
		super(parçaMakine, makineÝsimleri, parçaÝsimleri);
	}
	public DoðrusalKümeleme(String yazý, char ayýrýcý) throws Exception {
		super(yazý, ayýrýcý);
	}
	public DoðrusalKümeleme(String yazý) throws Exception {
		super(yazý);
	}

	public void makineBenzerlikMatrsiniHesapla() throws Exception{
		int makine1;
		for(makine1=0;makine1<makineÝsimleri.length;makine1++){
			makineSatýrýnBenzerlikHesapla(makine1);
		}
	}
	private void makineSatýrýnBenzerlikHesapla(int makine1) throws Exception {
		DoubleListVektör yeniSatýr = new DoubleListVektör();
		int makine2;
		for(makine2=0;makine2<makine1+1;makine2++){
			double benzerlikKatsayýsý = makineBenzerlikHesapla(makine1, makine2);
			yeniSatýr.ekle(benzerlikKatsayýsý);
		}
	    makinelerBenzerlik.satýrEkle(yeniSatýr);
	}
	private double makineBenzerlikHesapla(int makine1, int makine2) throws Exception{
		if(makine1 == makine2){
			return 0.0; // ayný makineleri gruplamasýn diye
		}
		else {
			 return farklýMakinelerÝçinHesapla(makine1, makine2);
		}
	}
	private double farklýMakinelerÝçinHesapla(int makine1, int makine2) throws Exception {
		 int ortakParçalar = makineOrtakParçaEriþ(makine1, makine2);
		 int benzerlik =  ortakParçalar*(parçaSayýsý()-1);
		 benzerlik = ikiMakinedeBirdenÝþlenmeyenleriEkle(makine1, makine2,benzerlik);
		 return benzerlik;
	}
	private int ikiMakinedeBirdenÝþlenmeyenleriEkle(int makine1, int makine2, int benzerlik) throws Exception {
		for(int elemanNo = 0;elemanNo<parçaSayýsý();elemanNo++){
			 if(parçaMakine.eriþ(makine1, elemanNo) == false && parçaMakine.eriþ(makine2, elemanNo) == false){
				 benzerlik ++;
			 }
		 }
		return benzerlik;
	}
    

	protected double yeniBenzerlikHesapla(int grupNo) {
		double grup1ÝleBenzerlik = grupBenzerlikBul(grup1, grupNo);
		double grup2ÝleBenzerlik = grupBenzerlikBul(grup2, grupNo);
		return (grup1ÝleBenzerlik + grup2ÝleBenzerlik ) / 2;
	}
	
	@Override
	public String çöz() throws Exception{
		çözüm += "Doðrusal Kümeleme \n\n"; 
		çözüm += super.çöz();
		return çözüm;	
	}

	@Override
	public String yaz() throws Exception{
		 return super.yaz();
	}

	public String hýzlýÇöz() throws Exception{
		return "Doðrusal Kümeleme \n\n" + super.hýzlýÇöz();	
	}
}
