package k�meci;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;

public class y�ntemSayfas� extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    public int y�ntemAd� ; // 1 par�ac�k s�r� , 2 ba�lant�l� , 3 derece s�ralama , 4 par�ac�k ailesi , 5 k ortalamalar
    
    public par�ac�kS�r�Ayar psaAyar = new par�ac�kS�r�Ayar();;
    public ba�lant�l�2 ba�lant�l�Ayar = new ba�lant�l�2();;
    
    public JCheckBox kOrtalamalar;
    public JCheckBox dereceS�ra;
    public JCheckBox ba�Enerji ;
    public JCheckBox k�meBulmaY�ntemi;
    public JTextField kOrtalamalarK�meSay�s�;
    public JTextField denemeSay�s�Yaz�;
    public JCheckBox a�amalar�G�ster;

    public boolean par�ac�kS�r�Se�ildiMi = false;
    public  boolean ba�lant�l�Se�ildiMi = false;
    
	public y�ntemSayfas�() {
		ana�ekliOlu�tur();
		yan�ekilleriOlu�tur();
		panelD���Tu�lar();
		paneller();
	}

	private void paneller() {
		panel0();
		JButton btnBalantlKmelemeAyarlar = panel1();
		panel2(btnBalantlKmelemeAyarlar);
	}
	private void panelD���Tu�lar() {
		t�mY�ntemler��zmeTu�u();
	}
	private void t�mY�ntemler��zmeTu�u() {
		// t�m y�ntemlerle ��zme tu�u
		JButton btnTmYntemlerlez = new JButton("T\u00FCm Y\u00F6ntemleri Se\u00E7");
		btnTmYntemlerlez.addActionListener(new ActionListener() { // t�m y�ntemlerle ��z tu�u
			public void actionPerformed(ActionEvent arg0) {
				// ba�lant�l�lar� i�aretleyelim
				ba�lant�l�lar�Se�();
				h�creselK�melemeye�zg�Olanlar�Se�();
				metaSezgisel��z();
			}
			private void metaSezgisel��z() {
				kOrtalamalar.setSelected(true);
				psaAyar.setVisible(true);
			}
			private void h�creselK�melemeye�zg�Olanlar�Se�() {
				dereceS�ra.setSelected(true);
				k�meBulmaY�ntemi.setSelected(true);
				ba�Enerji.setSelected(true);
			}
			private void ba�lant�l�lar�Se�() {
				ba�lant�l�Ayar.setVisible(true); // kullan�c� parametreleri girsin
				ba�lant�l�Ayar.do�rusalK�meleme.setSelected(true);
				ba�lant�l�Ayar.ortalamaBa�lant�l�.setSelected(true);
				ba�lant�l�Ayar.tekBa�lat�l�.setSelected(true);
			}
		});
		btnTmYntemlerlez.setBounds(221, 249, 166, 23);
		contentPane.add(btnTmYntemlerlez);
	}
	private JButton panel1() {
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "BA�LANTILI K�MELEME Y�NTEMLER�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 25, 250, 119);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		// ba�lant�l� k�meleme i�in ayaarlar tu�u
		JButton btnBalantlKmelemeAyarlar = new JButton("ba\u011Flant\u0131l\u0131 ayarlar");
		btnBalantlKmelemeAyarlar.setBounds(6, 56, 175, 52);
		panel_1.add(btnBalantlKmelemeAyarlar);
		return btnBalantlKmelemeAyarlar;
	}
	private void panel2(JButton btnBalantlKmelemeAyarlar) {
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "H�CRESEL K�MELEMEYE �ZG� Y�NTEMLER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(668, 16, 268, 221);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 177, 198);
		panel_2.add(separator);
		
		dereceS�ra = new JCheckBox("derece s\u0131ra");
		dereceS�ra.setBounds(6, 39, 97, 23);
		panel_2.add(dereceS�ra);
		
		ba�Enerji = new JCheckBox("ba\u011F enerji");
		ba�Enerji.setBounds(6, 79, 97, 23);
		panel_2.add(ba�Enerji);
		
		k�meBulmaY�ntemi = new JCheckBox("k\u00FCme bulma y\u00F6ntemi");
		k�meBulmaY�ntemi.setBounds(6, 123, 131, 23);
		panel_2.add(k�meBulmaY�ntemi);
		btnBalantlKmelemeAyarlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ba�lant�l�Se�ildiMi = true;
				ba�lant�l�Ayar.setVisible(true);
			}
		});
	}
	private void panel0() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "META SEZG�SELLER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(266, 16, 303, 193);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// par�ac�k s�r� i�in ayarlar tu�u
		JButton btnParackSrAyarlar = new JButton("par\u00E7ac\u0131k s\u00FCr\u00FC ayarlar\u0131");
		btnParackSrAyarlar.setBounds(78, 21, 166, 23);
		panel.add(btnParackSrAyarlar);
		
		JLabel lblKortalamalarKmeSays = new JLabel("k-ortalamalar k\u00FCme say\u0131s\u0131");
		lblKortalamalarKmeSays.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKortalamalarKmeSays.setBounds(10, 90, 190, 14);
		panel.add(lblKortalamalarKmeSays);
		
		kOrtalamalarK�meSay�s� = new JTextField();
		kOrtalamalarK�meSay�s�.setBounds(240, 87, 53, 20);
		panel.add(kOrtalamalarK�meSay�s�);
		kOrtalamalarK�meSay�s�.setColumns(10);
		
		kOrtalamalar = new JCheckBox("k- ortalamalar");
		kOrtalamalar.setBounds(10, 60, 180, 23);
		panel.add(kOrtalamalar);
		
		denemeSay�s�Yaz� = new JTextField();
		denemeSay�s�Yaz�.setBounds(240, 118, 53, 20);
		panel.add(denemeSay�s�Yaz�);
		denemeSay�s�Yaz�.setColumns(10);
		denemeSay�s�Yaz�.setText("1");
		
		JLabel lblDenemeSays = new JLabel("deneme say\u0131s\u0131");
		lblDenemeSays.setBounds(10, 121, 97, 14);
		panel.add(lblDenemeSays);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 303, 2);
		panel.add(separator);
		btnParackSrAyarlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				par�ac�kS�r�Se�ildiMi = true;
				psaAyar.setVisible(true);
			}
		});
	}
	
	private void ana�ekliOlu�tur() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 962, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yan�ekilleriOlu�tur() {
		a�amalar�G�ster = new JCheckBox("a\u015Famalar\u0131 g\u00F6ster");
		a�amalar�G�ster.setBounds(445, 249, 152, 23);
		contentPane.add(a�amalar�G�ster);
	}
}
