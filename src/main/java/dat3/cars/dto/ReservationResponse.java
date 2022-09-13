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
public class ReservationResponse
{
    private int id;
    private CarResponse carResponse;
    private MemberResponse memberResponse;

    private int carId;
    /*private Member member;
    private Car car;*/
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",shape = JsonFormat.Shape.STRING)
    private LocalDateTime reservationDate;
    @JsonFormat(pattern = "dd-MM-yyyy",shape = JsonFormat.Shape.STRING)
    private LocalDate rentalDate;

    public ReservationResponse(Reservation r, boolean includeAll)
    {
        this.id = r.getId();
        this.reservationDate = r.getReservationDate();
        this.rentalDate = r.getRentalDate();
        this.carId = r.getCar().getId();
        //this.carResponse = new CarResponse(r.getCar(),false);
        //this.memberResponse = new MemberResponse(r.getMember(),false);
        /*this.member = r.getMember();
        this.car = r.getCar();*/
    }
}
