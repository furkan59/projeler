package kümeci;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class parçacıkSürüAyar extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField parçacıkSayısıYazı;
	public JTextField kümeSayısıYazı;
	public JTextField kendineGüvenYazı;
	public JTextField sürüyeGüvenYazı;
	public JTextField eylemsizlikYazı;
	public JTextField ilkHızlarYazı;
	public JRadioButton aşamalarıGösterYazı;
	public JTextField enFazlaAşamaYazı;
	public JTextField enFazlaİlerlemeYazı;
    
    public int kümeSayısı;
    public int parçacıkSayısı ;
    public double kendineGüven ;
    public double sürüyeGüven ;
    public double eylemsizlik ;
    public double ilkHız  ;
    public int enFazlaAşama ;
    public boolean ilerlemeKısıtlı = true;
    public int enFazlaİlerleme = 0;
    public boolean aşamalarıGöster ;
    private JButton btnKaydetVeÇık;
    public String hatalar = "";
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parçacıkSürüAyar frame = new parçacıkSürüAyar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public parçacıkSürüAyar() {
		şekliOluştur();
		kaydetVeÇıkTuşu();
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	private void tümHatalarıYaz() {
		if(hatalar.compareTo("") != 0 ){
			çıktıYaz("parçacık sürü ayaları yapılırkenki hatalar\n" +hatalar);
		}
	}

	private void değerleriAl() {
		kümeVeParçacıkSayısınıAl();
	    güvenParametreleriniAl();
	    hızıAl();
	    ilerlemeParametreleriniAl();
	    aşamalarıGöster = aşamalarıGösterYazı.isSelected();
	}
	private void hızıAl() {
		String yazı = ilkHızlarYazı.getText();
		ilkHızGirilmişMiBak(yazı);
		ilkHız = Double.parseDouble(ilkHızlarYazı.getText());
		ilkHızPozitifMiBak();
	}
	private void ilkHızPozitifMiBak() {
		if(ilkHız <= 0){
			hatalar += "İlk hız pozitif olmalıdır. Girilen ilk hız değeri" + ilkHız +"\n";
		}
	}
	private void ilkHızGirilmişMiBak(String yazı) {
		if(yazı.compareTo("") ==0){
			hatalar += "ilk hız girilmemiş\n";
		}
	}
	
	private void ilerlemeParametreleriniAl() {
		enFazlaAşamaDeğeriniOku();
		enFazlaİlerlemDeğeriniOku();	
		ilerlemeKısıtılıyıOku();
	    aşamalarıGöster = aşamalarıGösterYazı.isSelected();
	}

	private void ilerlemeKısıtılıyıOku() {
		if ( enFazlaİlerleme == 0){ // hiç dokunulmamış ise 
			ilerlemeKısıtlı = false;	
		}
	}
	private void enFazlaİlerlemDeğeriniOku() {
		String yazı = enFazlaİlerlemeYazı.getText();
	    if(yazı.contains(",") || yazı.contains(".")){
	    	hatalar += "en fazla ilerleme değeri tam sayı olmalıdır\n";
	    }
	    enFazlaİlerleme  = Integer.parseInt(yazı);
	    if( enFazlaİlerleme < 0 ){
	    	hatalar += "en fazla ilerleme değeri pozitif olmalıdır\n";
	    }
	}

	private void enFazlaAşamaDeğeriniOku() {
		String yazı = enFazlaAşamaYazı.getText();
		enFazlaAşamaTamSayıMıBak(yazı);
		enFazlaAşama= Integer.parseInt(yazı);
		enFazlaAşamaPozitifMi();
	}
	private void enFazlaAşamaPozitifMi() {
		if( enFazlaAşama < 0 ){
	    	hatalar += "en fazla aşama değeri pozitif olmalıdır\n";
	    }
	}
	private void enFazlaAşamaTamSayıMıBak(String yazı) {
		if(yazı.contains(",") || yazı.contains(".") ){
			hatalar += "en fazla aşama tam sayı olmalıdır\n";
		}
	}

	private void güvenParametreleriniAl() {
		kendineGüvenmeOku();
		sürüyeGüvenOku();
	    eylemsizlikDeğeriniOku();
	}
	private void sürüyeGüvenOku() {
		sürüyeGüven = Double.parseDouble(sürüyeGüvenYazı.getText());
		if(sürüyeGüven <= 0 ){
	    	hatalar += "sürüye güvenme değeri sıfır ve ya sıfırdan küçük olamaz girilen sürüye güvenme değeri : " + sürüyeGüven +"\n";
	    }
		sürüyeGüven= Double.parseDouble(sürüyeGüvenYazı.getText());
	}
	private void kendineGüvenmeOku() {
		kendineGüven = Double.parseDouble(kendineGüvenYazı.getText());
	    if(kendineGüven <= 0 ){
	    	hatalar += "kendine güvenme değeri sıfır ve ya sıfırdan küçük olamaz girilen kendine güvenme değeri : " + kendineGüven +"\n";
	    }
	}
	private void eylemsizlikDeğeriniOku() {
		String yazı = eylemsizlikYazı.getText();
	    eylemsizlik= Double.parseDouble(yazı);
	    eylemsizlikUygunAralıktaMı();
	}
	private void eylemsizlikUygunAralıktaMı() {
		if(eylemsizlik <= 0 || eylemsizlik >= 1){
	    	hatalar += "eylemsizlik değeri 0 ile 1 arasında olmalı\n";
	    }
	}

	private void kümeVeParçacıkSayısınıAl() {
		kümeSayısıAl();
		parçacıkSayısıAl();
	}
	private void parçacıkSayısıAl() {
		String yazı = parçacıkSayısıYazı.getText();
		parçacıkSayısıTamSayıMı(yazı);
		parçacıkSayısı = Integer.parseInt(yazı);
		parçacıkSayısıPozitifMi();
	}
	private void parçacıkSayısıPozitifMi() {
		if( parçacıkSayısı <= 0 ){
	    	hatalar += "parçacık sayısı pozitif olmalıdır\n";
	    }
	}

	private void parçacıkSayısıTamSayıMı(String yazı) {
		if(yazı.contains(",") || yazı.contains(".")){
	    	hatalar += " parçacık sayısı tam sayı olmalıdır\n";
	    }
	}
	private void kümeSayısıAl() {
		String yazı = kümeSayısıYazı.getText();
		kümeSayısıTamMı(yazı);
		kümeSayısı = Integer.parseInt(yazı);
		kümeSayısıPozitifMi();
	}
	private void kümeSayısıPozitifMi() {
		if( kümeSayısı <= 0 ){
	    	hatalar += "küme sayısı pozitif olmalıdır\n";
	    }
	}
	private void kümeSayısıTamMı(String yazı) {
		if(yazı.contains(",") || yazı.contains(".")){
	    	hatalar += " küme sayısı tam sayı olmalıdır\n";
	    }
	}

	private void şekliOluştur() {
		anaŞekliOluştur();
		yazılarıOluştur();
	}

	private void kaydetVeÇıkTuşu() {
		btnKaydetVeÇık = new JButton("kaydet ve \u00E7\u0131k");
		btnKaydetVeÇık.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				değerleriAl();
				tümHatalarıYaz();
			}
		});
		btnKaydetVeÇık.setBounds(155, 218, 128, 23);
		contentPane.add(btnKaydetVeÇık);
		
	}
	private void anaŞekliOluştur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yazılarıOluştur() {
		JLabel lblParackSays = new JLabel("par\u00E7ac\u0131k say\u0131s\u0131");
		lblParackSays.setBounds(23, 11, 87, 14);
		contentPane.add(lblParackSays);
		
		parçacıkSayısıYazı = new JTextField();
		parçacıkSayısıYazı.setText("0");
		parçacıkSayısıYazı.setBounds(158, 8, 86, 20);
		contentPane.add(parçacıkSayısıYazı);
		parçacıkSayısıYazı.setColumns(10);
		
		JLabel lblKmeSays = new JLabel("k\u00FCme say\u0131s\u0131");
		lblKmeSays.setBounds(23, 40, 100, 14);
		contentPane.add(lblKmeSays);
		
		kümeSayısıYazı = new JTextField();
		kümeSayısıYazı.setText("0");
		kümeSayısıYazı.setBounds(158, 39, 86, 20);
		contentPane.add(kümeSayısıYazı);
		kümeSayısıYazı.setColumns(10);
		
		kendineGüvenYazı = new JTextField();
		kendineGüvenYazı.setText("0");
		kendineGüvenYazı.setBounds(158, 97, 86, 20);
		contentPane.add(kendineGüvenYazı);
		kendineGüvenYazı.setColumns(10);
		
		JLabel lblKendineGven = new JLabel("kendine g\u00FCven");
		lblKendineGven.setBounds(24, 100, 86, 14);
		contentPane.add(lblKendineGven);
		
		JLabel lblSryeGven = new JLabel("s\u00FCr\u00FCye g\u00FCven");
		lblSryeGven.setBounds(23, 125, 87, 14);
		contentPane.add(lblSryeGven);
		
		sürüyeGüvenYazı = new JTextField();
		sürüyeGüvenYazı.setText("0");
		sürüyeGüvenYazı.setBounds(158, 122, 86, 20);
		contentPane.add(sürüyeGüvenYazı);
		sürüyeGüvenYazı.setColumns(10);
		
		eylemsizlikYazı = new JTextField();
		eylemsizlikYazı.setText("0");
		eylemsizlikYazı.setBounds(158, 147, 86, 20);
		contentPane.add(eylemsizlikYazı);
		eylemsizlikYazı.setColumns(10);
		
		JLabel lblEylemsizlik = new JLabel("eylemsizlik");
		lblEylemsizlik.setBounds(23, 150, 64, 14);
		contentPane.add(lblEylemsizlik);
		
		ilkHızlarYazı = new JTextField();
		ilkHızlarYazı.setText("0");
		ilkHızlarYazı.setBounds(386, 8, 86, 20);
		contentPane.add(ilkHızlarYazı);
		ilkHızlarYazı.setColumns(10);
		
		JLabel lblIlkHzlar = new JLabel("ilk h\u0131zlar");
		lblIlkHzlar.setBounds(295, 11, 64, 14);
		contentPane.add(lblIlkHzlar);
		
		aşamalarıGösterYazı = new JRadioButton("a\u015Famalar\u0131 g\u00F6ster");
		aşamalarıGösterYazı.setBounds(312, 146, 147, 23);
		contentPane.add(aşamalarıGösterYazı);
		
		JLabel lblEnFazlaAama = new JLabel("en fazla a\u015Fama");
		lblEnFazlaAama.setBounds(295, 42, 87, 14);
		contentPane.add(lblEnFazlaAama);
		
		JLabel lblEnFazlaIlerleme = new JLabel("en fazla ilerleme");
		lblEnFazlaIlerleme.setBounds(295, 78, 87, 14);
		contentPane.add(lblEnFazlaIlerleme);
		
		enFazlaAşamaYazı = new JTextField();
		enFazlaAşamaYazı.setText("0");
		enFazlaAşamaYazı.setColumns(10);
		enFazlaAşamaYazı.setBounds(386, 39, 86, 20);
		contentPane.add(enFazlaAşamaYazı);
		
		enFazlaİlerlemeYazı = new JTextField();
		enFazlaİlerlemeYazı.setText("0");
		enFazlaİlerlemeYazı.setColumns(10);
		enFazlaİlerlemeYazı.setBounds(386, 75, 86, 20);
		contentPane.add(enFazlaİlerlemeYazı);
	}
	
	private void çıktıYaz(String yazı) {
		çıktı sonuç = new çıktı();
		sonuç.textArea.setText(yazı);
		sonuç.setVisible(true);
		sonuç.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
