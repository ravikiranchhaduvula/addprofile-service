package com.skilltracker.app.addprofile.service;

import com.skilltracker.app.addprofile.service.controller.HomeController;
import com.skilltracker.app.addprofile.service.dto.SkillRequest;
import com.skilltracker.app.addprofile.service.entity.NonTechExpertLevel;
import com.skilltracker.app.addprofile.service.entity.TechExpertLevel;
import com.skilltracker.app.addprofile.service.entity.User;
import com.skilltracker.app.addprofile.service.exception.AddProfileException;
import com.skilltracker.app.addprofile.service.repository.UserProfileRepository;
import com.skilltracker.app.addprofile.service.service.UserProfileService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class AddprofileServiceApplicationTests {

	@Autowired
	UserProfileService userProfileService;

    @MockBean
	UserProfileRepository userProfileRepository;

	@Autowired
	HomeController homeController;

	@Test
	public void findAllUsersTest() {
		List<TechExpertLevel> techExpertLevels = new ArrayList<>();
		TechExpertLevel techExpertLevel = new TechExpertLevel();
		techExpertLevel.setId(10);
		techExpertLevel.setExpertLevel(10);
		techExpertLevel.setSkillName("Java");
		techExpertLevel.setCreatedAt(LocalDate.now());
		techExpertLevels.add(techExpertLevel);
		List<NonTechExpertLevel> nonTechExpertLevels = new ArrayList<>();
		NonTechExpertLevel nonTechExpertLevel = new NonTechExpertLevel();
		nonTechExpertLevel.setId(9);
		nonTechExpertLevel.setExpertLevel(8);
		nonTechExpertLevel.setSkillName("Speaking");
		nonTechExpertLevel.setCreatedAt(LocalDate.now());
		nonTechExpertLevels.add(nonTechExpertLevel);
      when(userProfileRepository.findAll()).thenReturn(
			  Stream.of(new User(1L,"Ravi","CTS123","1122222","ravi@gmail.com",
					  techExpertLevels,nonTechExpertLevels,LocalDate.now())).collect(Collectors.toList()));
		Assert.assertEquals(1,userProfileService.findAllUsers().size());
	}

	@Test
	public void findProfileByCriteriaTest() throws AddProfileException {
		List<TechExpertLevel> techExpertLevels = new ArrayList<>();
		TechExpertLevel techExpertLevel = new TechExpertLevel();
		techExpertLevel.setId(10);
		techExpertLevel.setExpertLevel(10);
		techExpertLevel.setSkillName("Java");
		techExpertLevel.setCreatedAt(LocalDate.now());
		techExpertLevels.add(techExpertLevel);
		List<NonTechExpertLevel> nonTechExpertLevels = new ArrayList<>();
		NonTechExpertLevel nonTechExpertLevel = new NonTechExpertLevel();
		nonTechExpertLevel.setId(9);
		nonTechExpertLevel.setExpertLevel(8);
		nonTechExpertLevel.setSkillName("Speaking");
		nonTechExpertLevel.setCreatedAt(LocalDate.now());
		nonTechExpertLevels.add(nonTechExpertLevel);
		when(userProfileRepository.findSkillsByName("Ravi")).thenReturn(
				Stream.of(new User(1L,"Ravi","CTS123","1122222","ravi@gmail.com",
						techExpertLevels,nonTechExpertLevels,LocalDate.now())).collect(Collectors.toList()));
		Assert.assertEquals(1,userProfileService.findProfileByCriteria("name","Ravi").size());
	}

	@Test
	public void findProfileByCriteriaTest1() throws AddProfileException {
		List<TechExpertLevel> techExpertLevels = new ArrayList<>();
		TechExpertLevel techExpertLevel = new TechExpertLevel();
		techExpertLevel.setId(10);
		techExpertLevel.setExpertLevel(10);
		techExpertLevel.setSkillName("Java");
		techExpertLevel.setCreatedAt(LocalDate.now());
		techExpertLevels.add(techExpertLevel);
		List<NonTechExpertLevel> nonTechExpertLevels = new ArrayList<>();
		NonTechExpertLevel nonTechExpertLevel = new NonTechExpertLevel();
		nonTechExpertLevel.setId(9);
		nonTechExpertLevel.setExpertLevel(8);
		nonTechExpertLevel.setSkillName("Speaking");
		nonTechExpertLevel.setCreatedAt(LocalDate.now());
		nonTechExpertLevels.add(nonTechExpertLevel);
		when(userProfileRepository.findSkillsByAssociateId("CTS123")).thenReturn(
				Stream.of(new User(1L,"Ravi","CTS123","1122222","ravi@gmail.com",
						techExpertLevels,nonTechExpertLevels,LocalDate.now())).collect(Collectors.toList()));
		Assert.assertEquals(1,userProfileService.findProfileByCriteria("associateId","CTS123").size());
	}

	@Test
	public void findProfileByCriteriaTest3() throws AddProfileException {
		List<TechExpertLevel> techExpertLevels = new ArrayList<>();
		TechExpertLevel techExpertLevel = new TechExpertLevel();
		techExpertLevel.setId(10);
		techExpertLevel.setExpertLevel(10);
		techExpertLevel.setSkillName("Java");
		techExpertLevel.setCreatedAt(LocalDate.now());
		techExpertLevels.add(techExpertLevel);
		List<NonTechExpertLevel> nonTechExpertLevels = new ArrayList<>();
		NonTechExpertLevel nonTechExpertLevel = new NonTechExpertLevel();
		nonTechExpertLevel.setId(9);
		nonTechExpertLevel.setExpertLevel(8);
		nonTechExpertLevel.setSkillName("Speaking");
		nonTechExpertLevel.setCreatedAt(LocalDate.now());
		nonTechExpertLevels.add(nonTechExpertLevel);
		when(userProfileRepository.findTechByName("Java")).thenReturn(
				Stream.of(new User(1L,"Ravi","CTS123","1122222","ravi@gmail.com",
						techExpertLevels,nonTechExpertLevels,LocalDate.now())).collect(Collectors.toList()));
		Assert.assertEquals(1,userProfileService.findProfileByCriteria("skill","Java").size());
	}

	@Test
	public void findProfileByCriteriaTest4() throws AddProfileException {
		List<TechExpertLevel> techExpertLevels = new ArrayList<>();
		TechExpertLevel techExpertLevel = new TechExpertLevel();
		techExpertLevel.setId(10);
		techExpertLevel.setExpertLevel(10);
		techExpertLevel.setSkillName("Java");
		techExpertLevel.setCreatedAt(LocalDate.now());
		techExpertLevels.add(techExpertLevel);
		List<NonTechExpertLevel> nonTechExpertLevels = new ArrayList<>();
		NonTechExpertLevel nonTechExpertLevel = new NonTechExpertLevel();
		nonTechExpertLevel.setId(9);
		nonTechExpertLevel.setExpertLevel(8);
		nonTechExpertLevel.setSkillName("Speaking");
		nonTechExpertLevel.setCreatedAt(LocalDate.now());
		nonTechExpertLevels.add(nonTechExpertLevel);
		when(userProfileRepository.findNonTechByName("Speaking")).thenReturn(
				Stream.of(new User(1L,"Ravi","CTS123","1122222","ravi@gmail.com",
						techExpertLevels,nonTechExpertLevels,LocalDate.now())).collect(Collectors.toList()));
		Assert.assertEquals(1,userProfileService.findProfileByCriteria("skill","Speaking").size());
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	@Test
	public void findProfileByCriteriaTest5() {
		thrown.expect(AddProfileException.class);
		//you can test the exception message like
		thrown.expectMessage("expected messages");
		try {
			userProfileService.findProfileByCriteria("Fill", "Speaking");
		} catch(Exception e) {

		}
	}

	@Test
	public void testSaveUserProfile() {
		User user = new User();
		user.setName("Ravi");
		when(userProfileRepository.save(user)).thenReturn(user);
		Assert.assertEquals("Ravi",userProfileService.saveUserProfile(user).getName());
	}

	@Test
	public void testLoadUserByUsername() {
		Assert.assertEquals("admin",userProfileService.loadUserByUsername("Ravi").getUsername());
	}

	@Test
	public void testsaveUserProfile() {
		List<TechExpertLevel> techExpertLevels = new ArrayList<>();
		TechExpertLevel techExpertLevel = new TechExpertLevel();
		techExpertLevel.setId(10);
		techExpertLevel.setExpertLevel(10);
		techExpertLevel.setSkillName("Java");
		techExpertLevel.setCreatedAt(LocalDate.now());
		techExpertLevels.add(techExpertLevel);
		List<NonTechExpertLevel> nonTechExpertLevels = new ArrayList<>();
		NonTechExpertLevel nonTechExpertLevel = new NonTechExpertLevel();
		nonTechExpertLevel.setId(9);
		nonTechExpertLevel.setExpertLevel(8);
		nonTechExpertLevel.setSkillName("Speaking");
		nonTechExpertLevel.setCreatedAt(LocalDate.now());
		nonTechExpertLevels.add(nonTechExpertLevel);
		SkillRequest request = new SkillRequest();
		User user = new User(1L,"Ravi","CTS123","1122222","ravi@gmail.com",
				techExpertLevels,nonTechExpertLevels,LocalDate.now());
		request.setUser(user);
		Assert.assertEquals(null,homeController.saveUserProfile(request));
	}
}
