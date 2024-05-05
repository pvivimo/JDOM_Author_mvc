package pv.sc_sigrecords_mvc.service;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pv.sc_sigrecords_mvc.database.Database;
import pv.sc_sigrecords_mvc.dto.AuthorDto;
import pv.sc_sigrecords_mvc.dto.AuthorsListDto;
import pv.sc_sigrecords_mvc.model.Author;
import pv.sc_sigrecords_mvc.parser.XMLParser;

@Service
public class AppService {

	private XMLParser parser;
	private Database db;
	
	
	@Autowired
	public AppService(XMLParser parser, Database db) {
		super();
		this.parser = parser;
		this.db = db;
	}



	public AuthorsListDto getAuthorsAndOccurances(String orderBy) throws JDOMException, IOException {

		AuthorsListDto authorsListDto = new AuthorsListDto();
		
		
		List<Author> authorList = parser.getAllAuthors();
		for(int authorListIndex = 0; authorListIndex < authorList.size(); authorListIndex++) {
			
			Author currentAuthor = authorList.get(authorListIndex);
			
			
			AuthorDto searchedAuthorDto = authorsListDto.getAuthorDtoByName( currentAuthor.getName() );
			if( searchedAuthorDto != null ) {
				
				searchedAuthorDto.incremenetOccurance();
			}
			else {
				
				AuthorDto authorDto = new AuthorDto( currentAuthor.getName() );
				authorsListDto.addAuthor(authorDto);
			}
		}
		
		
		if( (orderBy == null) || (orderBy.equals("abc")) ) {
			authorsListDto.sortByName();
		}
		else if( orderBy.equals("321") ) {
			authorsListDto.sortByOccurances();
		}
		
		return authorsListDto;
	}

	
	public AuthorsListDto getSearchedText(String searchedText) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = new AuthorsListDto();
		
		List<Author> authorList = parser.getAllAuthors();
		
			for(int authorListIndex = 0; authorListIndex < authorList.size(); authorListIndex++) {
				
				Author currentAuthor = authorList.get(authorListIndex);
				String lowerCaseCurrentAuthor = currentAuthor.getName().toLowerCase();
				currentAuthor.setName(lowerCaseCurrentAuthor);
				boolean isContainsText = currentAuthor.getName().contains(searchedText);
				
				if(isContainsText) {
					
					String upperCaseCurrentAuthor = currentAuthor.getName().substring(0, 1).toUpperCase() 
														+ currentAuthor.getName().substring(1);
					currentAuthor.setName(upperCaseCurrentAuthor);
					AuthorDto authorDto = new AuthorDto( currentAuthor.getName() );
					authorsListDto.addAuthor(authorDto);
				}
				
			}
		return authorsListDto;
	}


	public AuthorsListDto saveFile(String export) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = new AuthorsListDto();
		
		
		//Releváns szerzők kiolvasása az eredeti XML fájlból
		List<Author> authorList = parser.getAllAuthors();
		for(int authorListIndex = 0; authorListIndex < authorList.size(); authorListIndex++) {
			
			Author currentAuthor = authorList.get(authorListIndex);
			
			
			AuthorDto searchedAuthorDto = authorsListDto.getAuthorDtoByName( currentAuthor.getName() );
			if( searchedAuthorDto != null ) {
				
				searchedAuthorDto.incremenetOccurance();
			}
			else {
				
				AuthorDto authorDto = new AuthorDto( currentAuthor.getName() );
				authorsListDto.addAuthor(authorDto);
			}
		}
		
		//Adatok kimentése a kívánt célállományba
		if( ( export.equals("xmlfile")) ) {
			
			boolean saved = parser.fileWriter(authorsListDto.getAuthorsList());
		}
		else if( export.equals("database") ) {
			boolean saved = db.persistFile(authorsListDto.getAuthorsList());
		}
		
		return authorsListDto;
	}




}


