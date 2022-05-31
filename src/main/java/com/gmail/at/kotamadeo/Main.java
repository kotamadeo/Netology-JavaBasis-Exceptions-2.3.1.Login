package com.gmail.at.kotamadeo;

import com.gmail.at.kotamadeo.exceptions.UserNotFoundException;
import com.gmail.at.kotamadeo.program.Program;

public class Main {
    public static void main(String[] args) throws UserNotFoundException {
        var program = new Program();
        program.start();
    }
}