package fr.albi.minastereclean;

import java.io.File;

public class CasinoTicketWritter extends fr.albi.minastereclean.TicketWritter {

	public CasinoTicketWritter(File file) {
		super(file);
		 dateToken="date";
		 totalReduc="total remises";
		 totalSansReduc="total achats";
		 separator=" ";
		 end="total a regler";
	}

	@Override
	protected boolean isRealEntry(String a) {
		if (!a.equals("")){
		if(a.subSequence(0, 1).toString().matches("-?\\d+(\\.\\d+)?")) {
			return true;}}
		return false;
	}

	@Override
	protected double getPriceFromEntry(String[] table) {
		return Double.parseDouble(table[table.length-1].trim().replace("e", "").replace(",", "."));
		
	}

	

}
