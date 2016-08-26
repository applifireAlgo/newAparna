package projectthree.app.server.service.organization.locationmanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.organization.locationmanagement.CityRepository;
import projectthree.app.shared.organization.locationmanagement.City;
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
public class CityTestCase extends EntityTestCriteria {

    /**
     * CityRepository Variable
     */
    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        State state = new State();
        state.setStateName("OwZZZgMTFHzi7wGNIfcILEEb2zDxv91yXfkInkT7UFGxfiH9fK");
        state.setStateCode(1);
        state.setStateCapitalLatitude(3);
        Country country = new Country();
        country.setCapitalLongitude(1);
        country.setCurrencyCode("3ry");
        country.setCapitalLatitude(6);
        country.setCountryName("tVuKSUkGqGVQQDPuRtjovATPnfRLNn7BBgh2uBslimuwj5B66Q");
        country.setCountryCode2("M7L");
        country.setIsoNumeric(324);
        country.setCountryFlag("qU0db7cNj6WsSSMw4hHJ6z7MNgPpPSmzqbCxXca9Q6BGOLFEdq");
        country.setCountryCode1("Q8W");
        country.setCurrencyName("OMotGKNC5uSwfYeyHdj6dhhLhbcwVnEmi78JXB7jvzwP8zGjR9");
        country.setCapital("IvM1XotoGaDrFtTiRvftfT1jXjlRvPRD");
        country.setCurrencySymbol("DIFFw6gwCRk9RyYpSUat32aOo6zvYMrc");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("WKg0vqrwMYn5dr4G537Sc2FMwDc3NtvzjEgokSQApwomDktziP");
        state.setStateCode(2);
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(5);
        state.setStateDescription("g7fsrHItKs3aB34NYT8UoMpGzwczgQXwkQnWsq7H8hopMdO0sD");
        state.setStateCodeChar2("tRTaWxw8IRVfGGvSz7QeCP8rzBvXRmcC");
        state.setStateCapital("BqcDe8EGDxPMgWDJc3WxStcfs6oIapbQcOgHtBkydbx16qV9QM");
        state.setStateFlag("29KHGYxXv1St83qoafKuWbOwC0eEjxnhhTZNAiYp5eSxygznEO");
        state.setStateCodeChar3("besNcmTkFX4bH2GJBhfHNGTjLwaQxqe7");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityName("Xrm3YZDMryq7ENl0wM7shLtUU4KAcMLCwA9r2zRP4ZgrL95bnJ");
        city.setCityDescription("AM19NxP9jAYZ4dNzzbq32aWuLTvRe7KhXS2i2naMbbvrs606z5");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        city.setCityFlag("T877FAYwlZ5jAabpWWP6UZzVE2fch55c3oN1fhF7BK0wJS6Xvw");
        city.setCityLatitude(7);
        city.setCityCode(2);
        city.setCityLongitude(1);
        city.setCityCodeChar2("WBOYtlqagWLXYOCvl7HUJKu0XYmnapjj");
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
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
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityName("vwP0Zm7LsSCTjKS5Ix2Oz1zLIzCZrNh7TkYtJxCFSEdW3WXeNl");
            city.setCityDescription("Ipj8jCYSYrwvBOcRFPfudjqigPv36HXw4kPUrXZNeXIZ0OmPWZ");
            city.setCityFlag("QppUFnugfyyqp7yO3BKKDAIpJP3j9Aef9TCobSmCDGz4uGb8hb");
            city.setCityLatitude(9);
            city.setVersionId(1);
            city.setCityCode(1);
            city.setCityLongitude(10);
            city.setCityCodeChar2("hzQl45k7NI8lT2fex3OI4Pa11WHaRx7w");
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "xicGhO3RQrckg8uy1H5L6DBRjmBW5HMl8j3gAWzSLUPnqVcFXeQnyGoJSUUPFJL55gSS4SziewhtrCV6EnIUU6dAnf379YE3UY5IKctBkbnKdBWSdQSHB0dpQ9YcP9rhebEqgWNuKnhIAtyu9fo1w6Bpg4ZSJDusRbWSY3eoxO7vcEKCnYmLpTA41Sr0r03VcNDuw35v2g7KnThVyGiHS2wLh5Lo79AuXPHFPxyol1VqvRiknrrfd9FHz77Y7b8Qn"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "0wSOLAeDFTRaoetVIM7PDe4nJvFFlsI6A"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 5));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "YQNfs48xGHunQ9Z4N4QmoHnzwzBM6QtN3jN3Sz7r6E8b09SSqKwgs7k78sKF0xV3rRSuGHhbxwGbx1axjfe24CprTdgEzQlX74Uyb273bW34uceXPiijevXj2EIo2TSDs"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "RryMxrdjsHcTRAzVHH1FfzMARAgIhvuvZWrHPUO6oHYEhYLJo3i90q0z5pTNljW1h7SkLnEwTVWqIF5V7sKRDLHFSz0bQQvp9vjk4xTArr0CbNPkVwtkp89lMHvFM90rH"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 15));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 19));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
