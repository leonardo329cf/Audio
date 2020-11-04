package senai.audio.controllers;

import java.io.File;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Slider;

import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import senai.audio.entities.Edit;
import senai.audio.view.FxmlSecondaryController;

@Controller
public class EditController implements FxmlSecondaryController {
	
	@FXML
	private Slider timeSlider;
	@FXML
	private Button playBt;
	@FXML
	private Slider beginSlider;
	@FXML
	private Slider endSlider;
	@FXML
	private Button SaveBt;
	@FXML
	private TextField nameTf;
	
	private Edit edit;
	
	private MainController mainController;
	
	
	public void setEdit(File file) {
		edit = new Edit(file, timeSlider, beginSlider, endSlider);
		playBt.setText("| |");
		nameTf.setText(file.getName().substring(0, file.getName().lastIndexOf('.')));
	}

	// Event Listener on Slider[#timeSlider].onMousePressed
	@FXML
	public void timeOnClick() {
		playBt.setText(edit.pause());
	}
	// Event Listener on Slider[#timeSlider].onMouseReleased
	@FXML
	public void timeOnRelease() {
		if(timeSlider.getValue() >= beginSlider.getValue() && timeSlider.getValue() <= endSlider.getValue()) {
			edit.setStartTime(Duration.seconds(timeSlider.getValue()));
		} else if(timeSlider.getValue() < beginSlider.getValue()) {
			edit.setStartTime(Duration.seconds(timeSlider.getValue()));
			beginSlider.setValue(timeSlider.getValue());
		} else {
			edit.setStartTime(Duration.seconds(timeSlider.getValue()));
			endSlider.setValue(timeSlider.getValue());
		}
	}
	// Event Listener on Slider[#beginSlider].onMouseReleased
	@FXML
	public void beginOnRelease() {
		if(beginSlider.getValue() > endSlider.getValue()) {
			endSlider.setValue(beginSlider.getValue());
		}
		edit.setStartTime(Duration.seconds(beginSlider.getValue()));
	}
	// Event Listener on Slider[#endSlider].onMouseReleased
	@FXML
	public void endOnRelease() {
		if(beginSlider.getValue() > endSlider.getValue()) {
			beginSlider.setValue(endSlider.getValue());
		}
		edit.setEndTime(Duration.seconds(endSlider.getValue()));
	}
	// Event Listener on Button[#playBt].onMouseClicked
	@FXML
	public void playBtOnClick(MouseEvent event) {
		playBt.setText(edit.play());
	}
	// Event Listener on Button[#SaveBt].onMouseClicked
	@FXML
	public void saveBtOnClick(MouseEvent event) {
		edit.save(nameTf.getText());
	}
	@Override
	public void initialize() {
		timeSlider.setValue(0);
		beginSlider.setValue(0);
	}
	@Override
	public void setMainController(MainController mainController) {
		mainController = this.mainController;
		
	}
}
