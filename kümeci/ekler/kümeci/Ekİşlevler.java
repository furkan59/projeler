package k�meci;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.TreeMap;

import metaSezgisel.Par�ac�kS�r�;
import veriYap�lar�.BoolDizi;
import veriYap�lar�.BoolVekt�r;
import yap�salK�meleme.Ba�Enerji;
import yap�salK�meleme.DereceS�ra;
import yap�salK�meleme.K�meBulma;
import yap�salK�meleme.OrtalamaBa�lant�l�List;
import yap�salK�meleme.TekBa�lant�l�;

public class Ek��levler {
	/**
	 * yo�unluk k�menin doluluk oran�d�r.
	 * <br> Ne kadar fazla olursa o kadar 1 olur
	 * @param sat�r
	 * @param sutun
	 * @param yo�unluk
	 * @return
	 * @throws Exception 
	 */
	public String rassalVeri�ret(int sat�r,int sutun,double yo�unluk) throws Exception{
		String yaz� = "";
		yo�unlu�uKontrolEt(yo�unluk);
		yaz� = par�alar�Yaz(sutun, yaz�);
		yaz� = sat�rlar�Yaz(sat�r, sutun, yo�unluk, yaz�);
		return yaz�;
	}
	private void yo�unlu�uKontrolEt(double yo�unluk) throws Exception {
		if(yo�unluk <= 0 || yo�unluk >= 1){
			throw new Exception("yo�unluk de�eri 0 ile 1 aras�nda olmal�d�r!! \n girilen yo�unluk de�eri : \n" + yo�unluk );
		}
	}
	private String sat�rlar�Yaz(int sat�r, int sutun, double yo�unluk, String yaz�) {
		for(int sat�rNo=0;sat�rNo<sat�r;sat�rNo++){
			yaz� = sat�rYaz(sutun, yo�unluk, yaz�, sat�rNo);
		}
		return yaz�;
	}
	private String par�alar�Yaz(int sutun, String yaz�) {
		yaz� += "\t"; 
		for(int sutunNo = 0;sutunNo<sutun;sutunNo++){
			yaz� += "p" + sutunNo + "\t";
		}
		yaz� += "\n";
		return yaz�;
	}
	private String sat�rYaz(int sutun, double yo�unluk, String yaz�, int sat�rNo) {
		yaz� += "m" + sat�rNo +"\t";
		for(int sutunNo = 0;sutunNo<sutun;sutunNo++){
			yaz� = boolDe�erYaz(yo�unluk, yaz�);
		}
		yaz� += "\n";
		return yaz�;
	}
	private String boolDe�erYaz(double yo�unluk, String yaz�) {
		if(Math.random() < yo�unluk){
			yaz� += "1\t";
		}
		else{
			yaz� += "0\t";
		}
		return yaz�;
	}

