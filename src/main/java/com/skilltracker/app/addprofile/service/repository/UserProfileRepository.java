package com.skilltracker.app.addprofile.service.repository;

import com.skilltracker.app.addprofile.service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote UserProfileRepository has the query to fetch data under various criteria
 */
@Repository
public interface UserProfileRepository extends JpaRepository<User,Long> {
    User findByUserId(Long userId);

    @Query("SELECT u FROM User u where u.name LIKE %:name%")
    List<User> findSkillsByName(@Param("name") String name);

    @Query("SELECT u FROM User u where u.associateId = ?1")
    List<User> findSkillsByAssociateId(String associateId);

    @Query("SELECT u FROM User u join u.techExpertLevels n where n.skillName=?1 and n.expertLevel>10")
    List<User> findTechByName(String skillName);

    @Query("SELECT u FROM User u join u.nonTechExpertLevels n where n.skillName=?1 and n.expertLevel>10")
    List<User> findNonTechByName(String skillName);
}
