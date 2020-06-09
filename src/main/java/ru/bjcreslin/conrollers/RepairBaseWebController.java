package ru.bjcreslin.conrollers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.conrollers.Exception.BadRequestException;
import ru.bjcreslin.conrollers.Exception.NotFoundException;
import ru.bjcreslin.domain.dto.DataPage;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.dto.ProcedureFromHtmlParser;
import ru.bjcreslin.domain.service.ItemDomainService;
import ru.bjcreslin.domain.service.RepairProcedureService;

@CrossOrigin
@Api
@Controller
@RequestMapping("/base/repair")
public class RepairBaseWebController {
    private final ItemDomainService itemDomainService;
    private final RepairProcedureService repairProcedureService;

    @Autowired
    public RepairBaseWebController(ItemDomainService itemDomainService, RepairProcedureService repairProcedureService) {
        this.itemDomainService = itemDomainService;
        this.repairProcedureService = repairProcedureService;
    }

    @PutMapping("/")
    @ResponseBody
    public String saveNeeded(@RequestBody ItemDto itemDto) {
        itemDomainService.saveNeeded(itemDto);
        if ((itemDto.isNeeded()) && !repairProcedureService.isPresentByUin(itemDto.getUin())) {
            //todo: доделать
            repairProcedureService.save(repairProcedureService.getItemByUin(itemDto.getUin()).get());
        }
        return "Ok";
    }


    @GetMapping("/get_all")
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
