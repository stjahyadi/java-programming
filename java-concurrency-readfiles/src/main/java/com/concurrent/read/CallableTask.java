package com.concurrent.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

	private String filename;

	public CallableTask(String filename) {
		this.filename = filename;
		System.out.println("Create Callable task to get content from " + filename);
	}

	@Override
	public String call() throws Exception {
		BufferedReader br = null;
		String content = "";
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
		return content;
	}

}
