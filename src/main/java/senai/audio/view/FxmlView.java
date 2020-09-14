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
	};
	
    abstract String getTitle();
    abstract String getFxmlFile();
	
}
