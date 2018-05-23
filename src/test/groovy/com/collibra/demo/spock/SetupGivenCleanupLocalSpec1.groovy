package com.collibra.demo.spock

import spock.lang.Issue
import spock.lang.Narrative
import spock.lang.See
import spock.lang.Specification
import spock.lang.Title

@Title("Spec for highlighting 'Specifications as Documentation' feature")
@Narrative("""
As a user
I want a first line of a file
to be what I expect
""")
class SetupGivenCleanupLocalSpec1 extends Specification {

    @See("http://spockframework.org/spec")
    @Issue("http://my.issues.org/FOO-1")
    def 'setup or given spec'(){

        given: 'a non empty file'
            MyFile file = new MyFile('SetupGivenCleanup.txt')

        and: 'a String we expect to be at the beginning'
            def fistLine = 'Spock is cool!'

        when: 'all the lines are read'
            List<String> lines = file.readLines()

        then: 'the first line in the file should be as we expected'
            lines[0] == fistLine

        and:
            lines.size() == 3

        cleanup: 'the file should be closed'
            file.close()
    }
}
