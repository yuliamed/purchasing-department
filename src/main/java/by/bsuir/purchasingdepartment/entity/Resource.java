package by.bsuir.purchasingdepartment.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource extends AbstractEntity{
    @Column(name="resource_name", length = 45, nullable = false)
    private String name;

    @Column(name="description", length = 350, nullable = false)
    private String description;
}
