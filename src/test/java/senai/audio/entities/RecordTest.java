package senai.audio.entities;

import java.io.File;

import javax.sound.sampled.AudioFormat;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecordTest {

	@Test
	public void gravar() {
		AudioFormat format = new AudioFormat(44100, 16, 2, true, true);
		File newFile = new File("C:/Users/leonardo/Documents/gravacoes/newfile.wav");
		
		Record newRecord = new Record(format, newFile);
		
		Thread stopper = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                newRecord.finish();
            }
        });

		stopper.start();
		newRecord.start();
		
	}
}
