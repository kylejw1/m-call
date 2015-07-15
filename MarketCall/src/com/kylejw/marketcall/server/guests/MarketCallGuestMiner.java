package com.kylejw.marketcall.server.guests;

public class MarketCallGuestMiner extends BnnGuestMiner {

	@Override
	protected String getFormat() {
		return "http://www.bnn.ca/WebServices/Cache/AjaxServices.svc/GetNewMarketCallUpcomingGuests?showID=280&numDays=8";
	}

}
