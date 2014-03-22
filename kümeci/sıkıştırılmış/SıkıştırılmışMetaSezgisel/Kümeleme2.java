package S�k��t�r�lm��MetaSezgisel;

import java.util.ArrayList;

import S�k��t�r�lm��VeriYap�lar�.BoolEksikMatrisA�a�l�;
import S�k��t�r�lm��VeriYap�lar�.IntAramaA�ac�2;
import veriYap�lar�.BoolDizi;
import veriYap�lar�.BoolVekt�r;

public class K�meleme2 {
	protected BoolEksikMatrisA�a�l� par�aMakine;
	protected String [] makine�simleri;
	protected String [] par�a�simleri;
	protected ArrayList<ArrayList<String>> makineGruplar� = new ArrayList<ArrayList<String>>();
	
	public K�meleme2(BoolEksikMatrisA�a�l� par�aMakine,String [] makine�simleri,String [] par�a�simleri) {
		this.par�a�simleri = par�a�simleri;
		this.makine�simleri = makine�simleri;
		this.par�aMakine = par�aMakine;
		gruplar�Olu�tur();
	}
	private void gruplar�Olu�tur() {
		int elemanNo;
		for(elemanNo = 0; elemanNo < makine�simleri.length ; elemanNo++){
			ArrayList<String> yeniGrup = new ArrayList<String>();
			yeniGrup.add(makine�simleri[elemanNo]);
			makineGruplar�.add(yeniGrup);
		}
	}
	/**
	 * makine isimleri; 
	 * par�a  isimleri;
	 * par�a - makine Matrisi
	 * @param yaz�
	 * @throws Exception 
	 */
	public K�meleme2(String yaz�,char ay�r�c�) throws Exception{
		String [] b�l�nm�� = yaz�.split(";");
		String  ay�ra� = "" + ay�r�c�;
		makine�simleriniAl(b�l�nm��, ay�ra�);
		par�a�simleriniAl(b�l�nm��, ay�ra�);
		par�aMakine = new BoolEksikMatrisA�a�l�(b�l�nm��[2], ay�r�c�);
		gruplar�Olu�tur();
	}
	private void par�a�simleriniAl(String[] b�l�nm��, String ay�ra�) {
		par�a�simleri = b�l�nm��[0].split(ay�ra�);
		if(par�a�simleri[0].contains("\n")){
			par�a�simleri[0] = par�a�simleri[0].split("\n")[0];
		}
		if(par�a�simleri[par�a�simleri.length-1].contains("\n")){
			par�a�simleri[par�a�simleri.length-1] = par�a�simleri[par�a�simleri.length-1].split("\n")[0];
				
		}
	}
	private void makine�simleriniAl(String[] b�l�nm��, String ay�ra�) {
		makine�simleri =  b�l�nm��[1].split(ay�ra�);
		if(makine�simleri[0].contains("\n")){
			makine�simleri[0] = makine�simleri[0].split("\n")[0];
		}
		if(makine�simleri[makine�simleri.length-1].contains("\n")){
			makine�simleri[makine�simleri.length-1] =  makine�simleri[makine�simleri.length-1].split("\n")[0];
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
<br>bu �ekilde girilecek
<br> veriler excelden dirke al�nabilir
	 * @param yaz�
	 * @throws Exception 
	 */
	public K�meleme2(String yaz�) throws Exception{
		String [] sat�rlar = yaz�.split("\\n");
		String [] par�alar = sat�rlar[0].split("\t");
		// ilk eleman bo� olacak
		par�a�simleriniAl(par�alar);
		makine�simleri = new String [sat�rlar.length-1];
		par�aMakine = new BoolEksikMatrisA�a�l�(sat�rlar.length -1);
		par�aMakine.sutunSay�s� = par�alar.length;
		 int sat�rNo;
		 for(sat�rNo=1;sat�rNo<sat�rlar.length;sat�rNo++){
			 String [] yeniSat�r = sat�rlar[sat�rNo].split("\t");
			 par�aMakine.sat�rOku(sat�rNo-1, yeniSat�r,1);
			 yeniMakineAl(sat�rNo, yeniSat�r);
		 }
	}
	private void yeniMakineAl(int sat�rNo, String[] yeniSat�r) {
		String yeniMakine = yeniSat�r[0];
		makine�simleri[sat�rNo-1] = yeniMakine;
	}
	private void par�a�simleriniAl(String[] par�alar) {
		par�a�simleri  = new String [par�alar.length-1];
		int par�aNo;
		for(par�aNo=1;par�aNo<par�alar.length;par�aNo++){
			par�a�simleri[par�aNo-1] = par�alar[par�aNo];
		}
	}
    
	
    public BoolEksikMatrisA�a�l� getPar�aMakine() {
		return par�aMakine;
	}
	public void setPar�aMakine(BoolEksikMatrisA�a�l� par�aMakine) {
		this.par�aMakine = par�aMakine;
	}
	public String[] getMakine�simleri() {
		return makine�simleri;
	}
	public void setMakine�simleri(String[] makine�simleri) {
		this.makine�simleri = makine�simleri;
	}
	public String[] getPar�a�simleri() {
		return par�a�simleri;
	}
	public void setPar�a�simleri(String[] par�a�simleri) {
		this.par�a�simleri = par�a�simleri;
	}

	public String yaz() throws Exception{
		String yaz� = "";
 		yaz� = par�alar�Yaz(yaz�);
		yaz� += "\n\n";
		yaz� = makineleriVeMatrisiYaz(yaz�);
		int grupNo;
		for(grupNo=0;grupNo<makineGruplar�.size();grupNo++){
			yaz� += "grup " + (grupNo+1) + " = " + makineGruplar�.get(grupNo).toString() +"\n";
		}
		return yaz�;
	}
	private String par�alar�Yaz(String yaz�) {
		int par�aNo;
 		yaz� += "\t";
 		for(par�aNo=0;par�aNo<par�a�simleri.length;par�aNo++){
 			yaz� += par�a�simleri[par�aNo] + "\t";
 		}
		return yaz�;
	}
	private String makineleriVeMatrisiYaz(String yaz�) throws Exception {
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
			yaz� += makine�simleri[sat�rNo] + "\t" + par�aMakine.dizi[sat�rNo].t�mDe�erleriYaz() + "\n";
		}
		return yaz�;
	}
	
	/**
	 * makine ve ya makine gruplar�n� birle�tirir
	 * @param grup1
	 * @param grup2
	 * @throws Exception
	 */
	protected void gruplar�Birle�tir(int grup1, int grup2) throws Exception{
		if(grup1 == grup2){
			throw new Exception("ayn� gruplar bunlar " + grup1 + "\n");
		}
		ArrayList<String> birinciGrup = makineGruplar�.get(grup1);
		ArrayList<String> ikinciGrup = makineGruplar�.get(grup2);
		yeniGrubuEkle(birinciGrup, ikinciGrup);
		eskiGruplar�Sil(grup1, grup2);
	}
	private void eskiGruplar�Sil(int grup1, int grup2) {
		if(grup1 < grup2){ // grup1 k���k
			makineGruplar�.remove(grup1); // ilk �nce o aradan �ekilsin
			makineGruplar�.remove(grup2-1); // sonra grup1 gitti�i i�in grup2 bir geriye kayd�
		}
		else{
			makineGruplar�.remove(grup2);
			makineGruplar�.remove(grup1-1);
		}
	}
	private void yeniGrubuEkle(ArrayList<String> birinciGrup, ArrayList<String> ikinciGrup) {
		ArrayList<String> yeniGrup = new ArrayList<String>();
		yeniGrup.addAll(birinciGrup);
		yeniGrup.addAll(ikinciGrup);
		makineGruplar�.add(yeniGrup);
	}
    
	protected int makineninGrubunuBul(String isim) throws Exception{
		int grupNo,grupSay�s� = makineGruplar�.size();
		int makineninGrubu = -1;
		for(grupNo=0;grupNo<grupSay�s�;grupNo++){
			makineninGrubu = makineyiGruptaAra(isim, grupNo, makineninGrubu);
		}
		return makineVarM�Bak(makineninGrubu);
	}
	private int makineyiGruptaAra(String isim, int grupNo, int makineninGrubu) {
		ArrayList<String> grup = makineGruplar�.get(grupNo);
		if(grup.contains(isim)){
			makineninGrubu = grupNo;
		}
		return makineninGrubu;
	}
	private int makineVarM�Bak(int makineninGrubu) throws Exception {
		if(makineninGrubu == -1){
			throw new Exception("makine bulunamad� \n");
		}
		else{
			return makineninGrubu;
		}
	}

    protected int par�aSay�s�(){
    	return par�a�simleri.length;
    }
    protected int makineSay�s�(){
    	return makine�simleri.length;
    }
}