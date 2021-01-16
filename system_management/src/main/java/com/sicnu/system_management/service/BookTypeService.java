package com.sicnu.system_management.service;

import com.sicnu.system_management.pojo.BookType;
import com.sicnu.system_management.util.Result;

public interface BookTypeService {
    Result addBookType(String bt_name);
    Result delBookType(Integer bt_id);
    Result findAllBookType();
    Result updateBookType(BookType bookType);
}
