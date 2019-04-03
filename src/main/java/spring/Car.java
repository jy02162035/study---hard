package spring;

import org.springframework.stereotype.Component;

@Component
public class Car {
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		System.out.println("***************set brand....  " + brand);
		this.brand = brand;
	}

	public Car(String brand) {
		System.out.println("***************paramter Constructor...");
		this.brand = brand;
	}

	public Car() {
		System.out.println("***************default Constructor...");
	}
	

	public void initCar() {
		System.out.println("***************initCar...");
	}
	
	public void destroyCar() {
		System.out.println("***************destroyCar...");
	}
}
