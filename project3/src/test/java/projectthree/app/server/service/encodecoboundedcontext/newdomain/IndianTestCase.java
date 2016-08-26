package projectthree.app.server.service.encodecoboundedcontext.newdomain;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.encodecoboundedcontext.newdomain.IndianRepository;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Indian;
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
import projectthree.app.shared.organization.locationmanagement.State;
import projectthree.app.server.repository.organization.locationmanagement.StateRepository;
import projectthree.app.shared.organization.locationmanagement.Country;
import projectthree.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class IndianTestCase extends EntityTestCriteria {

    /**
     * IndianRepository Variable
     */
    @Autowired
    private IndianRepository<Indian> indianRepository;

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

    private Indian createIndian(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateName("jzgn6myNJQJuFxDnmIJOe81e7G2q8QdkwvKenr4hzufQ8ed4c7");
        state.setStateCode(1);
        state.setStateCapitalLatitude(10);
        Country country = new Country();
        country.setCapitalLongitude(3);
        country.setCurrencyCode("ao7");
        country.setCapitalLatitude(7);
        country.setCountryName("izYrj8q6vDnIS6k0YzOzhu4PRwn5fz6SAH0RWH4O1ALY2tnewo");
        country.setCountryCode2("YG5");
        country.setIsoNumeric(992);
        country.setCountryFlag("Xe5nr40jQLjQQJtRGjWhquKJpIaA5eNjuglxIF3liovfH1P5UG");
        country.setCountryCode1("sQe");
        country.setCurrencyName("VfCicE5RU1UL3t5oo8j1HJtwVN7fUS5P7Tmg46EYpCeJ3egHRx");
        country.setCapital("PZxtDyF73kU2dKTmzpPf0OFVHgv93g63");
        country.setCurrencySymbol("u0l9eHPX7gqcQiQj0NdoxszwNrKBo4o0");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("A8aG5pmuirRWo9TAYwF0otpmDH5YBVICOG7452g5dC0SZMzNbt");
        state.setStateCode(1);
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(8);
        state.setStateDescription("ZoqwbCJbrDLcDAAl4WvmrYxbNg8sOsjeERVvyOOgqr8uxXIcxw");
        state.setStateCodeChar2("4bbewJh1EYObnliVCsYgge5UIuIzSKlp");
        state.setStateCapital("hHnBHqdEio3koxUYIwUs81PMoYdBiGSd17VAEZ2L4lQ4AcdFQM");
        state.setStateFlag("mVUecrx54il7mNo6LPvuSmhJpCqBGkvEpWQgwL5ty3vlilVB0w");
        state.setStateCodeChar3("i9LDOq0X2iuUowLuvrp253CKbKQcDmzA");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        Indian indian = new Indian();
        indian.setFromState((java.lang.String) StateTest._getPrimarykey());
        indian.setFoodType("pBJmFjGXFVQhpPjDh52mm6BUsnrGjHpnBVdUZJo5m8ykB6KFci");
        indian.setFoodName("N2KGcxOEdG0JMDG1jnzmfXuSSDnv9BORaXYBj8VJo41VhxCLDe");
        indian.setFoodId("sZuoMA6HujrLLXqL0wb4cJbvKtDPXiQbfPXuIKMV1jyB9SPXJU");
        indian.setCuisineName("nAhlQwCb6aCkkCh0dav95vvh0vyazH1Z51CJT78O1KjAeosdAi");
        indian.setEntityValidator(entityValidator);
        return indian;
    }

    @Test
    public void test1Save() {
        try {
            Indian indian = createIndian(true);
            indian.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            indian.isValid();
            indianRepository.save(indian);
            map.put("IndianPrimaryKey", indian._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("IndianPrimaryKey"));
            Indian indian = indianRepository.findById((java.lang.String) map.get("IndianPrimaryKey"));
            indian.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            indianRepository.update(indian);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByfromState() {
        try {
            java.util.List<Indian> listoffromState = indianRepository.findByFromState((java.lang.String) map.get("StatePrimaryKey"));
            if (listoffromState.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("IndianPrimaryKey"));
            indianRepository.delete((java.lang.String) map.get("IndianPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateIndian(EntityTestCriteria contraints, Indian indian) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            indian.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            indian.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            indian.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            indianRepository.save(indian);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}
