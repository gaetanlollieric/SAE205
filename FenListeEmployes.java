package vue;

import java.io.File;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;

public class FenListeEmployes extends Stage {
	
	private CtrlListeEmployes ctrl;
	
	public FenListeEmployes() throws IOException {
		this.setTitle("Gestion des membres");
		this.setResizable(true);
		Scene laScene = new Scene(creerSceneGraph());
		this.setScene(laScene);
	}

	private Pane creerSceneGraph() throws IOException {
		// Dans l'instruction suivante, indiquer le chemin complet du fichier fxml 
		File f = new File("/home/etuinfo/amjulien/Documents/dev_objet/TP5/listeEmployes.fxml");
		FXMLLoader loader;
		loader = new FXMLLoader(f.toURI().toURL());
		Pane racine = loader.load();
		ctrl = loader.getController();
		return racine;
	}
}