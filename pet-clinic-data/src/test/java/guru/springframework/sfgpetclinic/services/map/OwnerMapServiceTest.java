package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    private OwnerMapService ownerMapService;

    private final Long ownerId = 1L;
    private final String ownerLastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        ownerMapService.save(Owner.builder().id(ownerId).lastName(ownerLastName).build());
    }

    @Test
    void findById() {
        Owner foundOwner = ownerMapService.findById(ownerId);
        assertEquals(ownerId, foundOwner.getId());
    }

    @Test
    void saveNoId() {
        Owner newOwner = ownerMapService.save(Owner.builder().build());
        assertNotNull(newOwner);
        assertNotNull(newOwner.getId());
    }

    @Test
    void saveExisted() {
        long id = 2L;
        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void findAll() {
        assertEquals(1, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        assertEquals(ownerId, ownerMapService.findByLastName(ownerLastName).getId());
    }

    @Test
    void findByNotExistedLastName() {
        Owner notExistingOwner = ownerMapService.findByLastName("foo");
        assertNull(notExistingOwner);
    }
}