package com.ivi.dyndns.webszatyor.service;

import com.ivi.dyndns.webszatyor.model.Address;

public interface AddressService {

    boolean saveAddress(Address address);

    Address findAddressByBilling(boolean billing);

}
