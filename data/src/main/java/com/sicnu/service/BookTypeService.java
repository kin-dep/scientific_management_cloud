package com.sicnu.service;

import com.sicnu.pojo.BookType;
import com.sicnu.util.Result;

public interface BookTypeService {
    Result addBookType(String bt_name);
    Result delBookType(Integer bt_id);
    Result findAllBookType();
    Result updateBookType(BookType bookType);
}
