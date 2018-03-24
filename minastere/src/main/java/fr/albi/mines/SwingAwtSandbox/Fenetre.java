package fr.albi.mines.SwingAwtSandbox;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

	public Fenetre(){
		super();
		this.setTitle("Ma première fenêtre Java");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);
		setResizable(true);
		setAlwaysOnTop(true);
		//Instanciation d'un objet JPanel
	    Panneau pan = new Panneau();
	    //Définition de sa couleur de fond
	    ;        
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    this.setContentPane(pan);               
	    this.setVisible(true);
	}
}


