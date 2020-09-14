package senai.audio.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import senai.audio.spring.config.SpringFXMLLoader;

public class SceneManager {
	
	private SpringFXMLLoader loader;
	
	
	public SceneManager(SpringFXMLLoader loader) {
		this.loader = loader;
	}
	
	
	public Object switchScene(FxmlView view, AnchorPane sceneAnchor) throws IOException {
		 FXMLLoader innerLoader = loader.getLoader(view.getFxmlFile());
		 System.out.println(innerLoader);
		 AnchorPane anchor = innerLoader.load();
		sceneAnchor.getChildren().setAll(anchor);
		return innerLoader.getController();
	}
	
	public void closeScene(AnchorPane sceneAnchor, FxmlController controller) {
		sceneAnchor.getChildren().setAll();
		controller = null;
	}
}
