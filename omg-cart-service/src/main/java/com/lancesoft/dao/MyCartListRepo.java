package com.lancesoft.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lancesoft.entity.MyCart;
import com.lancesoft.entity.MyCartList;

public interface MyCartListRepo extends JpaRepository<MyCartList, String> {

	boolean existsByUserName(String userName);

	MyCartList findByUserName(String userName);

	MyCartList findByMyCartItems(MyCart myCartItems);
	
	MyCartList findByCartListId(String s);

	
}
