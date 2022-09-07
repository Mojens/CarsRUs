package dat3.cars.service;

import dat3.cars.dto.MemberResponse;
import dat3.cars.dto.ReservationRequest;
import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.DelegatingMethodHandle$Holder;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

  ReservationRepository reservationRepository;
  CarRepository carRepository;
  MemberRepository memberRepository;

  public ReservationService(ReservationRepository reservationRepository,
                            CarRepository carRepository,
                            MemberRepository memberRepository) {
    this.reservationRepository = reservationRepository;
    this.memberRepository = memberRepository;
    this.carRepository = carRepository;
  }

  public List<ReservationResponse> findReservations(){
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponse> reservationResponses = reservations.stream().map(reservation -> new ReservationResponse(reservation,false)).toList();
    return reservationResponses;
  }
  public void reserveCar(String userName, int carId, LocalDate date) {
    Member foundMember = memberRepository.findById(userName).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This reservation does not exist"));
    Car foundCar = carRepository.findById(carId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    ;

    if (reservationRepository.existsByCar_IdAndAndRentalDate(carId, date)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation already exist");
    }

    Reservation reservation = Reservation.builder()
        .member(foundMember)
        .car(foundCar)
        .rentalDate(date)
        .build();
    reservationRepository.save(reservation);
  }

  public void editReservation(ReservationRequest reservationRequest,int id){
    Reservation reservation = reservationRepository.findById(id).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This reservation does not exist"));
  if (reservationRequest.getId() != id){
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Kunne ikke rediger i reservationen");
  }
  reservation.setMember(reservationRequest.getMember());
  reservation.setRentalDate(reservationRequest.getRentalDate());
  reservationRepository.save(reservation);
  }

  public ReservationResponse findReservationById(@PathVariable int id) throws Exception{
    Reservation foundReservation = reservationRepository.findById(id).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This reservation does not exist"));
    return new ReservationResponse(foundReservation,false);
  }

  public void deleteById(int id){
    reservationRepository.deleteById(id);
  }

}

