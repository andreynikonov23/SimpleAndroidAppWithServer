package ru.nngasu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import ru.nngasu.permissions.Role;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "last_name")
    private String lastname;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @Transient
    private Bucket bucket;

    public User() {}

    public User(long id, String username, String password, String firstname, String lastname, Role role, Bucket bucket) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.bucket = bucket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return String.format("User: {id: %d, username: '%s', firstname: '%s', lastname: '%s', %s}", id, username, firstname, lastname, role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstname, lastname);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        if (obj.hashCode() != this.hashCode()) {
            return false;
        }
        User otherUser = (User) obj;
        return this.id == otherUser.getId() && this.username.equals(otherUser.username) && this.password.equals(otherUser.password)
                && this.firstname.equals(otherUser.firstname) && this.lastname.equals(otherUser.lastname);
    }
}
