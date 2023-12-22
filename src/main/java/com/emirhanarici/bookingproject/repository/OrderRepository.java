package com.emirhanarici.bookingproject.repository;

import com.emirhanarici.bookingproject.dto.OrderReportDTO;
import com.emirhanarici.bookingproject.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order>{

    Page<Order> findAllByUserId(Long userId, Pageable orderPageable);


    Page<Order> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endTime, Pageable orderPageable);


    @Query("""
        SELECT NEW com.emirhanarici.bookingproject.dto.OrderReportDTO
        (FUNCTION('MONTHNAME',o.createdAt),FUNCTION('YEAR',o.createdAt),COUNT(o.id),COUNT(b.id),SUM(b.price))
        FROM Order o INNER JOIN o.orderItems items INNER JOIN items.book b 
        WHERE (o.user.id = :customerId) 
        GROUP BY FUNCTION('MONTHNAME',o.createdAt),FUNCTION('YEAR',o.createdAt) 
        ORDER BY FUNCTION('YEAR', o.createdAt) DESC
    """)
    Page<OrderReportDTO> findOrderStatisticsByCustomerId(@Param("customerId") Long customerId, Pageable pageable);


    @Query("""
        SELECT NEW com.emirhanarici.bookingproject.dto.OrderReportDTO
        (FUNCTION('MONTHNAME',o.createdAt),FUNCTION('YEAR',o.createdAt),COUNT(o.id),COUNT(b.id),SUM(b.price))
        FROM Order o INNER JOIN o.orderItems items INNER JOIN items.book b 
        GROUP BY FUNCTION('MONTHNAME',o.createdAt),FUNCTION('YEAR',o.createdAt) 
        ORDER BY FUNCTION('YEAR', o.createdAt) DESC
    """
    )
    Page<OrderReportDTO> findAllOrderStatistics(Pageable pageable);


}
