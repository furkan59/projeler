package kümeci;

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

public class veriÜretmeEkranı extends JFrame {

	private JPanel contentPane;
	private JTextField satırSayısıYazıİle;
	private JTextField sutunSayısıYazıİle;
	private JTextField kümeSayısıYazıİle;
	
	private JLabel lblKmeSays ;
	
	private JTextArea girdiYeri;
	public int satırSayısı = -1;
	public int sutunSayısı = -1;
	public int kümeSayısı = -1;
	public String hatalar = "";

	public veriÜretmeEkranı(JTextArea girdi) {
		şekliOluştur();
		this.girdiYeri = girdi;
		kaydetVeÇıkTuşu();
	}
	private void kaydetVeÇıkTuşu() {
		// kaydet ve çık tuşu
		JButton btnNewButton = new JButton("Kaydet ve \u00C7\u0131k");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			    	verileriAl();
					girdiyiDeğiştir();
					tümHatalarıYaz();
				} catch (Exception e1) {    e1.printStackTrace();   }
				
			}
			private void tümHatalarıYaz() {
				if(hatalar.compareTo("") != 0) {
					çıktıYaz("veri üretirken yapılan hatalar \n" + hatalar);
				}

			}

			private void verileriAl() {
				kümeSayısıYoğunluğuAl();
				satırSayısıAl();
				sutunSayısıGir();
			}
			
            private void sutunSayısıGir() {
				sutunSayısıHatalarınıAl();
				sutunSayısı = Integer.parseInt(sutunSayısıYazıİle.getText());
			}
			private void sutunSayısıHatalarınıAl() {
				if(sutunSayısıYazıİle.getText().compareTo("") == 0){
					hatalar += "sutun sayısı girilmemiş\n";
				}
				if(sutunSayısıYazıİle.getText().contains(",") || sutunSayısıYazıİle.getText().contains(".") ){
					hatalar += "sutun sayısı tam sayı olmalı\n";
				}
			}
			
			private void satırSayısıAl() {
				satırSayısıHatalarınıAl();
				satırSayısı = Integer.parseInt(satırSayısıYazıİle.getText());
			}

			private void satırSayısıHatalarınıAl() {
				if(satırSayısıYazıİle.getText().compareTo("") == 0){
					hatalar += "satır sayısı girilmemiş\n";
				}
				if(satırSayısıYazıİle.getText().contains(",") || satırSayısıYazıİle.getText().contains(".") ){
					hatalar += "satır sayısı tam sayı olmalı\n";
				}
			}
			
			private void kümeSayısıYoğunluğuAl() {
					kümeSayısınıAl();
			}
			private void kümeSayısınıAl() {
				kümeSayısıGirilmişMi();
				kümeSayısıTamSayıMı();
				kümeSayısı = Integer.parseInt(kümeSayısıYazıİle.getText());
			}
			private void kümeSayısıTamSayıMı() {
				if(kümeSayısıYazıİle.getText().contains(",") || kümeSayısıYazıİle.getText().contains(".")){
					hatalar += "küme sayısı tam sayı olmalı\n";
				}
			}
			private void kümeSayısıGirilmişMi() {
				if(kümeSayısıYazıİle.getText().compareTo("") == 0){
					hatalar += "küme sayısı girilmemiş\n";
				}
			}
	
			private void girdiyiDeğiştir() throws Exception {
				Ekİşlevler ek = new Ekİşlevler();
			    girdiYeri.setText(ek.karıştırılmışKümelenmişVeriÜRet(satırSayısı, sutunSayısı, kümeSayısı));
			}
		});
		btnNewButton.setBounds(162, 207, 135, 23);
		contentPane.add(btnNewButton);
	}
	
	private void şekliOluştur() {
		anaŞekliOluştur();
		
		yazılarıOluştur();
		
		yazıYerleriniOluştur();
	}
	private void anaŞekliOluştur() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 463, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yazılarıOluştur() {
		JLabel satırSayısıYazı = new JLabel("sat\u0131r say\u0131s\u0131");
		satırSayısıYazı.setBounds(22, 27, 91, 14);
		contentPane.add(satırSayısıYazı);
		
		JLabel sutunSayısıYazı = new JLabel("sutun say\u0131s\u0131");
		sutunSayısıYazı.setBounds(22, 62, 91, 14);
		contentPane.add(sutunSayısıYazı);
					
		lblKmeSays = new JLabel("k\u00FCme Say\u0131s\u0131");
		lblKmeSays.setBounds(22, 165, 91, 14);
		lblKmeSays.setVisible(true);
		contentPane.add(lblKmeSays);
	}
	private void yazıYerleriniOluştur() {
		satırSayısıYazıİle = new JTextField();
		satırSayısıYazıİle.setBounds(232, 24, 86, 20);
		contentPane.add(satırSayısıYazıİle);
		satırSayısıYazıİle.setColumns(10);
		
		sutunSayısıYazıİle = new JTextField();
		sutunSayısıYazıİle.setColumns(10);
		sutunSayısıYazıİle.setBounds(232, 59, 86, 20);
		contentPane.add(sutunSayısıYazıİle);
		
		kümeSayısıYazıİle = new JTextField();
		kümeSayısıYazıİle.setColumns(10);
		kümeSayısıYazıİle.setBounds(232, 162, 86, 20);
		kümeSayısıYazıİle.setVisible(true);
		contentPane.add(kümeSayısıYazıİle);
	}
    
	 private void çıktıYaz(String yazı) {
			çıktı sonuç = new çıktı();
			sonuç.textArea.setText(yazı);
			sonuç.setVisible(true);
			sonuç.setDefaultCloseOperation(HIDE_ON_CLOSE);
		    }
}

