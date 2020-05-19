package ru.bjcreslin.conrollers;


import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.service.ItemDomainService;

@CrossOrigin
@Api
@Controller
@RequestMapping("/base/repair")
public class RepairBaseWebController {
    private final ItemDomainService itemDomainService;

    @Autowired
    public RepairBaseWebController(ItemDomainService itemDomainService) {
        this.itemDomainService = itemDomainService;
    }

    @PutMapping("/")
    @ResponseBody
    public String saveNeeded(@RequestBody ItemDto itemDto) {
        itemDomainService.saveNeeded(itemDto);
        return "Ok";
    }

}
