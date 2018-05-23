package com.collibra.demo.spock

import spock.lang.Specification
import spock.lang.Unroll

class WhereSpec extends Specification {

    def 'the maximum of two numbers should be the greater or equal one'(){

        expect:
            Math.max(1, 2) == 2
            Math.max(2, 2) == 2
            Math.max(2, 3) == 3
    }

    def 'the maximum of two numbers should be the greater or equal one - with where'(){

        expect:
            Math.max(first, second) == result

        where:
            first | second || result
            1     | 2      || 2
            2     | 2      || 2
            2     | 3      || 3

    }

    @Unroll
    def 'the maximum of #first and #second should be #result - with where and unroll'(){

        expect:
            Math.max(first, second) == result

        where:
            first | second || result
            1     | 2      || 2
            2     | 2      || 2
            2     | 3      || 3
    }
}
