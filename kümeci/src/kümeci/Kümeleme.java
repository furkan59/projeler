package kümeci;
import veriYapıları.*;
import yapısalKümeleme.Dendogram;
import yapısalKümeleme.DereceSıra;

import java.util.ArrayList;

public class Kümeleme {

	protected BoolDizi parçaMakine;
	protected String [] makineİsimleri;
	protected String [] parçaİsimleri;
	protected ArrayList<ArrayList<Integer>> makineGrupları = new ArrayList<ArrayList<Integer>>();
	protected ArrayList<ArrayList<Integer>> parçaGrupları = new ArrayList<ArrayList<Integer>>();
	
	protected ArrayList<ArrayList<Integer>> gruplananlar = new ArrayList<ArrayList<Integer>>();
	public boolean dendogramVarMı = false;
	protected DoubleDizi kümeMerkezleri ;
	
	// {{ kurucular 
	public Kümeleme(BoolDizi parçaMakine,String [] makineİsimleri,String [] parçaİsimleri) {
		this.parçaİsimleri = parçaİsimleri;
		this.makineİsimleri = makineİsimleri;
		this.parçaMakine = parçaMakine;
	}
	protected void makineGruplarıOluştur() {
		int elemanNo;
		for(elemanNo = 0; elemanNo < makineİsimleri.length ; elemanNo++){
			ArrayList<Integer> yeniGrup = new ArrayList<Integer>();
			yeniGrup.add(elemanNo);
			makineGrupları.add(yeniGrup);
		}
	}
	/**
	 * makine isimleri; 
	 * parça  isimleri;
	 * parça - makine Matrisi
	 * @param yazı
	 * @throws Exception 
	 */
	public Kümeleme(String yazı,char ayırıcı) throws Exception{
		String [] bölünmüş = yazı.split(";");
		String  ayıraç = "" + ayırıcı;
		makineİsimleriniAl(bölünmüş, ayıraç);
		parçaİsimleriniAl(bölünmüş, ayıraç);
		parçaMakine = new BoolDizi(bölünmüş[2], ayırıcı);
	}
	private void parçaİsimleriniAl(String[] bölünmüş, String ayıraç) {
		parçaİsimleri = bölünmüş[0].split(ayıraç);
		if(parçaİsimleri[0].contains("\n")){
			parçaİsimleri[0] = parçaİsimleri[0].split("\n")[0];
		}
		if(parçaİsimleri[parçaİsimleri.length-1].contains("\n")){
			parçaİsimleri[parçaİsimleri.length-1] = parçaİsimleri[parçaİsimleri.length-1].split("\n")[0];
				
		}
	}
	private void makineİsimleriniAl(String[] bölünmüş, String ayıraç) {
		makineİsimleri =  bölünmüş[1].split(ayıraç);
		if(makineİsimleri[0].contains("\n")){
			makineİsimleri[0] = makineİsimleri[0].split("\n")[0];
		}
		if(makineİsimleri[makineİsimleri.length-1].contains("\n")){
			makineİsimleri[makineİsimleri.length-1] =  makineİsimleri[makineİsimleri.length-1].split("\n")[0];
		}
	}
	/**
	    p1	p2	p3	p4	p5	p6	p7	p8 
<br>m1	1	0	1	0	0	1	0	1
<br>m2	1	0	0	0	0	1	0	0
<br>m3 	0	1	0	0	1	0	0	1
<br>m4  1	0	1	0	0	1	0	0
<br>m5	0	0	0	1	0	0	1	0
<br>m6  0	1	0	0	1	0	0	1
<br>m7  0	0	0	0	1	0	0	1
<br>m8	1	0	1	0	0	1	0	0
<br>m9	0	0	0	1	0	0	1	0
<br>m10	0	1	0	0	0	0	1	0
<br>bu şekilde girilecek
<br> veriler excelden dirke alınabilir
	 * @param yazı
	 * @throws Exception 
	 */
	public Kümeleme(String yazı) throws Exception{
		String [] bölünmüş = yazı.split("\\n");
		String [] parçalar = bölünmüş[0].split("\t");
		// ilk eleman boş olacak
		parçaİsimleriniAl(parçalar);
		makineİsimleri = new String [bölünmüş.length-1];
		parçaMakine = new BoolDizi(makineİsimleri.length, parçaİsimleri.length, false);
		int satırNo;
		for(satırNo=1;satırNo<bölünmüş.length;satırNo++){
			String [] yeniSatır = bölünmüş[satırNo].split("\t");
			yeniMakineAl(satırNo, yeniSatır);
			BoolVektör yeni = new BoolVektör(bölünmüş[satırNo],1,"\t");
			parçaMakine.matris[satırNo-1] = yeni;
		}
	}
	private void yeniMakineAl(int satırNo, String[] yeniSatır) {
		String yeniMakine = yeniSatır[0];
		makineİsimleri[satırNo-1] = yeniMakine;
	}
	private void parçaİsimleriniAl(String[] parçalar) {
		parçaİsimleri  = new String [parçalar.length-1];
		int parçaNo;
		for(parçaNo=1;parçaNo<parçalar.length;parçaNo++){
			parçaİsimleri[parçaNo-1] = parçalar[parçaNo];
		}
	}
    
