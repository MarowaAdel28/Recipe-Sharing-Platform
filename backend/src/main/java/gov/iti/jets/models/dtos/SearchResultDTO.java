package gov.iti.jets.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
@AllArgsConstructor
public class SearchResultDTO {
    private List<RecipeDTO> data;
    private long totalItems;
}
