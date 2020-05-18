package ru.bjcreslin.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bjcreslin.domain.dto.ProcedureDto;

public interface ProcedureRepo extends MongoRepository<ProcedureDto,String> {
}
