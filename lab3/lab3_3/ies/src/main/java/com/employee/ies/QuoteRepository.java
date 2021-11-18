package com.employee.ies;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote,Integer> {
    Quote findByAvaliacao(String title);
}