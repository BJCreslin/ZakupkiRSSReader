package ru.bjcreslin.conrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.bjcreslin.domain.dto.ItemDto;
import ru.bjcreslin.domain.service.ItemDomainService;

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
