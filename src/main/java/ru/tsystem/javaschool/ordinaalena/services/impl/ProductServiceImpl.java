package ru.tsystem.javaschool.ordinaalena.services.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.tsystem.javaschool.ordinaalena.DAO.api.ProductDAO;
import ru.tsystem.javaschool.ordinaalena.DTO.ProductDTO;
import ru.tsystem.javaschool.ordinaalena.converter.Converter;
import ru.tsystem.javaschool.ordinaalena.entities.Product;
import ru.tsystem.javaschool.ordinaalena.services.api.PictureService;
import ru.tsystem.javaschool.ordinaalena.services.api.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDAO productDAO;
    @Autowired
    Converter converter;
    @Autowired
    PictureService pictureService;
    private static final int PRODUCTS_ON_PAGE = 3;

    private static final Logger logger=Logger.getLogger(ProductServiceImpl.class);
    @Override
    public List<ProductDTO> getAll(String sortedByField, String[] categories, Integer page) {
        page = page==null?1:page;
        List<ProductDTO> dtos =  getProducts(categories).
                stream().map(product -> converter.convertToDTO(product))
                .collect(Collectors.toList());
        if(sortedByField!=null) {
            if (sortedByField.equals("cost"))
                dtos = sortByPrice(dtos);
            else if (sortedByField.equals("name"))
                dtos = sortByTitle(dtos);
        }

        int startIndex = (page - 1) * PRODUCTS_ON_PAGE;
        int endIndex = startIndex + PRODUCTS_ON_PAGE;

        if(endIndex>=dtos.size()) {
            dtos = dtos.subList(startIndex, dtos.size());
            return dtos;
        }
        return dtos.subList(startIndex, endIndex);
    }
    private List<Product> getProducts(String[] categories){
        if(categories==null)
            return productDAO.getAll(Product.class);
        else
            return productDAO.getByCategories(categories);

    }
    private List<ProductDTO> sortByPrice (List<ProductDTO> productDTOS){
        productDTOS.sort((ProductDTO a, ProductDTO b) ->{
            if(a.getPrice()>b.getPrice())
                return 1;
            if(a.getPrice()<b.getPrice())
                return -1;
            return 0;
        });
        return productDTOS;
    }

    private List<ProductDTO> sortByTitle(List<ProductDTO> productDTOS){
        productDTOS.sort((ProductDTO a, ProductDTO b) ->{
            int compare = a.getTitle().compareTo(b.getTitle());
            if(compare>0)
                return 1;
            if(compare<0)
                return -1;
            return 0;
        });
        return productDTOS;
    }

    private long getProductsCount(String[] categories){
        if(categories==null)
            return productDAO.getProductsCount();
        return productDAO.getProductsCount(categories);
    }

    @Override
    public List<String> getAllCategories() {
        return productDAO.getCategories();
    }

    @Override
    public ProductDTO getByTitle(String title) {
        Product product;
        if(title == null) {
            throw new NullPointerException("Name is null.");
        }
        product = productDAO.getByTitle(title);
        return converter.convertToDTO(product);
    }

    @Override
    @Transactional
    public List<ProductDTO> getByTitles(String[] titles) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for(String title :  titles){
            productDTOS.add(converter.convertToDTO(productDAO.getByTitle(title)));
        }
        return productDTOS;
    }

    @Override
    @Transactional
    public void addProduct(ProductDTO productDTO, MultipartFile picture) {
        logger.info("name - " + productDTO.getTitle());

        Product product = converter.convertToEntity(productDTO);
        productDAO.persist(product);

        pictureService.savePicture(product.getId(), picture);
    }

    @Override
    public long getPagesCount(String[] categories) {
            if(categories==null)
                return productDAO.getProductsCount();
            return productDAO.getProductsCount(categories);
    }
}