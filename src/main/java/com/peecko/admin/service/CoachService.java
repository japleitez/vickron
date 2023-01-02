package com.peecko.admin.service;

import com.peecko.admin.domain.Coach;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link Coach}.
 */
public interface CoachService {
    /**
     * Save a coach.
     *
     * @param coach the entity to save.
     * @return the persisted entity.
     */
    Coach save(Coach coach);

    /**
     * Updates a coach.
     *
     * @param coach the entity to update.
     * @return the persisted entity.
     */
    Coach update(Coach coach);

    /**
     * Partially updates a coach.
     *
     * @param coach the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Coach> partialUpdate(Coach coach);

    /**
     * Get all the coaches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Coach> findAll(Pageable pageable);

    /**
     * Get the "id" coach.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Coach> findOne(Long id);

    /**
     * Delete the "id" coach.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
