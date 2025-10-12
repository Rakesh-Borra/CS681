package umbcs681.hw3.car;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import umbcs681.hw3.car.Car;

class CarTest{

	private static List<Car> carList;

    @BeforeAll
    static void setUp(){
        carList = new ArrayList<>();
        carList.add(new Car("Civic", "Honda", 5000, 2025, 22000));
        carList.add(new Car("Corolla", "Toyota", 8000, 2022, 21000));
        carList.add(new Car("Accord", "Honda", 12000, 2024, 24000));
        carList.add(new Car("Camry", "Toyota", 10000, 2019, 23000));
        carList.add(new Car("Altima", "Nissan", 15000, 2020, 20000));
    }


    @Test
    public void check_low_and_high_price_of_car(){
    	float expected_min =20000;
    	float expected_max =24000;
    	float actual_min = carList.stream().map(car -> car.getPrice())
    			.reduce(Float.MAX_VALUE, (current_max_value, new_price) -> current_max_value < new_price ? current_max_value : new_price);
    	assertEquals(actual_min, expected_min);
    	float actual_max = carList.stream().map(car -> car.getPrice())
    			.reduce(Float.MIN_VALUE, (current_min_value, new_price) -> current_min_value > new_price ? current_min_value : new_price);
    	assertEquals(actual_max, expected_max);
    }
    
    @Test
    public void check_average_price_of_car(){
    	float expected_avg =22000;
    	double actual_avg = carList.stream().map(car->car.getPrice()).reduce(0.0, (res, price) -> { res =res+ price; return res; },
    		        (finalResult, intermediateResult) -> finalResult) / (double) carList.size();
    	assertEquals(expected_avg,actual_avg);
    }

    
}
