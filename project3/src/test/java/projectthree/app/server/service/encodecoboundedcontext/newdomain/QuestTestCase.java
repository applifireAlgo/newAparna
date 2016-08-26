package projectthree.app.server.service.encodecoboundedcontext.newdomain;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.encodecoboundedcontext.newdomain.QuestRepository;
import projectthree.app.shared.encodecoboundedcontext.newdomain.Quest;
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
import projectthree.app.shared.encodecoboundedcontext.newdomain.Answer;
import projectthree.app.server.repository.encodecoboundedcontext.newdomain.AnswerRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class QuestTestCase extends EntityTestCriteria {

    /**
     * QuestRepository Variable
     */
    @Autowired
    private QuestRepository<Quest> questRepository;

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

    private Quest createQuest(Boolean isSave) throws Exception {
        Quest quest = new Quest();
        quest.setQueName("lfBU8KTPczA6z1yQMAQMW8bj9s0x2LrFep0EkdyXGAu6fWn94d");
        java.util.List<Answer> listOfAnswer = new java.util.ArrayList<Answer>();
        Answer answer = new Answer();
        answer.setAnsName("NRauxa1iOhmFxrQh6JzUNd7NdN20esgQS5cgJ0mHf3rL6JGHu5");
        answer.setAnsWei(2147483647);
        listOfAnswer.add(answer);
        quest.addAllAnswer(listOfAnswer);
        quest.setEntityValidator(entityValidator);
        return quest;
    }

    @Test
    public void test1Save() {
        try {
            Quest quest = createQuest(true);
            quest.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            quest.isValid();
            questRepository.save(quest);
            map.put("QuestPrimaryKey", quest._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AnswerRepository<Answer> answerRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("QuestPrimaryKey"));
            Quest quest = questRepository.findById((java.lang.String) map.get("QuestPrimaryKey"));
            quest.setQueName("DW971E3sV6Olpnfya5yO9wLrbK9rgBVSmx0ep3QdYiF2Omzqe0");
            quest.setVersionId(1);
            quest.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            questRepository.update(quest);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("QuestPrimaryKey"));
            questRepository.findById((java.lang.String) map.get("QuestPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("QuestPrimaryKey"));
            questRepository.delete((java.lang.String) map.get("QuestPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateQuest(EntityTestCriteria contraints, Quest quest) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            quest.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            quest.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            quest.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            questRepository.save(quest);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "queName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "queName", "JTTiFq5QnlD1BSvONDilE4OLtFPSq0OSz0xR6rXb0CceedFWl5TuAjzW6ap72Iy9w1q0MIYiiiUYG2q2KHyoctoWmwwNM4tu4YUY7nCNdJOFqcd9u1R9YIBodsZoxDLQS02haK2hZyJBh1DDSTBdBkFmMDeyxVpwQY4SLNsfw8i49doPvgzvUynPMr0l02xb3T7gGPrTXCk5AGfoPowz16920vF48iMLWYlz8teca9OaZ02F5N1NKlXAKCuvc1GVx"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Quest quest = createQuest(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = quest.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(quest, null);
                        validateQuest(contraints, quest);
                        failureCount++;
                        break;
                    case 2:
                        quest.setQueName(contraints.getNegativeValue().toString());
                        validateQuest(contraints, quest);
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
