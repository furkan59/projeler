package veriYap�lar�;

import java.util.ArrayList;

public class DoubleListDizi {

	public ArrayList<DoubleListVekt�r> dizi = new ArrayList<DoubleListVekt�r>(); 
	public DoubleListDizi() {
	
	}
    public double eri�(int sat�r, int sutun){
    	return dizi.get(sat�r).eri�(sutun);
    }
    public void de�i�(int sat�r, int sutun, double de�er){
    	dizi.get(sat�r).de�i�(sutun, de�er);
    }
    
    public void sat�rDe�i�(int sat�r, DoubleListVekt�r yeniSat�r){
    	sat�rSil(sat�r);
    	dizi.add(sat�r, yeniSat�r);
    }
    public void sat�rEkle(int sat�r , DoubleListVekt�r yeniSat�r){
    	dizi.add(sat�r,yeniSat�r);
    }
    public void sat�rEkle(DoubleListVekt�r yeniSat�r){
    	dizi.add(yeniSat�r);
    }
    public void sutunDe�i�(int sutun, DoubleListVekt�r yeniSutun){
    	int sat�rNo;
    	int sat�rSay�s� = sat�rSay();
    	for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
    		double yeniDe�er = yeniSutun.eri�(sutun);
    		dizi.get(sat�rNo).de�i�(sutun, yeniDe�er);
    	}
    }
    public void sutunEkle(DoubleListVekt�r yeniSutun){
    	if(dizi == null) {// demek ki ilk ba�ta sutun olarak eklenecen 
    	      sutundanDiziYap(yeniSutun);
    	}
    	else{
    		diziyeSutunEkle(yeniSutun);
    	}
    }
	private void diziyeSutunEkle(DoubleListVekt�r yeniSutun) {
		int sat�rNo;
		int sat�rSay�s� = sat�rSay();
		for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
			double yeniDe�er = yeniSutun.eri�(sat�rNo);
			dizi.get(sat�rNo).ekle(yeniDe�er);;
		}
	}
	private void sutundanDiziYap(DoubleListVekt�r yeniSutun) {
		int sat�rNo;
		  int yeniSutununBoyu = yeniSutun.boy();
		  for(sat�rNo=0;sat�rNo<yeniSutununBoyu;sat�rNo++){
			  DoubleListVekt�r yeniSat�r = new DoubleListVekt�r();
			  yeniSat�r.ekle(yeniSutun.eri�(sat�rNo));
		  }
	}
    public void sutunEkle(int sutunNo, DoubleListVekt�r yeniSutun){
    	int sat�rNo;
    	int sat�rSay�s� = sat�rSay();
    	for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
    		double yeniDe�er = yeniSutun.eri�(sat�rNo);
    		dizi.get(sat�rNo).ekle(sutunNo,yeniDe�er);;
    	}
    }
	
    public int sat�rSay(){
		return dizi.size();
	}
	public int sutunSay(){
		return dizi.get(0).boy();
	}
	
	public void sat�rSil ( int sat�rNo){
		dizi.remove(sat�rNo);
	}
	public void sutunsil(int sutunNo){
		int sat�rNo;
		int sat�rSay�s� = sat�rSay();
		for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
			if(dizi.get(sat�rNo).boy() > sutunNo){
				dizi.get(sat�rNo).sil(sutunNo);	
			}
		}
	}
    public void sat�rSutunSil(int sat�rNo, int sutunNo){
    	sat�rSil(sat�rNo);
    	sutunsil(sutunNo);
    }
	
	public int [] enB�y���nYeri(){
		int sat�r,sutun;
		int sat�rSay�s� = sat�rSay();
		double enB�y�k = eri�(0,0);
		int [] enB�y���nYeri = new int [2];
		enB�y���Ata(enB�y���nYeri);
		for(sat�r=0;sat�r<sat�rSay�s�;sat�r++){
			int sutunSay�s� = dizi.get(sat�r).boy();
			for(sutun=0;sutun<sutunSay�s�;sutun++){
				enB�y�k = enB�y���Kar��la�t�r(sat�r, sutun, enB�y�k, enB�y���nYeri);
			}
		}
		return enB�y���nYeri;
	}
	private void enB�y���Ata(int[] enB�y���nYeri) {
		enB�y���nYeri [0] = 0;
		enB�y���nYeri [1] = 0;
	}
	private double enB�y���Kar��la�t�r(int sat�r, int sutun, double enB�y�k, int[] enB�y���nYeri) {
		double yeniDe�er = eri�(sat�r, sutun);
		if(yeniDe�er > enB�y�k){
			enB�y�k = yeniDe�er;
			enB�y���nYeri[0] = sat�r;
			enB�y���nYeri[1] = sutun;
		}
		return enB�y�k;
	}
 
	public Boolean b�y���VarM�(double aranacak){
		int sat�rNo;
		for(sat�rNo=0;sat�rNo<sat�rSay();sat�rNo++){
			DoubleListVekt�r sat�r = dizi.get(sat�rNo);
			if(sat�r.b�y���VarM�(aranacak)){
				return true;
			}
		}
		return  false; // e�er d�ng�den kurtulursa buraya gelir zaten
	}

	public String yaz(char ay�r�c�){
		int sat�rNo,sat�rSay�s� = sat�rSay();
		String yaz� = "";
		for(sat�rNo=0;sat�rNo<sat�rSay�s�-1;sat�rNo++){
			yaz� += dizi.get(sat�rNo).yaz(ay�r�c�) + "\n";
		}
		yaz� += dizi.get(sat�rNo).yaz(ay�r�c�);
		return yaz�;
	}

	public DoubleListDizi kopya (){
		DoubleListDizi kopya = new DoubleListDizi();
		int sat�rNo,sat�rSay�s� = sat�rSay();
		for(sat�rNo=0;sat�rNo<sat�rSay�s�;sat�rNo++){
			DoubleListVekt�r yeniSat�r = dizi.get(sat�rNo).kopya();
			kopya.sat�rEkle(yeniSat�r);
		}
		return kopya;
	}
}
