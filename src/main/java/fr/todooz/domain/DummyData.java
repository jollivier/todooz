package fr.todooz.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DummyData {
	public static List<Task> tasks() {
		final int TASK_COUNT = 10;

		List<Task> tasks = new ArrayList<Task>();

		for (int i = 0; i < TASK_COUNT; i++) {
			tasks.add(task());
		}

		return tasks;
	}

	private static Task task() {
		Task task = new Task();

		task.setDate(new Date());
		task.setTitle("Read Effective Java");
		task.setText("Read Effective Java before it's too late");
		task.setTags("java,java");

		return task;
	}
}