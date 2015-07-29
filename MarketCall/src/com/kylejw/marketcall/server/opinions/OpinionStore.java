package com.kylejw.marketcall.server.opinions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.kylejw.marketcall.shared.model.Opinion;

public class OpinionStore {
	private static final Logger LOG = Logger.getLogger(Opinion.class.getName());
	 
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
 
	public Collection<Opinion> store(Collection<Opinion> opinions) {
		PersistenceManager pm = PMF.getPersistenceManager();
		
		try {
			return pm.makePersistentAll(opinions);
		} finally {
			pm.close();
		}
	}
	
	public Opinion store(Opinion o) {
		
		Collection<Opinion> ops = store(Arrays.asList(o));
		
		return ops.iterator().next();
	}
	
	public Collection<Opinion> find(String expert) {

	    PersistenceManager manager = PMF.getPersistenceManager();
	    System.out.println("The list of available products:");

	    try
	    {


	        // 2. start tx and form query
	        manager.currentTransaction().begin();
	        Query query = manager.newQuery(Opinion.class);

	        // 3. perform query
	        Collection allProducts = (Collection)query.execute();

	        // 4. now iterate over the result to print each
	        // product and finish tx
	        java.util.Iterator iter = allProducts.iterator();
	        if (! iter.hasNext())
	        {
	            System.out.println("No Product entries found!");
	        }
	        while (iter.hasNext())
	        {
	            System.out.println(iter.next());
	        }
	        manager.currentTransaction().commit();
	    }
	    catch (Throwable t)
	    {
	        t.printStackTrace();
	    }
	    finally
	    {
	        manager.close();
	    }
		return null;
//		PersistenceManager pm = PMF.getPersistenceManager();
//		Query q = pm.newQuery(Opinion.class);
//		q.setFilter("expert != expertParam");
//		q.setOrdering("date desc");
//		q.declareParameters("String expertParam");

//		try {
//		  List<Opinion> results = (List<Opinion>) q.execute();
//		  if (!results.isEmpty()) {
//		    for (Opinion p : results) {
//		    	System.out.println(p.getExpert());
//		      // Process result p
//		    }
//		  } else {
//		    // Handle "no results" case
//		  }
//		} finally {
////		  q.closeAll();
//		}
		

// 		try {
//			StringBuilder sb = new StringBuilder();
//			sb.append("select * from " + Opinion.class.getName());
////			sb.append(" where expert == '" + expert);
////			sb.append("' order by date desc");
//			List<Opinion> opinions = (List<Opinion>) pm.newQuery(sb.toString()).execute();
// 
//			return opinions;
//		} finally {
//			pm.close();
//		}
	}
}
