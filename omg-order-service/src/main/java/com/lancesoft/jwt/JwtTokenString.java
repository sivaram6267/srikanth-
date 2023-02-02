package com.lancesoft.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtTokenString {
	private String userName;
	private Token token;
	private String role;
}
