package com.client.ws.rasmooplus.repository.jpa;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserCredentials,Long>{

    Optional<UserCredentials> findByUsername(String username);
}
