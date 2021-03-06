package ru.tsystem.javaschool.ordinaalena.DAO.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.entities.ProductOrders;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {
    private static final Logger logger = Logger.getLogger(AddressDAOImpl.class);

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void persist(Product product) {
        logger.info("persist new " + product.getClass());

        entityManager.persist(product);
    }

    @Override
    public Product find(int id, Class<Product> className) {
        logger.info("find by id " + className + " id " + id);
        return entityManager.find(className, id);
    }

    @Override
    public void remove(Product product) {
        logger.info("remove " + product.getClass() + " id " + product.getId());
        entityManager.remove(entityManager.merge(product));
    }

    @Override
    public void merge(Product product) {
        logger.info("merge " + product.getClass() + " id " + product.getId());
        entityManager.merge(product);
    }

    @Override
    public List<Product> getAllWithoutAvailable(Class<Product> className) {
        logger.info("find all " + className);
        return entityManager.
                createQuery("from "+className.getSimpleName(), className).
                getResultList();
    }
    @Override
    public List<Product> getAll(Class<Product> className) {
        logger.info("find all " + className);
        return entityManager.
                createQuery("from Product as prod " +
                        "where notavailable=false ", Product.class).
                getResultList();
    }
    @Override
    public List<Product> getByCategory(String category) {
        return entityManager.createQuery("from Product as prod " +
                "where prod.category=:category AND notavailable=false ", Product.class).
                setParameter("category", category).getResultList();
    }

    @Override
    public List<String> getCategories() {
        return entityManager.createQuery("SELECT DISTINCT category FROM Product as prod where prod.notavailable=false",
                String.class).getResultList();
    }

    @Override
    public Product getByTitle(String title) {
        return entityManager.createQuery(
                "select product from Product as product where product.title=:title",
                Product.class)
                .setParameter("title",title)
                .getSingleResult();
    }
@Override
public Product getById(Integer id){
        return entityManager.createQuery("select product from Product as product where product.id=:id",
                Product.class)
                .setParameter("id",id)
                .getSingleResult();
}
    @Override
    public List<Product> getByCategories(String[] categories) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder
                .createQuery(Product.class);
        ParameterExpression parameters[] = new ParameterExpression[categories.length];
        Predicate predicate[] = new Predicate[categories.length];
        Root<Product> root = criteriaQuery.from(Product.class);

        for (int i=0; i<predicate.length; i++){
            parameters[i] = criteriaBuilder.parameter(String.class);
            predicate[i] = criteriaBuilder.equal(root.get("category"), parameters[i]);
        }

        criteriaQuery.select(root)
                .where(criteriaBuilder.
                        and(criteriaBuilder
                                .or(predicate),criteriaBuilder.equal(root.get("notavailable"), false)));


        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);


        for (int i=0; i<predicate.length; i++) {
            query.setParameter(parameters[i], categories[i]);
        }

        return query.getResultList();
    }
    @Override
    public long getProductsCount(String[] categories) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder
                .createQuery(Long.class);
        ParameterExpression parameters[] = new ParameterExpression[categories.length];
        Predicate predicate[] = new Predicate[categories.length];
        Root<Product> root = criteriaQuery.from(Product.class);

        //make all predicates for criteria
        for (int i=0; i<predicate.length; i++){
            parameters[i] = criteriaBuilder.parameter(String.class);
            predicate[i] = criteriaBuilder.equal(root.get("category"), parameters[i]);
        }

        //make criteria query
        criteriaQuery.select(criteriaBuilder.count(root))
                .where(criteriaBuilder.
                        and(criteriaBuilder
                                .or(predicate),criteriaBuilder.equal(root.get("notavailable"), false)));

        //Convert criteria to query
        TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);

        //set all params
        for (int i=0; i<predicate.length; i++) {
            query.setParameter(parameters[i], categories[i]);
        }

        //find resultList
        return query.getSingleResult();
    }

    @Override
    public long getProductsCount() {
        return entityManager
                .createQuery("select count(*) from Product as prod where prod.notavailable=false", Long.class)
                .getSingleResult();
    }
    @Override
    public List<Product> getTopProducts(){
        return entityManager.
                createQuery("select productId from ProductOrders as po group by po.productId order by count(po.orders) desc ",
                        Product.class).getResultList();
    }
}
