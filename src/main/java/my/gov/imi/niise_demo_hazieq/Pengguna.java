package my.gov.imi.niise_demo_hazieq;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pengguna")
public class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama", length = 255)
    private String nama;

    @Column(name = "icno", length = 255)
    private String icno;

    @Column(name = "hash_password", length = 255)
    private String hashPassword;

    @Column(name = "email", length = 255)
    private String email;

    // Default no-argument constructor (Required by JPA)
    public Pengguna() {
    }

    // Constructor untuk mencipta rekod pengguna
    public Pengguna(String nama, String icno, String hashPassword, String email) {
        this.nama = nama;
        this.icno = icno;
        this.hashPassword = hashPassword;
        this.email = email;
    }

    // Getters dan Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getIcno() {
        return icno;
    }

    public void setIcno(String icno) {
        this.icno = icno;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}