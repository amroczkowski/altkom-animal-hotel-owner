package pl.altkom.animalhotel.ownerservice.client.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.animalhotel.ownerservice.controller.model.Dog;

public class ResponseMapper {

    public static List<Dog> map(final List<pl.altkom.animalhotel.ownerservice.client.model.Dog> dogs) {
        return dogs.stream()
                .map(ResponseMapper::map)
                .collect(Collectors.toList());
    }

    private static Dog map(final pl.altkom.animalhotel.ownerservice.client.model.Dog dog) {
        return Dog.builder()
                .id(dog.getId())
                .name(dog.getName())
                .breed(dog.getBreed())
                .dateOfBirth(dog.getDateOfBirth())
                .ownerId(dog.getOwnerId())
                .build();
    }
}
