package com.hengsu.duobao.mall.service.impl;

import com.google.common.collect.Collections2;
import com.hengsu.duobao.mall.model.BuyShoppingModel;
import com.hengsu.duobao.mall.service.GoodsService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
    public List<CodeModel> selectPage(CodeModel codeModel, Pageable pageable) {
        Code code = beanMapper.map(codeModel,Code.class);
        List<Code> codes = codeRepo.selectPage(code,pageable);
        return beanMapper.mapAsList(codes,CodeModel.class);
    }

    @Override
    @Transactional
    public void allotCode(Long userId,Long orderId, BuyShoppingModel buyShoppingModel) {
        String sql = "select * from mall_code where shop_id=? and status=0 for update";
        List<CodeModel> codeModels = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CodeModel.class), buyShoppingModel.getShopId());

        //打乱顺序,选择号码
        Collections.shuffle(codeModels);
        List<CodeModel> codes = codeModels.subList(0, buyShoppingModel.getTimes());

        //更新号码
        for (CodeModel codeModel : codes) {
            CodeModel param = new CodeModel();
            param.setId(codeModel.getId());
            param.setUserId(userId);
            param.setOrderId(orderId);
            param.setStatus(CODE_STATUS_USED);
            updateByPrimaryKeySelective(param);
        }
    }

    @Override
    @Transactional
    public void generateCode(Long shopId,Integer num) {
        String sql = "select g.type from mall_shopping s,mall_goods g\n" +
                "where s.goods_id = g.id\n" +
                "and s.id=?";
        Integer type = jdbcTemplate.queryForInt(sql,shopId);
        int times = num;
        if(GoodsService.GOODS_TYPE_10==type){
            times=10;
        }else if(GoodsService.GOODS_TYPE_50==type){
            times=2;
        }

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

    public static void main(String[] args) {
        CodeModel c1 = new CodeModel();
        c1.setId(1L);
        CodeModel c2 = new CodeModel();
        c2.setId(2L);
        CodeModel c3 = new CodeModel();
        c3.setId(3L);
        CodeModel c4 = new CodeModel();
        c4.setId(4L);
        List<CodeModel> codeModels = Arrays.asList(c1,c2,c3,c4);
        Collections.shuffle(codeModels);
        System.out.println(codeModels);
    }

}
