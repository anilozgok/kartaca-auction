package com.anilcan.kartaca.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bids")
public class Bid {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User bidderId;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item bidItemId;

    @Column(nullable = false)
    private Double bidPrice;

}
