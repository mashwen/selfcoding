package com.feri.dao.user;

import com.feri.entity.user.Userwallet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Feri
 * @since 2019-03-20
 */
public interface UserwalletMapper extends BaseMapper<Userwallet> {

    int updateByCions(@Param("cions") int cions, @Param("id") int id);
}