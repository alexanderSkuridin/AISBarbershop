package com.base.test.springonetomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository  extends JpaRepository<Barber, Long> {

}
