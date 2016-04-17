package com.gocal.android.emulator

import com.gocal.android.emulator.avd.Avd
import org.gradle.api.Project

class EmulatorExtension {
    String avdName
    Avd avd

    private Project project

    EmulatorExtension(Project project) {
        this.project = project
    }

    Avd avd(Closure closure) {
        def avd = (Avd) project.configure(new Avd(), closure)
        this.avd = avd
        avd
    }

}
