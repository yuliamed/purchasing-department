package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends AbstractEntity {

    @Column(name = "count", nullable = false)
    private Integer count;

    @Column(name = "delivery_date", nullable = false)
    private LocalDate deliveryDate;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "whole_price")
    private Double wholePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;

    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;
}
