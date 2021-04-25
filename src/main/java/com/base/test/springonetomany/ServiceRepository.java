package com.base.test.springonetomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository   extends JpaRepository<Service, Long> {
}
