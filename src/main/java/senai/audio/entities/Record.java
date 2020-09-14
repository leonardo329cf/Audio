package senai.audio.entities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;


public class Record {

	private AudioFormat format;
	private File newFile;
	private TargetDataLine targetLine;
	private AudioFileFormat.Type fileType;
	private boolean stopped;
	
	public Record(AudioFormat format, File newFile) {
		super();
		this.format = format;
		this.newFile = newFile;
		this.fileType = AudioFileFormat.Type.WAVE;
	}

	public AudioFormat getFormat() {
		return format;
	}

	public void setFormat(AudioFormat format) {
		this.format = format;
	}

	public File getNewFile() {
		return newFile;
	}

	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}

	public TargetDataLine getTargetLine() {
		return targetLine;
	}

	public void setTargetLine(TargetDataLine targetLine) {
		this.targetLine = targetLine;
	}

	public AudioFileFormat.Type getFileType() {
		return fileType;
	}

	public void setFileType(AudioFileFormat.Type fileType) {
		this.fileType = fileType;
	}
	
	
	public void start() {
		 DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		 
         if (!AudioSystem.isLineSupported(info)) {
             System.out.println("Line not supported");
             System.out.println(info.toString());
             System.exit(0);
         } else {
        		try {
					targetLine = (TargetDataLine) AudioSystem.getLine(info);
					targetLine.open(format);
					targetLine.start();
					ByteArrayOutputStream out  = new ByteArrayOutputStream();
					int numBytesRead;
					byte[] data = new byte[targetLine.getBufferSize() / 5];

					// Begin audio capture.
					targetLine.start();

					// Here, stopped is a global boolean set by another thread.
					while (!stopped) {
					   // Read the next chunk of data from the TargetDataLine.
					   numBytesRead =  targetLine.read(data, 0, data.length);
					   // Save this chunk of data.
					   out.write(data, 0, numBytesRead);
					}
					byte[] allBytes = out.toByteArray();
					ByteArrayInputStream bais =  new ByteArrayInputStream(allBytes);
					AudioInputStream ais = new AudioInputStream(bais, format, allBytes.length/format.getFrameSize());
					AudioSystem.write(ais, fileType, newFile);
				} catch (LineUnavailableException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		System.out.println("Gravação terminada");
				targetLine.stop();
				targetLine.close();
         }
	}
	
	public void finish() {
		System.out.println("Gravação terminada");
		stopped = true;
	}
	
}
