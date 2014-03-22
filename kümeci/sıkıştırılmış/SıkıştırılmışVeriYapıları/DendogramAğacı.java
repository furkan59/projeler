package S�k��t�r�lm��VeriYap�lar�;

import java.util.ArrayList;


public class DendogramA�ac� {

    public ArrayList<Integer> say�lar = new ArrayList<Integer>();
    public ArrayList<DendogramA�ac�> noktalar = new ArrayList<DendogramA�ac�>();
    public DendogramA�ac� �st ;
	
    // {{ kurucular
    public DendogramA�ac�() {
	}
    public DendogramA�ac�(int say�) {
		say�lar.add(say�);
	}
    public DendogramA�ac�(ArrayList<Integer> say�lar) {
		say�lar.addAll(say�lar);
		for(int elemanNo = 0;elemanNo<say�lar.size();elemanNo++){
			int yeniSay� = say�lar.get(elemanNo);
			noktaEkle(yeniSay�);
		}
	}
    public void a�ac�Grupla(ArrayList<ArrayList<Integer>> gruplananlar) throws CloneNotSupportedException{
    	for(int elemanNo = 0;elemanNo<gruplananlar.size();elemanNo++){
    		ArrayList<Integer> yeniK�me = gruplananlar.get(elemanNo); 
    		int grup1 = yeniK�me.get(0);
    		int grup2 = yeniK�me.get(1);
    		grupla(grup1, grup2);
    	}
    }
    public void a�ac�Doldur(int toplamNokta){
    	for(int elemanNo = 0;elemanNo<toplamNokta;elemanNo++){
    		noktaEkle(elemanNo);
    	}
    }
    // }} kurucular
    // {{ noktalar
    public void noktaEkle(int say�){
    	DendogramA�ac� yeniNokta = new DendogramA�ac�(say�);
    	noktalar.add(yeniNokta);
    	yeniNokta.�st = this;
    }
    public void noktaEkle(ArrayList<Integer> say�lar){
    	DendogramA�ac� yeniNokta = new DendogramA�ac�();
    	yeniNokta.say�lar = say�lar;
    	noktalar.add(yeniNokta);
    	yeniNokta.�st = this;
    }
    public void noktaEkle(DendogramA�ac� nokta){
    	noktalar.add(nokta);
    	nokta.�st = this;
    }
    /**
     * alt�ndaki t�m noktalar�n de�erlerini kendine al�r ve alt noktlar� siler
     * <br> her seferinde 2 grup gruplanaca�� i�in bu i�lemi g�recek olan noktan�n alt�nda 2 nokta olacakt�r
     * <br> fakat i�erisindeki listelerde birden �ok eleman olabilir
     */
    public void noktalar�n�Kendine�ek(){
    	ArrayList<Integer> t�mNoktalar = new ArrayList<Integer>();
    	for(int noktaNo = 0;noktaNo<noktalar.size();noktaNo++){
    		ArrayList<Integer> altNoktanan�nElemanlar� = noktalar.get(noktaNo).say�lar;
    		t�mNoktalar.addAll(altNoktanan�nElemanlar�);
    	}
    	say�lar = t�mNoktalar;
    }
    public void noktaSil(int noktaNo){
    	noktalar.remove(noktaNo);
    	
    }
    
    // }} noktalar
    
    // {{ gruplama
    /**
     * gruplar sona atar
     * <br> bu �izme i�in de�il olu�turma i�in �izerken de�i�ecek
     * @param nokta1
     * @param nokta2
     * @throws CloneNotSupportedException 
     */
    public void grupla (int nokta1,int nokta2) throws CloneNotSupportedException{
    	yeniNoktay�Ekle(nokta1, nokta2);
        eskiNoktalar�Sil(nokta1, nokta2);
    }
	private void yeniNoktay�Ekle(int nokta1, int nokta2) {
		DendogramA�ac� eleman1 = eri�(nokta1);
    	DendogramA�ac� eleman2 = eri�(nokta2);
        DendogramA�ac� yeniNokta = new DendogramA�ac�();
        yeniNokta.noktaEkle(eleman1);
        yeniNokta.noktaEkle(eleman2);
        noktaEkle(yeniNokta);
	}
	private void eskiNoktalar�Sil(int nokta1, int nokta2) {
		if( nokta1 < nokta2){
           noktaSil(nokta1);
           noktaSil(nokta2-1);
        }
        else {
        	noktaSil(nokta2);
        	noktaSil(nokta1-1);
        }
	}
	
	public DendogramA�ac� eri�(int noktaNo){
		return noktalar.get(noktaNo);
	}
	// }} gruplama

	public String yaz(){
		ArrayList<Integer> t�mListe = t�mDe�erleriListele();
		return t�mListe.toString();
	}
	public ArrayList<Integer> t�mDe�erleriListele(){
		ArrayList<Integer> de�erler = new ArrayList<Integer>();
		de�erler.addAll(say�lar);
		for(int noktaNo = 0;noktaNo < this.noktalar.size();noktaNo++){
			ArrayList<Integer> altListe = noktalar.get(noktaNo).t�mDe�erleriListele();
			de�erler.addAll(altListe);
		}
		return de�erler;
	}
    
}
