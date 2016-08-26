package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelName("A263Sj73BMEPw4a9oYxRHl19MtzsQCQkuCezJ4uaO2ulBoIuNE");
        useraccesslevel.setLevelIcon("HOSZ0Y2g8aiPIjPE5XCR7PkPquMeln6kzFdKqjyBZ0LQ6sZN92");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelHelp("asw02JAK6waGkBvU7vI80tlwbwKMJigrEYmhCJ5TNUn4JCqszg");
        useraccesslevel.setLevelDescription("YKzF0GajWtTKPSZNXBdCVz0inaNLGrZc6o2MAi6GOGVXq2Y02y");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setVersionId(1);
            useraccesslevel.setLevelName("QyiFLErdYSBcKTatpKlJaaxWxuvtVWuMVZEqA1zDmLV1tnA8YS");
            useraccesslevel.setLevelIcon("PJon6aSXCFtJkAK2c0jHE3grGFdSOMmabjbg1fuwYxB6fMwbjM");
            useraccesslevel.setUserAccessLevel(4955);
            useraccesslevel.setLevelHelp("2BZ3ypVmBGYoak2za4DunN5doj7nTgum6gyMYZ6ifH67SBBgUN");
            useraccesslevel.setLevelDescription("V4td9SDC3UdSRulwOU6NENscvSv3MIrZ99yBFcnkTeE4xLAIgy");
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 139614));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "COZXxcn9Xd7iOfYBC7zT3i8HECDlwzJBebdiMCAnf24BDYfL56DBjr1rzatT5TbglABE2OZux96jAA3PSKk4yBUtCnf7UUwWdh5BvYTh2p044FyBzNmfbQKMjv5pBh6EIEd9wG2oFhPAeOTKM6Z6OxsPBT9J4wUr9qbGtBBqyxL9mS7WeToaQ5XJfYMbgY8az8CN3WHt5tijJRYafNR9SRVa4XebGS8MIGeurORIjPqEyyPzsV6zR192QbiHq6D7V"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "ojyxyZj4Fq3CiXkKvtvslEixpAsbvQ36JwlUMgn8BaVujEE31nhai21EhEUMHQVncPanMzLtvCc7sjKD5loydA2qL9kKh4rprmpLkYkLwfhVt77Fym766TDOHB8siq7u8WxpnaxgI5DzS0MVsKgzNVxp3IzhcxId1NFv66rkvGWAL1GfnP2cXrg91U9td4gHdNH2ePDSCXRbAn41HnwcJaqX3evTwB0GpdbF33sI3CIBLrtHwV0CR6MheKwoCC7yL"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "yyfxlYt7ks1hIU2RkOgQzidXSYUwTqGX2wBOxsxv5hz0k5lIiRRTu3ysdEBK24wPQzCghg2EOzF5Ul3qCgSnrIl2flvFPOJ4ycCasdlkjX7An2qagUQdc7uI2UWuDy24GBLV3zVTcE1KRqUVu6O0LVXQ8saHpQzp01r7c4wa3OfSt5RAJ8slUSyWFMajBnKwd1OfMyMkQglW0AkyE1y6gVNCRPtpdDSXEHIogB62p1G7Tnt7jUI2f4ws6fRlilgAJ"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
