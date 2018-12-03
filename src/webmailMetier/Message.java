package webmailMetier;

/*
 * Créé le 24 oct. 2003
 */

/**
 * Message envoyé
 *
 *@author jrl
 *@version 1.0
 */
public class Message {

	private String m_from;
	private String m_to;
	private String m_sujet;
	private String m_contenu;


	/**
	 * Initialise ce message
	 * @param from Adresse émetteur sous la forme "Jean-Claude RIGAL" &lt;jcrigal@free.fr&gt;
	 * @param to Adresse destinataire sous la forme Paul AUCHON; pauchon@wanadoo.fr
	 * @param sujet Sujet du message
	 * @param contenu Contenu du message
	 */
	public Message (String from, String to, String sujet, String contenu)
	{
		m_from = from;
		m_to = to;
		m_sujet = sujet;
		m_contenu = contenu;
	}

	/**
	 * Obtient le contenu du message
	 * @return Le contenu du message
	 */
	public String getContenu() {
		return m_contenu;
	}

	/**
	 * Modifie le destinataire
	 * @param string Le destinataire
	 */
	public void setTo(String string) {
		m_to = string;
	}


	/**
	 * Obtient le nom de l'émetteur
	 * @return L'émetteur sous la forme "Jean-Claude RIGAL" &lt;jcrigal@free.fr&gt;
	 */
	public String getFrom() {
		return m_from;
	}

	/**
	 * Obtient le sujet du message
	 * @return Sujet
	 */
	public String getSujet() {
		return m_sujet;
	}

	/**
	 * Obtient le nom du destinataire
	 * @return Destinataire sous la forme Paul AUCHON; pauchon@wanadoo.fr
	 */
	public String getTo() {
		return m_to;
	}

	/**
	 * Obtient une chaine image de cet objet
	 * @return La chaine
	 */
    @Override
	public String toString()
	{
		StringBuilder loc = new StringBuilder();
		loc.append("[");
		loc.append(m_from).append(",");
		loc.append(m_to).append(",");
		loc.append(m_sujet).append(",");
		loc.append(m_contenu).append("]");
		return loc.toString();
	}
}
