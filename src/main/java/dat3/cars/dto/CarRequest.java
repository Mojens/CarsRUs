package dat3.cars.dto;

import dat3.cars.entity.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class CarRequest {

  int id;
  String brand;
  String model;
  double pricePrDay;
  double bestDiscount;
  @CreationTimestamp
  LocalDateTime created;

  @UpdateTimestamp
  LocalDateTime edited;

  public static Car getCarEntity(CarRequest carRequest){
      Car c  = Car.builder()
          .id(carRequest.id)
          .brand(carRequest.brand)
          .model(carRequest.model)
          .pricePrDay(carRequest.pricePrDay)
          .bestDiscount(carRequest.bestDiscount)
          .created(carRequest.created)
          .edited(carRequest.edited)
          .build();
      return c;
  }


  public CarRequest(Car c){
    this.id = c.getId();
    this.brand = c.getBrand();
    this.model = c.getModel();
    this.pricePrDay = c.getPricePrDay();
    this.bestDiscount = c.getBestDiscount();
    this.created = c.getCreated();
    this.edited = c.getEdited();
  }
}
