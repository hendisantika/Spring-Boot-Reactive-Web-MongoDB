package id.my.hendisantika.springbootreactivewebmongodb.repository;

import id.my.hendisantika.springbootreactivewebmongodb.model.Car;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
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
@Repository
public interface CarRepository extends ReactiveMongoRepository<Car, String> {
    Flux<Car> findByBrandIgnoreCase(String brand);

    Flux<Car> findByModelIgnoreCase(String model);

}
