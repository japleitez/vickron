package com.peecko.admin.service.impl;

import com.peecko.admin.domain.Coach;
import com.peecko.admin.repository.CoachRepository;
import com.peecko.admin.service.CoachService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Coach}.
 */
@Service
@Transactional
public class CoachServiceImpl implements CoachService {

    private final Logger log = LoggerFactory.getLogger(CoachServiceImpl.class);

    private final CoachRepository coachRepository;

    public CoachServiceImpl(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    @Override
    public Coach save(Coach coach) {
        log.debug("Request to save Coach : {}", coach);
        return coachRepository.save(coach);
    }

    @Override
    public Coach update(Coach coach) {
        log.debug("Request to update Coach : {}", coach);
        return coachRepository.save(coach);
    }

    @Override
    public Optional<Coach> partialUpdate(Coach coach) {
        log.debug("Request to partially update Coach : {}", coach);

        return coachRepository
            .findById(coach.getId())
            .map(existingCoach -> {
                if (coach.getCountry() != null) {
                    existingCoach.setCountry(coach.getCountry());
                }
                if (coach.getName() != null) {
                    existingCoach.setName(coach.getName());
                }
                if (coach.getSurname() != null) {
                    existingCoach.setSurname(coach.getSurname());
                }
                if (coach.getType() != null) {
                    existingCoach.setType(coach.getType());
                }
                if (coach.getEmail() != null) {
                    existingCoach.setEmail(coach.getEmail());
                }
                if (coach.getPhone() != null) {
                    existingCoach.setPhone(coach.getPhone());
                }
                if (coach.getResume() != null) {
                    existingCoach.setResume(coach.getResume());
                }
                if (coach.getContact() != null) {
                    existingCoach.setContact(coach.getContact());
                }
                if (coach.getLang() != null) {
                    existingCoach.setLang(coach.getLang());
                }

                return existingCoach;
            })
            .map(coachRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Coach> findAll(Pageable pageable) {
        log.debug("Request to get all Coaches");
        return coachRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Coach> findOne(Long id) {
        log.debug("Request to get Coach : {}", id);
        return coachRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Coach : {}", id);
        coachRepository.deleteById(id);
    }
}
