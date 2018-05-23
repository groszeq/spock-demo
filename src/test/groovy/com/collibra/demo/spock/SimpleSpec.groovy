package com.collibra.demo.spock

import spock.lang.Specification

class SimpleSpec extends Specification {


    def 'this is an expect block spec - use for stateless classes/methods'(){
        expect:
            Math.min(1,2) == 1
    }

    def 'this is an expect block spec - use for stateless classes/methods - fail'(){
        expect:
            Math.min(1,2) == helperMethod()
    }

    def 'this is a when-then block spec - use for classes/methods with side effects'(){
        List<Integer> numbers = [1,2,3,4,5]

        when:
            numbers.add(6)
        then:
            numbers.size() == 6
            numbers[5] == 6

    }

    //this is not a spec
    def helperMethod() {
        return 2
    }

}
