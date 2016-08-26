package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.UserRoleBridgeRepository;
import projectthree.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
import projectthree.app.shared.appbasicsetup.userrolemanagement.Roles;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserRoleBridgeTestCase extends EntityTestCriteria {

    /**
     * UserRoleBridgeRepository Variable
     */
    @Autowired
    private UserRoleBridgeRepository<UserRoleBridge> userrolebridgeRepository;

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

    private UserRoleBridge createUserRoleBridge(Boolean isSave) throws Exception {
        User user = new User();
        user.setAllowMultipleLogin(1);
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelIcon("NX7ueMo4mxrPCRJuNksqFBS469gOFTAGghLxDgTKGCSL2pugFY");
        useraccesslevel.setLevelHelp("CNH0h0XKEsv1NXTRte7CmvX0PNJ9NOavEjtNP6gwR1TQLJIzUu");
        useraccesslevel.setLevelName("Ml9MmfYoFRCEDllLpcX8wpSdX3uPqy8Fu256BrFJm0NJS00Vu2");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelDescription("jDcnbBLPjkdy7JpKQLtMgWAqWM7RsLd85Ilv6Va7pEIOOHHu2U");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainDescription("k7qiRVkX5ZYDBVAur35iBoLGx7NwiDa9xjWA5jZuYvwSemMufZ");
        useraccessdomain.setDomainName("G1FAcTOgZSIArFKUoJcLyPMGGcpibb4CcjMnqvF1sslKE1GqBS");
        useraccessdomain.setDomainIcon("7uAamOrYjwRIOAPULIJY5UGThim1ZgjEzENNvxBqMzZhzeCoZA");
        useraccessdomain.setDomainHelp("YnzpA2qP6ijAZ5sU6KPYAzWVRbTScvWIq77PwVUXKqDgFwQ73p");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        user.setAllowMultipleLogin(1);
        user.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setPasswordAlgo("qPAMgulQLqduahgFoLVtqEbO47nfkWx6Rg5eT7e7m93CQEFDqC");
        user.setIsLocked(1);
        user.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        user.setUserAccessCode(40631);
        user.setChangePasswordNextLogin(1);
        user.setSessionTimeout(810);
        user.setGenTempOneTimePassword(1);
        user.setIsDeleted(1);
        user.setMultiFactorAuthEnabled(1);
        user.setLastPasswordChangeDate(new java.sql.Timestamp(1472195861754l));
        user.setPasswordExpiryDate(new java.sql.Timestamp(1472195861754l));
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionDetails("s2Wiv5AZtz");
        question.setQuestionIcon("wlGHAsVQUxtWaN9oXloSbab6q071NsmchgHZhFilbdF4dg5AxO");
        question.setLevelid(3);
        question.setQuestion("qmmcEeUAXxvyKTWRqMHdAeO4jnUQKIFYiQrrZ30JqCHMStHW2U");
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUser(user);
        passrecovery.setAnswer("UZ97TcRCZe5MuSYLb5PVtJfr01deRvzKFG8mJLCq8JWPyynR8q");
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfPassRecovery.add(passrecovery);
        user.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setOneTimePassword("VhW9ICXoGwHOYWhx4zBLOPONMcFMPgHM");
        userdata.setOneTimePasswordExpiry(4);
        userdata.setOneTimePassword("Rz49qvYxnzaWE0F7CoTmhVdNKY1ICru8");
        userdata.setOneTimePasswordExpiry(1);
        userdata.setUser(user);
        userdata.setPassword("9JCxm4KMpKvTNzzH4hOyVK0NrHAmhUgretKLDLycatgzI8lYAZ");
        userdata.setLast5Passwords("5sxngVIXksGWGPuMA0BQlXR0tk61aeygcp2aeYC0idvX9f18H3");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1472195861901l));
        user.setUserData(userdata);
        User UserTest = new User();
        if (isSave) {
            UserTest = userRepository.save(user);
            map.put("UserPrimaryKey", user._getPrimarykey());
        }
        Roles roles = new Roles();
        roles.setRoleHelp("Pv5VNHgJ1noVJcrbm85WX5Fp4joWlDI3KlzcGtTqgvEk4ngiYw");
        roles.setRoleName("itPOCPGyMwK6HFUNx647M0MLQgH3qKMIYsap2E9UE0CzH6JCFh");
        roles.setRoleIcon("mG7Z1S91aie0L3jhJN1Gd7VlJIxBmuaKLEuETtRkVO0PnDRBzQ");
        roles.setRoleDescription("NWCnDZLSj8sCdpG99o3kJ9vCHD8HJzQy2VFeHgYtuS1D7k8d87");
        Roles RolesTest = new Roles();
        if (isSave) {
            RolesTest = rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        }
        UserRoleBridge userrolebridge = new UserRoleBridge();
        userrolebridge.setUserId((java.lang.String) UserTest._getPrimarykey()); /* ******Adding refrenced table data */
        userrolebridge.setRoleId((java.lang.String) RolesTest._getPrimarykey());
        userrolebridge.setEntityValidator(entityValidator);
        return userrolebridge;
    }

    @Test
    public void test1Save() {
        try {
            UserRoleBridge userrolebridge = createUserRoleBridge(true);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userrolebridge.isValid();
            userrolebridgeRepository.save(userrolebridge);
            map.put("UserRoleBridgePrimaryKey", userrolebridge._getPrimarykey());
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
    private RolesRepository<Roles> rolesRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            UserRoleBridge userrolebridge = userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
            userrolebridge.setVersionId(1);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            userrolebridgeRepository.update(userrolebridge);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<UserRoleBridge> listofuserId = userrolebridgeRepository.findByUserId((java.lang.String) map.get("UserPrimaryKey"));
            if (listofuserId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByroleId() {
        try {
            java.util.List<UserRoleBridge> listofroleId = userrolebridgeRepository.findByRoleId((java.lang.String) map.get("RolesPrimaryKey"));
            if (listofroleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.delete((java.lang.String) map.get("UserRoleBridgePrimaryKey")); /* Deleting refrenced data */
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            userRepository.delete((java.lang.String) map.get("UserPrimaryKey")); /* Deleting refrenced data */
            questionRepository.delete((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.delete((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.delete((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserRoleBridge(EntityTestCriteria contraints, UserRoleBridge userrolebridge) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userrolebridgeRepository.save(userrolebridge);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}
