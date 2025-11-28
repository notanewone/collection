
class Config1 {

    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, AuthenticationFilter authenticationFilter,
                                           EntryPoint entryPoint) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(authenticationFilter)
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        USER_RESOURCE_PATH + "/**",
                        PROVIDER_RESOURCE_PATH + "/**").authenticated())
                .authorizeHttpRequests(auth -> auth.requestMatchers(unsecuredPaths).permitAll())
                .exceptionHandling(config -> config.authenticationEntryPoint(entryPoint));

        return httpSecurity.build();
    }

    // ****************************************************

    @Value("${spring.security.oauth2.unsecured-paths}")
    private String[] unsecuredPaths;

    @Value("${spring.security.oauth2.issuer-uris}")
    private List<String> issuerUris;

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        JwtIssuerAuthenticationManagerResolver authenticationManagerResolver =
                new JwtIssuerAuthenticationManagerResolver(issuerUris);

        http.csrf(auth -> auth.disable())
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers(
                                        Stream.of(unsecuredPaths).map(path -> RegexRequestMatcher.regexMatcher(path))
                                                .toArray(RegexRequestMatcher[]::new))
                                .permitAll().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.authenticationManagerResolver(authenticationManagerResolver));
        return http.build();
    }

    // ****************************************************

    @Bean
    public ReactiveClientRegistrationRepository clientRegistrations() {
        ClientRegistration registration = ClientRegistration
                .withRegistrationId(AUTH0_REGISTRATION_ID)
                .tokenUri(tokenUri)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .build();
        return new InMemoryReactiveClientRegistrationRepository(registration);
    }

    @Bean
    public ReactiveOAuth2AuthorizedClientManager authorizedClientManager(
            final ReactiveClientRegistrationRepository clientRegistrations) {

        InMemoryReactiveOAuth2AuthorizedClientService clientService = new InMemoryReactiveOAuth2AuthorizedClientService(
                clientRegistrations);
        AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager authorizedClientManager =
                new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(
                        clientRegistrations, clientService);

        ReactiveOAuth2AuthorizedClientProvider authorizedClientProvider =
                ReactiveOAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials(clientCredentialsWithAudience(audience))
                        .build();

        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }

    @Bean
    public WebClient auth0WebClient(final ReactiveOAuth2AuthorizedClientManager authorizedClientManager) {
        ServerOAuth2AuthorizedClientExchangeFilterFunction oauth
                = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth.setDefaultClientRegistrationId(AUTH0_REGISTRATION_ID);
        return WebClient.builder()
                .filter(oauth)
                .build();
    }

    // ****************************************************

    @Bean
    public ExampleApi exampleApiClient(final WebClient auth0WebClient) {
        ApiClient apiClient = new ApiClient(auth0WebClient);
        apiClient.setBasePath(baseUrl);
        return new ExampleApi(apiClient);
    }

}