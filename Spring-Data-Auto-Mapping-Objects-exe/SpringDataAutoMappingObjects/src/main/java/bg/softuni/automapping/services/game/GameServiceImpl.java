package bg.softuni.automapping.services.game;

import bg.softuni.automapping.entities.Game;
import bg.softuni.automapping.repos.GameRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import static bg.softuni.automapping.constants.Constants.GAME_TITLE_REGEX;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game addGame(Game game) {
        gameValidation(game);
        return persistGame(game);
    }

    @Override
    public Game persistGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void editGameTitle(Long id, String title) {
        gameRepository.editGameTitle(id, title);
    }

    @Override
    public void editGamePrice(Long id, BigDecimal price) {
        gameRepository.editGamePrice(id, price);
    }

    @Override
    public void editGameSize(Long id, double size) {
        gameRepository.editGameSize(id, size);
    }

    @Override
    public void deleteGameById(Long id) {
        gameRepository.deleteGameById(id);
    }

    @Override
    public Game getById(long id) {
         return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game getGameByTitle(String title) {
        return gameRepository.findByTitle(title);
    }

    @Override
    public List<Game> getAllByUserId(long id) {
       return gameRepository.findAllByUserId(id);
    }


    public boolean gameValidation(Game game) {
        if (!Pattern.matches(GAME_TITLE_REGEX, game.getTitle())) {
            throw new IllegalArgumentException("Invalid title.");
        }
        if (Double.parseDouble(game.getPrice().toString()) <= 0) {
            throw new IllegalArgumentException("Price cannot be negative value.");
        }
        if (game.getSize() <= 0) {
            throw new IllegalArgumentException("Price cannot be negative value.");
        }
        if (game.getTrailer().length() != 11) {
            throw new IllegalArgumentException("Only Youtube videos are allowed");
        }
        String imageURL = game.getImage();
        if (!imageURL.startsWith("https://")) {
            if (!imageURL.startsWith("http://")) {
                throw new IllegalArgumentException("Invalid thumbnail url.");
            }
        }

        if (game.getDescription().length() < 20) {
            throw new IllegalArgumentException("Description have to be at least 20 symbols");
        }
        return true;
    }
}
