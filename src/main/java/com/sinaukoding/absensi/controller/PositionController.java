package com.sinaukoding.absensi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.absensi.common.RestResult;
import com.sinaukoding.absensi.common.StatusCode;
import com.sinaukoding.absensi.entity.Position;
import com.sinaukoding.absensi.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("positions")
public class PositionController extends BaseController {

    @Autowired
    private PositionService service;

    @GetMapping
    public RestResult get(@RequestParam(value = "param", required = false) String param,
                          @RequestParam(value = "offset") int offset,
                          @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        Position position = param != null ? new ObjectMapper().readValue(param, Position.class) : null;

        long rows = service.count(position);

        return new RestResult(rows > 0 ? service.find(position, offset, limit) : new ArrayList<>(), rows);
    }

    @PostMapping
    public RestResult save(@RequestBody Position param){
        param = service.save(param);

        return new RestResult(param, param != null ? StatusCode.SAVE_SUCCESS : StatusCode.SAVE_FAILED);
    }

    @PutMapping
    public RestResult update(@RequestBody Position position){
        position = service.update(position);

        return new RestResult(position, position != null ? StatusCode.UPDATE_SUCCESS : StatusCode.UPDATE_FAILED);
    }

    @DeleteMapping(value = "{id}")
    public RestResult delete(@PathVariable Long id){
        boolean deleted = false;
        Position position = service.searchById(id);

        if (position != null){
            service.statusDelete(id);
            deleted = service.delete(id);
        }

        return new RestResult(deleted ? StatusCode.DELETE_SUCCESS : StatusCode.DELETE_FAILED);
    }
}
