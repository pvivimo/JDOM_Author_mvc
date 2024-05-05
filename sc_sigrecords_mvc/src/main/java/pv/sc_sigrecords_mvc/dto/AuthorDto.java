package pv.sc_sigrecords_mvc.dto;

public class AuthorDto {
	private String name;
	private int occurance;
	
	
	public AuthorDto(String name) {
		super();
		this.name = name;
		this.occurance = 1;
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOccurance() {
		return occurance;
	}
	
	public void setOccurance(int occurance) {
		this.occurance = occurance;
	}
	
	public void incremenetOccurance() {
		this.occurance++;
	}


	@Override
	public String toString() {
		return "AuthorDto [name=" + name + ", occurance=" + occurance + "]";
	}
	
	
}
