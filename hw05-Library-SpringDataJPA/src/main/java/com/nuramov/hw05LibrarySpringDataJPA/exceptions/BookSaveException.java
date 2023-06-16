package com.nuramov.hw05LibrarySpringDataJPA.exceptions;

public class BookSaveException extends RuntimeException {

    @Override
    public String getLocalizedMessage() {
        return "Ошибка. Не удалось сохранить книгу. Проверьте данные и попробуйте еще раз.";
    }
}
