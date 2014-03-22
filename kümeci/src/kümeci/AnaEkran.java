package kümeci;

// küme sayýsý satýr sayýsýndan büyük olmamalý

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
import metaSezgisel.ParçacýkSürü;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import sýkýþtýrýlmýþBaðlantýlý.sýkýþtýrýlmýþBaðEnerji;
import veriYapýlarý.BoolDizi;
import yapýsalKümeleme.BaðEnerji;
import yapýsalKümeleme.Baðlantýlý;
import yapýsalKümeleme.Dendogram;
import yapýsalKümeleme.DereceSýra;
import yapýsalKümeleme.DoðrusalKümeleme;
import yapýsalKümeleme.KümeBulma;
import yapýsalKümeleme.KümeBulmaAðaçlý;
import yapýsalKümeleme.OrtalamaBaðlantýlýList;
import yapýsalKümeleme.TekBaðlantýlý;
import SýkýþtýrýlmýþVeriYapýlarý.*;
import SýkýþtýrýlmýþMetaSezgisel.*;

public class AnaEkran extends JFrame {

	private static final long serialVersionUID = 1L;
	public yöntemSayfasý yöntem = new yöntemSayfasý();;
	public veriÜretmeEkraný vr ;

	public JScrollPane scrollPane ;
	public JTextArea girdiYeri ;
	public JButton btnal ;
	private JButton btnYntemSe;
	private JButton btnVeriret;

