package metaSezgisel;
import veriYap�lar�.*;

import java.util.ArrayList;

import k�meci.K�meleme;

public class K_Ortalamalar extends K�meleme {

	DoubleDizi k�meMerkezleri;
	int k�meSay�s�;
	ArrayList<ArrayList<Integer>> k�meler; 
	Boolean ilerle = false; // her a�ama i�in bak�lacak 
	
    public K_Ortalamalar(BoolDizi par�aMakine, String[] makine�simleri,String[] par�a�simleri) {
		super(par�aMakine, makine�simleri, par�a�simleri);
		makineGruplar�Olu�tur();
	}
	public K_Ortalamalar(String yaz�, char ay�r�c�) throws Exception {
		super(yaz�, ay�r�c�);
		makineGruplar�Olu�tur();
	}
	public K_Ortalamalar(String yaz�) throws Exception {
		super(yaz�);
		makineGruplar�Olu�tur();
	}
    
	public void k�meSay�s�Belirle(int k�meSay�s�) throws Exception{
		k�meSay�s�n�KontrolEt(k�meSay�s�);
		this.k�meSay�s� = k�meSay�s�;
		k�meleriBo�Doldur();
	}
	private void k�meleriBo�Doldur() {
		k�meler = new ArrayList<ArrayList<Integer>>();
		int k�meNo;
		for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
			ArrayList<Integer> yeniK�me = new ArrayList<Integer>();
			k�meler.add(yeniK�me);
		}
	}
	private void k�meSay�s�n�KontrolEt(int k�meSay�s�) throws Exception {
		if(k�meSay�s� < 0 || k�meSay�s� > par�aMakine.sat�rSay()){
			throw new Exception("ge�ersiz k�me say�s�\ngirilen k�me say�s� : " + k�meSay�s� + "\ntoplam makine say�s�\n"); 
		}
	}
    public void k�meMerkezleriniRassalBelirle() throws Exception{
    	k�meMerkezleri = new DoubleDizi(k�meSay�s�, par�a�simleri.length);
    	k�meMerkezleri.rassalDiziDoldur();
    }
    
    public int makineyeEnYak�nK�meyiBul(int makineNo) throws Exception{
    	BoolVekt�r makineSat�r� = par�aMakine.sat�r(makineNo);
    	DoubleVekt�r ilkMakine = k�meMerkezleri.dizi[0];
    	double enK���kUzakl�k = makineSat�r�.�klidUzakl���Hesapla(ilkMakine); // ilk ba�ta b�yle kabul edelim
    	int enYak�nK�me = 0;
    	int k�meNo;
    	for(k�meNo=1;k�meNo<k�meSay�s�;k�meNo++){
    		DoubleVekt�r yeniK�me = k�meMerkezleri.dizi[k�meNo];
    		double yeniUzakl�k = makineSat�r�.�klidUzakl���Hesapla(yeniK�me);
    		if(yeniUzakl�k < enK���kUzakl�k){
    			enK���kUzakl�k = yeniUzakl�k;
    			enYak�nK�me = k�meNo;
    		}
    	}
    	return enYak�nK�me;
    }
    public void ilkK�meleme() throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<par�aMakine.sat�rSay();makineNo++){
    		int makineyeYak�nK�me = makineyeEnYak�nK�meyiBul(makineNo);
    		ArrayList<Integer> makineninGidece�iK�me = k�meler.get(makineyeYak�nK�me);
    		makineninGidece�iK�me.add(makineNo);
    	}
    }
    /**
     * bunun yap�labilmesi i�in k�menin dolu olmas� gerekir
     * @param k�meNo
     * @throws Exception 
     */
    private void yeniK�meMerkeziBelirle(int k�meNo) throws Exception{
    	k�melerBo�MuBak();
    	int makineSay�s� = k�meler.get(k�meNo).size();
    	int par�aSay�s� = par�aMakine.sutunSay();
    	int par�aNo;
    	for(par�aNo=0;par�aNo<par�aSay�s�;par�aNo++){
    		k�meMerkezi��inSutunOrtalamasIHesapla(k�meNo, makineSay�s�, par�aNo);
    	}
    }
	private void k�meMerkezi��inSutunOrtalamasIHesapla(int k�meNo, int makineSay�s�, int par�aNo) throws Exception {
		int makineNo;
		double toplam = 0;
		for(makineNo=0;makineNo<makineSay�s�;makineNo++){
			if(par�aMakine.eri�(makineNo, par�aNo) ){
				toplam ++;
			}
		}
		k�meMerkezleri.de�i�(k�meNo, par�aNo, toplam / makineSay�s�);
	}
	private void yeniK�meMerkezleriBelirle() throws Exception{
		int k�meNo;
		for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
			yeniK�meMerkeziBelirle(k�meNo);
		}
	}
	
    private void k�melerBo�MuBak() throws Exception {
		if(k�meler.get(0) == null){
    		throw new Exception("k�meler bo�\n");
    	}
	}
    private void yeniK�melemeYap() throws Exception{
    	int makineNo;
    	for(makineNo=0;makineNo<par�aMakine.sat�rSay();makineNo++){
    		makineyiK�meyeAta(makineNo);
    	}
    }
	private void makineyiK�meyeAta(int makineNo) throws Exception {
		int makineninEskiK�mesi = makineninK�mesiniBul(makineNo);
		int makineninYeniK�mesi = makineyeEnYak�nK�meyiBul(makineNo);
		if(makineninEskiK�mesi != makineninYeniK�mesi){ // de�i�im var
			makineyiBa�kaBirK�meyeAl(makineNo, makineninEskiK�mesi, makineninYeniK�mesi);
		}
	}
	private void makineyiBa�kaBirK�meyeAl(int makineNo,int makineninEskiK�mesi, int makineninYeniK�mesi) {
		makineyiEskiK�mesinden��kar(makineNo, makineninEskiK�mesi);
		makineyiYeniK�meyeEkle(makineNo, makineninYeniK�mesi);
		ilerle = true;
	}
	private void makineyiYeniK�meyeEkle(int makineNo, int makineninYeniK�mesi) {
		ArrayList<Integer> makineninGidece�iK�me = k�meler.get(makineninYeniK�mesi);
		makineninGidece�iK�me.add(makineNo);
	}
	private void makineyiEskiK�mesinden��kar(int makineNo,int makineninEskiK�mesi) {
		ArrayList<Integer> makineninGeldi�iK�me = k�meler.get(makineninEskiK�mesi);
		int makineninYeri = makineninGeldi�iK�me.indexOf(makineNo);
		makineninGeldi�iK�me.remove(makineninYeri);
		if(makineninGeldi�iK�me.size() == 0){
			ilerle = false; // bir k�me bitmi�
		}
	}
	/**
	 * @param makineNo
	 * @return
	 * @throws Exception 
	 */
    private int  makineninK�mesiniBul(int makineNo) throws Exception{
    	int k�meNo;
    	for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
    		ArrayList<Integer> k�me = k�meler.get(k�meNo);
    		if(k�me.contains(makineNo)){
    			return k�meNo;
    		}
    	}
    	throw new Exception("makine yok!\nmakineNo : " + makineNo + "\n" );
    }
	
    public String ��z() throws Exception{
    	String ��z�m = "";
    	ilkA�ama();
    	��z�m += yaz();
    	int a�amaNo;
    	for(a�amaNo=0;;a�amaNo++){
    		ilerle();
    		��z�m += "a�amaNo : " + a�amaNo + "\n\n" + yaz() + "\n\n"; 
    		if(ilerle == false){
    			break;
    		}
    	}
    	return ��z�m;
    }
	private void ilkA�ama() throws Exception {
		k�meleriBo�Doldur();
    	k�meMerkezleriniRassalBelirle();
    	ilkK�meleme();
	}
    @Override
    public String yaz(){
    	String yaz� = "de�i�im var m� : "  + ilerle + "\n\n";
    	yaz� += "k�me say�s� : " + k�meSay�s� + "\n\n";
    	yaz� += "k�me merkezleri :\n\n" + k�meMerkezleri.yaz("\t") + "\n\n";
    	yaz� += k�meleriYaz();
    	return yaz�;
    }
    public String k�meleriYaz(){
    	String yaz� ="";
    	int k�meNo;
    	for(k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
    		yaz� += k�meyiYazd�r(k�meNo);
    	}
    	return yaz�;
    }
    private String k�meyiYazd�r(int k�meNo){
    	String yaz� = "";
    	yaz� += "k�me : " + (k�meNo + 1)  + " : ";
    	ArrayList<Integer> k�me = k�meler.get(k�meNo);
		int elemanNo;
		for(elemanNo=0;elemanNo<k�me.size();elemanNo++){
			yaz� += k�me.get(elemanNo) + " , ";
		}
		yaz� += "\n";
		return yaz�;
    }

    public String h�zl���z() throws Exception{
    	String ��z�m = "";
    	ilkA�ama();
    	h�zl���z�mD�ng�s�();
    	��z�m += "par�a makine matrisi : " + yeniPar�aMakineMatrisiYaz2() + "\n";
    	return ��z�m;
    }
	private void h�zl���z�mD�ng�s�() throws Exception {
		int a�amaNo;
    	for(a�amaNo=0;;a�amaNo++){
    		ilerle();
    		if(ilerle == false){
    			break;
    		}
    	}
	}
	private void ilerle() throws Exception {
		ilerle = false; // ilk ba�ta de�i�im yok kabul edelim e�er de�i�im olmazs s�re� duracakt�r ama yeni k�meleme
		// yap�ld�ktan sonra de�i�im var ise g�r�n�r
		yeniK�meMerkezleriBelirle();
		yeniK�melemeYap();
	}

}
