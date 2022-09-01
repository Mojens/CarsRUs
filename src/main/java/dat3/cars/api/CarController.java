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

  //SECURITY ADMIN
  List<CarResponse> getCars(){
    return carService.findCars();
  }

  //SECURITY ADMIN
  @GetMapping("/{id}")
  CarResponse getCarById(@PathVariable int id) throws Exception{
    return carService.findCarByID(id);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  CarResponse addMember(@RequestBody CarRequest body){
    return carService.addCar(body);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.TEXT_HTML_VALUE)
  public String addMember2(@RequestBody CarRequest body) {
    carService.addCar(body);
    return  body.getId() + " was created";
  }

  @PutMapping("/{id}")
  ResponseEntity<Boolean> editMember(@RequestBody CarRequest body, @PathVariable int id){
    carService.editCar(body,id);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }
  @PatchMapping("/bestDiscount/{id}/{value}")
  void setBestDiscountForCar(@PathVariable int id, @PathVariable double value){
    carService.setBestDiscountForCar(id,value);
  }

  @DeleteMapping("/{id}")
  void deleteMemberByUsername(@PathVariable int id) {
    carService.deleteByid(id);
  }



}
