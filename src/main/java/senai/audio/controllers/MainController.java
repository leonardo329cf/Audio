package senai.audio.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import senai.audio.view.FxmlController;
import senai.audio.view.FxmlView;
import senai.audio.view.SceneManager;
import javafx.scene.control.Tab;

@Component
public class MainController implements FxmlController {
	private SceneManager sceneManager;

	@FXML
	private Tab recordTab;
	@FXML
	private HBox recordHBox;
	@FXML
	private AnchorPane recordAnchorP;
	private RecordController recordController;
	
	@FXML
	private Tab filesTab;
	@FXML
	private HBox filesHBox;
	
	@Autowired
	public MainController(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	private void loadRecordSection() {
		try {
			recordController = (RecordController) sceneManager.switchScene(FxmlView.RECORD, recordAnchorP);
			recordController.setMainController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void initialize() {
		loadRecordSection();		
	}

}
