package projectthree.app.server.service.organization.locationmanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.organization.locationmanagement.LanguageRepository;
import projectthree.app.shared.organization.locationmanagement.Language;
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
public class LanguageTestCase extends EntityTestCriteria {

    /**
     * LanguageRepository Variable
     */
    @Autowired
    private LanguageRepository<Language> languageRepository;

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

    private Language createLanguage(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setLanguageType("g2JnC6UpmH3xiO70isjcFqB8UlrwJQmj");
        language.setLanguageDescription("W2J9naWu6juSGT3zZvTzuQUCKlWtLxZIEQgJC2okk1RFLg64wG");
        language.setAlpha4parentid(3);
        language.setAlpha3("Ydl");
        language.setLanguage("IoYB7VXNFSffWa6qtS5GJEphhdlpd9mfG2JzyE0avSwHCEt1Vp");
        language.setLanguageIcon("LaTRh7ylqUv2x3BI39FRPd8ePcrTDcdhnyz2NgSJcBWP7ZM0J3");
        language.setAlpha4("sSgU");
        language.setAlpha2("6z");
        language.setEntityValidator(entityValidator);
        return language;
    }

    @Test
    public void test1Save() {
        try {
            Language language = createLanguage(true);
            language.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            language.isValid();
            languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            Language language = languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
            language.setLanguageType("R9pR6kEzlzwlmHwTbeQghVn52X9Rd1MU");
            language.setLanguageDescription("64gyD7jaTsfCqMvgzeNDWHolfGCrBtHQcJzUHjuWDpBIN31GaH");
            language.setAlpha4parentid(1);
            language.setAlpha3("m4c");
            language.setLanguage("Vs3DGFrIDosnNk5rXwXMxSGZZ7wS5VnyNhnM5zFPRcDDndx0cK");
            language.setVersionId(1);
            language.setLanguageIcon("0UyH0CjqaD5gIHmJ7PNFsWCfCL5bkGCmxCU7bhvwbhdSNcAWgo");
            language.setAlpha4("A2tg");
            language.setAlpha2("9t");
            language.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            languageRepository.update(language);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.delete((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLanguage(EntityTestCriteria contraints, Language language) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            language.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            language.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            language.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            languageRepository.save(language);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "language", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "language", "mXHKjqwzeFOUUGVoQZ1HumcgGNyUe8wRYqAMVwKYKYKOpI2HqL8VEzfMFWKt0s96uX3fuGScJ8dLP2F5Q5nhBEvW2qqTP7H7vv66xgenU3L3gp0lhc4roXziypYpdgrMxcNBK0PTzQQHYGqYSYBTJAiVru0U2kLeppN22BSoJLKu11KImgZab3uIjmPRcEOmnQCI5NCCeqZEpbIQODhgIYfzriqkRgmW86GHqJoeZgqssnpBGWdTTmeHXXP7xrv5X"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "languageType", "6qUH26b7X2Tc6rl5OVZbLIp3H5WqXhw56"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "languageDescription", "nleM33zYgw9dwRn7oB6Uxkqh4rQCuwxZL8H2lFKUSxTAMIh1gRUWvOWXafhGSpiS0JugZrZTBeGoLA3fpvvwPE0BwdDVnwfLXst08Snvfdwatg4gQmLlIxumaRKUzMJkI4wTqhHL8HBXZPIFo0uR0nj8rRABWV5715zaUZODdCk2osaw3QI147LI8NIRHgIgRGSzehrnnAfbG8KaRkAut4H7YBwfGOBKZjTBsPHxe2zdllDTaIBuxeOBsgnUylKBH"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "languageIcon", "ugnSpwv2GhtFZYBdsxsXiLmTQgR0Cq5hGAwm039eKzq8vgADGjajVJRxZLmUgT46BXTQuI5ytEj6YKgCtD3z7fM0WfMo74Ne5KxtXSspZxXoxvnQAibtFkN87dG98zmAa"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "alpha2", "tEA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "alpha3", "bfEj"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "alpha4", "oOg8T"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "alpha4parentid", 14));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Language language = createLanguage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = language.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(language, null);
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 2:
                        language.setLanguage(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 3:
                        language.setLanguageType(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 4:
                        language.setLanguageDescription(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 5:
                        language.setLanguageIcon(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 6:
                        language.setAlpha2(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 7:
                        language.setAlpha3(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 8:
                        language.setAlpha4(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 9:
                        language.setAlpha4parentid(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLanguage(contraints, language);
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
