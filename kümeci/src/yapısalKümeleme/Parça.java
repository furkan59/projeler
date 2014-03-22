package yap�salK�meleme;
import veriYap�lar�.*;
public class Par�a {

	String isim;
	String [] �zelliklerYaz� ;
	DoubleVekt�r �zelliklerSay�;
	public Par�a() {
		
	}
	public Par�a(String isim, String [] �zelliklerYaz�){
		this.isim = isim;
		this.�zelliklerYaz� = �zelliklerYaz�;
	}
	public Par�a(String isim, DoubleVekt�r �zelliklerSay�){
		this.isim = isim;
		this.�zelliklerSay� = �zelliklerSay�;
	}
	/**
	 * par�a ad� : par�an�n say�sal �zellikleri DoubleVekt�r halinde ;
	 * <br>par�a1:1,2,3,4,5,4 
	 * @param yaz�
	 * @throws Exception 
	 */
	public Par�a(String yaz�) throws Exception{
		�iftNokta��eriyorMuBak(yaz�);
		String [] b�l�nm�� = yaz�.split(":");
		DoubleVekt�r �zelliklerSay� = new DoubleVekt�r(b�l�nm��[1]);
		String isim = b�l�nm��[0];
		this.isim = isim;
		this.�zelliklerSay� = �zelliklerSay�;
	}
	private void �iftNokta��eriyorMuBak(String yaz�) throws Exception {
		if(yaz�.contains(":") == false){
			throw new Exception("yaz�  ':'  i�ermiyor \nyaz�n�n kendisi : " + yaz� + "\n");
		}
	}
	
    public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String[] get�zelliklerYaz�() {
		return �zelliklerYaz�;
	}
	public void set�zelliklerYaz�(String[] �zelliklerYaz�) {
		this.�zelliklerYaz� = �zelliklerYaz�;
	}
	public DoubleVekt�r get�zelliklerSay�() {
		return �zelliklerSay�;
	}
	public void set�zelliklerSay�(DoubleVekt�r �zelliklerSay�) {
		this.�zelliklerSay� = �zelliklerSay�;
	}

	public double minkowskiUzakl�kBul(Par�a par�a2) throws Exception{
		double benzerlik = 0;
		int �zellikNo;
		for(�zellikNo=0;�zellikNo<�zelliklerSay�.boy();�zellikNo++){
			double par�a1�zellik = �zelliklerSay�.eri�(�zellikNo);
			double par�a2�zellik = par�a2.�zelliklerSay�.eri�(�zellikNo);
			double mutlakFark = Math.abs(par�a2�zellik-par�a1�zellik);
			benzerlik += mutlakFark;
		}
		return benzerlik;
	}

	public String �zellikliYaz(){
		String  yaz� ="";
		yaz� += "isim : " + isim + "\n";
		yaz� += "Say�sal �zellikler : \n" + �zelliklerSay�.yaz("\t") + "\n";
		return yaz�;
	}
	public String yaz(){
		return "isim : " + isim;
	}

}
