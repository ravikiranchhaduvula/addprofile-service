package com.skilltracker.app.addprofile.service.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote NonTechSkills is the POJO for non technical skills
 * Where the data loads during application startup
 * This table is for reference purpose only
 */
@Entity
@Table(name="NON_TECH_SKILLS")
@AllArgsConstructor
@NoArgsConstructor
public class NonTechSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skillName;
}
