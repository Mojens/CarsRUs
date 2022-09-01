package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CarService {


  CarRepository carRepository;

  public CarService(CarRepository carRepository) {
    this.carRepository = carRepository;
  }

  public List<CarResponse> findCars() {
    List<Car> cars = carRepository.findAll();
    List<CarResponse> carResponses = cars.stream().map(car -> new CarResponse(car, false)).toList();
    return carResponses;
  }

  public void setBestDiscountForCar(int id, double value) {
    Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id already exist"));
    car.setBestDiscount(value);
    carRepository.save(car);
  }

  public void deleteByid(int id) {
    carRepository.deleteById(id);
  }

  public CarResponse addCar(CarRequest carRequest) {
    if (carRepository.existsById(carRequest.getId())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this ID already exist");
    }
    Car newCar = CarRequest.getCarEntity(carRequest);
    newCar = carRepository.save(newCar);
    return new CarResponse(newCar, true);
  }

  public void editCar(CarRequest carRequest, int id) {
    Car car = carRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id does not exist"));
    if (carRequest.getId() != id) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot change car");
    }
    car.setBrand(carRequest.getBrand());
    car.setModel(carRequest.getModel());
    car.setPricePrDay(carRequest.getPricePrDay());
    carRepository.save(car);
  }

  public CarResponse findCarByID(@PathVariable int id) throws Exception{
    Car foundCar = carRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"car not found"));
    return new CarResponse(foundCar,false);
  }




}
