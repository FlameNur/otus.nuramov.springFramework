package com.nuramov.hw05LibrarySpringDataJPA.exceptions;

public class BookDeleteException extends RuntimeException {

    @Override
    public String getLocalizedMessage() {
        return "Ошибка. Не удалось удалить книгу. Проверьте id и попробуйте еще раз.";
    }
}