	public BoolDizi k�melenmi�Veri�ret(int sat�r,int sutun,int k�meSay�s�) throws Exception{
		BoolDizi veri = new BoolDizi(sat�r);
		int k�meSat�rSay�s� = sat�r / k�meSay�s�; // bu ortalama de�erdir fakat son k�me di�erlerine k���k veya e�it olacakt�r
		int k�meSutunSay�s� = sutun / k�meSay�s�; // her k�me ka� sutun i�erecek
		d�zenliOlanK�meler(sutun, k�meSay�s�, veri, k�meSat�rSay�s�,k�meSutunSay�s�);
		sonK�meyiYap(k�meSay�s�, veri, k�meSat�rSay�s�,k�meSutunSay�s�);
		return veri;
	}
	private void sonK�meyiYap(int k�meSay�s�,BoolDizi veri, int k�meGeni�li�i,int k�meSutunSay�s�) throws Exception {
		if(veri.sat�rSay() % k�meSay�s� == 0 && veri.sutunSay() % k�meSay�s� == 0){ // veri tam olarak b�l�nebilir demektir
			d�zenliSonK�me(k�meSay�s�, veri, k�meGeni�li�i,k�meSutunSay�s�);
		}
		else{
			d�zensizSonK�me(k�meSay�s�, veri,k�meGeni�li�i,k�meSutunSay�s�);
		}
	}
	private void d�zensizSonK�me(int k�meSay�s�, BoolDizi veri,int k�meGeni�li�i,int k�meSutunSay�s�) {
		int k�meSat�rBa�� = (k�meSay�s�-1)*k�meGeni�li�i;
		int yeniK�meSat�r� = veri.sat�rSay() - k�meSat�rBa��;
		int ba� = (k�meSay�s�-1)*k�meSutunSay�s�;
		int son = veri.sutunSay();
		for(int sat�rNo=0;sat�rNo<yeniK�meSat�r�;sat�rNo++){
			BoolVekt�r yeniSat�r = new BoolVekt�r(veri.sutunSay(), ba�, son);
			veri.matris[k�meSat�rBa�� + sat�rNo] = yeniSat�r;
		}
	}
	private void d�zenliSonK�me(int k�meSay�s�, BoolDizi veri,int k�meGeni�li�i,int k�meSutunSay�s�) throws Exception {
		int k�meSat�rBa�� =  veri.sat�rSay() - k�meGeni�li�i ;
		int ba� = veri.sutunSay() - k�meSutunSay�s�;
		int son = veri.sutunSay();
		for(int sat�rNo=0;sat�rNo<k�meGeni�li�i;sat�rNo++){
			BoolVekt�r yeniSat�r = new BoolVekt�r(veri.sutunSay(), ba�, son);
			veri.matris [k�meSat�rBa�� +  sat�rNo] = yeniSat�r;
		}
	}
	private void d�zenliOlanK�meler(int sutun,int k�meSay�s�, BoolDizi veri,int k�meGeni�li�i,int k�meSutunSay�s�) throws Exception {
		for(int k�meNo=0;k�meNo<k�meSay�s�;k�meNo++){
			int ba� = k�meNo*k�meSutunSay�s�;
			int son = (k�meNo+1)*k�meSutunSay�s�;
			for(int sat�rNo=0;sat�rNo<k�meGeni�li�i;sat�rNo++){
				BoolVekt�r yeniSat�r = new BoolVekt�r(sutun, ba�, son);
				veri.matris [k�meNo*k�meGeni�li�i +  sat�rNo] = yeniSat�r;	
			}
		}
	}

	/**
	 * i�i dolu bir dizi verilmelidir
	 * @param veri
	 * @return
	 * @throws Exception
	 */
	public String k�melenmi�VeriyiYaz(BoolDizi veri) throws Exception{
		String yaz� = "";
		yaz� = par�alar�Yaz(veri.sutunSay(), yaz�);
		for(int sat�rNo=0;sat�rNo<veri.sat�rSay();sat�rNo++){
			yaz� += "m" + sat�rNo +  "\t" + veri.sat�r(sat�rNo).veriYaz() +"\n";
		}
		return yaz�;
	}

	public BoolDizi k�melenmi�VeriyiKar��t�r(BoolDizi veri) throws Exception{
		BoolDizi kar���k = new BoolDizi(veri.sat�rSay(), veri.sutunSay(), false);
		sutunlar�Kar��t�r2(veri, kar���k);
		sat�rlar�Kar��t�r2(veri, kar���k);
		return kar���k;
	}
	private void sutunlar�Kar��t�r(BoolDizi veri, BoolDizi kar���k)throws Exception {
		RassalDizi sutunlar = new RassalDizi(veri.sutunSay());
		for(int sutunNo=0;sutunNo<veri.sutunSay();sutunNo++){
			int sutun = sutunlar.rassalYerVer();
			BoolVekt�r yeniSutun = veri.sutun(sutun);
			kar���k.sutunaYerle�(sutunNo, yeniSutun);
		}
	}
	private void sat�rlar�Kar��t�r(BoolDizi veri, BoolDizi kar���k)throws Exception {
		RassalDizi sat�rlar = new RassalDizi(veri.sat�rSay());
		for(int sat�rNo=0;sat�rNo<veri.sat�rSay();sat�rNo++){
			int sat�r = sat�rlar.rassalYerVer();
			kar���k.matris[sat�rNo] = veri.sat�r(sat�r); 
		}
	}

