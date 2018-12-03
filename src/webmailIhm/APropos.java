package webmailIhm;

/*
 * Créé le 17 oct. 2003
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class APropos extends JDialog  implements ActionListener
{

	private JLabel lblDescription = new JLabel ();
	private JLabel lblCopyright = new JLabel ();
	private JLabel lblVersion = new JLabel () ;
	private JLabel lblIcone = new JLabel ();
	private JButton cmdOK = new JButton ("OK");

	public APropos(JFrame parent, String titre)
	{

		super(parent, "A Propos de " + titre, true);
		Point loc = parent.getLocation();
		this.setLocation((int) loc.getX()+50,(int)loc.getY()+100);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
		initControles();
	}

	private void initControles()
	{
		JPanel zoneClient = (JPanel) this.getContentPane();
		zoneClient.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		JPanel panDroite = new JPanel (new GridLayout(3,1,10,10));
		panDroite.add (lblDescription);
		panDroite.add (lblCopyright);
		panDroite.add (lblVersion);

		JPanel panHaut = new JPanel(new FlowLayout (FlowLayout.CENTER, 20, 0));
		panHaut.add (lblIcone);
		panHaut.add (panDroite);

		JPanel panBas = new JPanel (); // FlowLayout()
		cmdOK.addActionListener(this);
		panBas.add(cmdOK);

		zoneClient.add (panHaut, BorderLayout.NORTH);
		zoneClient.add (panBas, BorderLayout.SOUTH);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cmdOK) this.dispose();
	}

	public void setDescription (String texte)
	{
		lblDescription.setText(texte);
		this.pack();
	}

	public void setVersion (String texte)
	{
		lblVersion.setText(texte);
		this.pack();
	}

	public void setCopyright (String texte)
	{
		lblCopyright.setText(texte);
		this.pack();
	}

	public void setIcone (String url)
	{
		lblIcone.setIcon(new ImageIcon(url));
		this.pack();
	}
}
