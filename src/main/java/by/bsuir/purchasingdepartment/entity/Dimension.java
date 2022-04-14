package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "providers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Dimension extends AbstractEntity {
    @Column(name="dimension_name",length = 45, nullable = false)
    private String name;

    @Column(name="capacity", nullable = false)
    private Integer capacity;
}
