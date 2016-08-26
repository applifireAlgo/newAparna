package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.PasswordPolicyRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
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
public class PasswordPolicyTestCase extends EntityTestCriteria {

    /**
     * PasswordPolicyRepository Variable
     */
    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordpolicyRepository;

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

    private PasswordPolicy createPasswordPolicy(Boolean isSave) throws Exception {
        PasswordPolicy passwordpolicy = new PasswordPolicy();
        passwordpolicy.setMinSmallLetters(5);
        passwordpolicy.setMinPwdLength(3);
        passwordpolicy.setMinSpecialLetters(4);
        passwordpolicy.setPolicyName("4I7bWd7r39HqWjHUxAcpdWgTF2VEYrJ4qqFz5qM5qW6QtGIxU6");
        passwordpolicy.setPolicyDescription("qs21BaL7rlBCqSCC6PKSQsq7DPSCykxZTKmnrjEGfgUnXDvxFu");
        passwordpolicy.setMinNumericValues(9);
        passwordpolicy.setAllowedSpecialLetters("2x7sDxfs0KMPWp2FLNgMJ149JYCz4PBKwCMKrYS6kEmeKKr7cN");
        passwordpolicy.setMaxPwdLength(12);
        passwordpolicy.setMinCapitalLetters(5);
        passwordpolicy.setEntityValidator(entityValidator);
        return passwordpolicy;
    }

    @Test
    public void test1Save() {
        try {
            PasswordPolicy passwordpolicy = createPasswordPolicy(true);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordpolicy.isValid();
            passwordpolicyRepository.save(passwordpolicy);
            map.put("PasswordPolicyPrimaryKey", passwordpolicy._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            PasswordPolicy passwordpolicy = passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
            passwordpolicy.setMinSmallLetters(9);
            passwordpolicy.setMinPwdLength(2);
            passwordpolicy.setMinSpecialLetters(7);
            passwordpolicy.setPolicyName("zpk6qq7pGvyJIxOux9t9RRh1ecboTyirovpxGh3TtvPpIBdOxl");
            passwordpolicy.setPolicyDescription("kbd80JmGLEJINY8gKmqWA3BA1Elkx6RqPgr2bxkYUGHFJnorLZ");
            passwordpolicy.setVersionId(1);
            passwordpolicy.setMinNumericValues(9);
            passwordpolicy.setAllowedSpecialLetters("QBOP4y4YW215J5Oq57EHeWcFcVBkfBtYEYeJgnhvSm6KBXcio1");
            passwordpolicy.setMaxPwdLength(25);
            passwordpolicy.setMinCapitalLetters(10);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordpolicyRepository.update(passwordpolicy);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.delete((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordPolicy(EntityTestCriteria contraints, PasswordPolicy passwordpolicy) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordpolicyRepository.save(passwordpolicy);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "policyName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "policyName", "KustCBailzErwpQpruCOn5f5fQYyIlEYQ6PfGmDLd7NwRKRyprESxTrXkXsyWpZvdm5ojlM6hapLYtnS9VQ91Xwuezh42mzyIvEt38Q4pmcBzl1h8a25LWDja5HSY4tTxmgZkkT43EIvZ8cKd1aR5QZjOiwqCOdyQObMxyCkb432iHP4kI5FvsArXxgpxEX06q4swM0E1baOSUnxZwv9oSEFsbtQWVmjT3ZUpZADrBD0FiYvzP8wLaQvjdIJfDtyz"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "policyDescription", "Aks8MtcF5Bb43RBCGwrzcCKzWho17gdkMXFTlijNrZkZsF0OhFc4GnXFkEw59lhxMNz9Kd7qccIcunaFKC1Sa8M9xQBqFtJzVuutTTfgbxwchIAcQVV3IPCaKY6UziZBPXdX8LTJ1kzNHw7iEwEAMnZFXwnv3ogRtV3jJ0LKvqifEGNsnp2SwOm3BbGH5KII7AQjuHK6OTeRkFym4Qum3QimShe9DwY3XYm3eiRFtFxfD8dhNJdk2FJdXW7wh5MIw"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "maxPwdLength", 63));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "minPwdLength", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "minPwdLength", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "minCapitalLetters", 18));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "minSmallLetters", 18));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "minNumericValues", 21));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "minSpecialLetters", 15));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "allowedSpecialLetters", "oEO18SqlU2ltih2uoLA7tt981sldzAEsa4Lc8OY1p7oqc8T6OoyTN5sqAmkZt4mfk39JzWcBaoIAmd5c7VReLqNQdzkHcJOuEbkjE2RMxAZcLulLpIPRosD3ENEDgNLiQkKkQsTcwOl1EN75fqA0HG2c9kuO0I8MIZXL9hOaPwlbYYiBNiHQ9SboQtSuZK9ZJz6EmcRqrWF3Sw1nbYmhRVFELnom2UVK72wsKJZz2Rxzq2T6BGnzxS2JBAoTUf3Mo"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PasswordPolicy passwordpolicy = createPasswordPolicy(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordpolicy.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 2:
                        passwordpolicy.setPolicyName(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 3:
                        passwordpolicy.setPolicyDescription(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 4:
                        passwordpolicy.setMaxPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 6:
                        passwordpolicy.setMinPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 7:
                        passwordpolicy.setMinCapitalLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 8:
                        passwordpolicy.setMinSmallLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 9:
                        passwordpolicy.setMinNumericValues(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 10:
                        passwordpolicy.setMinSpecialLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 11:
                        passwordpolicy.setAllowedSpecialLetters(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
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
