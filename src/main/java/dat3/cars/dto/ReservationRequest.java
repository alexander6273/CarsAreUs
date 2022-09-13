package dat3.cars.dto;

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
public class ReservationRequest
{
    private int id;
    private Member member;
    private Car car;
    private LocalDateTime reservationDate;
    private LocalDate rentalDate;

    public static Reservation getReservationEntity(ReservationRequest r){
        return new Reservation(r.getId(), r.getMember(), r.getCar(), r.getReservationDate(), r.getRentalDate());
    }

    public ReservationRequest(Reservation r){
        this.id = r.getId();
        this.member = r.getMember();
        this.car = r.getCar();
        this.reservationDate = r.getReservationDate();
        this.rentalDate = r.getRentalDate();
    }
}