	// }} kurucular 
	
	// {{ yazdırma 
    public String yaz() throws Exception{
		String yazı = "";
 		yazı = parçalarıYaz(yazı);
		yazı += "\n\n";
		yazı = makineleriVeMatrisiYaz(yazı);
		int grupNo;
		for(grupNo=0;grupNo<makineGrupları.size();grupNo++){
			yazı += "grup " + (grupNo+1) + " = " + makineGrupları.get(grupNo).toString() +"\n";
		}
		return yazı;
	}
	private String parçalarıYaz(String yazı) {
		int parçaNo;
 		yazı += "\t";
 		for(parçaNo=0;parçaNo<parçaİsimleri.length;parçaNo++){
 			yazı += parçaİsimleri[parçaNo] + "\t";
 		}
		return yazı;
	}
	private String makineleriVeMatrisiYaz(String yazı) throws Exception {
		int satırNo;
		for(satırNo=0;satırNo<parçaMakine.satırSay();satırNo++){
			yazı += makineİsimleri[satırNo] + "\t" + parçaMakine.satır(satırNo).yaz() + "\n";
		}
		return yazı;
	}
	// }} yazdırma 
	
    // {{ gruplar
	/**
	 * makine ve ya makine gruplarını birleştirir
	 * @param grup1
	 * @param grup2
	 * @throws Exception
	 */
	protected void gruplarıBirleştir(int grup1, int grup2) throws Exception{
		if(grup1 == grup2){
			throw new Exception("aynı gruplar bunlar " + grup1 + "\n");
		}
		ArrayList<Integer> birinciGrup = makineGrupları.get(grup1);
		ArrayList<Integer> ikinciGrup = makineGrupları.get(grup2);
		if(dendogramVarMı){
			ArrayList<Integer> yeniGruplananlar = new ArrayList<Integer>();
			yeniGruplananlar.add(grup1);
			yeniGruplananlar.add(grup2);
			gruplananlar.add(yeniGruplananlar);
		}
		yeniGrubuEkle(birinciGrup, ikinciGrup);
		eskiGruplarıSil(grup1, grup2);
	}
	private void eskiGruplarıSil(int grup1, int grup2) {
		if(grup1 < grup2){ // grup1 küçük
			makineGrupları.remove(grup1); // ilk önce o aradan çekilsin
			makineGrupları.remove(grup2-1); // sonra grup1 gittiği için grup2 bir geriye kaydı
		}
		else{
			makineGrupları.remove(grup2);
			makineGrupları.remove(grup1-1);
		}
	}
	private void yeniGrubuEkle(ArrayList<Integer> birinciGrup, ArrayList<Integer> ikinciGrup) {
		ArrayList<Integer> yeniGrup = new ArrayList<Integer>();
		yeniGrup.addAll(birinciGrup);
		yeniGrup.addAll(ikinciGrup);
		makineGrupları.add(yeniGrup);
	}
    
	protected int makineninGrubunuBul(int makineNo) throws Exception{
		int grupNo,grupSayısı = makineGrupları.size();
		int makineninGrubu = -1;
		for(grupNo=0;grupNo<grupSayısı;grupNo++){
			makineninGrubu = makineyiGruptaAra(makineNo, grupNo, makineninGrubu);
		}
		return makineVarMıBak(makineninGrubu);
	}
	private int makineyiGruptaAra(int makineNo, int grupNo, int makineninGrubu) {
		ArrayList<Integer> grup = makineGrupları.get(grupNo);
		if(grup.contains(makineNo)){
			makineninGrubu = grupNo;
		}
		return makineninGrubu;
	}
	private int makineVarMıBak(int makineninGrubu) throws Exception {
		if(makineninGrubu == -1){
			throw new Exception("makine bulunamadı \n");
		}
		else{
			return makineninGrubu;
		}
	}

	
    protected int parçaSayısı(){
    	return parçaMakine.sutunSay();
    }
    protected int makineSayısı(){
    	return parçaMakine.satırSay();
    }

