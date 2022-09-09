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


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<ReservationResponse> getReservations(){
    List<Reservation> reservations = reservationRepository.findAll();
    List<ReservationResponse> response = reservations.stream().map(ReservationResponse::new).collect(Collectors.toList());
    return response;

  }
  public void reserveCar(String memberId, int carId, LocalDate dateToReserve){
    Member member = memberRepository.findById(memberId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this id found"));
    //Observe in the following, this strategy requires two round trips to the database
    Car car = carRepository.findById(carId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No car with this id found"));
    if(reservationRepository.existsByCarIdAndRentalDate(car.getId(),dateToReserve)){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car is already reserved on this date");
    }
    Reservation reservation = Reservation.builder()
        .member(member)
        .car(car)
        .rentalDate(dateToReserve)
        .build();
    reservationRepository.save(reservation);
  }
  public void reserveCarV2(String memberId, int carId, LocalDate dateToReserve){
    Member member = memberRepository.findById(memberId).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"No member with this id found"));
    //Observe in the following, this strategy requires only ONE round trip to the database
    Car car = carRepository.findCarByIdIfNotAlreadyReserved(carId, dateToReserve).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car is already reserved on this date"));
    Reservation reservation = Reservation.builder()
        .member(member)
        .car(car)
        .rentalDate(dateToReserve)
        .build();
    reservationRepository.save(reservation);
  }
/*
  public void editReservation(ReservationRequest reservationRequest,int id){
    Reservation reservation = reservationRepository.findById(id).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This reservation does not exist"));
  if (reservationRequest.getId() != id){
    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Kunne ikke rediger i reservationen");
  }
  reservation.setMember(reservationRequest.getMember());
  reservation.setRentalDate(reservationRequest.getRentalDate());
  reservationRepository.save(reservation);
  }

 */

  public ReservationResponse findReservationById(@PathVariable int id) throws Exception{
    Reservation foundReservation = reservationRepository.findById(id).orElseThrow(()->  new ResponseStatusException(HttpStatus.BAD_REQUEST,"This reservation does not exist"));
    return new ReservationResponse(foundReservation);
  }

  public void deleteById(int id){
    reservationRepository.deleteById(id);
  }

}

