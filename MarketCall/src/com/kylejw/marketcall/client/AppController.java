package com.kylejw.marketcall.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.kylejw.marketcall.client.presenter.GuestsPresenter;
import com.kylejw.marketcall.client.presenter.IPresenter;
import com.kylejw.marketcall.client.view.GuestsView.Presenter;
import com.kylejw.marketcall.client.view.GuestsViewImpl;
import com.kylejw.marketcall.shared.model.Guest;

public class AppController implements IPresenter, ValueChangeHandler<String> {
  private final HandlerManager eventBus;
  private final GuestServiceAsync rpcService; 
  private HasWidgets container;
  private GuestsViewImpl<Guest> guestsView = null;
  
  public AppController(GuestServiceAsync rpcService, 
  		HandlerManager eventBus) {
    this.eventBus = eventBus;
    this.rpcService = rpcService;
    bind();
  }
  
  private void bind() {
    History.addValueChangeHandler(this);
//
//    eventBus.addHandler(AddContactEvent.TYPE,
//        new AddContactEventHandler() {
//          public void onAddContact(AddContactEvent event) {
//            doAddNewContact();
//          }
//        });  
//
//    eventBus.addHandler(EditContactEvent.TYPE,
//        new EditContactEventHandler() {
//          public void onEditContact(EditContactEvent event) {
//            doEditContact(event.getId());
//          }
//        });  
//
//    eventBus.addHandler(EditContactCancelledEvent.TYPE,
//        new EditContactCancelledEventHandler() {
//          public void onEditContactCancelled(EditContactCancelledEvent event) {
//            doEditContactCancelled();
//          }
//        });  
//
//    eventBus.addHandler(ContactUpdatedEvent.TYPE,
//        new ContactUpdatedEventHandler() {
//          public void onContactUpdated(ContactUpdatedEvent event) {
//            doContactUpdated();
//          }
//        });  
  }
  
  private void doAddNewContact() {
    History.newItem("add");
  }
  
  private void doEditContact(String id) {
//    History.newItem("edit", false);
//    Presenter presenter = new EditContactPresenter(rpcService, eventBus, 
//    		new EditContactView(), id);
//    presenter.go(container);
  }
  
  private void doEditContactCancelled() {
    History.newItem("list");
  }
  
  private void doContactUpdated() {
    History.newItem("list");
  }
  
  public void go(final HasWidgets container) {
    this.container = container;
    
    if ("".equals(History.getToken())) {
      History.newItem("list");
    }
    else {
      History.fireCurrentHistoryState();
    }
  }

  public void onValueChange(ValueChangeEvent<String> event) {
    String token = event.getValue();
    
    if (token != null) {
      if (token.equals("list")) {
        GWT.runAsync(new RunAsyncCallback() {
          public void onFailure(Throwable caught) {
          }
      
          public void onSuccess() {
            // lazily initialize our views, and keep them around to be reused
            //
            if (guestsView == null) {
              guestsView = new GuestsViewImpl<Guest>();
              
            }
            new GuestsPresenter(rpcService, eventBus, guestsView)
            .go(container);
          }
        });
      }
      else if (token.equals("add") || token.equals("edit")) {
        GWT.runAsync(new RunAsyncCallback() {
          public void onFailure(Throwable caught) {
          }
      
          public void onSuccess() {
            // lazily initialize our views, and keep them around to be reused
            //
//            if (editContactView == null) {
//              editContactView = new EditContactView();
//              
//            }
//            new EditContactPresenter(rpcService, eventBus, editContactView).
//            go(container);
          }
        });
      }
    }
  }

}
