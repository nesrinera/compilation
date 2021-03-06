 package com;
//Rahali Nesrine et Bouchenafa Rania Groupe 05

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class compilation {

	private JFrame frmMonAnalyseur;
	JTextArea textArea;
	
	static JFileChooser file_chooser = new JFileChooser("");
	static FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichiers text", "snl");
	static ArrayList<String> mots = new ArrayList<String>();
	static ArrayList<String> lignes = new ArrayList<String>();
	static ArrayList<String> sortie_lexic = new ArrayList<String>();
	static String[] mot;
	static File song = new File("C:\\Users\\Toshiba\\workspace2018\\Projet2018\\src\\package1\\click.wav");
/*********************************************************************************************************************/
	
	static void click (File sound) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength()/50000);
			
		} catch (Exception e) {
			
		}
	}
	
/*********************************************************************************************************************/
	public static void charger() throws FileNotFoundException {
		file_chooser.addChoosableFileFilter(filter);
		if(file_chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
			File file = file_chooser.getSelectedFile();
			Scanner sc_lignes = new Scanner(file);
			Scanner sc_mots = new Scanner(file);
			mots.clear();
			lignes.clear();
				while(sc_lignes.hasNextLine()){
					lignes.add(sc_lignes.nextLine());
				}
				while(sc_mots.hasNext()){
					mots.add(sc_mots.next());
					}

			sc_mots.close();
			sc_lignes.close();
			}
	}

/*********************************************************************************************************************/

	public boolean isNum(String chaine, int i) {
		char[] nombre = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		int j = 0;
		while (j < nombre.length) {
			if (chaine.charAt(i) == nombre[j]) {
				return true;
			}
			j++;
		}

		return false;
	}

	public String num(String chaine) {
		int i = 0;
		int token_pos = 0;
		boolean point_unique = true;
		while (i < chaine.length()) {
			if (isNum(chaine, i)) token_pos++;
			else if(point_unique & chaine.charAt(token_pos)==',') {
				token_pos++;
				point_unique = false;
			}
			i++;
		}

		if (token_pos == chaine.length() && !chaine.contains(",")) return "Valeur entier";
		else if (token_pos == chaine.length() && !point_unique) return "Valeur reel";
		return null;

	}

/*********************************************************************************************************************/

	public boolean isChar(String chaine, int i) {
		char[] alphabet = { 'A', 'a', 'B', 'b', 'C', 'c', 'D', 'd', 'E', 'e', 'F', 'f', 'G', 'g', 'H', 'h', 'I', 'i',
				'J', 'j', 'K', 'k', 'L', 'l', 'M', 'm', 'N', 'n', 'O', 'o', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T',
				't', 'U', 'u', 'V', 'v', 'W', 'w', 'X', 'x', 'Y', 'y', 'Z', 'z' };
		int k = 0;
		while (k < alphabet.length) {
			if (chaine.charAt(i) == alphabet[k]) {
				return true;
			}
			k++;
		}
		return false;

	}

	public String id(String chaine) {
		boolean verifier_Premier = false;
		boolean tiret_unique = true;
		int token_pos = 0;
		int i = 0;
		if (isChar(chaine, 0)) {
			token_pos++;
			verifier_Premier = true;
		}
		if (verifier_Premier == true && chaine.length() == 1)
			return "identificateur";

		else if (chaine.length() > 1) {
			i = 1;
			while (i < chaine.length()) {

				if (isChar(chaine, i))
					{token_pos++;
					tiret_unique=true;
					}
				else if (isNum(chaine, i))
					{token_pos++;
					tiret_unique=true;
					}
				else if (chaine.charAt(i) == '_' && tiret_unique) {
					tiret_unique=true;
					token_pos++;
				}
				i++;
			}
			if (token_pos == chaine.length())
				return "Identificateur";
		}
		return null;
	}

