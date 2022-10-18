package com.skilltracker.app.addprofile.service.repository;

import com.skilltracker.app.addprofile.service.dto.TechResponse;
import com.skilltracker.app.addprofile.service.entity.TechExpertLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote TechSkillsRepository has the query to DB which joins user with techexpert levels
 * to get the required values
 */
@Repository
public interface TechSkillsRepository extends JpaRepository<TechExpertLevel,Long> {
    @Query("SELECT new com.skilltracker.app.addprofile.service.dto.TechResponse" +
            "(n.skillName,n.expertLevel,n.createdAt,u.userId,n) FROM User u join " +
            "u.techExpertLevels n")
    List<TechResponse> findTechExpertLevels();
}
