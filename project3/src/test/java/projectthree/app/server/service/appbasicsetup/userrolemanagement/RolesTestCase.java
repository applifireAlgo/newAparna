package projectthree.app.server.service.appbasicsetup.userrolemanagement;
import projectthree.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import projectthree.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import projectthree.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import projectthree.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import projectthree.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { projectthree.app.config.JPAConfig.class, projectthree.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleHelp("Ghr9VDpwfB6d9VzFrt4vTW6Ag6XlwThzqanogSTzxUyh9BgQ4q");
        roles.setRoleName("yZXVcxLAQgIYqYrQk6YGTgkQbIHRhz1wahdEwJGdKddR3sFNQa");
        roles.setRoleIcon("PROYdNB9xwdL6Uc34WRsb3ZL8j5Feh52ym6azz1WK8JbxuPkCX");
        roles.setRoleDescription("aBIZscYNSWJob5I5CVJ9p30p585wPC4hyKJxDLZdqt6V5JHZD8");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setExpiryDate(new java.sql.Timestamp(1472195859438l));
        appmenus.setMenuTreeId("DOtfyq4TAniDrkz6B7WUQ79vNWwoFNHLiSyAxWBqyNdBXuN3mE");
        appmenus.setRefObjectId("ocQ6im09OmknNGeTGDN0gB9QMUa3Z2ibJN2l3DDJ4193IMDFF5");
        appmenus.setMenuHead(true);
        appmenus.setUiType("dcU");
        appmenus.setGoLiveDate(new java.sql.Timestamp(1472195859439l));
        appmenus.setStartDate(new java.sql.Timestamp(1472195859439l));
        appmenus.setMenuLabel("zdWLoZk2bp2laj1GLWavDrIwJPvHYh2GNzHAp4VDpHZt3S0azc");
        appmenus.setMenuAction("cQ8MtQxzGDYJVB0JL1tM7PJlP3Ik6iUNh3qIsjB9qtBfiNGnQT");
        appmenus.setMenuIcon("sQnUINmprdwGRTl0Vz6sjR1bR9szSNCzAeXXzVr6Y8fz9Z49L6");
        appmenus.setAutoSave(true);
        appmenus.setAppType(2);
        appmenus.setAppId("0Zu1oA6wTE0lXpl7U55PxqHO9A7qvNNoMgIdeGWg7omN9ZMHLO");
        appmenus.setMenuCommands("TvjbiYmmw4k4r1eBRytOV29KVwgz1EeRFiOYhKtodK724BZdEB");
        appmenus.setMenuAccessRights(4);
        appmenus.setMenuDisplay(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setIsRead(true);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleHelp("Hw9xG9B6XhNpPxceB1SDjE7sPKO7eEf89sdCwFc7kAKlNsOnsG");
            roles.setRoleName("KLUJ0uKwgLRe2KZcAWvNrGp2vso3OhHyl2TXrNq4Qdp4YQJesx");
            roles.setRoleIcon("WYBuVX0RrZ6s4JM4LhglALbuhDYIYxSumSJBboMLBwVwNzNegu");
            roles.setVersionId(1);
            roles.setRoleDescription("itPdmGrgpnfbFGbIkyl1udc9JgsIVucWTUeodc76mxex5Kwp6h");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.delete((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.delete((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "5GispVzAdeS7iyKICBmI4Td7Lr3lWOLsxGiXwMNOHqdtnhAR5cBHTaTOtvTTvbgUWPwiObA02rBSB0QOrfaQgZSrYKjJBZVrbjOaa1ryERqHxhfXSAlDhPXKdo7ob1cX6jaykalhPE0kQt4yKymb9lZiOVxlEBQobViBkF9fwcd2ejW5uzqWjXC9FX5rY43sxTTc15aEc3zFLz2Q8RI6qpVXhdD2elkr1kKdOjjqJKEcmaJ5QZYxspqm8hNzjMFXh"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "onJ1Bvla8MwkJiZXPXzzldcH3DNDOCnvrtNVmHtldTBzIcO2uI1C0jC3mhD2Marlea6ljjFkFJEWEvgZIbbkLL4Mk5sbaX7wjVL5wYZAans8G4lsrz2e1ComxiPhthxFQuVJsiNOY8yJuBpRfAfgGJY7AALSE9w25MgsImU0RAXvlhTr2sjWV3ckxykEZwKHB3pxqmhwQaYTUEL9j5BN3rHkKi92zGX5Qlng5Fw3uuJ4NhdNwW1RVCn9WlHLzz9w0"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "MkP6hwZPMX4HmjQ4hwPXvEbHNmctws0sAMr93rs193NxwdGB73GXReIXbvXgh9AkZ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "6ZhDl3c1IM8LdtR69QVrTVVC7aryTXWlq41E5xpxPSfSgA1rFIrrb33YZAbSONZS3Ii3fwC9yQQeAL3qFcYq3cnhSi3NVOt2M36500fPYGRWz3Dk8xxoTjzBZtvUohUTBPGR3o1Vkql1UW1b9LGIp8XELR5hE7flJ87k9Ca71BRNcu6ZlVMyC9hOI6iNN8O3MELpb2whGCI0bu0cR2XfljnnvXNyawfFmQpwH9vxqNy1AitBz6Wscosu5rLLAMtcI"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
