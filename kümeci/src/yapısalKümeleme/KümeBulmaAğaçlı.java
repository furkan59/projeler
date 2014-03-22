package yapýsalKümeleme;

import java.util.ArrayList;
import java.util.TreeMap;

import veriYapýlarý.BoolDizi;
import kümeci.Kümeleme;

public class KümeBulmaAðaçlý extends Kümeleme {

	private TreeMap<Integer, Integer> çizilenSatýrlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> çizilmeyenSatýrlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> çizilenSutunlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> çizilmeyenSutunlar = new TreeMap<Integer, Integer>();
	
	private int grupNo=0; // gruplanan satýrlar için kullanýlacak 
    private long baþ = System.currentTimeMillis(),son;
	public KümeBulmaAðaçlý(String yazý) throws Exception {
		super(yazý);
	}
    
	private void satýrÇiz(int satýr) throws Exception{
			çizilenSatýrlar.put(satýr, satýr);
			çizilmeyenSatýrlardanÇýkar(satýr);
			makineGruplarý.get(grupNo).add(satýr);
	}
    private void çizilmeyenSatýrlardanÇýkar(int satýr) {
		çizilmeyenSatýrlar.remove(satýr);
	}
	
    private void sutunÇiz(int sutun) throws Exception{
    		çizilenSutunlar.put(sutun,sutun);
    		çizilmeyenSutunlardanÇýkar(sutun);
    		parçaGruplarý.get(grupNo).add(sutun);
	}
	private void çizilmeyenSutunlardanÇýkar(int sutun) {
		çizilmeyenSutunlar.remove(sutun);
	}
	
