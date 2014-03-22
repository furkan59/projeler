package k�meci;

// k�me say�s� sat�r say�s�ndan b�y�k olmamal�

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.StyledEditorKit.BoldAction;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JScrollPane;

import metaSezgisel.K_Ortalamalar;
import metaSezgisel.Par�ac�kS�r�;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import s�k��t�r�lm��Ba�lant�l�.s�k��t�r�lm��Ba�Enerji;
import veriYap�lar�.BoolDizi;
import yap�salK�meleme.Ba�Enerji;
import yap�salK�meleme.Ba�lant�l�;
import yap�salK�meleme.Dendogram;
import yap�salK�meleme.DereceS�ra;
import yap�salK�meleme.Do�rusalK�meleme;
import yap�salK�meleme.K�meBulma;
import yap�salK�meleme.K�meBulmaA�a�l�;
import yap�salK�meleme.OrtalamaBa�lant�l�List;
import yap�salK�meleme.TekBa�lant�l�;
import S�k��t�r�lm��VeriYap�lar�.*;
import S�k��t�r�lm��MetaSezgisel.*;

public class AnaEkran extends JFrame {

	private static final long serialVersionUID = 1L;
	public y�ntemSayfas� y�ntem = new y�ntemSayfas�();;
	public veri�retmeEkran� vr ;

	public JScrollPane scrollPane ;
	public JTextArea girdiYeri ;
	public JButton btnal ;
	private JButton btnYntemSe;
	private JButton btnVeriret;

	private boolean hi�BirY�ntemSe�ilmedi = true;
	private boolean y�ntemSayfas�A��lmad� = true;
	private String hatalar  = "";
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnaEkran frame = new AnaEkran();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AnaEkran() {
		�ekliOlu�tur();
		tu�lar();
	}
	private void tu�lar() {
		yeniSayfaTu�u();
		y�ntemSayfas�Tu�u();
		veri�retmeTu�u();
		�al��Tu�u();
	}
	private void �al��Tu�u() {
		// �al�� tu�u
		btnal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				veriGirilmi�MiBak();
				y�ntemSayfas�n�KontrolEt();
				veri�retmeTu�u();
				if(y�ntem.a�amalar�G�ster.isSelected()){
					metaSezgisel��z();
					yap�sal��z();
				}
				else{
					metaSezgiselH�zl���z();
					yap�salH�zl���z();
				}
				hatalar�Yaz();
			}

