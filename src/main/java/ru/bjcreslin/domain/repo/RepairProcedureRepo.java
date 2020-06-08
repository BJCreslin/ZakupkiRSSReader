package ru.bjcreslin.domain.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;

public interface RepairProcedureRepo extends MongoRepository<ProcedureFromHtmlParser,String> {
}
