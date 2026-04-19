package ru.ivan.NauJava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.ivan.NauJava.model.Report;

@RepositoryRestResource(path = "report")
public interface ReportRepository extends CrudRepository<Report, Long> {
}
