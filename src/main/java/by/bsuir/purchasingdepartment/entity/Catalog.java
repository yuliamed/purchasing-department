package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "catalog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Catalog extends AbstractEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    private Resource resource;

    @ManyToOne(fetch = FetchType.EAGER)
    private Provider provider;

    @Column(name="price", nullable = false)
    private Double price;

    @Column(name="delivery_time", nullable = false)
    private Integer deliveryTimeInDays;
}
