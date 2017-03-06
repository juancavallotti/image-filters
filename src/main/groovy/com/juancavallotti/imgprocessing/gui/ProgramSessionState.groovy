package com.juancavallotti.imgprocessing.gui

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.util.prefs.Preferences

/**
 * Created by juancavallotti on 2/22/15.
 */
@Singleton
class ProgramSessionState {

    private static final Logger logger = LogManager.getLogger(ProgramSessionState)

    private Preferences prefs = Preferences.userNodeForPackage(ProgramSessionState)

    private static final String PREFS_PREFIX = 'com.juancavallotti.imgprocessing.'

    private static final String LAST_OPENED_KEY = PREFS_PREFIX + 'lastEditedPhoto'


    File getLastOpened() {

        String fullPath = prefs.get(LAST_OPENED_KEY, null)

        logger.debug("Retrieved $fullPath as last opened file...")

        if (!fullPath) {
            return null
        }

        File ret = new File(fullPath)

        if (!ret.exists()) {
            logger.debug("File $ret.path was saved but the file no longer exists...")
            return null
        }

        return ret
    }

    void setLastOpened(File f) {

        if (f == null) {
            logger.warn("Attempting to save null file into preferences, will ignore this call.")
            return
        }


        if (!f.exists()) {
            logger.warn("Will not store non existing file into last viewed item: $f.path")
            return
        }

        prefs.put(LAST_OPENED_KEY, f.path)

        prefs.sync()
    }


}
