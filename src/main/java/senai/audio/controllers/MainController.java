package senai.audio.controllers;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import senai.audio.view.FxmlController;
import senai.audio.view.FxmlSecondaryController;
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
	@FXML
	private AnchorPane filesAnchorP;
	private FilesController filesController;
	
	@Autowired
	public MainController(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
	
	private void loadSection(FxmlView fxmlView, AnchorPane anchor, FxmlSecondaryController controller) {
		try {
			controller = (FxmlSecondaryController) sceneManager.switchScene(fxmlView, anchor);
			controller.setMainController(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void initialize() {
		loadSection(FxmlView.RECORD, recordAnchorP, recordController);	
		loadSection(FxmlView.FILES, filesAnchorP, filesController);
	}
	
	public void loadPlay(File file) {
		try {
			PlayController controller;
			controller = (PlayController) sceneManager.newScene(FxmlView.PLAY);
			controller.setMainController(this);
			controller.setPlay(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadEdit(File file) {
		try {
			EditController controller;
			controller = (EditController) sceneManager.newScene(FxmlView.EDIT);
			controller.setMainController(this);
			controller.setEdit(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
