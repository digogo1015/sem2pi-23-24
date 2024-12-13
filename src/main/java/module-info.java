module pt.ipp.isep.dei.esoft.project.all {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires eu.hansolo.fx.heatmap;
    requires eu.hansolo.toolbox;
    requires eu.hansolo.toolboxfx;
    requires com.almasb.fxgl.all;
    requires org.apache.commons.lang3;
    requires java.logging;
    requires AuthLib;
    requires commons.math;
    requires com.google.gson;
    requires java.base;

    exports pt.ipp.isep.dei.esoft.project.application.controller;
    exports pt.ipp.isep.dei.esoft.project.application.controller.authorization;
    exports pt.ipp.isep.dei.esoft.project.application.session;

    exports pt.ipp.isep.dei.esoft.project.database;

    exports pt.ipp.isep.dei.esoft.project.domain;

    exports pt.ipp.isep.dei.esoft.project.repository;

    exports pt.ipp.isep.dei.esoft.project.ui;
    exports pt.ipp.isep.dei.esoft.project.ui.console;
    exports pt.ipp.isep.dei.esoft.project.ui.console.authorization;
    exports pt.ipp.isep.dei.esoft.project.ui.console.menu;
    exports pt.ipp.isep.dei.esoft.project.ui.gui;

    exports pt.ipp.isep.dei.esoft.project.utils;

    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;

    opens pt.ipp.isep.dei.esoft.project.domain to com.google.gson;
}