    protected String makineGruplarınıYaz(){
    	int satırNo;
    	String yazı = "";
    	for(satırNo=0;satırNo<makineGrupları.size();satırNo++){
    		yazı += makineGrupları.get(satırNo).toString()+ "\n"; 
    	}
        return yazı;
    }
    protected int makineGruplarıTekBoyutluErişim(int makineYeri) throws Exception{
    	int grupNo,birikim = 0;
    	for(grupNo=0;grupNo<makineGrupları.size();grupNo++){
    		for(int yer = 0;yer < makineGrupları.get(grupNo).size();yer ++){
    			if(makineYeri == birikim){
    				return makineGrupları.get(grupNo).get(yer);
    			}
    			else{
    				birikim ++;
    			}
    		}
    	}
    	throw new Exception("makine bulunamadı\n girilen makine yeri : " + makineYeri + "\n\ntüm gruplar \n\n" + makineGruplarınıYaz() + "\n");
    }
    protected String [] makineİsimleriSıralı(){
    	int satırNo,makNo,elemaNo=0;
    	String [] sıralı = new String [makineİsimleri.length];
    	for(satırNo=0;satırNo<makineGrupları.size();satırNo++){
    		ArrayList<Integer> yeniGrup = makineGrupları.get(satırNo);
    		for(makNo=0;makNo<yeniGrup.size();makNo++){
    			sıralı [elemaNo] = makineİsimleri[yeniGrup.get(makNo)];
    			elemaNo++;
    		}
    	}
    	return sıralı;
    }
    
    
    protected String parçaGruplarınıYaz(){
    	int satırNo;
    	String yazı = "";
    	for(satırNo=0;satırNo<parçaGrupları.size();satırNo++){
    		yazı += parçaGrupları.get(satırNo).toString()+ "\n"; 
    	}
        return yazı;
    }
    protected int parçaGruplarıTekBoyutluErişim(int parçaYeri) throws Exception{
    	int grupNo,birikim = 0;
    	for(grupNo=0;grupNo<parçaGrupları.size();grupNo++){
    		for(int yer = 0;yer < parçaGrupları.get(grupNo).size();yer ++){
    			if(parçaYeri == birikim){
    				return parçaGrupları.get(grupNo).get(yer);
    			}
    			else{
    				birikim ++;
    			}
    		}
    	}
    	throw new Exception("parça bulunamadı\n girilen parça yeri : " + parçaYeri + "\n\ntüm gruplar \n\n" + parçaGruplarınıYaz() + "\n");
    }
    // }} gruplar
    
    // {{ yeni parça makine matrisi yapma
    /**
     * ilk önce satırları alır ve satırların sırlamış halini bulur sonra da  derece sıra ile sutunları sıralar
     * @return
     * @throws Exception
     */
    protected BoolDizi yeniParçaMakineMatrisiYap() throws Exception{
    	BoolDizi yeni = new BoolDizi(parçaMakine.satırSay(), parçaMakine.sutunSay(), false);
    	int satırNo;
    	for(satırNo=0;satırNo<yeni.satırSay();satırNo++){
    		int makineNo = makineGruplarıTekBoyutluErişim(satırNo);
    		yeni.matris[satırNo] = parçaMakine.satır(makineNo);
    	}
    	DereceSıra sutunlar = new DereceSıra(yeni, makineİsimleri, parçaİsimleri);
    	sutunlar.sutunlarıSırala();
    	return yeni;
    }
    /**
     * derece sıra kullanmaz onun yerine hazır olan parça gruplarını kullanır
     * @return
     * @throws Exception 
     */
    
