package com.sinaukoding.absensi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.absensi.common.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sinaukoding.absensi.common.RestResult;
import com.sinaukoding.absensi.entity.User;
import com.sinaukoding.absensi.service.UserService;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        User user = param != null ? new ObjectMapper().readValue(param, User.class) : null;
        Long rows = service.count(user);

        return new RestResult(rows > 0 ? service.find(user, offset, limit) : new ArrayList<>(), rows);
    }

    @PutMapping
    public RestResult update(@RequestBody User entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if(entity != null){
            result.setData(service.update(entity));
            result.setStatus(service.update(entity) != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
        }

        return result;
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id){
        boolean deleted = false;
        User user = service.searchById(id);

        if (user != null){
            service.inactiveUser(user);
            service.statusDelete(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
