package senai.audio.entities;




import java.io.File;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class Play {

	private String filePath;
	private MediaPlayer mediaPlayer;
	private Media media;
	private Double max;
	private Slider timeSlider;
	private ChangeListener<Duration> listener;
	
	
	public Play(File file, Slider timeSlider) {
		this.filePath = file.toURI().toString();
		media = new Media(filePath);
		mediaPlayer = new MediaPlayer(media);
		this.timeSlider = timeSlider;
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
	            mediaPlayer.setStartTime(Duration.seconds(0));
	            timeSlider.setValue(0.0);
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
	
	public void setTime(Duration time) {
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
}
