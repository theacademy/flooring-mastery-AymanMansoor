package com.flooringmastery.dao;

import com.flooringmastery.model.Tax;
import java.util.List;

public interface TaxDao {
    List<Tax> getAllTaxes();
    Tax getTaxByState(String stateAbbreviation);
}