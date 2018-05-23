package com.collibra.demo.junit;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleTest {

    @Test
    public void thisIsAnExpectBlockTestCase_useForStatelessClassesAndMethods(){
        //expect:
        Assert.assertEquals(1, Math.min(1,2));
    }

    @Test
    public void thisIsAWhenThenBlockTestCase_useForStatefulClassesAndMethods(){
        //when:
        List<Integer> numbers = Stream.of(1,2,3,4,5).collect(Collectors.toList());
        //then:
        Assert.assertEquals(2, numbers.get(1).intValue());
        Assert.assertEquals(5, numbers.size());
    }

    private void thisIsNotATestMethod(){}

}
