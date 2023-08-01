package com.codechallenge.taxes.model.tax;

import com.codechallenge.taxes.model.tax.TaxClass;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "taxClasses")
public class TaxClassCollection implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlElement(name="taxClass")
    private List<TaxClass> taxClassList;

    public TaxClassCollection() {
        taxClassList = new ArrayList<>();
    }

    public List<TaxClass> getTaxClassList() {
        return taxClassList;
    }

    public void addTaxClass(TaxClass taxClass) {
        this.taxClassList.add(taxClass);
    }
}
