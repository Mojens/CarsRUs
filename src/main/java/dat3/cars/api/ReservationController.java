package dat3.cars.api;

import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Reservation;
import dat3.cars.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/reservation")
public class ReservationController {

  ReservationService reservationService;

  public ReservationController(ReservationService reservationService) {
    this.reservationService = reservationService;
  }

  @PostMapping("/{memberId}/{carId}/{date}")
  public void makeReservation(@PathVariable String memberId , @PathVariable int carId, @PathVariable String date) {
    //date is assumed to come in as a string formatted like: day-month-year
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate reservationDate = LocalDate.parse(date,formatter);
    reservationService.reserveCar(memberId,carId,reservationDate);
  }

  /*
  @PostMapping()
  public void makeReservation(@RequestBody ReservationRequest request) {
    //date is assumed to come in as a string formatted like: day-month-year
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
    LocalDate reservationDate = LocalDate.parse(request.getDate(),formatter);
    reservationService.reserveCar(request.getMember_id(),request.getCar_id(),reservationDate);
  }
   */

  @GetMapping
  List<ReservationResponse> getReservations(){
    return reservationService.getReservations();
  }

  @GetMapping("/{reservationId}")
  ReservationResponse getReservationById(@PathVariable int reservationId) throws Exception{
    return reservationService.findReservationById(reservationId);
  }


  @PutMapping("/{reservationId}")
  ResponseEntity<Boolean> editReservation(@RequestBody ReservationRequest reservationRequest,@PathVariable int reservationId){
    reservationService.editReservation(reservationRequest,reservationId);
    return new ResponseEntity<>(true, HttpStatus.OK);
  }

  @DeleteMapping("/{reservationId}")
  void deleteReservationByID(@PathVariable int reservationId){
    reservationService.deleteById(reservationId);
  }
}
