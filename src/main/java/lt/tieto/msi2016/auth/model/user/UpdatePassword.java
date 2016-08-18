package lt.tieto.msi2016.auth.model.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by localadmin on 16.8.18.
 */
public class UpdatePassword {

    @NotNull
    @Size(min=6,max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String currentPassword;

    @NotNull
    @Size(min=6,max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$")
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
