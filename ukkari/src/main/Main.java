package main;

import java.time.LocalDateTime;

import model.Task;
import service.TaskService;
import util.InputUtil;

public class Main {
	private static TaskService taskService = new TaskService();

	public static void main(String[] args) {
		System.out.println("                        ");
		System.out.println("        うっかり忘れ防止アプリ");

		while (true) {
			System.out.println();
			System.out.println("1. 予定追加");
			System.out.println("2. 一覧表示");
			System.out.println("3. 状態変更");
			System.out.println("4. 予定検索");
			System.out.println("5. 予定削除");
			System.out.println("0. 終了");

			int choice = InputUtil.readInt("メニューを選択してください: ");

			switch (choice) {
			case 1:
				addNewTask();
				break;
			case 2:
				taskService.showAllTasks();
				break;
			case 3:
				changeStatus();
				break;
			case 4:
				searchTask();
				break;
			case 5:
				deleteTask();
				break;
			case 0:
				System.out.println("アプリを終了します。");
				return;
			default:
				System.out.println("正しい番号を入力してください。");
				break;
			}
		}
	}

	private static void addNewTask() {
		System.out.println("\n--- 予定追加 (0入力でメニューに戻る) ---");
		String title = InputUtil.readString("予定名: ");
		if (title.equals("0")) {
			System.out.println("メニューに戻ります。");
			return;
		}

		LocalDateTime deadline = InputUtil.readDateTime("期限");

		String remindTiming = "";
		while (true) {
			remindTiming = InputUtil.readString("事前通知のタイミング (例: 1日前, 30分前): ");
			if (remindTiming.equals("0")) {
				System.out.println("メニューに戻ります。");
				return;
			}
			if (remindTiming.contains("分前") || remindTiming.contains("時間前") || remindTiming.contains("日前")) {
				break;
			}
			System.out.println("「分前」や「時間前」などを付けて入力してください。");
		}

		String status = InputUtil.readString("初期状態 (未着手 / 進行中 / 完了): ");
		if (status.equals("0")) {
			System.out.println("メニューに戻ります。");
			return;
		}
		if (status.equals("")) {
			status = "未着手";
		}

		String priority = InputUtil.readString("優先度 (高 / 中 / 低): ");
		if (priority.equals("0")) {
			System.out.println("メニューに戻ります。");
			return;
		}

		Task newTask = new Task(title, deadline, remindTiming, status, priority);
		taskService.addTask(newTask);
	}

	private static void changeStatus() {
		System.out.println("\n--- 状態変更 (0入力でメニューに戻る) ---");
		if (taskService.isEmpty()) {
			System.out.println("予定がありません。");
			return;
		}
		taskService.showAllTasks();
		int id = InputUtil.readInt("状態を変える予定のIDを入力: ");
		if (id == 0) {
			System.out.println("メニューに戻ります。");
			return;
		}

		String status = InputUtil.readString("新しい状態 (未着手 / 進行中 / 完了): ");
		if (status.equals("0")) {
			System.out.println("メニューに戻ります。");
			return;
		}

		taskService.updateTaskStatus(id, status);
	}

	private static void searchTask() {
		System.out.println("\n--- 検索 (0入力でメニューに戻る) ---");
		String keyword = InputUtil.readString("検索キーワード: ");
		if (keyword.equals("0")) {
			System.out.println("メニューに戻ります。");
			return;
		}

		taskService.searchTasks(keyword);
	}

	private static void deleteTask() {
		System.out.println("\n--- 削除 (0入力でメニューに戻る) ---");
		if (taskService.isEmpty()) {
			System.out.println("予定がありません。");
			return;
		}
		taskService.showAllTasks();
		int id = InputUtil.readInt("削除する予定のIDを入力: ");
		if (id == 0) {
			System.out.println("メニューに戻ります。");
			return;
		}

		taskService.deleteTask(id);
	}
}