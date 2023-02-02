package com.lancesoft.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.lancesoft.entity.CategoriesEntity;
import com.lancesoft.entity.Inventory;
import com.lancesoft.entity.ProductsEntity;
import com.lancesoft.entity.RegistrationEntity;
import com.lancesoft.payload.ForgotPasswordPayload;

public interface AdminDashboardService  {
public void addCategory(CategoriesEntity categoriesEntity) ;

public ResponseEntity<?> updateProduct(HttpServletRequest httpServletRequest, ProductsEntity productsEntity);
public ResponseEntity getAllCategory();
public ResponseEntity<?> getoneCategory(String catName);

public ResponseEntity<?> getAllProducts(String userName);
}
