package com.collibra.demo.spock

import spock.lang.Shared
import spock.lang.Specification

class SetupGivenCleanupSpec1 extends Specification{

    @Shared MyFile file

    def setupSpec(){
        file = new MyFile('SetupGivenCleanup.txt')

    }

    def 'setup spec spec'(){

        expect:
            file.readLines()[0] == 'Spock is cool!'
    }

    def 'setup spec spec fails'(){

        when:
            file.readLines()
        then:
            NoSuchElementException exception = thrown()
            exception.message == 'No line found'


    }

    def cleanupSpec(){
        file.close()
    }
}
