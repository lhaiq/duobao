package com.hengsu.duobao.core.controller;

import com.google.common.cache.Cache;
import com.hengsu.duobao.ErrorCode;
import com.hengsu.duobao.core.RandomUtil;
import com.hengsu.duobao.core.ReturnCode;
import com.hengsu.duobao.core.annotation.IgnoreAuth;
import com.hengsu.duobao.core.model.AuthModel;
import com.hengsu.duobao.core.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.duobao.core.service.UserService;
import com.hengsu.duobao.core.model.UserModel;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestApiController
@RequestMapping("/duobao")
public class UserRestApiController {
    ///@Value("#{request.getAttribute('userId')}") Long userId
    private final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("sessionCache")
    private Cache<String, AuthModel> sessionCache;

    @Autowired
    @Qualifier("validateCache")
    private Cache<String, String> validateCache;

    /**
     * 注册获取验证码
     *
     * @param phone
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/registercode/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEnvelope<String> getRegisterAuthCode(@PathVariable String phone) {
        String code = RandomUtil.createRandom(true, 6);
        userService.generateValidateCode(phone, code);
        validateCache.put(phone, code);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return responseEnv;
    }

    /**
     * 注册
     *
     * @param registerVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<UserModel> register(@Valid @RequestBody RegisterVO registerVO) {

        //验证码
        if (!registerVO.getValidateCode().equals(validateCache.getIfPresent(registerVO.getPhone()))) {
            ErrorCode.throwBusinessException(ErrorCode.AUTH_CODE_ERROR);
        }

        //将注册信息保存
        UserModel userModel = beanMapper.map(registerVO, UserModel.class);
        userService.registerUser(userModel, registerVO.getAid());

        //登陆
        userModel = userService.userLogin(userModel);

        AuthModel authModel = new AuthModel(userModel.getId(), AuthModel.ROLE_USER);
        sessionCache.put(userModel.getAuthCode(), authModel);

        ResponseEnvelope<UserModel> responseEnv = new ResponseEnvelope<>(userModel, true);
        return responseEnv;
    }

    /**
     * 手机密码登录
     *
     * @param loginVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEnvelope<UserModel> accountLogin(@Valid @RequestBody LoginVO loginVO) {
        UserModel userModel = beanMapper.map(loginVO, UserModel.class);
        userModel = userService.userLogin(userModel);

        AuthModel authModel = new AuthModel(userModel.getId(), AuthModel.ROLE_USER);
        sessionCache.put(userModel.getAuthCode(), authModel);
        ResponseEnvelope<UserModel> responseEnv = new ResponseEnvelope<>(userModel, true);
        return responseEnv;
    }

    /**
     * 修改密码：获取验证码
     *
     * @param phone
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/changepasscode/{phone}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEnvelope<String> getChangePassAuthCode(@PathVariable String phone) {
        String code = RandomUtil.createRandom(true, 6);
        userService.generateValidateCode(phone, code);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return responseEnv;
    }


    /**
     * 忘记密码 修改
     *
     * @param changePassVO
     * @return
     */
    @IgnoreAuth
    @RequestMapping(value = "/auth/changepass", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEnvelope<String> changePass(@RequestBody ChangePassVO changePassVO) {

        //验证码
        if (validateCache.getIfPresent(changePassVO.getValidateCode()) == null) {
            ErrorCode.throwBusinessException(ErrorCode.AUTH_CODE_ERROR);
        }

        userService.changePass(changePassVO.getPhone(), changePassVO.getPassword());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return responseEnv;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<UserModel>> getUserById(@PathVariable Long id) {
        UserModel userModel = userService.findByPrimaryKey(id);
        userModel.setPassword(null);
        ResponseEnvelope<UserModel> responseEnv = new ResponseEnvelope<>(userModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 更新玩家资料
     *
     * @param id
     * @param userVO
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateUser(@PathVariable Long id,
                                                                @RequestBody UpdateUserVO userVO) {
        UserModel userModel = beanMapper.map(userVO, UserModel.class);
        userModel.setId(id);
        Integer result = userService.updateByPrimaryKeySelective(userModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    //商家申请

    //商家申请审核

    //代理商申请
    @RequestMapping(value = "/user/agent", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> agent(@Value("#{request.getAttribute('userId')}") Long userId,
                                                             @RequestBody AgentVO agentVO) {
        UserModel param = beanMapper.map(agentVO,UserModel.class);
        param.setId(userId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    //审核代理商

    //用户列表

    //玩家转账undo
    @RequestMapping(value = "/user/transfer", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> transfer(@Value("#{request.getAttribute('userId')}") Long userId,
                                                             @RequestBody TransferVO transferVO) {
       userService.transfer(userId,transferVO.getToId(),transferVO.getAmount());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OK, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    //查询好友 undo
    @RequestMapping(value = "/user/friends", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<Map<String, Object>>>> friends(@Value("#{request.getAttribute('userId')}") Long userId) {
        List<Map<String, Object>> friends = userService.friends(userId);
        ResponseEnvelope<List<Map<String, Object>>> responseEnv = new ResponseEnvelope<>(friends, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
