package fr.albi.minastere;


	public class Entry {
		private String name;
		private double price;
		
		public Entry(String a,String separator) {
			String[] table = a.trim().replace("\t"," ").split(separator);
			this.setName(table,separator);
			this.setPrice(table,separator);
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String[] table, String separator) {
			this.name="";
			
			for (int k=0;k<table.length-1;k++)
				this.name= this.name+table[k].replace(",", " ")+" ";
		}

		/**
		 * @return the price
		 */
		public double getPrice() {
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(String[] table,String separator) {
			try {
				String price=table[table.length-1].trim().replace("E", "").replace(",", ".").replaceAll("\t", " ");
				this.price = Double.valueOf(price.split(" ")[price.split(" ").length-1]);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}


