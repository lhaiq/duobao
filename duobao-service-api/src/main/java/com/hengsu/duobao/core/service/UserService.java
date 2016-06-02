
package com.hengsu.duobao.core.service;

import com.hengsu.duobao.core.model.UserModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    public int create(UserModel userModel);

    public int createSelective(UserModel userModel);

    public UserModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(UserModel userModel);

    public int updateByPrimaryKeySelective(UserModel userModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(UserModel userModel);

    public void registerUser(UserModel userModel, String aid);

    public UserModel userLogin(UserModel userModel);


    public void generateValidateCode(String phone, String code);

    public void changePass(String phone, String password);

    //玩家列表

    //玩家转账
    public void transfer(Long fromId, Long toId, Long amount);


    public int addBalance(Long userId,Long amount);

    //查询上级
    public UserModel findParent(Long userId);

    //查询下级
    public List<UserModel> findChildren(Long userId);

    public List<Map<String, Object>> friends(Long userId);


}