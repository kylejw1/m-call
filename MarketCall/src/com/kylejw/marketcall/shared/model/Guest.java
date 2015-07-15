package com.kylejw.marketcall.shared.model;

public class Guest {
    
	private final String name;
    private final String company = "";
    private final int id;
    
    public String getName() { return name; }
    public String getCompany() { return company; }

    public Guest(int id, String name) {
    	this.id = id;
    	this.name = name.trim();
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
