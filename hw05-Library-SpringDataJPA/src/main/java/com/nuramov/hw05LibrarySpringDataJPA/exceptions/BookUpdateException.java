package com.nuramov.hw05LibrarySpringDataJPA.exceptions;

public class BookUpdateException extends RuntimeException {

    @Override
    public String getLocalizedMessage() {
        return "Ошибка. Не удалось обновить книгу. Проверьте данные и попробуйте еще раз.";
    }
}
