package id.my.hendisantika.springbootreactivewebmongodb.service;

import id.my.hendisantika.springbootreactivewebmongodb.model.Car;
import id.my.hendisantika.springbootreactivewebmongodb.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-Boot-Reactive-Web-MongoDB
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/5/24
 * Time: 09:01
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Flux<Car> all() {
        return carRepository.findAll();
    }


}
