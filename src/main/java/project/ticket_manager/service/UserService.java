package project.ticket_manager.service;


import project.ticket_manager.dto.RegistrationDto;
import project.ticket_manager.model.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
