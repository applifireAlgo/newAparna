package projectthree.app.server.service.organization.locationmanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.organization.locationmanagement.StateRepository;
import projectthree.app.shared.organization.locationmanagement.State;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import projectthree.app.server.service.RandomValueGenerator;
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
import projectthree.app.shared.organization.locationmanagement.Country;
import projectthree.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
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
        country.setCapitalLongitude(3);
        country.setCurrencyCode("xQL");
        country.setCapitalLatitude(4);
        country.setCountryName("YG63IMMz29nuxETe4qdIRcUCZLR6x7HMJISFs9g2JE4ni0haA0");
        country.setCountryCode2("yzO");
        country.setIsoNumeric(984);
        country.setCountryFlag("L4EgqDSAsB6nNgLLtJ3YhvV1G6dh42PJ0GyCWIwczxZkgD6rTt");
        country.setCountryCode1("fZ9");
        country.setCurrencyName("I3TATjbzZG85AZe9IKP2Cbsknecwbo7BPR7Z3RnV7PHg12WPuB");
        country.setCapital("DjmfWv2gpwGNAALQounOPkamDH9AYUam");
        country.setCurrencySymbol("EdInGEqIwHrJw2l1GGI4MZE9ZwY7ugTB");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("iBZmGUwq5yc1FUKZxN3oI8cOLcgcHlL8pB2MZ5y0PUeMRHUity");
        state.setStateCode(2);
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateCapitalLongitude(11);
        state.setStateDescription("kXNau8DT7Ukl3a3C2dE5luwlfrCKA1rmGzch4i2Pdkordid73l");
        state.setStateCodeChar2("K2nXzAfH2B0jMH8CbBuJCdrxmdR4TUEy");
        state.setStateCapital("EUNME1Z7p7SnEcjHWLBEXkvmGCtsDheGPk7tVgaTOTHPnHICtW");
        state.setStateFlag("8OF41wyPDv0op9GkI2fDn51edZgO6PpFtCItYvblNwCZQ2Bh6O");
        state.setStateCodeChar3("psIpz4qICKSbGP7b0haR3XLtr36fnrlh");
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
            state.setStateName("39xnTWfEp2zRArStkmaFKOrpoPmHDC1rbKHnVLRrQwjTIwX7n7");
            state.setStateCode(1);
            state.setStateCapitalLatitude(7);
            state.setStateCapitalLongitude(10);
            state.setStateDescription("58GBlsnRFHchSAETw8AkozuVVstTM5INd8p6i82wwDKCJjC0Zr");
            state.setStateCodeChar2("95V2ON0TObyvbO9idDSGw69xif0hXcBa");
            state.setVersionId(1);
            state.setStateCapital("CcQI07UUlDUAIc0BlFb4DUPIZXvMl1mP6aqF0xrhZJgnr9lTka");
            state.setStateFlag("iB5SHXxDapqodZgE9jYOODJUcK9iXg6pHJxhLnt8N0l7fF61MC");
            state.setStateCodeChar3("BGzj5wvv71CFwSgE5JiAIFx1PbjsBmK4");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "Cn3PMNFziRBls2zbzSUYsrSnh4g0O9tVMnn6X6CGywmSHN8HWqiMAxi4PTdHIYTyV23MRZWe15LW4KdkgHqzf93x3Xq6zCd10Y2mKqvWZOEe5qciZx434ibPTkqWCqFXYT3v6Qfj9B7hfEeyUeLQROIWW35dy3VmbVBx68LDt87rpphgPwJ8cQNqTjxR1HXKDqRWnJJ0FpoJyyrNmx1fdAk5TGQrAnx6aGSNW8o6okbkt8SvPRTtNzdMg6hWtJpSu"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "3TRY8RqEBxaMoemsHtdyr7hcWqf93truO"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "SQZutjl4Ha15OVuJqCc59Y1VBWZAo9iXC"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "9W8E979MOx8KXQi4YIh5J4b0LMEy6ZVCueRGJNGgeZYS4GIKPfCAepeZNDhDTZW6831VF9GKeqR08uum5wjELUkdlWU3nWvpmKlTrldPoPGG6oQPwunJZWAcRWk0EfsnsoIA8qyQwUhFKSl427HaOZ2hfFI16gSGHxUCUS7Abs5hmTwLuxwLgfHvtuhc4Lqn1Emx0M9Fo7wWFyTRwtX5KldF58wdSUQVG0KU6IuQ2yxoaLSTtw0XubJET3KkB0Qqo"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "HSTuMfWnm9PBGfwMvULaDxCQmZsKR4hrAHaBl92DpfiilInFBZ3IebkJog5oLeLrIWSnaUTwRMNyqzNpy2wc2NZAAFQyruvF0cfxa6FtUHyFG82VFwaYFd6H6VODxK6EM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "mLkKrPfEzhTXnlfHObvhbwcXYIWBplktR4iPXsNqX8wHmYvkQT5sVOHMj3yx43NW20RgeZb7QhGjEFaMfkupFu07y3KGnkDtQfRrErXuRYAbTqT4Aq5XXN4tHVM3DhsjA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 15));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 20));
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
