package main.java;

import java.time.LocalDate;

public class User {

	public static int nextId = 1;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;

	public User(String firstName, String lastName, LocalDate birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String prepareLine() {
		return Utils.checkNull(firstName) + "," + Utils.checkNull(lastName) + "," + Utils.checkNull(birthDate) + "\n";
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate;
	}

}
