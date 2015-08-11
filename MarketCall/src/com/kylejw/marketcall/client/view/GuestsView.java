package com.kylejw.marketcall.client.view;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;
import com.kylejw.marketcall.shared.model.Guest;

public interface GuestsView<T> {

	  public interface Presenter<T> {
	    void onAddButtonClicked();
	    void onDeleteButtonClicked();
	    void onItemClicked(T clickedItem);
	    void onItemSelected(T selectedItem);
	  }
	  
	  void setPresenter(Presenter<T> presenter);
//	  void setColumnDefinitions(List<ColumnDefinition<T>> columnDefinitions);
	  void setGuestsList(List<Guest> guestList);
	  Widget asWidget();
	}