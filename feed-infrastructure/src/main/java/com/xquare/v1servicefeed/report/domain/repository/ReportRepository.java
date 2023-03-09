package com.xquare.v1servicefeed.report.domain.repository;


import com.xquare.v1servicefeed.report.domain.ReportEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ReportRepository extends CrudRepository<ReportEntity, UUID> {
}
