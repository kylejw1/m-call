package com.kylejw.marketcall.client.presenter;


import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.view.client.SelectionModel;
import com.kylejw.marketcall.client.GuestServiceAsync;
import com.kylejw.marketcall.client.view.GuestsView;
import com.kylejw.marketcall.shared.model.Guest;

import java.util.ArrayList;
import java.util.List;

public class GuestsPresenter implements IPresenter, 
  GuestsView.Presenter<Guest> {  

  private List<Guest> guests;
  private final GuestServiceAsync rpcService;
  private final HandlerManager eventBus;
  private final GuestsView<Guest> view;
//  private final SelectionModel<Guest> selectionModel;
  
  public GuestsPresenter(GuestServiceAsync rpcService, 
      HandlerManager eventBus, GuestsView<Guest> view) {
    this.rpcService = rpcService;
    this.eventBus = eventBus;
    this.view = view;
//    this.selectionModel = new SelectionModel<Guest>();
    this.view.setPresenter(this);
  }
  
  public void onAddButtonClicked() {
//    eventBus.fireEvent(new AddContactEvent());
  }
  
  public void onDeleteButtonClicked() {
    deleteSelectedContacts();
  }
  
  public void onItemClicked(Guest guest) {
//    eventBus.fireEvent(new EditContactEvent(contactDetails.getId()));
  }

  public void onItemSelected(Guest guest) {
//    if (selectionModel.isSelected(contactDetails)) {
//      selectionModel.removeSelection(contactDetails);
//    }
//    else {
//      selectionModel.addSelection(contactDetails);
//    }
  }
  
  public void go(final HasWidgets container) {
    container.clear();
    container.add(view.asWidget());
    fetchContactDetails();
  }

  public void setContactDetails(List<Guest> guests) {
    this.guests = guests;
  }
  
  public List<Guest> getContactDetails() {
    return guests;
  }
  
  public Guest getGuest(int index) {
    return guests.get(index);
  }
  
  private void fetchContactDetails() {
	  
	 
    rpcService.getGuestList(new AsyncCallback<ArrayList<Guest>>() {
      public void onSuccess(ArrayList<Guest> result) {
          guests = result;
          view.setGuestsList(guests);
      }
      
      public void onFailure(Throwable caught) {
        Window.alert("Error fetching contact details" + caught.toString());
      }
    });
  }

  private void deleteSelectedContacts() {
//    List<Guest> selectedContacts = selectionModel.getSelectedItems();
//    ArrayList<String> ids = new ArrayList<String>();
//    
//    for (int i = 0; i < selectedContacts.size(); ++i) {
//      ids.add(selectedContacts.get(i).getId());
//    }
//    
//    rpcService.deleteContacts(ids, new AsyncCallback<ArrayList<ContactDetails>>() {
//      public void onSuccess(ArrayList<ContactDetails> result) {
//        contactDetails = result;
//        sortContactDetails();
//        view.setRowData(contactDetails);
//      }
//      
//      public void onFailure(Throwable caught) {
//        System.out.println("Error deleting selected contacts");
//      }
//    });
  }
}
