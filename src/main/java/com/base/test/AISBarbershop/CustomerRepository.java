package com.base.test.AISBarbershop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository   extends JpaRepository<Customer, Long> {
}
