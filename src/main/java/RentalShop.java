package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RentalShop {
	private static final String USERS_FILE_PATH = "./users.csv";
	private static final String CARS_FILE_PATH = "./cars.csv";
	private Map<String, Car> mapOfCars;
	private Map<Integer, User> mapOfUsers;
	private DataRetriver dataRetriver;

	public RentalShop(DataRetriver dataRetriver) {
		super();
		this.dataRetriver = dataRetriver;
		mapOfUsers = this.dataRetriver.readUsersFromFile(USERS_FILE_PATH);
		mapOfCars = this.dataRetriver.readCarsFromFile(CARS_FILE_PATH);
	}

	public void showRentedCars() {

		for (String plateNo : mapOfCars.keySet()) {
			Car car = mapOfCars.get(plateNo);
			if (car.getRentedBy() != null) {
				System.out.println(car.toString());
			}
		}
	}

	public void showUsers() {

		int i = 0;
		for (User user : mapOfUsers.values()) {
			System.out.println("User nr." + i++);
			System.out.println(user.toString());
		}
	}

	public boolean returnCar(String carNumber) {

		if (mapOfCars.get(carNumber).getRentedBy() != null) {
			mapOfCars.get(carNumber).setRentedBy(null);
			return true;
		}
		return false;
	}

	public void showAvailableCars(int sortOrder) {

		List<Car> availableCars = findAvailableCars(this.mapOfCars);
		Collections.sort(availableCars, new Comparator<Car>() {
			@Override
			public int compare(Car c1, Car c2) {

				return (sortOrder == 1) ? c1.getDailyRate().compareTo(c2.getDailyRate())
						: c2.getDailyRate().compareTo(c1.getDailyRate());
			}
		});
		showList(availableCars);
	}

	private List<Car> findAvailableCars(Map<String, Car> mapOfAllCars) {

		List<Car> result = new ArrayList<>();
		for (String plateNo : mapOfAllCars.keySet()) {
			Car car = mapOfAllCars.get(plateNo);
			if (car.getRentedBy() == null) {
				result.add(car);
			}
		}
		return result;
	}

	public void showCarsByModel() {

		List<Car> cars = moveCarsToList(mapOfCars);
		Collections.sort(cars, new Comparator<Car>() {
			@Override
			public int compare(Car c1, Car c2) {

				return c1.getModel().compareTo(c2.getModel());
			}
		});
		showList(cars);
	}

	private List<Car> moveCarsToList(Map<String, Car> mapOfAllCars) {

		List<Car> result = new ArrayList<>();
		for (String plateNo : mapOfAllCars.keySet()) {
			Car car = mapOfAllCars.get(plateNo);
			result.add(car);
		}
		return result;
	}

	private void showList(List<Car> list) {

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).toString());
			Integer rentedBy = list.get(i).getRentedBy();
			if (rentedBy != null) {
				System.out.println(findUser(rentedBy));
			}
		}
	}

	private String findUser(Integer rentedBy) {

		return mapOfUsers.get(rentedBy).toString();
	}

	public boolean addUser(User user) {

		if (mapOfUsers.values().contains(user)) {
			return false;
		}
		mapOfUsers.put(User.nextId++, user);
		return true;
	}

	public void writeUsersToFile() {

		this.dataRetriver.writeUsersToFile(USERS_FILE_PATH, mapOfUsers);
	}

	public void writeCarsToFile() {

		this.dataRetriver.writeCarsToFile(CARS_FILE_PATH, mapOfCars);
	}

	public boolean rentCar(String carNumber, int userId) {

		if (mapOfUsers.containsKey(userId)) {
			mapOfCars.get(carNumber).setRentedBy(userId);
			return true;
		}
		return false;
	}
}
