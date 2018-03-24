package fr.albi.mines.SwingAwtSandbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {

	public Panneau() {
		this.setBackground(Color.ORANGE);

		// TODO Auto-generated constructor stub
	}

	public Panneau(LayoutManager arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Panneau(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Panneau(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	public void paintComponent(Graphics g){
		//Vous verrez cette phrase chaque fois que la méthode sera invoquée
		System.out.println("Je suis exécutée !"); 
		//dessiner une cercle
		g.fillOval(20, 20, 75, 75);
		//ecrire du texte
		Font font = new Font("Courier", Font.BOLD, 20);
		g.setFont(font);
		g.setColor(Color.red);          
		g.drawString("Tiens ! Le Site du Zéro !", 10, 20);
		//dessiner une image
		//		    try {
		//		        //Image img = ImageIO.read(new File("images.jpg"));
		//		        //g.drawImage(img, 0, 0, this);
		//		        //Pour une image de fond
		//		        //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		//		      } catch (IOException e) {
		//		        e.printStackTrace();
		//		      }  
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp, gp2, gp3, gp4, gp5, gp6; 
		gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
		gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
		gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
		gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
		gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
		gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

		g2d.setPaint(gp);
		g2d.fillRect(0, 0, 20, this.getHeight());               
		g2d.setPaint(gp2);
		g2d.fillRect(20, 0, 20, this.getHeight());
		g2d.setPaint(gp3);
		g2d.fillRect(40, 0, 20, this.getHeight());
		g2d.setPaint(gp4);
		g2d.fillRect(60, 0, 20, this.getHeight());
		g2d.setPaint(gp5);
		g2d.fillRect(80, 0, 20, this.getHeight());
		g2d.setPaint(gp6);
		g2d.fillRect(100, 0, 40, this.getHeight());
	}               

}
