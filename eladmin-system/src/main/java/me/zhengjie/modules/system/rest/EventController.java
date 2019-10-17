package me.zhengjie.modules.system.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.system.domain.Event;
import me.zhengjie.modules.system.service.EventService;
import me.zhengjie.modules.system.service.dto.EventQueryCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

/**
* @author andy
* @date 2019-10-17
*/
@Api(tags = "Event管理")
@RestController
@RequestMapping("api")
public class EventController {

    @Autowired
    private EventService eventService;

    @Log("查询Event")
    @ApiOperation(value = "查询Event")
    @GetMapping(value = "/event")
    @PreAuthorize("hasAnyRole('ADMIN','EVENT_ALL','EVENT_SELECT')")
    public ResponseEntity getEvents(EventQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(eventService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Event")
    @ApiOperation(value = "新增Event")
    @PostMapping(value = "/event")
    @PreAuthorize("hasAnyRole('ADMIN','EVENT_ALL','EVENT_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Event resources){
        return new ResponseEntity(eventService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Event")
    @ApiOperation(value = "修改Event")
    @PutMapping(value = "/event")
    @PreAuthorize("hasAnyRole('ADMIN','EVENT_ALL','EVENT_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Event resources){
        eventService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Event")
    @ApiOperation(value = "删除Event")
    @DeleteMapping(value = "/event/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EVENT_ALL','EVENT_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        eventService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}