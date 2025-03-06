package com.flooringmastery.test;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.dao.TaxDaoImpl;
import com.flooringmastery.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TaxDaoTest {
    private TaxDao taxDao;

    @BeforeEach
    void setUp() {
        taxDao = new TaxDaoImpl();
    }

    @Test
    void testGetAllTaxes() {
        List<Tax> taxes = taxDao.getAllTaxes();
        assertNotNull(taxes);
        assertFalse(taxes.isEmpty());
    }

    @Test
    void testGetTaxByState() {
        Tax tax = taxDao.getTaxByState("TX");
        assertNotNull(tax);
        assertEquals("TX", tax.getStateAbbreviation());
        assertEquals(new BigDecimal("4.45"), tax.getTaxRate());
    }
}
