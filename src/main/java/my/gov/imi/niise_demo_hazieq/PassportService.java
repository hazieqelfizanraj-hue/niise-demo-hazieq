package my.gov.imi.niise_demo_hazieq;

import my.gov.imi.niise_demo_hazieq.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class PassportService {

    private final PassportRepository repo;

    public Iterable<Passport> findAllPassports() {
        return repo.findAll();
    }

    public Passport findPassportById(int id) {
        return findOrThrow(id);
    }

    @Transactional
    public void removePassportById(int id) {
        if (!repo.existsById(id)) {
            throw new NotFoundException(
                    "Passport by id " + id + " was not found"
            );
        }

        repo.deleteById(id);
    }

    @Transactional
    public Passport addPassport(Passport passport) {
        return repo.save(passport);
    }

    @Transactional
    public Passport updatePassport(int id, Passport incomingPassport) {

        // Pastikan passport wujud
        Passport existingPassport = findOrThrow(id);

        // Tetapkan ID kepada data yang hendak dikemaskini
        incomingPassport.setId(id);

        // Simpan dan pulangkan data yang telah dikemaskini
        return repo.save(incomingPassport);
    }

    public List<Passport> searchPassports(String fullname, String icno) {

        boolean hasName =
                fullname != null && !fullname.trim().isEmpty();

        boolean hasIc =
                icno != null && !icno.trim().isEmpty();

        if (hasName && hasIc) {

            return repo.findByFullnameContainingIgnoreCaseAndIcnoContainingIgnoreCase(
                    fullname.trim(),
                    icno.trim()
            );

        } else if (hasName) {

            return repo.findByFullnameContainingIgnoreCase(
                    fullname.trim()
            );

        } else if (hasIc) {

            return repo.findByIcnoContainingIgnoreCase(
                    icno.trim()
            );

        } else {

            return repo.findAll();
        }
    }

    private Passport findOrThrow(final int id) {

        return repo
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                "Passport by id " + id + " was not found"
                        )
                );
    }
}