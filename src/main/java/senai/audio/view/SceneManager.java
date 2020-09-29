package senai.audio.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import senai.audio.spring.config.SpringFXMLLoader;

public class SceneManager {
	
	private SpringFXMLLoader loader;
	
	
	public SceneManager(SpringFXMLLoader loader) {
		this.loader = loader;
	}
	
	
	public Object switchScene(FxmlView view, AnchorPane sceneAnchor) throws IOException {
		FXMLLoader innerLoader = loader.getLoader(view.getFxmlFile());
		AnchorPane anchor = innerLoader.load();
		sceneAnchor.getChildren().setAll(anchor);
		return innerLoader.getController();
	}
	
	public void closeScene(AnchorPane sceneAnchor, FxmlController controller) {
		sceneAnchor.getChildren().setAll();
		controller = null;
	}
	
    public Object newScene(FxmlView view) throws IOException {
		FXMLLoader innerLoader = loader.getLoader(view.getFxmlFile());
		Parent parent = innerLoader.load();
		Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.setScene(newScene);
		newStage.show();
		return innerLoader.getController();
	}
}
