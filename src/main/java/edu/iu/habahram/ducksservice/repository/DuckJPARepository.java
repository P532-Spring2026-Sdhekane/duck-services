package edu.iu.habahram.ducksservice.repository;

import edu.iu.habahram.ducksservice.model.DuckEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuckJPARepository extends JpaRepository<DuckEntity, Integer> {
    // JpaRepository provides findAll(), save(), findById(), deleteById() automatically
}