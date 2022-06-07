package pl.altkom.animalhotel.ownerservice.controller.mapper;

import java.util.List;
import java.util.stream.Collectors;

import pl.altkom.animalhotel.ownerservice.controller.model.Dog;
import pl.altkom.animalhotel.ownerservice.repository.model.Owner;

public class ResponseMapper {
    public static List<pl.altkom.animalhotel.ownerservice.controller.model.Owner> map(final List<Owner> owners,
            final List<Dog> dogs) {
        return owners.stream()
                .map(owner -> map(owner,
                        dogs.stream().filter(d -> d.getOwnerId().equals(owner.getId())).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public static pl.altkom.animalhotel.ownerservice.controller.model.Owner map(final Owner owner,
            final List<Dog> dogs) {
        return pl.altkom.animalhotel.ownerservice.controller.model.Owner.builder()
                .id(owner.getId())
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .phone(owner.getPhone())
                .dogs(dogs)
                .build();
    }
}
