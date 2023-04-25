package dominio;

public class Sms {
	
	private int id;
	private String sms;
	
	public Sms(String sms) {
		this.sms = sms;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}

}
