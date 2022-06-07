package pl.altkom.animalhotel.ownerservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.altkom.animalhotel.ownerservice.controller.model.CreateOwnerRequest;
import pl.altkom.animalhotel.ownerservice.controller.model.Owner;
import pl.altkom.animalhotel.ownerservice.controller.model.UpdateOwnerRequest;
import pl.altkom.animalhotel.ownerservice.service.OwnerService;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/owner")
@RestController
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<Owner> getOwners() {
        log.info("Get all owners");
        return ownerService.getOwners();
    }

    @GetMapping("/{id}")
    public Owner getOwner(@PathVariable("id") final Long ownerId) {
        log.info("Get owner {}", ownerId);
        return ownerService.getOwner(ownerId);
    }

    @PostMapping
    public Owner createOwner(@Valid @RequestBody final CreateOwnerRequest request) {
        return ownerService.createOwner(request);
    }

    @PutMapping("/{id}")
    public Owner updateOwner(@PathVariable("id") final Long ownerId, @Valid @RequestBody final UpdateOwnerRequest request) {
        return ownerService.updateOwner(ownerId, request);
    }
}
