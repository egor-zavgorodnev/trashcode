package com.epam.library.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class History {

    @Id
    @Column(name="history_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="actiontext")
    private String actionText;

    public History() {
    }

    public History(UUID id, User user, String actionText) {
        this.id = id;
        this.user = user;
        this.actionText = actionText;
    }

    public UUID getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getActionText() {
        return actionText;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUser(User userId) {
        this.user = user;
    }

    public void setActionText(String actionText) {
        this.actionText = actionText;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", userId=" + user +
                ", actionText='" + actionText + '\'' +
                '}';
    }
}
