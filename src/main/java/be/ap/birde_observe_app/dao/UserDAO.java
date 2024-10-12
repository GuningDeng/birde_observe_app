package be.ap.birde_observe_app.dao;

import org.springframework.data.repository.CrudRepository;

import be.ap.birde_observe_app.model.User;

public interface UserDAO extends CrudRepository<User, Long> {
    User findUserByUsername(String username);
    
}
