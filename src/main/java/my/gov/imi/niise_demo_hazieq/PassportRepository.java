package my.gov.imi.niise_demo_hazieq;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {

    // Finds records matching both name and IC number
    List<Passport> findByFullnameContainingIgnoreCaseAndIcnoContainingIgnoreCase(
            String fullname,
            String icno
    );

    // Finds records matching only name
    List<Passport> findByFullnameContainingIgnoreCase(
            String fullname
    );

    // Finds records matching only IC number
    List<Passport> findByIcnoContainingIgnoreCase(
            String icno
    );
}