package pv.sc_sigrecords_mvc.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pv.sc_sigrecords_mvc.dto.AuthorDto;
import pv.sc_sigrecords_mvc.model.SavedAuthor;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database() {
		
		Configuration config = new Configuration();
		config.configure();
		
		sessionFactory = config.buildSessionFactory();
	}
	
	
	public boolean persistFile(List<AuthorDto> authorsList) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		boolean savedSuccessfull = false;
		
		for(int index = 0; index < authorsList.size(); index++) {
			
			AuthorDto authorDto = authorsList.get(index);
			SavedAuthor savedAuthor = new SavedAuthor();
			
			
			savedAuthor.setName(authorDto.getName());
			savedAuthor.setOccurance(authorDto.getOccurance());
			
//			//csak akkor mentse, ha még nem szerepel az adatbázisban
//			List<SavedAuthor> sAuthors = null;
//			SelectionQuery<SavedAuthor> query =
//					session.createSelectionQuery("SELECT s FROM SavedAuthor s", SavedAuthor.class);
//			
//			sAuthors = query.getResultList();
//			
//			if(savedAuthor != sAuthors) {
//				
				session.persist(savedAuthor);
				savedSuccessfull = true;
//			}
		}
		
		tx.commit();
		session.close();
		
		return savedSuccessfull;
		
	}
	

}
