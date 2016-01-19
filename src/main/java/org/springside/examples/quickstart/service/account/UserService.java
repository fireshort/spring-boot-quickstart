package org.springside.examples.quickstart.service.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springside.examples.quickstart.entity.User;
import org.springside.examples.quickstart.repository.UserDao;

import java.util.Date;

/**
 * Created by Ivan on 2016/1/19.
 * @Cacheable 同时和 @Transactional 在一起的话不生效，所以缓存方法单独放到这个类。
 */
@Component
public class UserService {
    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private AccountService accountService;

    @Cacheable(value = "userCache", key = "'user'+#id")
    public User getUser(Long id) {
        logger.info("无缓存的时候调用这里 at UserService1." + new Date());
        return accountService.getUser(id);
    }

    @CacheEvict(value ="userCache", key = "'user'+#user.id" )
    public void updateUser(User user) {
        accountService.updateUser(user);
    }
}
