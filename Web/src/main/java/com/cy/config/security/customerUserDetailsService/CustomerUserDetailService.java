package com.cy.config.security.customerUserDetailsService;

import com.cy.liveManagement.live_user.entity.LiveUser;
import com.cy.liveManagement.live_user.service.LiveUserService;
import com.cy.systemManagement.menu.entity.Menu;
import com.cy.systemManagement.menu.service.MenuService;
import com.cy.systemManagement.user.entity.User;
import com.cy.systemManagement.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //认证
        int index = s.indexOf(":");
        String username = s.substring(0, index);
        String userType = s.substring(index + 1, s.length());
        if (userType.equals("0")) { //业主
            LiveUser liveUser = liveUserService.loadUser(username);
            if (liveUser == null) {
                throw new UsernameNotFoundException("用户账户不存在！");
            }
            //查询业主的权限信息
            List<Menu> menuList = menuService.getMenuByUserIdForLiveUser(liveUser.getUserId());
            //获取权限字段
            List<String> collect = menuList
                    .stream()
                    .filter(item -> item != null)
                    .map(item -> item.getMenuCode())
                    .filter(item -> item != null)
                    .collect(Collectors.toList());
            //转成数组
            String[] strings = collect.toArray(new String[collect.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
            //设置用户的权限
            liveUser.setAuthorities(authorityList);
            return liveUser;
        } else if (userType.equals("1")) { //物主
            User user = userService.loadUser(username);
            if (user == null) {
                throw new UsernameNotFoundException("用户账户不存在！");
            }
            //查询用户权限信息
            List<Menu> menuList = menuService.getMenuListByUserId(user.getUserId());
            //获取权限字段
            List<String> collect = menuList
                    .stream()
                    .filter(item -> item != null)
                    .map(item -> item.getMenuCode())
                    .filter(item -> item != null)
                    .collect(Collectors.toList());
            //转成数组
            String[] strings = collect.toArray(new String[collect.size()]);
            List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings);
            //设置用户的权限
            user.setAuthorities(authorityList);
            return user;
        } else {
            throw new UsernameNotFoundException("用户类型不存在");
        }

    }
}
