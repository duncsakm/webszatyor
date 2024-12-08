package com.ivi.dyndns.webszatyor.repository;

import com.ivi.dyndns.webszatyor.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Address findAddressByBilling(boolean billing);

}
