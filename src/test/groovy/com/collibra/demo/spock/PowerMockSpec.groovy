package com.collibra.demo.spock

import spock.lang.Specification

class PowerMockSpec extends Specification {

    def 'test private static method'(){
        expect:
            Application.addTwo(2) == 4
    }

    def 'test private static method with mocking'(){

        GroovyMock(Application, global: true)
        Application.addTwo(_ as Integer) >> 3

        expect:
            Application.addTwo(2) == 3
    }

}
