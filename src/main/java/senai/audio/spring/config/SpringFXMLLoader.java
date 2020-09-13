package senai.audio.spring.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

@Component @Lazy
public class SpringFXMLLoader {

    private final ApplicationContext context;

    @Autowired
    public SpringFXMLLoader(ApplicationContext context) {

        this.context = context;
    }

    public FXMLLoader getLoader(String fxmlPath) throws IOException {      
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean); //Spring now FXML Controller Factory
        loader.setLocation(getClass().getResource(fxmlPath));
        return loader;
    }
}