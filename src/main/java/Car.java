package main.java;

public class Car {

	private String model;
	private String plateNo;
	private Double dailyRate;
	private Integer rentedBy;

	public Car(String model, String plateNo, Double dailyRate, Integer rentedBy) {
		super();
		this.model = model;
		this.plateNo = plateNo;
		this.dailyRate = dailyRate;
		this.rentedBy = rentedBy;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public Double getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(Double dailyRate) {
		this.dailyRate = dailyRate;
	}

	public Integer getRentedBy() {
		return rentedBy;
	}

	public void setRentedBy(Integer rentedBy) {
		this.rentedBy = rentedBy;
	}

	@Override
	public String toString() {
		return "model=" + model + ",\nplateNo=" + plateNo + ",\ndailyRate=" + dailyRate + "\n";
	}

	public String prepareLine() {
		return Utils.checkNull(model) + "," + Utils.checkNull(plateNo) + "," + Utils.checkNull(dailyRate) + ","
				+ Utils.checkNull(rentedBy) + "\n";
	}

}
