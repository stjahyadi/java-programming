package com.concurrent.read;

import java.util.ArrayList;
import java.util.List;

public class RunnableExample {
	public static void main(String[] args) {
		execute();
	}

	public static void execute() {
		// create tasks
		List<RunnableTask> tasks = new ArrayList<>();
		List<Thread> threads = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			RunnableTask task = new RunnableTask("file" + (i + 1) + ".txt");
			Thread t = new Thread(task);
			t.start();
			tasks.add(task);
			threads.add(t);
		}

		try {
			// wait for threads to finish
			for (int i = 0; i < 5; i++) {
				threads.get(i).join();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String content = "";
		for (int i = 0; i < 5; i++) {
			content += tasks.get(i).getContent() + System.lineSeparator();
		}
		System.out.println("Result: ");
		System.out.println(content);
	}
}
