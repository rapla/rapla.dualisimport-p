package org.rapla.plugin.dualisimport;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.rapla.framework.RaplaContext;
import org.rapla.framework.RaplaException;
import org.rapla.gui.CalendarModel;
import org.rapla.gui.toolkit.DialogUI;

import javax.swing.*;

public class DualisImportWizardDialog extends DialogUI {
    private static final long serialVersionUID = 1L;

    private DualisImportPanel dualisImportPanel;
    private static DualisImportWizardDialog dlg;
    private CalendarModel model;


    public static DualisImportWizardDialog getInstance(RaplaContext sm, Component owner, boolean modal, CalendarModel model) throws RaplaException {
        if (dlg == null)
        {

            Component topLevel = getOwnerWindow(owner);
            if (topLevel instanceof Dialog)
                dlg = new DualisImportWizardDialog(sm, (Dialog) topLevel, model);
            else
                dlg = new DualisImportWizardDialog(sm, (Frame) topLevel, model);

            dlg.init(modal);
            dlg.setSize(800, 565);
            dlg.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        }

        return dlg;
    }

    protected DualisImportWizardDialog(RaplaContext sm, Dialog owner, CalendarModel model) throws RaplaException {
        super(sm, owner);

        dualisImportPanel =  new DualisImportPanel(sm, model);
        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                dualisImportPanel.updateTimeIntervalLabel();
            }
        });
    }

    protected DualisImportWizardDialog(RaplaContext sm, Frame owner, CalendarModel model) throws RaplaException {
        super(sm, owner);

        dualisImportPanel =  new DualisImportPanel(sm, model);

        addWindowFocusListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                dualisImportPanel.updateTimeIntervalLabel();

            }
        });
    }

    private void init(boolean modal) {
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(dualisImportPanel.getComponent());
        this.pack();
        this.setResizable(true);
        this.setTitle("Veranstaltungen anlegen");

    }

    @Override
    public void start() {
        if (!isVisible())
            super.start();

    }

}

