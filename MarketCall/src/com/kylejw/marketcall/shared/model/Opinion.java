package com.kylejw.marketcall.shared.model;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable
public class Opinion implements IsSerializable {
	
    public Date getDate() { return date; }
	public String getSignal() { return signal; }
	public String getCompany() { return company; }
 	public String getExpert() { return expert; }
	public String getOpinionString() { return opinionString; }
	public double getPrice() { return price; }
	public String getSymbol() { return symbol; }

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long datastoreId;
	
	@Persistent
	private final Date date;
	
	@Persistent
	private final String signal;
	
	@Persistent
	private final String company;
	
	@Persistent
	private final String expert;
	
	@Persistent
    private final String opinionString;
	
	@Persistent
    private final double price;
	
	@Persistent
    private final String symbol;
    
    public Opinion(Date date, String expert, String company, String signal, double price, String symbol, String opinionString) {
    	this.date = date;
    	this.signal = signal;
    	this.company = company;
    	this.expert = expert.replaceAll("[^a-zA-Z]", "").toLowerCase();;
    	this.opinionString = opinionString;
    	this.price = price;
    	this.symbol = symbol;
    }
    
    @Override
    public int hashCode() {
    	
        int hash = date.hashCode() ^ signal.hashCode();
        hash = hash ^ company.hashCode();
        hash = hash ^ expert.hashCode();
        hash = hash ^ opinionString.hashCode();
        hash = hash ^ Double.valueOf(price).hashCode();
        hash = hash ^ symbol.hashCode();

        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}
