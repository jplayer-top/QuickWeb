package top.jplayer.quickpay.test.service;

import top.jplayer.quickpay.test.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Author
 * @since 2019-09-23
 */
public interface IUserService extends IService<User> {

    List<User> getUserList();
}
