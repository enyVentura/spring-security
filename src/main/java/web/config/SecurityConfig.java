package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import web.config.handler.LoginSuccessHandler;
import web.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private LoginSuccessHandler loginSuccessHandler;
    //private final UserDetailsService userDetailsService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public SecurityConfig(/*@Qualifier("userService")*/ /*UserDetailsService userService,*/ LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
        //this.userDetailsService = userService;
    }

    /* @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder(){
            return new BCryptPasswordEncoder();
        }*/
   @Autowired
   BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        //auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
        auth.userDetailsService(userService).passwordEncoder(cryptPasswordEncoder);
    }

   /* @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("ADMIN").password("ADMIN").roles("ADMIN");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable(); //- попробуйте выяснить сами, что это даёт

        http.authorizeRequests()

               // .antMatchers("/").permitAll() // доступность всем
               // .antMatchers("/user/**").access("hasAnyRole('ROLE_USER')") // разрешаем входить на /user пользователям с ролью User
               // .antMatchers("/admin/**").access("hasAnyRole('ROLE_ADMIN')")

                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/user/**").hasAuthority("ROLE_USER")
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()

                .and().formLogin()  // Spring сам подставит свою логин форму
                //.loginPage("/login")
                .successHandler(loginSuccessHandler)

                .and().logout()
              //  .permitAll()
              //  .logoutUrl("/login?logout")
                .logoutSuccessUrl("/");

        /*http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()

                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginPage("/")
                .successHandler(loginSuccessHandler)
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/home")
                //.usernameParameter("user_name")
                //.passwordParameter("password")

                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().exceptionHandling();*/





       /* http.formLogin()
                // указываем страницу с формой логина
                .loginPage("/login")
                //указываем логику обработки при логине
                .successHandler(new LoginSuccessHandler())
                // указываем action с формы логина
                .loginProcessingUrl("/login")
                // Указываем параметры логина и пароля с формы логина
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // даем доступ к форме логина всем
                .permitAll();

        http.logout()
                // разрешаем делать логаут всем
                .permitAll()
                // указываем URL логаута
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // указываем URL при удачном логауте
                .logoutSuccessUrl("/login?logout")
                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
                .and().csrf().disable();

        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/hello").access("hasAnyRole('ADMIN')").anyRequest().authenticated();*/
    }
}
