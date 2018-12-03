package webmailIhm;

/*
 * Créé le 17 oct. 2003
 */



import webmailIhm.APropos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
 
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;


import webmailMetier.Message;
import webmailMetier.MailMgr;
import webmailMetier.Fichier;

/**
 *
 *
 *@author jrl
 *@version 1.0
 */
public class FenetrePrincipale extends JFrame{
private JMenuItem mnuMessageNouveau = new JMenuItem ("Nouveau", 'N');
private JMenuItem mnuMessageOuvrir = new JMenuItem ("Ouvrir ...", 'O');
private JMenuItem mnuMessageEnvoyer = new JMenuItem ("Envoyer", 'E');
private JMenuItem mnuMessageQuitter = new JMenuItem ("Quitter", 'Q');
private JMenuItem mnuOptionParametre = new JMenuItem ("Paramètres ...", 'P');
private JMenuItem mnuAideApropos = new JMenuItem ("A propos ...", 'A');

private JButton cmdToolNouveau = new JButton (new ImageIcon("new.gif"));
private JButton cmdToolOuvrir = new JButton (new ImageIcon("open.gif"));
private JButton cmdToolEnvoyer = new JButton (new ImageIcon("send.gif"));
private JTextField txtSujet = new JTextField();
private JComboBox cboMailingList = new JComboBox ();
private JLabel lblInfo = new JLabel ("Aucun message envoyé");
private JLabel lblErr = new JLabel ();

private JTextArea txtMail = new JTextArea ();
private JPanel panStatus = new JPanel(new FlowLayout(FlowLayout.LEFT));

private MailMgr m_mailMgr ;
private boolean m_sending = true;

private static String strServeur = "smtp.wanadoo.fr";
private static String strFrom = "\"Patrice François\" <patrice.francois@afpa.fr>";

