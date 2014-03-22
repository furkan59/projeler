package yap�salK�meleme;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.tree.TreeNode;


/**
 * dendogram k�melemeyi g�sterir
 * <br> y�kseklik kesim noktalar� k�meleme yap�ld��� zaman ki ili�ki katsay�s�n� g�sterir
 * <br> bu katsay� giderek artar ��nk� gittik�e k�me merkezleri birbirinden uzakla�acakt�r.
 * <br> dendogram �ok noktadan tek noktaya do�ru �izilecektir
 * @author Furkan
 *
 */
public class Dendogram extends JApplet{
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> �ncekiKatmandakiTabanXDe�erleri = new ArrayList<Double>(); // o katmandaki t�m noktalar i�in
	private ArrayList<Double> katmandakiTabanXDe�erleri = new ArrayList<Double>(); // o katmandaki t�m noktalar i�in
	public ArrayList<ArrayList<Integer>> gruplananlar = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<String>> s�ralamalar = new ArrayList<ArrayList<String>>();
	
	public int toplamNokta;
	private int katY�ksekli�i;
	private int noktalarAras�Uzakl�k;
	private int enFazlaA�amaSay�s�;
	public Dendogram(int toplamNokta, int geni�lik,int y�kseklik,int enFazlaA�amaSay�s�,ArrayList<ArrayList<Integer>> gruplananlar,ArrayList<ArrayList<Integer>> k�meler) {
		this.toplamNokta = toplamNokta;
		this.katY�ksekli�i = y�kseklik/enFazlaA�amaSay�s�;
		this.noktalarAras�Uzakl�k = geni�lik/toplamNokta;
		this.enFazlaA�amaSay�s� = enFazlaA�amaSay�s�;
		this.gruplananlar = gruplananlar;
		�ekliOlu�turma(geni�lik, y�kseklik);
	}
	/**
	 * test ama�l�
	 */
	public Dendogram(){
		
	}
	private void �ekliOlu�turma(int geni�lik, int y�kseklik)throws HeadlessException {
		JFrame f = new JFrame("Dendogram");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		f.getContentPane().add("Center", this);
		init();
		f.pack();
		f.setSize(new Dimension(geni�lik,y�kseklik));
		f.setVisible(true);
	}
	// {{ �izim i�lemleri
	public void paint (Graphics grafik){
		taban�iz(grafik);
		int katSay�s�;
		if(gruplananlar.size() < enFazlaA�amaSay�s�){
			katSay�s� = gruplananlar.size();
		}
		else{
			katSay�s� = enFazlaA�amaSay�s�;
		}
        for(int katNo = 0;katNo<katSay�s�;katNo++){
			kat�iz(grafik, katNo);
		}
	}
	private void taban�iz(Graphics grafik) {
		double bo�luk = 40;
		for(int noktaNo=0;noktaNo<toplamNokta;noktaNo++){
			double x1 = noktalarAras�Uzakl�k*noktaNo;
			double x2 = x1;
			double y1 = 0;
			double y2 = katY�ksekli�i;
			�ncekiKatmandakiTabanXDe�erleri.add(x1);
			katmandakiTabanXDe�erleri.add(x1);
		}
		katmandakiTabanXDe�erleri.addAll(�ncekiKatmandakiTabanXDe�erleri);
	}
	private double dikey�izgi�iz(Graphics grafik, double x1, double y1, double x2, double y2) {
		Line2D �izgi = new Line2D.Double(x1, y1, x2, y2);
		Graphics2D graf2 = (Graphics2D) grafik;
		graf2.draw(�izgi);
		return x1;
	}
	/**
	 * �nce kat�n taban� �izilir bu taban bir alttaki kat�n ya da taban kat�n �at�s�d�r
	 * @param grafik
	 * @param katNo
	 */
	public void kat�iz(Graphics grafik,int katNo){
		Color renk = Color.GREEN;
		grafik.setColor(renk);
		ArrayList<Integer> yeniK�me = gruplananlar.get(katNo);
		int grup1 = yeniK�me.get(0);
		int grup2 = yeniK�me.get(1);
		ArrayList<String> yeniS�ralama = s�ralamalar.get(katNo);
		ArrayList<String> yeniEleman1 = eleman(grup1, yeniS�ralama);
		ArrayList<String> yeniEleman2 = eleman(grup2, yeniS�ralama); 
//		int yeniXDe�eri = (�ncekiKatmandakiTabanXDe�erleri.get(katNo+1) + �ncekiKatmandakiTabanXDe�erleri.get(katNo))/2; // d�zelmelli
//		yeniKatmanXDe�erleriniBul(katNo, yeniXDe�eri);
//		birle�tirici�izgi�iz(grafik, katNo, yeniK�me);
//		t�mKatman��iz(grafik, katNo, yeniXDe�eri);
	}
	private void birle�tirici�izgi�iz(Graphics grafik, int katNo,ArrayList<Integer> yeniK�me) {
//		int x1 = katmandakiTabanXDe�erleri.get(yeniK�me.get(0)); // d�zelmelli
//		int x2 = katmandakiTabanXDe�erleri.get(yeniK�me.get(1)); // d�zelmelli
//		int y1 = katY�ksekli�i*katNo;
//		int y2 = katY�ksekli�i*katNo;
//		grafik.drawLine(x1, y1, x2, y2);
	}
	private void yeniKatmanXDe�erleriniBul(int katNo, int yeniXDe�eri) {
		katmandakiTabanXDe�erleri.remove(katNo);
		katmandakiTabanXDe�erleri.remove(katNo);
		//katmandakiTabanXDe�erleri.add(katNo, yeniXDe�eri);
	}
	private void t�mKatman��iz(Graphics grafik, int katNo, int yeniXDe�eri) {
		yeniKat�izgisi�iz(grafik, katNo, yeniXDe�eri);	
		for(int elemanNo=0;elemanNo<katmandakiTabanXDe�erleri.size();elemanNo++){
			normalKat�izgisi�iz(grafik, katNo, elemanNo);
		}
	}
	private void normalKat�izgisi�iz(Graphics grafik, int katNo, int elemanNo) {
//		//int x1 = katmandakiTabanXDe�erleri.get(elemanNo);
//		int x2 = x1;
//		int y1 = katY�ksekli�i*katNo;
//		int y2 = katY�ksekli�i*(katNo+1);
//		grafik.drawLine(x1, y1, x2, y2);
	}
	private void yeniKat�izgisi�iz(Graphics grafik, int katNo, int yeniXDe�eri) {
		int x1 = yeniXDe�eri;
		int x2 = x1;
		int y1 = katY�ksekli�i*katNo;
		int y2 = katY�ksekli�i*(katNo+1);
		grafik.drawLine(x1, y1, x2, y2);
	}
	// }} �izim i�lemleri
	// {{ s�ralama i�in
	/**
	 * ba� , son
	 * @return
	 */
	public int [] elemanBa�SonBul(int aranan,ArrayList<String> s�ralama){
		int [] ba�Son = new int [2];
		ba�Son[0] = -1;
		ba�Son[1] = -1;
		int parantez = 0;
		int elemanSay = -1;
		for(int elemanNo = 0;elemanNo<s�ralama.size();){
			if(s�ralama.get(elemanNo).compareTo("(") != 0  && parantez == 0){ // parantezsiz eleman i�in
			      elemanSay ++;
			      if( elemanSay == aranan){
			    	  ba�Son[0] = elemanNo;
			    	  ba�Son[1] = elemanNo ;
			    	  break;
			      }
			      elemanNo++;
			}
			else {
				 int parantezden�tesi = 0;
				 for(parantezden�tesi = 0;elemanNo+parantezden�tesi<s�ralama.size();parantezden�tesi++){ // parantezin e�ini bulma
					 if(s�ralama.get(elemanNo+parantezden�tesi).compareTo(")") == 0){
						 parantez --;
					 }
					 if (s�ralama.get(elemanNo+parantezden�tesi).compareTo("(") == 0){
						 parantez ++;
					 }
					 if(parantez == 0){
						 break;
					 }
				 }
				 elemanSay ++;
				 if(elemanSay == aranan){  // aranan eleman bulundu ise
					 ba�Son[0] = elemanNo;
					 ba�Son[1] = elemanNo + parantezden�tesi ;
					 break;
				 }
				 elemanNo += parantezden�tesi +1; // i� parantezler ile kar��la�mas�n diye
			}
			
		}
		return ba�Son;
	}
	public ArrayList<String> gruplamaYap(int eleman1,int eleman2,ArrayList<String> s�ralama){
		ArrayList<String> cevap = new ArrayList<String>();
		cevap.addAll(s�ralama);
		int [] eleman1Ba�Son = elemanBa�SonBul(eleman1, cevap );
		int [] eleman2Ba�Son = elemanBa�SonBul(eleman2, cevap );
		yeniyiEkleEskiyiSil(cevap , eleman1Ba�Son, eleman2Ba�Son);
		return cevap;
	}
	private void yeniyiEkleEskiyiSil(ArrayList<String> s�ralama,int[] eleman1Ba�Son, int[] eleman2Ba�Son) {
		ArrayList<String> yenigrup = yeniGrubuOlu�tur(s�ralama, eleman1Ba�Son,eleman2Ba�Son);
		eskiGruplar�Sil(s�ralama, eleman1Ba�Son, eleman2Ba�Son);
		yeniGrubuEkle(s�ralama, yenigrup);
	}
	private void yeniGrubuEkle(ArrayList<String> s�ralama, ArrayList<String> yenigrup) {
		s�ralama.add("(");
		s�ralama.addAll(yenigrup);
		s�ralama.add(")");
	}
	private ArrayList<String> yeniGrubuOlu�tur(ArrayList<String> s�ralama,int[] eleman1Ba�Son, int[] eleman2Ba�Son) {
		ArrayList<String> grup1 = aras�n�Al(s�ralama, eleman1Ba�Son[0], eleman1Ba�Son[1]+1);
		ArrayList<String> grup2 = aras�n�Al(s�ralama, eleman2Ba�Son[0], eleman2Ba�Son[1]+1);
		ArrayList<String> yenigrup = new ArrayList<String>();
		yenigrup.addAll(grup1);
		yenigrup.addAll(grup2);
		return yenigrup;
	}
	private void eskiGruplar�Sil(ArrayList<String> s�ralama,int[] eleman1Ba�Son, int[] eleman2Ba�Son) {
		if(eleman1Ba�Son[0] < eleman2Ba�Son[0]){ // indexe te  kayma olacak
			elemanSil(s�ralama, eleman1Ba�Son, eleman2Ba�Son);
		}
		else{
			elemanSil(s�ralama, eleman2Ba�Son, eleman1Ba�Son);
		}
	}
	private void elemanSil(ArrayList<String> s�ralama, int[] eleman1Ba�Son,int[] eleman2Ba�Son) {
		int kayma = eleman1Ba�Son[1] - eleman1Ba�Son[0] +1;
		topluSilme(s�ralama, eleman1Ba�Son[0] , eleman1Ba�Son[1]+1);
		topluSilme(s�ralama, eleman2Ba�Son[0]-kayma, eleman2Ba�Son[1]-kayma+1);
	}
	public ArrayList<String> aras�n�Al( ArrayList<String> s�ralama,int ba�,int son){
		ArrayList<String> par�a = new ArrayList<String>();
		for(int elemanNo = ba�;elemanNo<son;elemanNo++){
			par�a.add(s�ralama.get(elemanNo));
		}
		return par�a;
	}
	public void topluSilme(ArrayList<String> s�ralama,int ba�,int son){
		for(int elemanNo=ba�;elemanNo<son;elemanNo++){
			s�ralama.remove(ba�);
		}
	}
	
