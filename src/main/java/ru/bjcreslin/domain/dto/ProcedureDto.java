package ru.bjcreslin.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * Класс для хранения всех процедур.
 * Подразумевается, что храниться, в другом класе, будут только с isNeeded==true;
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "overhaul")

public class ProcedureDto {
    @Id
    private String id;

    // Уинкальный номер процедуры в системе
    Long uin;

    //нужна ли эта процедура?
    boolean isNeeded;
}
