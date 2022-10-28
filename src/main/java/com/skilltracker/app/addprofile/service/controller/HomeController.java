package com.skilltracker.app.addprofile.service.controller;

import com.skilltracker.app.addprofile.service.dto.SkillRequest;
import com.skilltracker.app.addprofile.service.entity.User;
import com.skilltracker.app.addprofile.service.exception.AddProfileException;
import com.skilltracker.app.addprofile.service.model.JwtRequest;
import com.skilltracker.app.addprofile.service.repository.UserProfileRepository;
import com.skilltracker.app.addprofile.service.responses.LoginResponse;
import com.skilltracker.app.addprofile.service.responses.UserSearchResponse;
import com.skilltracker.app.addprofile.service.service.UserProfileService;
import com.skilltracker.app.addprofile.service.utility.JWTUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.validation.Valid;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import com.azure.messaging.servicebus.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote HomeController is a rest controller having the method handlers for the rest calls
 * And the JWT authentication procedures
 */
@RestController
@Slf4j
@Api( tags = "Users")
@CrossOrigin
public class HomeController {

 @Autowired
 JmsTemplate jmsTemplate;

 @Autowired
 Queue queue;

 @Autowired
 private JWTUtility jwtUtility;

 @Autowired
 private AuthenticationManager authenticationManager;

 @Autowired
 private UserProfileService userProfileService;

 @Autowired
 private UserProfileRepository userProfileRepository;

 static String connectionString = "Endpoint=sb://kashmora.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=oOp2OQwsep/gLN6f6p0buYNBcYOZtY5gGJmKGR0lkpg=";
 static String queueName = "rajmachawal";

 @PostMapping("/skill-tracker/api/v1/engineer/add-profile")
 @ApiOperation(value = "This method is used to Post users.")
 public User saveUserProfile(@RequestBody @Valid SkillRequest request) {
  log.info("Tracing HomeController:saveUserProfile method");
  return userProfileService.saveUserProfile(request.getUser());
 }

 @GetMapping("/skill-tracker/api/v1/engineer/get-profile")
 @ApiOperation(value = "This method is used to Get users.")
 public List<User> findAllUsers() {
  log.info("Tracing HomeController:findAllUsers method");
  return userProfileService.findAllUsers();
 }

 @PutMapping("/skill-tracker/api/v1/engineer/update-profile/{userId}")
 @ApiOperation(value = "This method is used to update the user skills.")
 public String updateUserById(@PathVariable Long userId,
                            @RequestBody String jsonBody) {
   log.info("Tracing HomeController:updateUserById method and passing message to ActiveMQ");
   ServiceBusSenderClient senderClient = new ServiceBusClientBuilder()
          .connectionString(connectionString)
          .sender()
          .queueName(queueName)
          .buildClient();

  // send one message to the queue

  ServiceBusMessage message = new ServiceBusMessage(jsonBody);
  message.setCorrelationId(userId.toString());
  senderClient.sendMessage(message);
  System.out.println("Sent a single message to the queue: " + queueName);
  try {
   //URL url = new URL("http://localhost:8086/skill-tracker/api/v1/engineer/postMessage");
   URL url = new URL("http://updateprofiler.g4gzffaxhyftemfq.australiaeast.azurecontainer.io:8086/skill-tracker/api/v1/engineer/postMessage");
   HttpURLConnection conn = (HttpURLConnection) url.openConnection();
   conn.setRequestMethod("GET");
   conn.connect();
  } catch(Exception e) {
   e.printStackTrace();
  }
  //receiveMessages();
  /*jmsTemplate.convertAndSend(queue, jsonBody, new MessagePostProcessor() {
   @Override
   public Message postProcessMessage(Message message) throws JMSException {
    message.setLongProperty("UserId", userId);
    return message;
   }
  });*/
  return "Published Successfully";
 }

 @GetMapping("/skill-tracker/api/v1/admin/{criteria}/{criteriaValue}")
 @ApiOperation(value = "This method is used to fetch the user skills.")
 public ResponseEntity<?> fetchProfileByCriteria(@PathVariable String criteria,
                                          @PathVariable String criteriaValue) throws AddProfileException {
  log.info("Tracing HomeController:fetchProfileByCriteria method");
  List<User> userList= userProfileService.findProfileByCriteria(criteria,criteriaValue);
  UserSearchResponse userSearchResponse = new UserSearchResponse();
  userSearchResponse.setUserList(userList);
  return ResponseEntity.ok(userSearchResponse);
 }

 @PostMapping("/authenticate")
 public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

  try {
   authenticationManager.authenticate(
           new UsernamePasswordAuthenticationToken(
                   jwtRequest.getUserName(),
                   jwtRequest.getPassword()
           )
   );
  } catch (BadCredentialsException e) {
   throw new Exception("INVALID_CREDENTIALS", e);
  }

  final UserDetails userDetails
          = userProfileService.loadUserByUsername(jwtRequest.getUserName());

  final String token =
          jwtUtility.generateToken(userDetails);
  LoginResponse response=new LoginResponse();
  response.setToken(token);
  return ResponseEntity.ok(response);
 }
}