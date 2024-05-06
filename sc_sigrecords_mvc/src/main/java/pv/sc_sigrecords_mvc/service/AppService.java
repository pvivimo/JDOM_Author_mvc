package pv.sc_sigrecords_mvc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pv.sc_sigrecords_mvc.database.Database;
import pv.sc_sigrecords_mvc.dto.AuthorDto;
import pv.sc_sigrecords_mvc.dto.AuthorsListDto;
import pv.sc_sigrecords_mvc.model.Author;
import pv.sc_sigrecords_mvc.model.SavedAuthor;
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



	public AuthorsListDto getAuthorsAndOccurances(String orderBy, String searchedAuthorName) throws JDOMException, IOException {

		AuthorsListDto authorsListDto = new AuthorsListDto();
		
		
		List<Author> authorList = parser.getAllAuthors();
		for(int authorListIndex = 0; authorListIndex < authorList.size(); authorListIndex++) {
			
			Author currentAuthor = authorList.get(authorListIndex);
			if(searchedAuthorName == null || currentAuthor.getName().contains(searchedAuthorName)) {
				
				AuthorDto searchedAuthorDto = authorsListDto.getAuthorDtoByName( currentAuthor.getName() );
				if( searchedAuthorDto != null ) {
					
					searchedAuthorDto.incremenetOccurance();
				}
				else {
					
					AuthorDto authorDto = new AuthorDto( currentAuthor.getName() );
					authorsListDto.addAuthor(authorDto);
				}
				
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


	public AuthorsListDto saveFile(String export) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = getAuthorsAndOccurances(null, null);
		
		List<SavedAuthor> savedAuthorList = new ArrayList<>();
		List<AuthorDto> authorDtoList = authorsListDto.getAuthorsList();
		for(int index = 0; index < authorDtoList.size(); index++) {
			
			AuthorDto currentAuthorDto = authorDtoList.get(index);
			SavedAuthor savedAuthor = new SavedAuthor();
			savedAuthor.setName(currentAuthorDto.getName());
			savedAuthor.setOccurance(currentAuthorDto.getOccurance());
			savedAuthorList.add(savedAuthor);
		}
		
		//Adatok kimentése a kívánt célállományba
		if( ( export.equals("xmlfile")) ) {
			
			boolean saved = parser.fileWriter(savedAuthorList);
		}
		else if( export.equals("database") ) {
			
			
			boolean saved = db.persistFile(savedAuthorList);
		}
		
		return authorsListDto;
	}




}


