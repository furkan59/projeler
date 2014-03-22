package yapýsalKümeleme;

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
 * dendogram kümelemeyi gösterir
 * <br> yükseklik kesim noktalarý kümeleme yapýldýðý zaman ki iliþki katsayýsýný gösterir
 * <br> bu katsayý giderek artar çünkü gittikçe küme merkezleri birbirinden uzaklaþacaktýr.
 * <br> dendogram çok noktadan tek noktaya doðru çizilecektir
 * @author Furkan
 *
 */
public class Dendogram extends JApplet{
	private static final long serialVersionUID = 1L;
	private ArrayList<Double> öncekiKatmandakiTabanXDeðerleri = new ArrayList<Double>(); // o katmandaki tüm noktalar için
	private ArrayList<Double> katmandakiTabanXDeðerleri = new ArrayList<Double>(); // o katmandaki tüm noktalar için
	public ArrayList<ArrayList<Integer>> gruplananlar = new ArrayList<ArrayList<Integer>>();
	private ArrayList<ArrayList<String>> sýralamalar = new ArrayList<ArrayList<String>>();
	
	public int toplamNokta;
	private int katYüksekliði;
	private int noktalarArasýUzaklýk;
	private int enFazlaAþamaSayýsý;
	public Dendogram(int toplamNokta, int geniþlik,int yükseklik,int enFazlaAþamaSayýsý,ArrayList<ArrayList<Integer>> gruplananlar,ArrayList<ArrayList<Integer>> kümeler) {
		this.toplamNokta = toplamNokta;
		this.katYüksekliði = yükseklik/enFazlaAþamaSayýsý;
		this.noktalarArasýUzaklýk = geniþlik/toplamNokta;
		this.enFazlaAþamaSayýsý = enFazlaAþamaSayýsý;
		this.gruplananlar = gruplananlar;
		þekliOluþturma(geniþlik, yükseklik);
	}
	/**
	 * test amaçlý
	 */
	public Dendogram(){
		
	}
	private void þekliOluþturma(int geniþlik, int yükseklik)throws HeadlessException {
		JFrame f = new JFrame("Dendogram");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {System.exit(0);}
		});
		f.getContentPane().add("Center", this);
		init();
		f.pack();
		f.setSize(new Dimension(geniþlik,yükseklik));
		f.setVisible(true);
	}
	// {{ çizim iþlemleri
	public void paint (Graphics grafik){
		tabanÇiz(grafik);
		int katSayýsý;
		if(gruplananlar.size() < enFazlaAþamaSayýsý){
			katSayýsý = gruplananlar.size();
		}
		else{
			katSayýsý = enFazlaAþamaSayýsý;
		}
        for(int katNo = 0;katNo<katSayýsý;katNo++){
			katÇiz(grafik, katNo);
		}
	}
	private void tabanÇiz(Graphics grafik) {
		double boþluk = 40;
		for(int noktaNo=0;noktaNo<toplamNokta;noktaNo++){
			double x1 = noktalarArasýUzaklýk*noktaNo;
			double x2 = x1;
			double y1 = 0;
			double y2 = katYüksekliði;
			öncekiKatmandakiTabanXDeðerleri.add(x1);
			katmandakiTabanXDeðerleri.add(x1);
		}
		katmandakiTabanXDeðerleri.addAll(öncekiKatmandakiTabanXDeðerleri);
	}
	private double dikeyÇizgiÇiz(Graphics grafik, double x1, double y1, double x2, double y2) {
		Line2D çizgi = new Line2D.Double(x1, y1, x2, y2);
		Graphics2D graf2 = (Graphics2D) grafik;
		graf2.draw(çizgi);
		return x1;
	}
	/**
	 * önce katýn tabaný çizilir bu taban bir alttaki katýn ya da taban katýn çatýsýdýr
	 * @param grafik
	 * @param katNo
	 */
	public void katÇiz(Graphics grafik,int katNo){
		Color renk = Color.GREEN;
		grafik.setColor(renk);
		ArrayList<Integer> yeniKüme = gruplananlar.get(katNo);
		int grup1 = yeniKüme.get(0);
		int grup2 = yeniKüme.get(1);
		ArrayList<String> yeniSýralama = sýralamalar.get(katNo);
		ArrayList<String> yeniEleman1 = eleman(grup1, yeniSýralama);
		ArrayList<String> yeniEleman2 = eleman(grup2, yeniSýralama); 
//		int yeniXDeðeri = (öncekiKatmandakiTabanXDeðerleri.get(katNo+1) + öncekiKatmandakiTabanXDeðerleri.get(katNo))/2; // düzelmelli
//		yeniKatmanXDeðerleriniBul(katNo, yeniXDeðeri);
//		birleþtiriciÇizgiÇiz(grafik, katNo, yeniKüme);
//		tümKatmanýÇiz(grafik, katNo, yeniXDeðeri);
	}
	private void birleþtiriciÇizgiÇiz(Graphics grafik, int katNo,ArrayList<Integer> yeniKüme) {
//		int x1 = katmandakiTabanXDeðerleri.get(yeniKüme.get(0)); // düzelmelli
//		int x2 = katmandakiTabanXDeðerleri.get(yeniKüme.get(1)); // düzelmelli
//		int y1 = katYüksekliði*katNo;
//		int y2 = katYüksekliði*katNo;
//		grafik.drawLine(x1, y1, x2, y2);
	}
	private void yeniKatmanXDeðerleriniBul(int katNo, int yeniXDeðeri) {
		katmandakiTabanXDeðerleri.remove(katNo);
		katmandakiTabanXDeðerleri.remove(katNo);
		//katmandakiTabanXDeðerleri.add(katNo, yeniXDeðeri);
	}
	private void tümKatmanýÇiz(Graphics grafik, int katNo, int yeniXDeðeri) {
		yeniKatÇizgisiÇiz(grafik, katNo, yeniXDeðeri);	
		for(int elemanNo=0;elemanNo<katmandakiTabanXDeðerleri.size();elemanNo++){
			normalKatÇizgisiÇiz(grafik, katNo, elemanNo);
		}
	}
	private void normalKatÇizgisiÇiz(Graphics grafik, int katNo, int elemanNo) {
//		//int x1 = katmandakiTabanXDeðerleri.get(elemanNo);
//		int x2 = x1;
//		int y1 = katYüksekliði*katNo;
//		int y2 = katYüksekliði*(katNo+1);
//		grafik.drawLine(x1, y1, x2, y2);
	}
	private void yeniKatÇizgisiÇiz(Graphics grafik, int katNo, int yeniXDeðeri) {
		int x1 = yeniXDeðeri;
		int x2 = x1;
		int y1 = katYüksekliði*katNo;
		int y2 = katYüksekliði*(katNo+1);
		grafik.drawLine(x1, y1, x2, y2);
	}
	// }} çizim iþlemleri
	// {{ sýralama için
	/**
	 * baþ , son
	 * @return
	 */
	public int [] elemanBaþSonBul(int aranan,ArrayList<String> sýralama){
		int [] baþSon = new int [2];
		baþSon[0] = -1;
		baþSon[1] = -1;
		int parantez = 0;
		int elemanSay = -1;
		for(int elemanNo = 0;elemanNo<sýralama.size();){
			if(sýralama.get(elemanNo).compareTo("(") != 0  && parantez == 0){ // parantezsiz eleman için
			      elemanSay ++;
			      if( elemanSay == aranan){
			    	  baþSon[0] = elemanNo;
			    	  baþSon[1] = elemanNo ;
			    	  break;
			      }
			      elemanNo++;
			}
			else {
				 int parantezdenÖtesi = 0;
				 for(parantezdenÖtesi = 0;elemanNo+parantezdenÖtesi<sýralama.size();parantezdenÖtesi++){ // parantezin eþini bulma
					 if(sýralama.get(elemanNo+parantezdenÖtesi).compareTo(")") == 0){
						 parantez --;
					 }
					 if (sýralama.get(elemanNo+parantezdenÖtesi).compareTo("(") == 0){
						 parantez ++;
					 }
					 if(parantez == 0){
						 break;
					 }
				 }
				 elemanSay ++;
				 if(elemanSay == aranan){  // aranan eleman bulundu ise
					 baþSon[0] = elemanNo;
					 baþSon[1] = elemanNo + parantezdenÖtesi ;
					 break;
				 }
				 elemanNo += parantezdenÖtesi +1; // iç parantezler ile karþýlaþmasýn diye
			}
			
		}
		return baþSon;
	}
	public ArrayList<String> gruplamaYap(int eleman1,int eleman2,ArrayList<String> sýralama){
		ArrayList<String> cevap = new ArrayList<String>();
		cevap.addAll(sýralama);
		int [] eleman1BaþSon = elemanBaþSonBul(eleman1, cevap );
		int [] eleman2BaþSon = elemanBaþSonBul(eleman2, cevap );
		yeniyiEkleEskiyiSil(cevap , eleman1BaþSon, eleman2BaþSon);
		return cevap;
	}
	private void yeniyiEkleEskiyiSil(ArrayList<String> sýralama,int[] eleman1BaþSon, int[] eleman2BaþSon) {
		ArrayList<String> yenigrup = yeniGrubuOluþtur(sýralama, eleman1BaþSon,eleman2BaþSon);
		eskiGruplarýSil(sýralama, eleman1BaþSon, eleman2BaþSon);
		yeniGrubuEkle(sýralama, yenigrup);
	}
	private void yeniGrubuEkle(ArrayList<String> sýralama, ArrayList<String> yenigrup) {
		sýralama.add("(");
		sýralama.addAll(yenigrup);
		sýralama.add(")");
	}
	private ArrayList<String> yeniGrubuOluþtur(ArrayList<String> sýralama,int[] eleman1BaþSon, int[] eleman2BaþSon) {
		ArrayList<String> grup1 = arasýnýAl(sýralama, eleman1BaþSon[0], eleman1BaþSon[1]+1);
		ArrayList<String> grup2 = arasýnýAl(sýralama, eleman2BaþSon[0], eleman2BaþSon[1]+1);
		ArrayList<String> yenigrup = new ArrayList<String>();
		yenigrup.addAll(grup1);
		yenigrup.addAll(grup2);
		return yenigrup;
	}
	private void eskiGruplarýSil(ArrayList<String> sýralama,int[] eleman1BaþSon, int[] eleman2BaþSon) {
		if(eleman1BaþSon[0] < eleman2BaþSon[0]){ // indexe te  kayma olacak
			elemanSil(sýralama, eleman1BaþSon, eleman2BaþSon);
		}
		else{
			elemanSil(sýralama, eleman2BaþSon, eleman1BaþSon);
		}
	}
	private void elemanSil(ArrayList<String> sýralama, int[] eleman1BaþSon,int[] eleman2BaþSon) {
		int kayma = eleman1BaþSon[1] - eleman1BaþSon[0] +1;
		topluSilme(sýralama, eleman1BaþSon[0] , eleman1BaþSon[1]+1);
		topluSilme(sýralama, eleman2BaþSon[0]-kayma, eleman2BaþSon[1]-kayma+1);
	}
	public ArrayList<String> arasýnýAl( ArrayList<String> sýralama,int baþ,int son){
		ArrayList<String> parça = new ArrayList<String>();
		for(int elemanNo = baþ;elemanNo<son;elemanNo++){
			parça.add(sýralama.get(elemanNo));
		}
		return parça;
	}
	public void topluSilme(ArrayList<String> sýralama,int baþ,int son){
		for(int elemanNo=baþ;elemanNo<son;elemanNo++){
			sýralama.remove(baþ);
		}
	}
	
    /**
     * gruplananalr taranacak
     */
	public void sýralamayýOluþtur(){
		ilkSýralama();
		for(int elemanNo = 0;elemanNo <gruplananlar.size();elemanNo++){
			yeniSýralamaEkle(elemanNo);
		}
	}
	private void yeniSýralamaEkle(int elemanNo) {
		ArrayList<String> yeniSýralama = yeniSýralamaOluþtur(elemanNo);
		sýralamalar.add(yeniSýralama);
	}
	private ArrayList<String> yeniSýralamaOluþtur(int elemanNo) {
		ArrayList<Integer> yeniKüme = gruplananlar.get(elemanNo); 
		int grup1 = yeniKüme.get(0);
		int grup2 = yeniKüme.get(1);
		ArrayList<String> yeniSýralama = new ArrayList<String>();
		ArrayList<String> birÜstSeviye = sýralamalar.get(elemanNo);
		yeniSýralama = gruplamaYap(grup1, grup2, birÜstSeviye);
		return yeniSýralama;
	}
    private void ilkSýralama (){
    	ArrayList<String> ilkSýralama = new ArrayList<String>();
    	for(int elemanNo=0;elemanNo<toplamNokta;elemanNo++){
    		String yeniEleman = ""  + elemanNo;
    		ilkSýralama.add(yeniEleman);
    	}
    	sýralamalar.add(ilkSýralama);
    }
    
    public String sýralamayýYaz(){
    	String yazý = "";
    	for(int satýrNo = 0;satýrNo<sýralamalar.size();satýrNo++){
    		yazý = sýralamaSatýrýYaz(yazý, satýrNo);
    	}
    	return yazý;
    }
	private String sýralamaSatýrýYaz(String yazý, int satýrNo) {
		ArrayList<String> yeniSatýr = sýralamalar.get(satýrNo);
		for(int elemanNo = 0;elemanNo<yeniSatýr.size();elemanNo++){
			yazý += yeniSatýr.get(elemanNo) + " ";
		}
		yazý += "\n";
		return yazý;
	}
    
    public String sýralamaElemanlarýnýYaz(ArrayList<String> sýralama){
    	String yazý = "";
    	for(int elemanNo = 0;elemanNo<sýralama.size();elemanNo++){
    		String yeniElemanYazý = "[";
    		int [] baþSon = new int [2];
    		baþSon  = elemanBaþSonBul(elemanNo, sýralama);
    		ArrayList<String> yeniEleman = arasýnýAl(sýralama, baþSon[0], baþSon[1]);
    		if(baþSon [0] == -1){
    			break;
    		}
    		else {
    			yazý = yeniElemanýYazdýr(yazý, yeniElemanYazý, yeniEleman);	
    		}
    	}
    	return yazý;
    }
	private String yeniElemanýYazdýr(String yazý, String yeniElemanYazý,ArrayList<String> yeniEleman) {
		for(int yazýNo = 0;yazýNo <yeniEleman.size();yazýNo++){
			yeniElemanYazý += yeniEleman.get(yazýNo) + ",";
		}
		yeniElemanYazý += "]";
		yazý += yeniElemanYazý + "\n";
		return yazý;
	}
	
	public ArrayList<String> eleman ( int eleman,ArrayList<String> sýralama){
		int [] baþSon = new int [2];
		baþSon = elemanBaþSonBul(eleman, sýralama);
		return arasýnýAl(sýralama, baþSon[0], baþSon[1]);
	}
	// }} sýralama için
     
	// {{ sýralama 2 

	
	// }} sýralama 2 

}


