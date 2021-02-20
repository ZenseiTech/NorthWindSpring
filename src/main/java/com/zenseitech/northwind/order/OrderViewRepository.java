package com.zenseitech.northwind.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderViewRepository extends JpaRepository<OrderView, Integer>, JpaSpecificationExecutor<OrderView> {
    Page<OrderView> findAll(Pageable pageable);
}
