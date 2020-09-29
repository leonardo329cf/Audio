package senai.audio.controllers;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.springframework.stereotype.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import senai.audio.view.FxmlSecondaryController;

@Controller
public class FilesController implements FxmlSecondaryController {
	@FXML
	private Label folderPathLabel;
	@FXML
	private TableColumn<File, String> nameTC;
	@FXML
	private TableView<File> fileTV;
	
	private ObservableList<File> files;
	
	private File folder;
	
	private MainController mainController;
	
	// Event Listener on Button.onMouseClicked
	@FXML
	public void pickFolderOnClick(MouseEvent event) {
		String folder;
		System.out.println(System.getProperty("file.separator"));
		final JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setDialogTitle("Diretório para gravação");
		int response = fc.showOpenDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			 folder = fc.getSelectedFile().toPath().toString();
			 folder = folder.replace("\\", "/");
			 this.folder = new File(folder);
			 folderPathLabel.setText(folder);
			 refreshList();
		}
	}
	// Event Listener on Button.onMouseClicked
	@FXML
	public void refreshOnClick(MouseEvent event) {
		refreshList();
	}
	@FXML
	public void playOnClick(MouseEvent event) {
		if(!fileTV.getSelectionModel().isEmpty()) {
			mainController.loadPlay(fileTV.getSelectionModel().getSelectedItem());
		}
	}
	
	public MainController getMainController() {
		return mainController;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	@Override
	public void initialize() {
		List<File> list = new ArrayList<>();
		files = FXCollections.observableArrayList(list);
		nameTC.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
	}
	
	private void refreshList() {
		FilenameFilter filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File f, String name) {
				return name.endsWith(".wav");
			}
		};
		
		if(folder.isDirectory() && folder.exists()) {
			List<File> fileList = new ArrayList<>();
			
			File[] fileArray = folder.listFiles(filter);
			for(File f : fileArray) {
				fileList.add(f);
			}
			
			files = FXCollections.observableArrayList(fileList);
			fileTV.setItems(files);
		}
	}
}
