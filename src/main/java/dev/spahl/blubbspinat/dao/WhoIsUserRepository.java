package dev.spahl.blubbspinat.dao;

import dev.spahl.blubbspinat.models.WhoIsUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WhoIsUserRepository extends JpaRepository<WhoIsUser, Long> {
}