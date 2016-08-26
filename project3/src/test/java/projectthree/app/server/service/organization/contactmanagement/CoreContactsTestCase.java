package projectthree.app.server.service.organization.contactmanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
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
import projectthree.app.shared.organization.contactmanagement.Gender;
import projectthree.app.server.repository.organization.contactmanagement.GenderRepository;
import projectthree.app.shared.organization.locationmanagement.Timezone;
import projectthree.app.server.repository.organization.locationmanagement.TimezoneRepository;
import projectthree.app.shared.organization.contactmanagement.Title;
import projectthree.app.server.repository.organization.contactmanagement.TitleRepository;
import projectthree.app.shared.organization.locationmanagement.Language;
import projectthree.app.server.repository.organization.locationmanagement.LanguageRepository;
import projectthree.app.shared.organization.locationmanagement.Address;
import projectthree.app.server.repository.organization.locationmanagement.AddressRepository;
import projectthree.app.shared.organization.locationmanagement.City;
import projectthree.app.server.repository.organization.locationmanagement.CityRepository;
import projectthree.app.shared.organization.locationmanagement.State;
import projectthree.app.server.repository.organization.locationmanagement.StateRepository;
import projectthree.app.shared.organization.locationmanagement.Country;
import projectthree.app.server.repository.organization.locationmanagement.CountryRepository;
import projectthree.app.shared.organization.locationmanagement.AddressType;
import projectthree.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import projectthree.app.shared.organization.contactmanagement.CommunicationData;
import projectthree.app.shared.organization.contactmanagement.CommunicationType;
import projectthree.app.server.repository.organization.contactmanagement.CommunicationTypeRepository;
import projectthree.app.shared.organization.contactmanagement.CommunicationGroup;
import projectthree.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
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
        gender.setGender("fEs0cQcdjhFNrhgBgMxmzpkWN34U7Bf0AL0tf6q51JEJNITXIW");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("G8IcK4ql84jJOQR8ro0vhkK1Ei8ZmpLsBqBKj3fPki1Tdlpjzt");
        timezone.setCountry("30zjHz8YoBSCRpXhBEZdDwUNBiXHy6tEshC3rgG2Nyd7GmhoKA");
        timezone.setGmtLabel("xFFErQmKyRi1snRh5NsCfTZ1qZJi7e1qRMoXFYxLVU5UNcVLgH");
        timezone.setCities("5rUgFCtuCTbdt3IgDP2JhKA8Yd5tLUQh0RNE2SnaJJ9Ae3JX1o");
        timezone.setUtcdifference(6);
        Title title = new Title();
        title.setTitles("cdgG63iKKAGhCLpKD5qXFBPYH3PthzrMdP7g6pafT5DlXggnzd");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageType("CK8tjfb6JmL2rb8XkApFCW6YiLLTHwxk");
        language.setLanguageDescription("rOum6UrLdzFPM4ed0d12BHx24EB3tZ7B1AyTCKfXLznvm53qXe");
        language.setAlpha4parentid(7);
        language.setAlpha3("t2m");
        language.setLanguage("ByhPvd045ihy0E6erSeDC31oglqdJwwox7YNySZBlKVEgwDzwv");
        language.setLanguageIcon("b3GDvlalDehWUDCZQIghoUdv5DndPBrGAk9o0Y2wgCmbNUIeol");
        language.setAlpha4("8Wpc");
        language.setAlpha2("RS");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472195845551l));
        corecontacts.setMiddleName("5XntCxdxGcqYCK7z5KiwLnfqGcs3hS41D3t9V3XefKzVSdQAXr");
        corecontacts.setNativeFirstName("MI2GbBHYbkDJdxlnjsIEa8T8FcHQQlgqTf2WG10eH48vzk7UsD");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("xhj692EJ4YFFSNrgcHyW5LM3NcX532PGUWCTopsPzmrqrjlLVK");
        corecontacts.setNativeMiddleName("qzAjK0HJluDJenlziDSncSaOeIxZeG507XrzuY5P4LD0TLIRW2");
        corecontacts.setNativeLastName("8P6MbJ2F40DGGNp4d6FxfWtQPHRYINcheCDXcE4STm6N7PS1fF");
        corecontacts.setAge(77);
        corecontacts.setNativeTitle("jqVdgywYFVnnRMLi0o2J4N99qagMyG26jmKWPRQbVdKnbcu4BR");
        corecontacts.setPhoneNumber("QKqfE8mjYHqEVc6kN9e2");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("qQbkAwWQs3HAOdE2JrJdmyflOVXnEoKZDtbbkbgx6wnMIc95KR");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("AfPXkvTKsK5svBsRyBKTXuZdx6GV8EUjJ7L0rHj5XjstOV6HxQ");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1472195845671l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        City city = new City();
        city.setCityName("aI5scJuJecCeXz1rZ59I3MiDUWTChqNn6XZ1T6gskUAjiWmHBA");
        city.setCityDescription("2EKQ2uj3uwMYx2qS1FruVRTRORgdlY8xrNMZA99Y7H7iqEk7Br");
        State state = new State();
        state.setStateName("0rdi3pDLHoFMgWW9UQpM5PzJ9Ice7fhDmCykCFA7ISD3jT9oAk");
        state.setStateCode(2);
        state.setStateCapitalLatitude(10);
        Country country = new Country();
        country.setCapitalLongitude(2);
        country.setCurrencyCode("pBH");
        country.setCapitalLatitude(10);
        country.setCountryName("VGzSUy9WhMTZUO7f8GO53GE3TAuEjNrASXXyrst6utn0x5InQj");
        country.setCountryCode2("UDS");
        country.setIsoNumeric(346);
        country.setCountryFlag("GUuBfphorKxn5gnV8JfUKwOIxWk65RPByJKfqsUJbAfcCWTX7O");
        country.setCountryCode1("R0i");
        country.setCurrencyName("gPR17RRJh1jM8ezN5YRKJX9FYrIqOB2N1FihsP4RbiIOmHIUhP");
        country.setCapital("lfwxtyKfBJ5VwFg80eydxx9krujq4WYn");
        country.setCurrencySymbol("uev0wN2cU0XDc7F4JhhkVyByFXjIvLc0");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("B9VmzU8zV7iWyBMEngDDBWAqKDfYWPNgmTueZF5JttqCSDYxP6");
        state.setStateCode(2);
        state.setStateCapitalLatitude(2);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(9);
        state.setStateDescription("pASvtNXYiVPyWQF1TEMnuhkL2LUBC1QkUH3rjpP6SATWEiubdf");
        state.setStateCodeChar2("IOQAJfdhdmOBQtOCrsfP1C7HnxK1oqAX");
        state.setStateCapital("yMxDiHvawh9YeTFIR8cpvMKxOUFqukqJ2jW7CqBOf7EM9ofHcy");
        state.setStateFlag("gmPgzUDOvv2rZDeGBEMmcA3MvbRcqM2nYZtctncSYXMPxkgLYE");
        state.setStateCodeChar3("dN3uLHWeETSLWQi8zY9zKVOqGoldNL3U");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("3r6rOpapM9wbeRjaWXn6U3WPnIjmipYWtX1UN1dnCuKem4iXHE");
        city.setCityDescription("G5i71JuuC0iwYvcx7aWvwFCxWmH4Mb9q862SHPkeUbRzepeM2F");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("HzTqSCBSrDO0OKMp4uG2uQTehSqKRYkBaOQaWInpuXi8XNTjcM");
        city.setCityLatitude(4);
        city.setCityCode(2);
        city.setCityLongitude(9);
        city.setCityCodeChar2("rqdSE8BzUTwBc3cqX80Xcrxrd6KRacEW");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("oajiQYcOMvGAT667LSkVlUMghrjXOwm7B276x2ohKl7aTWN77e");
        addresstype.setAddressTypeIcon("mzUxVL6w2PuHb8zSiIhmdwcXW47YKRpvLF4fIqf6s7AE6haHjt");
        addresstype.setAddressType("Ch3ocnc2KIzxV9RFzuPmIDrIkop0vPAcDTZf0sq6Cbu2ojYeum");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("34epL42wvlfOoQGJbsXMK1w0OoJfFM4WcdzUM1jb4BD20eio3N");
        address.setAddressLabel("bi9b2IUXsGp");
        address.setLongitude("8GEfsbZDwgZjHLexaKWyGdsZjEgUkfFcEZPBDIrItHL0AL54Bt");
        address.setZipcode("9CmEXl");
        address.setAddress3("c3uY9ecF89L1oXac6zVBDWIBJHyWZbstjD2JdRSieWbkQVdv16");
        address.setAddress2("GRcj0P0WHFzEizwPmK62WOIU9AWkAtxKNUn4CzFESWfjifX2FB");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("sgmdWYsuqpv37Pycg4ZfdSh9Vw1yhSMpTHnMxr7t6naaiVbbcq");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("SukemQJ3tuwhpV2pZEYAYi0KOu3DeijCLmawcLBoEz0V5VZbnh");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("RNMvENTPXYpz9YdefHzKS6QyXefbO79wJ1omPMegRpXj8OpWL3");
        communicationgroup.setCommGroupDescription("DCfOK0tI7dVsVGjI4ou18Lmpk07LA95jZ61pv7PD0JPzrrMGso");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("e7Xa2BZ4LltzWx2MSwryNUM6uhvugqyFNNS9dEJHGqk9S92qD1");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("Z2O29u9perRCpoYBMd686yHPqhGI1kfRYzDzGjOAxSTFN0YAyt");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationdata.setCommData("l5DjWvxhGT");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
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
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CommunicationTypeRepository<CommunicationType> communicationtypeRepository;

    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1472195845978l));
            corecontacts.setMiddleName("Ji5qIooXdZWfyS3FGJLY8oLF9k9OZVcqZsw3ZDu4t45QMAQKMy");
            corecontacts.setNativeFirstName("pgbhbe8khuC865CVS7yEycBQcTFLVgRkkyIvOkUfWenfoG9hDB");
            corecontacts.setVersionId(1);
            corecontacts.setFirstName("ai9DFtwLIhRuqqm53elXYVVFqlRXFgrIzCCjNpLW6jsQ67VBED");
            corecontacts.setNativeMiddleName("Hz39SjdxQDXdyvaYj7Gp3v3jh6N8lEWjM1JXJFkNiUzWYS7vTz");
            corecontacts.setNativeLastName("ehWzkwUO3UqQYA8XgzmLHlcU0PXMbdkmvPjFXzQIFj2b0Qz9zQ");
            corecontacts.setAge(41);
            corecontacts.setNativeTitle("Hvu8Fxzs9CK9hNKiX6ibjyFrFaA8l3M0E6YnsJ3u5PRGKzKy18");
            corecontacts.setPhoneNumber("xFkCuHgrxaxFZn7LWl3D");
            corecontacts.setEmailId("vrhyVJkhdVo8GNeVFCjBoyEIBVSiXuehKlujeKER5OUvPTQ5ak");
            corecontacts.setLastName("xtg0lMDfbQJ3ghNt4ljSF6MLnFVe1I7SEypuKTHMtKq9i97fm9");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1472195846130l));
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
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
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
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "wOGafUBhDBAD2NvVBkvNTr0rFUZRVjS2duwxD03BQVmb4SIc3wTauqn0VJHYZKrxXLY0OT3o2Duibp68QEayaz6OOLRUYyUd5UDZ6heCTDLzvE4a16bQ8UyhoHlNkRhmA7A7SXGHQWcKYNi5U85A4C3Eyx3tro4R6xmVxduO9be5OLKKXCqKUKKOp0WCS5FXtdyn1xCxrW4vnoqZEd4qfj2qp1EXpoymvZn3UQCP1tvIus6lGregu3rHbk58bBdoY"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "Asc4EKcXJ7pZ40fCIdfLERryV0bPFIWYxxya58wqkD3Y8A0pd0L20wR4BO0hSl8En7DxXUHsS3XUO5fcXeModALZU67rUQxIKBLLx201fk29J8AxUCMfkdn68RSZKGyvFukcVWvyvMu3tBgo1bxuFBFuZlaMa9MmrGoH9M3MKsuoQXixRPBXWW3ZRBFPGqcpkOikEsTWAP6yt4LjhUPgoQ8vAdJSNzazHs6lAsksZ04ngHqRcxbCOi655S65RlNMR"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "dCQFtogduIH1i1wa2hLKrAmJxQCYosikwkVghO4Mqr5qM5GPYeQ5phZUcacRWDxrC8wJjNeI7LCC91RY5jiYpJefEcy8h5J10plaXIO06xv7jQtsq6Vkj7J6rEuNoWYgcheuCc3oaqX7IDMIJurHXq0mfuI5B0Tv0UHReursCp73WNcRLb6J4VDKJyp8zupbJUaHK5lR3DHlZoGnFhnM41oSScsOQ1QoRDp0VN06HmopTPUxMtFWM3IdvrR4GfSvk"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "LfdbR4bfHQKEX7lPZmSM4NYnlI8wwNZRXq2yG8VVuNgafEoPQU70aWmz4ZYiEblwj"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "ody2sTG32NHoPZf1bjYS00MaHwTA6nM3KXnmg8eZdo50PQmzS6yXfcdpRNxpfunOj4ZOKzEib9TKRJ7D8oWJDyBGtf45v5Xb7rCqweo1LAd3voJrXdOxZnj5rkpcOuHAOsRDYzClYBeyt7vKHCtDEMWCSPUjseHkAVlkz5XArtgimmTytdZsruy5O6QuBFNzSIWbwZOWBJIsmbUf2et4Ka8x7XnU1O8PJpfwu24kAg8myumVkE3tPuEKrlCcSqbKB"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "NzL7nw0cYJZTxyor5tK1VfBMIDUBmRvDJwx9mrkvcQ2l6EdhsM5VbBhCwm1bxVgNhjdleamHl7o1lxmNbugzfXCzKJkoTzHRcCrJ2Pq85WKDE9biEQ03gAwIGAsy1gUkJ5zOAaCK0v8mTMxyZLUVmh2AmHw8m6LyRch3VEVc7UzhGNpryl47XAGY8OX7yk9TyzMIqr1cVHAGthpGUGw1T7s1LbHijusoCwHABIuCk6e77TcgyWLe2QO2f8TJqtOqX"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "7ezMTydwwkD8UABSb7FH3UvmuWoxlFvCu9DOPPjzq4xULrje3wQffgafoBSMPtbpVJfNCuyZoZzavReOmmvjr7mW8XaL9oVyy46T33dWvOmHyx7cbcNlMPYzfwy9gxuUHBBwVunuM29AqPZ9ZeXAzOsfnf4VXCCpCN0cTfXGtU0hcHcjCr8LVkK2PJ5q5p9Cdkrafqkk9I9725iH7ki1CpPEhzgmhE3fIncotdKbIprN7Z07xSdkBObeuSXegnf9E"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 212));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "Z5IL9x9cWldTqKZOVEGuD6Mo1lDJh8DDZS99WHKRGZsJcKVXSEx0uJ6D258dycqaqaEwgUm5a339qXBc73CDT3JHSd5Vcv8adpRLhXTqHoN4Xk3nIsd46aVi4qEAxdtzMUxn7kiChF8zYJxFRrKYl24sZeV7nduiEQv5xpkLRVaDqaAkyi86l0BjKFSg4yPMnFuEJT3NgSc5tWv1uPm9ecsdBdMmLwMndoKWtLp63i0wwPeqlkyeUDfAjsSBaeOfo"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "3027fIe7m257OzBedTKm3"));
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
