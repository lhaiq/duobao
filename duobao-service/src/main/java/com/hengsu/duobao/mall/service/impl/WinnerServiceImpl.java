package com.hengsu.duobao.mall.service.impl;

import com.hengsu.duobao.mall.entity.Winner;
import com.hengsu.duobao.mall.model.WinnerModel;
import com.hengsu.duobao.mall.repository.WinnerRepository;
import com.hengsu.duobao.mall.service.WinnerService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.SwitchTransformer;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.stat.StatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class WinnerServiceImpl implements WinnerService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private WinnerRepository winnerRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public int create(WinnerModel winnerModel) {
        return winnerRepo.insert(beanMapper.map(winnerModel, Winner.class));
    }

    @Transactional
    @Override
    public int createSelective(WinnerModel winnerModel) {
        return winnerRepo.insertSelective(beanMapper.map(winnerModel, Winner.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return winnerRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public WinnerModel findByPrimaryKey(Long id) {
        Winner winner = winnerRepo.selectByPrimaryKey(id);
        return beanMapper.map(winner, WinnerModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(WinnerModel winnerModel) {
        return winnerRepo.selectCount(beanMapper.map(winnerModel, Winner.class));
    }

    @Override
    @Async
    public void lottery(Long shopId) {

        //TODO 时间和老时彩
        String sql = "select apply_time from mall_order order by id desc limit 0,50";
        List<Long> applyTimes = jdbcTemplate.queryForList(sql,Long.class);
        CollectionUtils.transform(applyTimes,new Transformer<Long,Long>(){

            @Override
            public Long transform(Long input) {
                return null;
            }
        });

        Long timeCode = 0L;
        for(Long time:applyTimes){
            timeCode+=time;
        }

        //查询彩票号码
        Long referCode = 00000L;
        Long count = jdbcTemplate.queryForLong("select count(1) from mall_code where shop_id=?", shopId);
        Long code = timeCode + referCode % count + 10000001L;
        Long userId = jdbcTemplate.queryForLong("select user_id from mall_code where code = ?", code);

        //保存中奖号码
        WinnerModel winnerModel = new WinnerModel();
        winnerModel.setShopId(shopId);
        winnerModel.setDatetime(new Date());
        winnerModel.setTimeCode(timeCode);
        winnerModel.setCode(code);
        winnerModel.setUserId(userId);
        createSelective(winnerModel);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(WinnerModel winnerModel) {
        return winnerRepo.updateByPrimaryKey(beanMapper.map(winnerModel, Winner.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(WinnerModel winnerModel) {
        return winnerRepo.updateByPrimaryKeySelective(beanMapper.map(winnerModel, Winner.class));
    }

}
