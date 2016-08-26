package projectthree.app.shared.encodecoboundedcontext.newdomain;
import com.athena.server.pluggable.interfaces.CommonEntityInterface;
import java.io.Serializable;
import java.util.Comparator;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
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
import java.lang.Override;

@Table(name = "ast_Food_A")
@Entity
@Cache(type = CacheType.CACHE)
@SourceCodeAuthorClass(createdBy = "aparna.sawant@algorhythm.co.in", updatedBy = "aparna.sawant@algorhythm.co.in", versionNumber = "4", comments = "Food", complexity = Complexity.LOW)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "cuisineName", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Foo")
public abstract class Food implements Serializable, CommonEntityInterface, Comparator<Food> {

    private static final long serialVersionUID = 1472204966326L;

    @Column(name = "foodName")
    @JsonProperty("foodName")
    @NotNull
    @Size(min = 1, max = 256)
    private String foodName;

    @Column(name = "cuisineName")
    @JsonProperty("cuisineName")
    @NotNull
    @Size(min = 1, max = 256)
    private String cuisineName;

    @Transient
    private String primaryKey;

    @Id
    @Column(name = "foodId")
    @JsonProperty("foodId")
    @GeneratedValue(generator = "UUIDGenerator")
    @Size(min = 1, max = 256)
    private String foodId;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String _foodName) {
        if (_foodName != null) {
            this.foodName = _foodName;
        }
    }

    public String getCuisineName() {
        return cuisineName;
    }

    public void setCuisineName(String _cuisineName) {
        if (_cuisineName != null) {
            this.cuisineName = _cuisineName;
        }
    }

    public String getPrimaryKey() {
        return foodId;
    }

    public void setPrimaryKey(String _primaryKey) {
        this.primaryKey = _primaryKey;
    }

    public String _getPrimarykey() {
        return foodId;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String _foodId) {
        this.foodId = _foodId;
    }

    /**
     * Compares 2 objects and returns integer value
     * @param Food
     * @param Food
     */
    @Override
    public int compare(Food object1, Food object2) {
        return 0;
    }

    public String getPrimaryDisplay() {
        StringBuilder sb = new StringBuilder();
        sb.append((foodName == null ? " " : foodName));
        return sb.toString();
    }

    public String toString() {
        return getPrimaryDisplay();
    }

    public int hashCode() {
        if (foodId == null) {
            return super.hashCode();
        } else {
            return foodId.hashCode();
        }
    }

    public boolean equals(Object obj) {
        try {
            projectthree.app.shared.encodecoboundedcontext.newdomain.Food other = (projectthree.app.shared.encodecoboundedcontext.newdomain.Food) obj;
            if (foodId == null) {
                return false;
            } else if (!foodId.equals(other.foodId)) {
                return false;
            }
        } catch (java.lang.Exception ignore) {
            return false;
        }
        return true;
    }
}
