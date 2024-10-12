package be.ap.birde_observe_app.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblObservationEvent")
public class ObservationEvent {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ob_name")
    private String name;

    @Column(name = "ob_date")
    private LocalDate date;

    @Column(name = "ob_postCode")
    private String postCode;

    @Column(name = "ob_year")
    private String year;

    @Column(name = "ob_attendees")
    private String attendees;

    public ObservationEvent() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAttendees() {
        return attendees;
    }

    public void setAttendees(String attendees) {
        this.attendees = attendees;
    }

    
    
}
