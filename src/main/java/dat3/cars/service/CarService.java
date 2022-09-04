package dat3.cars.service;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.entity.Car;
import dat3.cars.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService
{
    CarRepository carRepository;

    public CarService(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    public List<CarResponse> findCars(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> responses = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
        return responses;
    }

    public CarResponse addCar(CarRequest carRequest){
        //Later you should add error checks --> Missing arguments, email taken etc.

        Car newCar = CarRequest.getCarEntity(carRequest);
        newCar = carRepository.save(newCar);

        return new CarResponse(newCar, false);
    }

    public CarResponse findCarById(Integer id) throws Exception{
        Car found = carRepository.findById(id).orElseThrow(()->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        return new CarResponse(found, false);
    }

    public List<CarResponse> getCars(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> responses = cars.stream().map(car -> new CarResponse(car, false)).collect(Collectors.toList());
        return responses;
    }

}
