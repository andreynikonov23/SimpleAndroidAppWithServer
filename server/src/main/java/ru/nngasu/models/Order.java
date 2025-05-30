package ru.nngasu.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Bucket bucket;

    public Order() {}

    public Order(long id, Bucket bucket) {
        this.id = id;
        this.bucket = bucket;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return String.format("User: {id: %d, bucket: '%s'}", id, bucket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bucket);
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
        Order otherOrder = (Order) obj;
        return this.id == otherOrder.getId() && this.bucket.equals(otherOrder.getBucket());
    }
}
