package pv.sc_sigrecords_mvc.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.stereotype.Repository;


import pv.sc_sigrecords_mvc.model.Author;

@Repository
public class XMLParser {

	public List<Author> getAllAuthors(String position) throws JDOMException, IOException {
		
		List<Author> authors = new ArrayList<>();
		
		//1. Dokumentum előállítása fájlból:
		SAXBuilder sb = new SAXBuilder();
		Document document = sb.build(new File("C:\\Users\\pvivi\\OneDrive\\Asztali gép\\Programozz te is\\Halado_Tanfolyam\\4_XML\\2\\sigmodRecords.xml"));
		
		 // 2. Gyökérelem megszerzése
		Element rootElement = document.getRootElement();
		
		//3. Gyökérelemből elkérjük a Namespace-t, ebből megkapjuk a Namespace Osztály egy példányát
		Namespace ns = Namespace.getNamespace(rootElement.getNamespaceURI()); 
		
		 // 4. Az elemeken való iteráció és adott attribútumának / elemének kiolvasása:
		readByXPath(document);
		
//		List<Element> authorElements = authorsElement.getChildren("author", ns);
//		
//	    for (int index = 0; index < authorElements.size(); index++) {
//	    	
//	    	Element currentauthorElement = authorElements.get(index);
//	    	
//	
//	        String position = currentAuthorElement.getAttributeValue("position");
//	        String name = currentAuthorElement.getValue();
//	        
//	        if(position.equals("01")) {
//	        	
//	        	Author author = new Author(position, name);
//	        	author.incrementCounter();
//			    authors.add(author);
//			        
//            }
//	       
//	    }
//		
		
		return authors;
	}

	public void readByXPath(Document document) {
		
		XPathFactory xpfac = XPathFactory.instance();
		XPathExpression<Attribute> xPathA = xpfac.compile("//SigmodRecord/issue/volume/number/articles/article/authors/author/@position", Filters.attribute());
		
		for(Attribute att : xPathA.evaluate(document)) {
			System.out.println("position:" + att.getValue());
		}
				
	}

}
