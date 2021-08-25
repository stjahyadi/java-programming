package com.concurrent.read;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
	public static void main(String[] args) {
		execute();
	}

	public static void execute() {
		// create tasks
		List<Callable<String>> tasks = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			tasks.add(new CallableTask("file" + (i + 1) + ".txt"));
		}

		List<Future<String>> results = null;
		try {
			// create threads for each task
			ExecutorService exec = Executors.newFixedThreadPool(2);
			results = exec.invokeAll(tasks);
			exec.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// collect results
        System.out.println("Result:");
        String content = "";
        try {
            for (Future<String> future : results) {
                content += future.get() + System.lineSeparator();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(content);
	}
}
