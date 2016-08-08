package lt.tieto.msi2016.utils.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class SecurityHolderImpl implements SecurityHolder{

    @Autowired
    private TokenStore tokenStore;

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

    @Override
    public void logout() {
        if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null)
        {
            String tokenValue = ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getTokenValue();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
            SecurityContextHolder.getContext().setAuthentication(null);
        }

    }

}
