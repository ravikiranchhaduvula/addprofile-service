package com.skilltracker.app.addprofile.service.dto;

import com.skilltracker.app.addprofile.service.entity.TechExpertLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote TechResponse is a DTO for technical expertise
 */
@Data
@NoArgsConstructor
@ToString
public class TechResponse {
    private String skillName;
    private Integer expertLevel;
    private LocalDate createdAt;
    private Long userId;

    private TechExpertLevel techExpertLevels;

    /** This is useful when the query use a subset of the fields used here **/
    public TechResponse(String skillName, Integer expertLevel,
                        LocalDate createdAt, Long userId, TechExpertLevel techExpertLevels) {
        this.skillName = skillName;
        this.expertLevel = expertLevel;
        this.createdAt = createdAt;
        this.userId = userId;
        this.techExpertLevels = techExpertLevels;
    }
}
