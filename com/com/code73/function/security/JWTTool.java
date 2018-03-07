package com.code73.function.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Date;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class JWTTool {

	public static void main(String[] args) throws Exception {

		// KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		// keyGenerator.initialize(1024);
		//
		// KeyPair kp = keyGenerator.genKeyPair();
		// RSAPrivateKey privateKey = (RSAPrivateKey) kp.getPrivate();
		// RSAPublicKey publicKey = (RSAPublicKey) kp.getPublic();

		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		BigInteger modulus = new BigInteger(
				"94455100959489949038838388755738601142593614952749827272786288563343568585478865972941280890155348003875020992280095955485418805003814134144381622380438836453234589423363069980812293229070802108645920041451542822803140545395956182492796845663721493901528998970201459769622436772874432118138529170588819640223");
		BigInteger pubExp = new BigInteger(
				"44165618105842653742865306392279989828854520905932003401853350423960515674098820038677580780290835470508966588147630505819539708264596797636761378095215940744343482706634012116539883139490892124379823951665011178135535307891042357802003660405749222622984326980497160362888974966307015269130664925161384944129");
		RSAPrivateKeySpec priv = new RSAPrivateKeySpec(modulus, pubExp);
		RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(priv);

		JWSSigner signer = new RSASSASigner(privKey);

		JWTClaimsSet claimsSet = new JWTClaimsSet.Builder().subject("alice").issuer("https://c2id.com")
				.expirationTime(new Date(new Date().getTime() + 60 * 1000)).build();

		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

		signedJWT.sign(signer);

		String s = signedJWT.serialize();

		System.out.println(s);
	}

}
