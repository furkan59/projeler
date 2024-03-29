package yapısalKümeleme;

import java.util.ArrayList;
import java.util.TreeMap;

import veriYapıları.BoolDizi;
import kümeci.Kümeleme;

public class KümeBulmaAğaçlı extends Kümeleme {

	private TreeMap<Integer, Integer> çizilenSatırlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> çizilmeyenSatırlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> çizilenSutunlar = new TreeMap<Integer, Integer>();
	private TreeMap<Integer, Integer> çizilmeyenSutunlar = new TreeMap<Integer, Integer>();
	
	private int grupNo=0; // gruplanan satırlar için kullanılacak 
    private long baş = System.currentTimeMillis(),son;
	public KümeBulmaAğaçlı(String yazı) throws Exception {
		super(yazı);
	}
    
	private void satırÇiz(int satır) throws Exception{
			çizilenSatırlar.put(satır, satır);
			çizilmeyenSatırlardanÇıkar(satır);
			makineGrupları.get(grupNo).add(satır);
	}
    private void çizilmeyenSatırlardanÇıkar(int satır) {
		çizilmeyenSatırlar.remove(satır);
	}
	
    private void sutunÇiz(int sutun) throws Exception{
    		çizilenSutunlar.put(sutun,sutun);
    		çizilmeyenSutunlardanÇıkar(sutun);
    		parçaGrupları.get(grupNo).add(sutun);
	}
	private void çizilmeyenSutunlardanÇıkar(int sutun) {
		çizilmeyenSutunlar.remove(sutun);
	}
	
    private ArrayList<Integer> satırdaBirleriBul(int satır) throws Exception{
		ArrayList<Integer> birlerinSutunları = new ArrayList<>();
		int sutunNo;
		for(sutunNo=0;sutunNo<parçaMakine.sutunSay();sutunNo++){
			if(parçaMakine.eriş(satır, sutunNo)){
				birlerinSutunları.add(sutunNo);
			}
		}
		return birlerinSutunları;
	}
	private ArrayList<Integer> sutundaBirleriBul(int sutun) throws Exception{
		ArrayList<Integer> birlerinSatırları = new ArrayList<>();
		int satırNo;
		for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
			if(parçaMakine.eriş(satırNo, sutun)){
				birlerinSatırları.add(satırNo);
			}
		}
		return birlerinSatırları;
	}

	
	private boolean satırÇizilebilirMi(int satır) throws Exception{
		return çizilmeyenSatırlar.containsKey(satır);
	}
	private boolean sutunÇizilebilirMi(int sutun) throws Exception{
		return çizilmeyenSutunlar.containsKey(sutun);
	}

	/**
	 * o satırdan başlar o satırı çizer sonra da o satırdaki 1 ler bulur ve 1 lerin
	 * <br> olduğu sutunları çizer (eğer sutunlar çizilebilirse)
	 * <br> satırın çizilip çizilemeyeceğinin başka bir fonksyon tarafından 
	 * <br> kontrol edilmesi gerekir
	 * @param satır
	 * @throws Exception 
	 */
    private boolean satırSutunÇiz(int satır) throws Exception{
    	satırÇiz(satır);
    	ArrayList<Integer> sutunlar = satırdaBirleriBul(satır);
    	return sutunSatırÇiz(sutunlar);
    }
    private boolean satırSutunÇiz(ArrayList<Integer> satırlar ) throws Exception{
    	int elemanNo;
    	boolean devamMı = false;
    	for(elemanNo=0;elemanNo<satırlar.size();elemanNo++){
    		int satır = satırlar.get(elemanNo);
    		if(satırÇizilebilirMi(satır)){
    			satırSutunÇiz(satır);
    			devamMı = true;
    		}
    	}
    	return devamMı;
    }
    
    private boolean sutunSatırÇiz(int sutun) throws Exception{
    	sutunÇiz(sutun);
    	ArrayList<Integer> satırlar = sutundaBirleriBul(sutun);
    	return satırSutunÇiz(satırlar);
    }
    private boolean sutunSatırÇiz(ArrayList<Integer> sutunlar ) throws Exception{
    	int elemanNo;
    	boolean devamMı = false;
    	for(elemanNo=0;elemanNo<sutunlar.size();elemanNo++){
    		int sutun = sutunlar.get(elemanNo);
    		if(sutunÇizilebilirMi(sutun)){
    			sutunSatırÇiz(sutun);
    			devamMı = true;
    		}
    	}
    	return devamMı;
    }

    private void çizilmeyenSutunlarıOluştur(){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<parçaMakine.sutunSay();elemanNo++){
    		çizilmeyenSutunlar.put(elemanNo,elemanNo);
    	}
    }
    private void çizilmeyenSatırlarıOluştur(){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<parçaMakine.satırSay();elemanNo++){
    		çizilmeyenSatırlar.put(elemanNo,elemanNo);
    	}
    }

    
    private void kümeBul() throws Exception{
    	// önce hangi satırdan başlicaz onu bulalım
    	int başlamaSatırı = çizilmeyenSatırlar.firstKey();
    	gruplananlarıGenişlet();
    	satırSutunÇiz(başlamaSatırı);
    	grupNo ++;
    }

	private void gruplananlarıGenişlet() {
		ArrayList<Integer> yenigrupSatır = new ArrayList<>();
    	ArrayList<Integer> yeniGrupSutun = new ArrayList<>();
    	parçaGrupları.add(yeniGrupSutun);
    	makineGrupları.add(yenigrupSatır);
	}
    
    private boolean tümMatrisÇizildiMi(){
    	if(çizilmeyenSatırlar.size() == 0 && çizilmeyenSutunlar.size() == 0){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    public String çöz() throws Exception{
    	String çözüm = "";
    	çizilmeyenleriOluştur();
    	çözüm += çözümDöngüsüYap();
    	çözüm += yeniParçaMakineMatrisi();
    	son = System.currentTimeMillis();
    	çözüm += "çözme süresi : " + (son-baş) +" milisaniye\n";
    	return çözüm;
    }

	private String yeniParçaMakineMatrisi() throws Exception {
		String çözüm = "";
		çözüm += "yeni parça makine matrisi \n";
    	çözüm +=  yeniParçaMakineMatrisiYaz2()+ "\n";
		return çözüm;
	}
	private void çizilmeyenleriOluştur() {
		çizilmeyenSatırlarıOluştur();
    	çizilmeyenSutunlarıOluştur();
	}
    private String çözümDöngüsüYap() throws Exception {
		String çözüm = "";
		int aşamaNo;
    	for(aşamaNo=0;;aşamaNo++){
    		if(tümMatrisÇizildiMi() ){
    			break;
    		}
    		else{
    			kümeBul();
        		çözüm += "aşamaNo : " + aşamaNo  + "\n\n-------------------------\n\n";
        		çözüm += yaz() + "\n\n";	
    		}
    	}
		return çözüm;
	}

    public String yaz() throws Exception{
    	String yazı ="";
    	yazı += "çizilen satırlar : " + çizilenSatırlar.toString() + "\n";
    	yazı += "çizilen sutunlar : " + çizilenSutunlar.toString() + "\n";
    	yazı += "çizilmeyen satırlar : "  + çizilmeyenSatırlar.toString() + "\n";
    	yazı += "çilzmeyen sutunlar  : " + çizilmeyenSutunlar.toString()+ "\n";
    	yazı += "gruplanan Satırlar : \n" + makineGruplarınıYaz() +"\n"; 
    	yazı += "gruplanan Sutunlar  : \n" + parçaGruplarınıYaz() +"\n";
    	return yazı;
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
