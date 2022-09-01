package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
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
    List<CarResponse> carResponses = carService.findCars();
    //checker om det er den rigtige antal elementer i listen
    assertEquals(3,carResponses.size());
    //Checker om de rigtige brands er der
    assertThat(carResponses, containsInAnyOrder(hasProperty("brand", is("Opel")), hasProperty("brand", is("Volvo")),hasProperty("brand", is("Bmw"))));
  }

  @Test
  void setBestDiscountForCar() {
    Car car = carRepository.findAll().get(0);
    assertEquals(30.0,car.getBestDiscount());
    carService.setBestDiscountForCar(1,40);
    assertEquals(40.0,car.getBestDiscount());
  }

  @Test
  void deleteByid() {
    assertEquals(3,carRepository.findAll().size());
    carService.deleteByid(1);
    assertEquals(2,carService.findCars().size());
  }

  @Test
  void addCar() {
    Car newCar = Car.builder()
        .brand("Mercedes")
        .model("C63 AMG")
        .pricePrDay(1500)
        .bestDiscount(3.4)
        .build();
    carRepository.save(newCar);
    assertEquals(4,newCar.getId());
    assertEquals("Mercedes",carRepository.findAll().get(3).getBrand());
    assertEquals(4,carRepository.count());
  }

  @Test
  void editCarBrandModel() {
    //Skifter kun brand og model
    assertEquals("Bmw",carRepository.findAll().get(1).getBrand());
    Car changesCar = Car.builder()
        .id(2)
        .brand("Mercedes")
        .model("C63 AMG")
        .pricePrDay(800)
        .bestDiscount(25.5)
        .build();
    CarRequest carRequest = new CarRequest(changesCar);
    carService.editCar(carRequest,2);
    assertEquals("Mercedes",carRepository.findAll().get(1).getBrand());

  }

  @Test
  void editCarPricePrDay() {
    //Skifter kun pris
    assertEquals(700,carRepository.findAll().get(0).getPricePrDay());
    Car priceChanges = Car.builder()
        .id(1)
        .brand("Volvo")
        .model("V70")
        .pricePrDay(1200)
        .bestDiscount(30.0)
        .build();
    CarRequest carRequest1 = new CarRequest(priceChanges);
    carService.editCar(carRequest1,1);
    assertEquals(1200,carRepository.findAll().get(0).getPricePrDay());

  }

  @Test
  void findCarByID() throws Exception {
    CarResponse carResponse = carService.findCarByID(1);
    assertEquals(1,carResponse.getId());
    assertEquals("Volvo",carResponse.getBrand());
  }
}