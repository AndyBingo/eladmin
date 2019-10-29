package cn.mine.modules.system.rest;

import cn.mine.aop.log.Log;
import cn.mine.modules.system.domain.Nvr;
import cn.mine.modules.system.service.NvrService;
import cn.mine.modules.system.service.dto.NvrQueryCriteria;
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
@Api(tags = "Nvr管理")
@RestController
@RequestMapping("api")
public class NvrController {

    @Autowired
    private NvrService nvrService;

    @Log("查询Nvr")
    @ApiOperation(value = "查询Nvr")
    @GetMapping(value = "/nvr")
    @PreAuthorize("hasAnyRole('ADMIN','NVR_ALL','NVR_SELECT')")
    public ResponseEntity getNvrs(NvrQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(nvrService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Nvr")
    @ApiOperation(value = "新增Nvr")
    @PostMapping(value = "/nvr")
    @PreAuthorize("hasAnyRole('ADMIN','NVR_ALL','NVR_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Nvr resources){
        return new ResponseEntity(nvrService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Nvr")
    @ApiOperation(value = "修改Nvr")
    @PutMapping(value = "/nvr")
    @PreAuthorize("hasAnyRole('ADMIN','NVR_ALL','NVR_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Nvr resources){
        nvrService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Nvr")
    @ApiOperation(value = "删除Nvr")
    @DeleteMapping(value = "/nvr/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','NVR_ALL','NVR_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        nvrService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}