/********************************************************************lexical*************************************************/

	public String UL_reserve(String chaine) {
		String[] mot_reserve = { "<", ">", ",", "Start_program", "Int_Number", ";;", ":", "Real_Number", "If", "Give", "Else",
				"Start", "--", "Finish", "ShowMes", "ShowVal", "//." ,"End_program"," \" "};

		String[] Affichage = { 
				"symbol inferieur", "symbol superieur", "caractere reservé virgule",
				"Mot reserve debut du programme", " Mot reserve declaration d'un entier",
				"Mot reserve", "caractere reservé", " Mot reserve declaration d'un Real",
				" Mot reserve pour une condition ", "Mot reserve", "Mot reserve pour condition SINON", "Debut d'un sous programme",
				"Mot reserve pour une condition", "Fin d'un sous programme",
				"Mot reserve pour afficher un message ", "Mot reservé pour afficher une valeur", "Mot reserve pour un commentair",
				"Mot reserve Fin du programme","motreserve"};
		int i = 0;
		while (i < mot_reserve.length) {
			if (chaine.equals(mot_reserve[i])) {
				return Affichage[i];
			}
			i++;

		}
		return null;
	}

/***********************************************************************************syntaxique**********************************/

	public String syntax(String chaine){
		char g='\"';
		if(chaine.equals("Start_program")) return "syntaxe correcte";
		else if(chaine.equals("Else")|| chaine.equals("else")) return "syntaxe correcte";
		else if(chaine.equals("Start")) return "syntaxe correcte";
		else if(chaine.equals("Finish")) return "syntaxe correcte";
		else if(chaine.startsWith("//.")) return "syntaxe correcte";
		else if(chaine.equals("End_program")) return "syntaxe correcte";
		else if(chaine.startsWith("ShowMes") && chaine.endsWith(" ;;")) return "syntaxe correcte";
		else if(chaine.startsWith("ShowVal")&& chaine.endsWith(";;")) return "syntaxe correcte";
		else if(chaine.contains(" ")) {
			mot = chaine.split(" ");
			int i=0, k=1;
			if(mot[i].equals("Int_Number")){
				i++;
				if(mot[i].equals(":"))
					i++;
					if(id(mot[i]) != null){
						i++;
						while(mot[i].equals(",") && i<7){
							i++;
							if(id(mot[i]) != null) i++;
						}
						if(mot[i].equals(";;"))return "syntaxe correcte";
					}
			}
			
			else if(mot[i].equals("Give")){
				i++;
				if(id(mot[i]) != null){
					i++;
				if(mot[i].equals(":"))i++;
					if(num(mot[i]) == "Valeur entier") {
						i++;
						if(mot[i].equals(";;")) return "syntaxe correcte";
					}
					else if(num(mot[i]) == "Valeur reel"){
						i++;
						if(mot[i].equals(";;")) return "syntaxe correcte";
					}

				
			}

			}
			
			else if(mot[i].equals("Real_Number")){
				i++;
				if(mot[i].equals(":"))i++;
				      
					if(id(mot[i]) != null)i++;
						if(mot[i].equals(";;")) return "syntaxe correcte";
					
					
					}
			else if(mot[i].equals("If")){
				i++;
				if(mot[i].equals("--")){
					i++;
				if(id(mot[i]) != null){
					i++;
					if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==")){
					i++;
					if(id(mot[i]) != null){
						i++;
					if(mot[i].equals("--")) return "syntaxe correcte"; 
						}}}}
			}		
			else if(mot[i].equals("Affect")){
				i++;
					if(id(mot[i]) != null)i++;
					if(mot[i].equals("to")){
						i++;
						if(id(mot[i]) != null)i++;
					 
						if(mot[i].equals(";;")) return "syntaxe correcte";
					}
					}
		}
								
		return "erreur de syntaxe";
	}

	
