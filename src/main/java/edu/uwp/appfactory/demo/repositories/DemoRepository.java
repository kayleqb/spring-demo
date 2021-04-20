package edu.uwp.appfactory.demo.repositories;

import edu.uwp.appfactory.demo.entities.Demo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// communicates with db
@Repository
public interface DemoRepository extends CrudRepository<Demo, UUID> {
    Boolean existsByName(String name);

    Optional<Demo> findByName(String name);

    @Override
    Optional<Demo> findById(UUID uuid);

    @Query(value = "select * from demo where uuid = :uuid")
    List<Demo> findAnyByUUID(@Param("uuid") UUID uuid);
}




