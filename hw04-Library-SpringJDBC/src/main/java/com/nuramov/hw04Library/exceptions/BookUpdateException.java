package com.nuramov.hw04Library.exceptions;

public class BookUpdateException extends Exception{

    public String toString()
    {
        return "Ошибка. Не удалось обновить данные книги";
    }
}
