package be.ap.birde_observe_app.dao;

import org.springframework.data.repository.CrudRepository;

import be.ap.birde_observe_app.model.ObservationEvent;

public interface ObservationEventDAO extends CrudRepository<ObservationEvent, Long> {
    
}
