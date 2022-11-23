package bg.softuni.automapping.constants;

public enum Constants {
    ;
    public static final String EMAIL_REGEX = "(?!.*\\.\\.)(^[^\\.][^@\\s]+@[^@\\s]+\\.[^@\\s\\.]+$)";
    public static final String PASS_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,}";
    public static final String GAME_TITLE_REGEX = "[A-Z]{1,}[a-z A-Z]{1,99}";
    public static final String REGISTER_COMMAND = "RegisterUser";
    public static final String LOGIN_COMMAND = "LoginUser";
    public static final String LOGOUT_COMMAND = "Logout";
    public static final String ADD_GAME_COMMAND = "AddGame";
    public static final String EDIT_GAME_COMMAND = "EditGame";
    public static final String DELETE_GAME_COMMAND = "DeleteGame";
    public static final String BUY_GAME_COMMAND = "BuyGame";
    public static final String ALL_GAME_COMMAND = "AllGames";
    public static final String DETAILS_PER_GAME_COMMAND = "DetailGame";
    public static final String ALL_OWNED_GAME_COMMAND = "OwnedGames";
    public static final String SUCCESSFUL_REGISTER_FORMAT_MESSAGE = "%s was registered.%n";
    public static final String SUCCESSFUL_LOGIN_FORMAT_MESSAGE = "Successfully logged in %s.%n";
    public static final String SUCCESSFUL_LOGOUT_FORMAT_MESSAGE = "User %s successfully logged out.%n";
    public static final String SUCCESSFUL_BOUGHT_GAME_FORMAT_MESSAGE = "%s Bought game %s%n";
    public static final String SUCCESSFUL_ADDED_GAME_FORMAT_MESSAGE = "Added %s.%n";
    public static final String SUCCESSFUL_EDITED_GAME_FORMAT_MESSAGE = "Edited %s%n";
    public static final String SUCCESSFUL_DELETED_GAME_FORMAT_MESSAGE = "Deleted %s%n";
    public static final String PRINT_GAME_FORMAT_MESSAGE = "%s %s%n";
    public static final String PRINT_DETAILS_PER_GAME_FORMAT_MESSAGE = "Title: %s%nPrice: %s%nDescription: %s%nRelease date: %s%n";






}
