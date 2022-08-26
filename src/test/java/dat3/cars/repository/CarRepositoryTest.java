package dat3.cars.repository;

import dat3.cars.entity.Car;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

  @Autowired
  CarRepository carRepository;

  static String car1Model;
  static String car2Model;

  @BeforeAll
  public static void setUpData(@Autowired CarRepository carRepository){
    Car car1 = new Car("bmw","x5",10,5);
    Car car2 = new Car("opel","corsa",5,2.5);

    carRepository.save(car1);
    car1Model = car1.getModel();
    carRepository.save(car2);
    car2Model = car2.getModel();
  }

  @Test
  public void testFindByID(){
    Car foundCar = carRepository.findById(car1Model).get();
    assertEquals("x5",foundCar.getModel());
  }

  @Test
  public void testModelExist(){
    boolean isItThere = carRepository.existsById("x5");
    assertTrue(isItThere);
  }


}