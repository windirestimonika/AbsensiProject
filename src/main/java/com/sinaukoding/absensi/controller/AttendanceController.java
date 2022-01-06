package com.sinaukoding.absensi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.absensi.common.RestResult;
import com.sinaukoding.absensi.common.StatusCode;
import com.sinaukoding.absensi.entity.Attendance;
import com.sinaukoding.absensi.service.AttendanceService;
import com.sinaukoding.absensi.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("attendances")
public class AttendanceController extends BaseController {

    @Autowired
    private AttendanceService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        Attendance attendance = param != null ? new ObjectMapper().readValue(param, Attendance.class) : null;

        long rows = service.count(attendance);

        return new RestResult(rows > 0 ? service.find(attendance, offset, limit) : new ArrayList<>(), rows);
    }

    @GetMapping("/by-date")
    public RestResult findByDate(@RequestParam(value = "param", required = false) String param,
                                 @RequestParam(value = "start-date") String startDate,
                                 @RequestParam(value = "end-date") String endDate) throws JsonProcessingException {
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        Attendance attendance = param != null ? new ObjectMapper().readValue(param, Attendance.class) : new Attendance();

        result.setData(service.findByDate(attendance,
                DateUtils.fromString(startDate),
                DateUtils.fromString(endDate)));
        result.setRows((long) service.findByDate(attendance,
                DateUtils.fromString(startDate),
                DateUtils.fromString(endDate)).size());

        return result;
    }

    @PostMapping
    public RestResult save(@RequestBody Attendance entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }
        return result;
    }

    @PutMapping
    public RestResult update(@RequestBody Attendance entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if(entity != null){
            result.setData(service.update(entity));
            result.setStatus(service.update(entity) != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
        }

        return result;
    }

    @PutMapping("/start-rest")
    public RestResult startRest(@RequestBody Attendance entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.startRest(entity));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @PutMapping("/end-rest")
    public RestResult endRest(@RequestBody Attendance entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.endRest(entity));
            result.setStatus(StatusCode.UPDATE_SUCCESS);
        }

        return result;
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id){
        boolean deleted = false;
        Attendance attendance = service.searchById(id);

        if (attendance != null){
            service.statusDelete(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