    protected BoolDizi yeniParçaMakineMatrisiYap2() throws Exception{
    	if(parçaGrupları.size() == 0){ // yöntem kendisi parça grubu yapamıyorsa bu uygulanır
    		parçaGruplarınıBoşDoldur();
    		parçaGruplarınıOluştur();
    	}
    	int satırNo,sutunNo;
    	BoolDizi yeni = new BoolDizi(makineSayısı(), parçaSayısı(), false);
    	for(satırNo=0;satırNo<yeni.satırSay();satırNo++){
    		int makine = makineGruplarıTekBoyutluErişim(satırNo);
    		for(sutunNo=0;sutunNo<yeni.sutunSay();sutunNo++){
    			int parça = parçaGruplarıTekBoyutluErişim(sutunNo);
    			boolean yeniDeğer = parçaMakine.eriş(makine, parça);
    			yeni.değiş(satırNo, sutunNo, yeniDeğer);
    		}
    	}
    	return yeni;
    }
    /**
     * başlıklarını kullanarak yazar parçalar grubunu kullanır
     * @return
     * @throws Exception 
     */
    protected String yeniParçaMakineMatrisiYaz2() throws Exception{
    	String yazı = "";
    	BoolDizi yeniMatris = yeniParçaMakineMatrisiYap2();
    	yazı += yeniMatrisParçalarıYaz(yeniMatris);
    	yazı += yeniMatrisSatırlarıYaz( yeniMatris);
    	return yazı;    
    }
	private String yeniMatrisSatırlarıYaz( BoolDizi yeniMatris) throws Exception {
		String yazı = "";
		for(int satırNo=0;satırNo<yeniMatris.satırSay();satırNo++){
    		yazı += yeniMatrisSatırYaz(satırNo, yeniMatris);
    	}
		return yazı;
	}
	private String yeniMatrisParçalarıYaz(BoolDizi yeniMatris)throws Exception {
		String yazı = "\t";
		for(int sutunNo = 0;sutunNo<yeniMatris.sutunSay();sutunNo++){
    		int yer = parçaGruplarıTekBoyutluErişim(sutunNo);
    		yazı += parçaİsimleri[yer] + "\t";
    	}
    	yazı += "\n";
		return yazı;
	}
	private String yeniMatrisSatırYaz( int satırNo,BoolDizi yeniMatris)throws Exception {
		String yazı = "";
		int makine = makineGruplarıTekBoyutluErişim(satırNo);
		yazı += makineİsimleri[makine] + "\t";
		for(int sutunNo=0;sutunNo<yeniMatris.sutunSay();sutunNo++){
			yazı += boolDeğerYaz(yeniMatris, satırNo, sutunNo);
		}
		yazı += "\n";
		return yazı;
	}
	private String boolDeğerYaz(BoolDizi yeniMatris, int makine,int parça) throws Exception {
		String yazı = "";
		if(yeniMatris.eriş(makine, parça)){
			yazı += "1\t";
		}
		else{
			yazı += "\t";
		}
		return yazı;
	}
    
	// }} yeni parça makine matrisi yapma
    // {{ kümeleme performans ölçümü için kullanılan yöntemler
	
	// bir kümenin elemanları ile merkezi arasındaki uzaklığı bulma
    private double ortalamaKümeİçiUzaklıklarHesapla() throws Exception{
    	double ortalamaKümelerİçiUzaklık = 0;
    	int kümeNo,kümeSayısı = makineGrupları.size() ;
    	for(kümeNo=0;kümeNo<kümeSayısı;kümeNo++){
    		ortalamaKümelerİçiUzaklık += kümeninElemanlarınaUzaklığı(kümeNo);
    	}
    	ortalamaKümelerİçiUzaklık /= kümeSayısı;
    	return ortalamaKümelerİçiUzaklık;
    }
    /**
     * bu işlemin kullanılabilmesi için kümeler dolu olmalıdır
     * @param kümeNo
     * @return
     * @throws Exception 
     */
    private float kümeninElemanlarınaUzaklığı(int kümeNo) throws Exception{
    	float uzaklık = 0;
    	int elemanNo;
    	ArrayList<Integer> küme = makineGrupları.get(kümeNo);
    	int elemanSayısı = küme.size();
    	for(elemanNo=0;elemanNo<elemanSayısı;elemanNo++){
    		uzaklık += uzaklıkHesapla(kümeNo, elemanNo, küme);
    	}
    	return uzaklık;
    }
	private float uzaklıkHesapla(int kümeNo,  int elemanNo,ArrayList<Integer> küme) throws Exception {
		float uzaklık = 0;
		int makineNo = küme.get(elemanNo);
		BoolVektör makineVektörü = parçaMakine.satır(makineNo);
		DoubleVektör kümeMerkezi = kümeMerkezleri.dizi[kümeNo];
		uzaklık += makineVektörü.öklidUzaklığıHesapla(kümeMerkezi);
		return uzaklık;
	}
	private void kümeMerkezleriniBul() throws Exception{
		kümeMerkezleri = new DoubleDizi(makineGrupları.size());
		for(int kümeNo=0;kümeNo<kümeMerkezleri.satırsay();kümeNo++){
			kümeMerkezleri.dizi[kümeNo] = kümeMerkeziHesapla(kümeNo);
		}
	}
	private DoubleVektör kümeMerkeziHesapla(int kümeNo) throws Exception{
		int sutunNo;
		DoubleVektör merkez = new DoubleVektör(makineGrupları.get(kümeNo).size());
		for(sutunNo=0;sutunNo<merkez.boy();sutunNo++){
			double toplam = merkezinBirElemanınıHesapla(kümeNo, sutunNo);
			merkez.değiş(sutunNo, toplam/merkez.boy());
		}
		return merkez;
	}
	private double merkezinBirElemanınıHesapla(int kümeNo, int sutunNo)throws Exception {
		ArrayList<Integer> küme = makineGrupları.get(kümeNo);
		int kümedekiElemanSayısı = küme.size();
		return merkezElemanıHesapla(sutunNo, küme, kümedekiElemanSayısı);
	}
	private double merkezElemanıHesapla(int sutunNo, ArrayList<Integer> küme,int kümedekiElemanSayısı ) throws Exception {
		double toplam = 0;
		for(int kümeElemanı = 0;kümeElemanı<kümedekiElemanSayısı;kümeElemanı++){
			toplam += toplamıArtır(sutunNo, küme,  kümeElemanı);
		}
		return toplam;
	}
	private double toplamıArtır(int sutunNo, ArrayList<Integer> küme, int kümeElemanı) throws Exception {
		double toplam = 0;
		int satırNo = küme.get(kümeElemanı);
		if(parçaMakine.eriş(satırNo, sutunNo)){
			toplam ++;
		}
		return toplam;
	}
	
