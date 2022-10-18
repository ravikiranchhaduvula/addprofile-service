package com.skilltracker.app.addprofile.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote NonTechExpertLevel is the POJO for technical expertise
 */
@Entity
@Data
@Table(name="TECH_EXPERT_LEVEL")
@AllArgsConstructor
@NoArgsConstructor
public class TechExpertLevel implements Comparable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skillName;
    @NotNull(message="One of the Techskill is blank")
    @Min(value = 0,message="One of the Techskill has expertlevel < 0")
    @Max(value = 20,message="One of the Techskill has expertlevel > 20")
    private Integer expertLevel;
    @CreationTimestamp
    private LocalDate createdAt;

    @Override
    public int compareTo(Object comparetech) {
        int compareTechVal=((TechExpertLevel)comparetech).getExpertLevel();
        /* For Descending order do like this */
        return compareTechVal-this.expertLevel;
    }
}
