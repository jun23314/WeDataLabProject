package com.wedatalab.project.domain.User.repository;

import com.wedatalab.project.domain.User.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);

    Boolean existsByNickname(String nickname);

}
