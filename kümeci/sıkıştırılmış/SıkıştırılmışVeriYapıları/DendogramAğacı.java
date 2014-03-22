package SýkýþtýrýlmýþVeriYapýlarý;

import java.util.ArrayList;


public class DendogramAðacý {

    public ArrayList<Integer> sayýlar = new ArrayList<Integer>();
    public ArrayList<DendogramAðacý> noktalar = new ArrayList<DendogramAðacý>();
    public DendogramAðacý üst ;
	
    // {{ kurucular
    public DendogramAðacý() {
	}
    public DendogramAðacý(int sayý) {
		sayýlar.add(sayý);
	}
    public DendogramAðacý(ArrayList<Integer> sayýlar) {
		sayýlar.addAll(sayýlar);
		for(int elemanNo = 0;elemanNo<sayýlar.size();elemanNo++){
			int yeniSayý = sayýlar.get(elemanNo);
			noktaEkle(yeniSayý);
		}
	}
    public void aðacýGrupla(ArrayList<ArrayList<Integer>> gruplananlar) throws CloneNotSupportedException{
    	for(int elemanNo = 0;elemanNo<gruplananlar.size();elemanNo++){
    		ArrayList<Integer> yeniKüme = gruplananlar.get(elemanNo); 
    		int grup1 = yeniKüme.get(0);
    		int grup2 = yeniKüme.get(1);
    		grupla(grup1, grup2);
    	}
    }
    public void aðacýDoldur(int toplamNokta){
    	for(int elemanNo = 0;elemanNo<toplamNokta;elemanNo++){
    		noktaEkle(elemanNo);
    	}
    }
    // }} kurucular
    // {{ noktalar
    public void noktaEkle(int sayý){
    	DendogramAðacý yeniNokta = new DendogramAðacý(sayý);
    	noktalar.add(yeniNokta);
    	yeniNokta.üst = this;
    }
    public void noktaEkle(ArrayList<Integer> sayýlar){
    	DendogramAðacý yeniNokta = new DendogramAðacý();
    	yeniNokta.sayýlar = sayýlar;
    	noktalar.add(yeniNokta);
    	yeniNokta.üst = this;
    }
    public void noktaEkle(DendogramAðacý nokta){
    	noktalar.add(nokta);
    	nokta.üst = this;
    }
    /**
     * altýndaki tüm noktalarýn deðerlerini kendine alýr ve alt noktlarý siler
     * <br> her seferinde 2 grup gruplanacaðý için bu iþlemi görecek olan noktanýn altýnda 2 nokta olacaktýr
     * <br> fakat içerisindeki listelerde birden çok eleman olabilir
     */
    public void noktalarýnýKendineÇek(){
    	ArrayList<Integer> tümNoktalar = new ArrayList<Integer>();
    	for(int noktaNo = 0;noktaNo<noktalar.size();noktaNo++){
    		ArrayList<Integer> altNoktananýnElemanlarý = noktalar.get(noktaNo).sayýlar;
    		tümNoktalar.addAll(altNoktananýnElemanlarý);
    	}
    	sayýlar = tümNoktalar;
    }
    public void noktaSil(int noktaNo){
    	noktalar.remove(noktaNo);
    	
    }
    
    // }} noktalar
    
    // {{ gruplama
    /**
     * gruplar sona atar
     * <br> bu çizme için deðil oluþturma için çizerken deðiþecek
     * @param nokta1
     * @param nokta2
     * @throws CloneNotSupportedException 
     */
    public void grupla (int nokta1,int nokta2) throws CloneNotSupportedException{
    	yeniNoktayýEkle(nokta1, nokta2);
        eskiNoktalarýSil(nokta1, nokta2);
    }
	private void yeniNoktayýEkle(int nokta1, int nokta2) {
		DendogramAðacý eleman1 = eriþ(nokta1);
    	DendogramAðacý eleman2 = eriþ(nokta2);
        DendogramAðacý yeniNokta = new DendogramAðacý();
        yeniNokta.noktaEkle(eleman1);
        yeniNokta.noktaEkle(eleman2);
        noktaEkle(yeniNokta);
	}
	private void eskiNoktalarýSil(int nokta1, int nokta2) {
		if( nokta1 < nokta2){
           noktaSil(nokta1);
           noktaSil(nokta2-1);
        }
        else {
        	noktaSil(nokta2);
        	noktaSil(nokta1-1);
        }
	}
	
	public DendogramAðacý eriþ(int noktaNo){
		return noktalar.get(noktaNo);
	}
	// }} gruplama

	public String yaz(){
		ArrayList<Integer> tümListe = tümDeðerleriListele();
		return tümListe.toString();
	}
	public ArrayList<Integer> tümDeðerleriListele(){
		ArrayList<Integer> deðerler = new ArrayList<Integer>();
		deðerler.addAll(sayýlar);
		for(int noktaNo = 0;noktaNo < this.noktalar.size();noktaNo++){
			ArrayList<Integer> altListe = noktalar.get(noktaNo).tümDeðerleriListele();
			deðerler.addAll(altListe);
		}
		return deðerler;
	}
    
}
