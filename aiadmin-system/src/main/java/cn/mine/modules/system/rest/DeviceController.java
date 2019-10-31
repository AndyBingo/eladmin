package cn.mine.modules.system.rest;

import cn.mine.aop.log.Log;
import cn.mine.modules.system.domain.Device;
import cn.mine.modules.system.service.DeviceService;
import cn.mine.modules.system.service.dto.DeviceQueryCriteria;
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
@Api(tags = "Device管理")
@RestController
@RequestMapping("api")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Log("查询Device")
    @ApiOperation(value = "查询Device")
    @GetMapping(value = "/device")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_SELECT')")
    public ResponseEntity getDevices(DeviceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(deviceService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Device")
    @ApiOperation(value = "新增Device")
    @PostMapping(value = "/device")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Device resources){
        return new ResponseEntity(deviceService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Device")
    @ApiOperation(value = "修改Device")
    @PutMapping(value = "/device")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Device resources){
        deviceService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Device")
    @ApiOperation(value = "删除Device")
    @DeleteMapping(value = "/device/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEVICE_ALL','DEVICE_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        deviceService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}