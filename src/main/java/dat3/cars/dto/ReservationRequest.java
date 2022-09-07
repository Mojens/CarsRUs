package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationRequest {

  private int id;

  private Member member;

  private Car car;

  private LocalDateTime reservationDate;

  private LocalDate rentalDate;

  public static Reservation getReservationEntity(ReservationRequest reservationRequest){
    Reservation r = Reservation.builder()
        .member(reservationRequest.getMember())
        .car(reservationRequest.getCar())
        .reservationDate(reservationRequest.getReservationDate())
        .rentalDate(reservationRequest.getRentalDate())
        .build();
    return r;
  }

  public ReservationRequest(Reservation reservation){
    this.id = reservation.getId();
    this.member = reservation.getMember();
    this.car = reservation.getCar();
    this.rentalDate = reservation.getRentalDate();
  }

}
