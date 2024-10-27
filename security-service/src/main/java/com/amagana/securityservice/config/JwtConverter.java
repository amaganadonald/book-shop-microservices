package com.amagana.securityservice.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = 
			new JwtGrantedAuthoritiesConverter();
	@Value("${jwt.auth.converter.pricipal-attribute}")
	private  String pricipalAttribute;
	
	@Value("${jwt.auth.converter.ressource-id}")
	private  String ressourceId;
	
	@Override
	public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
		Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), 
				extractResourceRoles(jwt).stream()
				).collect(Collectors.toSet());
		return new JwtAuthenticationToken(
				jwt,
				authorities,
				getPrincipleClaimName(jwt)
				);
	}

	private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
		Map<String, Object> resourceAccess;
		Map<String, Object> resource;
		Collection<String> resourceRoles;
		if(jwt.getClaim("resource_access") == null) return Set.of();
		System.out.println(jwt);
		resourceAccess = jwt.getClaim("resource_access");
		if(resourceAccess.get(ressourceId) == null) return Set.of(); 
		resource = (Map<String, Object>) resourceAccess.get(ressourceId);
		resourceRoles = (Collection<String>) resource.get("roles");
		return resourceRoles
				.stream()
				.map(role -> new SimpleGrantedAuthority("Role_"+role))
				.collect(Collectors.toSet());
	}
	
	public String getPrincipleClaimName(Jwt jwt) {
		String claimName = JwtClaimNames.SUB;
		if (pricipalAttribute != null)
			claimName = pricipalAttribute;
	    return jwt.getClaim(claimName);
	}
}
