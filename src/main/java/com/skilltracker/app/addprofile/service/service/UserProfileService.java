package com.skilltracker.app.addprofile.service.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.skilltracker.app.addprofile.service.entity.NonTechExpertLevel;
import com.skilltracker.app.addprofile.service.entity.TechExpertLevel;
import com.skilltracker.app.addprofile.service.entity.User;
import com.skilltracker.app.addprofile.service.exception.AddProfileException;
import com.skilltracker.app.addprofile.service.repository.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote UserProfileService has the Business logic for the incoming rest calls
 */
@Service
@Slf4j
public class UserProfileService implements UserDetailsService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("Tracing UserProfileService:loadUserByUsername");
        return new org.springframework.security.core.userdetails.User("admin", "password", new ArrayList<>());
    }

    //public User saveUserProfile(SkillRequest request) {
        /*User user = User.build(0L,request.getName(),
                request.getAssociateId(),request.getMobile(),request.getEmail(),request.getTechExpertLevels(),
                request.getNonTechExpertLevels(), LocalDate.now());*/
    public User saveUserProfile(User user) {
        log.info("Tracing UserProfileService:saveUserProfile");
        return userProfileRepository.save(user);
    }

    public List<User> findAllUsers() {
        log.info("Tracing UserProfileService:findAllUsers");
        return userProfileRepository.findAll();
    }

    public List<User> findProfileByCriteria(String criteria,String criteriaValue) throws AddProfileException {
        log.info("Tracing UserProfileService:findProfileByCriteria");
        if("name".equalsIgnoreCase(criteria)) {
            log.info("Tracing UserProfileService:findProfileByCriteria for Criteria Name");
            List<User> userSkillList= userProfileRepository.findSkillsByName(criteriaValue);
            //String jsonTech = new Gson().toJson(userSkillList);
            //System.out.println("...jsonTech..."+jsonTech);
            sortedSkillInfo(userSkillList);
            return userSkillList;
        } else if("associateId".equalsIgnoreCase(criteria)) {
            log.info("Tracing UserProfileService:findProfileByCriteria for Criteria associateId");
            List<User> userSkillList= userProfileRepository.findSkillsByAssociateId(criteriaValue);
            //String jsonTech = new Gson().toJson(userSkillList);
            //System.out.println("...jsonTech..."+jsonTech);
            sortedSkillInfo(userSkillList);
            return userSkillList;
        } else if("skill".equalsIgnoreCase(criteria)) {
            log.info("Tracing UserProfileService:findProfileByCriteria for Criteria skill");
            List<User> userAllSkillList = new ArrayList<>();
            List<User> userSkillList= userProfileRepository.findTechByName(criteriaValue);
            List<User> userNonSkillList= userProfileRepository.findNonTechByName(criteriaValue);
            if(!CollectionUtils.isEmpty(userSkillList)) {
                userAllSkillList.addAll(userSkillList);
            } else if (!CollectionUtils.isEmpty(userNonSkillList)) {
                userAllSkillList.addAll(userNonSkillList);
            }
            String jsonTech = new Gson().toJson(userAllSkillList);
            //System.out.println("...jsonTech..."+jsonTech);
            String jsonTech1 = new Gson().toJson(userNonSkillList);
            //System.out.println("...jsonTech1..."+jsonTech1);
            return userAllSkillList;
        } else {
            log.info("Tracing UserProfileService:findProfileByCriteria for No skill match");
           throw new AddProfileException("No Skill Criteria Match Found");
        }
    }

    public void sortedSkillInfo(List<User> unsortedList) {
        for(User user:unsortedList) {
            sortTechExpertLevels(user.getTechExpertLevels());
            sortNonTechExpertLevels(user.getNonTechExpertLevels());
        }
    }

    public void sortTechExpertLevels(List<TechExpertLevel> unSortedTechList) {
        Collections.sort(unSortedTechList);
    }

    public void sortNonTechExpertLevels(List<NonTechExpertLevel> unSortedNonTechList) {
        Collections.sort(unSortedNonTechList);
    }
}