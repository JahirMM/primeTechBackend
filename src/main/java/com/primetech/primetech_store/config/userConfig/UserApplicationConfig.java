package com.primetech.primetech_store.config.userConfig;

import com.primetech.primetech_store.common.filesystem.FileStorageService;
import com.primetech.primetech_store.user.application.*;
import com.primetech.primetech_store.user.domain.interfaces.UploadUserImageServiceInterface;
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
    private UploadUserImageServiceInterface uploadUserImageServiceInterface;
    private final FileStorageService fileStorageService;

    @Bean
    public GetUserInformationApplication getUserInformationApplication(){return new GetUserInformationApplication(userService, userRoleAssignmentService);}

    @Bean
    public UpdateUserInformationApplication updateUserInformation(){return  new UpdateUserInformationApplication(userService, userRoleAssignmentService);}

    @Bean
    public AssignRoleApplication assignSellerRoleApplication(){return  new AssignRoleApplication(userRoleAssignmentService);}

    @Bean
    public DeleteAssignedRoleApplication deleteAssignedRoleApplication(){return  new DeleteAssignedRoleApplication(userRoleAssignmentService);}

    @Bean
    public UploadUserImageApplication uploadUserProfileApplication(){return  new UploadUserImageApplication(uploadUserImageServiceInterface, fileStorageService);}
}
