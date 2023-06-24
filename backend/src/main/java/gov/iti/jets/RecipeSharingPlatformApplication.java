package gov.iti.jets;

import gov.iti.jets.models.dtos.RecipeDTO;
import gov.iti.jets.models.dtos.RejectedRecipeDTO;
import gov.iti.jets.models.dtos.stats.UserStatDTO;
import gov.iti.jets.repositories.UserRepository;
import gov.iti.jets.services.AdminRecipesService;
import gov.iti.jets.services.RejectedRecipeService;
import gov.iti.jets.services.StatisticsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication

public class RecipeSharingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeSharingPlatformApplication.class, args);
//		System.out.println(StatisticsService.getAgeStat());
//		System.out.println(StatisticsService.getGenderStat());
//		 StatisticsService.getUsersStat();
//		System.out.println(AdminRecipesService.acceptRecipe(13));
//		System.out.println(RejectedRecipeService.save(new RejectedRecipeDTO(4,"a5sss")));
	}

}