/**************************************************sémantique*******************************************************************/

	
	public String semantique(String chaine){
		if(chaine.equals("Start_program")) return "Début de programme";
		else if(chaine.equals("Else")) return "Sinon";
		else if(chaine.equals("Start")) return "début bloc";
		else if(chaine.equals("Finish")) return "fin d un bloc";
		else if(chaine.startsWith("//.")) return "exemple d un commentaire";
		else if(chaine.equals("End_program")) return "fin programme";
		else if(chaine.startsWith("ShowMes") && chaine.endsWith(" ;;")) return "affichage d'un message";
		else if(chaine.startsWith("ShowVal")&& chaine.endsWith(";;")) return "afficharge de la valeur de i";
		else if(chaine.contains(" ")) {
			mot = chaine.split(" ");
			int i=0;

				if(mot[i].equals("Int_Number")){
					i++;
					if(mot[i].equals(":"))
						i++;
						if(id(mot[i]) != null){
							i++;
							while(mot[i].equals(",") && i<7){
								i++;
								if(id(mot[i]) != null) i++;
							}
							if(mot[i].equals(";;"))return "Déclaration de troi entier";
						}}
				else if(mot[i].equals("Give")){
					i++;
					if(id(mot[i]) != null){
						i++;
					if(mot[i].equals(":"))i++;
						if(num(mot[i]) == "Valeur entier") {
							i++;
							if(mot[i].equals(";;")) return "affectation d'une valeur à i";
						}
						else if(num(mot[i]) == "Valeur reel"){
							i++;
							if(mot[i].equals(";;")) return "affectation d'une valeur reel à Af34_2";
						}
                        }}
				else if(mot[i].equals("Real_Number")){
					i++;
					if(mot[i].equals(":")) i++;
					if(id(mot[i]) != null)i++;
							if(mot[i].equals(";;")) return "déclaration d un réel";
						
						
						}
                else if(mot[i].equals("If")){
					i++;
					if(mot[i].equals("--")){
						i++;
					if(id(mot[i]) != null){
						i++;
						if(mot[i].equals("<") || mot[i].equals(">") || mot[i].equals("==")){
						i++;
						if(id(mot[i]) != null){
							i++;
						if(mot[i].equals("--")) return "Condition alors action"; 
							}}}}
				}
				
				
				
				
				
				else if(mot[i].equals("Affect")){
					i++;
						if(id(mot[i]) != null)i++;
						if(mot[i].equals("to")){
							i++;
							if(id(mot[i]) != null)i++;
						 
							if(mot[i].equals(";;")) return "Affectation de valeur entre variables";
						}
						}

				

				
								}
		return "erreur sémantique";
		
	}

	
	
/*********************************************************************************************************************/
	public void lexicale(List<String> liste) {
		int i = 0;

		while (i < mots.size()) {
			if (UL_reserve(mots.get(i)) != null) {
				sortie_lexic.add(UL_reserve(mots.get(i)));
			} else if (id(mots.get(i)) != null) {
				sortie_lexic.add(id(mots.get(i)));
			} else if (num(mots.get(i)) != null) {
				sortie_lexic.add(num(mots.get(i)));
			}
			else sortie_lexic.add("Erreur");

			i++;
		}

	}

