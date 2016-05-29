
package com.hengsu.duobao.mall.service;

import com.hengsu.duobao.mall.model.BuyShoppingModel;
import com.hengsu.duobao.mall.model.CodeModel;

public interface CodeService {

    public static final int CODE_STATUS_UNUSE = 0;
    public static final int CODE_STATUS_USED = 1;

    public int create(CodeModel codeModel);

    public int createSelective(CodeModel codeModel);

    public CodeModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(CodeModel codeModel);

    public int updateByPrimaryKeySelective(CodeModel codeModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(CodeModel codeModel);

    public void allotCode(Long userId, BuyShoppingModel buyShoppingModel);

    public void generateCode(Long shopId,int times);



}