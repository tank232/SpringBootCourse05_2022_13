package com.av.repository;


import com.av.db.Book;
import com.av.db.Comment;
import com.av.db.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {



}
