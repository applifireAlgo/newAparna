package projectthree.app.shared.encodecoboundedcontext.newdomain;
import com.athena.server.pluggable.interfaces.EntityValidatorInterface;
import java.io.Serializable;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.lang.Override;
import javax.xml.bind.annotation.XmlRootElement;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "5", comments = "PartUI", complexity = Complexity.MEDIUM)
@XmlRootElement
public class PartUI implements EntityValidatorInterface, Serializable {

    private static final long serialVersionUID = 1472204049542L;

    @Transient
    @JsonIgnore
    private EntityValidatorHelper<Object> dtoValidator;

    @Min(1)
    @Max(65535)
    private String ansId;

    @Min(1)
    @Max(65535)
    private String ansName;

    @Min(1)
    @Max(65535)
    private String ansWei;

    @Transient
    @JsonIgnore
    private boolean isDtoValidated = false;

    @JsonIgnore
    @Override
    public boolean isEntityValidated() {
        return isDtoValidated;
    }

    public String getAnsId() {
        return ansId;
    }

    public void setAnsId(String _ansId) {
        this.ansId = _ansId;
    }

    public String getAnsName() {
        return ansName;
    }

    public void setAnsName(String _ansName) {
        this.ansName = _ansName;
    }

    public String getAnsWei() {
        return ansWei;
    }

    public void setAnsWei(String _ansWei) {
        this.ansWei = _ansWei;
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
