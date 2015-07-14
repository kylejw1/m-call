package com.kylejw.marketcall.shared.model;

public class Guest {
    
	private final String name;
    private final String company = "";
    
    public String getName() { return name; }
    public String getCompany() { return company; }

    public Guest(String name) {
    	this.name = name;
    }
    
    @Override
    public String toString()
    {
        return getName();
    }

    @Override
    public boolean equals(Object obj)
    {
        return this.hashCode() == obj.hashCode();
    }

    @Override
    public int hashCode()
    {
        return name.hashCode() ^ company.hashCode();
    }
}
