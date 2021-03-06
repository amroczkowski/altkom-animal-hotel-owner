package pl.altkom.animalhotel.ownerservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.altkom.animalhotel.ownerservice.client.model.Dog;

@FeignClient(value = "DOG-SERVICE")
public interface DogClient {

    @GetMapping("/dog/owner/{ownerId}")
    List<Dog> getOwnerDogs(@PathVariable("ownerId") final Long ownerId);

    @GetMapping("/dog")
    List<Dog> getDogs();
}
