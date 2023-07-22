package VagasGaragem.Vagas.Service;

import VagasGaragem.Vagas.Models.ParkingData;
import VagasGaragem.Vagas.Models.UserData;
import VagasGaragem.Vagas.Repository.ParkingDataRepository;
import VagasGaragem.Vagas.Repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final ParkingDataRepository parkingDataRepository;

    @Autowired
    public UserDataService(UserDataRepository userDataRepository, ParkingDataRepository parkingDataRepository) {
        this.userDataRepository = userDataRepository;
        this.parkingDataRepository = parkingDataRepository;
    }

    public UserData findByName(String name) {
        Optional<UserData> userDataOptional = Optional.ofNullable(userDataRepository.findByName(name));

        if (userDataOptional.isPresent()) {
            // Use get() to fetch the UserData entity
            UserData userData = userDataOptional.get();

            // Use getParkingSpot() to fetch the associated ParkingData entity eagerly
            ParkingData parkingData = userData.getParkingSpot();

            // Return the UserData entity with the associated ParkingData
            return userData;
        } else {
            // If the user is not found, throw a ResponseStatusException with NOT_FOUND status.
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with name " + name + " not found.");
        }
    }
    public UserData save(UserData userData) {
        if (userData.getName() == null || userData.getDateBirth() == null) {
            throw new IllegalArgumentException("Verifique se a data de nascimento ou nome está vazio!");
        }

        long availableParkingNumber = findAvailableParkingNumber();
        ParkingData parkingData = new ParkingData(availableParkingNumber);
        parkingData = parkingDataRepository.save(parkingData);

        userData.setParkingSpot(parkingData);
        return userDataRepository.save(userData);
    }

    public long findAvailableParkingNumber() {
        // Assuming you have a method in ParkingDataRepository to get the list of all parking numbers
        List<Long> allParkingNumbers = parkingDataRepository.findAllParkingNumbers();

        // Find the first available parking number starting from 1
        for (long i = 1; i <= allParkingNumbers.size() + 1; i++) {
            if (!allParkingNumbers.contains(i)) {
                return i;
            }
        }

        // If all parking numbers are occupied, handle this case as per your application's requirements.
        throw new RuntimeException("No available parking numbers.");
    }
}
