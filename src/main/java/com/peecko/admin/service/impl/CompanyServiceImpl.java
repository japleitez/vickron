package com.peecko.admin.service.impl;

import com.peecko.admin.domain.Company;
import com.peecko.admin.repository.CompanyRepository;
import com.peecko.admin.service.CompanyService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Company}.
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final Logger log = LoggerFactory.getLogger(CompanyServiceImpl.class);

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company save(Company company) {
        log.debug("Request to save Company : {}", company);
        return companyRepository.save(company);
    }

    @Override
    public Company update(Company company) {
        log.debug("Request to update Company : {}", company);
        return companyRepository.save(company);
    }

    @Override
    public Optional<Company> partialUpdate(Company company) {
        log.debug("Request to partially update Company : {}", company);

        return companyRepository
            .findById(company.getId())
            .map(existingCompany -> {
                if (company.getCountry() != null) {
                    existingCompany.setCountry(company.getCountry());
                }
                if (company.getName() != null) {
                    existingCompany.setName(company.getName());
                }
                if (company.getState() != null) {
                    existingCompany.setState(company.getState());
                }
                if (company.getLicense() != null) {
                    existingCompany.setLicense(company.getLicense());
                }
                if (company.getStart() != null) {
                    existingCompany.setStart(company.getStart());
                }
                if (company.getEnd() != null) {
                    existingCompany.setEnd(company.getEnd());
                }
                if (company.getEmail() != null) {
                    existingCompany.setEmail(company.getEmail());
                }
                if (company.getPhone() != null) {
                    existingCompany.setPhone(company.getPhone());
                }
                if (company.getAddress() != null) {
                    existingCompany.setAddress(company.getAddress());
                }
                if (company.getBillingPhone() != null) {
                    existingCompany.setBillingPhone(company.getBillingPhone());
                }
                if (company.getBillingEmail() != null) {
                    existingCompany.setBillingEmail(company.getBillingEmail());
                }
                if (company.getBillingAddress() != null) {
                    existingCompany.setBillingAddress(company.getBillingAddress());
                }
                if (company.getVatin() != null) {
                    existingCompany.setVatin(company.getVatin());
                }

                return existingCompany;
            })
            .map(companyRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Company> findAll(Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Company> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        companyRepository.deleteById(id);
    }
}
