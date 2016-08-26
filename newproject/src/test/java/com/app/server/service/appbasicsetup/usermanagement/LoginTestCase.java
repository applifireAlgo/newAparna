package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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
import com.app.shared.appbasicsetup.usermanagement.User;
import com.app.server.repository.appbasicsetup.usermanagement.UserRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
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
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeMiddleName("9hWVoHlttpG6GgolrGG5As7sehhLpMVHYn5nRjTaQ7P5aPBmGr");
        corecontacts.setLastName("3Jt73uulhAtVad7OnEh7HfrCTZQ4fpqZghPUeD1dIB9d69LRxH");
        corecontacts.setPhoneNumber("539nqRYfiVwcEl2sXzUn");
        Gender gender = new Gender();
        gender.setGender("iLNzrqAK8Q81iOGlc3boSr7jPVZFfcOkKPIZeUUKhULvaxPRzT");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("Yvek0F1SvjHEn689vKvkpPYQLa0OExNQSMO6rcbPsDNWusXyaC");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setCities("WzR1zfuiIfc8NJSVoOherI8ewjvzJROWemsGQnQqrc0lII4mIk");
        timezone.setUtcdifference(4);
        timezone.setGmtLabel("pNaJvAqw7sWdTSuU5a2xZiuvwRaCOrgO1U2ibDU8ZgqNj2ESP6");
        timezone.setTimeZoneLabel("9u5aqs8Ufz4iq5cK9U4JzUpaft5XdjWgJsHMl1vZxTkCz4TPpL");
        timezone.setCountry("FumBk5m4d6MRetcr5eMqHR3BJMbNib8e1qA9TmNkR9E8k3x8PA");
        Language language = new Language();
        language.setAlpha3("H4u");
        language.setLanguageIcon("TD57OdzKN7lrVLG4It99lUvwuYVqWiIUR19IFe9j4quBrKAAvy");
        language.setLanguage("gRbg90dYGukp05VLAHf9ZklrgD720wsGv6PB7UbgDVAA8GoVJs");
        language.setAlpha2("4k");
        language.setLanguageType("v2kyTREwVMdKl7SSXix1Q4nitRnhow1a");
        language.setAlpha4parentid(11);
        language.setAlpha4("6kkV");
        language.setLanguageDescription("s4IN25zMy6gTfIK0fZPNwcraVVIcbV1hKdAMtmuDW1DTIkTLEi");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setNativeMiddleName("e9kZmIEwbQhE8mDKeorWfNhR7tgofK40RywpOUwy0lLV85a9Ed");
        corecontacts.setLastName("nXTnK0vu4St64X7lbemTuTr3AbDWWOl5u0U1jGtBAw5I2Y5IMf");
        corecontacts.setPhoneNumber("rwenH3DytMVa6CpmiDNR");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setMiddleName("X0v5IUVxKMdOwnoqW1osDQ1uTE7ZlcHDFF8zSdhqV99kXfUNCV");
        corecontacts.setEmailId("UpXuCMPxkRjiltE5zTLYKb1v9Kl451sYduQKvfGZ5ot0y3h348");
        corecontacts.setDateofbirth(new java.sql.Timestamp(1472208986151l));
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setAge(123);
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setNativeTitle("fyG8jMNPQD0pmptjcSsr90qKBqUQjLnaSC9iRZDZFSTgd9psT1");
        corecontacts.setFirstName("ojwAbzkcGVWwyXunpwfpxXjsBbRukaHCMN50D9u0jlgI3xeOFF");
        corecontacts.setNativeFirstName("y2FzWp1yJvLXlWxaBeAuLLTq674a98UABhx5IOwlaOm0BTvSef");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1472208986260l));
        corecontacts.setNativeLastName("zQjJBHXGCLZqWefWQ7TikOF8Y5HImTQ728WWXpMdfHMHNf85A4");
        java.util.List<CommunicationData> listOfCommunicationData = new java.util.ArrayList<CommunicationData>();
        CommunicationData communicationdata = new CommunicationData();
        CommunicationType communicationtype = new CommunicationType();
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("dAhGV66Y3uXzVuqS48eeLkp7A6ewn8qvvjBELSpYL0TFNqVhvU");
        communicationgroup.setCommGroupDescription("Ep1pbFB4U4KdxbJFQncgsNxXcdLBB1vnorbuUuLr0XLZyCOtIF");
        CommunicationGroup CommunicationGroupTest = new CommunicationGroup();
        if (isSave) {
            CommunicationGroupTest = communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        }
        communicationtype.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationtype.setCommTypeDescription("zXantw0lNSB6HdR54kava68MHQgw2W2TJkDsx4Ma37sbBMLP2U");
        communicationtype.setCommTypeName("9kqygU6QQNHH6q1hZhOlZLIE6HqvL6nebzhG7jIdOYI11kvz3Y");
        CommunicationType CommunicationTypeTest = new CommunicationType();
        if (isSave) {
            CommunicationTypeTest = communicationtypeRepository.save(communicationtype);
            map.put("CommunicationTypePrimaryKey", communicationtype._getPrimarykey());
        }
        communicationdata.setCommType((java.lang.String) CommunicationTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        communicationdata.setCommData("683hf9f5v2");
        communicationdata.setCommGroupId((java.lang.String) CommunicationGroupTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfCommunicationData.add(communicationdata);
        corecontacts.addAllCommunicationData(listOfCommunicationData);
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        address.setAddressLabel("1TutXD8bSog");
        address.setAddress1("bkEV5QssiYhmUBXrC8k4ekoAWYH7ZlWfnrsrjdf4qS9ew4viKl");
        City city = new City();
        city.setCityLongitude(10);
        city.setCityCode(2);
        city.setCityLatitude(6);
        city.setCityName("ufSwkHTNFse9TJqCsjIGx184rSDIP3ysBgWIYC41YFiFWd9qPv");
        city.setCityDescription("jjjL0WyWiP1aWJtUEGgYsozZRv58Xmz33RtZh6G04litN70gqQ");
        city.setCityCodeChar2("WQBB9Z9FMVbzjr12xaYlyrpxqVk8Q2xB");
        Country country = new Country();
        country.setCapitalLatitude(11);
        country.setCountryCode1("wLE");
        country.setCountryCode2("gj4");
        country.setCountryName("NZwOQKHy49RtM5b8QcRAFj0hM7OOg207Q6WR5gKvhMcYJVRtg1");
        country.setCurrencyCode("Gz0");
        country.setCapital("6JcSbFl9kHEHHJg642lG4Os3EOgC3HDY");
        country.setCurrencyName("DbfdrQglfGaEQxc6S3dNAQ2AI7q1CnkZFqdVQMp4INmVMoXQ2b");
        country.setCurrencySymbol("jifCCMYK2nWxhrZcDoEy0w7Ac501ciO7");
        country.setCapitalLongitude(9);
        country.setCountryFlag("QYENWHSSIs3zcu1QusqTTAog3D8Z7gLPxIgtz4UXSzX45X10ZP");
        country.setIsoNumeric(260);
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateName("c91XvIn1LR3juNDiIGw8GzpcOK1bcSRkaaMePy8o6XKmcIEW5E");
        state.setStateCode(2);
        state.setStateCapitalLatitude(4);
        state.setStateName("1XOozK0G4pEJdDHP0g2Uj892CE7NKp7IwiX2nEijHmiYRT50hP");
        state.setStateCode(2);
        state.setStateCapitalLatitude(1);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateCodeChar2("ybRmLCFbyUUHZJBgKrWheNnevOnlvgga");
        state.setStateCapitalLongitude(11);
        state.setStateDescription("wCHh8rM3Z7hV3DI58DJzoCixQeeGDDgnqp8Yg35pB6iR6CoZM9");
        state.setStateFlag("ehyyMgauDlXCUAUcoWR87dD0LanLp2bC3nEhMBR4XrXkRhZkeM");
        state.setStateCapital("Pz4mgEDAPnv0jnjGmFyjsVWzHC2UmuNlVeUS75HIBiQ2rAn4ZU");
        state.setStateCodeChar3("GYQeB2qloWz7lArnAXiRr2FECtOdOsfr");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(9);
        city.setCityCode(2);
        city.setCityLatitude(1);
        city.setCityName("Qdx9XByEA3LE569vJNR69eCn3M8OwIr7YNqhn21aBzHdamVMVt");
        city.setCityDescription("oLSh6F5B5rhYUKujTc089KAuDrKToJdHysm1rJIbTruyGHwc4y");
        city.setCityCodeChar2("uXw6PAzVe4FLuxQxGOPXyHjcwGPMV3I0");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityFlag("r4bFPdmCDgxpxQxy3fNQRKbR4E06NXilHUofEi4cqsU3qi7zlz");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("PFaJ76DC8xy0uJ6BAy2RyoP7FeUf7IRE4Se0jiV7JtErl9SbWr");
        addresstype.setAddressTypeIcon("HY6a3N3dNXLxnu53TTUbuuTcFrPfDiKqll3ouTK0YVYsq9LtKQ");
        addresstype.setAddressType("HEvwdH3umZyKVaTqyhrjlgzDFLDb2NxdcQvH0zyDBiA3S2jIJX");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        address.setAddressLabel("TMcOM8N2tdd");
        address.setAddress1("2H01Q6D42F48tW0jU09ni1SmrQ7PLp8ARcG5fkpXqJME1BX3jl");
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress2("KckJJlMiOPsNHi9tlWsE8PWGkBkZ9TSkjqNLKiP6KsNwejtGeJ");
        address.setLatitude("owSI84VKEFyOMfWMMtfAnsenkAZCpWuljR0MLk1KkbfSffsvBH");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("ooiNBy");
        address.setLongitude("j6Qw5UKH535TDJ3R6LmAGEn38HwBoGmrXN4AjJ4zHSGEY9EAET");
        address.setAddress3("h8VNCagvzAqLfqvAy0IJ1YWiPR8xRSbrzyjhgVXlK7mN7vLjH3");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        User user = new User();
        user.setAllowMultipleLogin(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472208986641l));
        user.setIsDeleted(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("jHNzAToFOEswNJncNXbIyxuMnWZ1t2iHz0wXSpqJmRFiNJKcRl");
        useraccessdomain.setDomainName("7QjpP961uFrUvM7VuuCeSID4goL7Jzfww7QF2U5IshGghGSJ3P");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("0qMMw7EfJ0bm7diw25GqdkwdIMVTGp1UBIwzlzh05MBHhRqIvZ");
        useraccessdomain.setDomainDescription("qsnl4B5Lknd8P39hfzdNzfDWLEz6IhJu9mRq4U4xPdgll5mnPJ");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("7iPfW2EuJjaouBawsDAbsOnnEFW6tyQNVPKSUbhBbxpGK6n04z");
        useraccesslevel.setLevelIcon("jK01zV9pr7lAowN30UF0fOuiakETBXGLgarYjKZo29ptugMGQv");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("GsUtWyfknT2jMxtVazTDWsxzB7Bz3Uz9cSBndHlU2Nijysf97D");
        useraccesslevel.setLevelDescription("1tcfCKUrFWfsEiba3rzk70YmzOGLuoia3vl8fyouuFIrOesWXC");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        user.setAllowMultipleLogin(1);
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472208986663l));
        user.setIsDeleted(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472208986704l));
        user.setIsLocked(1);
        user.setPasswordAlgo("1w42GgQanYY12G30GkoRn3QqOkBF2xDYRqCJxTZC5mcQkgFTtc");
        user.setSessionTimeout(2546);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(44218);
        user.setChangePasswordNextLogin(1);
        user.setGenTempOneTimePassword(1);
        user.setMultiFactorAuthEnabled(1);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        passrecovery.setAnswer("xc1KGo271wQU64UtvMbVqqAhBpIf0hYb9VX3brb5jdSzZzs2BR");
        Question question = new Question();
        question.setQuestionDetails("JS224AtezQ");
        question.setQuestion("sgDrOO3CcaPVubd7TqYvG6OJKZpMF2UhcWnELDb692Oq9KRuFV");
        question.setQuestionIcon("hXokoznLkCyIqN9hjw7HmUQVF0LWcYxonSKskxSyH81eLwcysR");
        question.setLevelid(4);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setAnswer("nhbNizGuHrwk8R29LBb8i3UEc9gz5il0UpVCIQzPg0hJ2fNkts");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setUser(user);
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472208986926l));
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472208986952l));
        userdata.setUser(user);
        userdata.setPassword("tyOMCmF2yrH767ShqGmCJ5QxjloaWQIspedpQ6021uxli3qaC8");
        userdata.setLast5Passwords("zyiyTopZDaiT7TL5iWQ4KMnDXKGW9n3MTaWIC1gt7Xb7BUSyvJ");
        userdata.setOneTimePasswordExpiry(10);
        userdata.setOneTimePassword("aByPz3r3ry9rjVyHGAQUhFtMFZGGmNCF");
        user.setUserData(userdata);
        Login login = new Login();
        login.setLoginId("LZASJ9od4QUTpzdz0BKCYxiAB6BAr6LdbeeH5fOLE8VhjFO9CM");
        login.setFailedLoginAttempts(5);
        login.setServerAuthText("WtU22c41UpiGfr14");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        user.setUserId(null);
        login.setUser(user);
        login.setIsAuthenticated(true);
        login.setServerAuthImage("ruAfbvA5vyaQcfhT5gFzHFl5oS1uvD8B");
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
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserPrimaryKey", login.getUser()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setVersionId(1);
            login.setLoginId("jMiMtNVZz0HrKzEo8XfzgxeQpQG2zs4gUU0cHlN7wvKs81gvLD");
            login.setFailedLoginAttempts(1);
            login.setServerAuthText("cNYKpGk9FwpZKLCt");
            login.setServerAuthImage("iviTHrkcx5ekxhRIilI5JaRs6PMsyTaA");
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
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
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
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.delete((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "6LAtNA6iK1cQPIzBEj6wBcq8PLeLiNRkDnDhEYtOFrb7HrD7kKzTSRiSpB1m3kwbzHoNVifphTD5ploPC2HpDVKkwXp5SlJuU7RhpJXCEZCk0irGSd4Pw1Tt0zSObNf7c4IDX7KU8mtgyGUbOOXRCoJB5YmxWBBnxiKOj1Nwf8O3IEiRMmcOgUPc9J8SWb5YsBR98x8Dz"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "DV3XZyJDr9BQl4JAGLXDQWDxdlC9HeEzo"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "Jyx30Q1JuUZECggrV"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 16));
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
