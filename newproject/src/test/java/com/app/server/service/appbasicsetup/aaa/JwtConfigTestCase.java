package com.app.server.service.appbasicsetup.aaa;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.aaa.JwtConfigRepository;
import com.app.shared.appbasicsetup.aaa.JwtConfig;
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
public class JwtConfigTestCase extends EntityTestCriteria {

    /**
     * JwtConfigRepository Variable
     */
    @Autowired
    private JwtConfigRepository<JwtConfig> jwtconfigRepository;

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

    private JwtConfig createJwtConfig(Boolean isSave) throws Exception {
        JwtConfig jwtconfig = new JwtConfig();
        jwtconfig.setJwtAlgorithm("o0KAkZxtXkf6dDMRFRCJNjspfk3PiZjKuIKIPYnwXGeiaZ2jAp");
        jwtconfig.setExpiration(new java.sql.Timestamp(1472208993047l));
        jwtconfig.setTokenKey("OhfLFE34U9clSTiePBWhXW8uHkBGGViyfe0lQrajTD4OvaZ3x3");
        jwtconfig.setEntityValidator(entityValidator);
        return jwtconfig;
    }

    @Test
    public void test1Save() {
        try {
            JwtConfig jwtconfig = createJwtConfig(true);
            jwtconfig.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            jwtconfig.isValid();
            jwtconfigRepository.save(jwtconfig);
            map.put("JwtConfigPrimaryKey", jwtconfig._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            JwtConfig jwtconfig = jwtconfigRepository.findById((java.lang.String) map.get("JwtConfigPrimaryKey"));
            jwtconfig.setJwtAlgorithm("2qyz7ob3Ko1XQ0ThyEn1Svbxc5EAyhQfZUGuVwqN4ycgZ93ci6");
            jwtconfig.setVersionId(1);
            jwtconfig.setExpiration(new java.sql.Timestamp(1472208993075l));
            jwtconfig.setTokenKey("r6xY5M1HUnjgxfAmh6AeATTgLGSJ99F4XfeCOUHWCKt6O7pNY9");
            jwtconfig.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            jwtconfigRepository.update(jwtconfig);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            jwtconfigRepository.findById((java.lang.String) map.get("JwtConfigPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("JwtConfigPrimaryKey"));
            jwtconfigRepository.delete((java.lang.String) map.get("JwtConfigPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateJwtConfig(EntityTestCriteria contraints, JwtConfig jwtconfig) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            jwtconfig.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            jwtconfigRepository.save(jwtconfig);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "jwtAlgorithm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "jwtAlgorithm", "nAyUaMz6WQnFHswPuBt9lRcEY7r6K0HWABAezefBMEuQnRilKqBMXd2HD3GUYch8YRh4k31grnXqbZX1DJn1XzBUkD3yr3JynxV3ZRpo8SKaviFQF0SVPmWkATi0kuzyKsKMTJMDMpRI8ZLs41qOltt771oAqGcLojD7zutokgVOOqiA3CdyN4vI5anV122Pm0ea9zYKCFWXAWzP4iA1fQLYxx5sy72r1XakhUNN6YNTsZDTPrfX1Uvpsq7xivWSo"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "expiration", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "tokenKey", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "tokenKey", "zCPw8j77eQWc90md3gRjzfM5GjenzzQoJxLPXtbXFfukvsQa2GEZ2kOF3FLDpub4828mNAP9JDHaNL3RUTmpvvcZt5o8rZFZF4eABqLOovmwoCZsUHCETQLakFXUljoiqdXe659AZhG0UCaFm9ZEnYjaRl4uXkgg6V9aGQHuClFgmAGjk0mG0Waludc1Im5NVshlC2z6Cgrbng9uwbJApAFGTRbvowRW3geLfy3PCQiPVwpoiR8lToBRoLYrWxGJt"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                JwtConfig jwtconfig = createJwtConfig(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = jwtconfig.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 2:
                        jwtconfig.setJwtAlgorithm(contraints.getNegativeValue().toString());
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(jwtconfig, null);
                        validateJwtConfig(contraints, jwtconfig);
                        failureCount++;
                        break;
                    case 5:
                        jwtconfig.setTokenKey(contraints.getNegativeValue().toString());
                        validateJwtConfig(contraints, jwtconfig);
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
