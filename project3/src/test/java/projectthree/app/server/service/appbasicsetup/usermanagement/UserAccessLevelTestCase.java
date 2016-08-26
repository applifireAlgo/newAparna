package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
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
        useraccesslevel.setLevelIcon("lX5TwfTJPjanlrLXAxzlUiCmvpjNGxaO1MhJzzgXVebMmH45Gx");
        useraccesslevel.setLevelHelp("OQG7Y0ACrVsf2pmcEppVdcrS74x587nteehUE8ow4veEYKxrww");
        useraccesslevel.setLevelName("uFiaq5uICJU9ZX3rtabyL8l2LCgQoqrrGdBNdqihh6mlbsYaww");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("uEyS8WbSPMgPUmFFKhTgB3pWsQ610zqKia9NyANsSYyT2wLfLJ");
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
            useraccesslevel.setLevelIcon("YNxmm1GmC45juS5PgAmBJcG1x2clfVxIZ0kJysFKUYQNyzplz7");
            useraccesslevel.setLevelHelp("uoPd7DyEMAZISAfP0AvphcJ1yyq1YHCXBwx47gCR3EhthQn255");
            useraccesslevel.setLevelName("h9uLq7JVqoWF4MUsiZS7xI6mQXC9ilrhZoxf8UoDavgH9Cc3Ig");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setUserAccessLevel(14888);
            useraccesslevel.setLevelDescription("VMi3nQl9nDf3gGdgwqkUc9bfBQO0Twn63eMWjdG6oDxt0xBOxI");
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 132742));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "CvhAPs7yCXHoj0JrKV54mKxNoeGHZxqDb0sJK84Yzw6u0NN53vU47MvrP1KxT3o5KmK2mGYv0DZJS2727ByNZkVBvGQ1d4aIOkb6N4DzeamCL4pyrQT178R52gFLEnVGscGnPiCkMcTm6t7Ja0cJt3tPVn6EYwZvWoEw557CZSBtbNSnHjhG23bXZUslFOeVDcRdVs8JYr3sI0LVvYIrfdXjFKppA1EqsDMFrfo5IFQMo0e1IgjzprQ545oJSPOwc"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "WsIaazK5WjMlLvsVUPu7DnLXMmhJ9lsIiy3BCJoEpo2krw4ic7g3QUPHRxdIy4I6alKAtz5YM74a7lXryo4efEhwEKcngr53mH6GO3a0wTPpbV2W5eBgUY3jNapWLQToii0ju3SrUdxZTQrcmQufBJjTckbkw5dwGNzdAwnJaQOFga3ZvLx1x1ebDmsz5eLOJBgLPSGXd1n5hCLv41Vhas2eXEHCsEXFwZq7E7U0H0pLU8TcMxhBV8EQtrF26Ibri"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "1wpKl41ARajLTcokyVa7bjCeX6Xl7EzxHowa0C26KKi4RUrOQmVMLQyHC7hrM7LEEMrai6ycWQ4UKKTM3HNqCeaRXdFilcB7GNoODe3hS5WJjrU9K4Muyk8c3D7am8Z5wJfhGbemeHQuJgvxainFDC0sBLUOIfe3QVAVbA2VDkAN7u9XOF0ezHjPu3zpqAnGdxkhuNJlNKfQ1iGubNFMUB63FTEQ8oFkRARRPOGBNlf8C3It4Cj2KzdmVIQ1vswEp"));
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
