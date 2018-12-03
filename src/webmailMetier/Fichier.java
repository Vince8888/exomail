package webmailMetier;


import java.io.*;
import java.text.*;
import java.util.*;

import javax.swing.*;

/**
 * Gestion de fichier
 *
 *@author 
 *@version 1.0
 */
public class Fichier {


	private String m_nom;

	/**
	 * Définit le nom du fichier
	 * @param file Nom du fichier
	 * @see Vector
	 */
	public Fichier(File file) {

		m_nom = file.getAbsolutePath();
	}

	/**
	 * Définit le nom du fichier
	 * @param nom Nom du fichier
	 */
	public Fichier(String nom)
	{
		m_nom = nom;
	}

	/**
	 * Définit le chemin relatif du fichier
         * sous la forme ./Msg/M20031024-0945.msg
	 * Chaque fichier à un nom unique
         * Le répertoire ./Msg doit exister
	 */
	public Fichier()
	{
	GregorianCalendar now = new GregorianCalendar ();
	SimpleDateFormat format =
                  new SimpleDateFormat ("'./msg/M'yyyyMMdd-HHmmss'.msg'");
	m_nom = format.format(now.getTime());
	}

	/**
	 * Retourne le contenu du fichier texte 
         * sous forme d'unE ArrayList, un élément par ligne
	 * @return l'ArrayList
	 */
	public ArrayList getLignes()
	{
	ArrayList local = new ArrayList();
	try
        {
	BufferedReader f = new BufferedReader(new FileReader(m_nom));
	for(;;)
        {
		String lg = f.readLine();
		if (lg==null) break;
		if (lg.length()>0) local.add(lg);
	}
	f.close();
	}
	catch (IOException e){
	}
	finally {
		Collections.sort(local)	;
	}
	return local;
	}

	/**
	 * Créer un fichier texte contenant la chaine
	 * @param val Contenu du fichier
	 */
	public void setContenu(String val)
	{
	try
        {
		PrintWriter f =
                     new PrintWriter(new FileWriter(m_nom, true));
		f.println(val);
		f.close();
	}
	catch (IOException e){
	JOptionPane.showMessageDialog(null, 
                "Sauvegarde du message impossible",
                "Erreur sur " + m_nom, JOptionPane.OK_OPTION);
	}

	}

	/**
	 * Retourne le contenu d'un fichier texte
         * sous forme d'une chaîne unique
	 * @return Le contenu
	 */
	public String getContenu()
	{
	StringBuilder local = new StringBuilder();
	try
        {
            BufferedReader f =
                         new BufferedReader(new FileReader(m_nom));
            for(;;)
            {
		String lg = f.readLine();
		if (lg==null) break;
		local.append(lg).append("\n");
            }
            f.close();
         }
	catch (IOException e)
        {
	}
        return local.toString();
	}

}
