/**
 * 
 */
package fr.albi.minastereclean;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;

import fr.albi.minastereclean.TicketWritter;

/**
 * @author plepetit
 *
 */
public class NeutralTicketWriter extends TicketWritter {

	/**
	 * @param file
	 */
	public NeutralTicketWriter() {
		super();
		 dateToken="date";
		 totalReduc="bon immediat";
		 totalSansReduc="total ";
		 separator=" ";
		 end="cb";
		 super.t=new Ticket("neutre");
		// TODO Auto-generated constructor stub
	}
	public void isManualentry(List<Ticket> lf,Scanner sc){
		
		System.out.println("y a-t-il des entrée manuel?(y/n");
		String h=sc.nextLine();
		if (h.toLowerCase().trim().equals("y")){
			Ticket t = new Ticket("manuel");
			double total=0;
			System.out.println("Saisir les entrées et ecrire \"fait\" quand vous avez terminé");

			String Hdefault="";
			do {
				System.out.println("entrez l'intitulé SANS caractères spéciaux ni ponctuation ou espace/n"
						+ "mettre un espace après l'intitulé et saisir le prix avec un point/n"
						+ "lorsque vous aurait saisie toute les entrées saisir \"fait\" ");
				Hdefault= sc.nextLine();
				if (super.isPatternEntry(Hdefault)) {
					super.setEntry(Hdefault);
					total=total+this.getPriceFromEntry(createTableofEntry(Hdefault));
					t.addEntry(getNameFromEntry(createTableofEntry(Hdefault)), getPriceFromEntry(createTableofEntry(Hdefault)));
				}
				else if(!Hdefault.equals("fait"))  {
					System.out.println("veuillez saisir 'fait'ou une entrée valide");
				}

			}while (!Hdefault.equals("fait"));
			do{System.out.println("saisir la réduction");
			Hdefault=sc.nextLine();
			if (!NumberUtils.isCreatable(Hdefault)) {
				System.out.println("saisir un nombre valide (avec un séparateur'.')");
			}
			}while(!NumberUtils.isCreatable(Hdefault));
			t.setReduc(Double.parseDouble(Hdefault));
			t.setTotal(new Entry("totalmanuel", total));
			lf.add(t);

		}
		else if(h.toLowerCase().trim().equals("n")) {

		}
		else {
			System.out.println("choisir \"y\" ou \"n\"");
			isManualentry(lf,sc);
		}
	}
	@Override
	protected boolean isRealEntry(String a) {
		// TODO Auto-generated method stub
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
	}
}


