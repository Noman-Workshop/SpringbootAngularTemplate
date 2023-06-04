package com.eastnetic.todoapp.auth.repository;

import com.eastnetic.todoapp.auth.domain.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
	
	// TODO: Refactor this into different branch
	default void deleteAllByEmail(String email) {
		this.findAll()
		    .forEach(refreshToken -> {
			    if (refreshToken.getEmail()
			                    .equals(email)) {
				    this.deleteById(refreshToken.getToken());
			    }
		    });
	}
}
