package com.concurrent.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RunnableTask implements Runnable {
	private String filename;
	private String content;

	public RunnableTask(String filename) {
		this.filename = filename;
		this.content = "";
		System.out.println("Create Task to get content from " + filename);
	}

	public String getFileName() {
		return filename;
	}

	public void run() {
		BufferedReader br = null;
		try {
			String currentDir = System.getProperty("user.dir");
			Path path = Paths.get(currentDir, "files", filename);
			File file = path.toFile();

			br = new BufferedReader(new FileReader(file));

			String line;
			while ((line = br.readLine()) != null) {
				content += line;
			}

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getContent() {
		return content;
	}
}
