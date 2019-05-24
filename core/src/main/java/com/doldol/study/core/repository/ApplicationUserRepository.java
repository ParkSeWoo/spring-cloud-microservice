package com.doldol.study.core.repository;

import com.doldol.study.core.model.ApplicationUser;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApplicationUserRepository extends PagingAndSortingRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
}
