package com.kylejw.marketcall.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.kylejw.marketcall.client.GuestService;
import com.kylejw.marketcall.server.guests.BnnGuestMiner;
import com.kylejw.marketcall.server.guests.IGuestMiner;
import com.kylejw.marketcall.server.guests.MarketCallGuestMiner;
import com.kylejw.marketcall.server.guests.MarketCallTonightGuestMiner;
import com.kylejw.marketcall.server.opinions.IOpinionMiner;
import com.kylejw.marketcall.server.opinions.OpinionStore;
import com.kylejw.marketcall.server.opinions.StockChaseOpinionMiner;
import com.kylejw.marketcall.shared.model.Guest;
import com.kylejw.marketcall.shared.model.Opinion;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GuestServiceImpl extends RemoteServiceServlet implements
		GuestService {

	private static final List<IGuestMiner> guestMiners = new ArrayList<IGuestMiner>();
	public GuestServiceImpl() {
		guestMiners.add(new MarketCallGuestMiner());
		guestMiners.add(new MarketCallTonightGuestMiner());
	}
	
	public ArrayList<Guest> getGuestList() throws IllegalArgumentException {
		
		ArrayList<Guest> guests = new ArrayList<Guest>();
		
		for (IGuestMiner miner : guestMiners) {
			guests.addAll(miner.getGuests());
		}
		
		return guests;
//		StringBuilder sb = new StringBuilder();
		
		
//		List<Guest> guests = new MarketCallGuestMiner().getGuests();
//		guests.addAll(new MarketCallTonightGuestMiner().getGuests());
//
//
//		for (Guest g : guests) {
//			sb.append(g.getName() + ", ");
//		}
//
//		IOpinionMiner om = new StockChaseOpinionMiner();
//		List<Opinion> opinions = om.getOpinions(new Date(), 1);
//		
//		OpinionStore os = new OpinionStore();
//		os.store(opinions);

//		Collection<Opinion> opinions = os.find("jim huang");
		
//		return sb.toString();
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
