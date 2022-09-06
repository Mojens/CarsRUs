package dat3.cars.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode  // When performance becomes important, never set like this

@Entity
public class Car {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  @Column(length = 50,nullable = false)
  private String brand;

  @Column(length= 50, nullable = false)
  private String model;

  double pricePrDay;

  //Best discount price (percent for pricePrDay) an admin can offer
  double bestDiscount;

  @CreationTimestamp
  LocalDateTime created;

  @UpdateTimestamp
  LocalDateTime edited;

  @OneToMany(mappedBy = "car" , cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();
  public void addReservation(Reservation reservation){
    reservations.add(reservation);
    reservation.setCar(this);
  }

  @OneToMany(mappedBy = "car" , cascade = CascadeType.ALL)
  private List<Rental> rentals = new ArrayList<>();

  public void addRental(Rental rental){
    rentals.add(rental);
    rental.setCar(this);
  }



}