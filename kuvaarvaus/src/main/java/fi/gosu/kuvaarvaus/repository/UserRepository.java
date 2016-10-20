package fi.gosu.kuvaarvaus.repository;

import fi.gosu.kuvaarvaus.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