	public FenetrePrincipale()
	{
		this.setTitle("Envoi de mails à un destinataire");
		this.setSize(700,300);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new AppWindowAdapter());
		this.setIconImage(new ImageIcon("mail.gif").getImage());
		initMenu();
		initControles();
		m_mailMgr = new MailMgr(strServeur);
	}

	private void initControles()
	{
        
        //this.setTitle("Interface Utilisateur");
            this.setBounds(300, 300, 700, 400);
		JPanel zoneClient = (JPanel) this.getContentPane();

		JToolBar tbPrincipale = new JToolBar ();
		tbPrincipale.setFloatable(false);

		cmdToolNouveau.addActionListener(new MenuActionListener());
		cmdToolNouveau.setToolTipText("Nouveau message");
		tbPrincipale.add(cmdToolNouveau);
		cmdToolOuvrir.addActionListener(new MenuActionListener());
		cmdToolOuvrir.setToolTipText("Ouvrir un message existant");
		tbPrincipale.add(cmdToolOuvrir);
		tbPrincipale.addSeparator();
		cmdToolEnvoyer.addActionListener(new MenuActionListener());
		cmdToolEnvoyer.setToolTipText("Envoyer le message");
		cmdToolEnvoyer.setEnabled(false);
		tbPrincipale.add(cmdToolEnvoyer);

                FlowLayout mailItem = new FlowLayout(FlowLayout.RIGHT);
                JPanel panDroite = new JPanel (mailItem);
		panDroite.add( new JLabel ("Sujet"));
		
		txtSujet.setColumns(20);
		txtSujet.setToolTipText("Sujet du message");
		panDroite.add( txtSujet);
		panDroite.add(new JLabel("Pour"));
		panDroite.add(cboMailingList)	;
		tbPrincipale.add (panDroite);

		zoneClient.add(tbPrincipale, BorderLayout.NORTH);

		
		txtMail.setLineWrap(true);
		txtMail.setWrapStyleWord(true);
		JScrollPane panMail = new JScrollPane (txtMail);
		zoneClient.add(panMail, BorderLayout.CENTER);

		cboMailingList.setEnabled(true);
		cboMailingList.setToolTipText("Adresse destinataire");
		Fichier f = new Fichier("MailingList.txt");
		ArrayList v = (ArrayList) f.getLignes();
		while (!v.isEmpty()) cboMailingList.addItem(v.remove(0));

		panStatus.setBorder(BorderFactory.createLoweredBevelBorder());
		panStatus.add(lblInfo)	;
		lblErr.setBorder(BorderFactory.createRaisedBevelBorder());
		lblErr.setForeground(Color.RED);
		panStatus.add(lblErr);
		zoneClient.add (panStatus, BorderLayout.SOUTH);

                txtMail.addCaretListener(new TexteCaretListener());
                txtSujet.addCaretListener(new TexteCaretListener());
	}

	private void initMenu()
	{
		JMenuBar mbPrincipale = new JMenuBar();
		this.setJMenuBar(mbPrincipale);

		JMenu mnuMessage = new JMenu ("Message");
		mnuMessage.setMnemonic('M');
		mbPrincipale.add (mnuMessage);
		JMenu mnuOption = new JMenu ("Options");
		mnuOption.setMnemonic('O');
		mbPrincipale.add (mnuOption);
		JMenu mnuAide = new JMenu ("?");
		mnuAide.setMnemonic('?');
		mbPrincipale.add (mnuAide);

		mnuMessageNouveau.addActionListener(new MenuActionListener());
		mnuMessageNouveau.addChangeListener(new MenuChangeListener());
		mnuMessage.add(mnuMessageNouveau);

		mnuMessageOuvrir.addActionListener(new MenuActionListener());
		mnuMessageOuvrir.addChangeListener(new MenuChangeListener());
		mnuMessage.add(mnuMessageOuvrir);

		mnuMessage.addSeparator();
		mnuMessageEnvoyer.setEnabled(false);
		mnuMessageEnvoyer.setAccelerator(KeyStroke.getKeyStroke("F2"));
		mnuMessageEnvoyer.addActionListener(new MenuActionListener());
		mnuMessageEnvoyer.addChangeListener(new MenuChangeListener());
		mnuMessage.add(mnuMessageEnvoyer);

		mnuMessage.addSeparator();
		mnuMessageQuitter.addActionListener(new MenuActionListener());
		mnuMessageQuitter.addChangeListener(new MenuChangeListener());
		mnuMessage.add(mnuMessageQuitter);

		mnuOptionParametre.addActionListener(new MenuActionListener());
		mnuOptionParametre.addChangeListener(new MenuChangeListener());
		mnuOption.add(mnuOptionParametre);

		mnuAideApropos.addActionListener(new MenuActionListener());
		mnuAideApropos.addChangeListener(new MenuChangeListener());
		mnuAide.add(mnuAideApropos);

	}


	class AppWindowAdapter extends WindowAdapter {
        @Override
	public void windowClosing(WindowEvent e)
        {
		mnuMessageQuitter_click();
	}

	}

	class TexteCaretListener implements CaretListener {

		public void caretUpdate(CaretEvent e) {
			Object o = e.getSource();
			if (o == txtMail ) txtMail_caret();
			if (o == txtSujet ) txtMail_caret();
		}
	}

	class MenuActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e){
		Object o = e.getSource();
		if (o == mnuMessageNouveau ) mnuMessageNouveau_click();
		if (o == mnuMessageOuvrir ) mnuMessageOuvrir_click();
		if (o == mnuMessageEnvoyer) mnuMessageEnvoyer_click();
		if (o == mnuMessageQuitter) mnuMessageQuitter_click();
		if (o == mnuOptionParametre) mnuOptionParametre_click();
		if (o == mnuAideApropos) mnuAideApropos_click();

		if (o == cmdToolOuvrir) mnuMessageOuvrir_click();
		if (o == cmdToolNouveau ) mnuMessageNouveau_click();
		if (o == cmdToolEnvoyer) mnuMessageEnvoyer_click();
		}
	}

	class MenuChangeListener implements ChangeListener {

	public void stateChanged(ChangeEvent e) {
	Object o = e.getSource();
        if (o == mnuMessageNouveau )
            lblInfo.setText("Créer un nouveau message");
	if (o == mnuMessageOuvrir )
            lblInfo.setText("Ouvrir un message existant");
	if (o == mnuMessageEnvoyer)
            lblInfo.setText("Envoyer un message");
	if (o == mnuMessageQuitter)
            lblInfo.setText("Quitter l'application");
	if (o == mnuOptionParametre)
            lblInfo.setText("Paramétrer la messagerie");
	if (o == mnuAideApropos)
            lblInfo.setText("A propos de l'application");
		}
	}

	class MessageFilter extends FileFilter {
		public boolean accept(File f) {
			return f.isDirectory() || f.getName().endsWith(".msg");
		}
		public String getDescription() {
			return "Fichier message (*.msg)";
		}
	}

	private void mnuMessageNouveau_click()
	{
		if (! txtMail.getText().equals("")){
		int rep = JOptionPane.showConfirmDialog(this,
                        "Voulez-vous effacer le contenu du message",
			this.getTitle(), JOptionPane.YES_NO_OPTION);
			if (rep == JOptionPane.YES_OPTION)	{
				txtMail.setText("")	  ;
			}
		}
	}

	private void mnuMessageOuvrir_click()
	{
		JFileChooser dlg = new JFileChooser ("./msg");
		dlg.addChoosableFileFilter(new MessageFilter());
		int rep = dlg.showOpenDialog(this);
		if (rep == JFileChooser.APPROVE_OPTION){
			Fichier ficMess = new Fichier(dlg.getSelectedFile());
			txtMail.setText(ficMess.getContenu());
		}
	}

	private void mnuMessageEnvoyer_click()
	{
		m_sending = true;
		lblErr.setText("");
		Message mess =
                new Message (strFrom, (String) cboMailingList.getSelectedItem(),
		                      txtSujet.getText(), txtMail.getText());

		if (m_mailMgr.envoyerMessage(mess) == MailMgr.OK)
			lblInfo.setText("Message transmis à " + mess.getTo() ) ;
		else
			lblErr.setText("Erreur d'émission");
	}

	private void mnuMessageQuitter_click()
	{
		if (m_sending)System.exit(0);
		int rep =
                JOptionPane.showConfirmDialog(this,
            "Le message n'a pas été envoyé\nVoulez-vous quitter l'application",
                        this.getTitle(), JOptionPane.YES_NO_OPTION);
		if (rep == JOptionPane.YES_OPTION)
			System.exit(0);
	}

	private void mnuOptionParametre_click()
	{
		// Création de l'objet et définition du parent
		// La boite est chargée en mémoire mais non visible
		Parametre dlg = new Parametre(this);
		// Les infos d'entrée sont passée et la boite devient visible
		int retCode = dlg.showDialog("Hello World");
		// La boite a été fermée par OK ou ANNULER
		// Si c'est par OK on récupère les informations saisies
		if (retCode == Parametre.OK)
			JOptionPane.showMessageDialog(this, dlg.getInfo());
// On a plus besoin de la boite, on libère les ressources graphiques
		dlg.dispose();
	}

	private void mnuAideApropos_click()
	{
		APropos dlg = new APropos (this, "Web Mail");
		dlg.setCopyright("(c) JC Rigal 2003");
		dlg.setDescription(this.getTitle());
		dlg.setIcone("mail.gif");
		dlg.setVersion("Version 1.0");
		dlg.show();
	}

	private void txtMail_caret()
	{
	boolean vide =
                txtMail.getText().equals("") || txtSujet.getText().equals("");
		cmdToolEnvoyer.setEnabled (!vide);
		mnuMessageEnvoyer.setEnabled (!vide);
		m_sending = vide;
	}

}
