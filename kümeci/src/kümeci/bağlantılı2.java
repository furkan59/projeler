package k�meci;


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

public class ba�lant�l�2 extends JFrame {

	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField enAzK�meSay�s�Yaz�;
	private JTextField enFazlaElemanSay�s�Yaz�;
	private JTextField e�ikDe�eriYaz�;
	private JTextField e�ikDe�eri�arpan�Yaz�;
	
	public int enAzK�meSay�s�=-1,enFazlaElemanSay�s�=-1;
	public double e�ikDe�eri=-1,e�ikDe�eri�arpan�=-1;
	public String hatalar =""; 
	
	public  JCheckBox tekBa�lat�l�;
	public  JCheckBox ortalamaBa�lant�l�;
	public  JCheckBox do�rusalK�meleme;
	private JButton btnKaydetVek;
	
	public ba�lant�l�2() {
		�ekliOlu�tur();
		kaydetVe��kTu�u();
	}
	private void t�mHatalar�Yaz() {
		if ( hatalar.compareTo("") != 0){
			��kt�Yaz("ba�lant�l� k�melel ayalar� yap�l�rken yap�lan hatalar \n" +hatalar);
		}
	}

	private void �ekliOlu�tur() {
		ana�ekliOlu�tur();
		yan�ekilleriOlu�tur();
	}


	private void kaydetVe��kTu�u() {
		btnKaydetVek = new JButton("Kaydet ve \u00C7\u0131k");
		btnKaydetVek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				de�eleriOku();
				t�mHatalar�Yaz();
			}
			private void de�eleriOku() {
				enAzK�meSay�s�n�Oku();
				enFazlaElemanSay�s�();
				e�ikDe�eriAl();
				e�ikDe�eri�arpan�Oku();
			}
			private void e�ikDe�eri�arpan�Oku() {
				String yaz� = e�ikDe�eri�arpan�Yaz�.getText();
				if(yaz�.compareTo("") != 0){ // e�ik de�eri �arpan� girilmeyebilir
					e�ikDe�eri�arpan� = Double.parseDouble(yaz�);
					if( e�ikDe�eri�arpan� < 0 || e�ikDe�eri�arpan� > 1){
						hatalar += "e�ik de�eri �arpan� 0 ile 1 aras�nda olmal�d�r\n";
					}
				}
			}
			private void e�ikDe�eriAl() {
				String yaz� = e�ikDe�eriYaz�.getText();
				if(yaz�.compareTo("") != 0){
					e�ikDe�eri = Double.parseDouble(yaz�);
					if(e�ikDe�eri < 0 || e�ikDe�eri > 1 ){
						hatalar += "e�ik de�eri 0 ile 1 aras�nda olmal�d�r";
					}
				}
			}
			private void enFazlaElemanSay�s�() {
				String yaz� = enFazlaElemanSay�s�Yaz�.getText();
				if(yaz�.compareTo("") != 0){
					if(yaz�.contains(",") || yaz�.contains(".")){
				    	hatalar += "en fazla eleman say�s� tam say� olmal�d�r\n";
				    }
					enFazlaElemanSay�s� = Integer.parseInt(enFazlaElemanSay�s�Yaz�.getText());
					if(enFazlaElemanSay�s�< 0 ){
				    	hatalar += "en fazla eleman say�s� pozitif olmal�d�r\n";
				    }
				}
			}
			private void enAzK�meSay�s�n�Oku() {
				String yaz� = enAzK�meSay�s�Yaz�.getText();
				if(yaz�.compareTo("") != 0){  // en az k�me say�s� girilmeyebilir
					if(yaz�.contains(",") || yaz�.contains(".")){
				    	hatalar += "en az k�me say�s� tam say� olmal�d�r\n";
				    }
					enAzK�meSay�s� = Integer.parseInt(yaz�);
					if( enAzK�meSay�s� < 0 ){
				    	hatalar += "en az k�me say�s� pozitif olmal�d�r\n";
				    }
				}
			}
		});
		btnKaydetVek.setBounds(113, 230, 122, 23);
		contentPane.add(btnKaydetVek);
	}

	private void ana�ekliOlu�tur() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 351, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yan�ekilleriOlu�tur() {
		JLabel lblKmeSays = new JLabel("en az k\u00FCme say\u0131s\u0131");
		lblKmeSays.setBounds(10, 11, 115, 14);
		contentPane.add(lblKmeSays);
		
		enAzK�meSay�s�Yaz� = new JTextField();
		enAzK�meSay�s�Yaz�.setBounds(227, 8, 86, 20);
		contentPane.add(enAzK�meSay�s�Yaz�);
		enAzK�meSay�s�Yaz�.setColumns(10);
		
		JLabel lblBirKmedekiEn = new JLabel("bir k\u00FCmedeki en fazla eleman say\u0131s\u0131");
		lblBirKmedekiEn.setBounds(10, 36, 179, 14);
		contentPane.add(lblBirKmedekiEn);
		
		enFazlaElemanSay�s�Yaz� = new JTextField();
		enFazlaElemanSay�s�Yaz�.setBounds(227, 33, 86, 20);
		contentPane.add(enFazlaElemanSay�s�Yaz�);
		enFazlaElemanSay�s�Yaz�.setColumns(10);
		
		JLabel lblEikDeeri = new JLabel("e\u015Fik de\u011Feri");
		lblEikDeeri.setBounds(10, 100, 86, 14);
		contentPane.add(lblEikDeeri);
		
		JLabel lblEikDeeriarpan = new JLabel("e\u015Fik de\u011Feri \u00E7arpan\u0131");
		lblEikDeeriarpan.setBounds(10, 125, 115, 14);
		contentPane.add(lblEikDeeriarpan);
		
		e�ikDe�eriYaz� = new JTextField();
		e�ikDe�eriYaz�.setBounds(227, 97, 86, 20);
		contentPane.add(e�ikDe�eriYaz�);
		e�ikDe�eriYaz�.setColumns(10);
		
		e�ikDe�eri�arpan�Yaz� = new JTextField();
		e�ikDe�eri�arpan�Yaz�.setBounds(227, 122, 86, 20);
		contentPane.add(e�ikDe�eri�arpan�Yaz�);
		e�ikDe�eri�arpan�Yaz�.setColumns(10);
		
		
		JLabel lblzmYntemi = new JLabel("\u00E7\u00F6z\u00FCm y\u00F6ntemi");
		lblzmYntemi.setBounds(114, 150, 105, 14);
		contentPane.add(lblzmYntemi);
		
		tekBa�lat�l� = new JCheckBox("tek ba\u011Flant\u0131l\u0131");
		tekBa�lat�l�.setBounds(10, 167, 97, 23);
		contentPane.add(tekBa�lat�l�);
		
		ortalamaBa�lant�l� = new JCheckBox("ortalama ba\u011Flant\u0131l\u0131");
		ortalamaBa�lant�l�.setBounds(10, 191, 128, 23);
		contentPane.add(ortalamaBa�lant�l�);
		
		do�rusalK�meleme = new JCheckBox("do\u011Frusal k\u00FCmeleme");
		do�rusalK�meleme.setBounds(167, 171, 117, 23);
		contentPane.add(do�rusalK�meleme);
	}
	private void ��kt�Yaz(String yaz�) {
		��kt� sonu� = new ��kt�();
		sonu�.textArea.setText(yaz�);
		sonu�.setVisible(true);
		sonu�.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
