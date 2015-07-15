package com.kylejw.marketcall.server.guests;

public class MarketCallTonightGuestMiner extends BnnGuestMiner {

	@Override
	protected String getFormat() {
		return "http://www.bnn.ca/WebServices/Cache/AjaxServices.svc/GetNewMarketCallUpcomingGuests?showID=315&numDays=7";
	}

}
