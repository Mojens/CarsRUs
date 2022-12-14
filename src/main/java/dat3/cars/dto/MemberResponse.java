package dat3.cars.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.cars.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
  @Setter
  @NoArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class MemberResponse {
    private String username; //Remember this is the primary key
    private String email;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;

    private List<ReservationResponse> reservationResponses;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime created;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime edited;
    private Integer ranking;

    //Convert Member Entity to Member DTO
    public MemberResponse(Member m, boolean includeAll) {
      this.username = m.getUsername();
      this.email = m.getEmail();
      this.street = m.getStreet();
      this.firstName = m.getFirstName();
      this.lastName = m.getLastName();
      this.city = m.getCity();
      this.zip = m.getZip();
      if(includeAll){
        this.created = m.getCreated();
        this.edited = m.getEdited();
        this.ranking = m.getRanking();
      }
      if(m.getReservations().size()>0){
        reservationResponses = m.getReservations().stream().map(r->ReservationResponse.builder()
            .id(r.getId())
            .carId(r.getCar().getId())
            .carBrand(r.getCar().getBrand())
            .rentalDate(r.getRentalDate())
            .build()
        ).collect(Collectors.toList());
      }
    }
  }

