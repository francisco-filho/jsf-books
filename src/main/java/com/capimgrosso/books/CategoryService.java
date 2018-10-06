package com.capimgrosso.books;

import com.capimgrosso.books.entity.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
public class CategoryService implements Serializable {
    @PersistenceContext(unitName = "booksPU", type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    protected EntityManager getEntityManager(){
        return em;
    }

    public Category create(Category entity){
        getEntityManager().persist(entity);
        return entity;
    }

    public Category read(Object id){
        return getEntityManager().find(Category.class, id);
    }

    public Category update(Category entity){
        Category result = getEntityManager().merge(entity);
        return result;
    }

    public void delete(Category entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Category save(Category entity){
        if (entity.getId() < 0){
            return create(entity);
        }
        return update(entity);
    }

    public List<Category> findAll(){
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Category.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
}