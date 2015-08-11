package com.kylejw.marketcall.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.kylejw.marketcall.shared.model.Guest;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GuestService extends RemoteService {
	ArrayList<Guest> getGuestList();
}
