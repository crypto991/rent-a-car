package org.crypto.repo;


import org.crypto.model.Kompanija;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KompanijaRepository extends JpaRepository<Kompanija, Long> {
}
