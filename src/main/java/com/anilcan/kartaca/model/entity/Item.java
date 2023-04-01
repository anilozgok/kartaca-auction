package com.anilcan.kartaca.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private double initialPrice;

    @OneToMany(mappedBy = "item")
    private List<Bid> itemBids;

}
