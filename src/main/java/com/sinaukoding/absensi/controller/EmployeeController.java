package com.sinaukoding.absensi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.absensi.common.RestResult;
import com.sinaukoding.absensi.common.StatusCode;
import com.sinaukoding.absensi.entity.Employee;
import com.sinaukoding.absensi.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("employees")
public class EmployeeController extends BaseController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        Employee employee = param != null ? new ObjectMapper().readValue(param, Employee.class) : null;

        long rows = service.count(employee);

        return new RestResult(rows > 0 ? service.find(employee, offset, limit) : new ArrayList<>(), rows);
    }

    @PostMapping
    public RestResult save(@RequestBody Employee entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }
        return result;
    }

    @PutMapping
    public RestResult update(@RequestBody Employee entity){
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
        Employee employee = service.searchById(id);

        if (employee != null){
            service.inactiveStatus(employee);
            service.statusDelete(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
