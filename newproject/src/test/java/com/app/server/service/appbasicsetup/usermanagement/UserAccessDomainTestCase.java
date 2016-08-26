package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainHelp("9d9duZLatiXVWXcpNIM2SZRnTATaJInXjO3kNdj4ahOooJKqS5");
        useraccessdomain.setDomainName("OLxKV4Wq9pJCrPpxtCDQnsZ82p4aNZ0wG98JHdRrO7sB5rfSG2");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainIcon("9xxvKBbTsNTjdtynl7l3q3VsGwUdF7myyLmZuYIb9tHfRSrQdj");
        useraccessdomain.setDomainDescription("MnxoBdjQUyR4z7wXVvDvMRUAIZ4yCZ1uEHVAO1Nw64Y9GLjryT");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setDomainHelp("jUgjvvadh8CXVLlxp4Vyq9SBcaPqCGSzUXZxVM5IFkkvEPnWP9");
            useraccessdomain.setDomainName("FMHilsnWBwT6dmdsFDnGdNSMO5cVoAAELGpizaAMT5wIjXANVN");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setUserAccessDomain(14137);
            useraccessdomain.setDomainIcon("fn0E8K9r2OswBoonZCl5bGMVLkl8Jc2k8gJnBji59xVQL3u3uF");
            useraccessdomain.setDomainDescription("0V67xayPr51FtznKSitfMAWhC0Kju3OtoTCb465mf98edgyy3O");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 102419));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "BRCrO5Npce8x1zzPpIeJ0m3agPp3M1Qul77xxNSPmDMY1rA4NH2YvybAuzrHwMuEM2BsxEPfWQ8P6a2X1uw18TZIG2M7weNs6QviR6gickM4yOPuFWRxrAfr89t4pyvBhLOvAAU44l7UhYmnlZTxkCEptO6eAB2tpXr110TlGWh96Y4ieFOltGAoDoVXrggMSazgxxdbXdZJxNBeiUB3S2NPsxBi9sq352Md8OmZcEmDGNm9BIXql7wc5fcEb9JlJ"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "r5Sb89JNXQYWNrJiJezHZnZoIJBUMN6yjDk2EeVcGKIquTCT7wwkj7l1cBb4zNQqoT22rCrFloA6BGSicTsMRBfwDHxKvsUlMuseKaZ4wkxxn6vTiEEyu3RrDyqtYUJPbWzIYaHUrnJwNetew42VodDAaWX1XehLkAHmEe2YAeJOwA5CcrIVuag9A8GxRq4x3K9AlfOqHdfcCiQs9lvPIa2hnFpMNvre1uYGyNNpGRVSW6VkFxOKXS0tNZT8jsUUw"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "8ZXxpBUIKwJ8msWEB4bWliChFuiDrKjZ84xvvpjXKBaKsGMzOU3Dvy4Gl8AUir4xe246AoQ2K8HhE0NAkhVs4tMvbJ6LcEagp1WCCA1KlE1ZS3YlEFSrANbR3MLomjWd5eYbAavtajUKx0PBanLfCJvbtc4HAaCDyYwSceaG02fg8hyvrUuBqL3oFrqwH4o6enqPkSh2TCnx5A5i4N0xMT56MobIqDaZdk1IR7VWx7FA0rspRvxfscZwRkcvMxoDn"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
