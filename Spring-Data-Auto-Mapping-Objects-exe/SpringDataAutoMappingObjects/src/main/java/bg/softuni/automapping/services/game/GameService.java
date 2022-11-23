package bg.softuni.automapping.services.game;

import bg.softuni.automapping.entities.Game;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {
    Game addGame(Game game);

    Game persistGame(Game game);

    void editGameTitle(Long id,String title);

    void editGamePrice(Long id, BigDecimal price);
    void editGameSize(Long id, double size);
    void deleteGameById(Long id);

    Game getById(long id);

    List<Game> getAllGames();

    Game getGameByTitle(String title);

    List<Game> getAllByUserId(long id);
}
