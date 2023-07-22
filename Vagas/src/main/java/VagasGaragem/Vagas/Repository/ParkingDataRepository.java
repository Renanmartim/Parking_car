package VagasGaragem.Vagas.Repository;

import VagasGaragem.Vagas.Models.ParkingData;
import VagasGaragem.Vagas.Models.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParkingDataRepository extends JpaRepository<ParkingData, Long> {
    @Query("SELECT pd.numberParking FROM ParkingData pd")
    List<Long> findAllParkingNumbers();
}
