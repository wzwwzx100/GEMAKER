package com.mogu.GEMAKER.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mogu.GEMAKER.dao.mapper.ApplicationDoMapper;
import com.mogu.GEMAKER.dao.mapper.ProductDoMapper;
import com.mogu.GEMAKER.entity.ApplicationDo;
import com.mogu.GEMAKER.entity.ProductDo;
import com.mogu.GEMAKER.service.MarketService;
import com.mogu.GEMAKER.util.BizResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketServiceImpl implements MarketService {



    @Autowired
    private ApplicationDoMapper applicationDoMapper;

    @Autowired
    private ProductDoMapper productDoMapper;


    @Override
    public BizResult getProduct(ProductDo productDo) {
        return BizResult.success(productDoMapper.get(productDo));
    }

    @Override
    public BizResult getApplication(ApplicationDo applicationDo) {
        return BizResult.success(applicationDoMapper.get(applicationDo));
    }

    @Override
    public BizResult registerProduct(ProductDo productDo) {
        return productDoMapper.save(productDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult registerApplication(ApplicationDo applicationDo) {
        return applicationDoMapper.save(applicationDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult modifyProduct(ProductDo productDo) {
        return productDoMapper.update(productDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult modifyApplication(ApplicationDo applicationDo) {
        return applicationDoMapper.update(applicationDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult deleteProduct(ProductDo productDo) {
        return productDoMapper.delete(productDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult disableApplication(ApplicationDo applicationDo) {
        applicationDo.setValid(0);
        return applicationDoMapper.update(applicationDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult enableApplication(ApplicationDo applicationDo) {
        applicationDo.setValid(1);
        return applicationDoMapper.update(applicationDo) == 1 ? BizResult.success() : BizResult.error();
    }

    @Override
    public BizResult lstProduct(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null){
            List<ProductDo> lst = productDoMapper.lst();
            return BizResult.success(lst);
        }
        String orderBy = "id asc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<ProductDo> lst = productDoMapper.lst();
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }

    @Override
    public BizResult lstApplication(Integer pageNum, Integer pageSize) {
        if(pageNum == null || pageSize == null){
            List<ApplicationDo> lst = applicationDoMapper.lst();
            return BizResult.success(lst);
        }
        String orderBy = "id asc";
        PageHelper.startPage(pageNum,pageSize,orderBy);
        List<ApplicationDo> lst = applicationDoMapper.lst();
        PageInfo pageInfo = new PageInfo(lst);
        return BizResult.success(pageInfo);
    }
}
