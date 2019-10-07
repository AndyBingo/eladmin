package me.zhengjie.modules.system.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.system.domain.DevicesAlgorithms;
import me.zhengjie.modules.system.service.DevicesAlgorithmsService;
import me.zhengjie.modules.system.service.dto.DevicesAlgorithmsQueryCriteria;
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
* @date 2019-10-07
*/
@Api(tags = "DevicesAlgorithms管理")
@RestController
@RequestMapping("api")
public class DevicesAlgorithmsController {

    @Autowired
    private DevicesAlgorithmsService devicesAlgorithmsService;

    @Log("查询DevicesAlgorithms")
    @ApiOperation(value = "查询DevicesAlgorithms")
    @GetMapping(value = "/devicesAlgorithms")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICESALGORITHMS_ALL','DEVICESALGORITHMS_SELECT')")
    public ResponseEntity getDevicesAlgorithmss(DevicesAlgorithmsQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(devicesAlgorithmsService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增DevicesAlgorithms")
    @ApiOperation(value = "新增DevicesAlgorithms")
    @PostMapping(value = "/devicesAlgorithms")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICESALGORITHMS_ALL','DEVICESALGORITHMS_CREATE')")
    public ResponseEntity create(@Validated @RequestBody DevicesAlgorithms resources){
        return new ResponseEntity(devicesAlgorithmsService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改DevicesAlgorithms")
    @ApiOperation(value = "修改DevicesAlgorithms")
    @PutMapping(value = "/devicesAlgorithms")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICESALGORITHMS_ALL','DEVICESALGORITHMS_EDIT')")
    public ResponseEntity update(@Validated @RequestBody DevicesAlgorithms resources){
        devicesAlgorithmsService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除DevicesAlgorithms")
    @ApiOperation(value = "删除DevicesAlgorithms")
    @DeleteMapping(value = "/devicesAlgorithms/{algorithmId}")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICESALGORITHMS_ALL','DEVICESALGORITHMS_DELETE')")
    public ResponseEntity delete(@PathVariable Long algorithmId){
        devicesAlgorithmsService.delete(algorithmId);
        return new ResponseEntity(HttpStatus.OK);
    }
}