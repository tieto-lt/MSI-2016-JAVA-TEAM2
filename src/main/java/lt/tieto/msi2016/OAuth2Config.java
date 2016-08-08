package lt.tieto.msi2016;

import javax.sql.DataSource;

import lt.tieto.msi2016.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class OAuth2Config implements AuthorizationServerConfigurer, ResourceServerConfigurer {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter c = new JwtAccessTokenConverter();
        c.setSigningKey("superrandomsigningkey");
        return c;
    }

    public class CustomJwtAccessTokenEnhancer implements TokenEnhancer {

        private JwtAccessTokenConverter c;

        public CustomJwtAccessTokenEnhancer(JwtAccessTokenConverter c) {
            this.c = c;
        }

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            DefaultOAuth2AccessToken token = new DefaultOAuth2AccessToken(accessToken);
            Map<String, Object> addInfo = new LinkedHashMap<>();
            addInfo.putAll(token.getAdditionalInformation());
            addInfo.put("userId", userRepository.findByUserName(((User)authentication.getPrincipal()).getUsername()).getId());
            token.setAdditionalInformation(addInfo);
            return c.enhance(token, authentication);
        }
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                   .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        JwtAccessTokenConverter converter = accessTokenConverter();
        endpoints.authenticationManager(authenticationManager)
                 .accessTokenConverter(converter)
                 .tokenEnhancer(new CustomJwtAccessTokenEnhancer(converter))
                 .tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient("web-ui")
               .authorizedGrantTypes("password", "refresh_token", "implicit")
               .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
               .scopes("read", "write", "trust")
               .accessTokenValiditySeconds(60 * 30)
               .refreshTokenValiditySeconds(60 * 60);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers("/api/users").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        // @formatter:on
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}