package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCapitalLatitude(8);
        country.setCountryCode1("4M5");
        country.setCountryCode2("B17");
        country.setCountryName("TjKgwNzKhS6ggPDJbAOb5t6REKygwIfw4Fkeh7AXs90vYfyXHv");
        country.setCurrencyCode("qpL");
        country.setCapital("auBdYT8kAgw6AkDs34r98yi8cIdY6Had");
        country.setCurrencyName("Bx9Fv83p8wwcxZHc1wZdzA77A1syJU18KpAtOyrhiR0ktall5Z");
        country.setCurrencySymbol("a5pQiGoJvJvki5J7S3S8mmUjFZeVt72P");
        country.setCapitalLongitude(4);
        country.setCountryFlag("lgfvRrR6iklcvKxXIzbd2YEkIZptBDkMDAMBSO6vcYULEFuAPK");
        country.setIsoNumeric(884);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("hP7biT8aImjSDokDpIKbr2KcZMdX7aB1YNrk0hppT4iaFHCVNM");
        state.setStateCode(1);
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCodeChar2("kkd4Dou1lPreZ1SdKrcwvedI6sHHswkb");
        state.setStateCapitalLongitude(6);
        state.setStateDescription("cMaYv2AqL898LFjQqsx3ucTiskJC1SII1zZ44MOque0bgclgPP");
        state.setStateFlag("Jvsdu0dUWom2RVAn6qfj7y8JU5ls29F3Y3JG2nHkKKCyqOsBHH");
        state.setStateCapital("adg6BCpPJVVX9zWgHBaGlNbQC4G9tVwkBFgwqEzuG2e9WOoCRh");
        state.setStateCodeChar3("INIdRYOYuqoHG3Zb2NeyscYhuwLVGZtL");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateName("Z8snESMtFaSWvZWWMyKnFrZnj8wN2cHjemIjBekL9ohcrKOnVm");
            state.setStateCode(1);
            state.setStateCapitalLatitude(1);
            state.setStateCodeChar2("3R8wdMt26xkZfDU2FqAyzPtfgA8OeSIT");
            state.setStateCapitalLongitude(11);
            state.setVersionId(1);
            state.setStateDescription("520YJRLRXys7fD4W7BjHoaSZgbqWCt2l2dALjmNF8JU0O7I5bX");
            state.setStateFlag("uNsY5C4ks7Uyg8nxTbZBCqyn40bfZ6A5xknRv0Guu1Srna25to");
            state.setStateCapital("E88P3mlKaJU5Rufd9JumX6245reHFtD1cJ41ZBhrZjswOr2GYG");
            state.setStateCodeChar3("hRmnkPsxZgEU51A0NyE6WDUbKa6ZMjC6");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "ziLqcUyXZjWqtQCjDfgyR0RGQWsfJY29lVniAZHYVby0vUuiNfTL9rbdPEgE7zeLwoRCcRw9FVHUc0dROSzAGiTOQzbLASqW0V9BXL6UGvvPskGEJGUira60UKLrtDhC2Eozt1v4e2XwO2cdXkcyG1O7Wp9RIyN9bpxtO7pT7aK8azW6Wbzl8f3uci0jNhZqCdGpdhAKkfQEoaxaTxv8lh7pFkDaNWfPYt0uU6qtWRUZVn2rryYmWN3xRs9q5bLdK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 3));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "TSaxtgffENeuKlI1vxSiOIG62kzjN2Yhn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "LcsEXoswbsFGsKOfYXVN98YBlqsUupZm7"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "6V51W4qTYbmY1BbtaF6UnjwwDIObkXvCAK9T9ULeeUGraFnF4rDvDifsgRINJEnMHdd4RnLGRtkCAkvr3b0GiJOwU01GiqGur7T9IuomF2vhzpG9y1GlEZiu0FLVngQkAgXEkfUL5m1QT7D4XB8l3L0FlHet688YPqLbdH1i7IaUIGDtf1irP5EeSrlUzaDtVXi0GfXkC275OHGxW1sVRUGyMiqUwYLIYbzgnKzbIabPHwUvJFuR3ExJa171LGS2Q"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "R8bdPSH0biSp4SptGDFCp6gwGHBKxDMqV99iB41aDylaZXpRmwdwX1oaaDVtfZEW1tlYcp4TwtuojHe77bKyGRLJLftODMEsHvJEbBBMSa7cBOjF1XCwTxMXDDfB6bJvZ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "b9M3IYMjmbwnr3auk9FGHOyQ0cXvBmXEVykgOS8t9othzp192YgUaLxb2ImbKkte1c4kMSpojeXyAdyYuvNmsKao2gnAw4ZNyHHQjAA1FZVVpUrLmQhlRIQywKhLEqXvv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 15));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
