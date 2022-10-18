package com.skilltracker.app.addprofile.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote User is the POJO with all add skill details along with validations
 */
@Entity
@Data
@Table(name="USER_PROFILE")
@AllArgsConstructor
//(staticName = "build")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank(message = "Name must not be blank")
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 chars length")
    private String name;
    @NotBlank(message = "AssociateId Must not be blank")
    @Size(min = 5, max = 30, message = "AssociateId should be between 5 and 30 chars length")
    @Pattern(regexp = "^.*CTS.*$",message = "Associate ID must start with CTS")
    private String associateId;
    @NotBlank(message = "Mobile shouldn't be blank")
    @Pattern(regexp = "^\\d{10}$",message = "Invalid Mobile Number")
    private String mobile;
    @NotBlank(message = "Email shouldn't be blank")
    @Email(message = "Invalid Email atleast one @ expected")
    private String email;
    @OneToMany(targetEntity = TechExpertLevel.class,cascade = CascadeType.ALL)
    @JoinColumn(name="cp_fk",referencedColumnName = "userId")
    @Valid
    private List<TechExpertLevel> techExpertLevels;
    @OneToMany(targetEntity = NonTechExpertLevel.class,cascade = CascadeType.ALL)
    @JoinColumn(name="cp_fk",referencedColumnName = "userId")
    @Valid
    private List<NonTechExpertLevel> nonTechExpertLevels;
    @CreationTimestamp
    private LocalDate createdAt;

}