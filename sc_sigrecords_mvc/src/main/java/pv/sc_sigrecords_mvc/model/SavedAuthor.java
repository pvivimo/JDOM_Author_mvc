package pv.sc_sigrecords_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="savedauthors")
public class SavedAuthor {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="occurance")
	private int occurance;
	
	
	public SavedAuthor() {
		super();
	}

	public SavedAuthor(String name, int occurance) {
		super();
		this.name = name;
		this.occurance = occurance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "SavedAuthor [id=" + id + ", name=" + name + ", occurance=" + occurance + "]";
	}

}
