package com.Payment.CQueriesJPQL.Repo;

import com.Payment.CQueriesJPQL.entity.Card;
import org.springframework.data.repository.CrudRepository;

public interface CardRepository extends CrudRepository<Card, String> {
}
