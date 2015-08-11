package com.kylejw.marketcall.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.kylejw.marketcall.shared.model.Guest;

public class GuestView extends Composite {

	private static GuestViewUiBinder uiBinder = GWT
			.create(GuestViewUiBinder.class);

	interface GuestViewUiBinder extends UiBinder<Widget, GuestView> {
	}
	
	@UiField HTML name;
	@UiField HTML company;
	@UiField HTML date;
	
	public void setGuest(Guest g) {
		this.name.setHTML(g.getName());
		this.company.setHTML(g.getCompany());
		this.date.setHTML(g.getAppearanceDate().toString());
	}

	public GuestView() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public GuestView(Guest g) {
		this();
		
		setGuest(g);
	}

}
