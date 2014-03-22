package kümeci;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.TreeMap;

import metaSezgisel.ParçacýkSürü;
import veriYapýlarý.BoolDizi;
import veriYapýlarý.BoolVektör;
import yapýsalKümeleme.BaðEnerji;
import yapýsalKümeleme.DereceSýra;
import yapýsalKümeleme.KümeBulma;
import yapýsalKümeleme.OrtalamaBaðlantýlýList;
import yapýsalKümeleme.TekBaðlantýlý;

public class EkÝþlevler {
	/**
	 * yoðunluk kümenin doluluk oranýdýr.
	 * <br> Ne kadar fazla olursa o kadar 1 olur
	 * @param satýr
	 * @param sutun
	 * @param yoðunluk
	 * @return
	 * @throws Exception 
	 */
	public String rassalVeriÜret(int satýr,int sutun,double yoðunluk) throws Exception{
		String yazý = "";
		yoðunluðuKontrolEt(yoðunluk);
		yazý = parçalarýYaz(sutun, yazý);
		yazý = satýrlarýYaz(satýr, sutun, yoðunluk, yazý);
		return yazý;
	}
	private void yoðunluðuKontrolEt(double yoðunluk) throws Exception {
		if(yoðunluk <= 0 || yoðunluk >= 1){
			throw new Exception("yoðunluk deðeri 0 ile 1 arasýnda olmalýdýr!! \n girilen yoðunluk deðeri : \n" + yoðunluk );
		}
	}
	private String satýrlarýYaz(int satýr, int sutun, double yoðunluk, String yazý) {
		for(int satýrNo=0;satýrNo<satýr;satýrNo++){
			yazý = satýrYaz(sutun, yoðunluk, yazý, satýrNo);
		}
		return yazý;
	}
	private String parçalarýYaz(int sutun, String yazý) {
		yazý += "\t"; 
		for(int sutunNo = 0;sutunNo<sutun;sutunNo++){
			yazý += "p" + sutunNo + "\t";
		}
		yazý += "\n";
		return yazý;
	}
	private String satýrYaz(int sutun, double yoðunluk, String yazý, int satýrNo) {
		yazý += "m" + satýrNo +"\t";
		for(int sutunNo = 0;sutunNo<sutun;sutunNo++){
			yazý = boolDeðerYaz(yoðunluk, yazý);
		}
		yazý += "\n";
		return yazý;
	}
	private String boolDeðerYaz(double yoðunluk, String yazý) {
		if(Math.random() < yoðunluk){
			yazý += "1\t";
		}
		else{
			yazý += "0\t";
		}
		return yazý;
	}

	public BoolDizi kümelenmiþVeriÜret(int satýr,int sutun,int kümeSayýsý) throws Exception{
		BoolDizi veri = new BoolDizi(satýr);
		int kümeSatýrSayýsý = satýr / kümeSayýsý; // bu ortalama deðerdir fakat son küme diðerlerine küçük veya eþit olacaktýr
		int kümeSutunSayýsý = sutun / kümeSayýsý; // her küme kaç sutun içerecek
		düzenliOlanKümeler(sutun, kümeSayýsý, veri, kümeSatýrSayýsý,kümeSutunSayýsý);
		sonKümeyiYap(kümeSayýsý, veri, kümeSatýrSayýsý,kümeSutunSayýsý);
		return veri;
	}
	private void sonKümeyiYap(int kümeSayýsý,BoolDizi veri, int kümeGeniþliði,int kümeSutunSayýsý) throws Exception {
		if(veri.satýrSay() % kümeSayýsý == 0 && veri.sutunSay() % kümeSayýsý == 0){ // veri tam olarak bölünebilir demektir
			düzenliSonKüme(kümeSayýsý, veri, kümeGeniþliði,kümeSutunSayýsý);
		}
		else{
			düzensizSonKüme(kümeSayýsý, veri,kümeGeniþliði,kümeSutunSayýsý);
		}
	}
	private void düzensizSonKüme(int kümeSayýsý, BoolDizi veri,int kümeGeniþliði,int kümeSutunSayýsý) {
		int kümeSatýrBaþý = (kümeSayýsý-1)*kümeGeniþliði;
		int yeniKümeSatýrý = veri.satýrSay() - kümeSatýrBaþý;
		int baþ = (kümeSayýsý-1)*kümeSutunSayýsý;
		int son = veri.sutunSay();
		for(int satýrNo=0;satýrNo<yeniKümeSatýrý;satýrNo++){
			BoolVektör yeniSatýr = new BoolVektör(veri.sutunSay(), baþ, son);
			veri.matris[kümeSatýrBaþý + satýrNo] = yeniSatýr;
		}
	}
	private void düzenliSonKüme(int kümeSayýsý, BoolDizi veri,int kümeGeniþliði,int kümeSutunSayýsý) throws Exception {
		int kümeSatýrBaþý =  veri.satýrSay() - kümeGeniþliði ;
		int baþ = veri.sutunSay() - kümeSutunSayýsý;
		int son = veri.sutunSay();
		for(int satýrNo=0;satýrNo<kümeGeniþliði;satýrNo++){
			BoolVektör yeniSatýr = new BoolVektör(veri.sutunSay(), baþ, son);
			veri.matris [kümeSatýrBaþý +  satýrNo] = yeniSatýr;
		}
	}
	private void düzenliOlanKümeler(int sutun,int kümeSayýsý, BoolDizi veri,int kümeGeniþliði,int kümeSutunSayýsý) throws Exception {
		for(int kümeNo=0;kümeNo<kümeSayýsý;kümeNo++){
			int baþ = kümeNo*kümeSutunSayýsý;
			int son = (kümeNo+1)*kümeSutunSayýsý;
			for(int satýrNo=0;satýrNo<kümeGeniþliði;satýrNo++){
				BoolVektör yeniSatýr = new BoolVektör(sutun, baþ, son);
				veri.matris [kümeNo*kümeGeniþliði +  satýrNo] = yeniSatýr;	
			}
		}
	}

