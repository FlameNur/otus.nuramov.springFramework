package com.nuramov.hw04Library.exceptions;

public class FindByIdException extends Exception {

    @Override
    public String getLocalizedMessage() {
        return "Ошибка. Не удалось найти книгу. Проверьте id и попробуйте еще раз.";
    }
}
