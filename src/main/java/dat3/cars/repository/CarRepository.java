package dat3.cars.repository;

import dat3.cars.entity.Car;
import dat3.cars.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Integer> {

 /* Optional<Car> findCarByIdIfNotAlreadyReserved(int carId, LocalDate dateToReserve);*/

}
