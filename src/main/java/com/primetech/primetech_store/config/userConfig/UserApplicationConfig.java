package com.primetech.primetech_store.config.userConfig;

import com.primetech.primetech_store.user.application.AssignRoleApplication;
import com.primetech.primetech_store.user.application.GetUserInformationApplication;
import com.primetech.primetech_store.user.application.UpdateUserInformationApplication;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserApplicationConfig {
    private UserServiceInterface userService;
    private UserRoleAssignmentServiceInterface userRoleAssignmentService;

    @Bean
    public GetUserInformationApplication getUserInformationApplication(){return new GetUserInformationApplication(userService, userRoleAssignmentService);}

    @Bean
    public UpdateUserInformationApplication updateUserInformation(){return  new UpdateUserInformationApplication(userService, userRoleAssignmentService);}

    @Bean
    public AssignRoleApplication assignSellerRoleApplication(){return  new AssignRoleApplication(userRoleAssignmentService);}
}
