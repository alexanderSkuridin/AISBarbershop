package com.ais.barbershop.springbootbarbershopais;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository  extends JpaRepository<Message, Integer> {
}
