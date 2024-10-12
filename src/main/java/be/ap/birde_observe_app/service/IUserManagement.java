package be.ap.birde_observe_app.service;

import java.util.List;

import be.ap.birde_observe_app.model.User;

public interface IUserManagement {
    
    public User saveUser(User user);

    public List<User> getAllUser();

    public User getUserById(Long id);

    public User updateUserById(User user, Long id);

    public void deleteById(Long id);

    public boolean existsUserByNameAndRoller(String name, String role);

}
