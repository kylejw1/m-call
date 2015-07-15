package com.kylejw.marketcall.shared.model;

import java.util.Date;

public class Guest {
    
	private final String name;
    private final String company = "";
    private final int id;
    private final Date appearanceDate;
    
    public String getName() { return name; }
    public String getCompany() { return company; }
    public Date getAppearanceDate() { return appearanceDate; }

    public Guest(Date appearanceDate, int id, String name) {
    	this.id = id;
    	this.name = name.trim();
    	this.appearanceDate = appearanceDate;
    }
    
    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object obj)
    {
    	if (obj.getClass() != Guest.class)
    		return false;
    	
    	return this.id == ((Guest)obj).id;
    	
//        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode()
    {
    	// TODO: Potential to mismatch here if ID comes from two sources.  Wtf to do?
    	
    	return id;
//        return name.hashCode() ^ company.hashCode();
    }
}
