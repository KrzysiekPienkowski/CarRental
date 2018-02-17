package main.java;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {

	DataRetriver dataRetriver = new DataRetriver();
	RentalShop rentalShop = new RentalShop(dataRetriver);

	public void selectInstruction(char instruction, Scanner scanner) {

		switch (instruction) {
		case '1':
			System.out.println("Firstname : ");
			String firstName = scanner.next();
			System.out.println("Lastname : ");
			String lastName = scanner.next();
			System.out.println("Birthdate (yyyy-MM-dd) : ");
			String birthdate = scanner.next();
			LocalDate convertedBirthDate = LocalDate.parse(birthdate);
			if (!rentalShop.addUser(new User(firstName, lastName, convertedBirthDate))) {
				System.out.println("Given user already exists in users set");
			}
			break;
		case '2':
			System.out.println("1 - From lowest daily rate / 2 - from highest daily rate : ");
			int sortOrder = scanner.nextInt();
			rentalShop.showAvailableCars(sortOrder);
			break;
		case '3':
			rentalShop.showCarsByModel();
			break;
		case '4':
			System.out.println("What car do number you want to rent(insert plate No.): ");
			String carNumber = scanner.next();
			System.out.println("Give your customer number: ");
			int customerNumber = scanner.nextInt();
			if (!rentalShop.rentCar(carNumber, customerNumber)) {
				System.out.println("You are not our customer. Please create an account.");
			}

			break;
		case '5':
			System.out.println("Here are rented cars: ");
			rentalShop.showRentedCars();
			System.out.println("What car do you want to return (plate No.): ");
			String carNumberToReturn = scanner.next();
			if (!rentalShop.returnCar(carNumberToReturn)) {
				System.out.println("Given car ID is not on rented list");
			}
			break;
		case '6':
			rentalShop.writeCarsToFile();
			rentalShop.writeUsersToFile();
			break;

		default:
			System.out.println("Command not correct. Possible options 1-6");
			scanner.nextLine();
			return;

		}
	}

}
