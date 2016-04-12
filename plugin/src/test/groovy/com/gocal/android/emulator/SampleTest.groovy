package com.gocal.android.emulator

import com.android.ddmlib.AndroidDebugBridge
import org.apache.commons.io.FileUtils
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

class SampleTest {

    @Test
    public void testStartEmulator() {

        def projectDir = new File('..\\sample')

        Project project = ProjectBuilder.builder().withProjectDir(projectDir).build()
        project.getPlugins().apply 'com.android.application'
        def androidExtension = project.android;
        def androidHome = getAndroidHome(androidExtension)

        def emulatorFile = FileUtils.getFile(androidHome, "tools", "emulator.exe")
        def adbFile = FileUtils.getFile(androidHome, "platform-tools", "adb.exe")
        def emulator = new AndroidEmulator(emulatorFile.absolutePath, adbFile.absolutePath)
        def version = emulator.stop()
        if(version != null) {

        }
    }

    private static String getAndroidHome(androidExtension) {
        def androidHome
        if (androidExtension.hasProperty('sdkHandler')) {
            androidHome = "${androidExtension.sdkHandler.sdkFolder}"
        } else if (androidExtension.hasProperty('sdkDirectory')) {
            androidHome = "${androidExtension.sdkDirectory}"
        } else {
            throw new IllegalStateException('The android plugin is not exposing the SDK folder in an expected way.')
        }
        androidHome
    }
}