package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
	private static int idCounter = 1;

	private int id;
	private String title;
	private LocalDateTime deadline;
	private String remindTiming;
	private String status;
	private String priority;

	public Task(String title, LocalDateTime deadline, String remindTiming, String status, String priority) {
		this.id = idCounter++;
		this.title = title;
		this.deadline = deadline;
		this.remindTiming = remindTiming;
		this.status = status;
		this.priority = priority;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		String deadlineStr = "未定";
		if (deadline != null) {
			deadlineStr = deadline.format(dtf);
		}

		return "[ID: " + id + "] " + title + " | 期限: " + deadlineStr + " | 状態: " + status + " | 優先度: " + priority
				+ " | 事前通知: " + remindTiming;
	}
}