	// }} kümeleme performans ölçümü için kullanılan yöntemler

	// {{ parça grupları 
	private int parçanınKümedeKaçKezİşlendiğiniBul(int parça,int grup) throws Exception{
		int işlenmeSayısı = 0;
		ArrayList<Integer> küme = makineGrupları.get(grup);
		for(int elemanNo = 0;elemanNo<küme.size();elemanNo++){
			if(parçaMakine.eriş(küme.get(elemanNo), parça)){
				işlenmeSayısı++;
			}
		}
		return işlenmeSayısı;
	}
	private int parçanınEnÇokİşlendiğiKümeyiBul(int parça) throws Exception{
		if(makineGrupları.size() == 1){
			return 0;
		}
		else{
			return enÇokİşlenenKümeyiBul(parça);
		}
	}
	private int enÇokİşlenenKümeyiBul(int parça) throws Exception {
		int enÇokİşlenenKüme = 0;
		int enÇokİşlenme = parçanınKümedeKaçKezİşlendiğiniBul(parça, 0);
		for(int kümeNo = 0;kümeNo< makineGrupları.size();kümeNo++){
			int yeniİşlenmeSayısı = parçanınKümedeKaçKezİşlendiğiniBul(parça, kümeNo);
			if(yeniİşlenmeSayısı > enÇokİşlenme ){
				enÇokİşlenme = yeniİşlenmeSayısı;
				enÇokİşlenenKüme = kümeNo;
			}
		}	
		return enÇokİşlenenKüme;
	}
    private void parçayıEnÇokİşlenenKümeyeAta(int parçaNo) throws Exception{
    	int atanacakKüme = parçanınEnÇokİşlendiğiKümeyiBul(parçaNo);
    	ArrayList<Integer> parçaKümesi = parçaGrupları.get(atanacakKüme);
    	parçaKümesi.add(parçaNo);
    }
    /**
     * makine grubu sayısı kadar parça grubu oluşturur
     */
    private void parçaGruplarınıBoşDoldur(){
    	for(int grup = 0;grup < makineGrupları.size();grup++){
    		ArrayList<Integer> yeniKüme = new ArrayList<Integer>();
    		parçaGrupları.add(yeniKüme);
    	}
    }

    /**
     * ilk önce kümeleri boş doldurur sonra da gerekli parçaları atar
     * @throws Exception 
     */
    private void parçaGruplarınıOluştur() throws Exception{
    	for(int parçaNo = 0;parçaNo<parçaSayısı();parçaNo++){
    		parçayıEnÇokİşlenenKümeyeAta(parçaNo);
    	}
    }
    // }} parça gruplar
    
    
    // {{ dendogram çizdirme
       public void dendogramçiz(){
    	   Dendogram çizelge = new Dendogram(makineSayısı(), 500, 500, 10, gruplananlar, makineGrupları);
       }
    
    // }} dendogram çizdirme
}
