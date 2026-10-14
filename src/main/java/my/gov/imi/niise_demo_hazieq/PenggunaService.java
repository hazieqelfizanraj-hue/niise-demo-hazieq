package my.gov.imi.niise_demo_hazieq;

import my.gov.imi.niise_demo_hazieq.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PenggunaService {

    private final PenggunaRepository repo;

    public Iterable<Pengguna> findAllPengguna() {
        return repo.findAll();
    }

    public Pengguna findPenggunaById(int id) {
        return findOrThrow(id);
    }

    public boolean checkEmailExists(String email) {
    return repo.existsByEmailIgnoreCase(email);
}
    @Transactional
    public void removePenggunaById(int id) {

        if (!repo.existsById(id)) {
            throw new NotFoundException(
                    "Pengguna by id " + id + " was not found"
            );
        }

        repo.deleteById(id);
    }

    @Transactional
    public Pengguna addPengguna(Pengguna pengguna) {
        return repo.save(pengguna);
    }

    @Transactional
    public Pengguna updatePengguna(
            int id,
            Pengguna incomingPengguna) {

        // Pastikan pengguna wujud
        Pengguna existingPengguna = findOrThrow(id);

        // Tetapkan ID kepada data yang hendak dikemaskini
        incomingPengguna.setId(id);

        // Simpan dan pulangkan data yang telah dikemaskini
        return repo.save(incomingPengguna);
    }

    public List<Pengguna> searchPengguna(
            String nama,
            String icno) {

        boolean hasName =
                nama != null && !nama.trim().isEmpty();

        boolean hasIc =
                icno != null && !icno.trim().isEmpty();

        if (hasName && hasIc) {

            return repo
                    .findByNamaContainingIgnoreCaseAndIcnoContainingIgnoreCase(
                            nama.trim(),
                            icno.trim()
                    );

        } else if (hasName) {

            return repo
                    .findByNamaContainingIgnoreCase(
                            nama.trim()
                    );

        } else if (hasIc) {

            return repo
                    .findByIcnoContainingIgnoreCase(
                            icno.trim()
                    );

        } else {

            return repo.findAll();
        }
    }

    private Pengguna findOrThrow(final int id) {

        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                "Pengguna by id " + id + " was not found"
                        )
                );
    }
}