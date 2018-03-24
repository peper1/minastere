package fr.albi.minastere;

import java.util.ArrayList;
import java.util.List;

public class  Ticket {
	private List<Entry> entries;
	private Entry total;
	private String type;
	private String date;
	private double reduc;
	

	/**
	 * @param entries
	 * @param total
	 * @param type
	 */
	public Ticket( String type) {
		this.type = type;
		this.entries= new ArrayList<Entry>();
		this.reduc=0;
	}
	/**
	 * @return the entries
	 */
	public List<Entry> getEntries() {
		return entries;
	}
	/**
	 * @param entries the entries to set
	 */
	public void addEntries(Entry entry) {
		this.entries.add(entry);
	}
	/**
	 * @return the total
	 */
	public Entry getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(Entry total) {
		this.total = total;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the reduc
	 */
	public double getReduc() {
		return reduc;
	}
	/**
	 * @param reduc the reduc to set
	 */
	public void setReduc(double reduc) {
		this.reduc = reduc;
	}

}