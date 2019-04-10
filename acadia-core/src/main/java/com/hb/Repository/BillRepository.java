package com.hb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.Model.bill.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
