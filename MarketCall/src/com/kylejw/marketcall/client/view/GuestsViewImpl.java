package com.kylejw.marketcall.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.dom.client.TableSectionElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kylejw.marketcall.shared.model.Guest;

public class GuestsViewImpl<T> extends Composite implements GuestsView<T> {
	
	private static GuestsViewImplUiBinder uiBinder = GWT
			.create(GuestsViewImplUiBinder.class);
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	interface GuestsViewImplUiBinder extends UiBinder<Widget, GuestsViewImpl> {
	}
	
	@UiField VerticalPanel guestsPanel;

	public GuestsViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	  private Presenter<T> presenter;
	  private List<Guest> guestList;
	  
	  public void setPresenter(Presenter<T> presenter) {
	    this.presenter = presenter;
	  }
	  
	  public void setGuestsList(List<Guest> guestList) {
		  
	    this.guestList = guestList;
	    logger.log(Level.INFO, "Rows: " + guestList.size());

	    for (int i = 0; i < guestList.size(); ++i) {
	    	try {
	    
	    		Guest g = guestList.get(i);
	    		GuestView gv = new GuestView(g);
	    		
	    		guestsPanel.add(gv.asWidget());
	        
	        
	    		logger.log(Level.INFO, "Data: " + i + g.getName());

	    	} catch (Exception ex) {
	    		logger.log(Level.SEVERE, ex.toString());
	    	}
	    }
	    
	  }
	  
	  private TableCellElement findNearestParentCell(Node node) {
	    while ((node != null)) {
	      if (Element.is(node)) {
	        Element elem = Element.as(node);

	        String tagName = elem.getTagName();
	        if ("td".equalsIgnoreCase(tagName) || "th".equalsIgnoreCase(tagName)) {
	          return elem.cast();
	        }
	      }
	      node = node.getParentNode();
	    }
	    return null;
	  }


//	  @UiHandler("guestsTable")
//	  void onTableClicked(ClickEvent event) {
//	    if (presenter != null) {
//	      EventTarget target = event.getNativeEvent().getEventTarget();
//	      Node node = Node.as(target);
//	      TableCellElement cell = findNearestParentCell(node);
//	      if (cell == null) {
//	        return;
//	      }
//
//	      TableRowElement tr = TableRowElement.as(cell.getParentElement());
//	      int row = tr.getSectionRowIndex();
//	      
//	      if (cell != null) {
//	        if (shouldFireClickEvent(cell)) {
//	          presenter.onItemClicked(rowData.get(row));
//	        }
//	        if (shouldFireSelectEvent(cell)) {
//	          presenter.onItemSelected(rowData.get(row));
//	        }
//	      }
//	    }
//	  }

	  private boolean shouldFireClickEvent(TableCellElement cell) {
	    boolean shouldFireClickEvent = false;
	    
//	    if (cell != null) {
//	      ColumnDefinition<T> columnDefinition = 
//	        columnDefinitions.get(cell.getCellIndex());
//	      
//	      if (columnDefinition != null) {
//	        shouldFireClickEvent = columnDefinition.isClickable();
//	      }
//	    }

	    return shouldFireClickEvent;
	  }
	  
	  private boolean shouldFireSelectEvent(TableCellElement cell) {
	    boolean shouldFireSelectEvent = false;
	    
//	    if (cell != null) {
//	      ColumnDefinition<T> columnDefinition = 
//	        columnDefinitions.get(cell.getCellIndex());
//	      
//	      if (columnDefinition != null) {
//	        shouldFireSelectEvent = columnDefinition.isSelectable();
//	      }
//	    }

	    return shouldFireSelectEvent;
	  }
	  
	  public Widget asWidget() {
	    return this;
	  }
}
