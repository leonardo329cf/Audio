package senai.audio;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.stage.Stage;
import senai.audio.view.FxmlView;
import senai.audio.view.SceneManager;
import senai.audio.view.StageManager;

@SpringBootApplication
public class AudioApplication extends Application {

	protected ConfigurableApplicationContext springContext;
	protected StageManager stageManager;
	protected SceneManager sceneManager;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = bootstrapSpringApplicationContext();
	}



	@Override
	public void start(Stage stage) throws Exception {
		stageManager = springContext.getBean(StageManager.class, stage);
		sceneManager = springContext.getBean(SceneManager.class);
        displayInitialScene();

	}
	
	@Override
	public void stop() throws Exception {
		springContext.close();
	}
	
	protected void displayInitialScene() {
        stageManager.switchScene(FxmlView.MAIN);
    }
	
	
    private ConfigurableApplicationContext bootstrapSpringApplicationContext() {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(AudioApplication.class);
        String[] args = getParameters().getRaw().stream().toArray(String[]::new);
        builder.headless(false); //needed for TestFX integration testing or eles will get a java.awt.HeadlessException during tests
        return builder.run(args);
    }
}
