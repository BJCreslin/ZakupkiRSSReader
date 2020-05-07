package ru.bjcreslin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemDto {
    // номер закона - ПП РФ 615 (Капитальный ремонт)
    String lawNumber;

    // Заказчк -
    String author;
}