	public String kar��t�r�lm��K�melenmi�Veri�Ret(int sat�r,int sutun,int k�meSay�s�) throws Exception{
		BoolDizi k�meli = k�melenmi�Veri�ret(sat�r, sutun, k�meSay�s�);
		BoolDizi kar���k = k�melenmi�VeriyiKar��t�r(k�meli);
		return k�melenmi�VeriyiYaz(kar���k);
	}
	public String t�mY�ntemlerle��z(String girdi) throws Exception{
		String ��z�m = "";
		long ba� = System.currentTimeMillis();

		Ba�Enerji be = new Ba�Enerji(girdi);
		��z�m += "BA� ENERJ� Y�NTEM� \n" + be.��z()  + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("ba� enerjisi bitti : "  + (System.currentTimeMillis()-ba�)+ "\n");
		ba� = System.currentTimeMillis();

		TekBa�lant�l� tb = new TekBa�lant�l�(girdi);
		��z�m += "TEK BA�LANTILI \n" + tb.��z() + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("TEK BA�LANTILI bitti : " + (System.currentTimeMillis()-ba�)+ "\n");
		ba� = System.currentTimeMillis();

		OrtalamaBa�lant�l�List ob = new OrtalamaBa�lant�l�List(girdi);
		��z�m += "ORTALAMA BA�LANTILI \n" + ob.��z() + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("ORTALAMA BA�LANTILI bitti : " + (System.currentTimeMillis()-ba�)+"\n");
		ba� = System.currentTimeMillis();

		K�meBulma kb = new K�meBulma(girdi);
		��z�m += "K�ME BULMA \n" + kb.��z() + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("K�ME BULMA bitti : "+ (System.currentTimeMillis()-ba�)+"\n");
		ba� = System.currentTimeMillis();

		DereceS�ra dr = new DereceS�ra(girdi);
		��z�m += "DERECE SIRA \n" + dr.��z() +"\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("DERECE SIRA bitti  : "+ (System.currentTimeMillis()-ba�)+"\n");


		return ��z�m;
	}

	private void sat�rlar�Kar��t�r2(BoolDizi veri, BoolDizi kar���k)throws Exception {
		boolean �iftTek = true;
		for(int sat�rNo=0;sat�rNo<veri.sat�rSay();sat�rNo++){
			if(�iftTek){
				int sat�r = veri.sat�rSay() - sat�rNo -1 ;
				kar���k.matris[sat�rNo] = veri.sat�r(sat�r); 	
			}
			else {
				kar���k.matris[sat�rNo] = veri.sat�r(sat�rNo); 	
			}
			�iftTek = !�iftTek;
		}
	}
	private void sutunlar�Kar��t�r2(BoolDizi veri, BoolDizi kar���k)throws Exception {
		boolean �iftTek = true;
		for(int sutunNo=0;sutunNo<veri.sutunSay();sutunNo++){
			if(�iftTek )
			{
				int sutun = veri.sutunSay() - sutunNo -1;
				BoolVekt�r yeniSutun = veri.sutun(sutun);
				kar���k.sutunaYerle�(sutunNo, yeniSutun);
			}
			else {
				BoolVekt�r yeniSutun = veri.sutun(sutunNo);
				kar���k.sutunaYerle�(sutunNo, yeniSutun);
			}
			�iftTek = !�iftTek; 
		}
	}

}
