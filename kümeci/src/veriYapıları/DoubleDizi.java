package veriYap�lar�;


import java.util.ArrayList;

public class DoubleDizi 
{
	public DoubleVekt�r[] dizi;
	private void diziyiDoldur(String[] sat�rlar) throws Exception {
		int sat�rSay�s� = sat�rlar.length;
		if(sat�rlar[0].length() == 0){
			dizi = new DoubleVekt�r[sat�rSay�s�-1];
			ilkElemanBo�kenDizi(sat�rlar, sat�rSay�s�);
		}
		else {
			dizi = new DoubleVekt�r[sat�rSay�s�];
			ilkElemanYokkenDizi(sat�rlar, sat�rSay�s�);
		}
	}
	private void ilkElemanYokkenDizi(String[] sat�rlar, int sat�rSay�s�)throws Exception {
		int elemanNo;
		for(elemanNo=0;elemanNo < sat�rSay�s�;elemanNo++)
		{
			dizi[elemanNo] = new DoubleVekt�r(sat�rlar[elemanNo]);
		}
	}
	private void ilkElemanBo�kenDizi(String[] sat�rlar, int sat�rSay�s�) throws Exception {
		int elemanNo;
		for(elemanNo=1;elemanNo < sat�rSay�s�;elemanNo++)
		{
			dizi[elemanNo-1] = new DoubleVekt�r(sat�rlar[elemanNo]);
		}
	}
    public DoubleDizi (int sat�rSay�s�,int sutunSay�s�,double say�s�) throws Exception{
    	diziOlu�turmBoyutKontrol(sat�rSay�s�, sutunSay�s�);
    	int sat�rNo;
    	dizi = new DoubleVekt�r[sat�rSay�s�];
    	for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
    		dizi[sat�rNo] =  new DoubleVekt�r(sutunSay�s�, say�s�);
    	}
    }
	private void diziOlu�turmBoyutKontrol(int sat�rSay�s�, int sutunSay�s�) throws Exception
    {
		if(sat�rSay�s� <= 0 || sutunSay�s� <= 0)
    	{
    		throw new Exception("boyut uyumsuzlu�u var sat�r say�s� = " + sat�rSay�s� + 
    				"  sutun say�s� = " + sutunSay�s�);
    	}
	}
    public DoubleDizi (int sat�rSay�s�){
    	dizi = new DoubleVekt�r[sat�rSay�s�];
    }
    public DoubleDizi (int sat�rSay�s�,int sutunSay�s�) throws Exception{
    	diziOlu�turmBoyutKontrol(sat�rSay�s�, sutunSay�s�);
    	int sat�rNo;
    	dizi = new DoubleVekt�r[sat�rSay�s�];
    	for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
    		dizi[sat�rNo] =  new DoubleVekt�r(sutunSay�s�);
    	}
    }
    /**
     * 1 2 3 4 
     * <br>4 4 5 6
     * <br>4 5 7 5
     * <br>�eklinde verildi�inde �al���r
     * @param yaz�
     * @throws Exception 
     */
    public DoubleDizi (String yaz�,String ay�ra�) throws Exception{
    	String d�zeltilmi� = "";
    	veriyiD�zenle(yaz�, d�zeltilmi�);
    	String [] sat�rlar = d�zeltilmi�.split(ay�ra�);
    	diziyiDoldur(sat�rlar);
    }
	private void veriyiD�zenle(String yaz�, String d�zeltilmi�) {
		if( yaz�.charAt(0) == '\n'){
    		d�zeltilmi� = yaz�.substring(1);
    	}
    	if (d�zeltilmi�.charAt(d�zeltilmi�.length()-1) == '\n'){
    		d�zeltilmi� = d�zeltilmi�.substring(0,d�zeltilmi�.length()-1);
    	}
	}
    

    
    public void rassalDiziDoldur() throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		DoubleVekt�r yeniSat�r = new DoubleVekt�r(sutunsay());
    		yeniSat�r.rassalDoldur();
    		dizi [sat�rNo] = yeniSat�r;
    	}
    }
	
    
    public int sutunsay()
	{
		return dizi[0].dizi.length;
	}
	public int sat�rsay()
	{
		return dizi.length;
	}
	public String yaz(String arayaGirenKarakter)
	{
		if(sat�rsay()== 0)
		{
			return "";
		}
		else
		{
			String yaz� = bo�OlmayanDiziYaz(arayaGirenKarakter);
			return yaz�;
		}
	}
	private String bo�OlmayanDiziYaz(String arayaGirenKarakter)
	{
		String yaz� = "";
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<dizi.length-1;sat�rNo++)
		{
			yaz� += dizi[sat�rNo].yaz(arayaGirenKarakter) + "\n";
		}
		yaz� += dizi[sat�rNo].yaz(arayaGirenKarakter);
		return yaz�;
	}

	public double eri�(int sat�rNo,int sutunNo) throws Exception
	{
		eri�imSat�rKontroEt(sat�rNo,sutunNo);
		eri�imSutunKontrolEt(sutunNo,sat�rNo);
		return dizi[sat�rNo].dizi[sutunNo];
	}
	private void eri�imSutunKontrolEt(int sutunNo,int sat�rNo) throws Exception {
		if(sutunNo < 0 || sutunNo > dizi[sat�rNo].boy()-1){
			throw new Exception("sutunno dizi s�n�rlar� d���nda sutunno = " + sutunNo + " dizinin sutun say�s� = " + sutunsay()
					+ "Sat�rno = " + sat�rNo);
		}
	}
	private void eri�imSat�rKontroEt(int sat�rNo,int sutunNo) throws Exception {
		if(sat�rNo<0 ||sat�rNo > sat�rsay()-1){
			throw new Exception("sat�rno dizi s�n�rlar� d���nda sat�rno = " + sat�rNo + " dizinin sat�r say�s� = " + sat�rsay()+ 
					"\nSutunNo = " + sutunNo);
		}
	}
	public void de�i�(int sat�rNo,int sutunNo,double yenide�er) throws Exception{
		eri�imSat�rKontroEt(sat�rNo, sutunNo);
		eri�imSutunKontrolEt(sutunNo, sat�rNo);
		dizi[sat�rNo].dizi[sutunNo] = yenide�er;
	}
	// matematiksel i�lemler

	public void sat�rTopla(int �arp�lacakSat�r,int eklenecekSat�r,double katsay� ) throws Exception{
		int sutunNo;
		for(sutunNo=0;sutunNo<sutunsay();sutunNo++){
			double yeniDe�er = eri�(eklenecekSat�r,sutunNo) + eri�(�arp�lacakSat�r,sutunNo)*katsay�;
			de�i�(eklenecekSat�r,sutunNo,yeniDe�er);
		}
	}
	
	public DoubleVekt�r �arp (DoubleVekt�r �arpan) throws Exception
	{
		vekt�relBoyutKontrol(�arpan);
		int elemanNo;
	    DoubleVekt�r cevap = new DoubleVekt�r(dizi.length);	
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			cevap.dizi[elemanNo] = dizi[elemanNo].vekt�rel�arp(�arpan);
		}
		return cevap;
	}
	
    private void vekt�relBoyutKontrol(DoubleVekt�r �arpan) throws Exception 
	{
		if(�arpan.dizi.length == 0 || �arpan.dizi.length != sutunsay())
		{
			throw new Exception("�arpan ile dizi uyumsuz �arpan�n boyu = " + �arpan.dizi.length + 
					" dizinin sutunsay�s� = " + sutunsay());
		}
	}
	
    public void sutun�lePivot(int pivotSat�r,DoubleVekt�r sutun) throws Exception{
    	sutun�lePivotHatalar(pivotSat�r, sutun);
        pivotSat�r�B�l(pivotSat�r, sutun);
    	yard�mc�Sutun�lePivot��lem(pivotSat�r, sutun);
    }
	private void sutun�lePivotHatalar(int pivotSat�r, DoubleVekt�r sutun)throws Exception {
		pivotSat�rKontrol(pivotSat�r);
    	yard�mc�Sutun�leDiziyiKar��la�t�r(sutun);
	}
	private void pivotSat�r�B�l(int pivotSat�r, DoubleVekt�r sutun)throws Exception {
		double b�len = sutun.dizi[pivotSat�r];
    	dizi[pivotSat�r].b�l(b�len);
	}
	private void yard�mc�Sutun�lePivot��lem(int pivotSat�r, DoubleVekt�r sutun)throws Exception {
		if(pivotSat�r == 0)
		{
    		yard�mc�SutunPivot�lkSat�r��in(pivotSat�r, sutun);
		}
		else if(pivotSat�r == sat�rsay()-1)
		{
			yard�mc�SutunPivotSonda(pivotSat�r, sutun);
		}
		else
		{
			yard�mc�SutunPivotArada(pivotSat�r, sutun);
		}
	}
	private void yard�mc�SutunPivotSonda(int pivotSat�r, DoubleVekt�r sutun)throws Exception {
		int sat�r;
		for(sat�r=0;sat�r<sat�rsay()-1;sat�r++)
		{
			if(sutun.dizi[sat�r]!= 0){
			double katsay� = -1 * sutun.dizi[sat�r];
			dizi[sat�r].�arpVeTopla(dizi[pivotSat�r], katsay�);
			}
		}
	}
	private void yard�mc�SutunPivotArada(int pivotSat�r, DoubleVekt�r sutun)throws Exception {
		int sat�r;
		for(sat�r=0;sat�r<pivotSat�r;sat�r++)
		{
			if(sutun.dizi[sat�r]!= 0){
				double katsay� = -1 * sutun.dizi[sat�r];
				dizi[sat�r].�arpVeTopla(dizi[pivotSat�r], katsay�);
			}
		}
		for(sat�r=pivotSat�r+1;sat�r<sat�rsay();sat�r++)
		{
			if(sutun.dizi[sat�r]!= 0){
				double katsay� = -1 * sutun.dizi[sat�r];
				dizi[sat�r].�arpVeTopla(dizi[pivotSat�r], katsay�);
			}
		}
	}
	private void yard�mc�SutunPivot�lkSat�r��in(int pivotSat�r,DoubleVekt�r sutun) throws Exception {
		int sat�r;
		for(sat�r=1;sat�r<sat�rsay();sat�r++)
		{
			if(sutun.dizi[sat�r] != 0){
				double katsay� = -1 * sutun.dizi[sat�r];
				dizi[sat�r].�arpVeTopla(dizi[pivotSat�r], katsay�);
			}
		}
	}
	private void yard�mc�Sutun�leDiziyiKar��la�t�r(DoubleVekt�r sutun)
			throws Exception {
		if(sutun.boy() != sat�rsay()){
    		throw new Exception("yard�mc� sutun ile dizinin boyutlar� uyumsuz boy : "+ sutun.boy() + " dizinin sat�r say�s� : " + sat�rsay());
    	}
	}
	private void pivotSat�rKontrol(int pivotSat�r) throws Exception {
		if(pivotSat�r<0 || pivotSat�r > sat�rsay()-1){
    		throw new Exception("pivot sat�r s�n�rlar d���nda pivot sat�r : " + pivotSat�r + " tablonun sat�rsay�s� : " + sat�rsay() );
    	}
	}
    
    public void pivot(int pivotsat�r,int pivotsutun) throws Exception
	{
		pivotSat�rSutunS�f�rdanK���kM�(pivotsat�r, pivotsutun);
		pivotSat�rSutunS�n�rlarD���ndaM�(pivotsat�r, pivotsutun);
		double pivotEleman = eri�(pivotsat�r,pivotsutun);
		dizi[pivotsat�r].b�l(pivotEleman); 
		pivot��lem(pivotsat�r, pivotsutun);
	}
    private void pivot��lem(int pivotsat�r, int pivotsutun) throws Exception 
	{
		if(pivotsat�r == 0)
		{
			pivotSat�rS�f�r(pivotsat�r, pivotsutun);
		}
		else if(pivotsat�r == sat�rsay()-1)
		{
			pivotSat�rSonda(pivotsat�r, pivotsutun);
		}
		else
		{
			pivotSat�rArada(pivotsat�r, pivotsutun);
		}
	}
    private void pivotSat�rArada(int pivotsat�r, int pivotsutun)throws Exception 
	{
		int sat�r;
		for(sat�r=0;sat�r<pivotsat�r;sat�r++)
		{
			if(eri�(sat�r,pivotsutun)!= 0){
				double katsay� = -1 * eri�(sat�r,pivotsutun);
				dizi[sat�r].�arpVeTopla(dizi[pivotsat�r], katsay�);
			}
		}
		for(sat�r=pivotsat�r+1;sat�r<sat�rsay();sat�r++)
		{
			if(eri�(sat�r,pivotsutun)!= 0){
				double katsay� = -1 * eri�(sat�r,pivotsutun);
				dizi[sat�r].�arpVeTopla(dizi[pivotsat�r], katsay�);
			}
		}
	}
    private void pivotSat�rSonda(int pivotsat�r, int pivotsutun) throws Exception 
	{
		int sat�r;
		for(sat�r=0;sat�r<sat�rsay()-1;sat�r++)
		{
			if(eri�(sat�r,pivotsutun)!= 0){
			double katsay� = -1 * eri�(sat�r,pivotsutun);
			dizi[sat�r].�arpVeTopla(dizi[pivotsat�r], katsay�);
			}
		}
	}	
    private void pivotSat�rS�f�r(int pivotsat�r, int pivotsutun) throws Exception 
	{
		int sat�r;
		for(sat�r=1;sat�r<sat�rsay();sat�r++)
		{
			if(eri�(sat�r,pivotsutun)!= 0){
				double katsay� = -1 * eri�(sat�r,pivotsutun);
				dizi[sat�r].�arpVeTopla(dizi[pivotsat�r], katsay�);
			}
		}
	}
	private void pivotSat�rSutunS�n�rlarD���ndaM�(int sat�rNo, int sutunNo)throws Exception 
	{
		if(sat�rNo > sat�rsay()-1 || sutunNo > sutunsay()-1)
		{
			throw new Exception("sat�rNo ve ya sutunNo dizi d���nda olamaz olamaz sat�rsay�s� = "+ 
		   sat�rsay() + " sutunsay�s� = " + sutunsay() + " girilen sutunNo = " + sutunNo + 
		   " girilen sat�rNo " + sat�rNo );
		}
	}
    private void pivotSat�rSutunS�f�rdanK���kM�(int sat�rNo, int sutunNo)throws Exception 
	{
		if(sat�rNo < 0 || sutunNo < 0)
		{
			throw new Exception("sat�rNo ve ya sutunNo s�f�rdan k���k olamaz sat�rsay�s� = "+ 
		   sat�rsay() + " sutunsay�s� = " + sutunsay() + " girilen sutunNo = " + sutunNo + 
		   " girilen sat�rNo " + sat�rNo );
		}
	}

    public void �arp(double say�){
    	int sat�rNo;
    	for(sat�rNo =0;sat�rNo<sat�rsay();sat�rNo++){
    		dizi[sat�rNo].�arp(say�);
    	}
    }
	public DoubleDizi ��kar(DoubleDizi ��kan) throws Exception{
		DoubleDizi sonu� = new DoubleDizi(sat�rsay());
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
			DoubleVekt�r yeniSat�r = new DoubleVekt�r(sutunsay());
			sonu�.dizi[sat�rNo] = yeniSat�r;
		}
		return sonu�;
		
	}
    public void topla(DoubleDizi toplanan) throws Exception{
    	int sat�rNo;
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		DoubleVekt�r eklenecek = toplanan.dizi[sat�rNo];
    		dizi[sat�rNo].topla(eklenecek);
    	}
    }
    // matematiksel i�lemler

    // yerle�tirme ve de�i�tirme ve �ekme
    
    public void sutunaYerle�(DoubleVekt�r sutun,int sutunNo,int ba�lang��) throws Exception{
    	sutunaYerle�Hata(sutunNo);
    	if(sutun == null){
    		throw new Exception("girilen sutun bo�");
    	}
    	int elemanNo;
    	for(elemanNo=0;elemanNo<sutun.dizi.length;elemanNo++){
    		de�i�(elemanNo+ba�lang��,sutunNo,sutun.dizi[elemanNo]);
    	}
    }
	private void sutunaYerle�Hata(int sutunNo) throws Exception {
		if(sutunNo < 0 )
    	{
    		throw new Exception("sutun no 0 dan k���k olamaz sutun no =" + sutunNo);
    	}
    	if(sutunNo > sutunsay()-1)
    	{
    		throw new Exception("sutun no dizi d���nda olamaz sutun no = "+ sutunNo);
    	}
	}
    public DoubleVekt�r sutuncek(int sutunNo) throws Exception
    {
    	sutunaYerle�Hata(sutunNo);
    	DoubleVekt�r sutun = new DoubleVekt�r(sat�rsay());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<sat�rsay();elemanNo++)
    	{
    		sutun.dizi[elemanNo] = eri�(elemanNo, sutunNo);
    	}
    	return sutun;
    }
    public DoubleVekt�r sutuncek(int sutunNo,int ba�lang��) throws Exception{
    	sutunaYerle�Hata(sutunNo);
    	sutuncekBa�lang��Hatas�(ba�lang��);
    	DoubleVekt�r sutun = new DoubleVekt�r(sat�rsay()-ba�lang��);
    	int elemanNo;
    	for(elemanNo=ba�lang��;elemanNo<sat�rsay();elemanNo++)
    	{
    		sutun.dizi[elemanNo-ba�lang��] = eri�(elemanNo, sutunNo);
    	}
    	return sutun;
    }
	private void sutuncekBa�lang��Hatas�(int ba�lang��) throws Exception {
		if(ba�lang�� < 0 || ba�lang�� > sat�rsay()-1){
    		throw new Exception("ba�lang�� dizi s�n�rlar� d���nda\ndizideki sat�rsay�s� = " + sat�rsay() + "girlen ba�lang�� de�eri" + ba�lang��);
    	}
	}

	
	public DoubleVekt�r sat�rcek(int sat�rNo,int ba�lang��,int son) throws Exception{
		sat�r�ekmeHatalar�Bul(sat�rNo, ba�lang��, son);
		return sat�r�ekme��lemi(sat�rNo, ba�lang��,son);
	}
	private void sat�r�ekmeHatalar�Bul(int sat�rNo, int ba�lang��, int son)throws Exception {
		sat�r�ekHata(sat�rNo);
		sat�r�ekmeBa�lang��Hatas�(ba�lang��);
		sat�r�ekmeSonDe�eriHatas�Bul(son);
	}
	private void sat�r�ekmeSonDe�eriHatas�Bul(int son) throws Exception {
		if(son < 0 || son > sutunsay()-1){
			throw new Exception("girilen son de�eri sat�r�ekme i�in uygunde�il\nson de�eri = " + son + "dizinin sutun say�s� = " + sutunsay());
		}
	}
	private DoubleVekt�r sat�r�ekme��lemi(int sat�rNo, int ba�lang��,int son)throws Exception {
		int elemanNo;
    	DoubleVekt�r sat�r =  new DoubleVekt�r(son - ba�lang��+1);
    	for(elemanNo=ba�lang��;elemanNo<=son;elemanNo++){
    		sat�r.dizi[elemanNo-ba�lang��] = eri�(sat�rNo,elemanNo);
    	}
		return sat�r;
	}
	private void sat�r�ekmeBa�lang��Hatas�(int ba�lang��) throws Exception {
		if(ba�lang�� < 0 || ba�lang�� > sutunsay()-1){
			throw new Exception("sat�rcekme i�in yanl�� ba�lang�� de�eri " + "\ngirilen ba�lang�� de�eri =" + ba�lang�� + 
					"dizideki sutunsay�s� = "  +sutunsay());
		}
	}
    public DoubleVekt�r sat�rcek(int sat�rNo) throws Exception
    {
    	sat�r�ekHata(sat�rNo);
    	DoubleVekt�r sat�r =  new DoubleVekt�r(sutunsay());
    	sat�r�ekme��lemi(sat�rNo, sat�r);
    	return sat�r;
    }
	private void sat�r�ekme��lemi(int sat�rNo, DoubleVekt�r sat�r)throws Exception {
		int elemanNo;
    	for(elemanNo=0;elemanNo<sutunsay();elemanNo++){
    		sat�r.dizi[elemanNo] = eri�(sat�rNo,elemanNo);
    	}
	}
	private void sat�r�ekHata(int sat�rNo) throws Exception {
		if(sat�rNo < 0 )
    	{
    		throw new Exception("sat�r no s�f�rdan k���k olamaz sat�r no = " +  sat�rNo);
    	}
    	if(sat�rNo > sat�rsay()-1)
    	{
    		throw new Exception("sat�r no dizinin s�n�rlar� d���nda olamaz sat�r no = " + sat�rNo);
    	}
	}
   	public void sat�raYerle�(DoubleVekt�r sat�r,int sat�rNo,int ba�lang��) throws Exception
	{
   		gelenVekt�r�KontrolEt(sat�r, ba�lang��);
		int elemanNo;
    	for(elemanNo=0;elemanNo<sat�r.dizi.length;elemanNo++){
    		de�i�(sat�rNo,elemanNo+ba�lang��,sat�r.dizi[elemanNo]);
    	}
	}
	private void gelenVekt�r�KontrolEt(DoubleVekt�r sat�r, int ba�lang��)
			throws Exception {
		if(sat�r.boy() + ba�lang��  > sutunsay()){
   			throw new Exception("gelen vekt�r dizi i�in b�y�k vekt�r�n boyu : " + sat�r.boy() + "\nba�lang�� : " + ba�lang�� + 
   					"\ndizinin sutunsay�s� : "  + sutunsay() );
   		}
	}
	public void sutundanSay���kar(int sutunNo,double say�) throws Exception{
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
			de�i�(sat�rNo,sutunNo,eri�(sat�rNo,sutunNo)-say�);
		}
	}
   	
	public DoubleDizi dizicek(int sat�r1,int sat�r2,int sutun1,int sutun2) throws Exception{
		sat�rlar�Kar��la�t�r(sat�r1, sat�r2);
		sutunlar�Kar��la�t�r(sutun1, sutun2);
		DoubleDizi �ekilen = new DoubleDizi(sat�r2-sat�r1+1, sutun2-sutun1 +1);
		int sat�rNo;
		for(sat�rNo=sat�r1;sat�rNo<=sat�r2;sat�rNo++){
			�ekilen.sat�raYerle�(sat�rcek(sat�rNo, sutun1, sutun2), sat�rNo-sat�r1, 0);
		}
		return �ekilen;
	}
	private void sutunlar�Kar��la�t�r(int sutun1, int sutun2) throws Exception {
		if(sutun2 < sutun1){
			throw new Exception("sutun2 sutun1 den k���k olamaz sutun1 : "  +sutun1 + "sutun2 : " + sutun2);
		}
	}
	private void sat�rlar�Kar��la�t�r(int sat�r1, int sat�r2) throws Exception {
		if(sat�r2 < sat�r1  ){
			throw new Exception("sat�r2 sat�r1 den k���k olamaz sat�r1 : "+ sat�r1 + " sat�r2 : "  + sat�r2);
		}
	}
	/**
	 * e�ik de�erden k���k olanlar� s�f�rlar
	 * @param e�ikDe�er
	 * @throws Exception 
	 */
	public void indirge(double e�ikDe�er) throws Exception{
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
			dizi[sat�rNo].indirge(e�ikDe�er);
		}
	}
	// yerle�tirme ve de�i�tirme ve �ekme
	
	

	public int ka�TaneVar(double say�){
		int sat�rNo=0;
		int bulunan=0;
		for(;sat�rNo<sat�rsay();sat�rNo++){
			bulunan += dizi[sat�rNo].ka�TaneVar(say�);
		}
		return bulunan;
	}
    public ArrayList<ArrayList<Integer>> Ayn�Sat�rdaOlanlar�Bul(double say�) throws Exception{
    	int sat�rNo;
    	ArrayList<ArrayList<Integer>> konumlar = new ArrayList<ArrayList<Integer>>();
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		konumlar.add(dizi[sat�rNo].konumlar(say�));
    	}
    	return konumlar;
    }
    public ArrayList<ArrayList<Integer>> Ayn�SutundaOlanlar�Bul(double say�) throws Exception{
    	int sutunNo;
    	ArrayList<ArrayList<Integer>> konumlar = new ArrayList<ArrayList<Integer>>();
    	for(sutunNo=0;sutunNo<sat�rsay();sutunNo++){
    		konumlar.add(sutuncek(sutunNo).konumlar(say�));
    	}
    	return konumlar;
    }
    public ArrayList<ArrayList<Integer>> konumlar�Bul(double say�) throws Exception{
         int sat�rNo,sutunNo;
         ArrayList<ArrayList<Integer>> konumlar  = new ArrayList<ArrayList<Integer>>();
         for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
        	 for(sutunNo=0;sutunNo<sutunsay();sutunNo++){
        		 ArrayList<Integer> sat�r = new ArrayList<Integer>();
        		 if(eri�(sat�rNo, sutunNo) == say�){
        			 sat�r.add(sat�rNo);
        			 sat�r.add(sutunNo);
        			 konumlar.add(sat�r);
        		 }
        	 }
         }
         return konumlar;
    }

    public DoubleDizi kopya() throws Exception{
    	int sat�rNo,sutunNo;
    	DoubleDizi kopya = new DoubleDizi(sat�rsay(),sutunsay(),0);
    	for(sat�rNo=0;sat�rNo<sat�rsay();sat�rNo++){
    		for(sutunNo=0;sutunNo<sutunsay();sutunNo++){
        		kopya.de�i�(sat�rNo, sutunNo, eri�(sat�rNo,sutunNo));
        	}
    	}
    	return kopya;
    }

}
