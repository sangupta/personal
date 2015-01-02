package com.sangupta.boost;

import java.io.File;
import java.io.IOException;

import com.sangupta.jerry.util.ConsoleUtils;

/**
 * @author sangupta
 *
 */
public class Boost {
	
	public static void main(String[] args) {
		try {
			File file = new File("out.mp3");
			if(file.exists()) {
				file.renameTo(new File("out-" + System.currentTimeMillis() + ".mp3"));
				
			}
			
			System.out.println("Running Boost...");
			Process process = Runtime.getRuntime().exec("c:\\tools\\boost\\boost.exe out");
			ConsoleUtils.readLine("press enter to stop?", true);
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