    private ArrayList<Integer> satýrdaBirleriBul(int satýr) throws Exception{
		ArrayList<Integer> birlerinSutunlarý = new ArrayList<>();
		int sutunNo;
		for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
			if(parçaMakine.eriþ(satýr, sutunNo)){
				birlerinSutunlarý.add(sutunNo);
			}
		}
		return birlerinSutunlarý;
	}
	private ArrayList<Integer> sutundaBirleriBul(int sutun) throws Exception{
		ArrayList<Integer> birlerinSatýrlarý = new ArrayList<>();
		int satýrNo;
		for(satýrNo=0;satýrNo<parçaMakine.satýrSay();satýrNo++){
			if(parçaMakine.eriþ(satýrNo, sutun)){
				birlerinSatýrlarý.add(satýrNo);
			}
		}
		return birlerinSatýrlarý;
	}

	
	private boolean satýrÇizilebilirMi(int satýr) throws Exception{
		return çizilmeyenSatýrlar.containsKey(satýr);
	}
	private boolean sutunÇizilebilirMi(int sutun) throws Exception{
		return çizilmeyenSutunlar.containsKey(sutun);
	}

	/**
	 * o satýrdan baþlar o satýrý çizer sonra da o satýrdaki 1 ler bulur ve 1 lerin
	 * <br> olduðu sutunlarý çizer (eðer sutunlar çizilebilirse)
	 * <br> satýrýn çizilip çizilemeyeceðinin baþka bir fonksyon tarafýndan 
	 * <br> kontrol edilmesi gerekir
	 * @param satýr
	 * @throws Exception 
	 */
    private boolean satýrSutunÇiz(int satýr) throws Exception{
    	satýrÇiz(satýr);
    	ArrayList<Integer> sutunlar = satýrdaBirleriBul(satýr);
    	return sutunSatýrÇiz(sutunlar);
    }
    private boolean satýrSutunÇiz(ArrayList<Integer> satýrlar ) throws Exception{
    	int elemanNo;
    	boolean devamMý = false;
    	for(elemanNo=0;elemanNo<satýrlar.size();elemanNo++){
    		int satýr = satýrlar.get(elemanNo);
    		if(satýrÇizilebilirMi(satýr)){
    			satýrSutunÇiz(satýr);
    			devamMý = true;
    		}
    	}
    	return devamMý;
    }
    
    private boolean sutunSatýrÇiz(int sutun) throws Exception{
    	sutunÇiz(sutun);
    	ArrayList<Integer> satýrlar = sutundaBirleriBul(sutun);
    	return satýrSutunÇiz(satýrlar);
    }
    private boolean sutunSatýrÇiz(ArrayList<Integer> sutunlar ) throws Exception{
    	int elemanNo;
    	boolean devamMý = false;
    	for(elemanNo=0;elemanNo<sutunlar.size();elemanNo++){
    		int sutun = sutunlar.get(elemanNo);
    		if(sutunÇizilebilirMi(sutun)){
    			sutunSatýrÇiz(sutun);
    			devamMý = true;
    		}
    	}
    	return devamMý;
    }

    private void çizilmeyenSutunlarýOluþtur(){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<parçaMakine.sutunSay();elemanNo++){
    		çizilmeyenSutunlar.put(elemanNo,elemanNo);
    	}
    }
    private void çizilmeyenSatýrlarýOluþtur(){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<parçaMakine.satýrSay();elemanNo++){
    		çizilmeyenSatýrlar.put(elemanNo,elemanNo);
    	}
    }

    
    private void kümeBul() throws Exception{
    	// önce hangi satýrdan baþlicaz onu bulalým
    	int baþlamaSatýrý = çizilmeyenSatýrlar.firstKey();
    	gruplananlarýGeniþlet();
    	satýrSutunÇiz(baþlamaSatýrý);
    	grupNo ++;
    }

	private void gruplananlarýGeniþlet() {
		ArrayList<Integer> yenigrupSatýr = new ArrayList<>();
    	ArrayList<Integer> yeniGrupSutun = new ArrayList<>();
    	parçaGruplarý.add(yeniGrupSutun);
    	makineGruplarý.add(yenigrupSatýr);
	}
    
    private boolean tümMatrisÇizildiMi(){
    	if(çizilmeyenSatýrlar.size() == 0 && çizilmeyenSutunlar.size() == 0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public String çöz() throws Exception{
    	String çözüm = "";
    	çizilmeyenleriOluþtur();
    	çözüm += çözümDöngüsüYap();
    	çözüm += yeniParçaMakineMatrisi();
    	son = System.currentTimeMillis();
    	çözüm += "çözme süresi : " + (son-baþ) +" milisaniye\n";
    	return çözüm;
    }

	private String yeniParçaMakineMatrisi() throws Exception {
		String çözüm = "";
		çözüm += "yeni parça makine matrisi \n";
    	çözüm +=  yeniParçaMakineMatrisiYaz2()+ "\n";
		return çözüm;
	}
	private void çizilmeyenleriOluþtur() {
		çizilmeyenSatýrlarýOluþtur();
    	çizilmeyenSutunlarýOluþtur();
	}
    private String çözümDöngüsüYap() throws Exception {
		String çözüm = "";
		int aþamaNo;
    	for(aþamaNo=0;;aþamaNo++){
    		if(tümMatrisÇizildiMi() ){
    			break;
    		}
    		else{
    			kümeBul();
        		çözüm += "aþamaNo : " + aþamaNo  + "\n\n-------------------------\n\n";
        		çözüm += yaz() + "\n\n";	
    		}
    	}
		return çözüm;
	}

    public String yaz() throws Exception{
    	String yazý ="";
    	yazý += "çizilen satýrlar : " + çizilenSatýrlar.toString() + "\n";
    	yazý += "çizilen sutunlar : " + çizilenSutunlar.toString() + "\n";
    	yazý += "çizilmeyen satýrlar : "  + çizilmeyenSatýrlar.toString() + "\n";
    	yazý += "çilzmeyen sutunlar  : " + çizilmeyenSutunlar.toString()+ "\n";
    	yazý += "gruplanan Satýrlar : \n" + makineGruplarýnýYaz() +"\n"; 
    	yazý += "gruplanan Sutunlar  : \n" + parçaGruplarýnýYaz() +"\n";
    	return yazý;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
