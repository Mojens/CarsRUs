package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CarServiceMockitoTest {

  @Mock
  CarRepository carRepository;

  @Autowired
  CarService carService;

  @BeforeEach
  public void setup(){
    carService = new CarService(carRepository);
  }
  @Test
  void findCars() {
    Mockito.when(carRepository.findAll()).thenReturn(List.of(
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
    ));
    List<CarResponse> carResponses = carService.findCars();
    assertEquals(3,carResponses.size());
  }

  @Test
  void setBestDiscountForCar() throws Exception {
    Car car = Car.builder()
        .id(1)
        .brand("BMW")
        .model("X5")
        .pricePrDay(700)
        .bestDiscount(55.55)
        .build();

    Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(car));
    CarResponse carResponse1 = carService.findCarByID(1);
    System.out.println("Gamle discount pris: "+carResponse1.getBestDiscount());
    assertEquals(55.55,carResponse1.getBestDiscount());

    carService.setBestDiscountForCar(carResponse1.getId(),25.55);
    Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(car));
    CarResponse carResponse2 = carService.findCarByID(1);
    assertEquals(25.55,carResponse2.getBestDiscount());
    System.out.println("Nye discount pris: "+carResponse2.getBestDiscount());

    /*
    Huske at bruge 'Mockito.when(carRepository.findById(1)).thenReturn(Optional.of(car));'
    Da man skal ind og få en ny response efter man har ændret den igennem.
    'carService.setBestDiscountForCar(carResponse1.getId(),25.55);'
     */

  }

  @Test
  void deleteByid() {
    Car newCar = Car.builder()
        .id(4)
        .brand("Ford")
        .model("EX")
        .pricePrDay(400)
        .bestDiscount(25.3)
        .build();
    Mockito.when(carRepository.save(any(Car.class))).thenReturn(newCar);
    CarRequest carRequest = new CarRequest(newCar);
    CarResponse carResponse = carService.addCar(carRequest);
    carService.deleteByid(carResponse.getId());
    assertFalse(carRepository.existsById(carResponse.getId()));
  }

  @Test
  void addCar() {
   Car newCar = Car.builder()
        .id(4)
        .brand("Ford")
        .model("EX")
        .pricePrDay(400)
        .bestDiscount(25.3)
        .build();
   Mockito.when(carRepository.save(any(Car.class))).thenReturn(newCar);
    CarRequest carRequest = new CarRequest(newCar);
    CarResponse carResponse = carService.addCar(carRequest);
    assertEquals("Ford",carResponse.getBrand());

  }

  @Test
  void editCar() throws Exception {
    Car newCar1 = Car.builder()
        .id(4)
        .brand("Ford")
        .model("EX")
        .pricePrDay(400)
        .bestDiscount(25.3)
        .build();
    Mockito.when(carRepository.findById(4)).thenReturn(Optional.of(newCar1));

    //Test carService with mocked repository
    CarResponse carResponse1 = carService.findCarByID(4);
    System.out.println("old car brand: "+carResponse1.getBrand());
    assertEquals(newCar1.getBrand(),carResponse1.getBrand());

    Car newCar2 = Car.builder()
        .id(4)
        .brand("BMW")
        .model("X5")
        .pricePrDay(600)
        .bestDiscount(40.0)
        .build();
    CarRequest carRequest2 = new CarRequest(newCar2);
    carService.editCar(carRequest2,carResponse1.getId());
    Mockito.when(carRepository.findById(4)).thenReturn(Optional.of(newCar2));

    //Test carService with mocked repository
    CarResponse carResponse2 = carService.findCarByID(4);
    System.out.println("new car brand: "+carResponse2.getBrand());
    assertEquals(newCar2.getBrand(),carResponse2.getBrand());


  }

  @Test
  void findCarByID() throws Exception{
    //Setup carRepository with mock data
    Car newCar = Car.builder()
        .id(4)
        .brand("Ford")
        .model("EX")
        .pricePrDay(400)
        .bestDiscount(25.3)
        .build();
    Mockito.when(carRepository.findById(4)).thenReturn(Optional.of(newCar));

    //Test carService with mocked repository
    CarResponse carResponse = carService.findCarByID(4);
    assertEquals("EX",carResponse.getModel());
  }
}