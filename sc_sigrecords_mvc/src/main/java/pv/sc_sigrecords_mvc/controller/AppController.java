package pv.sc_sigrecords_mvc.controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pv.sc_sigrecords_mvc.dto.AuthorsListDto;
import pv.sc_sigrecords_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;
	}

	
	@GetMapping("/authors")
	public String showAuthors(Model model) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = service.getAuthorsAndOccurances(null, null);
		model.addAttribute("authorsListDto", authorsListDto);
		
		return "authors.html";
	}
	
	
	@GetMapping("/authors/order")
	public String showAuthorsWithSelectedOrder(
							Model model,
							@RequestParam("orderby") String order
						) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = service.getAuthorsAndOccurances(order, null);
		
		
		model.addAttribute("authorsListDto", authorsListDto);
		
		return "authors.html";
	}
	
	@GetMapping("/authors/search")
	public String searchText(
				Model model,
				@RequestParam("search") String searchedAuthorName
			) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = service.getAuthorsAndOccurances(null, searchedAuthorName);
		model.addAttribute("authorsListDto", authorsListDto);
		
		return "authors.html";
		
	}
	
	@PostMapping("/authors/export")
	public String exportFile(
				Model model,
				@RequestParam("export") String export
			) throws JDOMException, IOException {
		
		AuthorsListDto authorsListDto = service.saveFile(export);
		model.addAttribute("authorsListDto", authorsListDto);
		model.addAttribute("savedsuccessfull", "Saved successfull!"); //alapból nem volt sikeres a mentés
		
		return "export.html";
	}
	
	@GetMapping("/")
	public String loadImportPage() {
				
		return "index.html";
	}
	
	@GetMapping("/authors/import")
	public String importFile(
				Model model,
				@RequestParam("source") String source,
				@RequestParam("path") String path
			) throws JDOMException, IOException {
		
		String targetPage = "";
		AuthorsListDto authorsListDto = service.sourceFile(source, path);
		model.addAttribute("authorsListDto", authorsListDto);
		if(authorsListDto != null) {
			
			targetPage = "authors.html";
		}else {
			
			targetPage = "index.html";
		}
		
		return targetPage;
		
		
	}
	
	

}
