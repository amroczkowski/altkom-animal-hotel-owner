package pl.altkom.animalhotel.ownerservice.controller.model;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Owner {

    private Long id;

    private String firstName;
    private String lastName;
    private String phone;
    private List<Dog> dogs;
}
