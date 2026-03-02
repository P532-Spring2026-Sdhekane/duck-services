package edu.iu.habahram.ducksservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ducks")
public class DuckEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private Integer size;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String quackSound;

    @Column(nullable = false)
    private Boolean flyBehavior;

    // Default constructor (required by JPA)
    public DuckEntity() {}

    // Constructor with parameters (without id)
    public DuckEntity(String name, String type, String color, Integer size,
                      Integer price, String quackSound, Boolean flyBehavior) {
        this.name = name;
        this.type = type;
        this.color = color;
        this.size = size;
        this.price = price;
        this.quackSound = quackSound;
        this.flyBehavior = flyBehavior;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getQuackSound() {
        return quackSound;
    }

    public void setQuackSound(String quackSound) {
        this.quackSound = quackSound;
    }

    public Boolean getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(Boolean flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    // Convert to DuckData
    public DuckData toDuckData() {
        return new DuckData(
                id,
                name,
                type,
                color,
                size,
                price,
                quackSound,
                flyBehavior
        );
    }
}