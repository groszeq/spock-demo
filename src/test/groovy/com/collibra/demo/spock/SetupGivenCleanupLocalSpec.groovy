package com.collibra.demo.spock

import spock.lang.Specification

class SetupGivenCleanupLocalSpec extends Specification {

    def 'setup or given spec'(){

        setup:
            MyFile file = new MyFile('SetupGivenCleanup.txt')

        when:
            List<String> lines = file.readLines()

        then:
            lines[0] == 'Spock is cool!'

        cleanup:
            file.close()
    }
}
