package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "storehouse")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Storehouse {
    @Id
    @OneToOne
    private Resource resource;

    @Column(name = "count", nullable = false)
    private Integer count;
}
