package com.saber.multiplication.multiplicationv2.repositories;

import com.saber.multiplication.multiplicationv2.dto.ChallengeAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeAttemptRepository extends JpaRepository<ChallengeAttempt,Long> {

    List<ChallengeAttempt> findTop10ByUserAliasOrderByIdDesc(String userAlias);

//    @Query("SELECT ch FROM ChallengeAttempt ch WHERE ch.user.alias =:userAlias ORDER BY ch.id DESC ")
//    List<ChallengeAttempt> lastAttempts(String userAlias);
}
