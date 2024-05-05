package pv.sc_sigrecords_mvc.dto;

import java.util.ArrayList;
import java.util.List;


public class AuthorsListDto {
	
private List<AuthorDto> authorsList;

	
	public AuthorsListDto() {
		authorsList = new ArrayList<>();
	}


	public List<AuthorDto> getAuthorsList() {
		return authorsList;
	}

	public void setAuthorsList(List<AuthorDto> authorsList) {
		this.authorsList = authorsList;
	}
	
	
	public void addAuthor(AuthorDto aDto) {
		this.authorsList.add(aDto);
	}


	public AuthorDto getAuthorDtoByName(String searchedAuthorName) {

		AuthorDto authorDto = null;
		
		
		for(int index = 0; index < authorsList.size(); index++) {
		
			AuthorDto currentAuthorDto = authorsList.get(index);
			if( currentAuthorDto.getName().equals(searchedAuthorName) ) {
				
				authorDto = currentAuthorDto;
				break;
			}
		}
		
		
		return authorDto;
	}
	
	
	public void sortByName() {
		
		for(int currentIndex = 0; currentIndex < authorsList.size(); currentIndex++) {
			
			AuthorDto currentAuthorDto = authorsList.get(currentIndex);
			
			for(int nextIndex = currentIndex + 1; nextIndex < authorsList.size(); nextIndex++) {
				
				AuthorDto nextAuthorDto = authorsList.get(nextIndex);
				if( currentAuthorDto.getName().compareTo( nextAuthorDto.getName() ) > 0 ) {
					
					authorsList.set(currentIndex, nextAuthorDto);
					authorsList.set(nextIndex, currentAuthorDto);
					currentIndex--;
					break;
				}
			}
		}
	}


	public void sortByOccurances() {
		
		for(int currentIndex = 0; currentIndex < authorsList.size(); currentIndex++) {
			
			AuthorDto currentAuthorDto = authorsList.get(currentIndex);
			
			for(int nextIndex = currentIndex + 1; nextIndex < authorsList.size(); nextIndex++) {
				
				AuthorDto nextAuthorDto = authorsList.get(nextIndex);
				if( currentAuthorDto.getOccurance() < nextAuthorDto.getOccurance() ) {

					authorsList.set(currentIndex, nextAuthorDto);
					authorsList.set(nextIndex, currentAuthorDto);
					currentIndex--;
					break;
				}
			}
		}
	}
	
	
}
