package dat3.cars.service;

import dat3.cars.dto.ReservationResponse;
import dat3.cars.entity.Car;
import dat3.cars.entity.Member;
import dat3.cars.entity.Reservation;
import dat3.cars.repository.CarRepository;
import dat3.cars.repository.MemberRepository;
import dat3.cars.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService
{
    ReservationRepository reservationRepository;
    MemberRepository memberRepository;
    CarRepository carRepository;

    public List<ReservationResponse> findReservations(){
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationResponse> responses = reservations.stream().map( reservation -> new ReservationResponse(reservation, false)).collect(Collectors.toList());
        return responses;
    }

    public ReservationResponse findReservationById(int id) throws Exception{
        Reservation found = reservationRepository.findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
        return new ReservationResponse(found,false);
    }

    public void reserveCar(String username, int carId, LocalDate rentalDate){
        Member member = memberRepository.findById(username).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST, "user not found"));
        Car car = carRepository.findById(carId).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"car not found"));

        if (reservationRepository.existsByCar_IdAndRentalDate(carId, rentalDate)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"car already reserved on this day");
        }

        Reservation reservation = new Reservation(member, car, rentalDate);
        reservationRepository.save(reservation);
    }
}