	/**
	 * içi dolu bir dizi verilmelidir
	 * @param veri
	 * @return
	 * @throws Exception
	 */
	public String kümelenmiþVeriyiYaz(BoolDizi veri) throws Exception{
		String yazý = "";
		yazý = parçalarýYaz(veri.sutunSay(), yazý);
		for(int satýrNo=0;satýrNo<veri.satýrSay();satýrNo++){
			yazý += "m" + satýrNo +  "\t" + veri.satýr(satýrNo).veriYaz() +"\n";
		}
		return yazý;
	}

	public BoolDizi kümelenmiþVeriyiKarýþtýr(BoolDizi veri) throws Exception{
		BoolDizi karýþýk = new BoolDizi(veri.satýrSay(), veri.sutunSay(), false);
		sutunlarýKarýþtýr2(veri, karýþýk);
		satýrlarýKarýþtýr2(veri, karýþýk);
		return karýþýk;
	}
	private void sutunlarýKarýþtýr(BoolDizi veri, BoolDizi karýþýk)throws Exception {
		RassalDizi sutunlar = new RassalDizi(veri.sutunSay());
		for(int sutunNo=0;sutunNo<veri.sutunSay();sutunNo++){
			int sutun = sutunlar.rassalYerVer();
			BoolVektör yeniSutun = veri.sutun(sutun);
			karýþýk.sutunaYerleþ(sutunNo, yeniSutun);
		}
	}
	private void satýrlarýKarýþtýr(BoolDizi veri, BoolDizi karýþýk)throws Exception {
		RassalDizi satýrlar = new RassalDizi(veri.satýrSay());
		for(int satýrNo=0;satýrNo<veri.satýrSay();satýrNo++){
			int satýr = satýrlar.rassalYerVer();
			karýþýk.matris[satýrNo] = veri.satýr(satýr); 
		}
	}

	public String karýþtýrýlmýþKümelenmiþVeriÜRet(int satýr,int sutun,int kümeSayýsý) throws Exception{
		BoolDizi kümeli = kümelenmiþVeriÜret(satýr, sutun, kümeSayýsý);
		BoolDizi karýþýk = kümelenmiþVeriyiKarýþtýr(kümeli);
		return kümelenmiþVeriyiYaz(karýþýk);
	}
	public String tümYöntemlerleÇöz(String girdi) throws Exception{
		String çözüm = "";
		long baþ = System.currentTimeMillis();

		BaðEnerji be = new BaðEnerji(girdi);
		çözüm += "BAÐ ENERJÝ YÖNTEMÝ \n" + be.çöz()  + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("bað enerjisi bitti : "  + (System.currentTimeMillis()-baþ)+ "\n");
		baþ = System.currentTimeMillis();

		TekBaðlantýlý tb = new TekBaðlantýlý(girdi);
		çözüm += "TEK BAÐLANTILI \n" + tb.çöz() + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("TEK BAÐLANTILI bitti : " + (System.currentTimeMillis()-baþ)+ "\n");
		baþ = System.currentTimeMillis();

		OrtalamaBaðlantýlýList ob = new OrtalamaBaðlantýlýList(girdi);
		çözüm += "ORTALAMA BAÐLANTILI \n" + ob.çöz() + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("ORTALAMA BAÐLANTILI bitti : " + (System.currentTimeMillis()-baþ)+"\n");
		baþ = System.currentTimeMillis();

		KümeBulma kb = new KümeBulma(girdi);
		çözüm += "KÜME BULMA \n" + kb.çöz() + "\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("KÜME BULMA bitti : "+ (System.currentTimeMillis()-baþ)+"\n");
		baþ = System.currentTimeMillis();

		DereceSýra dr = new DereceSýra(girdi);
		çözüm += "DERECE SIRA \n" + dr.çöz() +"\n\n\n\n\n-------------------------------------------------------------\n\n\n\n\n";
		System.out.print("DERECE SIRA bitti  : "+ (System.currentTimeMillis()-baþ)+"\n");


		return çözüm;
	}

	private void satýrlarýKarýþtýr2(BoolDizi veri, BoolDizi karýþýk)throws Exception {
		boolean çiftTek = true;
		for(int satýrNo=0;satýrNo<veri.satýrSay();satýrNo++){
			if(çiftTek){
				int satýr = veri.satýrSay() - satýrNo -1 ;
				karýþýk.matris[satýrNo] = veri.satýr(satýr); 	
			}
			else {
				karýþýk.matris[satýrNo] = veri.satýr(satýrNo); 	
			}
			çiftTek = !çiftTek;
		}
	}
	private void sutunlarýKarýþtýr2(BoolDizi veri, BoolDizi karýþýk)throws Exception {
		boolean çiftTek = true;
		for(int sutunNo=0;sutunNo<veri.sutunSay();sutunNo++){
			if(çiftTek )
			{
				int sutun = veri.sutunSay() - sutunNo -1;
				BoolVektör yeniSutun = veri.sutun(sutun);
				karýþýk.sutunaYerleþ(sutunNo, yeniSutun);
			}
			else {
				BoolVektör yeniSutun = veri.sutun(sutunNo);
				karýþýk.sutunaYerleþ(sutunNo, yeniSutun);
			}
			çiftTek = !çiftTek; 
		}
	}

}
