package top.jplayer.quickpay.test.service.impl;

import lombok.extern.slf4j.Slf4j;
import top.jplayer.quickpay.test.entity.User;
import top.jplayer.quickpay.test.mapper.UserMapper;
import top.jplayer.quickpay.test.service.IUserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Author
 * @since 2019-09-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public List<User> getUserList() {
        return baseMapper.getUser("2");
    }
}
