package projectthree.app.shared.encodecoboundedcontext;
import com.athena.server.dataengine.bizService.DTOMapperInterface;
import projectthree.app.config.annotation.Complexity;
import projectthree.app.config.annotation.SourceCodeAuthorClass;
import java.sql.Timestamp;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.athena.config.jsonHandler.CustomSqlTimestampJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "1", comments = "Testqone", complexity = Complexity.MEDIUM)
public class QoneRM implements DTOMapperInterface {

    private String empId;

    private String empName;

    private Long empSal;

    private Integer versionId;

    private String createdBy;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp createdDate;

    private String updatedBy;

    @JsonSerialize(using = CustomSqlTimestampJsonSerializer.class)
    @JsonDeserialize(using = CustomSqlTimestampJsonDeserializer.class)
    private Timestamp updatedDate;

    private Integer activeStatus;

    private Integer txnAccessCode;

    public QoneRM(Object[] aryObject) {
        if (aryObject != null) {
            empId = (aryObject[0] == null ? null : new java.lang.String(aryObject[0].toString()));
            empName = (aryObject[1] == null ? null : new java.lang.String(aryObject[1].toString()));
            empSal = (aryObject[2] == null ? null : new java.lang.Long(aryObject[2].toString()));
            versionId = (aryObject[3] == null ? null : new java.lang.Integer(aryObject[3].toString()));
            createdBy = (aryObject[4] == null ? null : new java.lang.String(aryObject[4].toString()));
            createdDate = (aryObject[5] == null ? null : (java.sql.Timestamp) aryObject[5]);
            updatedBy = (aryObject[6] == null ? null : new java.lang.String(aryObject[6].toString()));
            updatedDate = (aryObject[7] == null ? null : (java.sql.Timestamp) aryObject[7]);
            activeStatus = (aryObject[8] == null ? null : new java.lang.Integer(aryObject[8].toString()));
            txnAccessCode = (aryObject[9] == null ? null : new java.lang.Integer(aryObject[9].toString()));
        } else {
            empId = null;
            empName = null;
            empSal = null;
            versionId = null;
            createdBy = null;
            createdDate = null;
            updatedBy = null;
            updatedDate = null;
            activeStatus = null;
            txnAccessCode = null;
        }
    }

    public String getEmpId() {
        return empId;
    }

    public String getEmpName() {
        return empName;
    }

    public Long getEmpSal() {
        return empSal;
    }

    public Integer getVersionId() {
        return versionId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public Integer getTxnAccessCode() {
        return txnAccessCode;
    }
}
