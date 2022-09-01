package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

  CarService carService;

  public CarController(CarService carService){
    this.carService = carService;

  }

  //SECURITY ADMIN & USER
  List<CarResponse> getCars(){
    return carService.findCars();
  }

  //SECURITY ADMIN & USER
  @GetMapping("/{id}")
  CarResponse getCarById(@PathVariable int id) throws Exception{
    return carService.findCarByID(id);
  }

  //SECURITY ADMIN
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  CarResponse addCar(@RequestBody CarRequest body){
    return carService.addCar(body);
  }

  //SECURITY ADMIN
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_HTML_VALUE)
  public String addCar2(@RequestBody CarRequest body) {
    carService.addCar(body);
    return  body.getId() + " was created";
  }

  //SECURITY ADMIN
  @PutMapping("/{id}")
  ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable int id){
    carService.editCar(body,id);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  //SECURITY ADMIN
  @PatchMapping("/bestDiscount/{id}/{value}")
  void setBestDiscountForCar(@PathVariable int id, @PathVariable double value){
    carService.setBestDiscountForCar(id,value);
  }

  //SECURITY ADMIN
  @DeleteMapping("/{id}")
  void deleteCarById(@PathVariable int id) {
    carService.deleteByid(id);
  }



}
