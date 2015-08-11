package com.kylejw.marketcall.shared.model;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.rpc.IsSerializable;

@PersistenceCapable
public class Guest implements IsSerializable {
    
	
	@Persistent
	private String name;
	
	@Persistent
    private String company = "";
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Long key;
    
    @Persistent
    private int id;
    
    @Persistent
    private Date appearanceDate;
    
    public String getName() { return name; }
    public String getCompany() { return company; }
    public Date getAppearanceDate() { return appearanceDate; }

    public Guest() {
    	this.id = 0;
    	this.name = "";
//    	this.company = "";
    	this.appearanceDate = new Date();
    }
    
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
