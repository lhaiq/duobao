package com.hengsu.duobao.core.service.impl;

import com.hengsu.duobao.core.RandomUtil;
import com.hengsu.duobao.core.entity.User;
import com.hengsu.duobao.core.model.RecommendRelationModel;
import com.hengsu.duobao.core.model.UserModel;
import com.hengsu.duobao.core.repository.UserRepository;
import com.hengsu.duobao.core.service.RecommendRelationService;
import com.hengsu.duobao.core.service.UserService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.hengsu.duobao.ErrorCode.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RecommendRelationService recommendRelationService;

    @Transactional
    @Override
    public int create(UserModel userModel) {
        return userRepo.insert(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int createSelective(UserModel userModel) {
        User user = beanMapper.map(userModel, User.class);
        int retVal = userRepo.insertSelective(user);
        userModel.setId(user.getId());
        return retVal;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return userRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserModel findByPrimaryKey(Long id) {
        User user = userRepo.selectByPrimaryKey(id);
        return beanMapper.map(user, UserModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(UserModel userModel) {
        return userRepo.selectCount(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public void registerUser(UserModel userModel, String aid) {

        //判断phone是否注册
        if (findByPhone(userModel.getPhone()) != null) {
            throwBusinessException(REGISTER_PHONE_EXISTED);
        }

        //生成推广Id
        userModel.setAid(generateAid());

        //创建
        userModel.setPassword(DigestUtils.md5Hex(userModel.getPassword()));
        userModel.setRegisterTime(new Date());
        createSelective(userModel);

        //判断是否推荐存在
        if (StringUtils.isNotEmpty(aid)) {
            UserModel recommendUser = findByAid(aid);
            if (recommendUser == null) {
                throwBusinessException(AID_USER_NOT_EXISTED);
            }

            RecommendRelationModel recommendRelationModel = new RecommendRelationModel();
            recommendRelationModel.setUserId(userModel.getId());
            recommendRelationModel.setRecommendId(recommendUser.getId());
            recommendRelationModel.setCreateTime(System.currentTimeMillis());
            recommendRelationService.createSelective(recommendRelationModel);
        }

    }

    @Override
    public UserModel userLogin(UserModel userModel) {
        UserModel loginUser = findByPhone(userModel.getPhone());

        //判断用户是否存在
        if (null == loginUser) {
            throwBusinessException(USER_NOT_EXISTED);
        }

        //验证密码
        if (!loginUser.getPassword().equals(userModel.getPassword())) {
            throwBusinessException(ADMIN_LOGIN_ERROR);
        }

        //生成验证码
        String authCode = RandomUtil.generateAuthToken();
        loginUser.setAuthCode(authCode);
        loginUser.setPassword("");
        return loginUser;
    }


    @Transactional
    @Override
    public void changePass(String phone, String password) {
        UserModel userModel = findByPhone(phone);
        if (null == userModel) {
            throwBusinessException(USER_NOT_EXISTED);
        }

        UserModel param = new UserModel();
        param.setId(userModel.getId());
        userModel.setPassword(DigestUtils.md5Hex(userModel.getPassword()));
        updateByPrimaryKeySelective(userModel);
    }

    @Transactional
    @Override
    public void transfer(Long fromId, Long toId, Long amount) {
        int ret=addBalance(fromId,amount*-1);
        if(ret==0){
            //余额不足
            throwBusinessException(BALANCE_NOT_ENOUGH);
        }
        addBalance(toId,amount);
    }

    @Transactional
    @Override
    public int addBalance(Long userId, Long amount) {
        String sql = "update user set balance = balance + ? where id = ?";
        return jdbcTemplate.update(sql,amount,userId);
    }

    @Override
    public UserModel findParent(Long userId) {
        try {
            String sql = "select u.* from recommend_relation r JOIN user u on u.id=r.recommend_id and r.user_id=?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserModel.class), userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<UserModel> findChildren(Long userId) {
        String sql = "select u.* from recommend_relation r JOIN user u on u.id=r.user_id and r.recommend_id=?";
        List<UserModel> userModels = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(UserModel.class), userId);
        return userModels;
    }

    @Override
    public List<Map<String, Object>> friends(Long userId) {
        List<Map<String, Object>> friends = new ArrayList<>();
        //先查上级
        UserModel parent = findParent(userId);
        if (null != parent) {
            Map<String, Object> map = beanToMap(parent);
            map.put("relation", "parent");
            friends.add(map);
        }

        List<UserModel> children = findChildren(userId);
        for (UserModel userModel : children) {
            Map<String, Object> map = beanToMap(userModel);
            map.put("relation", "child");
            friends.add(map);
        }
        return friends;
    }

    private Map<String, Object> beanToMap(UserModel userModel) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", userModel.getId());
        map.put("avatar", userModel.getAvatar());
        map.put("nickname", userModel.getNickname());
        map.put("level", userModel.getLevel());
        map.put("balance", userModel.getBalance());
        map.put("experience", userModel.getExperience());
        return map;
    }


    @Transactional
    @Override
    public int updateByPrimaryKey(UserModel userModel) {
        return userRepo.updateByPrimaryKey(beanMapper.map(userModel, User.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(UserModel userModel) {
        return userRepo.updateByPrimaryKeySelective(beanMapper.map(userModel, User.class));
    }

    @Override
    public void generateValidateCode(String phone, String code) {
        //TODO 发送短信
    }

    private UserModel findByAid(String aid) {
        try {
            String sql = "select id from user where aid = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserModel.class), aid);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private UserModel findByPhone(String phone) {
        try {
            String sql = "select * from user where phone = ?";
            return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(UserModel.class), phone);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    private String generateAid() {
        String aid = "";
        while (true) {
            if (StringUtils.isNotEmpty(aid)) return aid;
            aid = RandomUtil.createRandom1(false, 8);
            if (null != findByAid(aid)) {
                aid = "";
            }
        }
    }
}
