package com.kylejw.marketcall.server.guests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kylejw.marketcall.shared.model.Guest;

public abstract class BnnGuestMiner implements IGuestMiner {

	protected abstract String getFormat();
	
	@Override
	public List<Guest> getGuests() {
		
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(getFormat());
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        String result =  buffer.toString();
	        
	        return parseGuests(result);
	    } 
	    catch (Exception ex) {
	    	
	    }
	    finally {
	        if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
	        }
	    }
		
		return null;
	}

    private List<Guest> parseGuests(String json)
    {
    	final String ID_STRING = "GuestID";
    	final String NAME_STRING = "GuestName";
    	
    	ArrayList<Guest> guests = new ArrayList<>();
    	
    	try {
    		
    		JSONArray jsa = new JSONArray(json);
    		
    		for (int i = 0; i < jsa.length(); i++) {
    			JSONObject o = jsa.getJSONObject(i);
    			
    			if (!o.has(ID_STRING) || !o.has(NAME_STRING))
    				continue;
    			
    			int id = Integer.parseInt(o.get(ID_STRING).toString().trim());
    			
    			Guest g = new Guest(id, o.get(NAME_STRING).toString().trim());
    			
    			guests.add(g);
    		}
    		
    	} catch (JSONException e) { }


        return guests;
    }
}
