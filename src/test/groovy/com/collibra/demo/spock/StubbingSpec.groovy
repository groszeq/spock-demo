package com.collibra.demo.spock

import spock.lang.Specification

class StubbingSpec extends Specification {

    class Publisher {
        List<Subscriber> subscribers = []
        List<String> send(String message){ subscribers.collect {it.receive(message)} }
    }

    interface Subscriber {
        String receive(String message)
    }

    //---------------------------------------------------

    Publisher publisher = new Publisher()

    Subscriber subscriber = Stub()
    Subscriber subscriber2 = Stub()

    def setup() {
        publisher.subscribers << subscriber
        publisher.subscribers << subscriber2
    }


    def 'simple stub spec 1'(){

        setup:
            _.receive("message1") >> "ok"
            _.receive("message2") >> "fail"

        expect:
            publisher.send("message1") == ["ok", "ok"]
            publisher.send("message2") == ["fail", "fail"]

    }

    def 'simple stub spec 2'(){

        setup:
            subscriber.receive("message1") >>> ["ok", "fail"]
            subscriber2.receive("message1") >>> ["fail", "ok"]

        expect:
            publisher.send("message1") == ["ok", "fail"]
            publisher.send("message1") == ["fail", "ok"]

    }

    def 'simple stub spec 3'(){

        setup:
            subscriber.receive("message2") >> { String message -> message.size() > 3 ? "ok" : "fail" }
            subscriber.receive("message3") >> { throw new InternalError("error") }
            subscriber.receive("message4") >>> ["ok", "fail", "ok"] >> { throw new InternalError() } >> "ok"

        when:
            publisher.send("message3")

        then:
            thrown(InternalError)

    }

}