	private boolean hiçBirYöntemSeçilmedi = true;
	private boolean yöntemSayfasýAçýlmadý = true;
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
		þekliOluþtur();
		tuþlar();
	}
	private void tuþlar() {
		yeniSayfaTuþu();
		yöntemSayfasýTuþu();
		veriÜretmeTuþu();
		çalýþTuþu();
	}
	private void çalýþTuþu() {
		// çalýþ tuþu
		btnal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				veriGirilmiþMiBak();
				yöntemSayfasýnýKontrolEt();
				veriÜretmeTuþu();
				if(yöntem.aþamalarýGöster.isSelected()){
					metaSezgiselÇöz();
					yapýsalÇöz();
				}
				else{
					metaSezgiselHýzlýÇöz();
					yapýsalHýzlýÇöz();
				}
				hatalarýYaz();
			}

			private void metaSezgiselHýzlýÇöz() {
				if(yöntem.parçacýkSürüSeçildiMi){
					parçacýkSürüHýzlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.kOrtalamalar.isSelected()){
					kOrtalamalarHýzlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
			}
			private void parçacýkSürüHýzlýÇöz() { 
				String girdi = girdiYeri.getText();
				try {
					parçacýkSürüHýzlýÇöz2(girdi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			private void parçacýkSürüHýzlýÇöz2(String girdi)throws Exception {
				String çözüm = "";
				ParçacýkSürü sürü = new ParçacýkSürü(girdi, yöntem.psaAyar.kümeSayýsý,yöntem.psaAyar.parçacýkSayýsý);
				kümeSayýsýHataAyýkla(sürü);
				parametreleriDoldur(sürü);
				çözüm = parçacýkSürüÇözümüYap(yöntem.psaAyar.aþamalarýGöster || yöntem.aþamalarýGöster.isSelected(), çözüm, sürü);
				çýktýYaz(çözüm);
			}

			private void kOrtalamalarHýzlýÇöz() {
				try {
					K_Ortalamalar ko = new K_Ortalamalar(girdiYeri.getText());
					int denemeSayýsý = kOrtalamalarParametreleriDoldur(ko);
					String çözüm = "";
					for(int denemeNo = 0;denemeNo<denemeSayýsý;denemeNo++){
						çözüm += denemeNo + "no lu çözüm \n--------------\n\n\n" + ko.hýzlýÇöz();
					}
					çýktýYaz(çözüm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void yapýsalHýzlýÇöz() {
				if (yöntem.baðlantýlýSeçildiMi){
					baðlantýlýHýzlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.baðEnerji.isSelected()){
					baðEnerjiHýzlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.kümeBulmaYöntemi.isSelected()){
					kümeBulmaHýzlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.dereceSýra.isSelected()){
					dereceSýraHýzlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
			}
			private void baðlantýlýHýzlýÇöz() {
				if(yöntem.baðlantýlýAyar.ortalamaBaðlantýlý.isSelected() ){
					ortalamaBaðlantýlýHýzlýÇöz();
				}
				if (yöntem.baðlantýlýAyar.tekBaðlatýlý.isSelected()){
					tekBaðlantýlýHýzlýÇöz();
				}
				if(yöntem.baðlantýlýAyar.doðrusalKümeleme.isSelected()){
					doðrusalKümelemeHýzlýÇöz();
				}
			}
			private void ortalamaBaðlantýlýHýzlýÇöz() {
				try {
					OrtalamaBaðlantýlýList ob = new OrtalamaBaðlantýlýList(girdiYeri.getText());
					ob.dendogramVarMý = true;
					ortalamaBaðlantýlýParametreleriDoldur(ob);
					çýktýYaz(ob.hýzlýÇöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void tekBaðlantýlýHýzlýÇöz() {
				try {
					TekBaðlantýlý tb = new TekBaðlantýlý(girdiYeri.getText());
					tekBaðlantýlýParametreDoldur(tb);
					çýktýYaz(tb.hýzlýÇöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void doðrusalKümelemeHýzlýÇöz() {
				try {
					DoðrusalKümeleme dk = new DoðrusalKümeleme(girdiYeri.getText());
					çýktýYaz(dk.hýzlýÇöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void baðEnerjiHýzlýÇöz() {
				try {
					BaðEnerji be = new BaðEnerji(girdiYeri.getText());
					çýktýYaz(be.hýzlýÇöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void kümeBulmaHýzlýÇöz() {
				try {
					KümeBulma kb = new KümeBulma(girdiYeri.getText());
					çýktýYaz(kb.hýzlýÇöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void dereceSýraHýzlýÇöz() {
				try {
					DereceSýra drs = new DereceSýra(girdiYeri.getText());
					çýktýYaz(drs.hýzlýÇöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}




			private void veriGirilmiþMiBak() {
				if(girdiYeri.getText().compareTo("") == 0){
					hatalar += "veri girilmemiþ\n";
				}
			}
			private void hatalarýYaz() {
				if(hiçBirYöntemSeçilmedi){
					hatalar += "Hiç bir yöntem seçilmemiþ\n";
				}
				if(hatalar.compareTo("") != 0){
					çýktýYaz("HATALAR \n" +hatalar);
				}
			}
			private void yöntemSayfasýnýKontrolEt() {
				if(yöntemSayfasýAçýlmadý){
					hatalar += "Yöntem sayfasý açýlmamýþ\n";
					çýktýYaz(hatalar);
				}
			}
			private void yapýsalÇöz() {
				if (yöntem.baðlantýlýAyar != null){
					baðlantýlýÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.baðEnerji.isSelected()){
					baðEnerjiÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.kümeBulmaYöntemi.isSelected()){
					kümeBulmaÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.dereceSýra.isSelected()){
					dereceSýraÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
			}
			private void metaSezgiselÇöz() {
				if(yöntem.psaAyar != null){
					parçacýkSürüÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
				if(yöntem.kOrtalamalar.isSelected()){
					kOrtalamalarÇöz();
					hiçBirYöntemSeçilmedi = false;
				}
			}

			private void kümeBulmaÇöz() {
				try {
					KümeBulma kb = new KümeBulma(girdiYeri.getText());
					çýktýYaz(kb.çöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void dereceSýraÇöz() {
				try {
					DereceSýra drs = new DereceSýra(girdiYeri.getText());
					çýktýYaz(drs.çöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void baðEnerjiÇöz() {
				try {
					BaðEnerji be = new BaðEnerji(girdiYeri.getText());
					çýktýYaz(be.çöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void kOrtalamalarÇöz() {
				try {
					K_Ortalamalar ko = new K_Ortalamalar(girdiYeri.getText());
					int denemeSayýsý = kOrtalamalarParametreleriDoldur(ko);
					String çözüm = "";
					for(int denemeNo = 0;denemeNo<denemeSayýsý;denemeNo++){
						çözüm += denemeNo + "no lu çözüm \n--------------\n\n\n" + ko.çöz();
					}
					çýktýYaz(çözüm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/**
			 * parametreleri alýr ve girilen deneme sayýsýný geri döner
			 * <br> deneme sayýsý ayný sorunun kaç kere çözüleceðidir. Çünkü k ortalamlar yönteminde 
			 * <br> soru ilk küme merkezlerine baðlýdýr bu nedenle bir kaç kez çözülmelidir
			 * @param ko
			 * @return
			 * @throws Exception
			 */
			private int kOrtalamalarParametreleriDoldur(K_Ortalamalar ko)throws Exception {
				kortalamlarKümeSayýsýAl(ko);
				int denemeSayýsý = kOrtamalarDenemeSayýsýAl();
				return denemeSayýsý;
			}
			private int kOrtamalarDenemeSayýsýAl() {
				String yazý = yöntem.denemeSayýsýYazý.getText();
				kOrtamalarDenemeSayýsýGirilmiþMi(yazý);
				int denemeSayýsý = Integer.parseInt(yazý);
				kOrtamalarDenemeSayýsýTamMý(yazý);
				return denemeSayýsý;
			}
			private void kOrtamalarDenemeSayýsýGirilmiþMi(String yazý) {
				if(yazý == ""){
					hatalar += "k ortalamalar için deneme sayýsý girilmemiþ\n";
				}
			}
			private void kOrtamalarDenemeSayýsýTamMý(String yazý) {
				if(yazý.contains(",") || yazý.contains(".")){
					hatalar += "k ortalamalar için deneme sayýsý tam sayý olmalýdýr\n";
				}
			}

			private void kortalamlarKümeSayýsýAl(K_Ortalamalar ko)throws Exception {
				String yazý = yöntem.kOrtalamalarKümeSayýsý.getText();
				kOrtamalarKümeSayýsýGirilmiþMi(yazý);
				int kümeSayýsý = Integer.parseInt(yazý);
				kOrtalamalarKümeSayýsýKontrolEt(ko, kümeSayýsý);
				kOrtalamalarKümeSayýsýTamMý(yazý);
				ko.kümeSayýsýBelirle(kümeSayýsý);
			}
			private void kOrtamalarKümeSayýsýGirilmiþMi(String yazý) {
				if(yazý == ""){
					hatalar += "k ortalamalar için küme sayýsý girilmemiþ\n";
				}
			}
			private void kOrtalamalarKümeSayýsýTamMý(String yazý) {
				if(yazý.contains(",") || yazý.contains(".")){
					hatalar += "k ortalamalar için küme sayýsý tam sayý olmalýdýr\n";
				}
			}
			private void kOrtalamalarKümeSayýsýKontrolEt(K_Ortalamalar ko,
					int kümeSayýsý) {
				if(kümeSayýsý > ko.makineSayýsý()){
					hatalar += "k-ortalamalar için girilen küme sayýsý makine sayýsýndan fazla olamaz\n"
							+ "makine sayýsý : " + ko.makineSayýsý() + " küme sayýsý : " + kümeSayýsý + "\n";
				}
			}

			// BAÐLANTILILAR
			private void baðlantýlýÇöz() {
				if(yöntem.baðlantýlýAyar.ortalamaBaðlantýlý.isSelected() ){
					ortalamaBaðlantýlýÇöz();
				}
				if (yöntem.baðlantýlýAyar.tekBaðlatýlý.isSelected()){
					tekBaðlantýlýÇöz();
				}
				if(yöntem.baðlantýlýAyar.doðrusalKümeleme.isSelected()){
					doðrusalKümelemeÇöz();
				}
			}
			private void doðrusalKümelemeÇöz() {
				try {
					DoðrusalKümeleme dk = new DoðrusalKümeleme(girdiYeri.getText());
					çýktýYaz(dk.çöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			private void tekBaðlantýlýÇöz() {
				try {
					TekBaðlantýlý tb = new TekBaðlantýlý(girdiYeri.getText());
					tekBaðlantýlýParametreDoldur(tb);
					çýktýYaz(tb.çöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void tekBaðlantýlýParametreDoldur(TekBaðlantýlý tb) {
				if(yöntem.baðlantýlýAyar.enAzKümeSayýsý > tb.makineSayýsý()){
					hatalar += "tek baðlantýlý kümeleme için girilen en az küme sayýsý makine sayýsýndan az olmalýdýr "
							+ "\n en az küme sayýsý : " + yöntem.baðlantýlýAyar.enAzKümeSayýsý + " makine sayýsý : " + tb.makineSayýsý() +"\n"; 
				}
				else {
					tekBaðlantýlýParametreleriAl(tb);
				}
			}
			private void tekBaðlantýlýParametreleriAl(TekBaðlantýlý tb) {
				if(yöntem.baðlantýlýAyar.enAzKümeSayýsý != -1){
					tb.enAzGrupSayýsý = yöntem.baðlantýlýAyar.enAzKümeSayýsý;
				}
				if(yöntem.baðlantýlýAyar.enFazlaElemanSayýsý != -1){
					tb.gruptakiEnFazlaMakineSayýsý = yöntem.baðlantýlýAyar.enFazlaElemanSayýsý;
				}
				if(yöntem.baðlantýlýAyar.eþikDeðeri != -1){
					tb.eþikDeðer = yöntem.baðlantýlýAyar.eþikDeðeri;
				}
				if(yöntem.baðlantýlýAyar.eþikDeðeriÇarpaný != -1){
					tb.eþikDeðerÇarpaný = yöntem.baðlantýlýAyar.eþikDeðeriÇarpaný ;
				}
			}

			private void ortalamaBaðlantýlýÇöz() {
				try {
					OrtalamaBaðlantýlýList ob = new OrtalamaBaðlantýlýList(girdiYeri.getText());
					ortalamaBaðlantýlýParametreleriDoldur(ob);
					çýktýYaz(ob.çöz());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			private void ortalamaBaðlantýlýParametreleriDoldur(OrtalamaBaðlantýlýList ob) {
				if(yöntem.baðlantýlýAyar.enAzKümeSayýsý > ob.makineSayýsý()){
					hatalar += "ortalama baðlantýlý için girilen en az küme sayýsý makine sayýsýndan az olmalýdýr "
							+ "\n-- en az küme sayýsý : " + yöntem.baðlantýlýAyar.enAzKümeSayýsý + " makine sayýsý : " + ob.makineSayýsý() +"\n"; 
				}
				else {
					ortalamaBaðlantýlýParametreleriniAl(ob);
				}
			}
			private void ortalamaBaðlantýlýParametreleriniAl(OrtalamaBaðlantýlýList ob) {
				if(yöntem.baðlantýlýAyar.enAzKümeSayýsý != -1){
					ob.enAzGrupSayýsý = yöntem.baðlantýlýAyar.enAzKümeSayýsý;
				}
				if(yöntem.baðlantýlýAyar.enFazlaElemanSayýsý != -1){
					ob.gruptakiEnFazlaMakineSayýsý = yöntem.baðlantýlýAyar.enFazlaElemanSayýsý;
				}
				if(yöntem.baðlantýlýAyar.eþikDeðeri != -1){
					ob.eþikDeðer = yöntem.baðlantýlýAyar.eþikDeðeri;
				}
				if(yöntem.baðlantýlýAyar.eþikDeðeriÇarpaný != -1){
					ob.eþikDeðerÇarpaný = yöntem.baðlantýlýAyar.eþikDeðeriÇarpaný ;
				}
			}
			// BAÐLANTILILAR

			private void parçacýkSürüÇöz() { 
				String girdi = girdiYeri.getText();
				try {
					parçacýkSürüÇöz2(girdi);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			private void parçacýkSürüÇöz2(String girdi)throws Exception {
				String çözüm = "";
				ParçacýkSürü sürü = new ParçacýkSürü(girdi, yöntem.psaAyar.kümeSayýsý,yöntem.psaAyar.parçacýkSayýsý);
				kümeSayýsýHataAyýkla(sürü);
				parametreleriDoldur(sürü);
				çözüm = parçacýkSürüÇözümüYap(yöntem.psaAyar.aþamalarýGöster || yöntem.aþamalarýGöster.isSelected(), çözüm, sürü);
				çýktýYaz(çözüm);
			}
			private void kümeSayýsýHataAyýkla(ParçacýkSürü sürü) {
				if(yöntem.psaAyar.kümeSayýsý >sürü.makineSayýsý() ){
					hatalar += "küme sayýsý makine sayýsýndan fazla olamaz -- küme sayýsý " +  yöntem.psaAyar.kümeSayýsý  + " makine sayýsý : " + sürü.makineSayýsý() + "\n";
				}
			}
			private void parametreleriDoldur(ParçacýkSürü sürü) throws Exception {
				sürü.güvenParameterileriniBelirle(yöntem.psaAyar.sürüyeGüven, yöntem.psaAyar.kendineGüven, yöntem.psaAyar.eylemsizlik);
				sürü.ilkHýzBelirle(yöntem.psaAyar.ilkHýz);
				sürü.enFazlaAþama = yöntem.psaAyar.enFazlaAþama;
				sürü.enFazlaÝlerleme = yöntem.psaAyar.enFazlaÝlerleme;
				sürü.ilerlemeKýsýtlý = yöntem.psaAyar.ilerlemeKýsýtlý;
			}
			private String parçacýkSürüÇözümüYap(boolean aþamalarýGöster, String çözüm,ParçacýkSürü sürü) throws Exception {
				if(aþamalarýGöster){
					çözüm += sürü.çöz();
				}
				else {
					çözüm += sürü.hýzlýÇöz();
				}
				return çözüm;
			}


		});
	}

	private void yeniSayfaTuþu() {
		JButton btnYeniSayfa = new JButton("yeni sayfa");
		btnYeniSayfa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String çýktý = "";
				Dendogram yeni = new Dendogram();
				try {
					ArrayList<ArrayList<Integer>> gruplananlar = new ArrayList<ArrayList<Integer>>();
					int toplamNokta = 8;
					
				    for(int satýrNo = 0;satýrNo <6;satýrNo++){
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
					
					DendogramAðacý aðaç = new DendogramAðacý();
					aðaç.aðacýDoldur(toplamNokta);
					aðaç.aðacýGrupla(gruplananlar);
					System.out.print(aðaç.yaz());
					
//					yeni.gruplananlar = gruplananlar;
//					yeni.toplamNokta = toplamNokta;
//					yeni.sýralamayýOluþtur();
//					System.out.println(yeni.sýralamayýYaz());
					// {{ sýralama denemesi 
					
//					System.out.println(dizi.toString());
//					dizi = yeni.gruplamaYap(0, 6, dizi);
//					System.out.println(dizi.toString());
//					dizi = yeni.gruplamaYap(1, 3, dizi);
//					System.out.println(dizi.toString());
//					dizi = yeni.gruplamaYap(2, 3, dizi);
//					System.out.println(dizi.toString());
					// }} sýralama denemesi 
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnYeniSayfa.setBounds(818, 21, 110, 23);
		getContentPane().add(btnYeniSayfa);
	}
	private void veriÜretmeTuþu() {
		// veri üretme
		btnVeriret = new JButton("veri \u00FCret");
		btnVeriret.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					vr = new veriÜretmeEkraný(girdiYeri);
					vr.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnVeriret.setBounds(275, 21, 89, 23);
		getContentPane().add(btnVeriret);
	}
	private void yöntemSayfasýTuþu() {
		// yöntem sayfasý
		btnYntemSe = new JButton("y\u00F6ntem se\u00E7");
		btnYntemSe.addActionListener(new ActionListener() { // yöntem seçme 
			public void actionPerformed(ActionEvent arg0) {
				yöntem.setVisible(true);
				yöntemSayfasýAçýlmadý = false;
			}
		});
		btnYntemSe.setBounds(34, 21, 132, 23);
		getContentPane().add(btnYntemSe);
	}

	private void þekliOluþtur() {
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
	private void çýktýYaz(String yazý) {
		çýktý sonuç = new çýktý();
		sonuç.textArea.setText(yazý);
		sonuç.setVisible(true);
		sonuç.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public void çizdirme(){

	}
}
