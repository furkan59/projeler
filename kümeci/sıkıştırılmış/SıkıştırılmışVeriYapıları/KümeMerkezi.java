package S�k��t�r�lm��VeriYap�lar�;

import java.util.ArrayList;

import veriYap�lar�.DoubleVekt�r;

public class K�meMerkezi {

	public DoubleVekt�r dizi ;
	public double kareleriToplam� = 0;
	
    public K�meMerkezi(String yaz�, String ay�ra�) throws Exception {
    	String [] b�l�nm��String = yaz�.split(ay�ra�);
    	int elemanNo;
    	int dizininBoyu = b�l�nm��String.length;
    	dizi =  new DoubleVekt�r(dizininBoyu);
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		double yeniDe�er = Double.parseDouble(b�l�nm��String[elemanNo]);
    		de�i�(elemanNo, yeniDe�er); 
    		kareleriToplam� += eri�(elemanNo)*eri�(elemanNo);
    	}
	}
	public K�meMerkezi(int vekt�rBoyu, double say�) throws Exception {
		int elemanNo;
		dizi =  new DoubleVekt�r(vekt�rBoyu);
    	for(elemanNo=0;elemanNo<vekt�rBoyu;elemanNo++)
    	{
    		de�i�(elemanNo, say�); 
    	}
    	kareleriToplam� += say�*say�*vekt�rBoyu;
	}
	public K�meMerkezi(int birimVekt�rBoyu, int say�n�nYeri, double say�) throws Exception {
		if(say�n�nYeri < 0 || say�n�nYeri > birimVekt�rBoyu-1)
    	{
    		throw new Exception("say�n�n yeri vekt�r d���nda say�n� yeri = " + say�n�nYeri + "vekt�r�n boyutu = " + birimVekt�rBoyu);
    	}
    	dizi = new DoubleVekt�r(birimVekt�rBoyu);
    	int elemanNo;
    	de�i�(say�n�nYeri, say�); // bir konuldu
    	for(elemanNo=0;elemanNo<say�n�nYeri;elemanNo++)
    	{
    		de�i�(elemanNo, 0);
    	}// birin oldu�u yere kadar ki k�s�m tamam
    	for(elemanNo= say�n�nYeri+1;elemanNo<dizi.boy();elemanNo++)
    	{
    		de�i�(elemanNo, 0);
    	}
    	kareleriToplam� = say�*say�;
	}
	public K�meMerkezi(int boy) throws Exception {
		int elemanNo;
		dizi =  new DoubleVekt�r(boy);
    	for(elemanNo=0;elemanNo<boy;elemanNo++)
    	{
    		dizi.de�i�(elemanNo, 0); 
    	}
	}
	
	public double eri�(int elemanNo) throws Exception{
		return dizi.eri�(elemanNo);
	}
    public void de�i�(int elemanNo,double yeniDe�er) throws Exception{
        eri�imiKontrolEt(elemanNo);
    	double ��kacakDe�er = dizi.eri�(elemanNo)*dizi.eri�(elemanNo);
        kareleriToplam� += yeniDe�er*yeniDe�er -��kacakDe�er;
        dizi.de�i�(elemanNo, yeniDe�er);
    }
	private void eri�imiKontrolEt(int elemanNo) throws Exception {
		if(elemanNo < 0 || elemanNo > dizi.boy()-1){
    		throw new Exception("eri�im hatas� girilen elemanNo : " + elemanNo + " dizinin boyu : " + dizi.boy());
    	}
	}
    public int boy(){
    	return dizi.boy();
    }
	
	 /**
	 * <br> burada �klid uzakl��� farkl� bir �ekilde bulunacak 
	 * <br> normal de a,b,c,d nin 1,0,0,1 e uzakl���n� d���n�rsek 
	 * <br> a^2 - 2*a +1  + b^2 + c^2 + d^2 - 2*d + 1 bu sebeple kareler he defas�nda hesaplan�yor bunun yerine
	 * <br> "kareler toplam�" - 2(a+d) + 2 = uzakl�k 
	 * <br> yaaani
	 * <br> this.kareleriToplam�  - 2*(dizi[t�m dolu sutunlar�n listesi])  + makineSat�r�.elemanSay�s�;
	 * @param vekt�r2
	 * @return
	 * @throws Exception
	 */
	public double �klidUzakl���Bul2(IntAramaA�ac�2 makineSat�r�) throws Exception{
		ArrayList<Integer> doluSutunlar = makineSat�r�.t�mDe�erleriListele();
		int elemanNo;
		double doluSutunlar�nToplam� = 0;
		for(elemanNo=0;elemanNo<doluSutunlar.size();elemanNo++){
			doluSutunlar�nToplam� += eri�(doluSutunlar.get(elemanNo));
		}
		double uzakl�k = kareleriToplam�  -2*doluSutunlar�nToplam�  + makineSat�r�.elemanSay�s�;
		return uzakl�k;
		
	}
	public double �klidUzakl���Bul(IntAramaA�ac�2 makineSat�r�) throws Exception{
		int elemanNo;
		double uzakl�k = 0;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(makineSat�r�.ara(elemanNo)){
				uzakl�k += (eri�(elemanNo)- 1)*(eri�(elemanNo)-1);
			}
			else {
			    uzakl�k += eri�(elemanNo)*eri�(elemanNo);
			}
		}
		//System.out.print("makinesat�r� : " + makineSat�r�.t�mDe�erleriYaz() + "\n" + "k�me merkezi : " + yaz(",") + "\nuzakl�k : " + uzakl�k + "\n\n" );
		return uzakl�k;
		
	}
	
	public double �klidUzakl���Bul(K�meMerkezi merkez2) throws Exception{
		double uzakl�k = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
            double fark = eri�(elemanNo) - merkez2.eri�(elemanNo);
            uzakl�k += fark*fark;
		}
		return uzakl�k;
	}
	
	public K�meMerkezi kopya() throws Exception{
		K�meMerkezi kopya = new K�meMerkezi(boy());
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDe�er = eri�(elemanNo);
			kopya.de�i�(elemanNo, yeniDe�er);
		}
		return kopya;
	}
	
    public void topla (K�meMerkezi eklenecek) throws Exception
	{
		dizi��lemBoyutKontrol(eklenecek);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDe�er = eri�(elemanNo) + eklenecek.eri�(elemanNo);
			de�i�(elemanNo, yeniDe�er);
		}
	}
	private void dizi��lemBoyutKontrol(K�meMerkezi eklenecek) throws Exception 
	{
		if(eklenecek.dizi.boy()!= dizi.boy())
		{
			throw new Exception("i�leme giren dizinin boyutu tutmuyor gelen dizinin boyutu = "
					+ eklenecek.dizi.boy()+ " yap�daki dizi boyutu = " + dizi.boy());
		}
	}
    public void �arpVeTopla(K�meMerkezi eklenecek ,double katsay�) throws Exception
    {
    	dizi��lemBoyutKontrol(eklenecek);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDe�er = eri�(elemanNo) + eklenecek.eri�(elemanNo)*katsay�;
			de�i�(elemanNo, yeniDe�er);
		}
    }
	public double vekt�rel�arp(K�meMerkezi �arp�lacak) throws Exception 
	{
		dizi��lemBoyutKontrol(�arp�lacak);
		int elemanNo;
		double �arp�m = 0;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			if(eri�(elemanNo) != 0 && �arp�lacak.eri�(elemanNo) != 0){
				�arp�m +=  dizi.eri�(elemanNo)* �arp�lacak.eri�(elemanNo);	
			}
		}
		return �arp�m;
	}
    public void ��kar(K�meMerkezi ��kar�lacak) throws Exception 
    {
    	dizi��lemBoyutKontrol(��kar�lacak);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDe�er = eri�(elemanNo) - ��kar�lacak.eri�(elemanNo);
			de�i�(elemanNo, yeniDe�er);
		}
    }
    
    public K�meMerkezi ��kar2(K�meMerkezi ��kan) throws Exception{
    	dizi��lemBoyutKontrol(��kan);
    	K�meMerkezi sonu� = new K�meMerkezi(boy());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDe�er = eri�(elemanNo) - ��kan.eri�(elemanNo);
    		sonu�.de�i�(elemanNo, yeniDe�er);
    	}
    	return sonu�;
    }
    
    public void topla (DoubleVekt�r eklenecek) throws Exception
	{
		dizi��lemBoyutKontrol(eklenecek);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++){
			double yeniDe�er = eri�(elemanNo) + eklenecek.eri�(elemanNo);
			de�i�(elemanNo, yeniDe�er);
		}
	}
	private void dizi��lemBoyutKontrol(DoubleVekt�r eklenecek) throws Exception 
	{
		if(eklenecek.dizi.length != dizi.boy())
		{
			throw new Exception("i�leme giren dizinin boyutu tutmuyor gelen dizinin boyutu = "
					+ eklenecek.dizi.length + " yap�daki dizi boyutu = " + dizi.boy());
		}
	}
    /**
     * eklenecek vekt�r�n� kaysat� ile �arp�p toplar
     * @param eklenecek
     * @param katsay�
     * @throws Exception
     */
    public void �arpVeTopla(DoubleVekt�r eklenecek ,double katsay�) throws Exception
    {
    	dizi��lemBoyutKontrol(eklenecek);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDe�er = eri�(elemanNo) + eklenecek.eri�(elemanNo)*katsay�;
			de�i�(elemanNo, yeniDe�er);
		}
    }
	public double vekt�rel�arp(DoubleVekt�r �arp�lacak) throws Exception 
	{
		dizi��lemBoyutKontrol(�arp�lacak);
		int elemanNo;
		double �arp�m = 0;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			if(eri�(elemanNo) != 0 && �arp�lacak.eri�(elemanNo) != 0){
				�arp�m +=  eri�(elemanNo) * �arp�lacak.eri�(elemanNo);	
			}
		}
		return �arp�m;
	}
    public void ��kar(DoubleVekt�r ��kar�lacak) throws Exception 
    {
    	dizi��lemBoyutKontrol(��kar�lacak);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDe�er = eri�(elemanNo) - ��kar�lacak.eri�(elemanNo);
			de�i�(elemanNo, yeniDe�er);
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
    
    /**
     * t�m diziyi bir say� ile �arpar
     * @param say�
     * @throws Exception
     */
    public void �arp(double say�) throws Exception{
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDe�er = eri�(elemanNo)*say�;
			dizi.dizi[elemanNo] = yeniDe�er;
		}
		kareleriToplam� *= say�*say�; // kolayl�k olsun diye
    }
    
    
    public void rassalDoldur() throws Exception{
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDe�er = Math.random();
            de�i�(elemanNo, yeniDe�er);
    	}
    }

    
    public String yaz(String arayaGirenKarakter) throws Exception
    {
    	if(dizi.boy()== 0)
    	{
    		return "";
    	}
    	else
    	{
    		return bo�OlmayanDiziYaz(arayaGirenKarakter);
    	}
    }
	private String bo�OlmayanDiziYaz(String arayaGirenKarakter) throws Exception
	{
		int elemanNo;	
		String yaz� = "";
		for(elemanNo=0;elemanNo<dizi.boy()-1;elemanNo++)
		{
			yaz� += eri�(elemanNo)+ arayaGirenKarakter;
		}
		yaz� += eri�(elemanNo);
		return yaz�;
	}
    
}
