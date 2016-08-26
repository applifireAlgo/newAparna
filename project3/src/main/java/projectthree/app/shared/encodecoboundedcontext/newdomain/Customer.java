package projectthree.app.shared.encodecoboundedcontext.newdomain;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import java.io.Serializable;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Customer", complexity = Complexity.MEDIUM)
@XmlRootElement
public class Customer implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1472200453493L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    private String custId;

    private String custName;

    private Integer custAccNo;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String _custId) {
        this.custId = _custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String _custName) {
        this.custName = _custName;
    }

    public Integer getCustAccNo() {
        return custAccNo;
    }

    public void setCustAccNo(Integer _custAccNo) {
        this.custAccNo = _custAccNo;
    }

    @Override
    public void setEntityValidator(EntityValidatorHelper<Object> _validateFactory) {
        this.dtoValidator = _validateFactory;
    }

    @JsonIgnore
    @Override
    public boolean isValid() throws SecurityException {
        boolean isValid = false;
        if (this.dtoValidator != null) {
            isValid = this.dtoValidator.validateEntity(this);
            this.isDtoValidated = true;
        } else {
            throw new SecurityException();
        }
        return isValid;
    }
}
