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
public class Provider extends AbstractEntity{
    @Column(name="provider_name", length = 45, nullable = false)
    private String name;

    @Column(name="provider_address", length = 45)
    private String address;

    @Column(name="provider_ph_number")
    private Integer phNumber;

}
