package com.hengsu.duobao.mall.service.impl;

import com.hengsu.duobao.mall.model.BuyShoppingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.duobao.mall.entity.Code;
import com.hengsu.duobao.mall.repository.CodeRepository;
import com.hengsu.duobao.mall.model.CodeModel;
import com.hengsu.duobao.mall.service.CodeService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.*;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CodeRepository codeRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int create(CodeModel codeModel) {
        return codeRepo.insert(beanMapper.map(codeModel, Code.class));
    }

    @Transactional
    @Override
    public int createSelective(CodeModel codeModel) {
        return codeRepo.insertSelective(beanMapper.map(codeModel, Code.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return codeRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public CodeModel findByPrimaryKey(Long id) {
        Code code = codeRepo.selectByPrimaryKey(id);
        return beanMapper.map(code, CodeModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(CodeModel codeModel) {
        return codeRepo.selectCount(beanMapper.map(codeModel, Code.class));
    }

    @Override
    @Transactional
    public void allotCode(Long userId, BuyShoppingModel buyShoppingModel) {
        String sql = "select * from mall_code where shop_id=? and status=0 for update";
        List<CodeModel> codeModels = jdbcTemplate.queryForList(sql, CodeModel.class, buyShoppingModel.getShopId());

        //打乱顺序,选择号码
        Collections.shuffle(codeModels);
        List<CodeModel> codes = codeModels.subList(0, buyShoppingModel.getTimes());

        //更新号码
        for (CodeModel codeModel : codes) {
            CodeModel param = new CodeModel();
            param.setId(codeModel.getId());
            param.setUserId(userId);
            param.setStatus(CODE_STATUS_USED);
            updateByPrimaryKeySelective(param);
        }
    }

    @Override
    @Transactional
    public void generateCode(Long shopId, int times) {

        for (int i = 1; i <= times; i++) {
            Long code = 10000000L + Long.valueOf(i);
            CodeModel codeModel = new CodeModel();
            codeModel.setShopId(shopId);
            codeModel.setCode(code);
            createSelective(codeModel);
        }
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(CodeModel codeModel) {
        return codeRepo.updateByPrimaryKey(beanMapper.map(codeModel, Code.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(CodeModel codeModel) {
        return codeRepo.updateByPrimaryKeySelective(beanMapper.map(codeModel, Code.class));
    }

}
