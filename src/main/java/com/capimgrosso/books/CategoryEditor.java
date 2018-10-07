package com.capimgrosso.books;

import com.capimgrosso.books.entity.Category;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("categoryEditor")
@SessionScoped
public class CategoryEditor implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger _logger = Logger.getLogger("CategoryEditor");
    private List<Category> categories;
    private List<Category> deletedCategories;

    @Inject
    private CategoryService categoryService;

    @PostConstruct
    public void init() {
        categories = categoryService.findAll();
        deletedCategories = new ArrayList<>();
    }

    public String addCategory() {
        categories.add(new Category());
        return "";
    }

    public String deleteCategory(Category category) {
        if (category.getId() >= 0) {
            deletedCategories.add(category);
        }
        _logger.log(Level.INFO, "Remove categories: {0}", deletedCategories);
        return "";
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> _categories) {
        this.categories = _categories;
    }

    public String save() {
        for (Category c : categories) {
            categoryService.save(c);
        }
        for (Category d : deletedCategories) {
            categoryService.delete(d);
        }
        deletedCategories = new ArrayList<>();
        _logger.log(Level.INFO, "Save categories: {0}", categories);
        return "";
    }
}
