package SýkýþtýrýlmýþVeriYapýlarý;

import java.util.ArrayList;

import veriYapýlarý.DoubleVektör;

public class KümeMerkezi {

	public DoubleVektör dizi ;
	public double kareleriToplamý = 0;
	
    public KümeMerkezi(String yazý, String ayýraç) throws Exception {
    	String [] bölünmüþString = yazý.split(ayýraç);
    	int elemanNo;
    	int dizininBoyu = bölünmüþString.length;
    	dizi =  new DoubleVektör(dizininBoyu);
    	for(elemanNo=0;elemanNo<dizininBoyu;elemanNo++)
    	{
    		double yeniDeðer = Double.parseDouble(bölünmüþString[elemanNo]);
    		deðiþ(elemanNo, yeniDeðer); 
    		kareleriToplamý += eriþ(elemanNo)*eriþ(elemanNo);
    	}
	}
	public KümeMerkezi(int vektörBoyu, double sayý) throws Exception {
		int elemanNo;
		dizi =  new DoubleVektör(vektörBoyu);
    	for(elemanNo=0;elemanNo<vektörBoyu;elemanNo++)
    	{
    		deðiþ(elemanNo, sayý); 
    	}
    	kareleriToplamý += sayý*sayý*vektörBoyu;
	}
	public KümeMerkezi(int birimVektörBoyu, int sayýnýnYeri, double sayý) throws Exception {
		if(sayýnýnYeri < 0 || sayýnýnYeri > birimVektörBoyu-1)
    	{
    		throw new Exception("sayýnýn yeri vektör dýþýnda sayýný yeri = " + sayýnýnYeri + "vektörün boyutu = " + birimVektörBoyu);
    	}
    	dizi = new DoubleVektör(birimVektörBoyu);
    	int elemanNo;
    	deðiþ(sayýnýnYeri, sayý); // bir konuldu
    	for(elemanNo=0;elemanNo<sayýnýnYeri;elemanNo++)
    	{
    		deðiþ(elemanNo, 0);
    	}// birin olduðu yere kadar ki kýsým tamam
    	for(elemanNo= sayýnýnYeri+1;elemanNo<dizi.boy();elemanNo++)
    	{
    		deðiþ(elemanNo, 0);
    	}
    	kareleriToplamý = sayý*sayý;
	}
	public KümeMerkezi(int boy) throws Exception {
		int elemanNo;
		dizi =  new DoubleVektör(boy);
    	for(elemanNo=0;elemanNo<boy;elemanNo++)
    	{
    		dizi.deðiþ(elemanNo, 0); 
    	}
	}
	
	public double eriþ(int elemanNo) throws Exception{
		return dizi.eriþ(elemanNo);
	}
    public void deðiþ(int elemanNo,double yeniDeðer) throws Exception{
        eriþimiKontrolEt(elemanNo);
    	double çýkacakDeðer = dizi.eriþ(elemanNo)*dizi.eriþ(elemanNo);
        kareleriToplamý += yeniDeðer*yeniDeðer -çýkacakDeðer;
        dizi.deðiþ(elemanNo, yeniDeðer);
    }
	private void eriþimiKontrolEt(int elemanNo) throws Exception {
		if(elemanNo < 0 || elemanNo > dizi.boy()-1){
    		throw new Exception("eriþim hatasý girilen elemanNo : " + elemanNo + " dizinin boyu : " + dizi.boy());
    	}
	}
    public int boy(){
    	return dizi.boy();
    }
	
