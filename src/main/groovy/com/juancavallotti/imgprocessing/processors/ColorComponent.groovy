package com.juancavallotti.imgprocessing.processors

/**
 * Created by juancavallotti on 2/21/15.
 */
enum ColorComponent {

    RED(0xFF0000, 16),
    GREEN(0xFF00, 8),
    BLUE(0xFF, 0)

    int mask
    int position

    ColorComponent(int mask, int position) {
        this.mask = mask
        this.position = position
    }

}