    /**
     * gruplananalr taranacak
     */
	public void s�ralamay�Olu�tur(){
		ilkS�ralama();
		for(int elemanNo = 0;elemanNo <gruplananlar.size();elemanNo++){
			yeniS�ralamaEkle(elemanNo);
		}
	}
	private void yeniS�ralamaEkle(int elemanNo) {
		ArrayList<String> yeniS�ralama = yeniS�ralamaOlu�tur(elemanNo);
		s�ralamalar.add(yeniS�ralama);
	}
	private ArrayList<String> yeniS�ralamaOlu�tur(int elemanNo) {
		ArrayList<Integer> yeniK�me = gruplananlar.get(elemanNo); 
		int grup1 = yeniK�me.get(0);
		int grup2 = yeniK�me.get(1);
		ArrayList<String> yeniS�ralama = new ArrayList<String>();
		ArrayList<String> bir�stSeviye = s�ralamalar.get(elemanNo);
		yeniS�ralama = gruplamaYap(grup1, grup2, bir�stSeviye);
		return yeniS�ralama;
	}
    private void ilkS�ralama (){
    	ArrayList<String> ilkS�ralama = new ArrayList<String>();
    	for(int elemanNo=0;elemanNo<toplamNokta;elemanNo++){
    		String yeniEleman = ""  + elemanNo;
    		ilkS�ralama.add(yeniEleman);
    	}
    	s�ralamalar.add(ilkS�ralama);
    }
    
