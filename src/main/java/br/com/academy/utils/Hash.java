package br.com.academy.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utilitária para gerar hash MD5.
 */
public class Hash {

	/**
	 * Gera um hash MD5 para a senha fornecida.
	 *
	 * @param senha a senha a ser convertida em hash.
	 * @return a representação hexadecimal do hash MD5 da senha.
	 * @throws NoSuchAlgorithmException se o algoritmo de hash MD5 não for
	 *                                  encontrado.
	 */
	public static String md5(String senha) throws NoSuchAlgorithmException {
		MessageDigest messagedig = MessageDigest.getInstance("MD5");
		byte[] hashBytes = messagedig.digest(senha.getBytes());
		BigInteger hash = new BigInteger(1, hashBytes);
		return hash.toString(16);
	}
}