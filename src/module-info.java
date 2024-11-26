module SmartHouse {
    requires java.base;
    requires org.junit.jupiter.api;
    requires org.junit.platform.commons;
    requires org.junit.platform.suite.api;

    exports smarthouse.controllers;
    exports smarthouse.concurrency;
    exports smarthouse.utils;
    exports smarthouse.models;
    exports smarthouse.resources.logs;

    opens smarthouse.controllers to org.junit.platform.commons;
    opens smarthouse.concurrency to org.junit.platform.commons;
    opens smarthouse.utils to org.junit.platform.commons;
    opens smarthouse.models to org.junit.platform.commons;
    opens smarthouse.resources.logs to org.junit.platform.commons;

    opens test to org.junit.platform.commons;
}

