package com.collibra.demo.spock;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MyFile {

    private InputStream fip;

    public MyFile(String name) {
        System.out.println("opening");
        fip = MyFile.class.getClassLoader().getResourceAsStream(name);

    }

    public List<String> readLines(){

        Scanner r = new Scanner(fip);
        List<String> lines = new LinkedList<>();
        lines.add( r.nextLine() );
        lines.add( r.nextLine() );

        return lines;

    }

    public final void close() throws IOException {
        System.out.println("closing");
        fip.close();
    }

}
