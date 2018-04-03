package com.mogu.GEMAKER.service;

import com.mogu.GEMAKER.entity.ApplicationDo;
import com.mogu.GEMAKER.entity.ProductDo;
import com.mogu.GEMAKER.util.BizResult;

public interface MarketService {

    BizResult getProduct(ProductDo productDo);

    BizResult getApplication(ApplicationDo applicationDo);

    BizResult registerProduct(ProductDo productDo);

    BizResult registerApplication(ApplicationDo applicationDo);

    BizResult modifyProduct(ProductDo productDo);

    BizResult modifyApplication(ApplicationDo applicationDo);

    BizResult deleteProduct(ProductDo productDo);

    BizResult disableApplication(ApplicationDo applicationDo);

    BizResult enableApplication(ApplicationDo applicationDo);

    BizResult lstProduct(Integer pageNum,Integer pageSize);

    BizResult lstApplication(Integer pageNum,Integer pageSize);
}
