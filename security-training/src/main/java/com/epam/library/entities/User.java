package com.epam.library.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class User{

    @Id
    @Column(name="user_id")
    private UUID id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "isblocked")
    private boolean isBlocked;

    @Column(name = "isadmin")
    private boolean isAdmin;

    public User() {
    }

    public User(UUID id, String nickname, String password, boolean isBlocked, boolean isAdmin) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.isBlocked = isBlocked;
        this.isAdmin = isAdmin;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", isBlocked=" + isBlocked +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
