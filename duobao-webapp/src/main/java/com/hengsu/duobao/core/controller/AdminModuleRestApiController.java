package com.hengsu.duobao.core.controller;

import com.hengsu.duobao.core.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.duobao.core.service.AdminModuleService;
import com.hengsu.duobao.core.model.AdminModuleModel;
import com.hengsu.duobao.core.vo.AdminModuleVO;

import java.util.List;

@RestApiController
@RequestMapping("/duobao")
public class AdminModuleRestApiController {

    private final Logger logger = LoggerFactory.getLogger(AdminModuleRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AdminModuleService adminModuleService;

    //TODO增加一个不能给自己授权
    @RequestMapping(value = "/core/permission/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updatePermission(@PathVariable Long id,
                                                                     @RequestBody List<Long> moduleIds) {
        adminModuleService.updatePermission(id, moduleIds);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
