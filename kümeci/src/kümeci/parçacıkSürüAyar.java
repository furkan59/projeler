package k�meci;

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

public class par�ac�kS�r�Ayar extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField par�ac�kSay�s�Yaz�;
	public JTextField k�meSay�s�Yaz�;
	public JTextField kendineG�venYaz�;
	public JTextField s�r�yeG�venYaz�;
	public JTextField eylemsizlikYaz�;
	public JTextField ilkH�zlarYaz�;
	public JRadioButton a�amalar�G�sterYaz�;
	public JTextField enFazlaA�amaYaz�;
	public JTextField enFazla�lerlemeYaz�;
    
    public int k�meSay�s�;
    public int par�ac�kSay�s� ;
    public double kendineG�ven ;
    public double s�r�yeG�ven ;
    public double eylemsizlik ;
    public double ilkH�z  ;
    public int enFazlaA�ama ;
    public boolean ilerlemeK�s�tl� = true;
    public int enFazla�lerleme = 0;
    public boolean a�amalar�G�ster ;
    private JButton btnKaydetVe��k;
    public String hatalar = "";
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					par�ac�kS�r�Ayar frame = new par�ac�kS�r�Ayar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public par�ac�kS�r�Ayar() {
		�ekliOlu�tur();
		kaydetVe��kTu�u();
	    setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	private void t�mHatalar�Yaz() {
		if(hatalar.compareTo("") != 0 ){
			��kt�Yaz("par�ac�k s�r� ayalar� yap�l�rkenki hatalar\n" +hatalar);
		}
	}

	private void de�erleriAl() {
		k�meVePar�ac�kSay�s�n�Al();
	    g�venParametreleriniAl();
	    h�z�Al();
	    ilerlemeParametreleriniAl();
	    a�amalar�G�ster = a�amalar�G�sterYaz�.isSelected();
	}
	private void h�z�Al() {
		String yaz� = ilkH�zlarYaz�.getText();
		ilkH�zGirilmi�MiBak(yaz�);
		ilkH�z = Double.parseDouble(ilkH�zlarYaz�.getText());
		ilkH�zPozitifMiBak();
	}
	private void ilkH�zPozitifMiBak() {
		if(ilkH�z <= 0){
			hatalar += "�lk h�z pozitif olmal�d�r. Girilen ilk h�z de�eri" + ilkH�z +"\n";
		}
	}
	private void ilkH�zGirilmi�MiBak(String yaz�) {
		if(yaz�.compareTo("") ==0){
			hatalar += "ilk h�z girilmemi�\n";
		}
	}
	
	private void ilerlemeParametreleriniAl() {
		enFazlaA�amaDe�eriniOku();
		enFazla�lerlemDe�eriniOku();	
		ilerlemeK�s�t�l�y�Oku();
	    a�amalar�G�ster = a�amalar�G�sterYaz�.isSelected();
	}

	private void ilerlemeK�s�t�l�y�Oku() {
		if ( enFazla�lerleme == 0){ // hi� dokunulmam�� ise 
			ilerlemeK�s�tl� = false;	
		}
	}
	private void enFazla�lerlemDe�eriniOku() {
		String yaz� = enFazla�lerlemeYaz�.getText();
	    if(yaz�.contains(",") || yaz�.contains(".")){
	    	hatalar += "en fazla ilerleme de�eri tam say� olmal�d�r\n";
	    }
	    enFazla�lerleme  = Integer.parseInt(yaz�);
	    if( enFazla�lerleme < 0 ){
	    	hatalar += "en fazla ilerleme de�eri pozitif olmal�d�r\n";
	    }
	}

	private void enFazlaA�amaDe�eriniOku() {
		String yaz� = enFazlaA�amaYaz�.getText();
		enFazlaA�amaTamSay�M�Bak(yaz�);
		enFazlaA�ama= Integer.parseInt(yaz�);
		enFazlaA�amaPozitifMi();
	}
	private void enFazlaA�amaPozitifMi() {
		if( enFazlaA�ama < 0 ){
	    	hatalar += "en fazla a�ama de�eri pozitif olmal�d�r\n";
	    }
	}
	private void enFazlaA�amaTamSay�M�Bak(String yaz�) {
		if(yaz�.contains(",") || yaz�.contains(".") ){
			hatalar += "en fazla a�ama tam say� olmal�d�r\n";
		}
	}

	private void g�venParametreleriniAl() {
		kendineG�venmeOku();
		s�r�yeG�venOku();
	    eylemsizlikDe�eriniOku();
	}
	private void s�r�yeG�venOku() {
		s�r�yeG�ven = Double.parseDouble(s�r�yeG�venYaz�.getText());
		if(s�r�yeG�ven <= 0 ){
	    	hatalar += "s�r�ye g�venme de�eri s�f�r ve ya s�f�rdan k���k olamaz girilen s�r�ye g�venme de�eri : " + s�r�yeG�ven +"\n";
	    }
		s�r�yeG�ven= Double.parseDouble(s�r�yeG�venYaz�.getText());
	}
	private void kendineG�venmeOku() {
		kendineG�ven = Double.parseDouble(kendineG�venYaz�.getText());
	    if(kendineG�ven <= 0 ){
	    	hatalar += "kendine g�venme de�eri s�f�r ve ya s�f�rdan k���k olamaz girilen kendine g�venme de�eri : " + kendineG�ven +"\n";
	    }
	}
	private void eylemsizlikDe�eriniOku() {
		String yaz� = eylemsizlikYaz�.getText();
	    eylemsizlik= Double.parseDouble(yaz�);
	    eylemsizlikUygunAral�ktaM�();
	}
	private void eylemsizlikUygunAral�ktaM�() {
		if(eylemsizlik <= 0 || eylemsizlik >= 1){
	    	hatalar += "eylemsizlik de�eri 0 ile 1 aras�nda olmal�\n";
	    }
	}

	private void k�meVePar�ac�kSay�s�n�Al() {
		k�meSay�s�Al();
		par�ac�kSay�s�Al();
	}
	private void par�ac�kSay�s�Al() {
		String yaz� = par�ac�kSay�s�Yaz�.getText();
		par�ac�kSay�s�TamSay�M�(yaz�);
		par�ac�kSay�s� = Integer.parseInt(yaz�);
		par�ac�kSay�s�PozitifMi();
	}
	private void par�ac�kSay�s�PozitifMi() {
		if( par�ac�kSay�s� <= 0 ){
	    	hatalar += "par�ac�k say�s� pozitif olmal�d�r\n";
	    }
	}

	private void par�ac�kSay�s�TamSay�M�(String yaz�) {
		if(yaz�.contains(",") || yaz�.contains(".")){
	    	hatalar += " par�ac�k say�s� tam say� olmal�d�r\n";
	    }
	}
	private void k�meSay�s�Al() {
		String yaz� = k�meSay�s�Yaz�.getText();
		k�meSay�s�TamM�(yaz�);
		k�meSay�s� = Integer.parseInt(yaz�);
		k�meSay�s�PozitifMi();
	}
	private void k�meSay�s�PozitifMi() {
		if( k�meSay�s� <= 0 ){
	    	hatalar += "k�me say�s� pozitif olmal�d�r\n";
	    }
	}
	private void k�meSay�s�TamM�(String yaz�) {
		if(yaz�.contains(",") || yaz�.contains(".")){
	    	hatalar += " k�me say�s� tam say� olmal�d�r\n";
	    }
	}

	private void �ekliOlu�tur() {
		ana�ekliOlu�tur();
		yaz�lar�Olu�tur();
	}

	private void kaydetVe��kTu�u() {
		btnKaydetVe��k = new JButton("kaydet ve \u00E7\u0131k");
		btnKaydetVe��k.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				de�erleriAl();
				t�mHatalar�Yaz();
			}
		});
		btnKaydetVe��k.setBounds(155, 218, 128, 23);
		contentPane.add(btnKaydetVe��k);
		
	}
	private void ana�ekliOlu�tur() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	private void yaz�lar�Olu�tur() {
		JLabel lblParackSays = new JLabel("par\u00E7ac\u0131k say\u0131s\u0131");
		lblParackSays.setBounds(23, 11, 87, 14);
		contentPane.add(lblParackSays);
		
		par�ac�kSay�s�Yaz� = new JTextField();
		par�ac�kSay�s�Yaz�.setText("0");
		par�ac�kSay�s�Yaz�.setBounds(158, 8, 86, 20);
		contentPane.add(par�ac�kSay�s�Yaz�);
		par�ac�kSay�s�Yaz�.setColumns(10);
		
		JLabel lblKmeSays = new JLabel("k\u00FCme say\u0131s\u0131");
		lblKmeSays.setBounds(23, 40, 100, 14);
		contentPane.add(lblKmeSays);
		
		k�meSay�s�Yaz� = new JTextField();
		k�meSay�s�Yaz�.setText("0");
		k�meSay�s�Yaz�.setBounds(158, 39, 86, 20);
		contentPane.add(k�meSay�s�Yaz�);
		k�meSay�s�Yaz�.setColumns(10);
		
		kendineG�venYaz� = new JTextField();
		kendineG�venYaz�.setText("0");
		kendineG�venYaz�.setBounds(158, 97, 86, 20);
		contentPane.add(kendineG�venYaz�);
		kendineG�venYaz�.setColumns(10);
		
		JLabel lblKendineGven = new JLabel("kendine g\u00FCven");
		lblKendineGven.setBounds(24, 100, 86, 14);
		contentPane.add(lblKendineGven);
		
		JLabel lblSryeGven = new JLabel("s\u00FCr\u00FCye g\u00FCven");
		lblSryeGven.setBounds(23, 125, 87, 14);
		contentPane.add(lblSryeGven);
		
		s�r�yeG�venYaz� = new JTextField();
		s�r�yeG�venYaz�.setText("0");
		s�r�yeG�venYaz�.setBounds(158, 122, 86, 20);
		contentPane.add(s�r�yeG�venYaz�);
		s�r�yeG�venYaz�.setColumns(10);
		
		eylemsizlikYaz� = new JTextField();
		eylemsizlikYaz�.setText("0");
		eylemsizlikYaz�.setBounds(158, 147, 86, 20);
		contentPane.add(eylemsizlikYaz�);
		eylemsizlikYaz�.setColumns(10);
		
		JLabel lblEylemsizlik = new JLabel("eylemsizlik");
		lblEylemsizlik.setBounds(23, 150, 64, 14);
		contentPane.add(lblEylemsizlik);
		
		ilkH�zlarYaz� = new JTextField();
		ilkH�zlarYaz�.setText("0");
		ilkH�zlarYaz�.setBounds(386, 8, 86, 20);
		contentPane.add(ilkH�zlarYaz�);
		ilkH�zlarYaz�.setColumns(10);
		
		JLabel lblIlkHzlar = new JLabel("ilk h\u0131zlar");
		lblIlkHzlar.setBounds(295, 11, 64, 14);
		contentPane.add(lblIlkHzlar);
		
		a�amalar�G�sterYaz� = new JRadioButton("a\u015Famalar\u0131 g\u00F6ster");
		a�amalar�G�sterYaz�.setBounds(312, 146, 147, 23);
		contentPane.add(a�amalar�G�sterYaz�);
		
		JLabel lblEnFazlaAama = new JLabel("en fazla a\u015Fama");
		lblEnFazlaAama.setBounds(295, 42, 87, 14);
		contentPane.add(lblEnFazlaAama);
		
		JLabel lblEnFazlaIlerleme = new JLabel("en fazla ilerleme");
		lblEnFazlaIlerleme.setBounds(295, 78, 87, 14);
		contentPane.add(lblEnFazlaIlerleme);
		
		enFazlaA�amaYaz� = new JTextField();
		enFazlaA�amaYaz�.setText("0");
		enFazlaA�amaYaz�.setColumns(10);
		enFazlaA�amaYaz�.setBounds(386, 39, 86, 20);
		contentPane.add(enFazlaA�amaYaz�);
		
		enFazla�lerlemeYaz� = new JTextField();
		enFazla�lerlemeYaz�.setText("0");
		enFazla�lerlemeYaz�.setColumns(10);
		enFazla�lerlemeYaz�.setBounds(386, 75, 86, 20);
		contentPane.add(enFazla�lerlemeYaz�);
	}
	
	private void ��kt�Yaz(String yaz�) {
		��kt� sonu� = new ��kt�();
		sonu�.textArea.setText(yaz�);
		sonu�.setVisible(true);
		sonu�.setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
}
