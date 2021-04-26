package com.base.test.AISBarbershop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository   extends JpaRepository<Service, Long> {
}
