package se.iths.stefan.enhetstestninglabb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.stefan.enhetstestninglabb.service.ATMService;

@Controller
@RequestMapping("/atm")
public class ATMController {
    private final ATMService atmService;

    public ATMController(ATMService atmService) {
        this.atmService = atmService;
    }

    @GetMapping("/balance")
    public String displayBalance(Model model) {
        model.addAttribute("balance", atmService.performDisplayBalance());
        return "atm-balance";
    }
}
