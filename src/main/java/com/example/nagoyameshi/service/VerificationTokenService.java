package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyameshi.entity.User;
import com.example.nagoyameshi.entity.VerificationToken;
import com.example.nagoyameshi.repository.VerificationTokenRepository;

@Service
public class VerificationTokenService {
	private final VerificationTokenRepository verificationTokenRepository;
	
	public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
		this.verificationTokenRepository = verificationTokenRepository;
	}
	
//	会員登録時にverificationTokenRepositoryにユーザー名とランダム生成したTokenを格納
	@Transactional
	public void create(User user, String token) {
		VerificationToken verificationToken = new VerificationToken();
		
		verificationToken.setUser(user);
		verificationToken.setToken(token);
		
		verificationTokenRepository.save(verificationToken);
	}
	
//	メール認証時にトークンの文字列で検索した結果を返す。
	public VerificationToken getVerificationToken(String token) {
		return verificationTokenRepository.findByToken(token);
	}

}
