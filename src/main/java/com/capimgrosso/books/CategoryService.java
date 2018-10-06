package main.java.com.capimgrosso.books;

import main.java.com.capimgrosso.books.entity.Category;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.*;
import java.io.Serializable;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CategoryService implements Serializable {
    @PersistenceUnit(unitName= "booksPU")
    private EntityManagerFactory emf;
    private EntityManager em;
    @Resource
    UserTransaction tx;

    @PostConstruct
    void init(){
        em = emf.createEntityManager();
    }

    protected EntityManager getEntityManager(){
        return em;
    }

    public Category create(Category entity) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        //em.getTransaction().begin();

        //tx.begin();
        getEntityManager().persist(entity);
        //tx.commit();
        //em.getTransaction().commit();
        return entity;
    }

    public Category read(Object id){
        return getEntityManager().find(Category.class, id);
    }

    public Category update(Category entity){
        //em.getTransaction().begin();
        Category result = getEntityManager().merge(entity);
        //em.getTransaction().commit();
        return result;
    }

    public void delete(Category entity){
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Category save(Category entity){
        try {
            if (entity.getId() < 0){
                return create(entity);
            }
            return create(entity);
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> findAll(){
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Category.class));
        return getEntityManager().createQuery(cq).getResultList();
    }
}
