package senai.audio.view;


public enum FxmlView {

	MAIN { 
		@Override
		String getTitle() {
			return "Main";
		}
		
		@Override
		String getFxmlFile() {
			return "/fxml/Main.fxml";
		}
	},
	RECORD { 
		@Override
		String getTitle() {
			return "Record";
		}
		
		@Override
		String getFxmlFile() {
			return "/fxml/Record.fxml";
		}
	},
	FILES { 
		@Override
		String getTitle() {
			return "Files";
		}
		
		@Override
		String getFxmlFile() {
			return "/fxml/Files.fxml";
		}
	},
	PLAY {
		@Override
		String getTitle() {
			return "Play";
		}
		
		@Override
		String getFxmlFile() {
			return "/fxml/Play.fxml";
		}
	},
	EDIT {
		@Override
		String getTitle() {
			return "Edit";
		}
		
		@Override
		String getFxmlFile() {
			return "/fxml/Edit.fxml";
		}
	};
	
	
    abstract String getTitle();
    abstract String getFxmlFile();
	
}
