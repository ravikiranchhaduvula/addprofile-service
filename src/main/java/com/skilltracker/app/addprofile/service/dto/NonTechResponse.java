package com.skilltracker.app.addprofile.service.dto;

import com.skilltracker.app.addprofile.service.entity.NonTechExpertLevel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote NonTechResponse is a DTO for non technical expertise
 */
@Data
@NoArgsConstructor
@ToString
public class NonTechResponse {
    private String skillName;
    private Integer expertLevel;
    private LocalDate createdAt;
    private Long userId;

    private NonTechExpertLevel nonTechExpertLevel;

    /** This is useful when the query use a subset of the fields used here **/
    public NonTechResponse(String skillName, Integer expertLevel, LocalDate createdAt
            , Long userId, NonTechExpertLevel nonTechExpertLevel) {
        this.skillName = skillName;
        this.expertLevel = expertLevel;
        this.createdAt = createdAt;
        this.userId = userId;
        this.nonTechExpertLevel = nonTechExpertLevel;
    }
}