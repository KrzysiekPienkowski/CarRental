package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DataRetriver {

	private static final String COMMA_SPLITTER = ",";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public Map<String, Car> readCarsFromFile(String filePath) {
		Map<String, Car> mapOfCarsFromFile = new HashMap<>();

		File file = new File(filePath);

		Scanner input;
		try {
			input = new Scanner(file);
			while (input.hasNextLine()) {

				String row = input.nextLine();
				String[] carData = row.split(COMMA_SPLITTER);
				Car newCar = createCar(carData);
				mapOfCarsFromFile.put(newCar.getPlateNo(), newCar);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return mapOfCarsFromFile;
	}

	public Map<Integer, User> readUsersFromFile(String filePath) {
		Map<Integer, User> mapOfUsersFromFile = new LinkedHashMap<>();

		File file = new File(filePath);
		User.nextId = 1;
		Scanner input;
		try {

			input = new Scanner(file);
			while (input.hasNextLine()) {

				String row = input.nextLine();
				String[] userData = row.split(COMMA_SPLITTER);
				User newUser = createUser(userData);
				mapOfUsersFromFile.put(User.nextId++, newUser);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mapOfUsersFromFile;
	}

	public void writeUsersToFile(String filePath, Map<Integer, User> users) {

		try {
			FileWriter writer = new FileWriter(filePath);
			for (User user : users.values()) {
				writer.write(user.prepareLine());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeCarsToFile(String filePath, Map<String, Car> cars) {

		try {
			FileWriter writer = new FileWriter(filePath);
			for (Car car : cars.values()) {
				writer.write(car.prepareLine());
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Car createCar(String[] carData) {
		if (carData.length == 1) {
			return createCar(carData[0], null, null, null);
		} else if (carData.length == 2) {
			return createCar(carData[0], carData[1], null, null);
		} else if (carData.length == 3) {
			return createCar(carData[0], carData[1], carData[2], null);
		} else {
			return createCar(carData[0], carData[1], carData[2], carData[3]);
		}
	}

	private Car createCar(String model, String plateNo, String dailyRate, String rentedBy) {

		Double parseDouble = (dailyRate != null) ? Double.parseDouble(dailyRate) : null;
		Integer parseInt = (rentedBy != null) ? Integer.parseInt(rentedBy) : null;

		return new Car(model, plateNo, parseDouble, parseInt);
	}

	private User createUser(String[] userData) {
		if (userData.length == 1) {
			return createUser(userData[0], null, null);
		} else if (userData.length == 2) {
			return createUser(userData[0], userData[1], null);
		} else {
			return createUser(userData[0], userData[1], userData[2]);
		}
	}

	private User createUser(String firstName, String lastName, String birthDate) {
		if (birthDate != null) {
			return new User(firstName, lastName, LocalDate.parse(birthDate, FORMATTER));
		} else {
			return new User(firstName, lastName, null);
		}
	}

}
