package com.wiredbrain.friends.service;

import com.wiredbrain.friends.model.Billionaire;
import org.springframework.data.repository.CrudRepository;

public interface BillionaireService extends CrudRepository<Billionaire, Integer> {
    Iterable<Billionaire> findByFirstNameAndLastName(String firstName, String lastName);

    Iterable<Billionaire> findByFirstName(String firstName);

    Iterable<Billionaire> findByLastName(String lastName);
}
