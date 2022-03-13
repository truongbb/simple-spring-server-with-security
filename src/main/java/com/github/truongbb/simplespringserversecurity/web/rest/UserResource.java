package com.github.truongbb.simplespringserversecurity.web.rest;

import com.github.truongbb.simplespringserversecurity.entity.User;
import com.github.truongbb.simplespringserversecurity.service.user.UserService;
import com.github.truongbb.simplespringserversecurity.util.Validators;
import com.github.truongbb.simplespringserversecurity.web.vm.UserVm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("${spring.data.rest.base-path}/user")
public class UserResource {

    UserService userService;

    @PostMapping(value = "/search")
    public ResponseEntity<List<User>> search(@RequestBody(required = false) UserVm userVm) {
        log.debug("search api entered...");
        List<User> users = userService.search(userVm);
        return !Validators.validCollection(users) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/get-user-info")
    public ResponseEntity<User> getUserInfo() {
        User user = userService.getUserInfo();
        return !Validators.validObject(user) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(user, HttpStatus.OK);
    }

}
