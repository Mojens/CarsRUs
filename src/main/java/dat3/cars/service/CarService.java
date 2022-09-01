package dat3.cars.service;

import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

  CarRepository carRepository;

  public CarService(CarRepository carRepository){
    this.carRepository = carRepository;
  }

  public List<CarResponse> findCars(){
    List<Car> cars = carRepository.findAll();
    List<CarResponse> carResponses = cars.stream().map(car -> new CarResponse(car,false)).toList();
    return carResponses;
  }
  


}
