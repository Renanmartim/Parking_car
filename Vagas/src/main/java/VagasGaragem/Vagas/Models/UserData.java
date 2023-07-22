package VagasGaragem.Vagas.Models;

import VagasGaragem.Vagas.Models.ParkingData;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date dateBirth;

    @OneToOne(mappedBy = "userData", cascade = CascadeType.PERSIST)
    private ParkingData parkingSpot;

    public UserData(String name, Date dateBirth) {
        this.name = name;
        this.dateBirth = dateBirth;
    }

    public UserData() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Getter for formatted date in "dd/mm/yyyy" format


    public Date getDateBirth() {
        return dateBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public ParkingData getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingData parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
