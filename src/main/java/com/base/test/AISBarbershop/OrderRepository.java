package com.base.test.springonetomany;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository   extends JpaRepository<Order, Long> {
}
