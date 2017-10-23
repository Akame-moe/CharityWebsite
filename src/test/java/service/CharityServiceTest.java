package service;

import com.charityconnector.WebsiteApplication;
import com.charityconnector.entity.Charity;
import com.charityconnector.service.CharityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebsiteApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharityServiceTest {

    @Autowired
    CharityService charityService;

    @Test
    public void crudCarity() {
        // add charity first
        Charity c = new Charity();
        String name = "Mengyao";
        String description = "This is Mengyao.";
        c.setName(name);
        c.setDescription(description);
        Charity added = charityService.addCharity(c);

        // then get this charity
        Charity res = charityService.findById(added.getId());

        if (res != null) {
            // assert equals after added one charity
            Assert.assertEquals(res.getDescription(), description);
            // assert equals after updateSelective one charity
            String newValue = "NewValue";
            c.setDescription(newValue);
            c.setId(res.getId());
            charityService.updateSelective(c);
            Assert.assertEquals(charityService.findById(res.getId()).getDescription(), newValue);
            // delete then assert null
            charityService.deleteById(res.getId());
            Assert.assertNull(charityService.findById(res.getId()));
        } else {
            Assert.fail();
        }
    }

}
