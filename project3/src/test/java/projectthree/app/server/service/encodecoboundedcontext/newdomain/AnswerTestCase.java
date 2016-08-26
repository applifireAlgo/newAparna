package projectthree.app.server.service.encodecoboundedcontext.newdomain;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.encodecoboundedcontext.newdomain.AnswerRepository;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Answer;
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
public class AnswerTestCase extends EntityTestCriteria {

    /**
     * AnswerRepository Variable
     */
    @Autowired
    private AnswerRepository<Answer> answerRepository;

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

    private Answer createAnswer(Boolean isSave) throws Exception {
        Answer answer = new Answer();
        answer.setAnsName("NTwemBJ1VxNsC45vdb1HWhOURamrCOSwdKRuNpdrncZ8RjXWIO");
        answer.setAnsWei(2147483647);
        answer.setEntityValidator(entityValidator);
        return answer;
    }

    @Test
    public void test1Save() {
        try {
            Answer answer = createAnswer(true);
            answer.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            answer.isValid();
            answerRepository.save(answer);
            map.put("AnswerPrimaryKey", answer._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AnswerPrimaryKey"));
            Answer answer = answerRepository.findById((java.lang.String) map.get("AnswerPrimaryKey"));
            answer.setAnsName("4bTvhFCTnwjvGtS7GLQFkfWvZCr1q5NgVtxW4pDRd2mIBI1kkl");
            answer.setAnsWei(2147483647);
            answer.setVersionId(1);
            answer.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            answerRepository.update(answer);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AnswerPrimaryKey"));
            answerRepository.findById((java.lang.String) map.get("AnswerPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AnswerPrimaryKey"));
            answerRepository.delete((java.lang.String) map.get("AnswerPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAnswer(EntityTestCriteria contraints, Answer answer) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            answer.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            answer.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            answer.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            answerRepository.save(answer);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "ansName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "ansName", "aiVukPBhRRaPwOEjPVICikOMF8A7h4Q5wnVS6sLqijCuJAZFcuyiHqGifS0mNRiNajg955rcMAyOPCsCUeX5DOX1aoVnf1aEcGrP5m57yRB6DkYsnrg1sI9cPhv3dz8HNvEuaDPiVIzxlD2MhAosE0LEBuZMO5v9uDxUr03vxuvpZe8QOgsESngJWpxXLfJel4DDyPlcUssThm31GG40dPIitV5DGgjTIzvCzzHv56fH2n3CGqDsdeHIp3ApxAZmi"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Answer answer = createAnswer(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = answer.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(answer, null);
                        validateAnswer(contraints, answer);
                        failureCount++;
                        break;
                    case 2:
                        answer.setAnsName(contraints.getNegativeValue().toString());
                        validateAnswer(contraints, answer);
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
