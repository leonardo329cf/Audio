package senai.audio.controllers;

import java.io.File;

import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Slider;


import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import senai.audio.entities.Play;
import senai.audio.view.FxmlSecondaryController;

@Controller
public class PlayController implements FxmlSecondaryController {
	@FXML
	private Slider timeSlider;
	@FXML
	private Button playBt;
	
	private Play play;
	
	private MainController mainController;
	

	public void setPlay(File file) {
		play = new Play(file, timeSlider);
		playBt.setText("| |");
	}
	
	
	// Event Listener on Slider[#timeSlider].onMouseDragReleased
	@FXML
	public void timeOnClick() {
		playBt.setText(play.pause());
	}
	
	@FXML
	public void timeOnRelease() {
		play.setTime(Duration.seconds(timeSlider.getValue()));
	}
	
	// Event Listener on Button[#playBt].onMouseClicked
	@FXML
	public void playBtOnClick(MouseEvent event) {
		playBt.setText(play.play());
	}
	
	@FXML
	public void stopBtOnClick(MouseEvent event) {
		play.setTime(Duration.seconds(timeSlider.getValue()));
		playBt.setText(play.pause());
	}


	@Override
	public void initialize() {
		timeSlider.setValue(0);
	}
	
	@Override
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}	
}
