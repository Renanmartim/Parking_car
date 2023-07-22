package VagasGaragem.Vagas.Repository;

import VagasGaragem.Vagas.Models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {

    @Query("SELECT u FROM UserData u WHERE u.name = :name")
    UserData findByName(@Param("name") String name);
}
