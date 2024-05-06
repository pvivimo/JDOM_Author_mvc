package pv.sc_sigrecords_mvc.parser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Repository;

import pv.sc_sigrecords_mvc.dto.AuthorDto;
import pv.sc_sigrecords_mvc.model.Author;
import pv.sc_sigrecords_mvc.model.SavedAuthor;


@Repository
public class XMLParser {

	public List<Author> getAllAuthors() throws JDOMException, IOException {
		
		List<Author> authorResultList = new ArrayList<>();

		SAXBuilder sb = new SAXBuilder();
		Document doc = sb.build( new File("C:\\Users\\pvivi\\OneDrive\\Asztali gép\\Programozz te is\\Halado_Tanfolyam\\4_XML\\2\\sigmodRecords.xml") );

		
		Element rootElement = doc.getRootElement();
		Namespace ns = Namespace.getNamespace( rootElement.getNamespaceURI() );
		
		
		/** ISSUE LIST */
		List<Element> issueList = rootElement.getChildren("issue", ns);
		for(int issueIndex = 0; issueIndex < issueList.size(); issueIndex++) {
			Element currentIssue = issueList.get(issueIndex);
			
			/** ARTICLES */
			Element articlesElement = currentIssue.getChild("articles", ns);
			
			/** ARTICLE LIST */
			List<Element> articleList = articlesElement.getChildren("article", ns);
			for(int articleIndex = 0; articleIndex < articleList.size(); articleIndex++) {
				Element currentArticle = articleList.get(articleIndex);
				
				/** AUTHORS */
				Element authorsElement = currentArticle.getChild("authors", ns);
				
				/** AUTHOR LIST */
				List<Element> authorList = authorsElement.getChildren("author", ns);
				if(authorList.size() >= 2) {
					
					for(int authorIndex = 0; authorIndex < authorList.size(); authorIndex++) {
						
						Element currentAuthor = authorList.get(authorIndex);
						String authorName = currentAuthor.getValue();
						String authorPosition = currentAuthor.getAttributeValue("position");

						Author author = new Author(authorName, authorPosition);
						authorResultList.add(author);
					}
				}
			}
		}
		
		
		return authorResultList;
	}

	public boolean fileWriter(List<SavedAuthor> savedAuthorsList) throws IOException {
		
			
		boolean savedSuccessfull = false;
		/** XML */
		FileWriter writer = new FileWriter("author.xml");
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		
		/** STRUCTURE */
		Document doc = new Document();
		
		/**
		 * <authors>
		 * 				<author occurance="1">Anthony I. Wasserman</author>
		 * 				<author occurance="2">Karen Botnich</author>
		 * </authors>
		 */
		
		Element rootElement = new Element("authors");
		
		
		for(int index = 0; index <savedAuthorsList.size(); index++) {
			SavedAuthor savedAuthor = savedAuthorsList.get(index);
			Element authorElement = new Element("author");
			authorElement.setText(savedAuthor.getName());
			authorElement.setAttribute("occurance", Integer.toString(savedAuthor.getOccurance()));
			rootElement.addContent(authorElement);
			savedSuccessfull = true;
			
		}
		
		doc.setRootElement(rootElement);
		
		/** SAVE */
		
		outputter.output(doc, writer);
		writer.close();
		
		return savedSuccessfull;
	}

}


