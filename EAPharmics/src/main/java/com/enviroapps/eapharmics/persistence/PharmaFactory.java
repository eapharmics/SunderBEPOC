package com.enviroapps.eapharmics.persistence;



import java.util.List;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Transaction;
import com.enviroapps.eapharmics.bom.pharma.Emp;
import com.enviroapps.eapharmics.ui.pharma.PharmaVO;
import com.enviroapps.eapharmics.ui.pharma.DictionaryVO;
import com.enviroapps.eapharmics.common.services.UtilityServiceFactory;
import com.enviroapps.eapharmics.common.services.logging.ILogger;
import com.enviroapps.eapharmics.das.persistence.DataAccessConstants;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceFactory;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManager;
import com.enviroapps.eapharmics.das.persistence.hibernate.HibernatePersistenceManagerFactory;


/**
 * @author EnviroApps
 * 
 */
public class PharmaFactory extends HibernatePersistenceFactory implements
                                                              DataAccessConstants
{

   private static ILogger log = UtilityServiceFactory.getLogger();

   private PharmaFactory()
   {
      persistenceManager = (HibernatePersistenceManager) HibernatePersistenceManagerFactory.getInstance().getPersistenceManager();
      log.debug(this, "PharmaFactory", persistenceManager);
   }

   private static PharmaFactory instance = new PharmaFactory();

   public static PharmaFactory getInstance()
   {
      return instance;
   }

   /*
    * public List getAllPharma() { log.debug(this, "getAllPharma in Pharma
    * Factory", "********* getAllPharma *********"); List lista = new
    * ArrayList(); OrderingParameter op = new OrderingParameter("batch_no",
    * true); log.debug(this, "after Ordering parameter in Pharma Factory",
    * "********* getAllPharma *********"); lista.add(op); log.debug(this, "Going
    * to query PharmaVO[]", "********* getAllPharma *********"); PharmaVO[]
    * pharma = (PharmaVO[])super.query(PharmaVO.class,lista); Session session =
    * persistenceManager.getCurrentSession(); //log.debug(pharma, "after
    * querying in Pharma Factory", "********* getAllPharma *********"); List
    * list = Arrays.asList(pharma); //log.debug(this, "after adding list in
    * list1 in Pharma Factory", "********* getAllPharma *********");
    * //System.out.println("Values PharmaVO" + list); return list;
    *  }
    */

   public List getAllPharma()
   {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(PharmaVO.class);
      log.debug(this,
                "Criteria in Pharma Factory",
                "********* getAllPharma *********");
      List list = criteria.list();
      System.out.println("Values of criteria" + list);
      return list;

   }

   public List getDictionary(String dictCode)
   {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(DictionaryVO.class).setProjection(
                          Projections.projectionList()
                          .add(Projections.property("eadictionarydetailid"))
                          .add(Projections.property("dictionaryvaluedescription"))
                                   )
                      .add(Restrictions.eq("dictionarycode", dictCode))
                      .addOrder(Order.asc("displayorder")) ;
                       List list = criteria.list();
                       return list;
      }
   
   
  public List getProduct()
   {
      Session session = persistenceManager.getCurrentSession();
      Criteria criteria = session.createCriteria(DictionaryVO.class).setProjection(
                          Projections.projectionList()
                          .add(Projections.property("dictionaryvalue"))
                          //.add(Projections.property("dictionaryvaluedescription"))
                                   )
                      .add(Restrictions.eq("dictionarycode", "PRODUCT"))
                      .addOrder(Order.asc("displayorder")) ;
                       List list = criteria.list();
                       System.out.println("Values of Products" + list);
                       return list;
                      
      }
   
   
   
   
   
   /**
    * @param userName
    * @return
    */
   public PharmaVO getPharma(Long batch_no)
   {
      return (PharmaVO) super.load(PharmaVO.class, batch_no);
   }

   /**
    * @param pharma
    */
   public void savePharma(PharmaVO pharma)
   {
      super.create(pharma);
   }

   public void SaveEmp(Emp emp)
   {
      super.create(emp);
   }

   
   
   
   /**
    * @param pharma
    */
   public void updatePharma(PharmaVO pharma)
   {
      log.debug(this,
                "Working on Store Functionality for Update",
                "********* UpdatePharma *********");
      Configuration configuration = new Configuration();
      SessionFactory  sessionFactory = configuration.configure().buildSessionFactory();
      Session session = sessionFactory.openSession();
      Transaction transaction = session.beginTransaction();
      transaction.begin();
      session.update(pharma);
      transaction.commit();
      session.close();
      log.debug(this,
                "Done with  Store Functionality for Update",
                "********* UpdatePharma *********");
      //super.store(pharma);
      
   }

}
