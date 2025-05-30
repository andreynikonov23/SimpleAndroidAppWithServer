package ru.nngasu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private String art;
    @NotNull
    private String name;
    @ManyToOne (fetch = FetchType.LAZY)
    private Category category;
    @NotNull
    private double cost;
    @NotNull
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Bucket bucket;
    @Column(name = "quantity_in_bucket")
    private int quantityInBucket;

    public Product() {}

    public Product(String art, String name, Category category, double cost, int quantity, Bucket bucket, int quantityInBucket) {
        this.art = art;
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.quantity = quantity;
        this.bucket = bucket;
        this.quantityInBucket = quantityInBucket;
    }

    public String getArt() {
        return art;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public int getQuantityInBucket() {
        return quantityInBucket;
    }

    public void setQuantityInBucket(int quantityInBucket) {
        this.quantityInBucket = quantityInBucket;
    }

    @Override
    public String toString() {
        return String.format("{art: %s, name: '%s', category: '%s', cost: '%f'}", art, name, category, cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(art, name, category, cost, quantity);
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
        Product otherProduct = (Product) obj;
        return this.art == otherProduct.getArt() && this.name.equals(otherProduct.getName()) && this.category.equals(otherProduct.getCategory())
                && this.cost == otherProduct.getCost() && this.quantity == otherProduct.getQuantity();
    }
}
