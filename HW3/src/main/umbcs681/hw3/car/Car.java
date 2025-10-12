package umbcs681.hw3.car;

import java.util.List;
public class Car {
	
		private String model, make;
		private int mileage, year,DominationCount ;
		private float price; 
		
		
		public Car(String model, String make, int mileage, int year, float price){
			super();
			this.model = model;
			this.make = make;
			this.mileage = mileage;
			this.year = year;
			this.price = price;
		}


		public String getModel() {
			return model;
		}
		
		public void setModel(String model) {
			this.model = model;
		}
		
		public String getMake() {
			return make;
		}
		
		public void setMake(String make) {
			this.make = make;
		}
		
		public int getMileage() {
			return mileage;
		}
		
		public void setMileage(int mileage) {
			this.mileage = mileage;
		}


		public int getYear() {
			return year;
		}
		
		public void setYear(int year) {
			this.year = year;
		}
		
		public float getPrice() {
			return price;
		}
		
		public void setPrice(float price) {
			this.price = price;
		}
		
		private boolean dominates( Car b) {

		    return (this.getPrice() <= b.getPrice()) && (this.getYear() >= b.getYear()) && (this.getMileage() <= b.getMileage()) &&
		           ((this.getPrice() < b.getPrice())||(this.getYear() > b.getYear())||(this.getMileage() < b.getMileage()));
		}
		
		public void setDominationCount(List<Car> cars){

			int count = 0 ;
		    for (Car other : cars) {
		        if ( other.dominates(this)){
		            count++;
		        }
		    }
		    this.DominationCount = count;
		}
		
		public int getDominationCount() {
			return DominationCount;
		}
		

}
