package kümeci;


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

public class yöntemSayfası extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    public int yöntemAdı ; // 1 parçacık sürü , 2 bağlantılı , 3 derece sıralama , 4 parçacık ailesi , 5 k ortalamalar
    
    public parçacıkSürüAyar psaAyar = new parçacıkSürüAyar();;
    public bağlantılı2 bağlantılıAyar = new bağlantılı2();;
    
    public JCheckBox kOrtalamalar;
    public JCheckBox dereceSıra;
    public JCheckBox bağEnerji ;
    public JCheckBox kümeBulmaYöntemi;
    public JTextField kOrtalamalarKümeSayısı;
    public JTextField denemeSayısıYazı;
    public JCheckBox aşamalarıGöster;

    public boolean parçacıkSürüSeçildiMi = false;
    public  boolean bağlantılıSeçildiMi = false;
    
	public yöntemSayfası() {
		anaŞekliOluştur();
		yanŞekilleriOluştur();
		panelDışıTuşlar();
		paneller();
	}

	private void paneller() {
		panel0();
		JButton btnBalantlKmelemeAyarlar = panel1();
		panel2(btnBalantlKmelemeAyarlar);
	}
	private void panelDışıTuşlar() {
		tümYöntemlerÇözmeTuşu();
	}
	private void tümYöntemlerÇözmeTuşu() {
		// tüm yöntemlerle çözme tuşu
		JButton btnTmYntemlerlez = new JButton("T\u00FCm Y\u00F6ntemleri Se\u00E7");
		btnTmYntemlerlez.addActionListener(new ActionListener() { // tüm yöntemlerle çöz tuşu
			public void actionPerformed(ActionEvent arg0) {
				// bağlantılıları işaretleyelim
				bağlantılılarıSeç();
				hücreselKümelemeyeÖzgüOlanlarıSeç();
				metaSezgiselÇöz();
			}
			private void metaSezgiselÇöz() {
				kOrtalamalar.setSelected(true);
				psaAyar.setVisible(true);
			}
			private void hücreselKümelemeyeÖzgüOlanlarıSeç() {
				dereceSıra.setSelected(true);
				kümeBulmaYöntemi.setSelected(true);
				bağEnerji.setSelected(true);
			}
			private void bağlantılılarıSeç() {
				bağlantılıAyar.setVisible(true); // kullanıcı parametreleri girsin
				bağlantılıAyar.doğrusalKümeleme.setSelected(true);
				bağlantılıAyar.ortalamaBağlantılı.setSelected(true);
				bağlantılıAyar.tekBağlatılı.setSelected(true);
			}
		});
		btnTmYntemlerlez.setBounds(221, 249, 166, 23);
		contentPane.add(btnTmYntemlerlez);
	}
	private JButton panel1() {
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "BAĞLANTILI KÜMELEME YÖNTEMLERİ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 25, 250, 119);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		// bağlantılı kümeleme için ayaarlar tuşu
		JButton btnBalantlKmelemeAyarlar = new JButton("ba\u011Flant\u0131l\u0131 ayarlar");
		btnBalantlKmelemeAyarlar.setBounds(6, 56, 175, 52);
		panel_1.add(btnBalantlKmelemeAyarlar);
		return btnBalantlKmelemeAyarlar;
	}
	private void panel2(JButton btnBalantlKmelemeAyarlar) {
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "HÜCRESEL KÜMELEMEYE ÖZGÜ YÖNTEMLER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(668, 16, 268, 221);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 16, 177, 198);
		panel_2.add(separator);
		
		dereceSıra = new JCheckBox("derece s\u0131ra");
		dereceSıra.setBounds(6, 39, 97, 23);
		panel_2.add(dereceSıra);
		
		bağEnerji = new JCheckBox("ba\u011F enerji");
		bağEnerji.setBounds(6, 79, 97, 23);
		panel_2.add(bağEnerji);
		
		kümeBulmaYöntemi = new JCheckBox("k\u00FCme bulma y\u00F6ntemi");
		kümeBulmaYöntemi.setBounds(6, 123, 131, 23);
		panel_2.add(kümeBulmaYöntemi);
		btnBalantlKmelemeAyarlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bağlantılıSeçildiMi = true;
				bağlantılıAyar.setVisible(true);
			}
		});
	}
	private void panel0() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "META SEZGİSELLER", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(266, 16, 303, 193);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// parçacık sürü için ayarlar tuşu
		JButton btnParackSrAyarlar = new JButton("par\u00E7ac\u0131k s\u00FCr\u00FC ayarlar\u0131");
		btnParackSrAyarlar.setBounds(78, 21, 166, 23);
		panel.add(btnParackSrAyarlar);
		
		JLabel lblKortalamalarKmeSays = new JLabel("k-ortalamalar k\u00FCme say\u0131s\u0131");
		lblKortalamalarKmeSays.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKortalamalarKmeSays.setBounds(10, 90, 190, 14);
		panel.add(lblKortalamalarKmeSays);
		
		kOrtalamalarKümeSayısı = new JTextField();
		kOrtalamalarKümeSayısı.setBounds(240, 87, 53, 20);
		panel.add(kOrtalamalarKümeSayısı);
		kOrtalamalarKümeSayısı.setColumns(10);
		
		kOrtalamalar = new JCheckBox("k- ortalamalar");
		kOrtalamalar.setBounds(10, 60, 180, 23);
		panel.add(kOrtalamalar);
		
		denemeSayısıYazı = new JTextField();
		denemeSayısıYazı.setBounds(240, 118, 53, 20);
		panel.add(denemeSayısıYazı);
		denemeSayısıYazı.setColumns(10);
		denemeSayısıYazı.setText("1");
		
		JLabel lblDenemeSays = new JLabel("deneme say\u0131s\u0131");
		lblDenemeSays.setBounds(10, 121, 97, 14);
		panel.add(lblDenemeSays);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 51, 303, 2);
		panel.add(separator);
		btnParackSrAyarlar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parçacıkSürüSeçildiMi = true;
				psaAyar.setVisible(true);
			}
		});
	}
	
	private void anaŞekliOluştur() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 962, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yanŞekilleriOluştur() {
		aşamalarıGöster = new JCheckBox("a\u015Famalar\u0131 g\u00F6ster");
		aşamalarıGöster.setBounds(445, 249, 152, 23);
		contentPane.add(aşamalarıGöster);
	}
}
