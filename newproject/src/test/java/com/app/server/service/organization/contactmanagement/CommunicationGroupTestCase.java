package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CommunicationGroupRepository;
import com.app.shared.organization.contactmanagement.CommunicationGroup;
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
public class CommunicationGroupTestCase extends EntityTestCriteria {

    /**
     * CommunicationGroupRepository Variable
     */
    @Autowired
    private CommunicationGroupRepository<CommunicationGroup> communicationgroupRepository;

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

    private CommunicationGroup createCommunicationGroup(Boolean isSave) throws Exception {
        CommunicationGroup communicationgroup = new CommunicationGroup();
        communicationgroup.setCommGroupName("viDWCsXHmh8tV1YptX3h48yvNXOm1jKSdDvZBdl2wJ22a0rIwB");
        communicationgroup.setCommGroupDescription("cV6AHVPJZhuoh2HjYIlpMqyh17vyoQjcBlwA0w88RxLkh7ZXRG");
        communicationgroup.setEntityValidator(entityValidator);
        return communicationgroup;
    }

    @Test
    public void test1Save() {
        try {
            CommunicationGroup communicationgroup = createCommunicationGroup(true);
            communicationgroup.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            communicationgroup.isValid();
            communicationgroupRepository.save(communicationgroup);
            map.put("CommunicationGroupPrimaryKey", communicationgroup._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CommunicationGroupPrimaryKey"));
            CommunicationGroup communicationgroup = communicationgroupRepository.findById((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
            communicationgroup.setCommGroupName("4FOTbMMFTKZumvr68V6eejDuOM7F6pRMRSw7O8skRdbAdIrSYL");
            communicationgroup.setVersionId(1);
            communicationgroup.setCommGroupDescription("BS9cbWYVmezyWXkZ8Bpgmr0EXuYHDX8bEGk2mAIyLYszzH6Mqi");
            communicationgroup.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            communicationgroupRepository.update(communicationgroup);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CommunicationGroupPrimaryKey"));
            communicationgroupRepository.findById((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CommunicationGroupPrimaryKey"));
            communicationgroupRepository.delete((java.lang.String) map.get("CommunicationGroupPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCommunicationGroup(EntityTestCriteria contraints, CommunicationGroup communicationgroup) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            communicationgroup.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            communicationgroup.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            communicationgroup.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            communicationgroupRepository.save(communicationgroup);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "commGroupName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "commGroupName", "SMDRLuGW8gYHC99und41fDT83mNyOs4AXUvica7VpzsMu687MsNGuBWH8PgjGVkBuOncUBUsveAY5T2ScQHr1Ir2XredfUNLacCQcNYVzjgYYvRXXfP0zzMcpBUr2e9ZL"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "commGroupDescription", "wVnObJWhQSFVjpEb3PCbZvcaFyd85eQ8oFPx1lUVxjbNwCq3sS1V4oCYHhvFeKOP0MpMaVqVVR8Ef8lV0PiksysmXksANPtSKVi38I4A3pqxd7nhWx01Sl3OaUDmEZyHIskHeAs5r7XRzFpUfs5yCuMMi18v22JsgLb3NUWnUA3R3NaY7nuGqEfl8UFIAXayIOc9MapmQBjPYTM3QbMyJoexu1DSdku0YxiEe7U8UYM4uRvPmVnaTXSYzi9zOBGUJ"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CommunicationGroup communicationgroup = createCommunicationGroup(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = communicationgroup.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(communicationgroup, null);
                        validateCommunicationGroup(contraints, communicationgroup);
                        failureCount++;
                        break;
                    case 2:
                        communicationgroup.setCommGroupName(contraints.getNegativeValue().toString());
                        validateCommunicationGroup(contraints, communicationgroup);
                        failureCount++;
                        break;
                    case 3:
                        communicationgroup.setCommGroupDescription(contraints.getNegativeValue().toString());
                        validateCommunicationGroup(contraints, communicationgroup);
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
