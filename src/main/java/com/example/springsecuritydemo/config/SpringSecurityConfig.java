package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.Filter.CheckTokenFilter;
import com.example.springsecuritydemo.handler.LoginAccessDefineHandler;
import com.example.springsecuritydemo.handler.LoginAuthHandler;
import com.example.springsecuritydemo.handler.LoginFiledHandler;
import com.example.springsecuritydemo.handler.LoginSuccessHandler;
import com.example.springsecuritydemo.service.CustomerUserDetailsService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity  //啟用Spring Security
public class SpringSecurityConfig {


    @Resource
    private CustomerUserDetailsService customerUserDetailsService;



    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFiledHandler loginFiledHandler;
    @Resource
    private LoginAuthHandler loginAuthHandler;
    @Resource
    private LoginAccessDefineHandler loginAccessDefineHandler;

    @Resource
    private CheckTokenFilter checkTokenFilter;
    /**
     * 密碼處理
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 新版的實現方法不再和舊版一樣在配置類裏面重寫方法，而是構建了一個過濾鏈對象並通過@Bean註解注入到IOC容器中
     * 新版整體代碼 （注意：新版AuthenticationManager認證管理器默認全局）
     * @param http http安全配置
     * @return SecurityFilterChain
     * @throws Exception 異常
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http    // 使用自己自定義的過濾器 去過濾接口請求
                .addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin((formLogin) ->
                        // 這裏更改SpringSecurity的認證接口地址，這樣就默認處理這個接口的登錄請求了
                        formLogin.loginProcessingUrl("/api/v1/user/login")
                                //　自定義的登錄驗證成功或失敗后的去向
                                .successHandler(loginSuccessHandler).failureHandler(loginFiledHandler)
                )
                // 禁用了 CSRF 保護。

                .csrf((csrf) -> csrf.disable())
                // 配置了會話管理策略為 STATELESS（無狀態）。在無狀態的會話管理策略下，應用程序不會創建或使用 HTTP 會話，每個請求都是獨立的，服務器不會在請求之間保留任何狀態信息。
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests((authorizeRequests) ->
                        // 這裏過濾一些 不需要token的接口地址
                        authorizeRequests
                                .requestMatchers("/api/v1/test/getTestInfo").permitAll()
                                .requestMatchers(  "/v3/**","/profile/**","/swagger-ui.html",
                                        "/swagger-resources/**",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/webjars/**","/swagger-ui/**","/v2/**","/favicon.ico","/webjars/springfox-swagger-ui/**","/static/**", "/webjars/**", "/v2/api-docs", "/v2/feign-docs",
                                        "/swagger-resources/configuration/ui",
                                        "/test/user",
                                        "/swagger-resources", "/swagger-resources/configuration/security",
                                        "/swagger-ui.html", "/webjars/**").permitAll()
                                .requestMatchers("/api/v1/user/login","/api/v1/user/getImageCode").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling((exceptionHandling) -> exceptionHandling
                        .authenticationEntryPoint(loginAuthHandler) // 匿名處理
                        .accessDeniedHandler(loginAccessDefineHandler)  // 無權限處理
                )
                .cors((cors) -> cors.configurationSource(configurationSource()))
                .headers((headers) -> headers.frameOptions((frameOptionsConfig -> frameOptionsConfig.disable())))
                .headers((headers) -> headers.frameOptions((frameOptionsConfig -> frameOptionsConfig.sameOrigin())));
        return http.build();
    }


    // 舊版本 需要繼承  extends WebSecurityConfigurerAdapter

    // 新版的比較簡單，直接定義好數據源，注入就可以了，無需手動到配置類中去將它提交給AuthenticationManager進行管理。
    // /**
    //  * 配置認證處理器
    //  * 自定義的UserDetailsService
    //  * @param auth
    //  * @throws Exception
    //  */
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(customerUserDetailsService);
    // }

    // /**
    //  * 配置權限資源
    //  * @param http
    //  * @throws Exception
    //  */
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     // 每次請求前檢查token
    //     http.addFilterBefore(checkTokenFilter, UsernamePasswordAuthenticationFilter.class);
    //     http.formLogin()
    //             .loginProcessingUrl("/api/v1/user/login")
    //             //　自定義的登錄驗證成功或失敗后的去向
    //             .successHandler(loginSuccessHandler).failureHandler(loginFiledHandler)
    //             // 禁用csrf防禦機制(跨域請求偽造)，這麼做在測試和開發會比較方便。
    //             .and().csrf().disable()
    //             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //             .and()
    //             .authorizeRequests()
    //             .antMatchers("/api/v1/test/getTestInfo").permitAll()
    //             // 放心swagger相關請求
    //             .antMatchers(  "/v3/**","/profile/**","/swagger-ui.html",
    //                     "/swagger-resources/**",
    //                     "/v2/api-docs",
    //                     "/v3/api-docs",
    //                     "/webjars/**","/swagger-ui/**","/v2/**","/favicon.ico","/webjars/springfox-swagger-ui/**","/static/**", "/webjars/**", "/v2/api-docs", "/v2/feign-docs",
    //                     "/swagger-resources/configuration/ui",
    //                     "/swagger-resources", "/swagger-resources/configuration/security",
    //                     "/swagger-ui.html", "/webjars/**").permitAll()
    //             .antMatchers("/api/v1/user/login","/api/v1/user/getImageCode").permitAll()
    //             .anyRequest().authenticated()
    //             .and()
    //             .exceptionHandling()
    //             // 匿名處理
    //             .authenticationEntryPoint(loginAuthenticationHandler)
    //             // 無權限處理
    //             .accessDeniedHandler(loginAccessDefineHandler)
    //             // 跨域配置
    //             .and()
    //             .cors()
    //             .configurationSource(configurationSource());
    //     // 設置iframe
    //     http.headers().frameOptions().sameOrigin();
    //     http.headers().frameOptions().disable();
    //
    // }


    /**
     * 跨域
     */
    CorsConfigurationSource configurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
        corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}

