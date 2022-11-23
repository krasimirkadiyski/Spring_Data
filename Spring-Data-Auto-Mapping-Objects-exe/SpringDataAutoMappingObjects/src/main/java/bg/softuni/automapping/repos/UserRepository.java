package bg.softuni.automapping.repos;

import bg.softuni.automapping.entities.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

        boolean existsUserByEmail(String email);
        Optional<User> findByEmailAndPassword(String email, String password);
        @Query("""
SELECT u FROM User  as u
WHERE u.loggedIn = :logIn
""")
        Optional<User> findByLoggedIn(boolean logIn);




}
