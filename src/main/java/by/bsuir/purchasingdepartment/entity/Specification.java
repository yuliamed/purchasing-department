package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "specification")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Specification extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "product_ID")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private Resource resource;

    @Column(name = "count", nullable = false)
    private Integer count;
}
