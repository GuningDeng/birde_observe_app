package be.ap.birde_observe_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ap.birde_observe_app.dao.UserDAO;
import be.ap.birde_observe_app.model.Role;
import be.ap.birde_observe_app.model.User;

@Service
public class UserManagement implements IUserManagement {
    @Autowired
    private UserDAO userDAO;

    @Override
    public User saveUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        userDAO.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User getUserById(Long id) {
        User user = new User();
        Role role = new Role();
        List<Role> list = new ArrayList<>();
        role.setName("ROLE_" + "Role".toLowerCase());
        list.add(role);
        user.setAccountNonExpired(false);
        user.setAccountNonLocked(false);
        user.setCredentialsNonExpried(null);
        user.setEnabled(false);
        user.setPassword("password");
        user.setUsername("username");
        user.setRoles(list);

        User foundUser = userDAO.findById(id).orElse(user);
        return foundUser;

    }

    @Override
    public User updateUserById(User user, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUserById'");
    }

    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Override
    public boolean existsUserByNameAndRoller(String name, String role) {
        List<User> users = new ArrayList<>();
        String roleStr = "ROLE_" + role.toLowerCase();
        userDAO.findAll().forEach(users::add);
        return users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(name) && 
        u.getRoles().stream().anyMatch(r -> r.getName().contains(roleStr)));
    }

    
}
