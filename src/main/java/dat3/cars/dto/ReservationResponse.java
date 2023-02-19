package dat3.cars.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import dat3.cars.entity.Reservation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponse {
  int id;
  String memberUsername;
  int carId;
  String carBrand;

  @JsonFormat(pattern = "yyyy-MM-dd",shape = JsonFormat.Shape.STRING)
  private LocalDate rentalDate;

  public ReservationResponse(Reservation r) {
    this.id= r.getId();
    this.memberUsername = r.getMember().getUsername();
    this.carId = r.getCar().getId();
    this.carBrand = r.getCar().getBrand();
    this.rentalDate = r.getRentalDate();
  }
}