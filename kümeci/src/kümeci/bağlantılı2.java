package kümeci;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class baðlantýlý2 extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField enAzKümeSayýsýYazý;
	private JTextField enFazlaElemanSayýsýYazý;
	private JTextField eþikDeðeriYazý;
	private JTextField eþikDeðeriÇarpanýYazý;
	
	public int enAzKümeSayýsý=-1,enFazlaElemanSayýsý=-1;
	public double eþikDeðeri=-1,eþikDeðeriÇarpaný=-1;
	public String hatalar =""; 
	
	public  JCheckBox tekBaðlatýlý;
	public  JCheckBox ortalamaBaðlantýlý;
	public  JCheckBox doðrusalKümeleme;
	private JButton btnKaydetVek;
	
	public baðlantýlý2() {
		þekliOluþtur();
		kaydetVeÇýkTuþu();
	}
	private void tümHatalarýYaz() {
		if ( hatalar.compareTo("") != 0){
			çýktýYaz("baðlantýlý kümelel ayalarý yapýlýrken yapýlan hatalar \n" +hatalar);
		}
	}

	private void þekliOluþtur() {
		anaÞekliOluþtur();
		yanÞekilleriOluþtur();
	}


	private void kaydetVeÇýkTuþu() {
		btnKaydetVek = new JButton("Kaydet ve \u00C7\u0131k");
		btnKaydetVek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deðeleriOku();
				tümHatalarýYaz();
			}
			private void deðeleriOku() {
				enAzKümeSayýsýnýOku();
				enFazlaElemanSayýsý();
				eþikDeðeriAl();
				eþikDeðeriÇarpanýOku();
			}
			private void eþikDeðeriÇarpanýOku() {
				String yazý = eþikDeðeriÇarpanýYazý.getText();
				if(yazý.compareTo("") != 0){ // eþik deðeri çarpaný girilmeyebilir
					eþikDeðeriÇarpaný = Double.parseDouble(yazý);
					if( eþikDeðeriÇarpaný < 0 || eþikDeðeriÇarpaný > 1){
						hatalar += "eþik deðeri çarpaný 0 ile 1 arasýnda olmalýdýr\n";
					}
				}
			}
			private void eþikDeðeriAl() {
				String yazý = eþikDeðeriYazý.getText();
				if(yazý.compareTo("") != 0){
					eþikDeðeri = Double.parseDouble(yazý);
					if(eþikDeðeri < 0 || eþikDeðeri > 1 ){
						hatalar += "eþik deðeri 0 ile 1 arasýnda olmalýdýr";
					}
				}
			}
			private void enFazlaElemanSayýsý() {
				String yazý = enFazlaElemanSayýsýYazý.getText();
				if(yazý.compareTo("") != 0){
					if(yazý.contains(",") || yazý.contains(".")){
				    	hatalar += "en fazla eleman sayýsý tam sayý olmalýdýr\n";
				    }
					enFazlaElemanSayýsý = Integer.parseInt(enFazlaElemanSayýsýYazý.getText());
					if(enFazlaElemanSayýsý< 0 ){
				    	hatalar += "en fazla eleman sayýsý pozitif olmalýdýr\n";
				    }
				}
			}
			private void enAzKümeSayýsýnýOku() {
				String yazý = enAzKümeSayýsýYazý.getText();
				if(yazý.compareTo("") != 0){  // en az küme sayýsý girilmeyebilir
					if(yazý.contains(",") || yazý.contains(".")){
				    	hatalar += "en az küme sayýsý tam sayý olmalýdýr\n";
				    }
					enAzKümeSayýsý = Integer.parseInt(yazý);
					if( enAzKümeSayýsý < 0 ){
				    	hatalar += "en az küme sayýsý pozitif olmalýdýr\n";
				    }
				}
			}
		});
		btnKaydetVek.setBounds(113, 230, 122, 23);
		contentPane.add(btnKaydetVek);
	}

	private void anaÞekliOluþtur() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 351, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yanÞekilleriOluþtur() {
		JLabel lblKmeSays = new JLabel("en az k\u00FCme say\u0131s\u0131");
		lblKmeSays.setBounds(10, 11, 115, 14);
		contentPane.add(lblKmeSays);
		
		enAzKümeSayýsýYazý = new JTextField();
		enAzKümeSayýsýYazý.setBounds(227, 8, 86, 20);
		contentPane.add(enAzKümeSayýsýYazý);
		enAzKümeSayýsýYazý.setColumns(10);
		
		JLabel lblBirKmedekiEn = new JLabel("bir k\u00FCmedeki en fazla eleman say\u0131s\u0131");
		lblBirKmedekiEn.setBounds(10, 36, 179, 14);
		contentPane.add(lblBirKmedekiEn);
		
		enFazlaElemanSayýsýYazý = new JTextField();
		enFazlaElemanSayýsýYazý.setBounds(227, 33, 86, 20);
		contentPane.add(enFazlaElemanSayýsýYazý);
		enFazlaElemanSayýsýYazý.setColumns(10);
		
		JLabel lblEikDeeri = new JLabel("e\u015Fik de\u011Feri");
		lblEikDeeri.setBounds(10, 100, 86, 14);
		contentPane.add(lblEikDeeri);
		
		JLabel lblEikDeeriarpan = new JLabel("e\u015Fik de\u011Feri \u00E7arpan\u0131");
		lblEikDeeriarpan.setBounds(10, 125, 115, 14);
		contentPane.add(lblEikDeeriarpan);
		
		eþikDeðeriYazý = new JTextField();
		eþikDeðeriYazý.setBounds(227, 97, 86, 20);
		contentPane.add(eþikDeðeriYazý);
		eþikDeðeriYazý.setColumns(10);
		
		eþikDeðeriÇarpanýYazý = new JTextField();
		eþikDeðeriÇarpanýYazý.setBounds(227, 122, 86, 20);
		contentPane.add(eþikDeðeriÇarpanýYazý);
		eþikDeðeriÇarpanýYazý.setColumns(10);
		
		
		JLabel lblzmYntemi = new JLabel("\u00E7\u00F6z\u00FCm y\u00F6ntemi");
		lblzmYntemi.setBounds(114, 150, 105, 14);
		contentPane.add(lblzmYntemi);
		
		tekBaðlatýlý = new JCheckBox("tek ba\u011Flant\u0131l\u0131");
		tekBaðlatýlý.setBounds(10, 167, 97, 23);
		contentPane.add(tekBaðlatýlý);
		
		ortalamaBaðlantýlý = new JCheckBox("ortalama ba\u011Flant\u0131l\u0131");
		ortalamaBaðlantýlý.setBounds(10, 191, 128, 23);
		contentPane.add(ortalamaBaðlantýlý);
		
		doðrusalKümeleme = new JCheckBox("do\u011Frusal k\u00FCmeleme");
		doðrusalKümeleme.setBounds(167, 171, 117, 23);
		contentPane.add(doðrusalKümeleme);
	}
	private void çýktýYaz(String yazý) {
		çýktý sonuç = new çýktý();
		sonuç.textArea.setText(yazý);
		sonuç.setVisible(true);
		sonuç.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
