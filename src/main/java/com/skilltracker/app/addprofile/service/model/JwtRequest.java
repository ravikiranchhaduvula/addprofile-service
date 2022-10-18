package com.skilltracker.app.addprofile.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @author Ravi Kiran
 * @version 1.0
 * @since 29/08/2022
 * @implNote JwtRequest is a POJO which take user name and password passed
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequest {
    private String userName;
    private String password;

}
