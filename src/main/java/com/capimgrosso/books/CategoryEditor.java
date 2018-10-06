package main.java.com.capimgrosso.books;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ManagedBean(name = "categoryEditor")
@SessionScoped
public class CategoryEditor {
    private static final long serialVersionUID = 1L;
    private static final Logger _logger =Logger.getLogger("CategoryEditor");

    private List<Category> _categories;

    @PostConstruct
    private void init(){
        _categories = new ArrayList<>();
        _categories.add(new Category(){{setId(1); setName("Java");}});
        _categories.add(new Category(){{setId(2); setName("Web");}});
    }

    public String addCategory(){
        _categories.add(new Category());
        return "";
    }

    public String deleteCategory(Category category) {
        _categories.remove(category);
        return "";
    }

    public List<Category> getCategories() {
        return _categories;
    }

    public void setCategories(List<Category> _categories) {
        this._categories = _categories;
    }

    public String save(){
        String categories = _categories
                .stream()
                .filter(cat -> !cat.getName().isEmpty())
                .map(cat -> cat.toString())
                .collect(Collectors.joining(", "));

        _logger.log(Level.INFO, "Save categories: {0}", categories);
        return "";
    }


}
