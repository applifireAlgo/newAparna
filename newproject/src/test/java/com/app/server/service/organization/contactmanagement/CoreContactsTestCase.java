package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.contactmanagement.CommunicationData;
import com.app.shared.organization.contactmanagement.CommunicationType;
import com.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("V3RddNT3jwP9oyXzZmtGkTG5hPL2bKAfcPhNGNxmjVVBPJ9uUC");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("doGYCaRgHtTuh78ac80h84Utkb2zEfSP66zrUQbAkZhNVCCmUJ");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("Qs78tZR1Y2Wg1WXFPsfvExSnLVkbZtAE2GUyKOIwSCyP4JMan9");
        timezone.setUtcdifference(6);
        timezone.setGmtLabel("qpOQHBZZrp7Q6za3eIyBhl8Cy4bd5F57sFZQu7yxUjdbteOOJ0");
        timezone.setTimeZoneLabel("87jXKOF8APRAvAe6s4At4yuBTRbuw1OMvrvle9XyluYEuHFkrN");
        timezone.setCountry("BmtCmfDzo9C2N6n1SwalLw3oOmEv8HICjNWrm2legBXCMm8qrT");
        Language language = new Language();
        language.setAlpha3("PeR");
        language.setLanguageIcon("JG9JUhs0uG8DYl99pyernC78gg2wrvsF1USbgExv6aTTYiukRH");
        language.setLanguage("59SZ0K76KoBs9kQsYCfZvChCIw5MGgOzW4A4XqbKvfT7BgCBUX");
        language.setAlpha2("3m");
        language.setLanguageType("IyzlZFJNdAccUPHX2TpucdocTunxGaWT");
        language.setAlpha4parentid(9);
        language.setAlpha4("CHhu");
        language.setLanguageDescription("FnrdTXhdSUcShcoLyo5IBEFDdfzG7Oewchn1Z8DqjJXiSIIYus");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("1asIu4v9xKlpoQIHlig7PN886ViEgvOAAr4z69r7pwlDBdtfrk");
        corecontacts.setLastName("aPh3GoDf6ZvVGLddwsotAccK3iRFFS4Bv5eOBUKLs2ZekGhWCW");
        corecontacts.setPhoneNumber("PN4kGAwnjSexqcWdGfV4");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("7LKMLm2OG5TCn4m6EvbXC94XLBDz3aLV71MWZu3msXYdv0T1KZ");
        corecontacts.setEmailId("0Yh6RsYebHHn0k2jOTacUnBKUycgNoYyPtqXd4OCNOMvN1b919");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1472208974907l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(4);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("ZJuOj2o11pnhoYbvY57LKWrh2wp4Rod82dGZAD2jgw6ug26krp");
        corecontacts.setFirstName("ClnUi6CgxbACxAsuLtM49JeWql5GNGePCBuwZTS34rOabojQAN");
        corecontacts.setNativeFirstName("5CB6jOcGTTjTpyYnYCNkGGJzgwJunYrNwWhAqBduaax9GMCUwK");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472208975036l));
        corecontacts.setNativeLastName("BVQIOqiBs2BDjuqGKbycd3TKyOM3n6AG1K5pzJSMeJVxNhS1rt");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("zYjyx6bz7uYZiCUsFP39haOFc4VGSupNHsJsbULWfxHeDg03nQ");
        communicationgroup.setCommGroupDescription("Seg0IQuqiOKwQpHFj0pR7D2VuwuD10Vj3lrgdEJV7sDFwBo6SP");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("rKLK0iblVCOz3knH2NDbu0qbSbeFAlkzcPhcRoSLvN7kAP8BLY");
        communicationtype.setCommTypeName("jsjHTslnxlLac5PPQJKn1Yr98WzhnAWxyHTvHIXMKGjggGiMCq");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("7VIdvoCxQo");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("fOXNbmxcRIS");
        address.setAddress1("DJY3l6rhZSMOdJb3XKrXs6O1V9jFczsWJBRgyQhjvSzOYSEqLz");
        City city = new City();
        city.setCityLongitude(9);
        city.setCityCode(2);
        city.setCityLatitude(3);
        city.setCityName("knRDnyvOnbNkU20hmlySCD0YOp3T8z4kMb8XsHnzOQBC0qkDis");
        city.setCityDescription("uYb8qpVPwRqKRYUFUBbJUyDOit60Cg7t22I0tQEoh5j5Z12UHN");
        city.setCityCodeChar2("p8EZzqxJdleifrnSDQ3dsP8vPEB5OUN9");
        Country country = new Country();
        country.setCapitalLatitude(11);
        country.setCountryCode1("55A");
        country.setCountryCode2("fqg");
        country.setCountryName("B4QPGKShiIN9lWFqlSV9fXHXLmtnKR1qPV9d5U9nT7LaOmPME8");
        country.setCurrencyCode("Jv4");
        country.setCapital("HEKv8LTurqxgNJYbrE9Va4LLRSRo6pnG");
        country.setCurrencyName("wflBb7MCEXN6F7VWh2XzJcThQmB7XkIG7bUM69bZI2ht31FPFJ");
        country.setCurrencySymbol("fOdziGjRmvWZMh4Nh6xR9gRDLxMElYTz");
        country.setCapitalLongitude(10);
        country.setCountryFlag("IOtO2VoaWQGuX0bElY8nroRpsMVJVqfXytdsbB1DRLs10MutqX");
        country.setIsoNumeric(889);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("1Z0ol8LxSr9UYVyRuCYdbyHxOtydY1XAt4YOH3k9XlcAMtZaVt");
        state.setStateCode(1);
        state.setStateCapitalLatitude(11);
        state.setStateName("xkG7Gf2hWRdlDZ4sZpysj8suYGsnKmRsCKLtkrixtp12eJJeZj");
        state.setStateCode(2);
        state.setStateCapitalLatitude(4);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("GrLdxrFH7rOUDMDzZdV2EKDqUD2HFvZh");
        state.setStateCapitalLongitude(2);
        state.setStateDescription("c7AWAqNTnCUbfzLpqnVH9V6S2iogaqlCoNCevQmi8oDBYUkE2P");
        state.setStateFlag("Ogih1XtwDLXCTXYTuNE1DoUiXyBCW4FbnLTvAMXH8uNDNbsZBh");
        state.setStateCapital("57u8XeNdiMXBjcUHVE3CRAMtBnpmjjdkspzHAdSb1GJytHQ9N5");
        state.setStateCodeChar3("mTqxJb3TjeRdSmcfq9mLJSlIhAtcMVCO");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(9);
        city.setCityCode(3);
        city.setCityLatitude(1);
        city.setCityName("OO8e4CmlcSBVbW40w05lpUdhNy7vNRJprURv6pW1amdXxZI0ta");
        city.setCityDescription("NGeDkoIpO1W49nTs3OIulxsOehCR6rqcyXWV9uH1OSD2oaucL0");
        city.setCityCodeChar2("L1cxGRKLncHth4OhzXblHO7xx89YW5zb");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("03Nei3AmfOWfHYzs2ZWfRXSEwNPH9ng9qrpAx1PYPT1YpczWrG");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("dEEuzITtvtY5QYo102LQHD6lU7tXhCkarT6cgXI0qrAFLyGivo");
        addresstype.setAddressTypeIcon("KDh2mQOKkvrwm13P0bxIkfIU0zbXZACBugG0kbfyzRGMJQ7WNS");
        addresstype.setAddressType("9nuOImt0B4uhPFB74odysoa0OnZhXiMd4pCsd7MMhiHvJ1xslI");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("qDSE95HPYNe");
        address.setAddress1("jt7c6lOFSFpCtphSUo1cqkmijEJm0NFlByVrTAHyGP0szhRJoj");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("h1N6K7EZ9uQFu9fZDIn8RNyP7yqHK9ycx8g1u7RVoobnM2vsFX");
        address.setLatitude("zwabS4pHAhFApvQFdE8Xk84I0hnc7rUZMjNCvMDKTJmbG6MvqD");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("YEFPBU");
        address.setLongitude("UtS8FiNGUUG1V8M4g3kqE98SrIGRVUoxwGAALxRefQYq8EdXBR");
        address.setAddress3("RlurSrsdmQfP1U8GpVYfPMySCXbZ1c3EDoTjnCZb7vY8qW1EuE");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeMiddleName("6rTx33gVQTfb8PhQM9blECahD6nVL9cpyyq63rj0WjyT9BKNnV");
            corecontacts.setLastName("INp9uK0tE9FVAFTklPCoyCyyHHoQmg7hlu6b2KaadppcsuTnUk");
            corecontacts.setPhoneNumber("Orx74vjsEjD9TnKFpYxT");
            corecontacts.setMiddleName("a4ms1IKpvGmEG6q350We24zuatJZ44pnMzPrHc6hi2MYSFNcUd");
            corecontacts.setEmailId("FhKt9Pj41z20ee3Ybakbxq9VhFunjaqnAgP1VOzU7IdqbM33WS");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1472208975455l));
            corecontacts.setAge(40);
            corecontacts.setVersionId(1);
            corecontacts.setNativeTitle("8ZAUfNu1iNO36Zygi0VRQkSL5lQHfJsD4GQhDJ1F01AXXfkMDD");
            corecontacts.setFirstName("OWjLaParIw7gwIRFHVigAN6P2XE0O3qLOznFC9uqf1wBJRedTw");
            corecontacts.setNativeFirstName("MoOcoo8n5KOXtfNWggtgsZpFPvppH6OGymGUNgs6NxkG6m9pb7");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1472208975561l));
            corecontacts.setNativeLastName("F9E940euVhjIDtvHvQ3PkoxTOni5N6ALVNk427gzuCwcsfkNKj");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.delete((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "MaT0lGuBJkIzZhw8GeIC6Kx4MILpUAZIPA18GcYnbFnibVwPqa3AfoXNrsD9LFWUdNgTyGeJ6acephYEZwZKc3cvp1i66Ng8Ol7AzNAcN7UnzUZmr2McxbquQcAyEdCXFLUfkE0NkskslLlfGzwdJEoLxLoqKt5YALcHaR4L7biQHw3YUynUjowVGQ4w7veq9O1nTa8CGHcBKVYORBWT8N0BHq0Wla9zvGD7nTqHfONy7ChUCgNsxLq4hzVGdjhk6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "liVIt4mSYWW6NGLra1Pz0F1tMXASEHbHiU8nwYun5cXroGUgZSedk04Tv4o5rx3liwh9i3SfgATBy0orNWENoegmk6UFtU231DMMzXQ6OVuccs2WpDCi6npagTMOxth6BXMQm0kSN4mkMFrZmhs7f6rYzaYJSOmItTUY1NTU0HPzgIujQInYsgXceyMb4WV2l6mGS3vYvI3MojSGtwaEbJtFYvOHJODOsVFUAJ2zGIbboT80ykdZzu5CdnmAcCG8i"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "0xzaf9G2JcFERJ551ZT424DjQAFizokRTrTBobJKqSB55HxRIk5GTNgU9CubauxquHvQlvw87qu12NzTsWYVinih6PEmbRLkcu7UQlEYoacjhpdZbdO94t437Ry0xEHhiW2eGWdtiZsAaRvjht003SMKbPoFLAsHXLFbkhx5fxkOw2Rt6YJZgHFSGu8TsVfxDvU9ZpKsdn0C6s908sCZCAtdwE7HG2rDxmKdpHkfofiEvn3kvrc3BcPEzeNMnbUHk"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "emXmS1b0jew2w4FzzcDotTGsXtMnJFKYDtClAnq8xDu9smr1W4B4QuHRZYnBGw5JZ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "eDg5LmfnJAMu3ivwHVqtlMIwvqLt12zWcSVPLLchX4EX4mV7QGkL0hieZAxiLva3sUMuJeONUgCmgmePaP4IjSxbkLIwKrNqwYsvAUgrFuN6RufowFslDu0obmfLzqoCZAmdMxnHtQqD6w3VHKB8KzTmbg3sdmshw6xZCG2Pc3fwPaUutk30i8Kbcrlmg21JZ9lNah5tX3kRkzv2hL6EdfV8NjdOIFXXN5dPGOeOvdwlmU9GYfbP74OSEhFD6vbX4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "XE6EaEGZkqmDbcSEkLmCIEpM0E0ACXf1yWyXdBCQuh9MFJLDdlqKiV8b76u4HxnkieCbKTW29E8cCFiEp0ucy43V43geKJ87yn7fLDHuOL6VUrDh8Agmi9Ht3xzEggvjueBtUmp1czxjixYpuadKBasjLjl0pUEMnpuVURoUPhENhlzpSIXvbOPgfgWlLVBGy6xaSw4qiLEt7O4LhLnmTpEmBR3j1UvqHVj2gGyVYemBsKJQEUg1dpvnPamim7x94"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "BrxabBqztrprbKfdjddDiEnEqQQaobReEyVVUBmVEEn0dQT25WsaZfLXYi9LhVwI0ZqrlgAzNOrV4S5wHUUhiYv1sAaoNLNqsPoCMfzQp0K5E8MMkKGyjR0glptRzSwp5ebRKFpSOdKr7nHKSLYJtNHaqF4D20v2AS6NvIZ6XVT7uFYc8KCVw6ZyPHPOUqllTmPo5l6qczFLGUYd7MyA8N0nzEQKfskUFAkKWlXcB7cdABaoposk6OfxfxpE9938t"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 127));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "zrqnW5A7cHE9AP3pxu0UvjT5rjqzVokXdUIVrnKx0QFPm4rMoQvchvO8FK5k0C2ADcUfecotEtiex97icDxzeM2NYUqJiOulzl0JwWqsbkdkT5pmi9KhL7bDirTITq9zKbKypCylohf7k5cZlKBeDjGiTvUptT8EvMgl25SEbWXtkuq4Q6Ysqkc36n7wQ2Uot0c0Lb5eeI7udbrvlI9YO1TzbVnaaEjaHMyZijrCQ7JLVkdETe9znvHYgHplJEH0i"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "4Y29IRZw7dGgoQdO8ZSDu"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
