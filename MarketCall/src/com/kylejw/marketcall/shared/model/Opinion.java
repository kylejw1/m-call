package com.kylejw.marketcall.shared.model;
import java.util.Date;

public class Opinion {
	
    public Date getDate() { return date; }
	public String getSignal() { return signal; }
	public String getCompany() { return company; }
 	public String getExpert() { return expert; }
	public String getOpinionString() { return opinionString; }
	public double getPrice() { return price; }
	public String getSymbol() { return symbol; }

	private final Date date;
	private final String signal;
	private final String company;
	private final String expert;
    private final String opinionString;
    private final double price;
    private final String symbol;
    
    public Opinion(Date date, String expert, String company, String signal, double price, String symbol, String opinionString) {
    	this.date = date;
    	this.signal = signal;
    	this.company = company;
    	this.expert = expert;
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
