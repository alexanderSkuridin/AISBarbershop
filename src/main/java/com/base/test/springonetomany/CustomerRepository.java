package com.base.test.springonetomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository   extends JpaRepository<Customer, Long> {
}
