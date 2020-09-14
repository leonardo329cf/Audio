package senai.audio.controllers;

import java.awt.Button;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import senai.audio.entities.enums.Channel;
import senai.audio.entities.enums.Resolution;
import senai.audio.entities.enums.SamplingRate;
import senai.audio.view.FxmlController;
import javafx.scene.control.ChoiceBox;

@Controller
public class RecordController implements FxmlController {
	
	private MainController mainController;
	@FXML
	private TextField folderTF;
	@FXML
	private TextField fileNameTF;
	@FXML
	private ChoiceBox samplingRateCB;
	@FXML
	private ChoiceBox resolutionCB;
	@FXML
	private ChoiceBox channelsCB;

	public MainController getMainController() {
		return mainController;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void findFolderBtOnClick() {
		System.out.println("Find");
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void recordBtOnClick() {
		System.out.println("record");
	}
	@Override
	public void initialize() {
		List<String> samplingRateList = new ArrayList<>();
		for (SamplingRate x : SamplingRate.values()) {
			samplingRateList.add(x.getName() + "(" + x.getValue() + ")");
		}
		ObservableList<String> obsSamplingRateList = FXCollections.observableList(samplingRateList);
		samplingRateCB.setItems(obsSamplingRateList);
		
		List<String> resolutionList = new ArrayList<>();
		for (Resolution x : Resolution.values()) {
			resolutionList.add(x.getName() + "(" + x.getValue() + " bits)");
		}
		ObservableList<String> obsResolutionList = FXCollections.observableList(resolutionList);
		resolutionCB.setItems(obsResolutionList);
		
		List<String> channelList = new ArrayList<>();
		for (Channel x : Channel.values()) {
			channelList.add(x.getName() + "(" + x.getValue() + ")");
		}
		ObservableList<String> obsChannelList = FXCollections.observableList(channelList);
		channelsCB.setItems(obsChannelList);
		
		
	}
}
