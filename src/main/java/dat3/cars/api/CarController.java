package dat3.cars.api;

import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

  CarService carService;

  public CarController(CarService carService){
    this.carService = carService;

  }

  //SECURITY ADMIN
  List<CarResponse> getCars(){
    return carService.findCars();
  }

  //SECURITY ADMIN
  @GetMapping("/{id}")
  CarResponse getCarById(@PathVariable int id) throws Exception{
    return carService.findCarByID(id);
  }

  



}
