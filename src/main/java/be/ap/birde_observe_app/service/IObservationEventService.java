package be.ap.birde_observe_app.service;

import java.util.List;

import be.ap.birde_observe_app.model.ObservationEvent;

public interface IObservationEventService {
    /**
     * 
     * @param observationEvent
     * @return
     */
    public ObservationEvent saveObservationEvent(ObservationEvent observationEvent);

    /**
     * 
     * @return
     */
    public List<ObservationEvent> observationEvents();

    /**
     * 
     * @param name
     * @param year
     * @return
     */
    public ObservationEvent getObservationEventBynameAndYear(String name, String year);

    /**
     * 
     * @param observationEvent
     * @param id
     * @return
     */
    public ObservationEvent updateObservationEvent(ObservationEvent observationEvent, Long id);

    /**
     * 
     * @param id
     * @return
     */
    public ObservationEvent getObservationEventById(Long id);

    public void deleteEventById(Long id);

    /**
     * 
     * @param name
     * @param year
     * @param postCode
     * @param attendees
     * @return
     */
    public boolean existsObservationEvent(String name, String year, String postCode, String attendees);

}
