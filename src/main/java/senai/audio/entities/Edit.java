package senai.audio.entities;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Edit {

	private String filePath;
	private MediaPlayer mediaPlayer;
	private Media media;
	private Double max;
	private Slider timeSlider;
	private Slider beginSlider;
	private Slider endSlider;
	private ChangeListener<Duration> listener;
	
	
	public Edit(File file, Slider timeSlider, Slider beginSlider, Slider endSlider) {
		this.filePath = file.getAbsolutePath().toString();
		media = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		this.timeSlider = timeSlider;
		this.beginSlider = beginSlider;
		this.endSlider = endSlider;
		
		mediaPlayer.setOnReady(new Runnable() {
	        @Override
	        public void run() {
	            max = media.getDuration().toSeconds();
	            timeSlider.setMax(max);
	    		timeSlider.setMajorTickUnit(10);
	    		timeSlider.setMin(0);
	    		timeSlider.setMinorTickCount(1);
	    		timeSlider.setShowTickMarks(true);
	    		timeSlider.setBlockIncrement(max/10);
	    		
	    		beginSlider.setMax(max);
	    		beginSlider.setMajorTickUnit(10);
	    		beginSlider.setMin(0);
	    		beginSlider.setMinorTickCount(1);
	    		beginSlider.setShowTickMarks(true);
	    		beginSlider.setBlockIncrement(max/10);
	    		
	    		endSlider.setMax(max);
	    		endSlider.setMajorTickUnit(10);
	    		endSlider.setMin(0);
	    		endSlider.setMinorTickCount(1);
	    		endSlider.setShowTickMarks(true);
	    		endSlider.setBlockIncrement(max/10);
	    		endSlider.setValue(max);
	        }
	    });
		listener = new ChangeListener<Duration>() {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
            	timeSlider.setValue(newValue.toSeconds());
            }
        };
		mediaPlayer.currentTimeProperty().addListener(listener);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.setOnEndOfMedia(new Runnable() {

	        @Override
	        public void run() {
	            mediaPlayer.stop();
	            mediaPlayer.setStartTime(Duration.seconds(beginSlider.getValue()));
	            timeSlider.setValue(beginSlider.getValue());
	        }
	    });
	}
		
		public String getFilePath() {
			return filePath;
		}

		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}

		public MediaPlayer getMediaPlayer() {
			return mediaPlayer;
		}

		public void setMediaPlayer(MediaPlayer mediaPlayer) {
			this.mediaPlayer = mediaPlayer;
		}
		
		public void setStartTime(Duration time) {
			mediaPlayer.setStartTime(time);
		}
		
		public String play() {
			if(!mediaPlayer.getStatus().toString().equals(Status.PLAYING.toString())) {
				mediaPlayer.currentTimeProperty().addListener(listener);
				mediaPlayer.play();
				return ">";
			} else {
				mediaPlayer.currentTimeProperty().removeListener(listener);
				mediaPlayer.pause();
				return "| |";
			}
		}
		
		public String pause() {
			if(mediaPlayer.getStatus().toString().equals(Status.PLAYING.toString())) {
				mediaPlayer.currentTimeProperty().removeListener(listener);
				mediaPlayer.pause();
			}
			return "| |";
		}
		
		public Duration getCurrentTime() {
			return mediaPlayer.getCurrentTime();
		}
		
		public Double getTotalDuration() {
			System.out.println(max);
			return max;
		}
		
		public void setEndTime(Duration time) {
			mediaPlayer.setStopTime(time);
		}
		
		public void save(String name) {
			media = null;
			mediaPlayer = null;
		    AudioInputStream inputStream = null;
		    AudioInputStream shortenedStream = null;
		    try {
		      File file = new File(filePath);
		      AudioFileFormat fileFormat = AudioSystem.getAudioFileFormat(file);
		      AudioFormat format = fileFormat.getFormat();
		      
		      inputStream = AudioSystem.getAudioInputStream(file);
		      int bytesPerSecond = format.getFrameSize() * (int)format.getFrameRate();
		      
		      System.out.println((long) (beginSlider.getValue() * bytesPerSecond));
		      inputStream.skip((long) (beginSlider.getValue() * bytesPerSecond));
		      
		      System.out.println((long) (long) ((endSlider.getValue() - beginSlider.getValue()) * (int)format.getFrameRate()));
		      long framesOfAudioToCopy = (long) ((endSlider.getValue() - beginSlider.getValue()) * (int)format.getFrameRate());
		      shortenedStream = new AudioInputStream(inputStream, format, framesOfAudioToCopy);
		      
		      System.out.println(filePath.substring(0, filePath.lastIndexOf('/') + 1) + name + "_edited");
		      File destinationFile = new File(filePath.substring(0, filePath.lastIndexOf('/') + 1) + name + "_edited.wav");
		      file.delete();
		      destinationFile.renameTo(new File(filePath.substring(0, filePath.lastIndexOf('/') + 1) + name + ".wav"));
		      
		      AudioSystem.write(shortenedStream, fileFormat.getType(), destinationFile);
		      System.out.println("fim");

		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		  }

}
