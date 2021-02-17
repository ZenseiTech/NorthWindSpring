package com.zenseitech.northwind.shipper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShipperRepository extends JpaRepository<Shipper, Integer>, JpaSpecificationExecutor<Shipper> {
    Shipper findByCompanyName(String companyName);
}
