package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet, Long ownerId){
        if (ownerId != null) {
            Customer owner = customerRepository.getOne(ownerId);
            pet.setCustomer(owner);
        }
        Pet savedPet = petRepository.save(pet);

        Customer owner = savedPet.getCustomer();
        if (owner != null) {
            if (owner.getPets() == null){
                owner.setPets(new ArrayList<>());
            }
            if (!owner.getPets().contains(savedPet)){
                owner.getPets().add(savedPet);
            }
            customerRepository.save(owner);
        }
        return savedPet;
    }

    public Pet getPetById(Long id){
        return petRepository.getOne(id);
    }

    public List<Pet> getPetsByCustomerId(Long customerId){
        return petRepository.getAllByCustomerId(customerId);
    }
}
