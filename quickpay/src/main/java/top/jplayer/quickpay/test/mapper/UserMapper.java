package top.jplayer.quickpay.test.mapper;

import top.jplayer.quickpay.test.entity.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Author
 * @since 2019-09-23
 */
public interface UserMapper extends BaseMapper<User> {
    List<User> getUser(String order_id);
}
