package com.wedatalab.project.domain.Board.repository;

import com.wedatalab.project.domain.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
