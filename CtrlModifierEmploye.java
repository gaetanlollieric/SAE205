package vue;

import controleur.Main;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import modele.*;

public class CtrlModifierEmploye {
    @FXML private Button 			bnAnnuler;
    @FXML private Button 			bnOK;
    @FXML private ComboBox<String> 	cbDepartement;
    @FXML private ComboBox<String> 	cbSuperieur;
    @FXML private DatePicker 		dpDateEmbauche;
    @FXML private TextField 		txtMatricule;
    @FXML private TextField 		txtNom;
    @FXML private TextField 		txtPoste;
    @FXML private TextField 		txtPrime;
    @FXML private TextField 		txtSalaire;
      
	@FXML public void annuler() {
    	Main.fermerModifierEmploye();
    }
	
	@FXML public void valider() {
    	int sup;
    	if (cbSuperieur.getSelectionModel().getSelectedIndex()==0 || cbSuperieur.getSelectionModel().getSelectedIndex()==-1) {
    		sup = -1;
    	} else {
    		sup = Integer.parseInt(cbSuperieur.getSelectionModel().getSelectedItem());
    	}
    	float sal;
    	if (txtSalaire.getText().isEmpty()) {
    		sal = -1;
    	} else {
    		sal = Float.parseFloat(txtSalaire.getText());
    	} 
    	float prime;
    	if (txtPrime.getText().isEmpty()) {
    		prime = -1;
    	} else {
    		prime = Float.parseFloat(txtPrime.getText());
    	} 
    	Main.modifierEmploye( 	Integer.parseInt(txtMatricule.getText()),
								txtNom.getText(),
								txtPoste.getText(),
								sup,
								dpDateEmbauche.getValue().getYear(),
								dpDateEmbauche.getValue().getMonthValue(),
								dpDateEmbauche.getValue().getDayOfMonth(),
								sal,
								prime,
								Integer.parseInt(cbDepartement.getSelectionModel().getSelectedItem())
    	);

    }
	

	public void afficherEmploye(Employe e) {
		txtMatricule.setText(""+e.getMatricule());
		txtNom.setText(e.getNom());
		txtPoste.setText(e.getPoste());
		if (!e.getEmbauche().equals("")) {
			String dateemb[] = e.getEmbauche().split("/");
			int annee = Integer.parseInt(dateemb[0]);
			int mois = Integer.parseInt(dateemb[1]);
			int jour = Integer.parseInt(dateemb[2]);
			dpDateEmbauche.setValue(LocalDate.of(annee, mois, jour));
		}
		if (e.getSuperieur()==-1) {
			cbSuperieur.setValue("- aucun -");
		} else {
			cbSuperieur.setValue(""+e.getSuperieur());
		}
		
		txtSalaire.clear();
		if (e.getSalaire()!=-1) txtSalaire.setText(""+e.getSalaire());
		txtPrime.clear();
		if (e.getPrime()!=-1) txtPrime.setText(""+e.getPrime());

		cbDepartement.setValue(""+e.getDept());		
	}

	public void initialize() {
    	// initialiser le formulaire de modification de l'employé
    	// - en remplissant les deux listes déroulantes (supérieur et département)
    	// - en fournissant des valeurs par défaut (supérieur, date embauche et département)
    	// - et en affectant à chaque champ la valeur de l'attribut correspondant
     	
    	ArrayList<Integer> lesSup = Donnees.getLesSuperieurs();
    	this.cbSuperieur.getItems().clear();
		for (int i=0; i<lesSup.size() ; i++) {
			this.cbSuperieur.getItems().add(""+lesSup.get(i));
		}
		this.cbSuperieur.getItems().add(0, "- aucun -");

		ArrayList<Integer> lesDep = Donnees.getLesDepartements();
    	this.cbDepartement.getItems().clear();
		for (int i=0; i<lesDep.size() ; i++) {
			this.cbDepartement.getItems().add(""+lesDep.get(i));
		}
		
		bnOK.disableProperty().bind(txtNom.textProperty().isEmpty());
    }
}