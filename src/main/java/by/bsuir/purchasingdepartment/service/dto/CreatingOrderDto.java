package by.bsuir.purchasingdepartment.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatingOrderDto {
    // Его надо брать из таблицы и отправлять, жесть конечно, но так проще.
    // Если не получится, то надо отправлять поставщика и ресурс айдишки, они ниже закомментированы
    //private Long catalogId;
    //TODO (ЮЛЯ) getResourceById
    private String resource;
    private Long providerId;
    private Long resourceId;
    private Integer count;
    private Long paymentTypeId;
}
