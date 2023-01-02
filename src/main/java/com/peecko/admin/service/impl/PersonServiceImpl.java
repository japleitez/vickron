package com.peecko.admin.service.impl;

import com.peecko.admin.domain.Person;
import com.peecko.admin.repository.PersonRepository;
import com.peecko.admin.service.PersonService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Person}.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(Person person) {
        log.debug("Request to save Person : {}", person);
        return personRepository.save(person);
    }

    @Override
    public Person update(Person person) {
        log.debug("Request to update Person : {}", person);
        return personRepository.save(person);
    }

    @Override
    public Optional<Person> partialUpdate(Person person) {
        log.debug("Request to partially update Person : {}", person);

        return personRepository
            .findById(person.getId())
            .map(existingPerson -> {
                if (person.getName() != null) {
                    existingPerson.setName(person.getName());
                }
                if (person.getSurname() != null) {
                    existingPerson.setSurname(person.getSurname());
                }
                if (person.getEmail() != null) {
                    existingPerson.setEmail(person.getEmail());
                }
                if (person.getLicense() != null) {
                    existingPerson.setLicense(person.getLicense());
                }
                if (person.getStart() != null) {
                    existingPerson.setStart(person.getStart());
                }
                if (person.getEnd() != null) {
                    existingPerson.setEnd(person.getEnd());
                }

                return existingPerson;
            })
            .map(personRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Person> findAll(Pageable pageable) {
        log.debug("Request to get all People");
        return personRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
    }
}
