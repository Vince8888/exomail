package webmailIhm;

/*
 * Créé le 2 avr. 2004
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 *
 *@author jrl
 *@version 1.0
 */
public class Parametre extends JDialog implements ActionListener{

	// Définition des constantes informant sur le type de fermeture
	public static final int OK = 0;
	public static final int ANNULER = -1;

	// Les controles de la boite (OK ANNULER + les autres)
	private JTextArea txtInfo = new JTextArea();
	private JButton cmdOK = new JButton("OK");
	private JButton cmdAnnuler = new JButton("Annuler");

	// Type de fermeture (ANNULER si fermé par la X)
	private int typeFermeture = ANNULER;

	// Le constructeur mémorise le parent, le mode d'ouverture (modal)
	// La fenetre se CACHE si on ferme par la x
	public Parametre (JFrame parent)
	{
		super(parent, "Choix des paramètres", true);
		this.setSize(300,200);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		initControles();
	}

	// Pseudo-surcharge de la méthode show() pour avoir une
	// valeur de retour
	// On en profite pour passer les information d'entrée
	public int showDialog(String info)
	{
		// Contrôles initialisés en foction des entrées
		txtInfo.setText(info);
		// Méthode bloquante jusqu'au HIDE
		this.setVisible(true);
		return typeFermeture;
	}

	// Autant de getters() que d'informations à récupérer
	public String getInfo()
	{
		return txtInfo.getText();
	}

	private void initControles()
	{
		JPanel zoneClient = (JPanel) this.getContentPane();

		JPanel panBas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panBouton = new JPanel (new GridLayout(0,2,10,10));
		panBas.add(panBouton);

		cmdOK.addActionListener(this);
		panBouton.add(cmdOK);
		cmdAnnuler.addActionListener(this);
		panBouton.add(cmdAnnuler);

		zoneClient.add(new JScrollPane(txtInfo), BorderLayout.CENTER);
		zoneClient.add(panBas,  BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cmdOK) cmdOK_click ();
		if (e.getSource() == cmdAnnuler) cmdAnnuler_click ();
	}

	// OK et ANNULER cachent la fenetre (sans la décharger)
	private void cmdAnnuler_click()
	{
		typeFermeture = ANNULER;
		this.hide();
	}

	private void cmdOK_click()
	{
		typeFermeture = OK;
		//this.hide();
	}

}

