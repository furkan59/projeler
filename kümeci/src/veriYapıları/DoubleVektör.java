package veriYap�lar�;

import java.util.ArrayList;

public class DoubleVekt�r 
{
	public double [] dizi ;
	
	
	public DoubleVekt�r(int boy) throws Exception 
	{
		diziOlu�turmaBoyutKontrol(boy);
		dizi =  new double[boy];
	}
	private void diziOlu�turmaBoyutKontrol(int boy) throws Exception {
		if(boy <= 0 )
		{
			throw new Exception("uygunsuz boy girilen boy = " + boy);
		}
	}
    public DoubleVekt�r(String virg�llleAyr�lm��) throws Exception
    {
    	bo�MuKontrol(virg�llleAyr�lm��);
    	String [] b�l�nm��String = virg�llleAyr�lm��.split(",");
    	virg�lVarm�Kontrol(b�l�nm��String);
    	int elemanNo;
    	int dizininBoyu = b�l�nm��String.length;
    	dizi =  new double [dizininBoyu];
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		dizi[elemanNo] = Double.parseDouble(b�l�nm��String[elemanNo]);
    	}
    }
	private void bo�MuKontrol(String virg�llleAyr�lm��) throws Exception {
		if(virg�llleAyr�lm��.length() == 0)
    	{
    		throw new Exception("girlen yaz� bo� ! ");
    	}
	}
	private void virg�lVarm�Kontrol(String[] b�l�nm��String) throws Exception {
		if(b�l�nm��String.length == 0)
    	{
    		throw new Exception("girlen yaz� virg�l i�ermiyor ");
    	}
	}
    public DoubleVekt�r(int birimVekt�rBoyu,int say�n�nYeri,double say�) throws Exception
    {
    	if(say�n�nYeri < 0 || say�n�nYeri > birimVekt�rBoyu-1)
    	{
    		throw new Exception("say�n�n yeri vekt�r d���nda say�n� yeri = " + say�n�nYeri + "vekt�r�n boyutu = " + birimVekt�rBoyu);
    	}
    	dizi = new double[birimVekt�rBoyu];
    	int elemanNo;
    	dizi [say�n�nYeri] = say�; // bir konuldu
    	for(elemanNo=0;elemanNo<say�n�nYeri;elemanNo++)
    	{
    		dizi[elemanNo] = 0;
    	}// birin oldu�u yere kadar ki k�s�m tamam
    	for(elemanNo= say�n�nYeri+1;elemanNo<dizi.length;elemanNo++)
    	{
    		dizi[elemanNo] = 0;
    	}
    }
    public DoubleVekt�r (int vekt�rBoyu,double say�) throws Exception
    {
    	diziOlu�turmaBoyutKontrol(vekt�rBoyu);
    	dizi =  new double[vekt�rBoyu];
    	int elemanNo;
    	for(elemanNo=0;elemanNo<vekt�rBoyu;elemanNo++)
    	{
    		dizi [elemanNo] = say�;
    	}
    }
    public DoubleVekt�r (String yaz�,String ay�ra�){
    	String [] b�l�nm��String = yaz�.split(ay�ra�);
    	int elemanNo;
    	int dizininBoyu = b�l�nm��String.length;
    	dizi =  new double [dizininBoyu];
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		dizi[elemanNo] = Double.parseDouble(b�l�nm��String[elemanNo]);
    	}
    }

    
    
    public void rassalDoldur() throws Exception{
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDe�er = Math.random();
            de�i�(elemanNo, yeniDe�er);
    	}
    }
    public int boy (){
    	return dizi.length;
    }
    
    
    
    // yazd�rma 
    public String yaz(String arayaGirenKarakter)
    {
    	if(dizi.length == 0)
    	{
    		return "";
    	}
    	else
    	{
    		return bo�OlmayanDiziYaz(arayaGirenKarakter);
    	}
    }
	private String bo�OlmayanDiziYaz(String arayaGirenKarakter)
	{
		int elemanNo;	
		String yaz� = "";
		for(elemanNo=0;elemanNo<dizi.length-1;elemanNo++)
		{
			yaz� += dizi[elemanNo] + arayaGirenKarakter;
		}
		yaz� += dizi[elemanNo];
		return yaz�;
	}
    // yazd�rma biti�
	
	// matematik i�lemleri
	public void topla (DoubleVekt�r eklenecek) throws Exception
	{
		dizi��lemBoyutKontrol(eklenecek);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo] + eklenecek.dizi[elemanNo];
		}
	}
	private void dizi��lemBoyutKontrol(DoubleVekt�r eklenecek) throws Exception 
	{
		if(eklenecek.dizi.length != dizi.length)
		{
			throw new Exception("i�leme giren dizinin boyutu tutmuyor gelen dizinin boyutu = "
					+ eklenecek.dizi.length + " yap�daki dizi boyutu = " + dizi.length);
		}
	}
    public void �arpVeTopla(DoubleVekt�r eklenecek ,double katsay�) throws Exception
    {
    	dizi��lemBoyutKontrol(eklenecek);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo] + eklenecek.dizi[elemanNo]*katsay�;
		}
    }
	public double vekt�rel�arp(DoubleVekt�r �arp�lacak) throws Exception 
	{
		dizi��lemBoyutKontrol(�arp�lacak);
		int elemanNo;
		double �arp�m = 0;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			if(eri�(elemanNo) != 0 && �arp�lacak.eri�(elemanNo) != 0){
				�arp�m +=  dizi[elemanNo] * �arp�lacak.dizi[elemanNo];	
			}
		}
		return �arp�m;
	}
    public void ��kar(DoubleVekt�r ��kar�lacak) throws Exception 
    {
    	dizi��lemBoyutKontrol(��kar�lacak);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo] - ��kar�lacak.dizi[elemanNo];
		}
    }
    public void b�l (double b�len) throws Exception
    {
    	b�lenS�f�rM�Kontrol(b�len);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] = dizi[elemanNo]/b�len;
		}
    }
	private void b�lenS�f�rM�Kontrol(double b�len) throws Exception {
		if(b�len == 0)
    	{
    		throw new Exception("b�len s�f�r olamaz");
    	}
	}
    public void �arp(double �arpan)
    {
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.length;elemanNo++)
		{
			dizi[elemanNo] *= �arpan;
		}
    }
    public void ��kar(double say�){
    	int elemanNo;
    	for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		dizi[elemanNo] = dizi[elemanNo] - say�;
    	}
    }
    public DoubleVekt�r ��kar2(DoubleVekt�r ��kan) throws Exception{
    	dizi��lemBoyutKontrol(��kan);
    	DoubleVekt�r sonu� = new DoubleVekt�r(boy());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDe�er = eri�(elemanNo) - ��kan.eri�(elemanNo);
    		sonu�.de�i�(elemanNo, yeniDe�er);
    	}
    	return sonu�;
    }
    // matematik i�lemleri
    
    // se�me 
    public int enK�����nYeri() throws Exception{
    	int elemanNo,enK�����nYeri=0;
    	for(elemanNo = 0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[enK�����nYeri] > dizi[elemanNo]){
    			enK�����nYeri = elemanNo;
    		}
    	}
    	return enK�����nYeri;
    }
    public int enB�y���nYeri()throws Exception{
    	int elemanNo,enB�y���nYeri=0;
    	for(elemanNo = 0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[enB�y���nYeri] < dizi[elemanNo]){
    			enB�y���nYeri = elemanNo;
    		}
    	}
    	return enB�y���nYeri;
    }
    public int enK�����nYeri(int nereyeKadarBak�ls�n) throws Exception{
    	int elemanNo,enK�����nYeri=0;
    	for(elemanNo = 0;elemanNo<nereyeKadarBak�ls�n;elemanNo++){
    		if(dizi[enK�����nYeri] > dizi[elemanNo]){
    			enK�����nYeri = elemanNo;
    		}
    	}
    	return enK�����nYeri;
    }
    public int enB�y���nYeri(int nereyeKadarBak�ls�n)throws Exception{
    	int elemanNo,enB�y���nYeri=0;
    	for(elemanNo = 0;elemanNo<nereyeKadarBak�ls�n;elemanNo++){
    		if(dizi[enB�y���nYeri] < dizi[elemanNo]){
    			enB�y���nYeri = elemanNo;
    		}
    	}
    	return enB�y���nYeri;
    }
    
    public double enB�y�k() throws Exception{
    	return dizi[enB�y���nYeri()];
    }
    public double enK���k() throws Exception{
    	return dizi[enK�����nYeri()];
    }
    public Boolean s�f�rdanK���kVarM�(){
    	int elemanNo;
    	Boolean s�f�rdanK���kVarM�=false;
    	for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[elemanNo]< 0){
    			s�f�rdanK���kVarM� = true;
    			break;
    		}
    	}
    	return s�f�rdanK���kVarM�;
    }
    public Boolean s�f�rdanK���kVarM�(int nereyeKadar){
    	int elemanNo;
    	Boolean s�f�rdanK���kVarM�=false;
    	for(elemanNo=0;elemanNo<nereyeKadar;elemanNo++){
    		if(dizi[elemanNo]< 0){
    			s�f�rdanK���kVarM� = true;
    			break;
    		}
    	}
    	return s�f�rdanK���kVarM�;
    }
    public Boolean s�f�rdanB�y�kVarM�(){
    	int elemanNo;
    	Boolean s�f�rdanB�y�kVarM�=false;
    	for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
    		if(dizi[elemanNo] > 0){
    			s�f�rdanB�y�kVarM� = true;
    			break;
    		}
    	}
    	return s�f�rdanB�y�kVarM�;
    }
    public Boolean s�f�rdanB�y�kVarM�(int nereyeKadar){
    	int elemanNo;
    	Boolean s�f�rdanB�y�kVarM�=false;
    	for(elemanNo=0;elemanNo<nereyeKadar;elemanNo++){
    		if(dizi[elemanNo] > 0){
    			s�f�rdanB�y�kVarM� = true;
    			break;
    		}
    	}
    	return s�f�rdanB�y�kVarM�;
    }

    public double eri�(int elemanNo) throws Exception{
    	eri�imiKontrolEt(elemanNo);
    	return dizi[elemanNo];
    }
	private void eri�imiKontrolEt(int elemanNo) throws Exception {
		if(elemanNo < 0 || elemanNo > boy()-1){
    		throw new Exception("eri�im hatas� girilen elemanNo : " + elemanNo + " dizinin boyu : " + boy());
    	}
	}
    public void de�i�(int elemanNo,double yeniDe�er) throws Exception{
    	eri�imiKontrolEt(elemanNo);
    	dizi[elemanNo] = yeniDe�er;
    }
    
    public DoubleVekt�r Vekt�r�ek (int ba�,int son) throws Exception{
    	DoubleVekt�r �ekilen = new DoubleVekt�r(son-ba�+1);
    	int elemanNo;
    	for(elemanNo=ba�;elemanNo<=son;elemanNo++){
    		�ekilen.de�i�(elemanNo-ba�, eri�(elemanNo));
    	}
    	return �ekilen;
    }
    // se�me

    public int ka�TaneVar(double say�){
    	int elemanNo=0,bulunan = 0;
    	for(;elemanNo<dizi.length;elemanNo++){
    		if(dizi[elemanNo] == say�){
    			bulunan ++;
    		}
    	}
    	return bulunan;
    }
    public ArrayList<Integer> konumlar(double say�){
         ArrayList<Integer> konumlar =  new ArrayList<Integer>();
         int elemanNo;
         for(elemanNo=0;elemanNo<dizi.length;elemanNo++){
        	 if(dizi[elemanNo] == say� ){
        		 konumlar.add(elemanNo);
        	 }
         }
         return konumlar;
    }
    
    public Boolean elemandan�tesiBirimMi(int ba�lang��){
    	int elemanNo;
    	int birSay�s� =0,s�f�rsay�s� = 0;
    	for(elemanNo=ba�lang��;elemanNo<boy();elemanNo++){
    		if((dizi[elemanNo] != 0 && dizi[elemanNo] != 1) || birSay�s� > 1 || s�f�rsay�s� + ba�lang�� + 1 > boy() ){
    			return false;
    		}
    		birSay�s� = birSay�s�n�Art�r(elemanNo, birSay�s�);
    		s�f�rsay�s� = s�f�rSay�s�n�Art�r(elemanNo, s�f�rsay�s�);
    	}
    	return birimMiKontrolEt(ba�lang��, s�f�rsay�s�);
    }
	private int s�f�rSay�s�n�Art�r(int elemanNo, int s�f�rsay�s�) {
		if(dizi[elemanNo] == 0){
			return s�f�rsay�s�+1;
		}
		else{
			return s�f�rsay�s�;
		}
	}
	private int birSay�s�n�Art�r(int elemanNo, int birSay�s�) {
		if(dizi[elemanNo ] == 1){
			return birSay�s�+1;
		}
		return birSay�s�;
	}
	private Boolean birimMiKontrolEt(int ba�lang��, int s�f�rsay�s�) {
		if(s�f�rsay�s�  + ba�lang�� + 1 == boy() ){
    		return true;
    	}
    	else {
    		return false;
    	}
	}
    public Boolean birimMi(){
    	int s�f�rlar=0,birler=0,elemanNo=0;
    	for(;elemanNo<boy();elemanNo++){
    		if(dizi[elemanNo] == 0){
    			s�f�rlar++;
    		}
    		else if(dizi[elemanNo] == 1){
    			birler++;
    		}
    		else if(birler > 1){
    			return false;
    		}
    		else {
    			return false;
    		}
    	}
    	if(s�f�rlar == boy() -1){
    		return true;
    	}
    	else {
    		return false;
    	}
    }
	
	
    public DoubleVekt�r kopya() throws Exception{
    	int elemanNo;
    	DoubleVekt�r kopya =  new DoubleVekt�r(boy());
    	for(elemanNo=0;elemanNo< boy();elemanNo++){
    		kopya.dizi[elemanNo] = dizi[elemanNo];
    	}
    	return kopya;
    }

    /**
     * e�ik de�erden k���k olanlar� s�f�rlar
     * @param e�ikDe�er
     * @throws Exception 
     */
    public void indirge(double e�ikDe�er) throws Exception{
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		if(eri�(elemanNo) < e�ikDe�er){
    			de�i�(elemanNo, 0);
    		}
    	}
    }
    
    public void yerle�(int ba�lang��,int son,DoubleVekt�r vekt�r) throws Exception{
    	ba�lang�c�KontrolEt(ba�lang��);
    	gelenVekt�r�KontrolEt(ba�lang��, son, vekt�r);
    	int elemanNo;
    	for(elemanNo=ba�lang��;elemanNo<=son;elemanNo++){
    		de�i�(elemanNo, vekt�r.eri�(elemanNo-ba�lang��));
    	}
    }
	private void gelenVekt�r�KontrolEt(int ba�lang��, int son,DoubleVekt�r vekt�r) throws Exception {
		if(vekt�r.boy() + ba�lang�� > boy() || vekt�r.boy() > son -ba�lang�� + 1){
    		throw new  Exception("gelen vekt�r �ok b�y�k vekt�r�n boyu : "  + vekt�r.boy()  + " ba�lang�� : " + ba�lang�� + " son : "+ son);
    	}
	}
	private void ba�lang�c�KontrolEt(int ba�lang��) throws Exception {
		if(ba�lang�� <0 || ba�lang��  > boy()-1){
    		throw new Exception("ge�ersiz ba�lang�� de�eri ba�lang�� : " + ba�lang�� + " boy : "+ boy());
    	}
	}

    public double �klidUzakl���Hesapla(DoubleVekt�r ikinci) throws Exception{
    	double uzakl�k = 0;
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double fark = eri�(elemanNo) - ikinci.eri�(elemanNo);
    		uzakl�k += fark*fark;
    	}
    	return Math.sqrt(uzakl�k);
    }
    public double �klidUzakl���Hesapla(BoolVekt�r ikinci) throws Exception {
    	double uzakl�k = 0;
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double fark;
    		fark = fark�Bul(ikinci, elemanNo);
    		uzakl�k += fark*fark;
    	}
    	return uzakl�k;
    }
	private double fark�Bul(BoolVekt�r ikinci, int elemanNo) throws Exception {
		double fark;
		Boolean ikincininEleman� = ikinci.eri�(elemanNo);
		if(ikincininEleman�){
			fark = 1- eri�(elemanNo);
		}
		else {
			fark = eri�(elemanNo);
		}
		return fark;
	}


}