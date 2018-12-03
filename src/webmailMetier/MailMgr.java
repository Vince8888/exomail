package webmailMetier;

/*
 * Créé le 17 oct. 2003
 */

/**
 * Gestionnaire de communication SMTP
 *
 *@author jrl
 *@version 1.0
 */
public class MailMgr {

	private String m_serveur;

	public static final int OK = 0;
	public static final int ERREUR = 1;

	/**
	 * Initialise cet objet
	 * @param string L'URL du serveur
	 */
	public MailMgr (String string)
	{
		m_serveur = string;
	}

	/**
	 * Envoyer le message par SMTP
	 * @param mess Le message
	 */
	public int envoyerMessage (Message mess)
	{
	    Fichier archive = new Fichier("M" + mess.getTo() + ".msg");
	    archive.setContenu(mess.getContenu());
	    System.out.println("Envoi du message: " + mess);
	    return 0;
	}

}

