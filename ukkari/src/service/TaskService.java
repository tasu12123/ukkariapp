package service;

import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TaskService {
	private List<Task> taskList;

	public TaskService() {
		taskList = new ArrayList<>();
	}

	public void addTask(Task task) {
		taskList.add(task);
		System.out.println("予定を追加しました: " + task.getTitle());
	}

	public void deleteTask(int id) {
		int targetIndex = -1;
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getId() == id) {
				targetIndex = i;
				break;
			}
		}
		if (targetIndex != -1) {
			taskList.remove(targetIndex);
			System.out.println("削除しました。");
		} else {
			System.out.println("指定されたIDの予定が見つかりません。");
		}
	}

	public void showAllTasks() {
		if (taskList.size() == 0) {
			System.out.println("予定がありません。");
			return;
		}
		System.out.println("--- 予定一覧 ---");
		for (Task task : taskList) {
			System.out.println(task.toString());
		}
	}

	public void searchTasks(String keyword) {
		boolean found = false;
		System.out.println("--- 検索結果 ---");
		for (Task task : taskList) {
			if (task.getTitle().indexOf(keyword) != -1) {
				System.out.println(task.toString());
				found = true;
			}
		}
		if (!found) {
			System.out.println("該当する予定が見つかりませんでした。");
		}
	}

	public void updateTaskStatus(int id, String newStatus) {
		for (Task task : taskList) {
			if (task.getId() == id) {
				task.setStatus(newStatus);
				System.out.println("状態を「" + newStatus + "」に変更しました。");
				return;
			}
		}
		System.out.println("指定されたIDの予定が見つかりません。");
	}

	public boolean isEmpty() {
		return taskList.isEmpty();
	}
}