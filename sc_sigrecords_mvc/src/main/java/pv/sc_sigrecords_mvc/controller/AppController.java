package pv.sc_sigrecords_mvc.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pv.sc_sigrecords_mvc.dto.AuthorDto;
import pv.sc_sigrecords_mvc.dto.AuthorsDto;
import pv.sc_sigrecords_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("/author/{position}")
	public String getAllAuthorByPosition(
				Model model,
				@PathVariable("position") String position
				) throws JDOMException, IOException {
		
		AuthorsDto authorsDto = service.getAllAuthorByPositon(position);
		model.addAttribute("authorsdto", authorsDto);
		
		return "author.html";
	}
	

}
