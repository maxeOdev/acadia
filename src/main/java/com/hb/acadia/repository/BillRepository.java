package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.bill.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
