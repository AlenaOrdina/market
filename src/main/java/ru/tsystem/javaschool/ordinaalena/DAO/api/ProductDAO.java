
package ru.tsystem.javaschool.ordinaalena.DAO.api;

import ru.tsystem.javaschool.ordinaalena.entities.Product;

import java.util.List;

public interface ProductDAO {
    void persist(Product product);
    Product find(int id, Class<Product> className);
    void remove(Product product);
    void merge(   Product product);
    List<Product> getAll(Class<Product> className);
    List<Product> getByCategory(String category);
    List<String> getCategories();
    Product getByTitle(String title);
    Product getById (Integer id);
    List<Product> getByCategories(String[] categories);
    List<Product> getAllWithoutAvailable(Class<Product> className);
    long getProductsCount(String[] categories);
    long getProductsCount();
    List<Product> getTopProducts();
    }