package my.gov.imi.niise_demo_hazieq;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaRepository
        extends JpaRepository<Pengguna, Integer> {

    List<Pengguna> findByNamaContainingIgnoreCaseAndIcnoContainingIgnoreCase(
            String nama,
            String icno
    );

    List<Pengguna> findByNamaContainingIgnoreCase(
            String nama
    );

    List<Pengguna> findByIcnoContainingIgnoreCase(
            String icno
    );

    boolean existsByEmailIgnoreCase(String email);

    // Untuk LOGIN
    Optional<Pengguna> findByEmail(String email);
}