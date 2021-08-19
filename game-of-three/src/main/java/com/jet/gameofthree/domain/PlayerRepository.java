package com.jet.gameofthree.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jet.gameofthree.domain.Player;

public interface PlayerRepository extends JpaRepository<Player, String> {

}
