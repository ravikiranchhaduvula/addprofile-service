package com.skilltracker.app.addprofile.service.dto;

import com.skilltracker.app.addprofile.service.entity.NonTechExpertLevel;
import com.skilltracker.app.addprofile.service.entity.TechExpertLevel;
import com.skilltracker.app.addprofile.service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote NonTechResponse is a DTO for Skill Request object which holds the add-profile data
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@ToString
public class SkillRequest {
    @Valid
    private User user;
    @Valid
    private TechExpertLevel techExpertLevel;
    @Valid
    private NonTechExpertLevel nonTechExpertLevel;
}
