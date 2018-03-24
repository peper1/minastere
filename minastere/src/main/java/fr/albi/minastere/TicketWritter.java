package fr.albi.minastere;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TicketWritter {
	File file;
	public TicketWritter(File file) {
		this.file=file;
	}

	public static String isType(File file) {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("UTF-16")));
			String a = bf.readLine();
			while (a!=null ) {
				a=a.trim().toLowerCase();
				if (a.contains("»")) {
					bf.close();
					return "leclerc";
				}
				else if(a.contains("total achats")||a.contains("total a regler ")) {
					bf.close();
					return "casino";}
				a=bf.readLine();

			}
			bf.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}
	/**
	 * @param entries the entries to set
	 * @throws IOException 
	 */
	public  Ticket createEntries(File file) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("UTF-16")));
		Ticket t = new Ticket(TicketWritter.isType(file));
		if (t.getType().equals("leclerc")){
			this.creerEntréLeclerc(t, bf);
		}
		else if (t.getType().equals("casino")){

			this.creerEntréCasino(t, bf);
		}
		else {System.out.println("type inconnus,séparateur ' ' utilisé par default ");}
		try {




			bf.close();


		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;




	}
	public void creerEntréCasino(Ticket t, BufferedReader bf) throws IOException{
		String a= bf.readLine();
		String separator=" ";
		while (a!=null) {
			a=a.trim();
			if(a.startsWith("TOTAL A REGLER"))
				break;
			else if (a.startsWith("Total remises")) {
				double reduc= Double.parseDouble(a.split("-")[1].replaceAll("E","").replace(",", "."));
				t.setReduc(reduc);
				break;
			}
			else if(a.endsWith("E")&&a.trim().split(" ")[a.trim().split(separator).length-1].replace("\t", "").replace(",", ".").split("\\.")[0].matches("[0-9]|[0-9][0-9]|[0-9][0-9][0-9]")) {
				if (a.toLowerCase().startsWith("total achats")||a.toLowerCase().contains("total a regler")) {
					t.setTotal(new Entry(a,separator));

				}
				else{
					t.addEntries(new Entry(a,separator));

				}
			}

			else if(a.toLowerCase().startsWith("date")) {
				String[] dateList= a.trim().split(":")[1].split("/");
				t.setDate(dateList[0]+"/"+dateList[1]+"/"+dateList[2]);
			}

			a=bf.readLine();

		}

	}
	public void creerEntréLeclerc(Ticket t, BufferedReader bf) throws IOException{
		String a= bf.readLine();
		String a1="a1";
		String separator=" ";
		int checker =0;
		while (a!=null) {

			a=a.trim();
			if (a.startsWith("CB")&&checker==1){
				break;
			}
			else if(a.toLowerCase().startsWith("date")) {
				String[] dateList= a.trim().split(":")[1].split("/");
				t.setDate(dateList[0]+"/"+dateList[1]+"/"+dateList[2]);

			}
			else if (test(a)){
				String b =a1+" "+a;
				try {
					t.addEntries(new Entry(b,separator));
				} catch (Exception e) {
					System.out.println();
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			else if( a.toLowerCase().startsWith("bon immediat")) {
				checker=1;
				a=a.split(" ")[a.split(" ").length-1].replace("\t"," ").replace(",", ".");
				double reduc= Double.parseDouble(a.split(" ")[a.split(" ").length-1]);
				t.setReduc(reduc);
				break;
			}
			else if(a.trim().replace("\t", " ").split(" ")[a.trim().replace("\t", " ").split(separator).length-1].replace(",", ".").split("\\.")[0].matches("[0-9]|[0-9][0-9]|[0-9][0-9][0-9]")) {
				if (a.startsWith("Total ")) {
					t.setTotal(new Entry(a,separator));

					checker=1;
				}
				else if(a.trim().replace("\t", " ").split(" ")[a.trim().replace("\t", " ").split(separator).length-1].replace(",", ".").split("\\.")[0].matches("[0-9]|[0-9][0-9]")){
					t.addEntries(new Entry(a,separator));

				}

			}
			a1=a;
			a=bf.readLine();





		}


	}
	public boolean test(String s) {
		if(s.length()>2)
			if(s.subSequence(0, 3).toString().matches("-?\\d+(\\.\\d+)?"+" X"))
				return true;
		return false;
	}
}