			private void metaSezgiselH�zl���z() {
				if(y�ntem.par�ac�kS�r�Se�ildiMi){
					par�ac�kS�r�H�zl���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.kOrtalamalar.isSelected()){
					kOrtalamalarH�zl���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
			}
			private void par�ac�kS�r�H�zl���z() { 
				String girdi = girdiYeri.getText();
				try {
					par�ac�kS�r�H�zl���z2(girdi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			private void par�ac�kS�r�H�zl���z2(String girdi)throws Exception {
				String ��z�m = "";
				Par�ac�kS�r� s�r� = new Par�ac�kS�r�(girdi, y�ntem.psaAyar.k�meSay�s�,y�ntem.psaAyar.par�ac�kSay�s�);
				k�meSay�s�HataAy�kla(s�r�);
				parametreleriDoldur(s�r�);
				��z�m = par�ac�kS�r���z�m�Yap(y�ntem.psaAyar.a�amalar�G�ster || y�ntem.a�amalar�G�ster.isSelected(), ��z�m, s�r�);
				��kt�Yaz(��z�m);
			}

			private void kOrtalamalarH�zl���z() {
				try {
					K_Ortalamalar ko = new K_Ortalamalar(girdiYeri.getText());
					int denemeSay�s� = kOrtalamalarParametreleriDoldur(ko);
					String ��z�m = "";
					for(int denemeNo = 0;denemeNo<denemeSay�s�;denemeNo++){
						��z�m += denemeNo + "no lu ��z�m \n--------------\n\n\n" + ko.h�zl���z();
					}
					��kt�Yaz(��z�m);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void yap�salH�zl���z() {
				if (y�ntem.ba�lant�l�Se�ildiMi){
					ba�lant�l�H�zl���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.ba�Enerji.isSelected()){
					ba�EnerjiH�zl���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.k�meBulmaY�ntemi.isSelected()){
					k�meBulmaH�zl���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.dereceS�ra.isSelected()){
					dereceS�raH�zl���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
			}
			private void ba�lant�l�H�zl���z() {
				if(y�ntem.ba�lant�l�Ayar.ortalamaBa�lant�l�.isSelected() ){
					ortalamaBa�lant�l�H�zl���z();
				}
				if (y�ntem.ba�lant�l�Ayar.tekBa�lat�l�.isSelected()){
					tekBa�lant�l�H�zl���z();
				}
				if(y�ntem.ba�lant�l�Ayar.do�rusalK�meleme.isSelected()){
					do�rusalK�melemeH�zl���z();
				}
			}
			private void ortalamaBa�lant�l�H�zl���z() {
				try {
					OrtalamaBa�lant�l�List ob = new OrtalamaBa�lant�l�List(girdiYeri.getText());
					ob.dendogramVarM� = true;
					ortalamaBa�lant�l�ParametreleriDoldur(ob);
					��kt�Yaz(ob.h�zl���z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void tekBa�lant�l�H�zl���z() {
				try {
					TekBa�lant�l� tb = new TekBa�lant�l�(girdiYeri.getText());
					tekBa�lant�l�ParametreDoldur(tb);
					��kt�Yaz(tb.h�zl���z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void do�rusalK�melemeH�zl���z() {
				try {
					Do�rusalK�meleme dk = new Do�rusalK�meleme(girdiYeri.getText());
					��kt�Yaz(dk.h�zl���z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void ba�EnerjiH�zl���z() {
				try {
					Ba�Enerji be = new Ba�Enerji(girdiYeri.getText());
					��kt�Yaz(be.h�zl���z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void k�meBulmaH�zl���z() {
				try {
					K�meBulma kb = new K�meBulma(girdiYeri.getText());
					��kt�Yaz(kb.h�zl���z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void dereceS�raH�zl���z() {
				try {
					DereceS�ra drs = new DereceS�ra(girdiYeri.getText());
					��kt�Yaz(drs.h�zl���z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}




			private void veriGirilmi�MiBak() {
				if(girdiYeri.getText().compareTo("") == 0){
					hatalar += "veri girilmemi�\n";
				}
			}
			private void hatalar�Yaz() {
				if(hi�BirY�ntemSe�ilmedi){
					hatalar += "Hi� bir y�ntem se�ilmemi�\n";
				}
				if(hatalar.compareTo("") != 0){
					��kt�Yaz("HATALAR \n" +hatalar);
				}
			}
			private void y�ntemSayfas�n�KontrolEt() {
				if(y�ntemSayfas�A��lmad�){
					hatalar += "Y�ntem sayfas� a��lmam��\n";
					��kt�Yaz(hatalar);
				}
			}
			private void yap�sal��z() {
				if (y�ntem.ba�lant�l�Ayar != null){
					ba�lant�l���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.ba�Enerji.isSelected()){
					ba�Enerji��z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.k�meBulmaY�ntemi.isSelected()){
					k�meBulma��z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.dereceS�ra.isSelected()){
					dereceS�ra��z();
					hi�BirY�ntemSe�ilmedi = false;
				}
			}
			private void metaSezgisel��z() {
				if(y�ntem.psaAyar != null){
					par�ac�kS�r���z();
					hi�BirY�ntemSe�ilmedi = false;
				}
				if(y�ntem.kOrtalamalar.isSelected()){
					kOrtalamalar��z();
					hi�BirY�ntemSe�ilmedi = false;
				}
			}

			private void k�meBulma��z() {
				try {
					K�meBulma kb = new K�meBulma(girdiYeri.getText());
					��kt�Yaz(kb.��z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void dereceS�ra��z() {
				try {
					DereceS�ra drs = new DereceS�ra(girdiYeri.getText());
					��kt�Yaz(drs.��z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void ba�Enerji��z() {
				try {
					Ba�Enerji be = new Ba�Enerji(girdiYeri.getText());
					��kt�Yaz(be.��z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void kOrtalamalar��z() {
				try {
					K_Ortalamalar ko = new K_Ortalamalar(girdiYeri.getText());
					int denemeSay�s� = kOrtalamalarParametreleriDoldur(ko);
					String ��z�m = "";
					for(int denemeNo = 0;denemeNo<denemeSay�s�;denemeNo++){
						��z�m += denemeNo + "no lu ��z�m \n--------------\n\n\n" + ko.��z();
					}
					��kt�Yaz(��z�m);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * parametreleri al�r ve girilen deneme say�s�n� geri d�ner
			 * <br> deneme say�s� ayn� sorunun ka� kere ��z�lece�idir. ��nk� k ortalamlar y�nteminde 
			 * <br> soru ilk k�me merkezlerine ba�l�d�r bu nedenle bir ka� kez ��z�lmelidir
			 * @param ko
			 * @return
			 * @throws Exception
			 */
			private int kOrtalamalarParametreleriDoldur(K_Ortalamalar ko)throws Exception {
				kortalamlarK�meSay�s�Al(ko);
				int denemeSay�s� = kOrtamalarDenemeSay�s�Al();
				return denemeSay�s�;
			}
			private int kOrtamalarDenemeSay�s�Al() {
				String yaz� = y�ntem.denemeSay�s�Yaz�.getText();
				kOrtamalarDenemeSay�s�Girilmi�Mi(yaz�);
				int denemeSay�s� = Integer.parseInt(yaz�);
				kOrtamalarDenemeSay�s�TamM�(yaz�);
				return denemeSay�s�;
			}
			private void kOrtamalarDenemeSay�s�Girilmi�Mi(String yaz�) {
				if(yaz� == ""){
					hatalar += "k ortalamalar i�in deneme say�s� girilmemi�\n";
				}
			}
			private void kOrtamalarDenemeSay�s�TamM�(String yaz�) {
				if(yaz�.contains(",") || yaz�.contains(".")){
					hatalar += "k ortalamalar i�in deneme say�s� tam say� olmal�d�r\n";
				}
			}

			private void kortalamlarK�meSay�s�Al(K_Ortalamalar ko)throws Exception {
				String yaz� = y�ntem.kOrtalamalarK�meSay�s�.getText();
				kOrtamalarK�meSay�s�Girilmi�Mi(yaz�);
				int k�meSay�s� = Integer.parseInt(yaz�);
				kOrtalamalarK�meSay�s�KontrolEt(ko, k�meSay�s�);
				kOrtalamalarK�meSay�s�TamM�(yaz�);
				ko.k�meSay�s�Belirle(k�meSay�s�);
			}
			private void kOrtamalarK�meSay�s�Girilmi�Mi(String yaz�) {
				if(yaz� == ""){
					hatalar += "k ortalamalar i�in k�me say�s� girilmemi�\n";
				}
			}
			private void kOrtalamalarK�meSay�s�TamM�(String yaz�) {
				if(yaz�.contains(",") || yaz�.contains(".")){
					hatalar += "k ortalamalar i�in k�me say�s� tam say� olmal�d�r\n";
				}
			}
			private void kOrtalamalarK�meSay�s�KontrolEt(K_Ortalamalar ko,
					int k�meSay�s�) {
				if(k�meSay�s� > ko.makineSay�s�()){
					hatalar += "k-ortalamalar i�in girilen k�me say�s� makine say�s�ndan fazla olamaz\n"
							+ "makine say�s� : " + ko.makineSay�s�() + " k�me say�s� : " + k�meSay�s� + "\n";
				}
			}

			// BA�LANTILILAR
			private void ba�lant�l���z() {
				if(y�ntem.ba�lant�l�Ayar.ortalamaBa�lant�l�.isSelected() ){
					ortalamaBa�lant�l���z();
				}
				if (y�ntem.ba�lant�l�Ayar.tekBa�lat�l�.isSelected()){
					tekBa�lant�l���z();
				}
				if(y�ntem.ba�lant�l�Ayar.do�rusalK�meleme.isSelected()){
					do�rusalK�meleme��z();
				}
			}
			private void do�rusalK�meleme��z() {
				try {
					Do�rusalK�meleme dk = new Do�rusalK�meleme(girdiYeri.getText());
					��kt�Yaz(dk.��z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void tekBa�lant�l���z() {
				try {
					TekBa�lant�l� tb = new TekBa�lant�l�(girdiYeri.getText());
					tekBa�lant�l�ParametreDoldur(tb);
					��kt�Yaz(tb.��z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void tekBa�lant�l�ParametreDoldur(TekBa�lant�l� tb) {
				if(y�ntem.ba�lant�l�Ayar.enAzK�meSay�s� > tb.makineSay�s�()){
					hatalar += "tek ba�lant�l� k�meleme i�in girilen en az k�me say�s� makine say�s�ndan az olmal�d�r "
							+ "\n en az k�me say�s� : " + y�ntem.ba�lant�l�Ayar.enAzK�meSay�s� + " makine say�s� : " + tb.makineSay�s�() +"\n"; 
				}
				else {
					tekBa�lant�l�ParametreleriAl(tb);
				}
			}
			private void tekBa�lant�l�ParametreleriAl(TekBa�lant�l� tb) {
				if(y�ntem.ba�lant�l�Ayar.enAzK�meSay�s� != -1){
					tb.enAzGrupSay�s� = y�ntem.ba�lant�l�Ayar.enAzK�meSay�s�;
				}
				if(y�ntem.ba�lant�l�Ayar.enFazlaElemanSay�s� != -1){
					tb.gruptakiEnFazlaMakineSay�s� = y�ntem.ba�lant�l�Ayar.enFazlaElemanSay�s�;
				}
				if(y�ntem.ba�lant�l�Ayar.e�ikDe�eri != -1){
					tb.e�ikDe�er = y�ntem.ba�lant�l�Ayar.e�ikDe�eri;
				}
				if(y�ntem.ba�lant�l�Ayar.e�ikDe�eri�arpan� != -1){
					tb.e�ikDe�er�arpan� = y�ntem.ba�lant�l�Ayar.e�ikDe�eri�arpan� ;
				}
			}

			private void ortalamaBa�lant�l���z() {
				try {
					OrtalamaBa�lant�l�List ob = new OrtalamaBa�lant�l�List(girdiYeri.getText());
					ortalamaBa�lant�l�ParametreleriDoldur(ob);
					��kt�Yaz(ob.��z());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void ortalamaBa�lant�l�ParametreleriDoldur(OrtalamaBa�lant�l�List ob) {
				if(y�ntem.ba�lant�l�Ayar.enAzK�meSay�s� > ob.makineSay�s�()){
					hatalar += "ortalama ba�lant�l� i�in girilen en az k�me say�s� makine say�s�ndan az olmal�d�r "
							+ "\n-- en az k�me say�s� : " + y�ntem.ba�lant�l�Ayar.enAzK�meSay�s� + " makine say�s� : " + ob.makineSay�s�() +"\n"; 
				}
				else {
					ortalamaBa�lant�l�ParametreleriniAl(ob);
				}
			}
			private void ortalamaBa�lant�l�ParametreleriniAl(OrtalamaBa�lant�l�List ob) {
				if(y�ntem.ba�lant�l�Ayar.enAzK�meSay�s� != -1){
					ob.enAzGrupSay�s� = y�ntem.ba�lant�l�Ayar.enAzK�meSay�s�;
				}
				if(y�ntem.ba�lant�l�Ayar.enFazlaElemanSay�s� != -1){
					ob.gruptakiEnFazlaMakineSay�s� = y�ntem.ba�lant�l�Ayar.enFazlaElemanSay�s�;
				}
				if(y�ntem.ba�lant�l�Ayar.e�ikDe�eri != -1){
					ob.e�ikDe�er = y�ntem.ba�lant�l�Ayar.e�ikDe�eri;
				}
				if(y�ntem.ba�lant�l�Ayar.e�ikDe�eri�arpan� != -1){
					ob.e�ikDe�er�arpan� = y�ntem.ba�lant�l�Ayar.e�ikDe�eri�arpan� ;
				}
			}
			// BA�LANTILILAR

			private void par�ac�kS�r���z() { 
				String girdi = girdiYeri.getText();
				try {
					par�ac�kS�r���z2(girdi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			private void par�ac�kS�r���z2(String girdi)throws Exception {
				String ��z�m = "";
				Par�ac�kS�r� s�r� = new Par�ac�kS�r�(girdi, y�ntem.psaAyar.k�meSay�s�,y�ntem.psaAyar.par�ac�kSay�s�);
				k�meSay�s�HataAy�kla(s�r�);
				parametreleriDoldur(s�r�);
				��z�m = par�ac�kS�r���z�m�Yap(y�ntem.psaAyar.a�amalar�G�ster || y�ntem.a�amalar�G�ster.isSelected(), ��z�m, s�r�);
				��kt�Yaz(��z�m);
			}
			private void k�meSay�s�HataAy�kla(Par�ac�kS�r� s�r�) {
				if(y�ntem.psaAyar.k�meSay�s� >s�r�.makineSay�s�() ){
					hatalar += "k�me say�s� makine say�s�ndan fazla olamaz -- k�me say�s� " +  y�ntem.psaAyar.k�meSay�s�  + " makine say�s� : " + s�r�.makineSay�s�() + "\n";
				}
			}
			private void parametreleriDoldur(Par�ac�kS�r� s�r�) throws Exception {
				s�r�.g�venParameterileriniBelirle(y�ntem.psaAyar.s�r�yeG�ven, y�ntem.psaAyar.kendineG�ven, y�ntem.psaAyar.eylemsizlik);
				s�r�.ilkH�zBelirle(y�ntem.psaAyar.ilkH�z);
				s�r�.enFazlaA�ama = y�ntem.psaAyar.enFazlaA�ama;
				s�r�.enFazla�lerleme = y�ntem.psaAyar.enFazla�lerleme;
				s�r�.ilerlemeK�s�tl� = y�ntem.psaAyar.ilerlemeK�s�tl�;
			}
			private String par�ac�kS�r���z�m�Yap(boolean a�amalar�G�ster, String ��z�m,Par�ac�kS�r� s�r�) throws Exception {
				if(a�amalar�G�ster){
					��z�m += s�r�.��z();
				}
				else {
					��z�m += s�r�.h�zl���z();
				}
				return ��z�m;
			}


		});
	}

	private void yeniSayfaTu�u() {
		JButton btnYeniSayfa = new JButton("yeni sayfa");
		btnYeniSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ��kt� = "";
				Dendogram yeni = new Dendogram();
				try {
					ArrayList<ArrayList<Integer>> gruplananlar = new ArrayList<ArrayList<Integer>>();
					int toplamNokta = 8;
					
				    for(int sat�rNo = 0;sat�rNo <6;sat�rNo++){
				    	ArrayList<Integer> grup = new ArrayList<Integer>();
				    	gruplananlar.add(grup);
				    }
					gruplananlar.get(0).add(0);
					gruplananlar.get(0).add(8);
					
					gruplananlar.get(1).add(0);
					gruplananlar.get(1).add(7);
					
					gruplananlar.get(2).add(0);
					gruplananlar.get(2).add(1);
					
					gruplananlar.get(3).add(0);
					gruplananlar.get(3).add(1);

					gruplananlar.get(4).add(0);
					gruplananlar.get(4).add(1);
					
					gruplananlar.get(5).add(1);
					gruplananlar.get(5).add(3);
					
					DendogramA�ac� a�a� = new DendogramA�ac�();
					a�a�.a�ac�Doldur(toplamNokta);
					a�a�.a�ac�Grupla(gruplananlar);
					System.out.print(a�a�.yaz());
					
//					yeni.gruplananlar = gruplananlar;
//					yeni.toplamNokta = toplamNokta;
//					yeni.s�ralamay�Olu�tur();
//					System.out.println(yeni.s�ralamay�Yaz());
					// {{ s�ralama denemesi 
					
//					System.out.println(dizi.toString());
//					dizi = yeni.gruplamaYap(0, 6, dizi);
//					System.out.println(dizi.toString());
//					dizi = yeni.gruplamaYap(1, 3, dizi);
//					System.out.println(dizi.toString());
//					dizi = yeni.gruplamaYap(2, 3, dizi);
//					System.out.println(dizi.toString());
					// }} s�ralama denemesi 
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnYeniSayfa.setBounds(818, 21, 110, 23);
		getContentPane().add(btnYeniSayfa);
	}
	private void veri�retmeTu�u() {
		// veri �retme
		btnVeriret = new JButton("veri \u00FCret");
		btnVeriret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vr = new veri�retmeEkran�(girdiYeri);
					vr.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVeriret.setBounds(275, 21, 89, 23);
		getContentPane().add(btnVeriret);
	}
	private void y�ntemSayfas�Tu�u() {
		// y�ntem sayfas�
		btnYntemSe = new JButton("y\u00F6ntem se\u00E7");
		btnYntemSe.addActionListener(new ActionListener() { // y�ntem se�me 
			public void actionPerformed(ActionEvent arg0) {
				y�ntem.setVisible(true);
				y�ntemSayfas�A��lmad� = false;
			}
		});
		btnYntemSe.setBounds(34, 21, 132, 23);
		getContentPane().add(btnYntemSe);
	}

	private void �ekliOlu�tur() {
		getContentPane().setLayout(null);
		getContentPane().setVisible(true);
		setSize(1000, 600);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 76, 881, 431);
		getContentPane().add(scrollPane);

		girdiYeri = new JTextArea();
		scrollPane.setViewportView(girdiYeri);

		btnal = new JButton("\u00E7al\u0131\u015F");
		btnal.setBounds(433, 21, 74, 23);
		getContentPane().add(btnal);

		JLabel lblVeriGirmeYeri = new JLabel("VER\u0130 G\u0130RME YER\u0130");
		lblVeriGirmeYeri.setBounds(391, 55, 142, 14);
		getContentPane().add(lblVeriGirmeYeri);

	}
	private void ��kt�Yaz(String yaz�) {
		��kt� sonu� = new ��kt�();
		sonu�.textArea.setText(yaz�);
		sonu�.setVisible(true);
		sonu�.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public void �izdirme(){

	}
}
