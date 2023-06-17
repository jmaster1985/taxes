package com.codechallenge.taxes.model;

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
}
