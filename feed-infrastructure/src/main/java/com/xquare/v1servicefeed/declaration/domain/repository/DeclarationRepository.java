package com.xquare.v1servicefeed.declaration.domain.repository;

import com.xquare.v1servicefeed.declaration.domain.DeclarationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DeclarationRepository extends CrudRepository<DeclarationEntity, UUID> {
}
