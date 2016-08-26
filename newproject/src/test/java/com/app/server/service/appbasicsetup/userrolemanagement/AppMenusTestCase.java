package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1472208991096l));
        appmenus.setMenuIcon("jxcg9J2W3HhTRUWFG6K7XVOorVL6P01Lw0HbHdn4QmXvha9OJ0");
        appmenus.setUiType("nzK");
        appmenus.setMenuAction("osJV4rvYcdAHShlWf080kcQAFhlpYkZndi20YJL85KEw3cMhWl");
        appmenus.setExpiryDate(new java.sql.Timestamp(1472208991096l));
        appmenus.setMenuHead(true);
        appmenus.setMenuAccessRights(5);
        appmenus.setMenuCommands("Nkzp2JPIRNKVBwTiDnODfsRvqJ7EUMtBT346KcPPDkC3437eQs");
        appmenus.setStartDate(new java.sql.Timestamp(1472208991096l));
        appmenus.setMenuTreeId("mYdHUzDdu0URZJ10MJPVXzXy5me0lJtSs2hvnIdYwOymXTqXuk");
        appmenus.setAppType(1);
        appmenus.setAppId("ISW7kYjELNKDJ2DVKXpD6KQ1rZEW0Ro3ftiAXc1sq8ThhNOScy");
        appmenus.setRefObjectId("IFUoUonU5mBcByAIL8aJ6n5nDe70DhrSdozbSju3Ww8tvAf5xb");
        appmenus.setMenuLabel("O5KRSR3m4aNqbNp8CQIGXT4V14ulZsz5LBnAq7ROqm4KJvBEc0");
        appmenus.setMenuDisplay(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setVersionId(1);
            appmenus.setGoLiveDate(new java.sql.Timestamp(1472208991121l));
            appmenus.setMenuIcon("nbkhqL4gifvDLZy7ivpHIgg1V8gEMFY1rbTBQW3rPxKuwmVGVu");
            appmenus.setUiType("rEP");
            appmenus.setMenuAction("hjiCworedSilPUSvh8BGrqhpZOMayVs5ToTCwf9M3rPUpNqHLd");
            appmenus.setExpiryDate(new java.sql.Timestamp(1472208991124l));
            appmenus.setMenuAccessRights(2);
            appmenus.setMenuCommands("BrhtiruNSzVTQlM8ZENZILcenrTlo6ekA0mnDQHNRcT7YXyniY");
            appmenus.setStartDate(new java.sql.Timestamp(1472208991126l));
            appmenus.setMenuTreeId("UyJw4m1rVtEbVjjzxOjDMzyFNRD4exri99RWhXYgEVfrmkBsAZ");
            appmenus.setAppType(2);
            appmenus.setAppId("4d9rQNPctCg38L4Go2WD8DNpPUA3BuhZTPeSii2w2pYoIfiZFx");
            appmenus.setRefObjectId("a62023GVeE23PEc0gxTOLVQhMv5kbXMvZNjZPrkX8T4VnAczXS");
            appmenus.setMenuLabel("70YElW9rrqRI3FHsuQ7UNHakrgptBDUArN5VFbSCHbZIyqhhWQ");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "baZMzXamfZdC86DrECkCWubrMsX4e7enp5uTCB40G5u75hyjESOK3YZbyC34H74HpWYcPY0WmXvrsvs1cft6VG8hdE4t25oKDHV4hguhr9D8RHmFSPferShJsrv8RIK86fd4vaBMgQPF4GMncJGZ6UAXt5xaRbcinajPr50O2w62VwZ6q3l7lAjVwgGALnH9YADslnE07P4VggFY3RSOmyFkyUNpg7uTXz6Gy7Bc3u9jpDp7MFUw3CK0AMopMb4un"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "DOP3IZXW7j71nCjJQv3Jt0Dun7BrYBm1Rk02bpp78PRvdLImwQ8hiYJIEm6lm8eyIk0AlCGZWSlCQRjG7jTlQImJryrDstGHk79U0xdevgDGIfQgKbd7Vh4nExblVdhfV05QqDeGvkANiKZYZT5YCp3bP8U5Z6F8ZpDqA7c0V8T7rpEqBh0hwh6nlH3JJJUdMMb5IWUvTKeXqzBzAPr6y3M1btL0WRAclBUkI8upxt1EfASnOBGGQEOo2sxmTLN5i"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "gfHstOkWrYRjWVIfxoUeBCJ2aSW81EuuLaIFEToQN4vBWRf1zu4qpjkbteQsd7yrGOeyDPUbJsm1AY0tFlKJTXFzrSdisM29LfW2XKBd9mIefuCMMGlVO0VA4RKwJNY1cztvuADZFYXXuegOH98wpkwX6Ex5cLfD0KDgb90btty4zqCBkzn5qZQW9AVkPSZ3XEaMqthnUbsg9GUNuLjrfZcV7A0L9L0n0gv7K7IYmZqEKo0SFw175p5vooGjlR1Jd"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "4bmIzhwyguM3id3KxxryJfEERY5yR40dTJM51f9oc0HHQIG3CVMCBeZicnFWSuxkQ"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "HX4hdawsuoRGdAjecDWShiKbUeKeLMOeZK6ZAzszKLO0OvOZfss9zJnABZGEZF0Q6uE4dSNkoDbfGZK7mObUH3LQoqt6LY04HJ9uEIPCjL7jDAVoWo8qOu1xOCzsmHpjiUi7VnbcrvyWBQR49VilR272okjRs5haR6JZ0t8SFMQjG2ukP3HTU116jHyzp0KC3pJONoC7YRGMWJ0ssOhnr7BFfk9X2eaGjIEYtRzlspCf7X3DmV1NEvJEMN1srKwgh"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "Q3YC"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "g7uqRYevk5ATo62ve3972HGxscp0hQSSnaE6RZTiw0NH133IEZrNOyBcT9EXcgSmVAoW3BGg16n2sbEH1jyU6dwSbHWCAxMDwXuKsZDd2eM4eedpvFggrPdEmwYhIGIrlX22H6rH3g4kiyjeYL80dpYzyA2DbJPIkOac3zUZhpvWbYY2fPUdRa9PhqNlDRzclrkD3H0vmL3ZP9igM7iBaqSAAGmLIV4cCXx0XjqNw7juDKLbBjyPWk5rofe09W2Lb"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 21));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "Ali5qDkU1V9MRdHResggKQw3eQaBTLz3b3XExVtwKScWlomK432NZmtJNSFrzMGIQkF6VBmm7XEWA521Gr9i5fdKgZlunPvJqvyF1PVKubYJUWAojHWHpVFkDVhYMenyDNLdeZWNX1sEWj1Pp2inAHpu8uCjNT6P0Wj5FMYzuzThwMXcXh71RERy3cvW8a0XRrNa3meQFySAabgjeDI9j8eNcFFdoirEMna70gEns99RfPvXsb6fjEuF1ocyznEWj"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
