package pl.altkom.animalhotel.ownerservice.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import pl.altkom.animalhotel.ownerservice.client.DogClient;
import pl.altkom.animalhotel.ownerservice.controller.mapper.RequestMapper;
import pl.altkom.animalhotel.ownerservice.controller.mapper.ResponseMapper;
import pl.altkom.animalhotel.ownerservice.controller.model.CreateOwnerRequest;
import pl.altkom.animalhotel.ownerservice.controller.model.Dog;
import pl.altkom.animalhotel.ownerservice.controller.model.Owner;
import pl.altkom.animalhotel.ownerservice.controller.model.UpdateOwnerRequest;
import pl.altkom.animalhotel.ownerservice.repository.OwnerRepository;

@RequiredArgsConstructor
@Service
public class OwnerService {

    private final DogClient dogClient;
    private final OwnerRepository ownerRepository;

    public List<Owner> getOwners() {
        final List<Dog> dogs = pl.altkom.animalhotel.ownerservice.client.mapper.ResponseMapper.map(dogClient.getDogs());
        return ResponseMapper.map(ownerRepository.findAll(), dogs);
    }

    public Owner getOwner(final Long ownerId) {
        final pl.altkom.animalhotel.ownerservice.repository.model.Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        final List<Dog> dogs = pl.altkom.animalhotel.ownerservice.client.mapper.ResponseMapper.map(dogClient.getOwnerDogs(owner.getId()));
        return ResponseMapper.map(owner, dogs);
    }

    public Owner createOwner(final CreateOwnerRequest request) {
        final pl.altkom.animalhotel.ownerservice.repository.model.Owner savedOwner = ownerRepository.save(RequestMapper.bind(request));
        return ResponseMapper.map(savedOwner, List.of());
    }

    public Owner updateOwner(final Long ownerId, final UpdateOwnerRequest request) {

        final pl.altkom.animalhotel.ownerservice.repository.model.Owner sourceOwner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final pl.altkom.animalhotel.ownerservice.repository.model.Owner modifiedOwner = ownerRepository
                .save(RequestMapper.bind(request, sourceOwner));
        final List<Dog> dogs = pl.altkom.animalhotel.ownerservice.client.mapper.ResponseMapper
                .map(dogClient.getOwnerDogs(modifiedOwner.getId()));
        return ResponseMapper.map(modifiedOwner, dogs);
    }
}
