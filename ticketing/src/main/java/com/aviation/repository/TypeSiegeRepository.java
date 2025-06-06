package com.aviation.repository;

import com.aviation.model.TypeSiege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSiegeRepository extends JpaRepository<TypeSiege, Integer> {
    // Méthodes personnalisées si nécessaire
}
