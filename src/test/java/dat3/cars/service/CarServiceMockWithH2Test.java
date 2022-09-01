package dat3.cars.service;

import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarServiceMockWithH2Test {

  public CarService carService;

  public static CarRepository carRepository;

  @BeforeAll
  public static void setupData(@Autowired CarRepository car_Repository){
    carRepository = car_Repository;
    List<Car> carList = List.of(
        Car.builder()
            .brand("Volvo")
            .model("V70")
            .pricePrDay(700)
            .bestDiscount(30.0)
            .build(),
        Car.builder()
            .brand("Bmw")
            .model("x5")
            .pricePrDay(800)
            .bestDiscount(25.5)
            .build(),
        Car.builder()
            .brand("Opel")
            .model("Corsa")
            .pricePrDay(400)
            .bestDiscount(10.4)
            .build()
    );
    carRepository.saveAll(carList);
  }

  @BeforeEach
  public void setMemberService(){
    carService = new CarService(carRepository);
  }


  @Test
  void findCars() {
  }

  @Test
  void setBestDiscountForCar() {
  }

  @Test
  void deleteByid() {
  }

  @Test
  void addCar() {
  }

  @Test
  void editCar() {
  }

  @Test
  void findCarByID() {
  }
}