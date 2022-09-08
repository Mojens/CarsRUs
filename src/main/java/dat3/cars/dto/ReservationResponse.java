package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {

  private int id;

  private String member_name;

  private int carId;

  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
  private LocalDateTime reservationDate;

  @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
  private LocalDate rentalDate;


  public ReservationResponse(Reservation reservation, boolean includeAll){
    this.id = reservation.getId();
    this.reservationDate = reservation.getReservationDate();
    this.rentalDate = reservation.getRentalDate();
    if (includeAll){
      this.member_name = reservation.getMember().getUsername();
      this.carId = reservation.getCar().getId();
    }
  }

  public ReservationResponse(Reservation reservation) {

  }
}
