package com.techelevator.recipeserver.dao;

import com.techelevator.recipeserver.model.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CategoryJdbcDao {
    private JdbcTemplate template;

    public CategoryJdbcDao(DataSource dataSource){
        template = new JdbcTemplate(dataSource);
    }
    private Category mapRowToCategory(SqlRowSet rowSet){
        Category category = new Category();
        category.setCategoryId(rowSet.getInt("category_id"));
        category.setCategoryType(rowSet.getString("category_id"));
        category.setCategoryName(rowSet.getString("category_id"));
        return category;
    }




}
