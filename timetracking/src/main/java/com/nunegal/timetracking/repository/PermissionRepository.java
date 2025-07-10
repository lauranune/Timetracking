package com.nunegal.timetracking.repository;

import com.nunegal.timetracking.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
