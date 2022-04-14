package by.bsuir.purchasingdepartment.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Plan extends AbstractEntity{
    @Column(name="count", nullable = false)
    private Integer count;

    @Column(name="date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "dimension")
    private Dimension dimension;
}
