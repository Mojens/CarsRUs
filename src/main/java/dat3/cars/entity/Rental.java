package dat3.cars.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
// Lombok
@Entity
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private LocalDate rentalDate;

  private double pricePrDay;

  @ManyToOne
  private Member member;

  @ManyToOne
  private Car car;


}
