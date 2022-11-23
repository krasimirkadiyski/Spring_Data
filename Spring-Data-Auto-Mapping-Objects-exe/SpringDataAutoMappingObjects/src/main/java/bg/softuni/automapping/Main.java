package bg.softuni.automapping;

import bg.softuni.automapping.entities.Game;
import bg.softuni.automapping.entities.User;
import bg.softuni.automapping.entities.dtos.UserDTO;
import bg.softuni.automapping.services.game.GameService;
import bg.softuni.automapping.services.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import static bg.softuni.automapping.constants.Constants.*;

@Component
public class Main implements CommandLineRunner {
    private final Scanner scanner;
    private final UserService userService;
    private final GameService gameService;


    public Main(Scanner scanner, UserService userService, GameService gameService) {
        this.scanner = scanner;
        this.userService = userService;
        this.gameService = gameService;

    }

    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();

        while (!input.equals("close")) {
            String[] arguments = input.split("\\|");
            String command = arguments[0];
            try {
                switch (command) {
                    case REGISTER_COMMAND -> {
                        UserDTO userDTO = new UserDTO(arguments[1], arguments[2], arguments[3], arguments[4]);
                        String fullName = userService.registerUser(userDTO);
                        System.out.printf(SUCCESSFUL_REGISTER_FORMAT_MESSAGE, fullName);
                    }
                    case LOGIN_COMMAND -> {
                        String fullName = userService.loginUser(arguments[1], arguments[2]);
                        System.out.printf(SUCCESSFUL_LOGIN_FORMAT_MESSAGE,fullName);
                    }
                    case LOGOUT_COMMAND -> {
                        String fullName = userService.logoutUser();
                        System.out.printf(SUCCESSFUL_LOGOUT_FORMAT_MESSAGE,fullName);
                    }
                    case ADD_GAME_COMMAND -> {
                        userService.checkIsAdministratorLoggedIn();
                        Game game = new Game(arguments[1], new BigDecimal(arguments[2]),Double.parseDouble(arguments[3]),
                                arguments[4],arguments[5],arguments[6],
                                LocalDate.parse(arguments[7], DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        gameService.addGame(game);
                        System.out.printf(SUCCESSFUL_ADDED_GAME_FORMAT_MESSAGE,game.getTitle());
                    }
                    case EDIT_GAME_COMMAND -> {
                        userService.checkIsAdministratorLoggedIn();
                        long id = Long.parseLong(arguments[1]);
                        List<String> inputs = Arrays.stream(arguments).skip(2).toList();
                        for (String current : inputs) {
                            String criteria = current.split("=")[0];
                            String value = current.split("=")[1];
                            if (criteria.equals("price")){
                                gameService.editGamePrice(id,new BigDecimal(value));
                            }
                            if (criteria.equals("title")){
                                gameService.editGameTitle(id,value);
                            }
                            if (criteria.equals("size")){
                                gameService.editGameSize(id,Double.parseDouble(value));
                            }
                            //TO DO
                            //може да се добави валидация на value
                        }
                        Game game = gameService.getById(id);
                        System.out.printf(SUCCESSFUL_EDITED_GAME_FORMAT_MESSAGE,game.getTitle());
                    }
                    case DELETE_GAME_COMMAND -> {
                        userService.checkIsAdministratorLoggedIn();
                        long id = Long.parseLong(arguments[1]);
                        String title = gameService.getById(id).getTitle();
                        gameService.deleteGameById(id);

                        System.out.printf(SUCCESSFUL_DELETED_GAME_FORMAT_MESSAGE, title);
                    }
                    case BUY_GAME_COMMAND -> {
                        long id = Long.parseLong(arguments[1]);
                        User user = userService.getUserLoggedIn(true);
                        Game game = gameService.getById(id);
                        user.purchaseGame(game);
                        userService.persistUser(user);
                        System.out.printf(SUCCESSFUL_BOUGHT_GAME_FORMAT_MESSAGE,user.getFullName(),game.getTitle());
                    }
                    case ALL_GAME_COMMAND -> {
                        List<Game> allGames = gameService.getAllGames();
                        for (Game game : allGames) {
                            System.out.printf(PRINT_GAME_FORMAT_MESSAGE,game.getTitle(),game.getPrice().toString());
                        }
                    }
                    case DETAILS_PER_GAME_COMMAND -> {
                        String title = arguments[1];
                        Game game = gameService.getGameByTitle(title);
                        System.out.printf(PRINT_DETAILS_PER_GAME_FORMAT_MESSAGE,game.getTitle(),game.getPrice().toString(),
                                game.getDescription(),game.getReleaseDate().toString());
                    }
                    case ALL_OWNED_GAME_COMMAND -> {
                        User user = userService.getUserLoggedIn(true);
                        long id = user.getId();
                        List<Game> games = gameService.getAllByUserId(id);
                        for (Game game : games) {
                            System.out.printf(PRINT_GAME_FORMAT_MESSAGE,game.getTitle(),game.getPrice());
                        }
                    }


                    default -> System.out.println("Unknown command.");
                }
            } catch (IllegalArgumentException er) {
                System.out.println(er.getMessage());
            }
            input = scanner.nextLine();
        }
    }
}