/*********************************************************************************************************************/
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					compilation window = new compilation();
					window.frmMonAnalyseur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public compilation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMonAnalyseur = new JFrame();
		frmMonAnalyseur.setResizable(false);
		frmMonAnalyseur.setTitle("Analyseur lexicale , syntaxique et lexical");
		frmMonAnalyseur.getContentPane().setBackground(new Color(119, 136, 153));
		frmMonAnalyseur.getContentPane().setLayout(null);
		frmMonAnalyseur.setLocationRelativeTo(null);
		
		Cursor cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(95, 158, 160));
		panel.setBounds(10, 45, 250, 414);
		frmMonAnalyseur.getContentPane().add(panel);
		
				JButton btnNewButton = new JButton("Charger");
				btnNewButton.setCursor(cursor);
				btnNewButton.setBounds(10, 11, 212, 109);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
				 
						try {
							textArea.setText("");
							charger();	

							int i = 0;
							while (i < lignes.size()) {
								textArea.setText(textArea.getText()+lignes.get(i)+"\n");
								i++;}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					  
					}  
				});
				panel.setLayout(null);
				btnNewButton.setForeground(new Color(0, 0, 0));
				btnNewButton.setBackground(SystemColor.controlHighlight);
				btnNewButton.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 28));
				panel.add(btnNewButton);
				
				JButton btnAlexicale = new JButton("Analyse Lexicale");
				btnAlexicale.setCursor(cursor);
				btnAlexicale.setBounds(10, 147, 212, 71);
				btnAlexicale.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText("");
						lexicale(mots);
						int i = 0;
						while (i < mots.size()) {
							textArea.setText(textArea.getText()+mots.get(i) + " : " + sortie_lexic.get(i)+"\n");
							i++;}
					}
				});
				btnAlexicale.setForeground(new Color(0, 0, 0));
				btnAlexicale.setBackground(SystemColor.controlHighlight);
				btnAlexicale.setFont(new Font("Roboto", Font.BOLD, 17));
				panel.add(btnAlexicale);
				
				JButton btnAsyntaxique = new JButton("Analyse Syntaxique");
				btnAsyntaxique.setCursor(cursor);
				btnAsyntaxique.setBounds(10, 239, 212, 71);
				btnAsyntaxique.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText("");
						int i = 0;
						while (i < lignes.size()) {
							textArea.setText(textArea.getText()+lignes.get(i) + " ------> " +syntax(lignes.get(i))+"\n");
							i++;}
					}
				});
				btnAsyntaxique.setForeground(new Color(0, 0, 0));
				btnAsyntaxique.setBackground(SystemColor.controlHighlight);
				btnAsyntaxique.setFont(new Font("Roboto", Font.BOLD, 17));
				panel.add(btnAsyntaxique);
				
				JButton btnAsmantique = new JButton("Analyse S\u00E9mantique");
				btnAsmantique.setCursor(cursor);
				btnAsmantique.setBounds(10, 330, 212, 72);
				btnAsmantique.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						textArea.setText("");
						int i = 0;
						
						
						while (i < lignes.size()) {
							textArea.setText(textArea.getText()+lignes.get(i) + " ---------> " +semantique(lignes.get(i))+"\n");
							
							i++;}
					}
				});
				btnAsmantique.setForeground(new Color(0, 0, 0));
				btnAsmantique.setBackground(SystemColor.controlHighlight);
				btnAsmantique.setFont(new Font("Roboto", Font.BOLD, 17));
				panel.add(btnAsmantique);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(95, 158, 160));
		panel_1.setBounds(270, 45, 506, 414);
		frmMonAnalyseur.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 11, 467, 373);
		panel_1.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(Color.WHITE);
		textArea.setFont(new Font("Perpetua", Font.BOLD, 16));
		
		JLabel lblSortie = new JLabel("Sortie :");
		lblSortie.setForeground(new Color(0, 0, 0));
		lblSortie.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblSortie.setBounds(280, 23, 104, 14);
		frmMonAnalyseur.getContentPane().add(lblSortie);
		
		JLabel lblNewLabel = new JLabel("Commandes :");
		lblNewLabel.setBounds(10, 17, 124, 24);
		frmMonAnalyseur.getContentPane().add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(-27, 0, 814, 490);
		frmMonAnalyseur.getContentPane().add(lblNewLabel_3);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\SAMSUNG\\Desktop\\ranger-4df6c1b6.png"));
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\SAMSUNG\\Desktop\\Free-HD-Light-Blue-Wallpaper-Photos.jpg"));
		lblNewLabel_1.setBounds(0, 0, 787, 490);
		frmMonAnalyseur.getContentPane().add(lblNewLabel_1);
		frmMonAnalyseur.setBounds(100, 100, 793, 518);
		frmMonAnalyseur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
