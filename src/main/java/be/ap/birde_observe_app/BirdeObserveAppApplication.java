package be.ap.birde_observe_app;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import be.ap.birde_observe_app.dao.UserDAO;
import be.ap.birde_observe_app.model.Role;
import be.ap.birde_observe_app.model.User;

@SpringBootApplication
public class BirdeObserveAppApplication {
	@Autowired
	private UserDAO userDAO;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Bean
	CommandLineRunner runner(){
		return args -> {
			User user = new User();
			user.setUsername("jan");
			// user.setPassword("123");
			user.setPassword(encoder.encode("123"));
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpried(true);
			user.setEnabled(true);
			List<Role> rList = new ArrayList<>();
			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_admin");
			rList.add(roleAdmin);
			user.setRoles(rList);
			// save user
			userDAO.save(user);

			User user1 = new User();
			user1.setUsername("aan");
			// user1.setPassword("123");
			user1.setPassword(encoder.encode("123"));
			user1.setAccountNonExpired(true);
			user1.setAccountNonLocked(true);
			user1.setCredentialsNonExpried(true);
			user1.setEnabled(true);
			List<Role> rList1 = new ArrayList<>();
			Role roleClient = new Role();
			roleClient.setName("ROLE_editor");
			rList1.add(roleClient);
			user1.setRoles(rList1);
			// save user
			userDAO.save(user1);

			User user2 = new User();
			user2.setUsername("bart");
			// user2.setPassword("123");
			user2.setPassword(encoder.encode("123"));
			user2.setAccountNonExpired(true);
			user2.setAccountNonLocked(true);
			user2.setCredentialsNonExpried(true);
			user2.setEnabled(true);
			List<Role> rList2 = new ArrayList<>();
			Role roleReader = new Role();
			roleReader.setName("ROLE_reader");
			rList2.add(roleReader);
			user2.setRoles(rList2);
			// save user
			userDAO.save(user2);

			System.out.println("user psw->" + user.getPassword());
			

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(BirdeObserveAppApplication.class, args);
	}

}
