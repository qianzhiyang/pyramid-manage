package com.qianzhiyang;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.qianzhiyang.constants.enums.IncludeEnum;
import com.qianzhiyang.entity.Pyramid;
import com.qianzhiyang.service.PyramidManageService;
import com.qianzhiyang.service.impl.strategy.pyramid.AddPyramidAlignLeft;
import com.qianzhiyang.service.impl.strategy.pyramid.AddPyramidAlignRight;

@SpringBootApplication
public class PyramidManageApplication implements CommandLineRunner {

    @Autowired
    private PyramidManageService pyramidManageService;

    public static void main(String[] args) {
        SpringApplication.run(PyramidManageApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printAll();
        queryLeaderPyramidsOfPyramidX(7);
    }

    void init() {
        add3childOfRoot();
        add3childOfRootSon();
        addChildOf3();
    }

    private void deleteOnePyramidAndFollowersOfIndex(int index) {
        pyramidManageService.deleteOnePyramidAndFollowers(indexedPyramid(index));
    }

    private void queryFollowerPyramidsOfIndex() {
        printAll(pyramidManageService.queryFollowerPyramids(indexedPyramid(0), IncludeEnum.INCLUDE));
    }

    private void addChild(long seqNo, int index) {
        pyramidManageService.addChildPyramid(String.format("qian-son%s-left", seqNo), indexedPyramid(index), new AddPyramidAlignLeft());
    }

    private void queryLeaderPyramidsOfPyramidX(int index) {
        printAll(pyramidManageService.queryLeaderPyramids(indexedPyramid(index), IncludeEnum.INCLUDE));
    }

    private void deletePyramidOfRootSonX(int index) {
        pyramidManageService.deleteOnePyramid(indexedPyramid(index));
    }

    private Pyramid rootPyramid() {
        return indexedPyramid(0);
    }

    private Pyramid indexedPyramid(int index) {
        return pyramidManageService.findAll().get(index);
    }

    private void add3childOfRoot() {
        pyramidManageService.addChildPyramid("qian-son1-left", rootPyramid(), new AddPyramidAlignLeft());
        pyramidManageService.addChildPyramid("qian-son2-left", rootPyramid(), new AddPyramidAlignLeft());
        pyramidManageService.addChildPyramid("qian-son3-right", rootPyramid(), new AddPyramidAlignRight());

        printAll();
    }

    private void add3childOfRootSon() {
        pyramidManageService.addChildPyramid("qian-son4-left", indexedPyramid(1), new AddPyramidAlignLeft());
        pyramidManageService.addChildPyramid("qian-son5-left", indexedPyramid(2), new AddPyramidAlignLeft());
        pyramidManageService.addChildPyramid("qian-son6-right", indexedPyramid(2), new AddPyramidAlignRight());

        printAll();
    }

    private void addChildOf3() {
        pyramidManageService.addChildPyramid("qian-son7-left", indexedPyramid(3), new AddPyramidAlignLeft());
        pyramidManageService.addChildPyramid("qian-son8-right", indexedPyramid(3), new AddPyramidAlignRight());

        printAll();
    }

    private void printAll() {
        pyramidManageService.findAll().forEach(System.out::println);
        System.out.println("end");
    }

    private void printAll(List<Pyramid> pyramids) {
        pyramids.forEach(System.out::println);
        System.out.println("end");
    }
}
