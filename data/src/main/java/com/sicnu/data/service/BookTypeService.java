package com.sicnu.data.service;

import com.sicnu.data.BookType;
import com.sicnu.data.Result;

public interface BookTypeService {
    Result addBookType(String bt_name);
    Result delBookType(Integer bt_id);
    Result findAllBookType();
    Result updateBookType(BookType bookType);
}
