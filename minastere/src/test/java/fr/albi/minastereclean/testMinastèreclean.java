package fr.albi.minastereclean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import fr.albi.minastereclean.Ticket;
import fr.albi.minastereclean.TicketWritter;

public class testMinastèreclean {


	@Test
	public void test() {
		List<Ticket> lf= new ArrayList<Ticket>();
		File f = new File("/home/plepetit/Documents/tickets/testfiles");
		if(f.isDirectory()) {
			for (File file : f.listFiles()) {
				if(file.getName().endsWith("txt"))
					try {
						TicketWritter tw=null;
						if (TicketFileReader.isType(file).equals(TicketFileReader.CASINO))
							tw= new CasinoTicketWritter(file);
						else
							tw = new LeclercTicketWritter(file);
						tw.creerEntrées();
						lf.add(tw.t);;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}
	}

}
