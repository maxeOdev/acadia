package com.hb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.model.bill.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
