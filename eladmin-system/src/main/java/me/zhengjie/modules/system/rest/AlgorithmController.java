package me.zhengjie.modules.system.rest;

import me.zhengjie.aop.log.Log;
import me.zhengjie.modules.system.domain.Algorithm;
import me.zhengjie.modules.system.service.AlgorithmService;
import me.zhengjie.modules.system.service.dto.AlgorithmQueryCriteria;
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
@Api(tags = "Algorithm管理")
@RestController
@RequestMapping("api")
public class AlgorithmController {

    @Autowired
    private AlgorithmService algorithmService;

    @Log("查询Algorithm")
    @ApiOperation(value = "查询Algorithm")
    @GetMapping(value = "/algorithm")
    @PreAuthorize("hasAnyRole('ADMIN','ALGORITHM_ALL','ALGORITHM_SELECT')")
    public ResponseEntity getAlgorithms(AlgorithmQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity(algorithmService.queryAll(criteria,pageable),HttpStatus.OK);
    }

    @Log("新增Algorithm")
    @ApiOperation(value = "新增Algorithm")
    @PostMapping(value = "/algorithm")
    @PreAuthorize("hasAnyRole('ADMIN','ALGORITHM_ALL','ALGORITHM_CREATE')")
    public ResponseEntity create(@Validated @RequestBody Algorithm resources){
        return new ResponseEntity(algorithmService.create(resources),HttpStatus.CREATED);
    }

    @Log("修改Algorithm")
    @ApiOperation(value = "修改Algorithm")
    @PutMapping(value = "/algorithm")
    @PreAuthorize("hasAnyRole('ADMIN','ALGORITHM_ALL','ALGORITHM_EDIT')")
    public ResponseEntity update(@Validated @RequestBody Algorithm resources){
        algorithmService.update(resources);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @Log("删除Algorithm")
    @ApiOperation(value = "删除Algorithm")
    @DeleteMapping(value = "/algorithm/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ALGORITHM_ALL','ALGORITHM_DELETE')")
    public ResponseEntity delete(@PathVariable Long id){
        algorithmService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}