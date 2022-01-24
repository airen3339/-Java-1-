package com.cy.config.security.customerUserDetailsService;

import com.cy.liveManagement.live_user.service.LiveUserService;
import com.cy.systemManagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author cy
 * @program: WuYeManagementProgram_java
 * @description:
 * @date 2022-01-24 11:58:57
 */
@Component("CustomerUserDetailService")
public class CustomerUserDetailService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Autowired
    private LiveUserService liveUserService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //认证
        int index = s.indexOf(":");
        String username = s.substring(0, index);
        String userType = s.substring(index+1,s.length());
        UserDetails user = null;
        if(userType.equals("0")){ //业主
            user = liveUserService.loadUser(username);
        }else if(userType.equals("1")) { //物主
            user =  userService.loadUser(username);
        }else{
            throw new UsernameNotFoundException("用户类型不存在!");
        }
        if(user == null){
            throw new UsernameNotFoundException("用户名或密码错误!");
        }
        //授权
        return user;
    }
}
