package fr.albi.minastere;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVWritter {
	private List<Ticket> lf;
	private FileWriter lr;
	private int rowcnt;
	private int colcnt;
	private static String[] colList ={"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S"};
	
	
	public CSVWritter(String dirPath,String path) throws IOException {
		lf = new ArrayList<Ticket>();
		rowcnt=1;
		colcnt=0;
		setLf(dirPath);
		try {
			lr = new FileWriter(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void setLf(String dirPath) {
		File f = new File(dirPath);
		if(f.isDirectory()) {
			for (File file : f.listFiles()) {
				if(file.getName().endsWith("txt"))
					try {
						TicketWritter tw = new TicketWritter(file);
						lf.add(tw.createEntries(file));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		}
	}
	
	
	public String createContent() {
		String content="::";
		for (int k =0;k<colcnt;k++) {
			content=content+"1::";
		}
		return content;
	}
	
	
	public void createDate(Ticket t) {
		ecrireLigne("date::"+ t.getDate());
		rowcnt++;
	}
	
	
	public void ecrireLigne(String texte) {
		ArrayList<String> listeRemplie =new ArrayList<String>();
		ArrayList<String> listeARemplir =new  ArrayList<String>();
		for (String t:texte.split("::")) {
			listeRemplie.add(t);
		}
		for (String s : listeRemplie) {
			listeARemplir.add(s);
			if (listeARemplir.containsAll(listeRemplie)) {
				try {
					lr.write(s+"\n");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					lr.write(s+",");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}



	public void createHeader() {
		Scanner sc = new Scanner (System.in);
		String Hdefault="";
		do {
			System.out.println("tous le minastère ? (Y/N)");
			Hdefault= sc.nextLine();
		}while (!Hdefault.equals("y")&&!Hdefault.equals("n"));
		if (Hdefault.toLowerCase().equals("y")) {
			colcnt=7;
			ecrireLigne("Produit::Prix::Pierrick::Tanguy::Quentin::Emma::Pierre::Aïda::Moulin::prix/participants");
		}
		else if (Hdefault.toLowerCase().equals("n")) {
			String a="";
			String content = "Produit::Prix::";
			System.out.println("rentrer les participants 1 par 1 (appuyez sur 'entré' aparès chaque nom saisi).\n Quand tous les participants on etait saisie,saisir 'fait' et appuyer sur entré ");
			while (!a.equals("fait")) {
				if (a!="") {
					content = content +a+"::";
					colcnt++;}
				a=sc.nextLine();
			}
			ecrireLigne(content+"prix/participants");
		}
		else {
			System.out.println("choisir 'y' ou 'n'");
			createHeader();
		}
		sc.close();
	}




	public void createFooter(int rowcnt) throws IOException {
		String content="";
		for (int k=0;k<colcnt;k++) {
			String s=colList[k];
			content=content+"=";
			for(int i=2;i<rowcnt;i++) {
				content =content+s+i+"*"+colList[colcnt]+i+"+";
			}
			content =content+s+rowcnt+"*"+colList[colcnt]+rowcnt+"-"+sommeReduc()+"/"+colcnt+"::";


		}
		
		int r=rowcnt+1;
		content=content+"=SUM("+colList[0]+r+":"+colList[colcnt-1]+r+"";
		ecrireLigne("Total,"+"=SUM(B2:B"+rowcnt+")"+","+content);
		
	}

	public void createTicket(List<Ticket> lf) {

		for (Ticket t : lf) {
			createDate(t);
			for (Entry e : t.getEntries()) {
				rowcnt++;
				ecrireLigne(e.getName()+"::"+e.getPrice()+createContent()+"=B"+rowcnt+"/SUM(C"+rowcnt+":"+colList[colcnt-1]+rowcnt+")");
			}
		}

	}
	public void createTotal() throws IOException {
		for (Ticket t :lf)
			ecrireLigne((t.getTotal().getName()+"::"+t.getTotal().getPrice()));

		ecrireLigne("total sommé sur les 'total achats'::"+sommeTotal());
	}
	private double sommeTotal() {
		double total=0;
		for (Ticket t :lf) {
			total=total+t.getTotal().getPrice();
		}
		return total;
	}
	private double sommeReduc() {
		double reduc=0;
		for (Ticket t :lf) {
			reduc=reduc+t.getReduc();
		}
		return reduc;
	}
	public void createReduc() {
		
		ecrireLigne("total sommé sur les 'reductions'::"+sommeReduc());
		ecrireLigne("total avec reduction prise en compte::="+sommeTotal()+"-"+sommeReduc());
	}

		
	

	public void entriesWrite() throws IOException {
		createHeader();
		createTicket(lf);
		createFooter(rowcnt);
		ecrireLigne("totaux");
		createTotal();
		ecrireLigne("reductions");
		createReduc();
		lr.close();
	}



}
