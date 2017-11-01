package unit.service;

import com.charityconnector.dao.PaypalRepository;
import com.charityconnector.entity.Transaction;
import com.charityconnector.service.PaypalService;
import com.charityconnector.serviceImpl.PaypalServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import java.util.Date;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class TransactionServiceUnitTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    PaypalService paypalService;

    @Mock
    PaypalRepository paypalRepository;


    @Before
    public void initService() {
        paypalService = new PaypalServiceImpl(paypalRepository);
    }

    @Test
    public void testGetPaypal() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);

        when(paypalRepository.findOne(1L)).thenReturn(transaction);
        Transaction returned = paypalService.findById(1L);

        assertThat(returned.getId(), is(1L));
    }

    @Test
    public void testAddPaypal() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);

        paypalService.addPaypal(transaction);

        verify(paypalRepository, times(1)).save(transaction);
        verifyNoMoreInteractions(paypalRepository);
    }


    @Test
    public void testDeletePaypal() {
        paypalService.deleteById(1L);

        verify(paypalRepository, times(1)).delete(1L);
        verifyNoMoreInteractions(paypalRepository);
    }

    @Test
    public void testUpdatePaypal() {

        Transaction oldTransaction = new Transaction();
        Transaction newTransaction = new Transaction();

        oldTransaction.setId(1L);
        oldTransaction.setCharityId(2L);
        oldTransaction.setAmount(1000);
        oldTransaction.setDate(new Date());

        newTransaction.setId(1L);
        newTransaction.setCharityId(3L);
        newTransaction.setAmount(10);
        newTransaction.setDate(new Date());

        when(paypalRepository.findOne(1L)).thenReturn(oldTransaction);

        paypalService.updateSelective(newTransaction);
        verify(paypalRepository).save(argThat(new SelectiveUpdatePaypalMatcher(newTransaction)));
    }
}
