package com.springboot.pople.repository;

import com.springboot.pople.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// 주문 이력 조회
// 조회 조건이 복잡하지 않을 경우 Query어노테이션 방식으로 구현
public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

    // 현재 로그인 사용자의 주문 데이터를 페이징 조건에 맞춰서 조회
    @Query("select o from Order o where o.users.email = :email order by  o.orderStatus, o.orderDate desc")
   List<Order> findOrders(@Param("email") String email, Pageable pageable);

//    // 현재 로그인한 회원의 주문 개수가 몇 개인지 조회
    @Query("select count(o) from Order o where o.users.email = :email")
    Long countOreder(@Param("email") String email);


}
