package xyz.bnayagrawal.game.rockpaperscissor.repository;

import org.springframework.data.repository.CrudRepository;
import xyz.bnayagrawal.game.rockpaperscissor.model.domain.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {

}
