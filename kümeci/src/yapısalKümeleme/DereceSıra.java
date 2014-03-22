package yapısalKümeleme;
import kümeci.Kümeleme;
import veriYapıları.*;
public class DereceSıra extends Kümeleme {

	
	private Boolean satırlarMı ; // satıları mı sıralıyoruz
	public DereceSıra(BoolDizi parçaMakine, String[] makineİsimleri,String[] parçaİsimleri) {
		super(parçaMakine, makineİsimleri, parçaİsimleri);
		satırlarMı = true; 
		makineGruplarıOluştur();
	}
    public DereceSıra(String girdi) throws Exception{
    	super(girdi);
    	satırlarMı = true;
    	makineGruplarıOluştur();
    }
    private void satırNoKontrolEt(int satırNo) throws Exception {
		if(satırNo < 0 || satırNo > this.parçaMakine.satırSay()-1){
    		throw new Exception("satırNo uyumsuz girilen satırno = " + satırNo + " toplam satır sayısı = " + parçaMakine.satırSay() + "\n");
    	}
	}
    
	public void satırlarıSırala() throws Exception{
		//parçaMakine.satırlarıSırala();
		int satırNo;
    	for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
    		enBüyükSatırıBaşaAl(satırNo);
    	}
	}
    private void enBüyükSatırıBaşaAl(int satırNo) throws Exception {
		int satırNo2;
		int Enbüyük = satırNo; // ilk başta bunu kabul edelim
		for(satırNo2=satırNo+1;satırNo2<parçaMakine.satırSay();satırNo2++){
			if(parçaMakine.satır(Enbüyük).büyükMü(parçaMakine.satır(satırNo2)) == 1) {// satırNo2 daha büyük
				Enbüyük = satırNo2;
			}
		}
		satırİçinDeğiştirmeler(satırNo, Enbüyük);
	}
    private void satırİçinDeğiştirmeler(int satırNo, int Enbüyük) throws Exception {
		parçaMakine.satırıDeğiş(satırNo, Enbüyük);
		String değişecekMakine = makineİsimleri[satırNo]; 
		makineİsimleri[satırNo] = makineİsimleri[Enbüyük];
		makineİsimleri[Enbüyük] = değişecekMakine;
	}
	
    public void sutunlarıSırala() throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
    		enBüyükSutunuBaşaAl(sutunNo);
    	}
    }
    private void enBüyükSutunuBaşaAl(int sutunNo) throws Exception {
		int sutunNo2;
		int Enbüyük = sutunNo; // ilk başta bunu kabul edelim
		for(sutunNo2=sutunNo+1;sutunNo2<parçaMakine.sutunSay();sutunNo2++){
			if(parçaMakine.ikiSutunuKarşılaştır(sutunNo2, Enbüyük) == 1) {// sutunNo2 daha büyük
				Enbüyük = sutunNo2;
			}
		}
		sutunİçinDeğiştirmeYap(sutunNo, Enbüyük);
	}

	private void sutunİçinDeğiştirmeYap(int sutunNo, int Enbüyük) throws Exception {
		parçaMakine.sutunuDeğiş(sutunNo, Enbüyük);
		String değişecekParça = parçaİsimleri[sutunNo];
		parçaİsimleri[sutunNo] = parçaİsimleri[Enbüyük];
		parçaİsimleri[Enbüyük] = değişecekParça;
	}
	
	public Boolean satırlarSıralıMı() throws Exception{
		return parçaMakine.satırlarSıralıMı();
	}
	public Boolean sutunlarSıralıMı() throws Exception{
		return parçaMakine.sutunlarSıralıMı();
	}

	/**
	 * eğer ilerlenebilir bir durumda ise true döner 
	 * @return
	 * @throws Exception
	 */
	public Boolean ilerle() throws Exception{
		if(satırlarMı == true){
			return satırİlerle();
		}
		else {
			return sutundanİlerle();
		}
	}
    private Boolean sutundanİlerle() throws Exception {
		if(sutunlarSıralıMı() == true){
			return false;
		}
		else {
			sutunlarıSırala();
			return true;
		}
	}
	private Boolean satırİlerle() throws Exception {
		if(satırlarSıralıMı() == true){
			return false;
		}
		else {
			satırlarıSırala();
			return true;
		}
	}
    
	public String çöz () throws Exception{
		String çözüm = "";
		int aşamaNo;
		for(aşamaNo=0;aşamaNo<10;aşamaNo++){
			if( ilerle() == true){
				çözüm += "aşama no" + aşamaNo  + "\n\n"  + yaz()  +"\n\n";
				satırlarMı = !satırlarMı;	
			}
			else {
				break;
			}
			
		}
		çözüm += "aşama no" + aşamaNo  + "\n\n"  + yaz()  +"\n\n";
		return çözüm;
	}
	
	@Override
	public String yaz() throws Exception{
		String yazı = "satırlar mı = " + satırlarMı + "\n\n" +
                       super.yaz();
		return yazı;
	}
	public String hızlıÇöz() throws Exception {
		String çözüm = "";
		int aşamaNo;
		for(aşamaNo=0;aşamaNo<10;aşamaNo++){
			if( ilerle() == true){
				satırlarMı = !satırlarMı;	
			}
			else {
				break;
			}
			
		}
		çözüm += "aşama no" + aşamaNo  + "\n\n"  + yaz()  +"\n\n";
		return çözüm;
	}
}
