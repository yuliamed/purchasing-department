package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends AbstractEntity {
    @Column(name="product_name", length = 45, nullable = false)
    private String name;
    @Column(name="is_product", nullable = false)
    private Boolean isProduct = true;
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "product", cascade = CascadeType.ALL)
    List<Specification> specifications = new ArrayList<>();
}