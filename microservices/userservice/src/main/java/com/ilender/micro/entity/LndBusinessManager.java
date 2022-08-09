package com.ilender.micro.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Table(name = "LNDBUSINESSMANAGER")
@Entity
public class LndBusinessManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lnduserid")
    private int lndUserId;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @JsonInclude()
    @Transient
    private String startDateString;

    @JsonInclude()
    @Transient
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLndUserId() {
        return lndUserId;
    }

    public void setLndUserId(int lndUserId) {
        this.lndUserId = lndUserId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}