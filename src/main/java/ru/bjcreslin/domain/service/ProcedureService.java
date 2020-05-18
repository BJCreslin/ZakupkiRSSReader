package ru.bjcreslin.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.dto.ProcedureDto;
import ru.bjcreslin.domain.repo.ProcedureRepo;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class ProcedureService {
    private final ProcedureRepo procedureRepo;

    @Autowired
    public ProcedureService(ProcedureRepo procedureRepo) {
        this.procedureRepo = procedureRepo;
    }

    /**
     * метод сохранения ProcedureDto в базу Item
     *
     * @param procedureDto объект
     * @return сохраненный объект
     */
    public ProcedureDto save(ProcedureDto procedureDto) {

        ProcedureDto item = new ProcedureDto();
        BeanUtils.copyProperties(procedureDto, item);
        return procedureRepo.insert(item);

    }


    /**
     * Метод- есть ли в базе объект с uin таким то
     *
     * @param uin - uin искомого объекта
     * @return true ессли есть, false если нет
     */
    public boolean isItemDtoExistByUin(Long uin) {
        Example<ProcedureDto> example = getItemDtoExample(uin);
        return procedureRepo.exists(example);
    }


    /**
     * Метод получения Итем из базы по uin
     *
     * @param uin искомого объекта
     * @return Optional от itemDto
     */

    public Optional<ProcedureDto> getItemByUin(Long uin) {
        Example<ProcedureDto> example = getItemDtoExample(uin);
        return procedureRepo.findOne(example);
    }


    /**
     * Метод получения количества элементов
     *
     * @return количество
     */
    public Long count() {
        return procedureRepo.count();
    }


    /**
     * Метод получения example из uin
     *
     * @param uin получаемый uin
     * @return Example
     */

    private Example<ProcedureDto> getItemDtoExample(Long uin) {
        ExampleMatcher uinMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("uin", ignoreCase());
        ProcedureDto probe = new ProcedureDto();
        probe.setUin(uin);
        return Example.of(probe, uinMatcher);
    }

}
