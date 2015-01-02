package com.sangupta.camtrack;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;
import com.sangupta.jerry.util.ConsoleUtils;

/**
 * Click picture from webcam every given time interval.
 * 
 */
public class CamTrack {
	
	public static void main(String[] args) {
		final File baseFolder = new File("c:/camtrack");
		final long interval = 60l * 1000l; // one minute
		final Webcam webcam = Webcam.getDefault();
		webcam.open();
		
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				BufferedImage image = webcam.getImage();
				try {
					ImageIO.write(image, "PNG", new File(baseFolder, "camtrack-" + System.currentTimeMillis() + ".png"));
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					image.flush();
				}
			}
		};
		
		timer.scheduleAtFixedRate(task, 0, interval);

		do {
			String command = ConsoleUtils.readLine("Type EXIT to end: ", true);
			if("exit".equalsIgnoreCase(command)) {
				timer.cancel();
				timer.purge();
				break;
			}
		} while(true);
	}
}
