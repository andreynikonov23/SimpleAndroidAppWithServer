package ru.nngasu.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "buckets")
public class Bucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "bucket", fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public Bucket() {}

    public Bucket(long id, User user, Set<Product> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return String.format("{id: %d, user: '%s'}", id, user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user);
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
        Bucket otherBucket = (Bucket) obj;
        return this.id == otherBucket.getId() && this.user.equals(otherBucket.getUser());
    }
}
