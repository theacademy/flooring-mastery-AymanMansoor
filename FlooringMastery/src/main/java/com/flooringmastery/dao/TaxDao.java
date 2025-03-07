package com.flooringmastery.dao;

import com.flooringmastery.model.Tax;
import java.util.List;
import java.util.Map;

public interface TaxDao {
    List<Tax> getAllTaxes();
    Tax getTaxByState(String stateAbbreviation);
    Map<String, Tax> getTaxInfo();
}
