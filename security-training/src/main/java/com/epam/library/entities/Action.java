package com.epam.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Action {
    @Id
    @Column(name = "action_id")
    private UUID id;

    @Column
    private String message;

    public Action() {
    }

    public Action(UUID id, String message) {
        this.id = id;
        this.message = message;
    }

    public UUID getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
}
