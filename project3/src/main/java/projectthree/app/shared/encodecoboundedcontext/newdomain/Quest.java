package projectthree.app.shared.encodecoboundedcontext.newdomain;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Version;
import javax.persistence.Embedded;
import projectthree.app.shared.EntityAudit;
import projectthree.app.shared.SystemInfo;
import java.lang.Override;
import javax.persistence.NamedQueries;

@Table(name = "ast_Quest_T")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "8", comments = "Quest", complexity = Complexity.LOW)
@NamedQueries({ @javax.persistence.NamedQuery(name = "Quest.findAll", query = " select u from Quest u where u.systemInfo.activeStatus=1"), @javax.persistence.NamedQuery(name = "Quest.findById", query = "select e from Quest e where e.systemInfo.activeStatus=1 and e.queId =:queId") })
public class Quest implements Serializable, CommonEntityInterface, Comparator<Quest> {

    private static final long serialVersionUID = 1472201427599L;

    @Column(name = "queName")
    @JsonProperty("queName")
    @NotNull
    @Size(min = 1, max = 256)
    private String queName;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "queId")
    @JsonProperty("queId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String queId;

    @JoinTable(name = "ast_QueAns_B", joinColumns = { @javax.persistence.JoinColumn(name = "queId", referencedColumnName = "queId") }, inverseJoinColumns = { @javax.persistence.JoinColumn(name = "ansId", referencedColumnName = "ansId") })
    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answer;

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

    public String getQueName() {
        return queName;
    }

    public void setQueName(String _queName) {
        if (_queName != null) {
            this.queName = _queName;
        }
    }

    public String getPrimaryKey() {
        return queId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return queId;
    }

    public String getQueId() {
        return queId;
    }

    public void setQueId(String _queId) {
        this.queId = _queId;
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

    public Quest addAnswer(Answer _answer) {
        if (this.answer == null) {
            this.answer = new java.util.ArrayList<projectthree.app.shared.encodecoboundedcontext.newdomain.Answer>();
        }
        if (_answer != null) {
            this.answer.add(_answer);
        }
        return this;
    }

    public Quest removeAnswer(Answer _answer) {
        if (this.answer != null) {
            this.answer.remove(_answer);
        }
        return this;
    }

    public Quest addAllAnswer(List<Answer> _answer) {
        if (this.answer == null) {
            this.answer = new java.util.ArrayList<projectthree.app.shared.encodecoboundedcontext.newdomain.Answer>();
        }
        if (_answer != null) {
            this.answer.addAll(_answer);
        }
        return this;
    }

    @JsonIgnore
    public Integer sizeOfAnswer() {
        if (this.answer != null) {
            return this.answer.size();
        }
        return 0;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> _answer) {
        this.answer = _answer;
    }

    @JsonIgnore
    public List<Answer> getDeletedAnswerList() {
        List<Answer> answerToRemove = new java.util.ArrayList<Answer>();
        for (Answer _answer : answer) {
            if (_answer.isHardDelete()) {
                answerToRemove.add(_answer);
            }
        }
        answer.removeAll(answerToRemove);
        return answerToRemove;
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
        setValidatoranswer(_validateFactory);
    }

    private void setValidatoranswer(EntityValidatorHelper<Object> _validateFactory) {
        if (answer != null) {
            for (int i = 0; i < answer.size(); i++) {
                answer.get(i).setEntityValidator(_validateFactory);
            }
        }
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
        if (this.answer == null) {
            this.answer = new java.util.ArrayList<Answer>();
        }
        for (Answer _answer : answer) {
            _answer.setEntityAudit(customerId, userId, recordType);
            _answer.setSystemInformation(recordType);
        }
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
        if (this.answer == null) {
            this.answer = new java.util.ArrayList<Answer>();
        }
        for (Answer _answer : answer) {
            _answer.setEntityAudit(customerId, userId);
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
            this.systemInfo.setActiveStatus(-1);
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
     * @param Quest
     * @param Quest
     */
    @Override
    public int compare(Quest object1, Quest object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((queName == null ? " " : queName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (queId == null) {
            return super.hashCode();
        } else {
            return queId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            projectthree.app.shared.encodecoboundedcontext.newdomain.Quest other = (projectthree.app.shared.encodecoboundedcontext.newdomain.Quest) obj;
            if (queId == null) {
                return false;
            } else if (!queId.equals(other.queId)) {
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
