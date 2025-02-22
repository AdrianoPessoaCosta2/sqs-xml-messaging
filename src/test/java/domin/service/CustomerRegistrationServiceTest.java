package domin.service;

import com.adriano.sqsxmlmessaging.application.dto.Customer;
import com.adriano.sqsxmlmessaging.application.dto.CustomerRegistration;
import com.adriano.sqsxmlmessaging.application.dto.Invoice;
import com.adriano.sqsxmlmessaging.application.dto.Invoices;
import com.adriano.sqsxmlmessaging.domain.service.CustomerRegistrationService;
import com.adriano.sqsxmlmessaging.domain.service.CustomerService;
import com.adriano.sqsxmlmessaging.domain.service.InvoiceService;
import com.adriano.sqsxmlmessaging.infrastructure.exception.MessageProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerRegistrationServiceTest {

    @Mock
    private CustomerService customerService;
    @Mock
    private InvoiceService invoiceService;
    @Mock
    private CustomerRegistration customerRegistration;
    @Mock
    private Invoice invoice;
    @Mock
    private Invoices invoices;
    @Mock
    private Customer customer;
    @Mock
    private CustomerRegistrationService customerRegistrationService;

    @Test
    void shouldSaveCustomerWhenReceivedMessage() throws MessageProcessingException {
        customerRegistrationService.save(customerRegistration);
        verify(customerRegistrationService, times(1)).save(customerRegistration);
    }
}
