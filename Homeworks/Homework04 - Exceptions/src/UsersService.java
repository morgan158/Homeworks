public interface UsersService {
    /**
     * Сохраняет пользователя с указанными данными в списке (ArrayList, LinkedList)
     * @param email емейл пользователя (должен содержать символ @)
     * @param password пароль пользователя (должен состоять из букв и цифр, длина > 7)
     * @throws BadEmailException - если неверный формат почты
     * @throws BadPasswordException - если формат пароля неверный
     */
    void signUp(String email, String password);

    /**
     * Аутентифицирует пользователя
     * @param email емейл пользователя
     * @param password пароль пользователя
     * @throws UserNotFoundException - если пользователь не был найден
     */
    void signIn(String email, String password);

}
