package gov.iti.jets.controllers;

import gov.iti.jets.models.dtos.stats.*;
import gov.iti.jets.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/dashboard")
//@CrossOrigin(origins = "http://localhost:4200")
public class AdminDashboardController {

    private StatisticsService statisticsService;

    public AdminDashboardController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/getGenderStat")
    public GenderStatDTO getGender() {
        return statisticsService.getGenderStat();

    }

    @GetMapping("/getAgeStat")
    public AgeStatDTO getAge() {
        return statisticsService.getAgeStat();

    }

    @GetMapping("/getRegisterDateStat")
    public RegistrationDateStatDTO getRegisterDateStat() {
        return statisticsService.getRegisterDateStat();

    }

    @GetMapping("/getRecipeStatusStat")
    public RecipeStatusStatDTO getRecipeStatusStat() {
        return statisticsService.getRecipeStatusStat();

    }

    @GetMapping("/getUsersStat/{page}")
    public List<UserStatDTO> getUsersStat(@PathVariable int page) {
        return statisticsService.getUsersStat(PageRequest.of(page, 10));
    }

    @GetMapping("/getUsersCount")
    public Long getUsersCount() {
        return statisticsService.getUsersCount();
    }

}
