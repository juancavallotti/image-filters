package com.juancavallotti.imgprocessing.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by juancavallotti on 2/21/15.
 */
public class ExecuteClosureListener implements ActionListener {

    def param
    Closure action

    @Override
    public void actionPerformed(ActionEvent e) {

        //set the parameters
        action.call(param)
    }
}
