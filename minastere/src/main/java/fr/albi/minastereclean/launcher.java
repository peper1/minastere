package fr.albi.minastereclean;

import java.io.IOException;



/**
 * @author plepetit
 *
 */
public class launcher {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//premier paramètre chemin d'acces vers les fichier tickets de caisse au format txt, 2eme parametre fichier de sortis
		CSVWritter cs = new CSVWritter("/home/plepetit/Documents/tickets","/home/plepetit/Documents/tickets/testnew.csv");
		cs.entriesWrite();
	}

}