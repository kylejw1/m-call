package com.kylejw.marketcall.server;

import java.util.List;

import com.kylejw.marketcall.client.GreetingService;
import com.kylejw.marketcall.server.guests.BnnGuestMiner;
import com.kylejw.marketcall.server.guests.IGuestMiner;
import com.kylejw.marketcall.server.guests.MarketCallGuestMiner;
import com.kylejw.marketcall.server.guests.MarketCallTonightGuestMiner;
import com.kylejw.marketcall.shared.model.Guest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		
		List<Guest> guests = new MarketCallGuestMiner().getGuests();
		guests.addAll(new MarketCallTonightGuestMiner().getGuests());

		StringBuilder sb = new StringBuilder();
		for (Guest g : guests) {
			sb.append(g.getName() + ", ");
		}

		return sb.toString();
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
