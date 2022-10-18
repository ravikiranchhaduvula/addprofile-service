package com.skilltracker.app.addprofile.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote NonTechExpertLevel is the POJO for non technical expertise
 */
@Entity
@Data
@Table(name="NON_TECH_EXPERT_LEVEL")
@AllArgsConstructor
@NoArgsConstructor
public class NonTechExpertLevel implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String skillName;
    @NotNull(message="One of the Nontechskill is blank")
    @Min(value = 0,message="One of the Nontechskill has expertlevel < 0")
    @Max(value = 20,message="One of the Nontechskill has expertlevel > 20")
    private Integer expertLevel;
    @CreationTimestamp
    private LocalDate createdAt;

    @Override
    public int compareTo(Object compareNontech) {
        int compareNonTechVal=((NonTechExpertLevel)compareNontech).getExpertLevel();
        /* For Descending order do like this */
        return compareNonTechVal-this.expertLevel;
    }
}
