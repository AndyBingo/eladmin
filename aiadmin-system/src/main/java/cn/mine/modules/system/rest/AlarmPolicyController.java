package cn.mine.modules.system.rest;

import cn.mine.aop.log.Log;
import cn.mine.modules.system.domain.AlarmPolicy;
import cn.mine.modules.system.service.AlarmPolicyService;
import cn.mine.modules.system.service.dto.AlarmPolicyQueryCriteria;
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
@Api(tags = "AlarmPolicy管理")
@RestController
@RequestMapping("api")
public class AlarmPolicyController {

    @Autowired
    private AlarmPolicyService alarmPolicyService;

    @Log("查询AlarmPolicy")
    @ApiOperation(value = "查询AlarmPolicy")
    @GetMapping(value = "/alarmPolicy")
    @PreAuthorize("hasAnyRole('ADMIN','ALARMPOLICY_ALL','ALARMPOLICY_SELECT')")
    public ResponseEntity getAlarmPolicys(AlarmPolicyQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(alarmPolicyService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增AlarmPolicy")
    @ApiOperation(value = "新增AlarmPolicy")
    @PostMapping(value = "/alarmPolicy")
    @PreAuthorize("hasAnyRole('ADMIN','ALARMPOLICY_ALL','ALARMPOLICY_CREATE')")
    public ResponseEntity create(@Validated @RequestBody AlarmPolicy resources){
        return new ResponseEntity(alarmPolicyService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改AlarmPolicy")
    @ApiOperation(value = "修改AlarmPolicy")
    @PutMapping(value = "/alarmPolicy")
    @PreAuthorize("hasAnyRole('ADMIN','ALARMPOLICY_ALL','ALARMPOLICY_EDIT')")
    public ResponseEntity update(@Validated @RequestBody AlarmPolicy resources){
        alarmPolicyService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除AlarmPolicy")
    @ApiOperation(value = "删除AlarmPolicy")
    @DeleteMapping(value = "/alarmPolicy/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ALARMPOLICY_ALL','ALARMPOLICY_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        alarmPolicyService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}