	 /**
	 * <br> burada öklid uzaklýðý farklý bir þekilde bulunacak 
	 * <br> normal de a,b,c,d nin 1,0,0,1 e uzaklýðýný düþünürsek 
	 * <br> a^2 - 2*a +1  + b^2 + c^2 + d^2 - 2*d + 1 bu sebeple kareler he defasýnda hesaplanýyor bunun yerine
	 * <br> "kareler toplamý" - 2(a+d) + 2 = uzaklýk 
	 * <br> yaaani
	 * <br> this.kareleriToplamý  - 2*(dizi[tüm dolu sutunlarýn listesi])  + makineSatýrý.elemanSayýsý;
	 * @param vektör2
	 * @return
	 * @throws Exception
	 */
	public double öklidUzaklýðýBul2(IntAramaAðacý2 makineSatýrý) throws Exception{
		ArrayList<Integer> doluSutunlar = makineSatýrý.tümDeðerleriListele();
		int elemanNo;
		double doluSutunlarýnToplamý = 0;
		for(elemanNo=0;elemanNo<doluSutunlar.size();elemanNo++){
			doluSutunlarýnToplamý += eriþ(doluSutunlar.get(elemanNo));
		}
		double uzaklýk = kareleriToplamý  -2*doluSutunlarýnToplamý  + makineSatýrý.elemanSayýsý;
		return uzaklýk;
		
	}
	public double öklidUzaklýðýBul(IntAramaAðacý2 makineSatýrý) throws Exception{
		int elemanNo;
		double uzaklýk = 0;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			if(makineSatýrý.ara(elemanNo)){
				uzaklýk += (eriþ(elemanNo)- 1)*(eriþ(elemanNo)-1);
			}
			else {
			    uzaklýk += eriþ(elemanNo)*eriþ(elemanNo);
			}
		}
		//System.out.print("makinesatýrý : " + makineSatýrý.tümDeðerleriYaz() + "\n" + "küme merkezi : " + yaz(",") + "\nuzaklýk : " + uzaklýk + "\n\n" );
		return uzaklýk;
		
	}
	
	public double öklidUzaklýðýBul(KümeMerkezi merkez2) throws Exception{
		double uzaklýk = 0;
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
            double fark = eriþ(elemanNo) - merkez2.eriþ(elemanNo);
            uzaklýk += fark*fark;
		}
		return uzaklýk;
	}
	
	public KümeMerkezi kopya() throws Exception{
		KümeMerkezi kopya = new KümeMerkezi(boy());
		int elemanNo;
		for(elemanNo=0;elemanNo<boy();elemanNo++){
			double yeniDeðer = eriþ(elemanNo);
			kopya.deðiþ(elemanNo, yeniDeðer);
		}
		return kopya;
	}
	
    public void topla (KümeMerkezi eklenecek) throws Exception
	{
		diziÝþlemBoyutKontrol(eklenecek);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDeðer = eriþ(elemanNo) + eklenecek.eriþ(elemanNo);
			deðiþ(elemanNo, yeniDeðer);
		}
	}
	private void diziÝþlemBoyutKontrol(KümeMerkezi eklenecek) throws Exception 
	{
		if(eklenecek.dizi.boy()!= dizi.boy())
		{
			throw new Exception("iþleme giren dizinin boyutu tutmuyor gelen dizinin boyutu = "
					+ eklenecek.dizi.boy()+ " yapýdaki dizi boyutu = " + dizi.boy());
		}
	}
    public void çarpVeTopla(KümeMerkezi eklenecek ,double katsayý) throws Exception
    {
    	diziÝþlemBoyutKontrol(eklenecek);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDeðer = eriþ(elemanNo) + eklenecek.eriþ(elemanNo)*katsayý;
			deðiþ(elemanNo, yeniDeðer);
		}
    }
	public double vektörelÇarp(KümeMerkezi çarpýlacak) throws Exception 
	{
		diziÝþlemBoyutKontrol(çarpýlacak);
		int elemanNo;
		double çarpým = 0;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			if(eriþ(elemanNo) != 0 && çarpýlacak.eriþ(elemanNo) != 0){
				çarpým +=  dizi.eriþ(elemanNo)* çarpýlacak.eriþ(elemanNo);	
			}
		}
		return çarpým;
	}
    public void çýkar(KümeMerkezi çýkarýlacak) throws Exception 
    {
    	diziÝþlemBoyutKontrol(çýkarýlacak);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDeðer = eriþ(elemanNo) - çýkarýlacak.eriþ(elemanNo);
			deðiþ(elemanNo, yeniDeðer);
		}
    }
    
    public KümeMerkezi çýkar2(KümeMerkezi çýkan) throws Exception{
    	diziÝþlemBoyutKontrol(çýkan);
    	KümeMerkezi sonuç = new KümeMerkezi(boy());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDeðer = eriþ(elemanNo) - çýkan.eriþ(elemanNo);
    		sonuç.deðiþ(elemanNo, yeniDeðer);
    	}
    	return sonuç;
    }
    
    public void topla (DoubleVektör eklenecek) throws Exception
	{
		diziÝþlemBoyutKontrol(eklenecek);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++){
			double yeniDeðer = eriþ(elemanNo) + eklenecek.eriþ(elemanNo);
			deðiþ(elemanNo, yeniDeðer);
		}
	}
	private void diziÝþlemBoyutKontrol(DoubleVektör eklenecek) throws Exception 
	{
		if(eklenecek.dizi.length != dizi.boy())
		{
			throw new Exception("iþleme giren dizinin boyutu tutmuyor gelen dizinin boyutu = "
					+ eklenecek.dizi.length + " yapýdaki dizi boyutu = " + dizi.boy());
		}
	}
    /**
     * eklenecek vektörünü kaysatý ile çarpýp toplar
     * @param eklenecek
     * @param katsayý
     * @throws Exception
     */
    public void çarpVeTopla(DoubleVektör eklenecek ,double katsayý) throws Exception
    {
    	diziÝþlemBoyutKontrol(eklenecek);
    	int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDeðer = eriþ(elemanNo) + eklenecek.eriþ(elemanNo)*katsayý;
			deðiþ(elemanNo, yeniDeðer);
		}
    }
	public double vektörelÇarp(DoubleVektör çarpýlacak) throws Exception 
	{
		diziÝþlemBoyutKontrol(çarpýlacak);
		int elemanNo;
		double çarpým = 0;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			if(eriþ(elemanNo) != 0 && çarpýlacak.eriþ(elemanNo) != 0){
				çarpým +=  eriþ(elemanNo) * çarpýlacak.eriþ(elemanNo);	
			}
		}
		return çarpým;
	}
    public void çýkar(DoubleVektör çýkarýlacak) throws Exception 
    {
    	diziÝþlemBoyutKontrol(çýkarýlacak);
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDeðer = eriþ(elemanNo) - çýkarýlacak.eriþ(elemanNo);
			deðiþ(elemanNo, yeniDeðer);
		}
    }
    public DoubleVektör çýkar2(DoubleVektör çýkan) throws Exception{
    	diziÝþlemBoyutKontrol(çýkan);
    	DoubleVektör sonuç = new DoubleVektör(boy());
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDeðer = eriþ(elemanNo) - çýkan.eriþ(elemanNo);
    		sonuç.deðiþ(elemanNo, yeniDeðer);
    	}
    	return sonuç;
    }
    
    /**
     * tüm diziyi bir sayý ile çarpar
     * @param sayý
     * @throws Exception
     */
    public void çarp(double sayý) throws Exception{
		int elemanNo;
		for(elemanNo=0;elemanNo<dizi.boy();elemanNo++)
		{
			double yeniDeðer = eriþ(elemanNo)*sayý;
			dizi.dizi[elemanNo] = yeniDeðer;
		}
		kareleriToplamý *= sayý*sayý; // kolaylýk olsun diye
    }
    
    
    public void rassalDoldur() throws Exception{
    	int elemanNo;
    	for(elemanNo=0;elemanNo<boy();elemanNo++){
    		double yeniDeðer = Math.random();
            deðiþ(elemanNo, yeniDeðer);
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
    		return boþOlmayanDiziYaz(arayaGirenKarakter);
    	}
    }
	private String boþOlmayanDiziYaz(String arayaGirenKarakter) throws Exception
	{
		int elemanNo;	
		String yazý = "";
		for(elemanNo=0;elemanNo<dizi.boy()-1;elemanNo++)
		{
			yazý += eriþ(elemanNo)+ arayaGirenKarakter;
		}
		yazý += eriþ(elemanNo);
		return yazý;
	}
    
}
