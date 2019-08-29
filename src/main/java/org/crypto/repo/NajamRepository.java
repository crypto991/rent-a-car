package org.crypto.repo;


import org.crypto.model.Najam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NajamRepository extends JpaRepository<Najam, Long> {
}
