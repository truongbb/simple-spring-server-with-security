package com.github.truongbb.simplespringserversecurity.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

  Long userId;

  String username;
  String password;

  String firstName;
  String midName;
  String lastName;
  Boolean isActive;
  String avatarUrl;

  Date birthday;
  String address;
  String email;
  String phoneNumber;
  String gender;
  String roleName;

  String activeToken;

  List<String> roles;


}
