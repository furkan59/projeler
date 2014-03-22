package k�meci;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class veri�retmeEkran� extends JFrame {

	private JPanel contentPane;
	private JTextField sat�rSay�s�Yaz��le;
	private JTextField sutunSay�s�Yaz��le;
	private JTextField k�meSay�s�Yaz��le;
	
	private JLabel lblKmeSays ;
	
	private JTextArea girdiYeri;
	public int sat�rSay�s� = -1;
	public int sutunSay�s� = -1;
	public int k�meSay�s� = -1;
	public String hatalar = "";

	public veri�retmeEkran�(JTextArea girdi) {
		�ekliOlu�tur();
		this.girdiYeri = girdi;
		kaydetVe��kTu�u();
	}
	private void kaydetVe��kTu�u() {
		// kaydet ve ��k tu�u
		JButton btnNewButton = new JButton("Kaydet ve \u00C7\u0131k");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			    	verileriAl();
					girdiyiDe�i�tir();
					t�mHatalar�Yaz();
				} catch (Exception e1) {    e1.printStackTrace();   }
				
			}
			private void t�mHatalar�Yaz() {
				if(hatalar.compareTo("") != 0) {
					��kt�Yaz("veri �retirken yap�lan hatalar \n" + hatalar);
				}

			}

			private void verileriAl() {
				k�meSay�s�Yo�unlu�uAl();
				sat�rSay�s�Al();
				sutunSay�s�Gir();
			}
			
            private void sutunSay�s�Gir() {
				sutunSay�s�Hatalar�n�Al();
				sutunSay�s� = Integer.parseInt(sutunSay�s�Yaz��le.getText());
			}
			private void sutunSay�s�Hatalar�n�Al() {
				if(sutunSay�s�Yaz��le.getText().compareTo("") == 0){
					hatalar += "sutun say�s� girilmemi�\n";
				}
				if(sutunSay�s�Yaz��le.getText().contains(",") || sutunSay�s�Yaz��le.getText().contains(".") ){
					hatalar += "sutun say�s� tam say� olmal�\n";
				}
			}
			
			private void sat�rSay�s�Al() {
				sat�rSay�s�Hatalar�n�Al();
				sat�rSay�s� = Integer.parseInt(sat�rSay�s�Yaz��le.getText());
			}

			private void sat�rSay�s�Hatalar�n�Al() {
				if(sat�rSay�s�Yaz��le.getText().compareTo("") == 0){
					hatalar += "sat�r say�s� girilmemi�\n";
				}
				if(sat�rSay�s�Yaz��le.getText().contains(",") || sat�rSay�s�Yaz��le.getText().contains(".") ){
					hatalar += "sat�r say�s� tam say� olmal�\n";
				}
			}
			
			private void k�meSay�s�Yo�unlu�uAl() {
					k�meSay�s�n�Al();
			}
			private void k�meSay�s�n�Al() {
				k�meSay�s�Girilmi�Mi();
				k�meSay�s�TamSay�M�();
				k�meSay�s� = Integer.parseInt(k�meSay�s�Yaz��le.getText());
			}
			private void k�meSay�s�TamSay�M�() {
				if(k�meSay�s�Yaz��le.getText().contains(",") || k�meSay�s�Yaz��le.getText().contains(".")){
					hatalar += "k�me say�s� tam say� olmal�\n";
				}
			}
			private void k�meSay�s�Girilmi�Mi() {
				if(k�meSay�s�Yaz��le.getText().compareTo("") == 0){
					hatalar += "k�me say�s� girilmemi�\n";
				}
			}
	
			private void girdiyiDe�i�tir() throws Exception {
				Ek��levler ek = new Ek��levler();
			    girdiYeri.setText(ek.kar��t�r�lm��K�melenmi�Veri�Ret(sat�rSay�s�, sutunSay�s�, k�meSay�s�));
			}
		});
		btnNewButton.setBounds(162, 207, 135, 23);
		contentPane.add(btnNewButton);
	}
	
	private void �ekliOlu�tur() {
		ana�ekliOlu�tur();
		
		yaz�lar�Olu�tur();
		
		yaz�YerleriniOlu�tur();
	}
	private void ana�ekliOlu�tur() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 463, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yaz�lar�Olu�tur() {
		JLabel sat�rSay�s�Yaz� = new JLabel("sat\u0131r say\u0131s\u0131");
		sat�rSay�s�Yaz�.setBounds(22, 27, 91, 14);
		contentPane.add(sat�rSay�s�Yaz�);
		
		JLabel sutunSay�s�Yaz� = new JLabel("sutun say\u0131s\u0131");
		sutunSay�s�Yaz�.setBounds(22, 62, 91, 14);
		contentPane.add(sutunSay�s�Yaz�);
					
		lblKmeSays = new JLabel("k\u00FCme Say\u0131s\u0131");
		lblKmeSays.setBounds(22, 165, 91, 14);
		lblKmeSays.setVisible(true);
		contentPane.add(lblKmeSays);
	}
	private void yaz�YerleriniOlu�tur() {
		sat�rSay�s�Yaz��le = new JTextField();
		sat�rSay�s�Yaz��le.setBounds(232, 24, 86, 20);
		contentPane.add(sat�rSay�s�Yaz��le);
		sat�rSay�s�Yaz��le.setColumns(10);
		
		sutunSay�s�Yaz��le = new JTextField();
		sutunSay�s�Yaz��le.setColumns(10);
		sutunSay�s�Yaz��le.setBounds(232, 59, 86, 20);
		contentPane.add(sutunSay�s�Yaz��le);
		
		k�meSay�s�Yaz��le = new JTextField();
		k�meSay�s�Yaz��le.setColumns(10);
		k�meSay�s�Yaz��le.setBounds(232, 162, 86, 20);
		k�meSay�s�Yaz��le.setVisible(true);
		contentPane.add(k�meSay�s�Yaz��le);
	}
    
	 private void ��kt�Yaz(String yaz�) {
			��kt� sonu� = new ��kt�();
			sonu�.textArea.setText(yaz�);
			sonu�.setVisible(true);
			sonu�.setDefaultCloseOperation(HIDE_ON_CLOSE);
		    }
}

