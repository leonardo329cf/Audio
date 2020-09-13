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
	};
	
    abstract String getTitle();
    abstract String getFxmlFile();
	
}
