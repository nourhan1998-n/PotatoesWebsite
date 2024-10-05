package org.example.services;

import org.example.DAOs.AdminDAO;
import org.example.DAOs.OrderDAO;
import org.example.DAOs.OrderItemDAO;
import org.example.entities.*;

import java.math.BigDecimal;

public class main {
    public static void main(String[]args){

//        ProductServiceImpl p=new ProductServiceImpl();
//        p.updateProduct()
//        AdminDAO adminDAO=new AdminDAO();
//        Admin a=new Admin();
//        a.setEmail("mina@gmail.com");
//        a.setPassword("123");
//        adminDAO.save(a);
//         addCategory();
        //addProduct();
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.deleteProductByName("Strawbarry");
//        Category testc=new Category();
//        testc.setId(232);
//        Product testp=new Product();
//        testp.setId(222);
//        testp.setName("mangaaa");
//        testp.setIdcategory(testc);
//        Cartitem test=new Cartitem();
//        test.setIdproduct(testp);
//        System.out.println(test.getIdproduct());
  }

    public static void addCategory(){
        CategoryServiceImpl ctgService = new CategoryServiceImpl();
        ctgService.addCategoryByName("Vegetables");
        ctgService.addCategoryByName("fruits");
        ctgService.addCategoryByName("juice");
        ctgService.addCategoryByName("dried");
    }

    public static void addProduct(){
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.addProduct(createProduct("Pepper",new BigDecimal(10),new BigDecimal(10),"images/product-1.jpg", "Vegetables"));
        productService.addProduct(createProduct("Strawbarry",new BigDecimal(10),new BigDecimal(10)," images/product-2.jpg", "fruits"));
        productService.addProduct(createProduct("Beans",new BigDecimal(10),new BigDecimal(10)," images/product-3.jpg", "dried"));
        productService.addProduct(createProduct("Cabbage",new BigDecimal(10),new BigDecimal(10)," images/product-4.jpg", "Vegetables"));
        productService.addProduct(createProduct("Tomato",new BigDecimal(10),new BigDecimal(10)," images/product-5.jpg", "fruits"));
        productService.addProduct(createProduct("Broccoli",new BigDecimal(10),new BigDecimal(10)," images/product-6.jpg", "Vegetables"));
        productService.addProduct(createProduct("Carrot",new BigDecimal(10),new BigDecimal(10)," images/product-7.jpg", "Vegetables"));

    }
    public static Product createProduct(String name, BigDecimal quantity, BigDecimal price, String imgURL, String categoryName){
        Category productCategory = new CategoryServiceImpl().getCategoryByName(categoryName);


        Product createdProduct = new Product();
        createdProduct.setName(name);
        createdProduct.setQuantity(quantity);
        createdProduct.setPrice(price);
        createdProduct.setImg(imgURL);
        createdProduct.setIdcategory(productCategory);

        return createdProduct;

    }
}

//package org.example.services;
//
//import org.eclipse.tags.shaded.org.apache.xpath.operations.Or;
//import org.example.DAOs.AdminDAO;
//import org.example.DAOs.OrderDAO;
//import org.example.DAOs.OrderItemDAO;
//import org.example.entities.*;
//import java.util.List;
//import java.util.ArrayList;
//
//import java.math.BigDecimal;
//
//public class main {
//    public static void main(String[]args) {
//
//    }
//}