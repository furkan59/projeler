package yapýsalKümeleme;
import veriYapýlarý.*;
public class Parça {

	String isim;
	String [] özelliklerYazý ;
	DoubleVektör özelliklerSayý;
	public Parça() {
		
	}
	public Parça(String isim, String [] özelliklerYazý){
		this.isim = isim;
		this.özelliklerYazý = özelliklerYazý;
	}
	public Parça(String isim, DoubleVektör özelliklerSayý){
		this.isim = isim;
		this.özelliklerSayý = özelliklerSayý;
	}
	/**
	 * parça adý : parçanýn sayýsal özellikleri DoubleVektör halinde ;
	 * <br>parça1:1,2,3,4,5,4 
	 * @param yazý
	 * @throws Exception 
	 */
	public Parça(String yazý) throws Exception{
		çiftNoktaÝçeriyorMuBak(yazý);
		String [] bölünmüþ = yazý.split(":");
		DoubleVektör özelliklerSayý = new DoubleVektör(bölünmüþ[1]);
		String isim = bölünmüþ[0];
		this.isim = isim;
		this.özelliklerSayý = özelliklerSayý;
	}
	private void çiftNoktaÝçeriyorMuBak(String yazý) throws Exception {
		if(yazý.contains(":") == false){
			throw new Exception("yazý  ':'  içermiyor \nyazýnýn kendisi : " + yazý + "\n");
		}
	}
	
    public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String[] getÖzelliklerYazý() {
		return özelliklerYazý;
	}
	public void setÖzelliklerYazý(String[] özelliklerYazý) {
		this.özelliklerYazý = özelliklerYazý;
	}
	public DoubleVektör getÖzelliklerSayý() {
		return özelliklerSayý;
	}
	public void setÖzelliklerSayý(DoubleVektör özelliklerSayý) {
		this.özelliklerSayý = özelliklerSayý;
	}

	public double minkowskiUzaklýkBul(Parça parça2) throws Exception{
		double benzerlik = 0;
		int özellikNo;
		for(özellikNo=0;özellikNo<özelliklerSayý.boy();özellikNo++){
			double parça1Özellik = özelliklerSayý.eriþ(özellikNo);
			double parça2Özellik = parça2.özelliklerSayý.eriþ(özellikNo);
			double mutlakFark = Math.abs(parça2Özellik-parça1Özellik);
			benzerlik += mutlakFark;
		}
		return benzerlik;
	}

	public String özellikliYaz(){
		String  yazý ="";
		yazý += "isim : " + isim + "\n";
		yazý += "Sayýsal özellikler : \n" + özelliklerSayý.yaz("\t") + "\n";
		return yazý;
	}
	public String yaz(){
		return "isim : " + isim;
	}

}
