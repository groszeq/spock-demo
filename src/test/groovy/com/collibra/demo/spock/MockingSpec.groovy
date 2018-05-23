package com.collibra.demo.spock

import spock.lang.Specification

class MockingSpec extends Specification {

    class Publisher {
        List<Subscriber> subscribers = []
        void send(String message){ subscribers.each {it.receive(message)} }
    }

    interface Subscriber {
        void receive(String message)
    }

    //---------------------------------------------------

    Publisher publisher = new Publisher()
    Subscriber subscriber = Mock()
    Subscriber subscriber2 = Mock()

    def setup() {
        publisher.subscribers << subscriber
        publisher.subscribers << subscriber2
    }

    def 'simple mock spec 1'(){

        when:
            publisher.send("hello")

        then:
            1 * subscriber.receive("hello")
            1 * subscriber2.receive("hello")

    }

    /**

     1 * subscriber.receive("hello")      // exactly one call
     0 * subscriber.receive("hello")      // zero calls
     (1..3) * subscriber.receive("hello") // between one and three calls (inclusive)
     (1.._) * subscriber.receive("hello") // at least one call
     (_..3) * subscriber.receive("hello") // at most three calls
     _ * subscriber.receive("hello")      // any number of calls, including zero

     */




    def 'simple mock spec 2'(){

        when:
            publisher.send("hello")

        then:
            2 * _.receive("hello")

    }

    /**

     1 * subscriber.receive("hello") // a call to 'subscriber'
     1 * _.receive("hello")          // a call to any mock object

     */




    def 'simple mock spec 3'(){

        when:
            publisher.send("hello")

        then:
            1 * subscriber._("hello")
            1 * subscriber2./r.*e/("hello")

    }

    /**

     1 * subscriber.receive("hello") // a method named 'receive'
     1 * subscriber./r.*e/("hello")  // a method whose name matches the given regular expression

     */


    def 'simple mock spec 4'(){

        when:
            publisher.send("hello")

        then:
            1 * subscriber.receive(_ as String)
            1 * subscriber2.receive({it.length() == 5})

    }

    /**

     1 * subscriber.receive("hello")     // an argument that is equal to the String "hello"
     1 * subscriber.receive(!"hello")    // an argument that is unequal to the String "hello"
     1 * subscriber.receive()            // the empty argument list (would never match in our example)
     1 * subscriber.receive(_)           // any single argument (including null)
     1 * subscriber.receive(*_)          // any argument list (including the empty argument list)
     1 * subscriber.receive(!null)       // any non-null argument
     1 * subscriber.receive(_ as String) // any non-null argument that is-a String
     1 * subscriber.receive({ it.size() > 3 }) // an argument that satisfies the given predicate

     */

    def 'simple mock spec 5'(){

        when:
            publisher.send("hello")

        then:
            2 * _._(_ as String)

    }

    def 'simple mock spec 6'(){

        when:
            publisher.send("message1")

        then:
            1 * subscriber.receive("message1")

        when:
            publisher.send("message2")

        then:
            1 * subscriber.receive("message2")

    }

}
