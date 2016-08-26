package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
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
        city.setCityLongitude(3);
        city.setCityCode(2);
        city.setCityLatitude(6);
        city.setCityName("1dNmaTDo1GtWM3jEDZFMps90TlaHVZuMUvQAW4IBCJPq1FpIbh");
        city.setCityDescription("nyPRCFWKHfqPX38PBymqPDhTGTR4fJxPjfoD3JyMvfYjaojsn8");
        city.setCityCodeChar2("G9ffhJCmEQhI3r7uMoPsWcLhwWCZg9SJ");
        Country country = new Country();
        country.setCapitalLatitude(9);
        country.setCountryCode1("lml");
        country.setCountryCode2("t0o");
        country.setCountryName("XLPN2tIVDBzPhPIIrpzJ4i6JHb5xOHoyoAL5hNdobL0gpzat0c");
        country.setCurrencyCode("yBF");
        country.setCapital("t9LGnZ38cGSysAtIPYIMYhUGmxm8UH4d");
        country.setCurrencyName("jEl7CiD8rchXTNGZIJBSOb7Y1s2pLrWur6LTSitUQkpiNxZeWC");
        country.setCurrencySymbol("eQNdelhavU3DAoQT4g5wchv13SvBbnDl");
        country.setCapitalLongitude(9);
        country.setCountryFlag("Ffv6yrvZGL5MzeOxOD2FCuRp5u9bWvLsBLEOEgy1Yb4Z4ebVQi");
        country.setIsoNumeric(608);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("7c5WK8gILuPWIasuvxsLmluid3tSBMySaMTehQpaziHyYo6iwD");
        state.setStateCode(2);
        state.setStateCapitalLatitude(1);
        state.setStateName("neOAUPjN3m7MmIfEebGgbmXI3x2Jz2GVXuTTzjzP6e3uQj1oUS");
        state.setStateCode(2);
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("zFWQBjU7wWb3qA7djDI7AbmypSFflCyL");
        state.setStateCapitalLongitude(5);
        state.setStateDescription("ut7FHpZCGJHNTyD1OiNIALbfXXeNfVIsfPhv2Cdt4capaHnigZ");
        state.setStateFlag("F1dXqbRD74MdmrasOKskzpoykUk3G0ppFMLHBulEwgmmpj56tg");
        state.setStateCapital("O3AAvDepsqMwzv7IWFM9O0yaweIxsu59pSVuY8aaJGIlaCiC7I");
        state.setStateCodeChar3("8MDXvTG0P4HBkb8xERHL9X4rAK72U6Ky");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(5);
        city.setCityCode(3);
        city.setCityLatitude(3);
        city.setCityName("iH4Bhq7Etkd00BSHCQn00T3fAA8ePNLP140sQdNdMAWMNIsCMH");
        city.setCityDescription("ZvlAkmgrznjkyovbHzQVPPTAWgbVTPWrMVu19hNhKkxlnpW8Vg");
        city.setCityCodeChar2("U3frvACa6rJ7sKWMfXA6ryoMe8t4lqPY");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("wxu1UixtihnMwWpCjLfcRCNIOg8cwJaDwrwhTE8dd25QIEb1O4");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("iAggkwvqJLnTB4SwY39dl9Jqq59Vl0e0fFla9httNaprkcqZwP");
        addresstype.setAddressTypeIcon("loenQQIhYsIu1n31CE2D0eL3yooL1T4rfYwSbV42Kn9gWQxzQR");
        addresstype.setAddressType("ahrhdmkqh1nt8H7FjCgN7QF4DRMWntc5bn9cvdMZI6anpTk3Jt");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        Address address = new Address();
        address.setAddressLabel("FRbW34iiZqX");
        address.setAddress1("5tGn38nnhrUgg8BKVqKLn5BD4gilUBW1tvs01aGoHRKLsEbg7L");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("KrwkgNRCc8SI4QhgAh1uzsYO00hwJ7h1QZuF2gWzCRnkwkQLZ7");
        address.setLatitude("APyyAJV2SzOmVnsUl7j7W83l6XXnU4lTehq7CgJtb2Q58uBe3H");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("LhcV6n");
        address.setLongitude("cNfItIEKcngePzgjnl2wC4f2DDvWO7GuA4DXYUTUxn357XElA1");
        address.setAddress3("TKArneUTgYrTUWSBEy90dWS2hit9NTksnDRPHayp1YblxPeUvv");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
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
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddressLabel("0hdAFbK2VA7");
            address.setAddress1("3ka6rD68wfRGwpNu797wRyT5UAREdbtb9mXtavD1qLwPY2OIfr");
            address.setVersionId(1);
            address.setAddress2("sqBkVmMG4dCY15PKvwXEnPM1mSU4fqg8F9KAEgIkI3f4fYv5Tk");
            address.setLatitude("unUbim9eQvJbohJbH4XCxPlbDGJmQZxjs6sP9WdDf6hWys30o7");
            address.setZipcode("58UEQI");
            address.setLongitude("Gfx28GQnsjwPhnHGlqjasyvJaGenzPjWGFiLPFOtTzKCkuWx1j");
            address.setAddress3("p9y2n2q3SoBcAGWQPM0DDr07KqiuhsYKEYBgxGXpmGHm8ZslRi");
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
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "O8OCPHiTBrCr"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "xwlDn8bogVbATB7pMjpwHCnB1jSSN7Cdfj3pxgKqtJ8fSlTFnGV9WMCkT"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "uI3xeGIKjB8s9H0dBgMtvV3IVsuBGVFTV5zHV4VDL3b2e8Y86VFCbryX1"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "L4fjH86k0hxZeRtac1RflU8zf6JjRiKMOzefuFQgT6j2HQDqNlu8kMre1"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "Gete4f4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "W8LQQk0MBDlIiQGiUs92b4vHTt1l0a9KgfvnWZc6iXUkwFgp4B6xtUzpxTzFHknTk"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "Jc5MgkuzpIrGOs3Ut3zq2VLjH0ZlWPTGkGEuhhtjq93J0liCHktA7ieqViELgNZLl"));
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
