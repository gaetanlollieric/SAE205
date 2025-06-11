package vue;

import java.io.File;

import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Employe;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class FenModifierEmploye extends Stage {
	
	private CtrlModifierEmploye ctrl;
	
	public FenModifierEmploye() throws IOException {
		this.setTitle("Détail d'un membre");
		this.setResizable(false);
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier fxml 
		File f = new File("/home/etuinfo/amjulien/Documents/dev_objet/TP5/modifierEmploye.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		ctrl = loader.getController();
		return racine;
	}
	
	public void afficherEmploye(Employe e) {
		ctrl.afficherEmploye(e);
	}

}