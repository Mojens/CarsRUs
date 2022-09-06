package dat3.cars.service;

import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.invoke.DelegatingMethodHandle$Holder;
import java.time.LocalDate;

@Service
public class ReservationService {

  ReservationRepository reservationRepository;
  CarRepository carRepository;
  MemberRepository memberRepository;

  public ReservationService(ReservationRepository reservationRepository,
                            CarRepository carRepository,
                            MemberRepository memberRepository){
    this.reservationRepository = reservationRepository;
    this.memberRepository = memberRepository;
    this.carRepository = carRepository;
  }

  public void reserveCar(String userName, int carId, LocalDate date){
    Member foundMember = memberRepository.findById(userName).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    Car foundCar = carRepository.findById(carId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Car not found"));;

   if (reservationRepository.existsByCar_IdAndAndRentalDate(carId,date)){
     throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Reservation already exist");
   }

    Reservation reservation = Reservation.builder()
        .member(foundMember)
        .car(foundCar)
        .rentalDate(date)
        .build();
    reservationRepository.save(reservation);
  }

}
