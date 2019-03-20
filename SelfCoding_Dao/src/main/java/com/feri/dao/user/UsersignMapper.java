package com.feri.dao.user;

import com.feri.entity.user.Usersign;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Feri
 * @since 2019-03-20
 */
public interface UsersignMapper extends BaseMapper<Usersign> {

    Usersign queryLastSign(int uid);
}