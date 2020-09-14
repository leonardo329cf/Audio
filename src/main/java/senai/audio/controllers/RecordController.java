package senai.audio.controllers;

import java.awt.Button;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.swing.JFileChooser;

import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import senai.audio.entities.Record;
import senai.audio.entities.enums.Channel;
import senai.audio.entities.enums.Resolution;
import senai.audio.entities.enums.SamplingRate;
import senai.audio.view.FxmlController;

@Controller
public class RecordController implements FxmlController {
	
	private MainController mainController;
	@FXML
	private Label folderLabel;
	@FXML
	private TextField fileNameTF;
	@FXML
	private ChoiceBox samplingRateCB;
	@FXML
	private ChoiceBox resolutionCB;
	@FXML
	private ChoiceBox channelsCB;
	@FXML
	private javafx.scene.control.Button recordBt;
	
	private boolean recording;
	
	private Record record;

	public MainController getMainController() {
		return mainController;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void findFolderBtOnClick() {
		String folder;
		System.out.println(System.getProperty("file.separator"));
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Diretório para gravação");
		int response = fc.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			 folder = fc.getSelectedFile().toPath().toString();
			 folder = folder.replace("\\", "/");
			 folderLabel.setText(folder);
		}
	}

	public void recordBtOnClick() {
		if(recording) {
			record.finish();
			recordBt.setText(">");
			recordBt.setTextFill(Color.BLACK);
		} else {
			if(folderLabel.getText().isBlank() ||
					fileNameTF.getText().isBlank() ||
					samplingRateCB.getSelectionModel().isEmpty() ||
					resolutionCB.getSelectionModel().isEmpty() ||
					channelsCB.getSelectionModel().isEmpty()			
					) {
				Alert alert = new Alert(AlertType.ERROR, "Campos vazios!", ButtonType.OK);
				alert.show();
			} else {
				String filePath = folderLabel.getText() + "/" + fileNameTF.getText()+ ".wav";
				File newFile = new File(filePath);
				
				Integer samplingRate = SamplingRate.getEnumByname(samplingRateCB.getSelectionModel().getSelectedItem().toString()).getValue();
				Integer resolution = Resolution.getEnumByName(resolutionCB.getSelectionModel().getSelectedItem().toString()).getValue();
				Integer channels = Channel.getEnumByName(channelsCB.getSelectionModel().getSelectedItem().toString()).getValue();
				
				AudioFormat format = new AudioFormat(samplingRate, resolution, channels, true, true);
				
				record = new Record(format, newFile);
				
				recordBt.setText("| |");
				recordBt.setTextFill(Color.RED);
				Thread startRecording = new Thread(new Runnable() {
			        public void run() {
			        	record.start();
			        	
			        }
			    });
				startRecording.start();
			}
		}
		recording = !recording;
		
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
		
		recording = false;
	}
	

}
