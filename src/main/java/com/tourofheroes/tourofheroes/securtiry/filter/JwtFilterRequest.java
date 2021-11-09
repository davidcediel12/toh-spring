package com.tourofheroes.tourofheroes.securtiry.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.tourofheroes.tourofheroes.securtiry.JWTUtil;
import com.tourofheroes.tourofheroes.services.AuthUsersService;

@Service
public class JwtFilterRequest extends OncePerRequestFilter {
	
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthUsersService authService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		System.out.println("Hello im the filter");
		
		// Obtaining the header from the incoming request
		String authHeader = request.getHeader("Authorization");
		// Si existe el header y es una autorizacion de JWT, lo capturamos
		if(authHeader != null && authHeader.startsWith("Bearer")) {
			// Le quieto el 'Bearer '
			String jwt = authHeader.substring(7);
			String username = jwtUtil.getUsername(jwt);
			// Si hay un usuario y no hay una autenticacion
			if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = authService.loadUserByUsername(username);
				if(jwtUtil.validateToken(jwt, userDetails)) {
					/*
					 *  Creamos un nuevo token de usuario para la sesion
					 *  No le mandamos credenciales particulares, por eso null
					 *  Le damos los roles (aunque no los hayamos manejado)
					 */
					UsernamePasswordAuthenticationToken authToken = 
							new UsernamePasswordAuthenticationToken(
									userDetails, null, userDetails.getAuthorities());
					
					/*
					 *  Le damos los detalles de la conexion al token como
					 *  Navegador, horario, etc.
					 */
					authToken.setDetails(
							new WebAuthenticationDetailsSource().buildDetails(request));
					
					/*
					 *  Agregamos la autenticacion para que no tenga que pasar por todo 
					 *  el proceso de creado
					 */
					SecurityContextHolder.getContext().setAuthentication(authToken);
				} // End of validation token
			} // End of check if there are no authentication
		} // End of check if exists the auth header
		
		
		// Seguimos con la peticion normal
		filterChain.doFilter(request, response);
	}

}
