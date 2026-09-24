package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputUtil {
	private static Scanner scanner = new Scanner(System.in);

	public static String readString(String message) {
		System.out.print(message);
		return scanner.nextLine();
	}

	public static int readInt(String message) {
		while (true) {
			System.out.print(message);
			try {
				String str = scanner.nextLine();
				return Integer.parseInt(str);
			} catch (Exception e) {
				System.out.println("数字を入力してください。");
			}
		}
	}

	public static LocalDateTime readDateTime(String message) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		while (true) {
			System.out.print(message + " (例: 2026/10/01 15:30) ※未設定はEnter、戻る場合は0: ");
			String input = scanner.nextLine();
			if (input.equals("")) {
				return null;
			}
			if (input.equals("0")) {
				return null;
			}
			try {
				return LocalDateTime.parse(input, dtf);
			} catch (Exception e) {
				System.out.println("形式が間違っています。");
			}
		}
	}
}