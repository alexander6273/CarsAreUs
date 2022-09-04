package dat3.cars.api;

import dat3.cars.dto.CarRequest;
import dat3.cars.dto.CarResponse;
import dat3.cars.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController
{
    CarService carService;

    public CarController(CarService carService)
    {
        this.carService = carService;
    }

    @GetMapping
    List<CarResponse> getCars(){
        return carService.findCars();
    }

    @GetMapping(path = "/{id}")
    CarResponse getCarById(@PathVariable Integer id) throws Exception{
        return carService.findCarById(id);
    }

    @PostMapping
    CarResponse addCar(@RequestBody CarRequest body){
        CarResponse carResponse = carService.addCar(body);
        return carResponse;
    }

    @PutMapping("/{id}")
    ResponseEntity<Boolean> editCar(@RequestBody CarRequest body, @PathVariable Integer id){
        return null;
    }

    @PatchMapping("/pricePrDay/{id}/{value}")
    void setPricePrDayForCar(@PathVariable Integer id, @PathVariable int value){
    }

    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable Integer id){

    }
}
