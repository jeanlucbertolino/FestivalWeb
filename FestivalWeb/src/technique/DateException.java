package technique;

import javax.swing.JOptionPane;

public class DateException extends Exception {
	
	// Constructeur 1
	public DateException() {
	
//		System.out.println("Veuillez SVP renseigner une DATE de programmation !!");
		JOptionPane.showConfirmDialog(null, "Veuillez utiliser le calendrier pour choisir une DATE svp!!!", "CONTRÔLE DATE", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);	
		
	}

}
