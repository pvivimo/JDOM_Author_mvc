package pv.sc_sigrecords_mvc.model;

public class Author {
	
	private String name;
	private String position;
	
	
	public Author(String name, String position) {
		super();
		this.name = name;
		this.position = position;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

}
