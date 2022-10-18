package com.skilltracker.app.addprofile.service.repository;

import com.skilltracker.app.addprofile.service.dto.NonTechResponse;
import com.skilltracker.app.addprofile.service.entity.NonTechExpertLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote NonTechSkillsRepository has the query to DB which joins user with nontechexpert levels
 * to get the required values
 */
@Repository
public interface NonTechSkillsRepository extends JpaRepository<NonTechExpertLevel,Long> {
    @Query("SELECT new com.skilltracker.app.addprofile.service.dto.NonTechResponse" +
            "(n.skillName,n.expertLevel,n.createdAt,u.userId,n) " +
            "FROM User u join u.nonTechExpertLevels n")
    List<NonTechResponse> findNonTechExpertLevels();
}
