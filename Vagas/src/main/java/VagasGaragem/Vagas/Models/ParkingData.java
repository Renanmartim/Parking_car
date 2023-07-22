package VagasGaragem.Vagas.Models;

import jakarta.persistence.*;

@Entity
public class ParkingData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long numberParking;

    @OneToOne
    @JoinColumn(name = "user_data_id")
    private UserData userData;

    public ParkingData(long numberParking) {
        this.numberParking = numberParking;
    }

    public ParkingData() {
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public long getNumberParking() {
        return numberParking;
    }

    public void setNumberParking(long numberParking) {
        this.numberParking = numberParking;
    }
}
