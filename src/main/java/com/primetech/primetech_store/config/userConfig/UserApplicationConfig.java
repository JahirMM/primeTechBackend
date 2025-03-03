package com.primetech.primetech_store.config.userConfig;

import com.primetech.primetech_store.common.infrastructure.filesystem.FileStorageService;
import com.primetech.primetech_store.user.application.*;
import com.primetech.primetech_store.user.domain.interfaces.UserImageServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserRoleAssignmentServiceInterface;
import com.primetech.primetech_store.user.domain.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class UserApplicationConfig {
    private final UserServiceInterface userService;
    private final UserRoleAssignmentServiceInterface userRoleAssignmentService;
    private final UserImageServiceInterface userImageService;
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
    public UploadUserImageApplication uploadUserProfileApplication(){return  new UploadUserImageApplication(userImageService, fileStorageService);}

    @Bean
    public DeleteUserImageApplication deleteUserImageApplication(){return new DeleteUserImageApplication(userImageService, fileStorageService);}

    @Bean
    public GetUserImageApplication getUserImageApplication(){return new GetUserImageApplication(userImageService);}
}
