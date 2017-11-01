package unit.service;

import com.charityconnector.entity.Transaction;
import org.mockito.ArgumentMatcher;

public class SelectiveUpdatePaypalMatcher extends ArgumentMatcher<Transaction>  {

    Transaction thisObject;

    SelectiveUpdatePaypalMatcher(Transaction thisObject) {
        this.thisObject = thisObject;
    }

    @Override
    public boolean matches(Object argument) {
        if (!(argument instanceof Transaction))
            return false;
        if (argument == thisObject)
            return true;
        if (argument.equals(thisObject))
            return true;
        if (thisObject.getId() != null && !thisObject.getId().equals(((Transaction) argument).getId()))
            return false;
        if (thisObject.getCharityId() != (((Transaction) argument).getCharityId()))
            return false;
        return thisObject.getAmount() == (((Transaction) argument).getAmount());
    }
}
