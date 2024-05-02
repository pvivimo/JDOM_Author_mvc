package pv.sc_sigrecords_mvc.dto;

import java.util.ArrayList;
import java.util.List;

import pv.sc_sigrecords_mvc.model.Author;

public class AuthorsDto {
	
	private List<AuthorDto> authorsDtosList;


	public AuthorsDto() {
		super();
		this.authorsDtosList = new ArrayList<>();
	}
	
	public List<AuthorDto> getAuthorsDtosList() {
		return authorsDtosList;
	}

	public void setAuthorsDtosList(List<AuthorDto> authorsDtosList) {
		this.authorsDtosList = authorsDtosList;
	}

	public void addAuthor(AuthorDto authorDto) {
		this.authorsDtosList.add(authorDto);
	}
	
	public void sortAuthors() {
		
		for(int currentIndex = 0; currentIndex < authorsDtosList.size(); currentIndex++) {
			
			AuthorDto currentDtoList = authorsDtosList.get(currentIndex);
			for(int nextIndex = 0; nextIndex < authorsDtosList.size(); nextIndex++) {
				
				AuthorDto nextDto = authorsDtosList.get(nextIndex);
				if(currentDtoList.getName().compareTo(nextDto.getName()) > 0 ) {
					
					authorsDtosList.set(nextIndex, currentDtoList);
					authorsDtosList.set(currentIndex, nextDto);
					currentIndex--;
					break;
					
				}
			}
			
		}
		
	}
	
	
	@Override
	public String toString() {
		return "AuthorsDto [authorsDtosList=" + authorsDtosList + "]";
	}

	
	
	
	
	

}
