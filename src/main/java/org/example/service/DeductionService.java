package org.example.service;

import jakarta.annotation.PostConstruct;
import org.example.model.PagibigMatrix;
import org.example.model.PhilhealthMatrix;
import org.example.model.SssMatrix;
import org.example.model.TaxMatrix;
import org.example.repository.PagibigRepository;
import org.example.repository.PhilhealthRepository;
import org.example.repository.SssRepository;
import org.example.repository.TaxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeductionService {

    @Autowired
    private SssRepository sssRepository;

    @Autowired
    private PhilhealthRepository philhealthRepository;

    @Autowired
    private PagibigRepository pagibigRepository;

    @Autowired
    private TaxRepository taxRepository;

    private static List<SssMatrix> sss;
    private static List<PhilhealthMatrix> philhealth;
    private static List<PagibigMatrix> pagibig;
    private static List<TaxMatrix> tax;

    @PostConstruct
    public void init() {
        sss = sssRepository.findAll();
        philhealth = philhealthRepository.findAll();
        pagibig = pagibigRepository.findAll();
        tax = taxRepository.findAll();
    }

    public double getSssContribution(double salary) {
        for (SssMatrix bracket: sss) {
            if (salary >= bracket.getMinSalary() && salary < bracket.getMaxSalary()) {
                return bracket.getContribution();
            }
        }
        return 0;
    }

    public double getPhilhealthContribution(double salary) {
        for (PhilhealthMatrix bracket: philhealth) {
            if (salary >= bracket.getMinSalary() && salary < bracket.getMaxSalary()) {
                return Math.min(salary * bracket.getPremiumRate(), bracket.getMaxContribution()) * bracket.getEmployeeShareRate();
            }
        }
        return 0;
    }

    public double getPagibigContribution(double salary) {
        for (PagibigMatrix bracket: pagibig) {
            if (salary >= bracket.getMinSalary() && salary <= bracket.getMaxSalary()) {
                return Math.min(salary * bracket.getEmployeeRate(), bracket.getMaxContribution());
            }
        }
        return 0;
    }

    public double getTaxContribution(double taxableIncome) {
        for (TaxMatrix bracket: tax) {
            if (taxableIncome >= bracket.getMinSalary() && taxableIncome <= bracket.getMaxSalary()) {
                return bracket.getBaseTax() + (taxableIncome - bracket.getExcessBase()) * bracket.getExcessRate();
            }
        }
        return 0;
    }
}
