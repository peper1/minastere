package fr.albi.minastereclean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class TicketFileReader {
	private static final String LECLERCFLAG ="»";
	private static final String[] CASINOFLAG = {"total a regler ","total achats"};
	public static final String CASINO="casino";
	public static final String LECLERC="leclerc";
	
	
	public static String isType(File file) {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file),Charset.forName("UTF-16")));
			String a = bf.readLine();
			while (a!=null ) {
				a=a.trim().toLowerCase();
				if (a.contains(LECLERCFLAG)) {
					bf.close();
					return LECLERC;
				}
				else if(a.contains(CASINOFLAG[0])||a.contains(CASINOFLAG[1])) {
					bf.close();
					return CASINO;}
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
	}

