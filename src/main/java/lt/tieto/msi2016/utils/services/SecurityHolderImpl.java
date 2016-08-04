package lt.tieto.msi2016.utils.services;


import lt.tieto.msi2016.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

@Component
public class SecurityHolderImpl implements SecurityHolder{

    /**
     *{@inheritDoc}
     */
    @Override
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public User getUserPrincipal() {
        return (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
