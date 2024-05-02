package pv.sc_sigrecords_mvc.service;

import java.io.IOException;
import java.util.List;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pv.sc_sigrecords_mvc.dto.AuthorDto;
import pv.sc_sigrecords_mvc.dto.AuthorsDto;
import pv.sc_sigrecords_mvc.model.Author;
import pv.sc_sigrecords_mvc.parser.XMLParser;

@Service
public class AppService {
	
	private XMLParser parser;
	
	@Autowired
	public AppService(XMLParser parser) {
		super();
		this.parser = parser;
	}

	public AuthorsDto getAllAuthorByPositon(String position) throws JDOMException, IOException {
		
		AuthorsDto authorsDto = new AuthorsDto();
		
		List<Author> authors = parser.getAllAuthors(position);
				
		for(int index = 0; index < authors.size(); index++) {
			
			AuthorDto authorDto = null;
			Author currentAuthor = authors.get(index);
			
			authorDto = new AuthorDto(
					currentAuthor.getName(),
					currentAuthor.getCounter());
				
			authorsDto.addAuthor(authorDto);
			}
					
		authorsDto.sortAuthors();
		
		
		return authorsDto;
	}
	
	

}
