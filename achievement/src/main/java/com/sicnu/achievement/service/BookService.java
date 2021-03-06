package com.sicnu.achievement.service;

import com.sicnu.achievement.pojo.Book;
import com.sicnu.achievement.util.Result;

import java.text.ParseException;

public interface BookService {
    Result addBook(Book book, String checkMessage, String message);

    Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException;

    Result delBookById(Integer book_id);

    Result updateBook(Book book);
    Result selectAllBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException;
    Result findBookById(Integer book_id);

}
