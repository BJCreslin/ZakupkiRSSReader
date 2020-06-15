package ru.bjcreslin.conrollers;


import io.swagger.annotations.Api;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.conrollers.Exception.BadRequestException;
import ru.bjcreslin.conrollers.Exception.NotFoundException;
import ru.bjcreslin.domain.dto.DataPage;
import ru.bjcreslin.domain.dto.ProcedureDto;
import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;
import ru.bjcreslin.domain.fromHtml.RepaireTradeProcedureParser;
import ru.bjcreslin.domain.service.ItemDomainService;
import ru.bjcreslin.domain.service.RepairProcedureService;

import static ru.bjcreslin.configuration.Constants.SAVE_REPAIRED_CONTROLLER;

@CrossOrigin
@Log
@Api
@Controller
@RequestMapping(SAVE_REPAIRED_CONTROLLER)
public class RepairBaseWebController {
    private final ItemDomainService itemDomainService;
    private final RepairProcedureService repairProcedureService;
    private final RepaireTradeProcedureParser parser;

    @Autowired
    public RepairBaseWebController(ItemDomainService itemDomainService, RepairProcedureService repairProcedureService, RepaireTradeProcedureParser parser) {
        this.itemDomainService = itemDomainService;
        this.repairProcedureService = repairProcedureService;
        this.parser = parser;
    }

    @PostMapping("/")
    @ResponseBody
    public String saveNeeded(@RequestBody ProcedureDto itemDto) {
        //itemDomainService.saveNeeded(itemDto);
        ProcedureFromHtmlParser procedureFromHtmlParser=parser.getResult(itemDto.getUin());
        log.info(procedureFromHtmlParser.toString());
        repairProcedureService.save(procedureFromHtmlParser);
//        repairProcedureService.save(repairProcedureService.getItemByUin(itemDto.getUin()).get());
//        if ((itemDto.isNeeded()) && !repairProcedureService.isPresentByUin(itemDto.getUin())) {
//            //todo: доделать
//            repairProcedureService.save(repairProcedureService.getItemByUin(itemDto.getUin()).get());
//        }
        return "Ok";
    }


    @PostMapping("/get_all")
    @ResponseBody
    public Page<ProcedureFromHtmlParser> getAll(@RequestBody DataPage dataPage) {
        checkDataPage(dataPage);
        return repairProcedureService.getPageableProcedure(dataPage.getPageNumber(), dataPage.getPageSize());
    }

    @GetMapping("/{uin}")
    @ResponseBody
    public ProcedureFromHtmlParser getOne(@PathVariable("uin") String uin) {
        if (repairProcedureService.getItemByUin(uin).isEmpty()) {
            throw new NotFoundException();
        }
        return repairProcedureService.getItemByUin(uin).get();
    }


    @GetMapping("/count")
    @ResponseBody
    public long count() {
        return repairProcedureService.count();
    }

    private void checkDataPage(DataPage dataPage) {
        if ((dataPage.getPageNumber() < 0) ||
                (dataPage.getPageSize() < 1)) throw new BadRequestException();
    }

}
