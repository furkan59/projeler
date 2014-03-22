package SıkıştırılmışMetaSezgisel;

import java.util.ArrayList;

import SıkıştırılmışVeriYapıları.BoolEksikMatrisAğaçlı;
import SıkıştırılmışVeriYapıları.IntAramaAğacı2;
import veriYapıları.BoolDizi;
import veriYapıları.BoolVektör;

public class Kümeleme2 {
	protected BoolEksikMatrisAğaçlı parçaMakine;
	protected String [] makineİsimleri;
	protected String [] parçaİsimleri;
	protected ArrayList<ArrayList<String>> makineGrupları = new ArrayList<ArrayList<String>>();
	
	public Kümeleme2(BoolEksikMatrisAğaçlı parçaMakine,String [] makineİsimleri,String [] parçaİsimleri) {
		this.parçaİsimleri = parçaİsimleri;
		this.makineİsimleri = makineİsimleri;
		this.parçaMakine = parçaMakine;
		gruplarıOluştur();
	}
	private void gruplarıOluştur() {
		int elemanNo;
		for(elemanNo = 0; elemanNo < makineİsimleri.length ; elemanNo++){
			ArrayList<String> yeniGrup = new ArrayList<String>();
			yeniGrup.add(makineİsimleri[elemanNo]);
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
	public Kümeleme2(String yazı,char ayırıcı) throws Exception{
		String [] bölünmüş = yazı.split(";");
		String  ayıraç = "" + ayırıcı;
		makineİsimleriniAl(bölünmüş, ayıraç);
		parçaİsimleriniAl(bölünmüş, ayıraç);
		parçaMakine = new BoolEksikMatrisAğaçlı(bölünmüş[2], ayırıcı);
		gruplarıOluştur();
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
	public Kümeleme2(String yazı) throws Exception{
		String [] satırlar = yazı.split("\\n");
		String [] parçalar = satırlar[0].split("\t");
		// ilk eleman boş olacak
		parçaİsimleriniAl(parçalar);
		makineİsimleri = new String [satırlar.length-1];
		parçaMakine = new BoolEksikMatrisAğaçlı(satırlar.length -1);
		parçaMakine.sutunSayısı = parçalar.length;
		 int satırNo;
		 for(satırNo=1;satırNo<satırlar.length;satırNo++){
			 String [] yeniSatır = satırlar[satırNo].split("\t");
			 parçaMakine.satırOku(satırNo-1, yeniSatır,1);
			 yeniMakineAl(satırNo, yeniSatır);
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
    
	
    public BoolEksikMatrisAğaçlı getParçaMakine() {
		return parçaMakine;
	}
	public void setParçaMakine(BoolEksikMatrisAğaçlı parçaMakine) {
		this.parçaMakine = parçaMakine;
	}
	public String[] getMakineİsimleri() {
		return makineİsimleri;
	}
	public void setMakineİsimleri(String[] makineİsimleri) {
		this.makineİsimleri = makineİsimleri;
	}
	public String[] getParçaİsimleri() {
		return parçaİsimleri;
	}
	public void setParçaİsimleri(String[] parçaİsimleri) {
		this.parçaİsimleri = parçaİsimleri;
	}

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
			yazı += makineİsimleri[satırNo] + "\t" + parçaMakine.dizi[satırNo].tümDeğerleriYaz() + "\n";
		}
		return yazı;
	}
	
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
		ArrayList<String> birinciGrup = makineGrupları.get(grup1);
		ArrayList<String> ikinciGrup = makineGrupları.get(grup2);
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
	private void yeniGrubuEkle(ArrayList<String> birinciGrup, ArrayList<String> ikinciGrup) {
		ArrayList<String> yeniGrup = new ArrayList<String>();
		yeniGrup.addAll(birinciGrup);
		yeniGrup.addAll(ikinciGrup);
		makineGrupları.add(yeniGrup);
	}
    
	protected int makineninGrubunuBul(String isim) throws Exception{
		int grupNo,grupSayısı = makineGrupları.size();
		int makineninGrubu = -1;
		for(grupNo=0;grupNo<grupSayısı;grupNo++){
			makineninGrubu = makineyiGruptaAra(isim, grupNo, makineninGrubu);
		}
		return makineVarMıBak(makineninGrubu);
	}
	private int makineyiGruptaAra(String isim, int grupNo, int makineninGrubu) {
		ArrayList<String> grup = makineGrupları.get(grupNo);
		if(grup.contains(isim)){
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
    	return parçaİsimleri.length;
    }
    protected int makineSayısı(){
    	return makineİsimleri.length;
    }
}