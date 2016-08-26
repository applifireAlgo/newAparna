package projectthree.app.server.service.appbasicsetup.usermanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
        useraccessdomain.setDomainDescription("ydfFThhsxFvh8oxxvblBaVVHOkDhu5BztC2rtTFbgkYepvMKta");
        useraccessdomain.setDomainName("Mfi5jsUdouoEVIoydVPI0T9WunZzkbcz50ADffHJdsmRN9BT0Z");
        useraccessdomain.setDomainIcon("dsXjkaPFSCrq96a67F5ZtBivOeJaSr1uagAS6ks3mIZZc8cVY9");
        useraccessdomain.setDomainHelp("jWzOgpz4qzsOaTFljgZWg1GEzCmgSEFcb15aTfTXaCX40dDvWm");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
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
            useraccessdomain.setDomainDescription("y6uVBYDFkm9kG2V4ejLjP1nR7NH11mRxaYWkLLMa03Bm24EhKU");
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("6RaBSc7mvAZzP39AVSHCGNlBj8RWi2dkOaUqjLecJryjdkAtun");
            useraccessdomain.setDomainIcon("BVR0aohPhSDpHzTCDMOdsiuJ0yBkQ2pL4wOsQesnuv6KXeluj7");
            useraccessdomain.setDomainHelp("49rBIbXCtXQxPqk7f2gZNVCfs3VB99sklDsO6GdpzxPCj1sbAu");
            useraccessdomain.setUserAccessDomain(66943);
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 129708));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "vdqVJXmWQLwQLfHClahPjAHeBXrexyZNgdzw80W5V3bhCITs8voNd6E5N8O3PF2PXkGbT2lnNNz25Mz4kKwJJ5CLaHb1OXQzbZ9JqbesgQciUEZGvcPLT90u9jwk3hOVLICFJ5x1L0KGI6dR07ZMH3OrVaqRBU8CWVbGiPUi1LvjVAkztBjgiTs6LJSqSt4Wbl8zeI1O9eFgNtAodsiFoLV6OXAUBU8PO359p2atScBmUCPWpCXUnIwfqgDxKBy5w"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "tMTRRyiliKoMVundHh757gAU5MiOBpSuLQteDxO5eAM8oRjGUYIGQCnXbCqP77Q3kiSGjadiOI0hvyYhX9ggNVM7WmkgR472FeAJA5y1T2JXN4Ef1FE1RoxJGqRszl1b6J20kEeBsztXXjRVJ0TyH4QAUOkhv6q9ubihceN468bQVvJq9WeHv8L9IrQrWkWHGvJq47PjrmnuDRsvs8U6bS4DdWoxnPk6cezJktyE1weYDTyGmoeyAgHpoepDeJmJe"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "byzCtpt46YWi06mso5lntA6FFTAUgpujqclBJkdTmOhymKHAN3wrABqVFhlka3dMhX3y35URZHu1pJHblexY7VZDhztirhzNoLc7kBlbKdTU99f6HwTn4ICk6tbxqm25ioC1aIQooATAtbFgHHQURSCi8Ednh58wWud2GFeAIlbvVJx5yyyvJwbviGSekc42PxhuOLzmpgH0LZUs6Vf4KYXlbRSgHMSS22pUC1wF8bZsr3JkGdkOxJd5ibXjh7hD3"));
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
