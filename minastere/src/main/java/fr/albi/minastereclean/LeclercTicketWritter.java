package fr.albi.minastereclean;

import java.io.File;

import fr.albi.minastereclean.TicketWritter;

public class LeclercTicketWritter extends fr.albi.minastereclean.TicketWritter{

	public LeclercTicketWritter(File file) {
		super(file);
		 dateToken="date";
		 totalReduc="bon immediat";
		 totalSansReduc="total ";
		 separator=" ";
		 end="cb";
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	protected boolean isRealEntry(String s) {
		if(s.length()>2)
			if(s.subSequence(0, 3).toString().matches("-?\\d+(\\.\\d+)?"+" x"))
				return true;
		return false;		
	}


	@Override
	protected double getPriceFromEntry(String[] s) {
		String price=null;
		try {
			String[] table=s[s.length-1].replace(",", ".").split("e");
			 price =table[table.length-1].trim();
		 Double.parseDouble(price);			
	}catch(Exception es) {
		System.out.println();
	}
		finally {
			return Double.parseDouble(price);
		}
}}
