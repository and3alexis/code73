package com.code73.function.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.text.ParseException;

import org.springframework.stereotype.Component;

import com.code73.function.exceptionmapper.AppException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

@Component
public class SecurityImpl implements Security{
	
	private RSAPublicKey pubKey;
	
	public SecurityImpl() {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			BigInteger modulus = new BigInteger("94455100959489949038838388755738601142593614952749827272786288563343568585478865972941280890155348003875020992280095955485418805003814134144381622380438836453234589423363069980812293229070802108645920041451542822803140545395956182492796845663721493901528998970201459769622436772874432118138529170588819640223") ;
			BigInteger pubExp = new BigInteger("65537") ;
			RSAPublicKeySpec ks = new RSAPublicKeySpec(modulus, pubExp);
			pubKey = (RSAPublicKey) keyFactory.generatePublic(ks);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	public boolean verify(String jwt){
		try {
			SignedJWT signedJWT = SignedJWT.parse(jwt);
			JWSVerifier verifier = new RSASSAVerifier(pubKey);
			return signedJWT.verify(verifier);
		} catch (NullPointerException e) {
			throw new AppException(new NullPointerException("JWT required"));
		} catch (ParseException e) {
			throw new AppException(e);
		} catch (Exception e) {
			throw new AppException(e);
		}
		
	}

}
