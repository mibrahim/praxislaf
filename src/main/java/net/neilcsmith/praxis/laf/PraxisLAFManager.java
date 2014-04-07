/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2010 Neil C Smith.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation; either version 2.1 of the License,
 * or (at your option) any later version.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this work; if not, see http://www.gnu.org/licenses/
 *
 *
 * Please visit http://neilcsmith.net if you need additional information or
 * have any questions.
 *
 */
package net.neilcsmith.praxis.laf;

import java.awt.EventQueue;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Neil C Smith
 * @author Mohamed Ibrahim
 */
public class PraxisLAFManager {

    private static final PraxisLAFManager instance = new PraxisLAFManager();

    private PraxisLAFManager() {
    }

    public void installUI() throws Exception {
        if (EventQueue.isDispatchThread()) {
            doInstall();
        } else {
            try {
                EventQueue.invokeAndWait(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            doInstall();
                        } catch (IOException ex) {
                            Logger.getLogger(PraxisLAFManager.class.getName()).log(Level.SEVERE, "Problem installing the UI", ex);
                        }
                    }
                });
            } catch (InterruptedException | InvocationTargetException ex) {
                throw new Exception("Could not install praxislaf", ex);
            }
        }
    }

    private void doInstall() throws IOException {
        boolean useSystemTheme;
        try {
            useSystemTheme = Boolean.parseBoolean(System.getProperty("praxis.useSystemTheme", "false"));
        } catch (Exception ex) {
            useSystemTheme = false;
        }
        if (useSystemTheme) {
            return;
        }
        try {
            LookAndFeel laf = new PraxisLookAndFeel();
            UIManager.setLookAndFeel(laf);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(PraxisLAFManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PraxisLAFManager getInstance() {
        return instance;
    }

}
