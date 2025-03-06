package com.flooringmastery.dao;

import com.flooringmastery.model.Tax;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class TaxDaoImpl implements TaxDao {
    private final String TAX_FILE = "./SampleFileData/Data/Taxes.txt";
    private final Map<String, Tax> taxes = new HashMap<>();

    public TaxDaoImpl() {
        loadTaxes();
    }

    @Override
    public List<Tax> getAllTaxes() {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax getTaxByState(String stateAbbreviation) {
        return taxes.get(stateAbbreviation);
    }

    private void loadTaxes() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(TAX_FILE)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String stateAbbreviation = parts[0];
                    String stateName = parts[1];
                    BigDecimal taxRate = new BigDecimal(parts[2]);

                    Tax tax = new Tax(stateAbbreviation, stateName, taxRate);
                    taxes.put(stateAbbreviation, tax);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Tax file not found.");
        }
    }
}