    public String s�ralamay�Yaz(){
    	String yaz� = "";
    	for(int sat�rNo = 0;sat�rNo<s�ralamalar.size();sat�rNo++){
    		yaz� = s�ralamaSat�r�Yaz(yaz�, sat�rNo);
    	}
    	return yaz�;
    }
	private String s�ralamaSat�r�Yaz(String yaz�, int sat�rNo) {
		ArrayList<String> yeniSat�r = s�ralamalar.get(sat�rNo);
		for(int elemanNo = 0;elemanNo<yeniSat�r.size();elemanNo++){
			yaz� += yeniSat�r.get(elemanNo) + " ";
		}
		yaz� += "\n";
		return yaz�;
	}
    
    public String s�ralamaElemanlar�n�Yaz(ArrayList<String> s�ralama){
    	String yaz� = "";
    	for(int elemanNo = 0;elemanNo<s�ralama.size();elemanNo++){
    		String yeniElemanYaz� = "[";
    		int [] ba�Son = new int [2];
    		ba�Son  = elemanBa�SonBul(elemanNo, s�ralama);
    		ArrayList<String> yeniEleman = aras�n�Al(s�ralama, ba�Son[0], ba�Son[1]);
    		if(ba�Son [0] == -1){
    			break;
    		}
    		else {
    			yaz� = yeniEleman�Yazd�r(yaz�, yeniElemanYaz�, yeniEleman);	
    		}
    	}
    	return yaz�;
    }
	private String yeniEleman�Yazd�r(String yaz�, String yeniElemanYaz�,ArrayList<String> yeniEleman) {
		for(int yaz�No = 0;yaz�No <yeniEleman.size();yaz�No++){
			yeniElemanYaz� += yeniEleman.get(yaz�No) + ",";
		}
		yeniElemanYaz� += "]";
		yaz� += yeniElemanYaz� + "\n";
		return yaz�;
	}
	
	public ArrayList<String> eleman ( int eleman,ArrayList<String> s�ralama){
		int [] ba�Son = new int [2];
		ba�Son = elemanBa�SonBul(eleman, s�ralama);
		return aras�n�Al(s�ralama, ba�Son[0], ba�Son[1]);
	}
	// }} s�ralama i�in
     
	// {{ s�ralama 2 

	
	// }} s�ralama 2 

}


