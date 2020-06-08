package ru.bjcreslin.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;
import ru.bjcreslin.domain.repo.RepairProcedureRepo;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class RepairProcedureService {
    RepairProcedureRepo procedureRepo;

    /**
     * метод сохранения ProcedureFromHtmlParser в базу Item
     *
     * @return сохраненный объект
     */
    public ProcedureFromHtmlParser save(ProcedureFromHtmlParser procedureDto) {
        var item = new ProcedureFromHtmlParser();
        BeanUtils.copyProperties(procedureDto, item);
        return procedureRepo.insert(item);
    }

    /**
     * Метод получения Итем из базы по uin
     *
     * @param uin искомого объекта
     * @return Optional от itemDto
     */

    public Optional<ProcedureFromHtmlParser> getItemByUin(String uin) {
        Example<ProcedureFromHtmlParser> example = getItemDtoExample(uin);
        return procedureRepo.findOne(example);
    }

    /**
     * Возвращает Постраничные данные процедур ремонта
     * @param pageNumber текущая страница
     * @param pageSize размер страницы
     * @return коллекция результатов
     */
    public Page<ProcedureFromHtmlParser> getPageableProcedure(int pageNumber, int pageSize) {
        pageNumber = normalisationPageNumber(pageNumber);
        pageSize = normalisationPageSize(pageSize);

        var pageD = PageRequest.of(pageNumber, pageSize);
        return procedureRepo.findAll(pageD);
    }

    private int normalisationPageSize(int pageSize) {
        if (pageSize < 5) {
            pageSize = 5;
        }
        if (pageSize > 50) {
            pageSize = 50;
        }
        return pageSize;
    }

    private int normalisationPageNumber(int pageNumber) {
        return Math.max(pageNumber, 0);
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

    private Example<ProcedureFromHtmlParser> getItemDtoExample(String uin) {
        ExampleMatcher uinMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("uin", ignoreCase());
        var probe = new ProcedureFromHtmlParser();
        probe.setUin(uin);
        return Example.of(probe, uinMatcher);
    }


}
