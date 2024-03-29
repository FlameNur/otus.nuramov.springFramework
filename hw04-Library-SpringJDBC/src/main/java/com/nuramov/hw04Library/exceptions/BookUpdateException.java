package com.nuramov.hw04Library.exceptions;

public class BookUpdateException extends RuntimeException {

    @Override
    public String getLocalizedMessage() {
        return "Ошибка. Не удалось обновить книгу. Проверьте данные и попробуйте еще раз.";
    }
}
