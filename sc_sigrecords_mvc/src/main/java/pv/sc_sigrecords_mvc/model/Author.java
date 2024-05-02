package pv.sc_sigrecords_mvc.model;

public class Author {
	
	private String position;
	private String name;
	private int counter;
	
	
	public Author(String position, String name) {
		super();
		this.position = position;
		this.name = name;
		this.counter = 0;
	}
	

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
	
	public void incrementCounter() {
		this.counter++;
	}


	@Override
	public String toString() {
		return "Author [position=" + position + ", name=" + name + ", counter=" + counter + "]";
	}
	
	
	
	
	

}
