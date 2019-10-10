package top.jplayer.quickpay.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import top.jplayer.quickpay.test.entity.User;
import top.jplayer.quickpay.test.service.IUserService;

/**
 * Created by Obl on 2019/9/29.
 * top.jplayer.quickpay.schedule
 * call me : jplayer_top@163.com
 * github : https://github.com/oblivion0001
 */
@Component
public class TestScheduleTask {

    private int count = 0;

    @Autowired
    IUserService mIUserService;

    @Scheduled(cron = "* * * 1 * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6000 * 1000)
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
        List<User> userList = mIUserService.getUserList();
        System.out.println("当前在线用户："+userList.size());
    }
}
