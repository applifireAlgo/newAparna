package projectthree.app.server.service.appbasicsetup.aaa;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.aaa.PublicApiRepository;
import projectthree.app.shared.appbasicsetup.aaa.PublicApi;
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
public class PublicApiTestCase extends EntityTestCriteria {

    /**
     * PublicApiRepository Variable
     */
    @Autowired
    private PublicApiRepository<PublicApi> publicapiRepository;

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

    private PublicApi createPublicApi(Boolean isSave) throws Exception {
        PublicApi publicapi = new PublicApi();
        publicapi.setApiData("saFayLuomT6hI8s2oahuOBxg2I0y0uV9sgFdwUJhNNyf8hbUsL");
        publicapi.setSchedulerDetails("urNobpo3UPUPqyPWz6w7LZyXpzGnuYz0mnAo14Dncc5vInz5NJ");
        publicapi.setEntityValidator(entityValidator);
        return publicapi;
    }

    @Test
    public void test1Save() {
        try {
            PublicApi publicapi = createPublicApi(true);
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            publicapi.isValid();
            publicapiRepository.save(publicapi);
            map.put("PublicApiPrimaryKey", publicapi._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            PublicApi publicapi = publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
            publicapi.setVersionId(1);
            publicapi.setApiData("iGjEnVlMda3k2vnz1mQFu2j6Dne6eP5KuuU8p9Kry7tcPh9jpq");
            publicapi.setSchedulerDetails("Y4ubXGqql1RIJ4Dqbp7wvjrLZSS8ka0joiXuOys8MoT6jMMXDU");
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            publicapiRepository.update(publicapi);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.delete((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePublicApi(EntityTestCriteria contraints, PublicApi publicapi) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            publicapiRepository.save(publicapi);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "apiData", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "apiData", "NmuF4dOIgn4VFq3DRLuRiY4SUbxcc4lLzbw8CxQrlcLxPmwLXiSc0ZczLGrBzdqAm0h4YXDnMRKjmkfKFcuNvLIniBZAHe6ZZJ4aOFCo3GM84SiY7md5CxRqdoKWQAQYUkRDJSXAYbPWQzUS6bOgNOTSVAywpcmovur71UA5cdwM39Co8bPV0QjLqhIH8RzHAd5eWgZIoFzdHduCHnpMy8bEN4yxnk3Js1LdE6mv4t4ixyCcJMrVUX4RAzdhMZbEK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "schedulerDetails", "jDemX44IYOgG0bUsyVrur7OlW7G8bwY2bBolgjzZTX1wAEkuT32bfXpV51PnZ3SgPBe83dsLfkkzXUKiLxegs0y04aBYbNy2H5UMJzGPYl0gMjhSQdSBW2gTEmAvSJWPerzXflCvIu42f2v6JcpkSMBXSX1KBcw0Ok282GapJ9jfDHWv3ZtwtZxkQNC50l4nLN2ViOki60BGJn5DukAwUfIUnOlLmnqPscGPeS40ndFwRagykEluq6knG1aU2FeG5"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PublicApi publicapi = createPublicApi(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = publicapi.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(publicapi, null);
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 2:
                        publicapi.setApiData(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 3:
                        publicapi.setSchedulerDetails(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
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
