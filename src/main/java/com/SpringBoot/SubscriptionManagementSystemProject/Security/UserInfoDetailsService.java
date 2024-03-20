package com.SpringBoot.SubscriptionManagementSystemProject.Security;


import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Admin;
import com.SpringBoot.SubscriptionManagementSystemProject.Entity.Users;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.AdminRepository;
import com.SpringBoot.SubscriptionManagementSystemProject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
    public class UserInfoDetailsService implements UserDetailsService {
        @Autowired
        AdminRepository adminRepository;
        @Autowired
        UserRepository userRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            System.out.println(username);
            if (adminRepository.existsByAdminUsername(username)) {
                Optional<Admin> admin = adminRepository.findAll().stream().filter(user -> user.getAdminUsername().equals(username)).findFirst();
                System.out.println(admin.get().getAdminPassword());
                return admin.map(AdminInfo::new).orElseThrow(() -> new UsernameNotFoundException("Admin not found " + username));
            }
            Optional<Users> users = userRepository.findAll().stream().filter(user -> user.getUserName().equals(username)).findFirst();
            return users.map(UsersInfo::new).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
        }
    }

