package projectthree.app.shared.appbasicsetup.usermanagement;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import org.eclipse.persistence.config.CacheIsolationType;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import javax.persistence.Embedded;
import projectthree.app.shared.EntityAudit;
import projectthree.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_UserAccessDomain_M")
@Entity
@Cache(type = CacheType.CACHE, isolation = CacheIsolationType.ISOLATED)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "3", comments = "UserAccessDomain", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "UserAccessDomain.findAll", query = " select u from UserAccessDomain u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "UserAccessDomain.findById", query = "select e from UserAccessDomain e where e.systemInfo.activeStatus=1 and e.userAccessDomainId =:userAccessDomainId") })
public class UserAccessDomain implements Serializable, CommonEntityInterface, Comparator<UserAccessDomain> {

    private static final long serialVersionUID = 1472195850752L;

    @Column(name = "userAccessDomain")
    @JsonProperty("userAccessDomain")
    @NotNull
    @Min(0)
    @Max(99999)
    private Integer userAccessDomain;

    @Column(name = "domainName")
    @JsonProperty("domainName")
    @NotNull
    @Size(min = 3, max = 256)
    private String domainName;

    @Column(name = "domainDescription")
    @JsonProperty("domainDescription")
    @NotNull
    @Size(min = 3, max = 256)
    private String domainDescription;

    @Column(name = "domainHelp")
    @JsonProperty("domainHelp")
    @Size(min = 3, max = 2048)
    private String domainHelp;

    @Column(name = "domainIcon")
    @JsonProperty("domainIcon")
    @Size(min = 3, max = 256)
    private String domainIcon;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "userAccessDomainId")
    @JsonProperty("userAccessDomainId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 3, max = 64)
    private String userAccessDomainId;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> entityValidator;

    @Version
    @Column(name = "versionId")
    @JsonProperty("versionId")
    private int versionId;

    @Embedded
    @JsonIgnore
    private EntityAudit entityAudit = new EntityAudit();

    @Embedded
    private SystemInfo systemInfo = new SystemInfo();

    @Transient
    private String primaryDisplay;

    public Integer getUserAccessDomain() {
        return userAccessDomain;
    }

    public void setUserAccessDomain(Integer _userAccessDomain) {
        if (_userAccessDomain != null) {
            this.userAccessDomain = _userAccessDomain;
        }
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String _domainName) {
        if (_domainName != null) {
            this.domainName = _domainName;
        }
    }

    public String getDomainDescription() {
        return domainDescription;
    }

    public void setDomainDescription(String _domainDescription) {
        if (_domainDescription != null) {
            this.domainDescription = _domainDescription;
        }
    }

    public String getDomainHelp() {
        return domainHelp;
    }

    public void setDomainHelp(String _domainHelp) {
        this.domainHelp = _domainHelp;
    }

    public String getDomainIcon() {
        return domainIcon;
    }

    public void setDomainIcon(String _domainIcon) {
        this.domainIcon = _domainIcon;
    }

    public String getPrimaryKey() {
        return userAccessDomainId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return userAccessDomainId;
    }

    public String getUserAccessDomainId() {
        return userAccessDomainId;
    }

    public void setUserAccessDomainId(String _userAccessDomainId) {
        this.userAccessDomainId = _userAccessDomainId;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int _versionId) {
        this.versionId = _versionId;
    }

    public void setPrimaryDisplay(String _primaryDisplay) {
        this.primaryDisplay = _primaryDisplay;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(SystemInfo _systemInfo) {
        this.systemInfo = _systemInfo;
    }

    /**
     * Returns boolean value if System information's active status =-1
     * @return boolean
     */
    @JsonIgnore
    public boolean isHardDelete() {
        if (this.systemInfo == null) {
            this.systemInfo = new SystemInfo();
        }
        if (this.systemInfo.getActiveStatus() == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validates the entity fields based on java.validation.constraints annotaions and sets isEntityValided value in System information object
     * @return boolean
     * @throws java.lang.SecurityException
     */
    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.entityValidator != null) {
            isValid = this.entityValidator.validateEntity(this);
            this.systemInfo.setEntityValidated(true);
        } else {
            throw new java.lang.SecurityException();
        }
        return isValid;
    }

    /**
     * Sets EntityValidator object
     * @param _validateFactory
     */
    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.entityValidator = _validateFactory;
    }

    /**
     * Creates a new entity audit object and  if primarykey fields are null then sets created by, updated by, active status values in the entity audit field.
     * @param customerId
     * @param userId
     */
    @Override
    public void setEntityAudit(int customerId, String userId, RECORD_TYPE recordType) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (recordType == RECORD_TYPE.ADD) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
        setSystemInformation(recordType);
    }

    /**
     * Creates a new entity audit object and System Information object and based on @Params RECORD_TYPE sets created by and updated by values in the entity audit field.
     * @param CustomerId
     * @param userId
     * @param RECORD_TYPE
     */
    @Override
    public void setEntityAudit(int customerId, String userId) {
        if (entityAudit == null) {
            entityAudit = new EntityAudit();
        }
        if (getPrimaryKey() == null) {
            this.entityAudit.setCreatedBy(userId);
            this.entityAudit.setUpdatedBy(userId);
            this.systemInfo.setActiveStatus(1);
        } else {
            this.entityAudit.setUpdatedBy(userId);
        }
    }

    /**
     * Returns Logged in user informatio.
     * @return auditInfo
     */
    @JsonIgnore
    public String getLoggedInUserInfo() {
        String auditInfo = "";
        if (this.entityAudit != null) {
            auditInfo = entityAudit.toString();
        }
        return auditInfo;
    }

    /**
     * Creates new System Information object.
     * @param RECORD_TYPE
     */
    @Override
    @JsonIgnore
    public void setSystemInformation(RECORD_TYPE recordType) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        if (recordType == RECORD_TYPE.DELETE) {
            this.systemInfo.setActiveStatus(0);
        } else {
            this.systemInfo.setActiveStatus(1);
        }
    }

    /**
     * Sets active status in System Information object.
     * @param activeStatus
     */
    @JsonIgnore
    public void setSystemInformation(Integer activeStatus) {
        this.systemInfo.setActiveStatus(activeStatus);
    }

    /**
     * Returns system information object.
     * @return systemInfo
     */
    @JsonIgnore
    public String getSystemInformation() {
        String systemInfo = "";
        if (this.systemInfo != null) {
            systemInfo = systemInfo.toString();
        }
        return systemInfo;
    }

    /**
     * Creates System information obect if null and sets transaction access code in that object.
     * @param transactionAccessCode
     */
    @Override
    @JsonIgnore
    public void setSystemTxnCode(Integer transactionAccessCode) {
        if (systemInfo == null) {
            systemInfo = new SystemInfo();
        }
        this.systemInfo.setTxnAccessCode(transactionAccessCode);
    }

    /**
     * Compares 2 objects and returns integer value
     * @param UserAccessDomain
     * @param UserAccessDomain
     */
    @Override
    public int compare(UserAccessDomain object1, UserAccessDomain object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((userAccessDomain == null ? " " : userAccessDomain) + ",").append((domainName == null ? " " : domainName) + ",").append((domainDescription == null ? " " : domainDescription));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (userAccessDomainId == null) {
            return super.hashCode();
        } else {
            return userAccessDomainId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain other = (projectthree.app.shared.appbasicsetup.usermanagement.UserAccessDomain) obj;
            if (userAccessDomainId == null) {
                return false;
            } else if (!userAccessDomainId.equals(other.userAccessDomainId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return this.systemInfo.isEntityValidated();
    }
}
