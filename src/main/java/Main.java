package main.java;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Menu menu = new Menu();
		Scanner scanner = new Scanner(System.in);
		do {
			showOptions();
			char instruction = scanner.next().charAt(0);
			if (instruction == 'Q') {
				break;
			}
			menu.selectInstruction(instruction, scanner);

		} while (true);
		scanner.close();
	}

	private static void showOptions() {
		System.out.println("1: Add User");
		System.out.println("2: Show available cars");
		System.out.println("3: Show cars by model");
		System.out.println("4: Rent a car");
		System.out.println("5: Return a car");
		System.out.println("6: Save data");
		System.out.println("Q: Quit");
		System.out.println("enter command: ");
	}

}
