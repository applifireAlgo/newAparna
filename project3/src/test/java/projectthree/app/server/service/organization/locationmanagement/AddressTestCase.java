package projectthree.app.server.service.organization.locationmanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.organization.locationmanagement.AddressRepository;
import projectthree.app.shared.organization.locationmanagement.Address;
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
import projectthree.app.shared.organization.locationmanagement.City;
import projectthree.app.server.repository.organization.locationmanagement.CityRepository;
import projectthree.app.shared.organization.locationmanagement.State;
import projectthree.app.server.repository.organization.locationmanagement.StateRepository;
import projectthree.app.shared.organization.locationmanagement.Country;
import projectthree.app.server.repository.organization.locationmanagement.CountryRepository;
import projectthree.app.shared.organization.locationmanagement.AddressType;
import projectthree.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    /**
     * AddressRepository Variable
     */
    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        City city = new City();
        city.setCityName("J3wbSmsmDexI4KkVqXKbMk0IT9Ue8Q6QpFwr7k4C7jHsRAh0t3");
        city.setCityDescription("xxRjDttzZUZnT44dvdHBNEFw8llAoipe9XlvwirGumlELpcTO8");
        State state = new State();
        state.setStateName("jv8hwKGR0xY20gVmWP4NudmSkWN1w4aeQZoEVDDWRSlQYoujpw");
        state.setStateCode(1);
        state.setStateCapitalLatitude(9);
        Country country = new Country();
        country.setCapitalLongitude(9);
        country.setCurrencyCode("vFU");
        country.setCapitalLatitude(6);
        country.setCountryName("GS19jGBCQeMT0p9T7zFGoEpF4FwBlfX5DuxLpvkxCSmWP1zYnH");
        country.setCountryCode2("e8u");
        country.setIsoNumeric(349);
        country.setCountryFlag("HIj126PjLQ9jkIqq3vjhKHVv1fhCELolnC9Ba6ONVjPBclxMLU");
        country.setCountryCode1("QQl");
        country.setCurrencyName("HDIxqIdiCube2A4goepbd5KnSEstklbzGxsiTqQfLcJXKV8fG3");
        country.setCapital("SxL3uIVF0ArjHMElW7HyHcubwaMfOsE3");
        country.setCurrencySymbol("wzhCU44NYuJgN3uX6qzYwMNbar631YFx");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("FRT8y24y6HQXlqCcCvefTiZkm4Uryu4X8fnDoAPbNp7weSOK4h");
        state.setStateCode(2);
        state.setStateCapitalLatitude(5);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(1);
        state.setStateDescription("NRymnavdDldvP1Mo9VQLygRzhe7WSLb7IlX27HvtdRDtQNwOZH");
        state.setStateCodeChar2("JNYmWHfNCLLIuJljjNTYefguEUr82P2V");
        state.setStateCapital("oVBlwvykBh1ZSgYxZ7WgIYjG6O6vueJXEGxBhqaVKH3VLACstp");
        state.setStateFlag("NA7OwBUYaVMsymyoDMdxsm6b1UMHBT87MxBhFNF30FlXJDmX6d");
        state.setStateCodeChar3("mCnpLgI5XdBSLxKCNAlcnrQ4blmUTgMi");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("jfaskBBuozTIp1yv2dAm8GK7I1iKYD3CSvmCwz1I5jxhgWzIeg");
        city.setCityDescription("VA3j6voqdme50jrTox4eghMnidHBv2AERN386CG2HphFybNCM6");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("WxVKwpdFOe5MLY8y1CufJzTiELH2eEupAZWBsI8JBmTrlvaIXd");
        city.setCityLatitude(7);
        city.setCityCode(2);
        city.setCityLongitude(4);
        city.setCityCodeChar2("GjSY86qB7v8B3paY2lb50UUYVIDbbAjV");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("mWR9duFZkTGSQvdvej1Z0mVOniZiBzWOS0oZfXJl1ZnsfiHk0o");
        addresstype.setAddressTypeIcon("Auqx5UjwdL6dmdh2KM261hIeORKauXj26Gci3WC9Fg6fkJv4WL");
        addresstype.setAddressType("B5hKBRxSIoo5C6gexhNvS2uVJBe5PjVrJGtcopHr5Br61j0N9g");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("z27CFUrovgEA1NYsrm85U3rWV7YNsBDxyjHdnPyI53fDRo3qSR");
        address.setAddressLabel("6lcnB42Zz6h");
        address.setLongitude("nY7Jr4MXFfxHZet0LhxkUqQdRSgCgbX5OHH8yLGH5szPmHd8d4");
        address.setZipcode("bzKHKM");
        address.setAddress3("CiZ30Z7Xpk18Re1EjtiXuFTm1iaHjgzeRXiupeqlywVbeHlLJm");
        address.setAddress2("BerlzDvRRGYe8bHBbTHXHuh0jVprvOoBFuH2cBsdsM8de8EX9c");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("jd5ln3yttZHQG0bMMhNZd2FrqGjaSSlZ6wnu2DgOe5eIV91w0c");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress1("2Osyhjktmpx4lZiFl1L4KWYrk0Kl4iBpD8I2bDfpnlUJoQCWWU");
            address.setAddressLabel("vRg3CwWn0i0");
            address.setLongitude("zkMqMHpHVXPOptlTuMtMcv4UMvFkEXhsvWT9TH84UHuIs5DR3s");
            address.setVersionId(1);
            address.setZipcode("34d9Ot");
            address.setAddress3("o9b5RsD1mnCV2lbSwH6st9eV7tSJaUE0l42W9CehXfDc6cPiIo");
            address.setAddress2("JurN5exQYf4LwHQ2XRfPvxEqc1rVDot2T1xvXzBbsn6hUCX5TS");
            address.setLatitude("CQFT7FCjQJRWpla8xIIeocwLdn32PRWl5hSxi3c8ayDt0sRbMd");
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
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
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.delete((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "qhTyCRJeXIIq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "4CsjNGtKh9IQiHzlea9kKxdvfRrdepvv4n2VlHNKzvTQoGIzqnS1kSkMj"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "RctYP1gdEs3WRjMQ610X7pD9i8zWYlDY1nhetojsXN5DlXKAExeNnILB2"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "ujoAYkWnvoX1eZQmOcr68eRfeuqhptreVEJabqkBxJCc32S8HV9Ja2mBX"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "xqM4Vtu"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "S6inmm6tR8Pc1MLmbM3tum8WRffywoHHybey2YMjecJKJZPAV4WhyWGcoMhchSNQ4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "8JwX6L9XoFfNTmBecnrVoQcRCIMXJjf8QYQeBh1aqhQv6JrnysoSvoPuG8Bs1odts"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
