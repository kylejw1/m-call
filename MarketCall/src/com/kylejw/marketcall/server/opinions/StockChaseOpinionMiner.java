package com.kylejw.marketcall.server.opinions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kylejw.marketcall.shared.model.Opinion;

public class StockChaseOpinionMiner implements IOpinionMiner {

    private final String format = "http://www.stockchase.com/opinions/recent/sort/date/page/%d/direction/desc/max/120";
    private int failures = 0;
	
    private String requestPage(int index) {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(String.format(format, index));
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        String result =  buffer.toString();
	        
	        return result;
	    } 
	    catch (Exception ex) {
	    	System.out.println(ex.toString());
	    	
	    	return "";
	    }
	    finally {
	        if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
	        }
	    }
    }
    
    public List<Opinion> parseOpinions(String html) {
    	
    	final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    	final Pattern PRICE_PATTERN = Pattern.compile("(\\$)([0-9,]+(\\.[0-9]+)?)");
    	final Pattern SYMBOL_MATCH = Pattern.compile("<br>([^<]*)</a>");
    	
    	List<Opinion> opinions = new ArrayList<Opinion>();
    	
    	Document doc = Jsoup.parse(html);
    	
    	Elements opinionRows = doc.select("div#opinions tr");
    	opinionRows.remove(0);
    	
    	for (Element e : opinionRows) {
    		try {
    			
    			String dateStr = e.select("[class~=.*date.*]").first().text().trim();
    			String signal = e.select("[class~=.*OpinionSignal.*]").first().text().trim();
    			String company = e.select("[class~=.*company.*]").first().text().trim();
    			String companyHtml = e.select("[class~=.*company.*]").first().html().trim();
    			String expert = e.select("[class~=.*expert.*]").first().text().trim();
    			String opinionString = e.select("[class~=.*opinion.*]").first().text().trim();
    			String priceStr = e.select("[class~=.*price.*]").first().text().trim();

    			Date date = DATE_FORMAT.parse(dateStr);  
    			
    			String symbol = "";
    			Matcher symMatch = SYMBOL_MATCH.matcher(companyHtml);
    			if (symMatch.find()) {
    				symbol = symMatch.group(1);
    			}
    			
    			double price = 0;
    			Matcher m = PRICE_PATTERN.matcher(priceStr);
    			if (m.find()) {
    				price = Double.valueOf(m.group(2));
    			}
    			
    			Opinion o = new Opinion(date, expert, company, signal, price, symbol, opinionString);
    			
    			opinions.add(o);
    			
    		} catch (Exception ex) {
    			System.out.println(ex.toString());
    		}
    	}
    	
    	return opinions;
    }
    
	@Override
	public List<Opinion> getOpinions(Date oldest, int max) {
		
        failures = 0;
        List allOpinions = new ArrayList<Opinion>();
        
        int emptyCount = 0;
        boolean finished = false;
        for (int index = 1; (index < max) && !finished; index++)
        {
            String page = requestPage(index);
            List<Opinion> opinions = parseOpinions(page);
            
            if (null == opinions || opinions.size() == 0) {
            	if (emptyCount++ < 10)
            		continue;
        		else 
        			break;
            }
            
            allOpinions.addAll(opinions);
            
            for (Opinion o : opinions) {
            	if (o.getDate().getTime() < oldest.getTime())
            	{
            		finished = true;
            		break;
            	}
            }
        }

        return allOpinions;
	}

}
