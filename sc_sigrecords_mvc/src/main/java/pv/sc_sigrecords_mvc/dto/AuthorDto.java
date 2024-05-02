package pv.sc_sigrecords_mvc.dto;

public class AuthorDto {
	
	private String name;
	private int counter;
	
	
	public AuthorDto(String name, int counter) {
		super();
		this.name = name;
		this.counter = counter;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}


	@Override
	public String toString() {
		return "AuthorDto [name=" + name + ", counter=" + counter + "]";
	}
	
	
	
	
	

}
