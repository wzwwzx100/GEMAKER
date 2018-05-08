package com.mogu.GEMAKER.dao.mapper;

import com.mogu.GEMAKER.model.entity.ProductDo;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDoMapper {

    ProductDo get(ProductDo productDo);

    int save(ProductDo productDo);
    int update(ProductDo productDo);

    int delete(ProductDo productDo);

    @Select("select * from tbl_product")
    @ResultMap("BaseResultMap")
    List<ProductDo> lst();
}
