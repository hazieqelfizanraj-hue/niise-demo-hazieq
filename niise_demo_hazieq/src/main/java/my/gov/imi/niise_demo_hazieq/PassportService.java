package my.gov.imi.niise_demo_hazieq;

import my.gov.imi.niise_demo_hazieq.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassportService {

    private final PassportRepository repo;

    public PassportService(PassportRepository repo) {
        this.repo = repo;
    }

    public List<Passport> findAllPassports() {
        return repo.findAll();
    }

    public Passport findPassportById(int id) {
        return findOrThrow(id);
    }

    public void removePassportById(int id) {
        findOrThrow(id);
        repo.deleteById(id);
    }

    public Passport addPassport(Passport passport) {
        passport.setId(0);
        return repo.save(passport);
    }

    public Passport updatePassport(int id, Passport passport) {
        Passport existing = findOrThrow(id);
        existing.setFullname(passport.getFullname());
        existing.setIcno(passport.getIcno());
        return repo.save(existing);
    }

    private Passport findOrThrow(int id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Passport by id " + id + " was not found"));
    }
}
