package com.nuramov.hw04Library.exceptions;

public class BookSaveException extends RuntimeException {

    @Override
    public String getLocalizedMessage() {
        return "Ошибка. Не удалось сохранить книгу. Проверьте данные и попробуйте еще раз.";
    }
}
