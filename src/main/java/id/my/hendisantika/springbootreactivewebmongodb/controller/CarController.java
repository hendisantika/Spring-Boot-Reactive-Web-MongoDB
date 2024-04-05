package id.my.hendisantika.springbootreactivewebmongodb.controller;

import id.my.hendisantika.springbootreactivewebmongodb.model.Car;
import id.my.hendisantika.springbootreactivewebmongodb.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-Boot-Reactive-Web-MongoDB
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 4/5/24
 * Time: 09:03
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService service;

    @GetMapping
    public Flux<Car> all(@RequestParam(required = false) String brand) {
        if (brand == null) return service.all();
        return service.byBrand(brand);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> byId(@PathVariable String id) {
        return service.createCarMonoOpt(service.byId(id)).flatMap(flatMapMonoCarOK);
    }

}
