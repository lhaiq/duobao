package com.hengsu.duobao.core.controller;

import com.google.common.cache.Cache;
import com.hengsu.duobao.ErrorCode;
import com.hengsu.duobao.core.ReturnCode;
import com.hengsu.duobao.core.annotation.IgnoreAuth;
import com.hengsu.duobao.core.model.AuthModel;
import com.hengsu.duobao.core.vo.UpdatePassVO;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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

import com.hengsu.duobao.core.service.AdminService;
import com.hengsu.duobao.core.model.AdminModel;
import com.hengsu.duobao.core.vo.AdminVO;

@RestApiController
@RequestMapping("/duobao")
public class AdminRestApiController {

    private final Logger logger = LoggerFactory.getLogger(AdminRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private AdminService adminService;

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, AuthModel> sessionCache;

    /**
     * 管理员详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/core/admin/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<AdminVO>> getAdminById(@PathVariable Long id) {
        AdminModel adminModel = adminService.findByPrimaryKey(id);
        AdminVO adminVO = beanMapper.map(adminModel, AdminVO.class);
        ResponseEnvelope<AdminVO> responseEnv = new ResponseEnvelope<>(adminVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加管理员
     *
     * @param adminVO
     * @return
     */
    @RequestMapping(value = "/core/admin", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createAdmin(@RequestBody AdminVO adminVO) {
        AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
        adminModel.setPassword(DigestUtils.md5Hex(adminModel.getPassword()));
        Integer result = adminService.createSelective(adminModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除管理员
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/core/admin/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteAdminByPrimaryKey(@PathVariable Long id) {
        Integer result = adminService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 管理员登录
     *
     * @param adminVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<AdminModel>> adminLogin(
            @RequestBody AdminVO adminVO) {
        AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
        adminModel = adminService.adminLogin(adminModel);
        AuthModel authModel = new AuthModel(adminModel.getId(), adminModel.getRole());
        sessionCache.put(adminModel.getAuthCode(), authModel);
        ResponseEnvelope<AdminModel> responseEnv = new ResponseEnvelope<>(adminModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 超级管理员更新密码
     *
     * @param id
     * @param adminVO
     * @return
     */
    @RequestMapping(value = "/core/admin/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updatePass(@PathVariable Long id,
                                                                @RequestBody AdminVO adminVO) {
        AdminModel adminModel = beanMapper.map(adminVO, AdminModel.class);
        adminModel.setId(id);
        adminModel.setPassword(DigestUtils.md5Hex(adminModel.getPassword()));
        Integer result = adminService.updateByPrimaryKeySelective(adminModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 更新自己密码
     * @param userId
     * @param updatePassVO
     * @return
     */
    @RequestMapping(value = "/core/admin", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<String>> updateSelfPass(@Value("#{request.getAttribute('userId')}") Long userId,
                                                                   @RequestBody UpdatePassVO updatePassVO) {
        //验证就密码是否正确
        AdminModel param = new AdminModel();
        param.setId(userId);
        param.setPassword(DigestUtils.md5Hex(updatePassVO.getOldPass()));
        if (CollectionUtils.isEmpty(adminService.selectPage(param, new PageRequest(0, 1)))) {
            ErrorCode.throwBusinessException(ErrorCode.ADMIN_PASS_ERROR);
        }

        param.setPassword(DigestUtils.md5Hex(updatePassVO.getNewPass()));
        adminService.updateByPrimaryKeySelective(param);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
