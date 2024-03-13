package co.kr.adcapsule.apt.login.repository;

import co.kr.adcapsule.apt.login.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    public User findByLoginId(String login_id);
    public Boolean existsByLoginId(String login_id);
}
