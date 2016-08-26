package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
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
        roles.setRoleDescription("LFfps2CkBjtPz75N2pUTL35LoYB98LMyrWJeUojEQMDRKyMFxb");
        roles.setRoleIcon("Wrzi5KuiBhxOvDbCEvQtKDGpdk4gw6hpLPNvlrUFmzKwisIYZX");
        roles.setRoleName("itsNPKp2XjIGgSGF1mMc38YSrLgSAWs3EYzz6EVk7R6enhlroN");
        roles.setRoleHelp("NNWWIunss6QTpVDgAKtvJkp9LhfReveMZpX7PvCFJGVTMhbHJT");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        AppMenus appmenus = new AppMenus();
        appmenus.setAutoSave(true);
        appmenus.setGoLiveDate(new java.sql.Timestamp(1472208989867l));
        appmenus.setMenuIcon("dqVxnrcgWdmEXNN3AnToEtukcpHiaXuQvqEEfmUprzisU6So2b");
        appmenus.setUiType("3Gb");
        appmenus.setMenuAction("IDn89vwVBFjQ2umB2qSNkCHpPKCCz1tZ3hmLu42EAUTlQpsicD");
        appmenus.setExpiryDate(new java.sql.Timestamp(1472208989868l));
        appmenus.setMenuHead(true);
        appmenus.setMenuAccessRights(11);
        appmenus.setMenuCommands("I6qZOaiOVxXDFvkBrtNWwpImfGyuLhGUdr67Ctlt1im0vK8oJr");
        appmenus.setStartDate(new java.sql.Timestamp(1472208989868l));
        appmenus.setMenuTreeId("1NUrm7x654JJb7oNZ41iNA5rSit0WtdKJvtmtK6e6BY2e3MALV");
        appmenus.setAppType(1);
        appmenus.setAppId("7dm2SB4I2hidPV07IubqL4h42HjxXN3QKgzVZ1mj6jFXDbtXvk");
        appmenus.setRefObjectId("5SZuexPpUKeLBFdcTSHTeXVW6hs4wG6gRvp5MjVm1lT6BSLw6Y");
        appmenus.setMenuLabel("MNzGmLZutYp3M6Jf27eEcwECvbexcRLQaWJ1tCqRSKBJBzXFPQ");
        appmenus.setMenuDisplay(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setRoles(roles);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsWrite(true);
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
            roles.setRoleDescription("Zd5i7GHpBZqZjvaWSm16ZgHemaUZhkxoBJSbKgxqttSCUdCzx5");
            roles.setRoleIcon("RhGsF4gtNyrFnMTwv8rfzNhZOOaToBxAhvYwtuY8AY71WLVUfM");
            roles.setRoleName("oMoZ0yELrWqC7fp9KxQ3OpoGNp9SI18wKVdNVAoSb3JeCqBXwm");
            roles.setRoleHelp("uMvuqsnzQ7256bWrOtRIWc2fzXg4wTpqxe8MECjeY5GxD5svKE");
            roles.setVersionId(1);
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
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "LE4Yac0poFOlgUGFUP4v3TSR7dv0Hcqp7jAMsVMeu3zLhS0CSSFdBm4HcPsbh3CosqBEFi2IDUz8djs4fNnaPReEAPqaX8UyWpK6bTdTa4yLeW894gCTlRpfqez8TgLt44OrHokV6GygIxAOC6PugkgV56nf84yKKuyEJx74VqON1X3yC4v1atcuE7WpCJxJnPhAwPMYA6UqrOpLpCWlgXHO6xcNLyTCldN7Oy8kKIuNuLXmUJ0EduN3mqMAVEu5b"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "Abho1Q0iid5So2k28Wo1H98NrM9E0MjzO5KWp5qNKmIVVagqLCDRGIX1TnJ42LroTDwYQH1shz8ZrQOKfyesz2oVuHf957OrMsCU5MAwRyEqOk0tw1IYRw8VDaW7EmYrpCJKajc2QzysfrWTlMQZRuiY3p4e5OmfiIZor37nzzpTIs5Hepodlg9EI21fHFVmzUY60EOGHdv7TBbFdMRq2iNAH48MbBjno8qE76ssGj2igy0a1qkJdQBWiVsOklOEw"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "zZUNsFM5hmZeAJHAv7pZ0m6cD3PXhdWxKKSYpuJGY1ytQtJEc4s5TiviDIKj6wD9V"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "7gTBcpjoaA2on9JuJzUAkalMA1Lz1KOAaNbHjZDlKQ4H40XomjYoHiN2AFXgSp206Z1amD83HgMvItEteMEfagrlEFCBNNHPNHQIm03CJhE25xOuuVvTj9doixjpsOr6uGObVoPioMzukRw8I4yW4wNxi0nlC599fagemU77qJiU2oYajpoFib1nV9DLokbo4sr4kTILUM2jsh83wDNFRONTTSp6MEZcnX2J4aSMKjcsLkh3eE6hXGWEm1c1JqOU8"));
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
