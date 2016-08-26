package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import projectthree.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
        appmenus.setExpiryDate(new java.sql.Timestamp(1472195860567l));
        appmenus.setMenuTreeId("WZZzpNm80l13RgudkpQCQI1wnkRLDEhYAlLXWVMdksta6TPY7u");
        appmenus.setRefObjectId("bCUnr1j3kzJUkmdZ2BU0ipyvAwgKci1LBAynlqhP3nflWmLhP2");
        appmenus.setMenuHead(true);
        appmenus.setUiType("vqT");
        appmenus.setGoLiveDate(new java.sql.Timestamp(1472195860567l));
        appmenus.setStartDate(new java.sql.Timestamp(1472195860567l));
        appmenus.setMenuLabel("nRuRrt4nhJyXeudBEwOlXG4bjwlokzWv0rHAYtLzMFTl3yuI21");
        appmenus.setMenuAction("RfoouSvFXFXCYOsLmkPyk2JPs9zSIH1pZXK1G6QatJ3ayzuY49");
        appmenus.setMenuIcon("cHfCYGi6N60BCH0LjBClbNGwTrVtd5rjheC3JGLVlckGhMvaW7");
        appmenus.setAutoSave(true);
        appmenus.setAppType(1);
        appmenus.setAppId("lR1Dq9lmmu1jtwHiRDeth4ryZIm85qHb8r3fLNrClUt9qCdoHU");
        appmenus.setMenuCommands("rIap8SD7oSYN28sa1yNweRSEu6WASSoXcNqdWRQO9kJLGzupot");
        appmenus.setMenuAccessRights(8);
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
            appmenus.setExpiryDate(new java.sql.Timestamp(1472195860579l));
            appmenus.setMenuTreeId("M4mUFdBJStYXK3D3ZwVvVU3FvmdkNoL979hFdVktQ6f0lXOgwL");
            appmenus.setRefObjectId("1xyKQREChH3JKDGG0dBRbHYzvJld3mFmlWVpbvm7xm1bkokJRC");
            appmenus.setUiType("J4f");
            appmenus.setGoLiveDate(new java.sql.Timestamp(1472195860585l));
            appmenus.setStartDate(new java.sql.Timestamp(1472195860585l));
            appmenus.setMenuLabel("kMVxMJticK3PO137Ufx9hUVyDUA0t5K4dFPWDp8KSJNJwroPd1");
            appmenus.setMenuAction("87W9tLwPQekZjelBAHKyMxg2vu1FIRJVUFJhOPsvQBpqEfyTve");
            appmenus.setMenuIcon("XnAJ16pkPvuAr5b8MaMfdVbxi7Gcf2jjOXfLUPhDUoFRgRkSbL");
            appmenus.setVersionId(1);
            appmenus.setAppType(1);
            appmenus.setAppId("I3wFCKdKlMMe54a57Akhois2K6fZjFplDidZ9AqVgWSfAbUnnS");
            appmenus.setMenuCommands("Mo4ld5eoTIGEjvhnnNoIntyuviVXk2b0sG6eb1zGduCfL2yIza");
            appmenus.setMenuAccessRights(8);
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "RVQIvBAz0ED0Lr4laWwvngQUOHWtH14cQ1ZBvyqOYEiZ4OhoQP1j63iZMUzQhiEKeC6hnos99AH7RAzzRjXqNeJUU22k4ztULiIc2fReDmBOTjk2bUoJhxY8NlwMNgW6CI7JEF4PnrIXb7L8r9VnzDD3NdRiC9M3tMWV74iNwJW8YD6Wq2KARNp8C9DzeUCkqoh7g84YcGR7n4oUaVGrBNB19toNS5zlWNXq8HpWFtr65pGxXlzcu47a9Q0NWCTpl"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "5gpmTygKosKEhNzV6KPrR0BIWVjURgrua9CsTED9xU1Zgx4rCJQpVsDIVzFnYV1uGRQKVZrEo5cp1IRgeERUGUYiucQ08xuqUotBPeH0TJ2qhNC6Ix1idTkgXoqHdhyrIwvQiZJErcAIUdAfSiE6NeZVgdIJ1DscelI65Xxcre9WEhitFtRpqwJ3TByR986btJJx1Sr8WtvNSYZr1G7FpK7ave27WLgQXajVbrwXMNh4IeGsIF5GDRQlelXTjHtls"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "K3JVqOvTXb9wNTKLmc9s74jUOPWeiDQN0AyLtdkn9BQwYRVGzAJ3Vuomi3Jf8I1uRRC2vrbZeFwUS2k3YmKDPrqSl9QKGEUHvPjTPWFK1Q1YWkIxyz3S0LM2Ca2ePZk9pQ0NSH6Xc0GWkXjcPmBBmTU3frstsrl2eZ4T52QcCji2ra1wQsMTqQKdMmg462xQol5bPFYZynva5v8XJnEZYcbaxSwp11xUWjuGBWA84cCekrNdPmsFVvEPDQtH5SZga"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "6UMtPXMerbAF917u47Gh5bubNmnZPPcUqR4TObtgSVOCQuWi6PX9gQnQuNHINZL0e"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "zxsJ6TKqVhr5OOfHJWsZYoLksu3PprOtRDSflH2m1YNA2hwRiNrsLyQ74b2z1Mqy8EqeNb8nLfpGhHs3kFtPn1Foc92a2SyGSQaoo56KOdqxTen09A1cLLgkvW9qmC60mt5a2Wayf2tZ2rL1mY8uY3ssxJaAv8cMJxDYG2pn4LODmLlz0p4lQFeRiJDIf8eDl6XJg1ru81LLA9sibjbmBiOBbsNztD2m4oCDdKLcgvvUHLqNgEWwQk9XNvFsu3ENp"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "9vsT"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "7z7M0IXH5TxcMxcDT9CBHcPGiG8vxGEIOcz6ggI8XhpT7AnV1ux9w5uw1ZCkitS7boftrkHLOLYBQi3vSL3pVdnAs1deAWoNHHDK2iHin1xNrbhZdcLSdpe8XfMwqfd8ij0N8NiVJT3kK8rDQ8PymIyZp1k9slxOkaJ1CYmF20iEqoRTGxMmThL9uclwqnwGIyK2vItzyGrtIoGWzjPLZ8FbYA89HkjS8lkwx3FZRIJ8uKAqjqPfY0h6fLLWoFmAm"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 21));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 3));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "vuVy38tYea0twq9L2vHvFF0C3iVxBnV3cXvrnGM52zu2NzdzFRJhGkdatNmpacMxysYS2YUE4iy6aFcxG4CvrV1Qe67JDvzYJcuHPaQgCJCMGGonxB9TzYPgl0Z8bGWbF6Xszmlh8IB2MVhixxUVopR9hNyXxJYYIMDLEhLXAjUUIQ8AgRhZBbZaWCFm5TlP1NlSs2AmEFmn8JE3OfNIaCrdKqb1YWJQsMHTKNMxc8RmZGOWQcpYUI54lQcNomVKq"));
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
