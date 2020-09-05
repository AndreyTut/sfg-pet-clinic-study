package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    private static final String LAST_NAME = "Smith";

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerSDJpaService ownerService;

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).lastName("Smith").build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(owner);
        Owner smith = ownerService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, smith.getLastName());
        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(any())).thenReturn(Optional.of(owner));
        Owner ownerById = ownerService.findById(owner.getId());
        assertNotNull(ownerById);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(any())).thenReturn(Optional.empty());
        Owner ownerById = ownerService.findById(23L);
        assertNull(ownerById);
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = ownerService.save(ownerToSave);
        assertNotNull(savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> returnOwners = ownerService.findAll();
        assertEquals(2, returnOwners.size());
    }

    @Test
    void delete() {
        ownerService.delete(owner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerService.deleteById(1L);
        verify(ownerRepository).deleteById(anyLong());
    }
}