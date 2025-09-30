plugins {
    // no plugins at root
}

allprojects {
    tasks.withType<org.gradle.api.tasks.compile.JavaCompile> {
        options.encoding = "UTF-8"
    }
}