package vue;

import controleur.Main;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import modele.Employe;

public class CtrlListeEmployes {

    @FXML private Button bnModifier;
    @FXML private Button bnSupprimer;
    @FXML private Button bnFermer;
    @FXML private TableView<Employe> tvListeEmployes;
    @FXML private CheckBox saison;
    //@FXML private CheckBox cours;
    @FXML private CheckBox nom;
    
    // clic sur bouton Modifier
    @FXML void clicModifier(ActionEvent event ) {
    	controleur.Main.ouvrirModifierEmploye(tvListeEmployes.getSelectionModel().getSelectedItem());
    }
    
    // clic sur bouton Supprimer
    @FXML void clicSupprimer(ActionEvent event) {
    	Alert alert = new Alert(
    			AlertType.CONFIRMATION,
    			"Voulez-vous vraiment supprimer ce membre ?",
    			ButtonType.YES,
    			ButtonType.NO
    	);
    	alert.setTitle("Confirmation de suppression");
    	alert.showAndWait();
    	if(alert.getResult() == ButtonType.YES) {
    		System.out.print("Suppression...");
    		controleur.Main.supprimerEmploye(tvListeEmployes.getSelectionModel().getSelectedItem());
    	}
    }
     
    // clic sur bouton Fermer
    @FXML void clicFermer(ActionEvent event) {
    	controleur.Main.fermerAppli();
    	controleur.Main.fermerNouvelEmploye();
    	controleur.Main.fermerModifierEmploye();
    }
    
    @FXML void doubleClic(MouseEvent e) {
    	
    }
    
    @FXML void initialize() {
    	TableColumn<Employe,Integer> colonne1 = new TableColumn<Employe,Integer>("Identifiant");
		colonne1.setCellValueFactory(new PropertyValueFactory<Employe,Integer>("identifiant"));	
		tvListeEmployes.getColumns().set(0, colonne1);
		TableColumn<Employe, String> colonne2 = new TableColumn<Employe,String>("Nom");
		colonne2.setCellValueFactory(new PropertyValueFactory<Employe, String>("nom"));
		tvListeEmployes.getColumns().set(1, colonne2);
		TableColumn<Employe, String> colonne3 = new TableColumn<Employe,String>("Prénom");
		colonne3.setCellValueFactory(new PropertyValueFactory<Employe, String>("prenom"));
		tvListeEmployes.getColumns().set(2, colonne3);
		TableColumn<Employe,Integer> colonne4 = new TableColumn<Employe,Integer>("Inscription cours");
		colonne4.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("inscription"));
		tvListeEmployes.getColumns().set(3, colonne4);
		TableColumn<Employe,Integer> colonne5 = new TableColumn<Employe,Integer>("Montant payé");
		colonne4.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("paye"));
		tvListeEmployes.getColumns().set(4, colonne5);
		TableColumn<Employe,Integer> colonne6 = new TableColumn<Employe,Integer>("Montant dû");
		colonne4.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("impaye"));
		tvListeEmployes.getColumns().set(5, colonne6);
				
		tvListeEmployes.setItems(Main.getLesEmployes());
		tvListeEmployes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//A FAIRE griser les boutons Modifier et Supprimer quand aucune sélection
		
		BooleanBinding rien = Bindings.equal(tvListeEmployes.getSelectionModel().selectedIndexProperty(), -1);
		bnSupprimer.disableProperty().bind(rien);
		bnModifier.disableProperty().bind(rien);
    }
    
}