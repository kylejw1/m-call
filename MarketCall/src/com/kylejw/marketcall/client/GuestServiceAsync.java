package com.kylejw.marketcall.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.kylejw.marketcall.shared.model.Guest;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GuestServiceAsync {
	void getGuestList(AsyncCallback<ArrayList<Guest>> callback);
}
