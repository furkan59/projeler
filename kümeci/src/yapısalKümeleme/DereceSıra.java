package yap�salK�meleme;
import k�meci.K�meleme;
import veriYap�lar�.*;
public class DereceS�ra extends K�meleme {

	
	private Boolean sat�rlarM� ; // sat�lar� m� s�ral�yoruz
	public DereceS�ra(BoolDizi par�aMakine, String[] makine�simleri,String[] par�a�simleri) {
		super(par�aMakine, makine�simleri, par�a�simleri);
		sat�rlarM� = true; 
		makineGruplar�Olu�tur();
	}
    public DereceS�ra(String girdi) throws Exception{
    	super(girdi);
    	sat�rlarM� = true;
    	makineGruplar�Olu�tur();
    }
    private void sat�rNoKontrolEt(int sat�rNo) throws Exception {
		if(sat�rNo < 0 || sat�rNo > this.par�aMakine.sat�rSay()-1){
    		throw new Exception("sat�rNo uyumsuz girilen sat�rno = " + sat�rNo + " toplam sat�r say�s� = " + par�aMakine.sat�rSay() + "\n");
    	}
	}
    
	public void sat�rlar�S�rala() throws Exception{
		//par�aMakine.sat�rlar�S�rala();
		int sat�rNo;
    	for(sat�rNo=0;sat�rNo<par�aMakine.sat�rSay();sat�rNo++){
    		enB�y�kSat�r�Ba�aAl(sat�rNo);
    	}
	}
    private void enB�y�kSat�r�Ba�aAl(int sat�rNo) throws Exception {
		int sat�rNo2;
		int Enb�y�k = sat�rNo; // ilk ba�ta bunu kabul edelim
		for(sat�rNo2=sat�rNo+1;sat�rNo2<par�aMakine.sat�rSay();sat�rNo2++){
			if(par�aMakine.sat�r(Enb�y�k).b�y�kM�(par�aMakine.sat�r(sat�rNo2)) == 1) {// sat�rNo2 daha b�y�k
				Enb�y�k = sat�rNo2;
			}
		}
		sat�r��inDe�i�tirmeler(sat�rNo, Enb�y�k);
	}
    private void sat�r��inDe�i�tirmeler(int sat�rNo, int Enb�y�k) throws Exception {
		par�aMakine.sat�r�De�i�(sat�rNo, Enb�y�k);
		String de�i�ecekMakine = makine�simleri[sat�rNo]; 
		makine�simleri[sat�rNo] = makine�simleri[Enb�y�k];
		makine�simleri[Enb�y�k] = de�i�ecekMakine;
	}
	
    public void sutunlar�S�rala() throws Exception{
    	int sutunNo;
    	for(sutunNo=0;sutunNo<par�aMakine.sutunSay();sutunNo++){
    		enB�y�kSutunuBa�aAl(sutunNo);
    	}
    }
    private void enB�y�kSutunuBa�aAl(int sutunNo) throws Exception {
		int sutunNo2;
		int Enb�y�k = sutunNo; // ilk ba�ta bunu kabul edelim
		for(sutunNo2=sutunNo+1;sutunNo2<par�aMakine.sutunSay();sutunNo2++){
			if(par�aMakine.ikiSutunuKar��la�t�r(sutunNo2, Enb�y�k) == 1) {// sutunNo2 daha b�y�k
				Enb�y�k = sutunNo2;
			}
		}
		sutun��inDe�i�tirmeYap(sutunNo, Enb�y�k);
	}

	private void sutun��inDe�i�tirmeYap(int sutunNo, int Enb�y�k) throws Exception {
		par�aMakine.sutunuDe�i�(sutunNo, Enb�y�k);
		String de�i�ecekPar�a = par�a�simleri[sutunNo];
		par�a�simleri[sutunNo] = par�a�simleri[Enb�y�k];
		par�a�simleri[Enb�y�k] = de�i�ecekPar�a;
	}
	
	public Boolean sat�rlarS�ral�M�() throws Exception{
		return par�aMakine.sat�rlarS�ral�M�();
	}
	public Boolean sutunlarS�ral�M�() throws Exception{
		return par�aMakine.sutunlarS�ral�M�();
	}

	/**
	 * e�er ilerlenebilir bir durumda ise true d�ner 
	 * @return
	 * @throws Exception
	 */
	public Boolean ilerle() throws Exception{
		if(sat�rlarM� == true){
			return sat�r�lerle();
		}
		else {
			return sutundan�lerle();
		}
	}
    private Boolean sutundan�lerle() throws Exception {
		if(sutunlarS�ral�M�() == true){
			return false;
		}
		else {
			sutunlar�S�rala();
			return true;
		}
	}
	private Boolean sat�r�lerle() throws Exception {
		if(sat�rlarS�ral�M�() == true){
			return false;
		}
		else {
			sat�rlar�S�rala();
			return true;
		}
	}
    
	public String ��z () throws Exception{
		String ��z�m = "";
		int a�amaNo;
		for(a�amaNo=0;a�amaNo<10;a�amaNo++){
			if( ilerle() == true){
				��z�m += "a�ama no" + a�amaNo  + "\n\n"  + yaz()  +"\n\n";
				sat�rlarM� = !sat�rlarM�;	
			}
			else {
				break;
			}
			
		}
		��z�m += "a�ama no" + a�amaNo  + "\n\n"  + yaz()  +"\n\n";
		return ��z�m;
	}
	
	@Override
	public String yaz() throws Exception{
		String yaz� = "sat�rlar m� = " + sat�rlarM� + "\n\n" +
                       super.yaz();
		return yaz�;
	}
	public String h�zl���z() throws Exception {
		String ��z�m = "";
		int a�amaNo;
		for(a�amaNo=0;a�amaNo<10;a�amaNo++){
			if( ilerle() == true){
				sat�rlarM� = !sat�rlarM�;	
			}
			else {
				break;
			}
			
		}
		��z�m += "a�ama no" + a�amaNo  + "\n\n"  + yaz()  +"\n\n";
		return ��z�m;
	}
}
