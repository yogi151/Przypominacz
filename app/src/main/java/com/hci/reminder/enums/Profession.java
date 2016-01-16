package com.hci.reminder.enums;

public enum Profession {
    DIRECTOR(1), SCREENWRITER(2), MUSIC(3), CINEMATOGRAPHER(4), ORIGINAL_MATERIALS(5), ACTOR(6), PRODUCER(9),
    MONTAGE(10), COSTUME_DESIGNER(11);

    private int code;

    Profession(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}