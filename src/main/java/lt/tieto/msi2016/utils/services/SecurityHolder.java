package lt.tieto.msi2016.utils.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.security.Principal;

/**
 * Created by localadmin on 16.8.4.
 */
public interface SecurityHolder {
    /**
     *
     * @return logged user Authentication
     */
    Authentication getAuthentication();

    /**
     *
     * @return logged user {@link User}
     */
    User getUserPrincipal();
}
