package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.Login;
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
import projectthree.app.shared.appbasicsetup.usermanagement.User;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.PassRecovery;
import projectthree.app.shared.appbasicsetup.usermanagement.Question;
import projectthree.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserData;
import projectthree.app.shared.organization.contactmanagement.CoreContacts;
import projectthree.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        User user = new User();
        user.setAllowMultipleLogin(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("DeSt1YizzOjgIprxvOelVOjnKe3LCdScmyr9CTzh5WqYGNBICc");
        useraccesslevel.setLevelHelp("v2gcJjnw90t3qZYodclI2G3ICvvrhDCnkkDIMjK9J0TczP3P7Q");
        useraccesslevel.setLevelName("p19LZwaM28SgyKOvpjF0scurg9N5Ta73w5UDsl3GkSgucRwxEp");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("7ouSMr3uAgCYu3KwsRnMGDLsfIcaimFI0AMDONwR8hZnBCc9TA");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("ZID7vkmJNJlbLD6puzFfUhP0P8mrOWcB3o5j4Q8DYugag0E2uG");
        useraccessdomain.setDomainName("WSGX7UGr9ESmwm75RLsecvBpc1pdfFGw2doD1MCm6lrv7lDLRp");
        useraccessdomain.setDomainIcon("Kz9LBEg1GPHoUsVbDOnPo1JnfxFv05ARUfHSRuHcRKSDleks7R");
        useraccessdomain.setDomainHelp("jNM6MZO93GQ4Y5NMBbAo1Ld3x5k44oSlg5KKPp42iOQYBbvYS3");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setAllowMultipleLogin(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("dQoMbVfL5FgqpwRBCakEZutKMJRyXu45avElvEY6uk1VqmXWHW");
        user.setIsLocked(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(59603);
        user.setChangePasswordNextLogin(1);
        user.setSessionTimeout(1693);
        user.setGenTempOneTimePassword(1);
        user.setIsDeleted(1);
        user.setMultiFactorAuthEnabled(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472195856107l));
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472195856107l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("v5bNJqsTGs");
        question.setQuestionIcon("fm3hM97rOl7f0AJ6OT05UKDRJQ82Mu3RCvAT1TG8fBJK939H6W");
        question.setLevelid(9);
        question.setQuestion("0yMAmAVr4ektN49bMA45sUBzKuNxVfgKWY9MUN7pLoB4fnWDDn");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setAnswer("V6YQaYddWKjgP2D0hL490H2r9ZC4FXEyOVdfvZaM9MPw6FENqZ");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("BAsTMPCKydIeD6IyCe2ArrQuoHvHzuIx");
        userdata.setOneTimePasswordExpiry(1);
        userdata.setOneTimePassword("TtW61LSGTwjrmWfb2c6Ii4GLaU8cebFt");
        userdata.setOneTimePasswordExpiry(4);
        userdata.setUser(user);
        userdata.setPassword("h2Kb7ANMD18kFJY66oYmIo1VJZJQ9u9l8nPscVlhZiD9Q4HSGO");
        userdata.setLast5Passwords("STAcviVtlVkIb599VQhGdz5SVYaVGvHTCRCdtgtctBb8FtVQd1");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472195856322l));
        user.setUserData(userdata);
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472195856408l));
        corecontacts.setMiddleName("2EDV521LdBXnjXnUALIdTp69n66paDYMdBoLpuBrEO14BLzZOQ");
        corecontacts.setNativeFirstName("xEBHLXWUHrg2owFfKD1J7L6slcZkxo5AVGYwlZnRDIULY0IXFh");
        Gender gender = new Gender();
        gender.setGender("zWCSUvPm5SopQ647r2PlxEtpkf9sKh37EDXGp55y3HIQY39XW6");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("MKKzU8cBKWdKckCg7Lxo3kzeiTuSqRtRrzYGqQUH1MDpiLV7St");
        timezone.setCountry("H9spGbXglvLIFEnzOEYAxCoRitalbCTa55TicR5tWRZVdoJZ3I");
        timezone.setGmtLabel("ve1SKMxndwiTzeTD70ziPpzY6yxDRvnrrdwr3mTUi8wrWz9KJ5");
        timezone.setCities("ftB6qpGvNKE47RGdCOlteUADmaPVMwj3LhHJv7sqMdeLbajoEI");
        timezone.setUtcdifference(10);
        Title title = new Title();
        title.setTitles("youK6lX8CD5AM4LLDnCkgVPkG1n51s3dXrQndfx8E183oEvLzp");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Language language = new Language();
        language.setLanguageType("Adlc6Ff7cQFTiuD6Pip7UWhy0lpSrgZZ");
        language.setLanguageDescription("oBMya1VaTZTicWITbh3ZPdOYDfv0i2yeNhWUej18AycXfFeL8n");
        language.setAlpha4parentid(10);
        language.setAlpha3("Bho");
        language.setLanguage("ZeJcXE6L6yfOdlNINcUQpojtiLRzdWvplVsSaWMcjVnSbvC26J");
        language.setLanguageIcon("dS05W2sdDpD0lysjLMstd7PUqpMLbIxKUViKkqF1S7jfMypCAw");
        language.setAlpha4("q6vm");
        language.setAlpha2("zs");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472195856431l));
        corecontacts.setMiddleName("2GYYga0OA8KNilTT3WJHp7YB7lVbNtOTaFgRYqXSSzqJ2l8dNz");
        corecontacts.setNativeFirstName("J6xfYkhdp8rA8tbUWqqoaetRSPPkyj8pCRD32JJDjx4BOTCfK0");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("KUDzsBEia8tCDBPhc1srF6GBTIWb5nEVe0mZQiRign7KWeiR3R");
        corecontacts.setNativeMiddleName("6QV82Tnj0aASMW1JtmABy9ZpnO4EItz0eaBQbLTR3tOB1aXhaE");
        corecontacts.setNativeLastName("p056vAZcBiGqV4UJDQmR6jxBTDXjZGp0l0ONz7vtpuJbolm8OW");
        corecontacts.setAge(100);
        corecontacts.setNativeTitle("skZK2RZRZmuyPQLckOpSiTkTj2tWJz4zWjNe4J6h96hQmcahog");
        corecontacts.setPhoneNumber("F5vtA6EMHt82cH6BDXhH");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setEmailId("fZongUiTW9VvZ10z7yGjCKGqX5kZhl4oTDzay9b9PlYi0AGkJr");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setLastName("SQM4omhRnmvXzBiMPuDw1QGPrBMkNHAvNVDAPGbh7ERgvrgPEj");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1472195856593l));
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        City city = new City();
        city.setCityName("VfPSCK93kPfcMAtjehiPVbn8GNjCcGwv1iOr4MrhqRzfyKy6au");
        city.setCityDescription("oYYxZBMwjOTD2BPWtisyCtDrCe1Hm1RTitPOoId99XLU4WQAJL");
        State state = new State();
        state.setStateName("Dro38w8TyIIcCT0nGjKq9itDXDp0xSfao8vAGpFzz6AiWd0tiu");
        state.setStateCode(1);
        state.setStateCapitalLatitude(5);
        Country country = new Country();
        country.setCapitalLongitude(7);
        country.setCurrencyCode("Lgj");
        country.setCapitalLatitude(9);
        country.setCountryName("S8Ee3lIwvLf3uuPmRc0YFtJCVQfvO0WIHkRrqGvPyBjSz8uzB5");
        country.setCountryCode2("6EU");
        country.setIsoNumeric(370);
        country.setCountryFlag("5whNZVCNoOStAewe5i7R0VeARSxsOIXsimvMkHnrVecCgDPXmB");
        country.setCountryCode1("3ZD");
        country.setCurrencyName("IH9rdxEcYIblPpQFCIMSDwVY5wLkr9xOWqWJFT1duFFmTUKTGt");
        country.setCapital("S8MpW8ZsOwpkOcL4bJBXydEWCMYN21rR");
        country.setCurrencySymbol("DfYcMG9O4MBz1GUbi1CWmmlFVGobPBSQ");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        state.setStateName("S4VM7ZY8poQHyXrXIlwOqPvdXZLr9kwa3TmsExxpG91NYzwFjt");
        state.setStateCode(1);
        state.setStateCapitalLatitude(7);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCapitalLongitude(10);
        state.setStateDescription("5CrKL4FIo1tndF9jNTAPJkOl8dQCZ344CHbuCgj6uT2y3ciOCp");
        state.setStateCodeChar2("LbPSliWmngJUdxAzjKCbkwtirvrH68b2");
        state.setStateCapital("m9tUgoboTUFK1qRG8KEtJgPC8F7VcS0SwJfouUBB2XIX85F2sj");
        state.setStateFlag("Cwuj1XGEiMa0NmNfR866hqe4lplWt6RoIC2PBcYBEqAIi7soke");
        state.setStateCodeChar3("oEDOJHXoHKTEi2JNtaQXcNbBH588GF0P");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityName("zCk6XlXjOjFTgLbX9zECcc6bfLJSmIoNthwxvxZWCx59ly7uZu");
        city.setCityDescription("cg1ZOysEKYXcKqQvL7p4dZ5MomzAK75DBU8e4j7KLEIMPlLo9b");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("uP5p735uClU0ucImjJdnhGiarTtelz8z8rlR7Jcra3adXotpTI");
        city.setCityLatitude(9);
        city.setCityCode(2);
        city.setCityLongitude(7);
        city.setCityCodeChar2("EHpz7HbXwzTNUozu2x0RriAcwiEvyo9b");
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("XcYasT3uJRlLDunQnNqm7WbhoFUh16NI45igifHLvHYVRZwf75");
        addresstype.setAddressTypeIcon("9szuHqYuMmZ5j4k9jo4dsPfisrzy7f5UnDmb8dOza6cyWwMwrf");
        addresstype.setAddressType("wXxQEUXqaZzTNlTUa4mOMaOU7hEKJJy884AjDMIXDvkoC97xfV");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("0UcD35hfa4rdyucBl1cwEhfz2rK4ODKLUtQSl3XdLwOUd5DYiE");
        address.setAddressLabel("JBLGXfEMZLa");
        address.setLongitude("bcEsytJgoqxCIYpJcEv9xotDEulSZZCXWvMkkTlBzRXCRs80fR");
        address.setZipcode("cU0KOa");
        address.setAddress3("G0xjbL2L5wmR03tDAiEl33s9iToZAu9sysh3LWtVtKSevSnNAT");
        address.setAddress2("d3ZJEVvFSzN62idbpf5iXbXHeRI2DwhT6sC265k57BnyCSpVu2");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setLatitude("3SvM6NE69wTp0eeVVQuLgOMI7oxS3KlJRLC8U5L31czAwejyEq");
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        communicationtype.setCommTypeName("AFIClz8uSk4GNMKRQqs0kcftyor73TcTHkBTF9R2B8PlLgpkOn");
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("EqJZIXkmjNuFlfQEghpCpSXu1ddYgrBN9rbZ5KgYDRPxU1MgKi");
        communicationgroup.setCommGroupDescription("twMxihXh9jAXilb8p1LVaxjytUMwuQqRAyeMPlupfNoblo2efH");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommTypeName("Kcx9Sj5KXAzp5tpFpTSCIJRfsGlIlQu6wRVsgDFtXcIxzsqzA5");
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("5tVWM77yWwPgQ7TfCi7zVSCArgmtmx73tdKh4ihnSJF73iotD0");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey());
        communicationdata.setCommData("OJkxcOiPcR");
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        Login login = new Login();
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        login.setFailedLoginAttempts(6);
        login.setLoginId("JxCofj48KrIMBvm66eCw7YolG5vyHVO7ev4ss7MQuhkIjytii6");
        login.setServerAuthImage("oZK7t57wEIWuYbQ4Jz3cF1quVTjMvCS7");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("2vLLWteY1OJL1HOT");
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setFailedLoginAttempts(5);
            login.setLoginId("wuNst5EiD8vRxmizQoDpS9KrlD3sQaZDzcnmL9bYzJ8D7PZae3");
            login.setServerAuthImage("ONfGMiQv4yiF13Gp6VZi4M20NpYZZVrN");
            login.setServerAuthText("9hl1CC3HQ9QfnSmv");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            communicationtypeRepository.delete((java.lang.String) map.get("CommunicationTypePrimaryKey")); /* Deleting refrenced data */
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.delete((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            cityRepository.delete((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.delete((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.delete((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            titleRepository.delete((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.delete((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            genderRepository.delete((java.lang.String) map.get("GenderPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "sKJrna9u67Gj7u0AsX1abYYmR4iuj59wkc3vYUnOCsKiEqnO4nlM2ZJEsvhadDWkQjRa4nYUzSL6IT1IEJlcEJsxvV0Uj5ay6UEWfxOSfC3fCM6fxMIxU7X06Dm320ix5AkEruWTDM5kv43VLuN2G5DhUlqmgpDF5vONOQOIn0WmHv0aEqWKRem79KK3ipXKow7fJpkzx"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "mnk3scKyaLOde0MJjV6QztljhspRoCIlJ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "LgYfWWwlMjTUvKvIn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 21));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
