package com.skilltracker.app.addprofile.service.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote TechSkills is the POJO for technical skills
 * Where the data loads during application startup.
 * This table is for reference purpose only
 */
@Entity
@Table(name="TECH_SKILLS")
@AllArgsConstructor
@NoArgsConstructor
public class TechSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skillName;
}
