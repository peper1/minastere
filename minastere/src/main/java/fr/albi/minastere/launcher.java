package fr.albi.minastere;

import java.io.IOException;



/**
 * @author plepetit
 *
 */
public class launcher {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		CSVWritter cs = new CSVWritter("/home/plepetit/Documents/tickets","/home/plepetit/Documents/tickets/testold.csv");
		cs.entriesWrite();
	}

}