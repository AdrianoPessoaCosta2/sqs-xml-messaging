package com.adriano.sqsxmlmessaging.infrastructure.repository.impl;

import com.adriano.sqsxmlmessaging.domain.entity.CustomerEntity;
import com.adriano.sqsxmlmessaging.infrastructure.config.ElsqlBundleFactory;
import com.adriano.sqsxmlmessaging.infrastructure.repository.CustomerRepository;
import com.adriano.sqsxmlmessaging.infrastructure.repository.columns.CustomerColumns;
import com.adriano.sqsxmlmessaging.infrastructure.repository.mapper.CustomerEntityRowMapper;
import com.opengamma.elsql.ElSqlBundle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;
    @Autowired
    private CustomerEntityRowMapper customerEntityRowMapper;
    @Autowired
    private ElsqlBundleFactory elSqlBundleFactory;

    private ElSqlBundle elSqlBundle;

    @PostConstruct
    private void init() {
        this.elSqlBundle = elSqlBundleFactory.createElSqlBundle(this.getClass());
    }

    @Override
    public Long save(final CustomerEntity customer) {
        final String sql = elSqlBundle.getSql("Save");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(CustomerColumns.CUSTOMER_IDENTIFIER.getValue(), customer.getCustomerIdentifier());
        params.addValue(CustomerColumns.NAME.getValue(), customer.getName());
        params.addValue(CustomerColumns.TYPE_CUSTOME.getValue(), customer.getTypeCustomer().getValue());
        params.addValue(CustomerColumns.REFERENCE_DATE.getValue(), customer.getReferenceDate());
        return template.queryForObject(sql, params,
                (rs, i) -> rs.getLong(CustomerColumns.CUSTOMER_ID.getValue()));
    }

    @Override
    public CustomerEntity findByCustomerId(final Long customerId) {
        final String sql = elSqlBundle.getSql("FindByCustomerId");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(CustomerColumns.CUSTOMER_ID.getValue(), customerId);
        return template.queryForObject(sql, params, customerEntityRowMapper);
    }

    @Override
    public Boolean isCustomers(final List<Long> customerIdentifiers){
            final String sql = elSqlBundle.getSql("IsCustomers");
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(CustomerColumns.CUSTOMER_IDENTIFIER.getValue(), customerIdentifiers);
            params.addValue(CustomerColumns.SIZE.getValue(), customerIdentifiers.size());
            return template.queryForObject(sql, params, Boolean.class);
    }

    @Override
    public Optional<Long> findIdByCustomerIdentifier(final Long customerIdentifier){
        final String sql = elSqlBundle.getSql("FindIdByCustomerIdentifier");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue(CustomerColumns.CUSTOMER_IDENTIFIER.getValue(), customerIdentifier);
        try {
            return Optional.ofNullable(template.queryForObject(sql, params, Long.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
