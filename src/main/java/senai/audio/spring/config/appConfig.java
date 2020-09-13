package senai.audio.spring.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;



import javafx.stage.Stage;
import senai.audio.view.SceneManager;
import senai.audio.view.StageManager;

@Configuration
public class appConfig {
	@Autowired SpringFXMLLoader springFXMLLoader;
    
    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public StageManager stageManager(Stage stage) throws IOException {
        return new StageManager(springFXMLLoader, stage);
    }
    
    @Bean
    @Lazy(value = true) //Stage only created after Spring context bootstap
    public SceneManager sceneManager() throws IOException {
        return new SceneManager(springFXMLLoader);
    }

}
