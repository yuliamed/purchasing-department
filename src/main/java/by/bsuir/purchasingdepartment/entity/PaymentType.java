package by.bsuir.purchasingdepartment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentType extends AbstractEntity{
    @Column(name="payment_name", length = 45, nullable = false)
    private String name;

    @Column(name="rus_payment_name", length = 45, nullable = false)
    private String name_rus;
}
