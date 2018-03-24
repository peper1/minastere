package fr.albi.minastereclean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.math.NumberUtils;


public abstract class  TicketWritter {
	protected BufferedReader bf; 
	protected Ticket t;
	protected String dateToken;
	protected String  totalReduc;
	protected String totalSansReduc;
	protected String separator;
	protected String end;
	protected int checker;
	public TicketWritter() {}

	public TicketWritter(File file) {
		checker =0;
		t = new Ticket(TicketFileReader.isType(file));
		try {
			bf = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("UTF-16")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}


	public void creerEntrées() throws IOException{
		String a=cleanString(bf.readLine());
		String a1="";
		while (a!=null) {


			if (isEnded(a)){
				break;
			}
			else if(isDate(a)) {
				setDate( a);
			}
			else if (isRealEntry(a)){
				String b =a1+" "+a;
				String[] table=createTableofEntry(b);
				try {
					t.addEntries(new Entry(getNameFromEntry(table),getPriceFromEntry(table)));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if( isReduc(a)) {
				setReduc(a);
			}
			else if(isPatternEntry(a)&&checker==0) {
				String[] table=createTableofEntry(a);
				if (a.startsWith(totalSansReduc)||a.startsWith(end)) {
					t.setTotal(new Entry(getNameFromEntry(table),getPriceFromEntry(table)));
					checker=1;
				}
				else 
					t.addEntries(new Entry(getNameFromEntry(table),getPriceFromEntry(table)));

			}
			a1=a;
			a=bf.readLine();
			if (a!=null)
				a= cleanString(a);

		}

		if (t.getTotal()==null) {
			System.out.println();
		}




	}

	private boolean isDate(String a) {
		if(a.toLowerCase().startsWith(dateToken))
			return true;
		// TODO Auto-generated method stub
		return false;
	}


	protected abstract boolean isRealEntry(String a);
	protected  boolean isEnded(String a) {
		if (a.startsWith(end)&&checker==1)
			return true;
		return false;
	};
	protected  boolean isReduc(String a) {
		if(a.toLowerCase().startsWith(totalReduc)) {;
		return true;}
		return false;}


	protected String cleanString(String a) {
		a.trim();
		return a.trim().replace("\t", separator).replace(":",separator).replace(",",".").toLowerCase();
	}

	protected void setDate(String a) {
		String[] dateList= a.trim().split(":")[1].split("/");
		t.setDate(dateList[0]+"/"+dateList[1]+"/"+dateList[2]);
	}
	protected void setReduc(String a) {
		checker=1;

		double reduc = getPriceFromEntry(createTableofEntry(a)); ;
		t.setReduc(reduc);

	}

	protected  boolean isPatternEntry(String s) {
		String[] table = createTableofEntry(s);
		if (table[table.length-1].split("\\.")[0].matches("[0-9]|[0-9][0-9]|[0-9][0-9][0-9]"))
			return true;
		else {
			return false;
		}
	}
	protected abstract double getPriceFromEntry(String[] s);
	protected String getNameFromEntry(String[] table) {
		String name="";

		for (int k=0;k<table.length-1;k++)
			name= name+table[k].replace(",", " ")+" ";
		return name;
	}
	protected  String[] createTableofEntry(String s){
		return s.split(separator);		
	}
	protected void setEntry(String a) {
		String[] table = createTableofEntry(a);
		t.addEntry(getNameFromEntry(table),getPriceFromEntry(table));
	}
}
