package com.kylejw.marketcalldaemon;

import java.util.List;

import com.kylejw.marketcall.server.guests.IGuestMiner;
import com.kylejw.marketcall.server.guests.MarketCallGuestMiner;
import com.kylejw.marketcall.server.guests.MarketCallTonightGuestMiner;
import com.kylejw.marketcall.shared.model.Guest;

public class MarketCallDaemon {

	public static void main(String[] args) {
		
		List<Guest> guests = new MarketCallGuestMiner().getGuests();
		guests.addAll(new MarketCallTonightGuestMiner().getGuests());

		StringBuilder sb = new StringBuilder();
		for (Guest g : guests) {
			sb.append(g.getName() + ", ");
		}
	}
}
