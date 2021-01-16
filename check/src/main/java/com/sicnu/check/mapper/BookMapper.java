package com.sicnu.check.mapper;


import com.sicnu.check.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookMapper {
    void addBook(Book book);

    Book selectBookByNumber(Date publish_time);

    List<Book> selectBookByCondition(Map<String, Object> map);

    void delBookById(Integer book_id);

    void updateBook(Book book);

    int selectTotalBook(Map<String, Object> map);

    Integer selectCountBook(Map<String, Object> map);

    List<Book> findBookByLeaderId(Integer leader_id);
    Integer selectBookId(Integer leader_id,String book_name);
    Book findBookById(Integer book_id);

    Integer countBook();
}
