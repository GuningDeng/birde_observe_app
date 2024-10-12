package be.ap.birde_observe_app.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ap.birde_observe_app.model.ObservationEvent;
import be.ap.birde_observe_app.service.IObservationEventService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/observationEvent")
public class ObservationEventController {
    @Autowired
    private IObservationEventService observationEventService;

    @PreAuthorize("hasAnyRole('admin', 'editor', 'reader')")
    @GetMapping("/events")
    public String getEvents(Model model) {
        List<ObservationEvent> events = observationEventService.observationEvents();

        model.addAttribute("events", events);
        model.addAttribute("title", "Events");

        return "listEvent";
        
    }
    
    @PreAuthorize("hasAnyRole('admin', 'editor')")
    @GetMapping("/newEvent")
    public String getNewEvent(Model model) {
        model.addAttribute("title", "New event");
        return "newEvent";
    }

    @PostMapping("/newEvent")
    public String postNewEvent(
        @RequestParam("name") String name,
        @RequestParam("date") LocalDate date,
        @RequestParam("postCode") String postCode,
        @RequestParam("attendees") String attendees,
        @RequestParam("year") String year,
        Model model
    ) {
        ObservationEvent event = new ObservationEvent();

        event.setAttendees(attendees);
        event.setDate(date);
        event.setName(name);
        event.setPostCode(postCode);
        event.setYear(year);

        if (!observationEventService.existsObservationEvent(name, year, postCode, attendees)) {
            observationEventService.saveObservationEvent(event);
            return "redirect:/observationEvent/events";
        } else {
            return "redirect:/observationEvent/newEvent";
        }
        
    }

    @PreAuthorize("hasAnyRole('admin', 'editor')")
    @GetMapping("/updateEventById/{id}")
    public String getMethodName(
        Model model,
        @PathVariable("id") Long id
    ) {
        ObservationEvent event = observationEventService.getObservationEventById(id);
        
        model.addAttribute("title", "Updata event: " + event.getName());
        model.addAttribute("event", event);
        return "updateData";
    }

    @PostMapping("/updateEventById/{id}")
    public String postMethodName(
        @PathVariable("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("year") String year,
        @RequestParam("postCode") String postCode,
        @RequestParam("date") LocalDate date,
        @RequestParam("attendees") String attendees
    ) {
        ObservationEvent event = new ObservationEvent();
        event.setAttendees(attendees);
        event.setDate(date);
        event.setName(name);
        event.setPostCode(postCode);
        event.setYear(year);

        observationEventService.updateObservationEvent(event, id);
        
        return "redirect:/observationEvent/events";
    }

    @PreAuthorize("hasAnyRole('admin', 'editor')")
    @GetMapping("/deleteEventById/{id}")
    public String getMethodName(
        @PathVariable("id") Long id
    ) {
        observationEventService.deleteEventById(id);
        return "redirect:/observationEvent/events";
    }
    
    
    
    
    


}
