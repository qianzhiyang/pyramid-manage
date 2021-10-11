package com.qianzhiyang.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qianzhiyang.entity.Pyramid;

/**
 * @author qianzhiyang <qianzhiyang@kuaishou.com>
 * Created on 2021-10-08
 */
@Repository
@Transactional
public interface PyramidRepository extends CrudRepository<Pyramid, Long> {

    List<Pyramid> findAllByLeftValIsLessThanAndRightValGreaterThanOrderByLeftVal(Long left, Long right);

    List<Pyramid> findAllByLeftValGreaterThanAndRightValIsLessThanOrderByLeftVal(Long left, Long right);

    @Modifying
    @Query("UPDATE Pyramid p SET p.leftVal = p.leftVal + :delta WHERE p.leftVal > :left")
    void addAllLeftAfter(@Param("left") long left, @Param("delta") long delta);

    @Modifying
    @Query(value = "UPDATE Pyramid p SET p.rightVal = p.rightVal + :delta WHERE p.rightVal > :right")
    void addAllRightAfter(@Param("right") long right, @Param("delta")  long delta);

    @Modifying
    @Query(value = "UPDATE Pyramid p SET p.rightVal = p.rightVal - :delta, p.leftVal = p.leftVal - "
            + ":delta WHERE p.leftVal > :left AND p.rightVal < :right")
    void plusLeftAndRightAfterAndBefore(@Param("left") long left, @Param("right") long right, @Param("delta")  long delta);
}
