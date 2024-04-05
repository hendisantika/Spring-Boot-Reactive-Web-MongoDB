package id.my.hendisantika.springbootreactivewebmongodb.controller;

import id.my.hendisantika.springbootreactivewebmongodb.model.Car;
import id.my.hendisantika.springbootreactivewebmongodb.service.CarService;
import io.netty.handler.codec.DecoderException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Optional;
import java.util.function.Function;

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

    @PostMapping
    public Mono<ResponseEntity<?>> create(@RequestBody Car car, UriComponentsBuilder uriBuilder) {
        try {
            return service.create(car).flatMap(createdCar -> {
                String location = uriBuilder.path("cars/{id}").buildAndExpand(createdCar.getId()).toUriString();
                return Mono.just(ResponseEntity.created(URI.create(location)).body(createdCar));
            });
        } catch (DecoderException e) {
            System.err.println("error: " + e.getMessage());
            return Mono.just(ResponseEntity.badRequest().body(e.getCause()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Mono.just(ResponseEntity.internalServerError().body(e.getCause()));
        }
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<?>> update(@PathVariable String id, @RequestBody Car car) {
        try {
            return service.createCarMonoOpt(service.update(id, car))
                    .flatMap(flatMapMonoCarOK);
        } catch (DecoderException e) {
            System.err.println("error: " + e.getMessage());
            return Mono.just(ResponseEntity.badRequest().body(e.getCause()));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return Mono.just(ResponseEntity.internalServerError().body(e.getCause()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id).subscribe();
        return ResponseEntity.noContent().build();
    }


    private final Function<Optional<Car>, Mono<ResponseEntity<?>>> flatMapMonoCarOK = carOpt ->
            carOpt.<Mono<ResponseEntity<?>>>map(car -> Mono.just(ResponseEntity.ok(car)))
                    .orElseGet(() -> Mono.just(ResponseEntity.notFound().build()));

}
