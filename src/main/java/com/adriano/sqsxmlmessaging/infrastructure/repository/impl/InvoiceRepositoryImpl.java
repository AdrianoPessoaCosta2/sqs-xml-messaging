package com.adriano.sqsxmlmessaging.infrastructure.repository.impl;

import com.adriano.sqsxmlmessaging.domain.entity.InvoiceEntity;
import com.adriano.sqsxmlmessaging.infrastructure.config.ElsqlBundleFactory;
import com.adriano.sqsxmlmessaging.infrastructure.repository.InvoiceRepository;
import com.adriano.sqsxmlmessaging.infrastructure.repository.columns.InvoiceColumns;
import com.opengamma.elsql.ElSqlBundle;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Autowired
    private ElsqlBundleFactory elSqlBundleFactory;

    private ElSqlBundle elSqlBundle;

    @PostConstruct
    private void init() {
        this.elSqlBundle = elSqlBundleFactory.createElSqlBundle(this.getClass());
    }

    @Override
    public void saveAll(final List<InvoiceEntity> invoiceEntities) {
        final String sql = elSqlBundle.getSql("SaveAll");
        List<MapSqlParameterSource> batchParams = new ArrayList<>();
        for (InvoiceEntity invoiceEntity : invoiceEntities) {
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue(InvoiceColumns.CUSTOMER_ID.getValue(), invoiceEntity.getCustomerId());
            params.addValue(InvoiceColumns.TRANSACTION_ID.getValue(), invoiceEntity.getTransactionId());
            params.addValue(InvoiceColumns.TYPE_INVOICE.getValue(), invoiceEntity.getTypeInvoice().getValue());
            params.addValue(InvoiceColumns.AMOUNT.getValue(), invoiceEntity.getAmount());
            batchParams.add(params);
        }
        template.batchUpdate(sql, batchParams.toArray(new MapSqlParameterSource[0]));
    }
}
