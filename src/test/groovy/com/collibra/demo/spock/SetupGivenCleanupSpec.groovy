package com.collibra.demo.spock

import spock.lang.Specification

class SetupGivenCleanupSpec extends Specification{

    MyFile file

    def setup(){
        file = new MyFile('SetupGivenCleanup.txt')

    }

    def 'setup or given spec one'(){

        expect:
            file.readLines()[0] == 'Spock is cool!'

    }

    def 'setup or given spec two'(){

        expect:
            file.readLines()[0] == 'Spock is cool!'

    }

    def cleanup(){
        file.close()
    }
}
