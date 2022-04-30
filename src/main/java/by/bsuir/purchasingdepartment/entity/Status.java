package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Status extends AbstractEntity{
    @Column(name="status_name", length = 45, nullable = false)
    private String name;

    @Column(name="rus_status_name", length = 45, nullable = false)
    private String name_rus;
}
