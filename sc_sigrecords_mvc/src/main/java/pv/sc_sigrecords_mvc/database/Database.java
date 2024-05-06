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
	
	
	public boolean persistFile(List<SavedAuthor> authorsList) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		boolean savedSuccessfull = false;
		
		for(int index = 0; index < authorsList.size(); index++) {
			
			SavedAuthor savedAuthor = authorsList.get(index);
			
			session.persist(savedAuthor);
			savedSuccessfull = true;
		}
		
		tx.commit();
		session.close();
		
		return savedSuccessfull;
		
	}
	

}
