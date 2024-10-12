package be.ap.birde_observe_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import be.ap.birde_observe_app.dao.ObservationEventDAO;
import be.ap.birde_observe_app.model.ObservationEvent;

@Controller
public class ObservationEventService implements IObservationEventService {
    @Autowired
    private ObservationEventDAO observationEventDAO;

    @Override
    public ObservationEvent saveObservationEvent(ObservationEvent observationEvent) {
        return observationEventDAO.save(observationEvent);
    }

    @Override
    public List<ObservationEvent> observationEvents() {
        List<ObservationEvent> observationEvents = new ArrayList<>();
        observationEventDAO.findAll().forEach(observationEvents::add);
        return observationEvents;
    }

    @Override
    public ObservationEvent getObservationEventBynameAndYear(String name, String year) {
        List<ObservationEvent> observationEvents = new ArrayList<>();
        observationEventDAO.findAll().forEach(observationEvents::add);

        ObservationEvent observationEvent = new ObservationEvent();
        observationEvent.setAttendees("Attendees");
        observationEvent.setDate(null);
        observationEvent.setName("observationEvent");
        observationEvent.setPostCode("postCode");
        observationEvent.setYear("4202");

        ObservationEvent ob = observationEvents.stream()
        .filter(o -> o.getName().equalsIgnoreCase(name) && o.getYear().equalsIgnoreCase(year))
        .findFirst().orElse(observationEvent);

        return ob;

    }

    @Override
    public ObservationEvent updateObservationEvent(ObservationEvent observationEvent, Long id) {
        ObservationEvent currentObservationEvent = observationEventDAO.findById(id).get();

        if (Objects.nonNull(observationEvent.getAttendees()) && !"".equalsIgnoreCase(observationEvent.getAttendees())) {
            currentObservationEvent.setAttendees(observationEvent.getAttendees());            
        }

        if (Objects.nonNull(observationEvent.getPostCode()) && !"".equalsIgnoreCase(observationEvent.getPostCode())) {
            currentObservationEvent.setPostCode(observationEvent.getPostCode());            
        }

        if (Objects.nonNull(observationEvent.getName()) && !"".equalsIgnoreCase(observationEvent.getName())) {
            currentObservationEvent.setName(observationEvent.getName());            
        }

        if (Objects.nonNull(observationEvent.getDate())) {
            currentObservationEvent.setDate(observationEvent.getDate());            
        }

        if (Objects.nonNull(observationEvent.getYear()) && !"".equalsIgnoreCase(observationEvent.getYear())) {
            currentObservationEvent.setYear(observationEvent.getYear());;            
        }

        return observationEventDAO.save(currentObservationEvent);

    }

    @Override
    public ObservationEvent getObservationEventById(Long id) {
        ObservationEvent observationEvent = new ObservationEvent();
        observationEvent.setAttendees("Attendees");
        observationEvent.setDate(null);
        observationEvent.setName("observationEvent");
        observationEvent.setPostCode("postCode");
        observationEvent.setYear("4202");

        return observationEventDAO.findById(id).orElse(observationEvent);
    }

    @Override
    public void deleteEventById(Long id) {
        observationEventDAO.deleteById(id);
    }

    @Override
    public boolean existsObservationEvent(String name, String year, String postCode, String attendees) {
        List<ObservationEvent> observationEvents = new ArrayList<>();
        observationEventDAO.findAll().forEach(observationEvents::add);

        return observationEvents.stream().anyMatch(o -> o.getName().equalsIgnoreCase(name) && 
        o.getYear().equalsIgnoreCase(year) && 
        o.getPostCode().equalsIgnoreCase(postCode) && o.getAttendees().equalsIgnoreCase(attendees));
    }

    
}
