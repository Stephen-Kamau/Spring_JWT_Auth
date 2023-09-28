package user.userauthtoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.userauthtoken.model.UserData;

import java.util.Optional;

public interface UserDataJpa extends JpaRepository<UserData, Long> {
    //    we also add to find use by a token..
    Optional<UserData> findByLogin(String login);
}
